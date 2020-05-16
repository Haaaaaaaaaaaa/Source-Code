/*
Description:Socket 服务器端编程主要步骤如下：
				1、socket() 创建一个 Socket
    			2、bind()
    			3、listen() 监听
    			4、accept() 接收连接的请求
    			5、进行会话
    			6、close() 关闭 Socket
Version:	Dev-C++ 5.11
Date:		2020/05/01
*/ 

#include <stdio.h>          // Needed for printf()
#include <string.h>         // Needed for memcpy() and strcpy()
#include <windows.h>        // Needed for all Winsock stuff
#include<stdlib.h>			//解决程序结果一闪而过 
//-----------------------------------------------------
#define  PORT_NUM   5000      // 服务器端口号
#define  MAX_LISTEN    3      // 最大监听数

unsigned int         server_s;        // Server socket 标示符
struct sockaddr_in   server_addr;     // Server 地址
unsigned int         client_s;        // Client socket标示符
struct sockaddr_in   client_addr;     // Client Internet address
struct in_addr       client_ip_addr;  // Client IP address
int                  addr_len;        // Internet address length
char                 out_buf[1024*4];    // 100-byte 输出缓冲区
char                 in_buf[1024*4];     // 100-byte 接收缓冲区

/*初始化，建立Socket */
void SocketInit(){
	// 建立socket
	if((server_s = socket(AF_INET, SOCK_STREAM, 0))<0){
		perror("SocketError");
		exit(1);
	}else{
		printf("Server create socket successfully!\n");
	}
//	server_s = socket(AF_INET, SOCK_STREAM, 0);

	// 配置socket
	server_addr.sin_family = AF_INET;          // Address family to use
	server_addr.sin_port = htons(PORT_NUM);    // 使用端口号
	server_addr.sin_addr.s_addr = htonl(INADDR_ANY); //监听任一IP地址
	bind(server_s,(struct sockaddr *)&server_addr, sizeof(server_addr));
} 

/*开始监听,等待客户端连接 */
void Listen (){
	// 监听连接
	printf("开始监听...\n");
//	while(1){
//		if(listen(server_s, MAX_LISTEN))listen(server_s, MAX_LISTEN);
//	}
	listen(server_s, MAX_LISTEN);
}

/*接受连接，显示IP和端口*/
void Accept(){
	// 接受连接
	addr_len = sizeof(client_addr);
//	client_s = accept (server_s, (struct sockaddr *)&client_addr, &addr_len);
	if((client_s = accept (server_s, (struct sockaddr *)&client_addr, &addr_len))<0){
		perror("AcceptError");
		exit(1);
	}else{
		printf("Accept successfully!\n");
	}
	memcpy(&client_ip_addr, &client_addr.sin_addr.s_addr, 4);
	
	// 输出一条接收完成的消息
	printf("Accept completed!!!  IP address of client = %s  port = %d \n", inet_ntoa(client_ip_addr), ntohs(client_addr.sin_port));
}

/*向客户端发送数据*/
void SendToClient(){
	// 向客户端发送一条消息
	strcpy(out_buf, "Message -- server to client");
	send(client_s, out_buf, (strlen(out_buf) + 1), 0);
} 

/*接受从客户端发送来的数据*/ 
void ReceiveFromClient(){
	// 接收从客户端返回的消息
	recv(client_s, in_buf, sizeof(in_buf), 0);
	printf("Received from client... data = '%s' \n", in_buf);
} 

//主程序 
int main(){ 
	WORD wVersionRequested = MAKEWORD(1,1);  // Stuff for WSA functions
	WSADATA wsaData;                         // Stuff for WSA functions
	// 初始化 winsock
	WSAStartup(wVersionRequested, &wsaData);
	
	SocketInit(); 
	Listen();
	Accept();
	SendToClient();
	ReceiveFromClient();

	// 关闭 sockets
	closesocket(server_s);
	closesocket(client_s);
	
	// 释放winsock
	WSACleanup();
	system("pause");//解决程序结果一闪而过 
	return 0; 
}

