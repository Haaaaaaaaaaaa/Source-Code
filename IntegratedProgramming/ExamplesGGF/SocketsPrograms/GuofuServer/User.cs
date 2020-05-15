using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net;
using System.Net.Sockets;

namespace GuofuServer
{
    class User
    {
        private int id;                             //用户的id
        //private string username;                    //用户名
        //private string password;                    //密码
        private Socket sClient;                     //与用户进行通信的socket

        public User(int id, Socket sclient)         //构造函数
        {
            this.id = id;
            this.sClient = sclient;
        }

        public int Id                                //id可读
        {
            get { return this.id; }
        }

        public Socket SClient                        //socket可读
        {
            get { return this.sClient; }
        }

        public override string ToString()            // 重写对象的输出，输出客户端的地址;
        {
            return this.sClient.RemoteEndPoint.ToString();
        }
    }
}
