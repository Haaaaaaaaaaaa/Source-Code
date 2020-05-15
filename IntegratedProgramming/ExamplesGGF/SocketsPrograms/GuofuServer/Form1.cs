using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using System.Windows.Forms;

namespace GuofuServer
{
    public partial class GuguofuServer : Form
    {
        private bool IsListen = false;                      // 是否正在监听;
        private Socket server;                              // 服务器端的socket;
        // private Socket client;                           // 负责进行通信的socket;
        private List<User> userList = new List<User>();     // 记录在线的用户;
        private Thread listenWatch;                         // 负责监听的线程;
        private Thread Commicate;                           // 负责通信的线程;
        private int maxSize = 1024 * 8;                     // 缓冲区的大小

        public GuguofuServer()
        {
            InitializeComponent();
        }

        private void ListenBtn_Click(object sender, EventArgs e)
        {
            if(!IsListen)
            {
                ListenBtn.Text = "停止监听";
                IsListen = true;

                // 初始化本地绑定的参数
                server = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
                IPAddress ip = IPAddress.Any;
                IPEndPoint local = new IPEndPoint(ip, Convert.ToInt32(portText.Text));
                server.Bind(local);                                 // 绑定监听地址
                server.Listen(10);                                  // 设置监听的最大队列;
                messageList.Items.Add("开始监听.....");             // 开始监听的提示
                
                // 创建监听线程
                listenWatch = new Thread(Listening);
                listenWatch.IsBackground = true;
                listenWatch.Start();
            }
            else
            {
                ListenBtn.Text = "开始监听";
                IsListen = false;

                // 关闭服务器的socket
                listenWatch.Abort();
                server.Close();
                // 关闭在线的用户？？

            }
        }

        // 监听的方法
        private void Listening()
        {
            while(true)
            {
                Socket client = server.Accept();                           // 获取客户端的socket
                User user = new User(userList.Count, client);       // 创建用户对象
                userList.Add(user);                                 // 添加到列表中
                AdressList.Items.Add(user.ToString());              // 添加到下拉列表中
                messageList.Items.Add($"{user.ToString()}连接成功");  // 添加到消息显示中;

                // 向客户端发送服务器的文件名信息，使用首位0作为标记
                SendAllFilesName(user.SClient);

                // 开启一个新的线程，不断的接收客户端发来的消息
                Commicate = new Thread(Communicating);
                Commicate.IsBackground = true;
                Commicate.Start(user);

            }

        }
        
        // 通信的方法
        private void Communicating(object obj)
        {
            User user = obj as User;
            Socket client = user.SClient;

            while(true)
            {
                // 接收客户端的消息;
                byte[] rece = new byte[maxSize];                        // 一次可接收8KB大小的内容
                try
                {
                    int count = client.Receive(rece);
                    if (count != 0)
                    {
                        // 根据客户端发来不同信息进行不同的操作;
                        if (rece[0] == 3)                                   // 属于文件的目录信息或者文字消息
                        {
                            string message = Encoding.UTF8.GetString(rece);
                            messageList.Items.Add("3");
                        }
                        else
                            if(rece[0]==1)                                                  // 用户发来文件，保存文件
                        {
                            messageList.Items.Add("2");
                            ReceiveAFile(rece.Skip(1).Take(count - 1).ToArray(), user);
                            //Thread receFileTh = new Thread(() => ReceiveAFile(rece.Skip(1).Take(count - 1).ToArray(), user));
                            //receFileTh.IsBackground = true;
                            //receFileTh.Start();
                        }
                        else if(rece[0] == 2)                                                           // 客户端请求下载文件
                        {
                            string filename = Encoding.UTF8.GetString(rece.Skip(1).Take(count-1).ToArray()); // 文件名字

                            //Thread sendFileTh = new Thread(()=>SendAFile(filename,user));              // 创建一个发送文件的线程,使用了lambda表达式
                            //sendFileTh.IsBackground = true;
                            //sendFileTh.Start();
                            SendFileServal(filename, user);
                            messageList.Items.Add("2");
                        }
                    }
                    else
                    {
                        messageList.Items.Add($"客户端{user.ToString()}断开了连接");  // 客户端断开了连接
                        AdressList.Items.Remove(user.ToString());                     //删除用户对象;
                        client.Close();
                        userList.Remove(user);
                        break;
                    }
                }catch
                {
                    messageList.Items.Add($"客户端{user.ToString()}断开了连接");  // 客户端断开了连接
                    AdressList.Items.Remove(user.ToString());                     //删除用户对象;
                    client.Close();
                    userList.Remove(user);
                    break;
                }
            }
        }

        /// <summary>
        /// 接收文件
        /// </summary>
        /// <param name="rece">文件的第一个分组</param>
        /// <param name="user">用户对象</param>
        private void ReceiveAFile(byte[] rece, User user)                                   // 注意转码的长度问题
        {
            int len = Convert.ToInt32(rece[0]);                                          // 文件信息的长度
            string infor = Encoding.UTF8.GetString(rece.Skip(1).Take(len).ToArray());     // 文件信息;
            string filename = infor.Split('*').First();
            long fileLength = Convert.ToInt64(infor.Split('*').Last());
            long recLength = 0;                                                            // 已接收的文件长度

            try
            {
                FileStream fs = new FileStream(filename, FileMode.Create);
                fs.Write(rece, 1 + len, rece.Length - 1 - len);               // 写入第一个分组的文件内容 
                fs.Flush();
                recLength += rece.Length - 1 - len;
                // 接收并写入剩余分组的文件内容

                byte[] buffer = new byte[maxSize];                                // 8KB的缓冲区
                int r = 0;
                while (recLength < fileLength)
                {
                    r = user.SClient.Receive(buffer, 0, buffer.Length, SocketFlags.None);
                    if (r == 0)
                        break;
                    recLength += r;
                    fs.Write(buffer, 0, r);
                    fs.Flush();
                }
                fs.Close();
                if(recLength==fileLength)
                {
                    messageList.Items.Add($"{user.ToString()}发来的文件{filename}接收成功");
                    //// 向所用的用户更新当前服务器的文件目录//？？？有问题
                    //SendToAllFilesName();
                    // 对当前的用户更新文件消息
                    SendAllFilesName(user.SClient);
                }
                else
                {
                    messageList.Items.Add($"{user.ToString()}取消了上传");
                    DeleteFile(filename);
                    userList.Remove(user);                                              // 删除该用户，重连后会重新创建
                }
                
            }
            catch
            {
                messageList.Items.Add("上传出错！");
                DeleteFile(filename);
            }

        }

        // 使用分组发送
        private void SendFileServal(object o , object u)
        {
            string fullname = o as string;                                              // 文件的绝对路径
            User user = u as User;                                                      // 正在通话的用户
            Socket client = user.SClient;                                               // 客户端socket;
            FileInfo file = new FileInfo(fullname);                                     // 文件对象
            long fileLength = file.Length;                                              // 文件大小
            string name = file.Name;                                                    // 文件名字

            byte[] buffer = new byte[maxSize];                                          // 分组发送的缓冲区
            int readLength = 0;                                                         // 每次发送的字节大小
            long sendLength = 0;                                                        // 总共发送的发送的文件大小

            try
            {
                FileStream fs = new FileStream(fullname, FileMode.Open, FileAccess.Read);// 文件读写对象
                // 文件信息处理
                List<byte> first = new List<byte>();
                first.Add(1);                                                            // 标记位   
                //string infor = string.Format("{0}*{1}", name, fileLength);             // 文件名*文件长度
                string infor = string.Format("{0}", fileLength);                         // 只标记文件长度   
                byte[] temp = Encoding.UTF8.GetBytes(infor);                             // 使用utf8编码字节数组，长度会边长
                first.Add(Convert.ToByte(temp.Length));                                  // 文件信息的长度，用于标记
                first.AddRange(temp);                                                    // 加入文件信息

                byte[] newbuffer = new byte[maxSize - first.Count];                      // 第一个分组能发送的文件内容的大小的缓冲区
                readLength = fs.Read(newbuffer, 0, newbuffer.Length);
                first.AddRange(newbuffer);

                client.Send(first.ToArray(), 0, first.Count, SocketFlags.None);           // 发送第一个分组   
                sendLength += readLength;

                // 剩余的文件内容
                while ((readLength = fs.Read(buffer, 0, buffer.Length)) > 0 && sendLength < fileLength)
                {
                    client.Send(buffer, 0, readLength, SocketFlags.None);
                    sendLength += readLength;
                }
                fs.Close();
                messageList.Items.Add($"文件{name}发送到{user.ToString()}成功。");
            }
            catch(Exception e)
            {
                // 失败处理
                messageList.Items.Add($"{name}发送到{user.ToString()}失败。");
                //MessageBox.Show(e.ToString());
                userList.Remove(user);                                                      // 删除重新创建; 
            }
        }

        // 发送文件，不使用分组发送
        private void SendAFile(object o,object u)
        {
            string filename = o as string;
            User user = u as User;
            Socket client = user.SClient; 

            FileInfo file = new FileInfo(filename);                                     // 文件对象
            byte[] fileBuffer = new byte[file.Length];                                  // 创建存储的数组
            int r = 0;                                                                  // 存放文件的大小（B）
            using (FileStream fs = new FileStream(filename, FileMode.Open, FileAccess.Read))// 读文件流对象
            {
                r = fs.Read(fileBuffer, 0, fileBuffer.Length);                          // 读取文件内容存入数组buf
            }

            List<byte> bufferList = new List<byte>();
            bufferList.Add(1);                                                          // 添加标记位1;       
            bufferList.AddRange(fileBuffer);                                            // 添加文件内容

            client.Send(bufferList.ToArray(), 0,r+1, SocketFlags.None);                 // 发送到服务器;

            messageList.Items.Add($"{file.Name}发送到{client.RemoteEndPoint.ToString()}");   // 文件发送成功，显示提示
        }

        private void SendToAllFilesName()
        {
            foreach(var u in userList)
            {
                SendAllFilesName(u.SClient);
            }
        }

        private void SendAllFilesName(Socket client)
        {
            // 向客户端发送服务器的文件名信息，使用首位0作为标记
            byte[] newbuffer = GetFilesNames();
            client.Send(newbuffer);
        }

        // 读取本地的文件名信息，返回字节数组,首位用0作为标记    //可以添加参数，默认时当前目录下;
        private byte[] GetFilesNames()
        {
            //读取当前文件夹下的文件名字，以"[#]"分割;
            DirectoryInfo dir = new DirectoryInfo(Directory.GetCurrentDirectory());
            FileSystemInfo[] infos = dir.GetFileSystemInfos();
            string fname = "";
            foreach (var temp in infos)
                fname += "[#]" + temp.Name;
            byte[] buffer = Encoding.UTF8.GetBytes(fname);          // 文件目录信息
            List<byte> flist = new List<byte>();
            flist.Add(0);
            flist.AddRange(buffer);
            byte[] newbuffer = flist.ToArray();
            return newbuffer;
        }

        /// <summary>
        /// 接收文件出错，删除接收的文件
        /// </summary>
        /// <param name="fullFilename"></param>
        void DeleteFile(string fullFilename)
        {
            if (File.Exists(fullFilename))
            {
                // Use a try block to catch IOExceptions, to
                // handle the case of the file already being
                // opened by another process.
                try
                {
                    File.Delete(fullFilename);
                }
                catch
                {
                    //Console.WriteLine(e.Message);
                    return;
                }
            }
        }


        private void Form1_Load(object sender, EventArgs e)
        {
            Control.CheckForIllegalCrossThreadCalls = false;    // 关闭线程检查，不安全;
                                                                // 可以采用回调函数替换;
        }

        /// <summary>
        /// 退出程序
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void exitBtn_Click(object sender, EventArgs e)
        {
            // 释放资源
            byte[] buffer = new byte[maxSize];
            foreach(var u in userList)
            {
                u.SClient.Send(buffer, 0,0, SocketFlags.None)
;               u.SClient.Shutdown(SocketShutdown.Both);
                u.SClient.Close();
            }
            userList.Clear();
            Application.Exit();     // 资源释放
        }

        /// <summary>
        /// 窗口关闭时，释放资源
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Form1_FormClosed(object sender, FormClosedEventArgs e)
        {
            // 释放资源
        }
    }
}
