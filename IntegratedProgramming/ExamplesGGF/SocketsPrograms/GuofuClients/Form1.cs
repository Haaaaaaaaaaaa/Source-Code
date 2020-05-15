using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Net;
using System.Net.Sockets;
using System.Threading;
using System.IO;

namespace GuofuClients
{
    public partial class GuofuClient : Form
    {
        private Socket client;                                  // 客户端的socket
        private bool IsConnect = false;                         // 是否连接了;
        private bool IsUpload = false;                          // 是否处于上传状态;
        private Thread ReceTh;                                  // 不断接收服务器的内容的线程
        private int maxSize = 1024 * 8;                         // 缓冲区的大小
        public static int highestPercentageReached = 0;         // 当前传输文件的进度;
        ProcessBarDiage processBarDiage;                        // 进度条窗体;
        private bool isCancel = false;                          // 是否取消了下载



        public GuofuClient()
        {
            InitializeComponent();
        }

        /// <summary>
        /// 连接server端的方法：，创建socket，创建节点（包含IP和端口号），然后发起请求连接，最后创建一个接收服务器消息的线程。
        /// </summary>
        private void ConnectServer()
        {
            // 创建客户端的socket;
            this.client = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
            // 建立连接
            EndPoint endPoint = new IPEndPoint(IPAddress.Parse(ipText.Text), Convert.ToInt32(portText.Text));
            // EndPoint endPoint = new IPEndPoint(IPAddress.Parse("101.200.73.209"), 5188);
            try
            {
                client.Connect(endPoint);
                IsConnect = true;
                // 创建一个后台线程，不断的接收服务器发来的消息;
                ReceTh = new Thread(ReceiceData);
                ReceTh.IsBackground = true;
                ReceTh.Start();
            }
            catch
            {
                MessageBox.Show("连接失败！");
                IsConnect = false;
            }
        }

        /// <summary>
        /// 断开连接：首先关闭通信的socket，然后判断接收消息的线程是否存在，若存在则关闭。
        /// </summary>
        private void CloseServer()
        {
            IsConnect = false;                                              // 标记未连接状态
            if (client != null)
            {
                this.client.Shutdown(SocketShutdown.Both);                      // 禁止接收和发送
                this.client.Close();                                            // 关闭socket
            } 
            if (ReceTh != null)
            {
                ReceTh.Abort();                                                 // 关闭线程              
            }

        }

        /// <summary>
        /// 连接服务器的按钮触发的事件
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void ConnectBtn_Click(object sender, EventArgs e)
        {
            if(!IsConnect)                                                     // 连接服务器 ;
            {
                ConnectServer();                                              // 实现连接
               if(IsConnect)
                {
                    
                    MessageBox.Show("连接成功！");
                    ConnectBtn.Text = "断开连接";
                    Sendbtn.Enabled = true;
                    downBtn.Enabled = true;

                }
            }
            else                                                               // 断开服务器
            {
                CloseServer();                                                 // 实现断开
                filesView.Items.Clear();                                       // 清空文件列表信息
                Sendbtn.Enabled = false;
                downBtn.Enabled = false;
                ConnectBtn.Text = "连接";
            }
        }

        // 不断的接收服务器发来的内容
        void ReceiceData()
        {
            while (IsConnect)
            {
                byte[] buffer = new byte[maxSize];                          // 一次可接受8KB大小的数据;
                try
                {
                    int count = client.Receive(buffer);                     // 接收服务器发来的内容;
                    if (count == 0)
                    {
                        MessageBox.Show("服务器断开连接");
                        filesView.Items.Clear();                            // 清空文件列表信息
                        Sendbtn.Enabled = false;
                        downBtn.Enabled = false;
                        ConnectBtn.Text = "连接";
                        break;
                    }
                    if (buffer[0] == 0)                                     // 服务器发来了目录信息，需要显示在目录列表中；
                    {
                        FileListUpadate(buffer, 1, count - 1);
                    }
                    else                                                    // 接收服务器发送来的文件
                        if (buffer[0] == 1)
                    {
                        string filename = saveFile.FileName;                                // 文件的绝对路径;     
                        ReceiveAFile(filename,buffer.Skip(1).Take(count - 1).ToArray());     // 使用分组接收文件
                    }
                }
                catch
                {
                     // 出错处理？？？
                }
            }
        }

        /// <summary>
        /// 处理来自服务器发来的文件信息
        /// </summary>
        /// <param name="buffer">保存目录信息</param>
        /// <param name="x">起始下标</param>
        /// <param name="y">长度</param>
        private void FileListUpadate(byte[] buffer,int x ,int y)
        {
            string recStr = Encoding.UTF8.GetString(buffer, x, y); // 将字节类型数据转换成字符类型;
            string[] fnameList = recStr.Split(new string[] { "[#]" }, StringSplitOptions.RemoveEmptyEntries); // 分割每个文件信息；

            filesView.Items.Clear();                                                                          // 清空目录
            foreach (var fi in fnameList)                                                                     // 添加信息;
            {
                filesView.Items.Add(fi);
            }
            filesView.SelectedIndex = 0;                                                                      //默认选择第一个;
        }

        // 初始化载入的函数
        private void Form1_Load(object sender, EventArgs e)
        {
            // 取消检查线程间的操作，，这种方法不可取，可以使用回调函数取代;
            Control.CheckForIllegalCrossThreadCalls = false;
        }

        /// <summary>
        /// 上传文件的按钮事件，打开文件视图，选择文件，开始上传
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Sendbtn_Click(object sender, EventArgs e)
        {
            string filePath = string.Empty;                                         // 用来保存文件的绝对路径；
            this.isCancel = false;                                                  // 初始化为没有取消
            //打开文件视图;
            using (OpenFileDialog openFileDialog = new OpenFileDialog())
            {
                openFileDialog.InitialDirectory = "D:\\";
                openFileDialog.Title = "选择上传的文件";
                openFileDialog.Filter = "txt files (*.txt)|*.txt|All files (*.*)|*.*";
                openFileDialog.FilterIndex = 2;
                openFileDialog.RestoreDirectory = true;

                if (openFileDialog.ShowDialog() == DialogResult.OK)
                {
                    filePath = openFileDialog.FileName;             // 上传文件的绝对路径
                    IsUpload = true;                                // 标记在上传中;

                    // 设置使用backgroundWorker
                    processBarDiage = new ProcessBarDiage();                // 进度条
                    processBarDiage.TransfEvent+=(bool judge)=>{ this.isCancel = judge; };
                    highestPercentageReached = 0;
                    processBarDiage.Show(this);                             
                    upLoad_fileWorker1.RunWorkerAsync(filePath);            // 使用backgroundWorker
                }
            }
        }

        /// <summary>
        /// 使用分组上传文件
        /// </summary>
        /// <param name="o">文件的绝对路径</param>
        private void UpLoad_file(object o, BackgroundWorker worker, DoWorkEventArgs e)
        {
            if (worker.CancellationPending)                                     // 如果取消了线程
            {
                e.Cancel = true;
            }
            else
            {
                string fullname = o as string;                                              // 文件的绝对路径
                FileInfo file = new FileInfo(fullname);                                     // 文件对象
                long fileLength = file.Length;                                              // 文件的长度
                string name = file.Name;                                                    // 文件的名字

                byte[] buffer = new byte[maxSize];                                          // 每次分组的缓冲区  
                int readLength = 0;                                                         // 每次读取文件的长度
                long sendLength = 0;                                                        // 总共已发送的长度 

                processBarDiage.setLabel1($"\"{name}\"正在上传中");
                Sendbtn.Enabled = false;
                ConnectBtn.Enabled = false;
                downBtn.Enabled = false;
                exitBtn.Enabled = false;

                try
                {
                    FileStream fs = new FileStream(fullname, FileMode.Open, FileAccess.Read);// 文件读写对象
                                                                                             // 文件信息处理
                    List<byte> first = new List<byte>();
                    first.Add(1);                                                            // 标记位   
                    string infor = string.Format("{0}*{1}", name, fileLength);                // 文件名*文件长度
                    byte[] temp = Encoding.UTF8.GetBytes(infor);                             // 使用utf8编码字节数组，长度会边长
                    first.Add(Convert.ToByte(temp.Length));                                  // 文件信息的长度，用于标记
                    first.AddRange(temp);                                                    // 加入文件信息

                    byte[] newbuffer = new byte[maxSize - first.Count];              // 第一个分组能发送的文件内容的大小的缓冲区
                    readLength = fs.Read(newbuffer, 0, newbuffer.Length);
                    first.AddRange(newbuffer);

                    client.Send(first.ToArray(), 0, first.Count, SocketFlags.None);             // 发送第一个分组   
                    sendLength += readLength;
                    precentChange(sendLength, fileLength, worker);                              // 更新进度

                    // 剩余的文件内容
                    while ((readLength = fs.Read(buffer, 0, buffer.Length)) > 0 && sendLength < fileLength)
                    {
                        if (worker.CancellationPending)                                         // 如果取消了线程
                        {
                            e.Cancel = true;
                            CloseServer();                                                      // 断开
                            ConnectServer();                                                    // 重连;
                            break;
                        }
                        client.Send(buffer, 0, readLength, SocketFlags.None);
                        sendLength += readLength;
                        precentChange(sendLength, fileLength, worker);                          // 更新进度
                    }

                    fs.Close();                                                                 // 关闭文件操作流
                    this.processBarDiage.Close();
                }
                catch(Exception i)
                {
                    e.Result = i;

                }
            }          
        }

        ///// <summary>
        ///// 将文件发送给服务器，将文件信息保存在字节数组中     // 未采用分组发送
        ///// </summary>
        ///// <param name="o">参数为文件的绝对路径</param>
        //private void upload_files(object o)
        //{
        //    string filename = o as string;

        //    FileInfo file = new FileInfo(filename);                                     // 文件对象
        //    byte[] fileBuffer = new byte[file.Length];                                  // 创建存储的数组
        //    int r = 0;
        //    using (FileStream fs = new FileStream(filename, FileMode.Open, FileAccess.Read))// 读文件流对象
        //    {
        //        r = fs.Read(fileBuffer, 0, fileBuffer.Length);                              // 读取文件内容存入数组buf
        //    }

        //    byte[] name = Encoding.UTF8.GetBytes(file.Name);                            // 文件名转成byte[]

        //    List<byte> bufferList = new List<byte>();                           
        //    bufferList.Add(1);                                                          // 添加标记位1;
        //    bufferList.Add(Convert.ToByte(name.Length));                                // 添加文件名的长度
        //    bufferList.AddRange(name);                                                  // 添加文件名字                               
        //    bufferList.AddRange(fileBuffer);                                            // 添加文件内容
        //    byte[] newbuffer = bufferList.ToArray();                                    // 转换成字节数组

        //    try
        //    {
        //        client.Send(newbuffer,0,r+2+name.Length,SocketFlags.None);                  // 发送到服务器;
        //        filesView.Items.Add(file.Name);                                             // 文件发送成功，添加到文件列表;
        //    }catch            {
        //        MessageBox.Show("上传失败！");                                              

        //    }           
        //}

        /// <summary>
        /// 请求下载的按钮事件，向服务器发送文件的名字，以首位2为标记，返回的内容由：ReceiceData处理
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void downBtn_Click(object sender, EventArgs e)
        {
            string filename = filesView.SelectedItem.ToString();                // 下载的文件的名字
            saveFile.InitialDirectory = Environment.CurrentDirectory;           // 程序的当前文件夹下;
            saveFile.FileName = filename;                                       // 默认的文件名字;
            saveFile.Filter = "txt files (*.txt)|*.txt|All files (*.*)|*.*";    // 设置过滤器
            saveFile.FilterIndex = 2;                                           // 默认过滤器选项
            saveFile.RestoreDirectory = true;
            this.isCancel = false;                                              // 初始化没有取消下载;

            if (saveFile.ShowDialog() == DialogResult.OK)                        // 确定下载,发送请求
            {
                List<byte> bufList = new List<byte>();                           // 作为中间转换
                bufList.Add(2);                                                  // 首位设置为2
                byte[] fbname = Encoding.UTF8.GetBytes(filename);                // 将文件名转成字节数组
                bufList.AddRange(fbname);                                        // 完整的请求

                try
                {
                    client.Send(bufList.ToArray());                              // 发送下载请求,保存文件会在ReceiceData中处理
                }
                catch
                {
                    MessageBox.Show("下载失败！");
                }
            }
                                                                                 // 否则不请求
        }

        // 打开新窗口，进度框
        private void OpenProcess()
        {
            ProcessBarDiage barDiage = new ProcessBarDiage();
            barDiage.TransfEvent += (bool judge) => { this.isCancel = judge; };
            highestPercentageReached = 0;
            barDiage.label1.Text = $"正在下载\"{filesView.SelectedItem.ToString()}\"";
            barDiage.Show();
        }

        // 接收服务器发来的文件//下载文件
        private void ReceiveAFile(string filename,byte[] rece)
        {
            MethodInvoker mi = new MethodInvoker(OpenProcess);
            BeginInvoke(mi);

            int len = Convert.ToInt32(rece[0]);                                           // 文件信息的长度
            string infor = Encoding.UTF8.GetString(rece.Skip(1).Take(len).ToArray());     // 文件信息(仅文件长度);
            long fileLength = Convert.ToInt64(infor);
            long recLength = 0;                                                            // 已接收的文件长度
            FileStream fs;

            try
            {
                fs = new FileStream(filename, FileMode.Create);
                fs.Write(rece, 1 + len, rece.Length - 1 - len);                     // 写入第一个分组的文件内容 
                fs.Flush();
                recLength += rece.Length - 1 - len;
                precentChange(recLength, fileLength);
                                                                                    // 接收并写入剩余分组的文件内容
                byte[] buffer = new byte[maxSize];                                  // 8KB的缓冲区
                int r = 0;                                                          // 每次接收到的字节大小
                while ((!isCancel)&&(recLength < fileLength))
                {
                    r = client.Receive(buffer, 0, buffer.Length, SocketFlags.None);
                    recLength += r;
                    fs.Write(buffer, 0, r);
                    fs.Flush();
                    precentChange(recLength, fileLength);
                }
                fs.Close();
                // 下载成功，  还是取消的下载，下载不成功
                if (!isCancel && recLength == fileLength)
                {
                    MessageBox.Show($"\"{filename}\"下载完成。");
                }
                else
                {
                    MessageBox.Show($"\"{filename}\"下载取消或失败。");
                    DeleteFile(filename);                                               // 删除文件;
                    isCancel = false;                                                   // 回复默认
                    CloseServer();                                                      // 断开
                } 
            }
            catch(Exception e)
            {
                ConnectServer();                                                    // 重连;
                MessageBox.Show("重新连接！");
                // 出错处理 
            }
        }

        // 下载出错，删除下载的文件
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

        /// <summary>
        /// 程序退出
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void exitBtn_Click(object sender, EventArgs e)          
        {
            // ???需要释放资源吗
            Application.Exit();
        }

        /// <summary>
        /// 窗口关闭触发的事件
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Form1_FormClosed(object sender, FormClosedEventArgs e)
        {
            // 资源释放
        }


        // 用上传文件的后台线程
        private void upLoad_fileWorker1_DoWork(object sender, DoWorkEventArgs e)
        {
            BackgroundWorker worker = sender as BackgroundWorker;
            UpLoad_file((string)e.Argument,worker,e);

        }
        /// <summary>
        /// 在发送中
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void upLoad_fileWorker1_ProgressChanged(object sender, ProgressChangedEventArgs e)
        {
            //this.processBarDiage.set_ValuePercent(e.ProgressPercentage);
            if (this.isCancel)                                                              // 取消了上传
            {
                this.upLoad_fileWorker1.CancelAsync();
            }
        }

        /// <summary>
        /// 发送的线程结束时;
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void upLoad_fileWorker1_RunWorkerCompleted(object sender, RunWorkerCompletedEventArgs e)
        {

            if (e.Error != null)
            {
                MessageBox.Show(e.Error.Message + " 上传出错！");
                setupBtn();
            }
            else if (e.Cancelled)
            {
                MessageBox.Show("取消了上传!");
                setupBtn();
            }
            else if (e.Result != null)
            {
                MessageBox.Show(e.Result.ToString() + "服务器关闭，上传出错");
                setclosebtn();
            }
            else
            {
                MessageBox.Show("上传成功！");
                setupBtn();
            }
        }

        // 上传成功，上传取消，e.Error!=0，处理按钮
        private void setupBtn()
        {
            isCancel = false;
            Sendbtn.Enabled = true;
            ConnectBtn.Enabled = true;
            downBtn.Enabled = true;
            exitBtn.Enabled = true;
        }
        // 服务器关闭设置按钮和列表
        private void setclosebtn()
        {
            this.processBarDiage.Close();                                               // ？？？？
            CloseServer();                                                              // 断开
            filesView.Items.Clear();                            // 清空文件列表信息
            Sendbtn.Enabled = false;
            downBtn.Enabled = false;
            ConnectBtn.Enabled = true;
            ConnectBtn.Text = "连接";
        }

        // 上传计算进度的函数，并显示
        private void precentChange(long x,long y,BackgroundWorker worker)
        {
            // Report progress as a percentage of the total task.
            int percentComplete = (int)(x / (double)y * 100);
            if (percentComplete > highestPercentageReached)
            {
                highestPercentageReached = percentComplete;
                worker.ReportProgress(percentComplete);
            }
        }
        // 下载时，计算进度
        private void precentChange(long x, long y )
        {
            // Report progress as a percentage of the total task.
            int percentComplete = (int)(x / (double)y * 100);
            if (percentComplete > highestPercentageReached)
            {
                highestPercentageReached = percentComplete;
            }
        }

    }
}
