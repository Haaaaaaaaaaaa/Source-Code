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

#include <stdio.h>          //Needed for printf()
#include <string.h>         //Needed for memcpy() and strcpy()
#include <winsock2.h>
#include <windows.h>        //Needed for all Winsock stuff
#include <ws2tcpip.h>		//Needed forsocklen_t 
#include <stdlib.h>			//解决程序结果一闪而过 
#include "dirent.h"   		//查询文件名需要 

//-----------------------------------------------------
#define PORT_NUM   			5000      //服务器端口号
#define MAX_LISTEN    		3      	  //最大监听数
#define FILE_NAME_MAX_SIZE	512  	  //文件名最大长度 
#define BUFFER_SIZE         1024*4	  //缓冲区大小 
#define FilePath            "./"	  //文件目录，提供目录中所有文件名给client 

unsigned int        server_s;         //Server socket 标示符
struct sockaddr_in  server_addr;      //Server 地址
unsigned int        client_s;         //Client socket标示符
struct sockaddr_in	client_addr;      //Client Internet address
struct in_addr      client_ip_addr;   //Client IP address
int                 addr_len;         //Internet address length
char                out_buf[BUFFER_SIZE];    // 1024*4-byte 输出缓冲区
char                in_buf[BUFFER_SIZE];     // 1024*4-byte 接收缓冲区

/*初始化，建立Socket */
void SocketInit(){
	// 建立socket
	if((server_s = socket(AF_INET, SOCK_STREAM, 0))<0){
		perror("SocketError");
		exit(1);
	}else{
		printf("Server create socket successfully!\n");
	}
	// 配置socket
	server_addr.sin_family 		= AF_INET;          	// Address family to use
	server_addr.sin_port 		= htons(PORT_NUM);    	// 使用端口号
	server_addr.sin_addr.s_addr = htonl(INADDR_ANY); 	//监听任一IP地址
	//bind函数将socket与本地协议端口绑定 
	bind(server_s,(struct sockaddr *)&server_addr, sizeof(server_addr));
} 

/*开始监听,等待客户端连接 */
void Listen (){
	// 监听连接
	printf("Start listening...\n");
	listen(server_s, MAX_LISTEN);
}

/*接受连接，显示IP和端口*/
void Accept(){
	// 接受连接
	addr_len = sizeof(client_addr);
	if((client_s = accept (server_s, (struct sockaddr *)&client_addr, &addr_len))<0){
		perror("AcceptError");
		exit(1);
	}else{
		printf("Accept successfully!\n");
	}
	memcpy(&client_ip_addr, &client_addr.sin_addr.s_addr, 4);
	//输出一条接收完成的消息
	//inet_ntoa将一个十进制网络字节序转换为点分十进制IP格式的字符串
	printf("Accept completed!!!  IP address of client = %s  port = %d \n", inet_ntoa(client_ip_addr), ntohs(client_addr.sin_port));
}

/*向客户端发送数据*/
void SendToClient(){
	memset(in_buf,'\0',sizeof(in_buf));					//将in_buf清空，用来接收数据 
	socklen_t length = sizeof(client_addr);
    length = recv(client_s, in_buf, sizeof(in_buf), 0); //接收client发送的文件名 
    if (length < 0)
    {
        printf("Server recieve data failed!\n");
        exit(1);
    }
    char file_name[FILE_NAME_MAX_SIZE + 1];				//存放文件名 
	memset(file_name,'\0',sizeof(file_name));			//将file_name清空，用来存放文件名 
	//将接收到的文件名放入file_name
    strncpy(file_name, in_buf,strlen(in_buf) > FILE_NAME_MAX_SIZE ? FILE_NAME_MAX_SIZE : strlen(in_buf));
    //文件操作 
    FILE *fp = fopen(file_name, "r");
    if (fp == NULL)
    {
        printf("File:\t%s not found!\n", file_name);
    }
	else
    {
        memset(out_buf,'\0',sizeof(out_buf));			//将out_buf清空，用来存放要发送的数据
        int file_block_length = 0;						//文件长度 
        //将文件数据存入到out_buf，准备发送 
        while( (file_block_length = fread(out_buf, sizeof(char), BUFFER_SIZE, fp)) > 0)
        {
            printf("File length = %d\n", file_block_length);
            // 发送buffer中的字符串到client_s,实际上就是发送给客户端
            if (send(client_s, out_buf, (strlen(out_buf) + 1), 0) < 0)
            {
                printf("Send file:\t%s failed!\n", file_name);
                break;
            }
        	memset(out_buf,'\0',sizeof(out_buf));		//将out_buf清空，以便下次使用 
        }
        fclose(fp);
        printf("File:\t%s transfer finished!\n", file_name);
    }    
} 

/*接收从客户端发送来的数据*/ 
void ReceiveFromClient(){
	char file_name[FILE_NAME_MAX_SIZE + 1];  	//存放文件名 
	memset(file_name,'\0',sizeof(file_name)); 	//将file_name清空，用来存放文件名 
	memset(in_buf,'\0',sizeof(in_buf));			//将in_buf清空，用来接收收到的文件名  
	recv(client_s, in_buf, sizeof(in_buf), 0);	//接收client发送过来的文件名 
	//将接收到的文件名放入file_name 
	strncpy(file_name, in_buf, strlen(in_buf) > BUFFER_SIZE ? BUFFER_SIZE : strlen(in_buf));  
   	//文件操作 
	FILE *fp = fopen(file_name, "w");  
    if (fp == NULL)  
    {  
        printf("File:\t%s Can not open to write!\n", file_name);  
        exit(1);  
    }
	// 从服务器端接收数据到in_buf中     
	memset(in_buf,'\0',sizeof(in_buf));			//将in_buf清空，用来接受数据 
    int length = 0;  
	//接收client发送来的数据 
    while(length = recv(client_s, in_buf, sizeof(in_buf), 0))  
    {  
        if (length < 0)  
        {  
            printf("Recieve data from server failed!\n");  
            break;  
        }  
        //将接收的数据写入文件 
        int write_length = fwrite(in_buf, sizeof(char), length, fp);  
        if (write_length < length)  
        {  
            printf("File:\t%s write failed!\n", file_name);  
            break;  
        }  
        memset(in_buf,'\0',sizeof(in_buf));		//将in_buf清空，以便下次使用 
    }
    fclose(fp); //关闭文件 
	printf("Recieve file:\t %s from client finished!\n", file_name);  
}

/*
发送所有文件名给client以便列表出server所有文件
会找出.和..这种目录，没有办法传输 
*/
void SendFileNameToClient(){
	int i = 0;				//记录文件数目 
	char stop[4]="Stop";	//文件名发送完毕标志 
    DIR *dir = NULL;  
    struct dirent *entry;  
    if((dir = opendir(FilePath))==NULL)  
    {  
      	printf("opendir failed!");  
      //return -1;  
    }
	else //if (entry->d_name[0] != '.') //文件名不是'.'或'..'时,没办法忽略 
    {  
     	while(entry=readdir(dir))  
      	{  
      		i++;
       		printf("File Name%d: %s\n",i,entry->d_name);	//输出文件或者目录的名称
       		memset(out_buf,'\0',sizeof(out_buf));			//将out_buf清空，用来存放要发送的文件名
			//将接收到的文件名放入file_name 
			strncpy(out_buf, entry->d_name, strlen(entry->d_name) > BUFFER_SIZE ? BUFFER_SIZE : strlen(entry->d_name)); 
			send(client_s, out_buf, (strlen(out_buf) + 1), 0); 
     	}  
     	closedir(dir);
     	//发送结束标志，文件名全部发送完毕。
		//弊端就是要是文件有以Stop...来命名就会出现BUG 
		send(client_s, stop, (strlen(stop) + 1), 0); 
     }	   
} 
 
/*接收client发送来的功能选择*/
void ReceiveChoiceFromClient(){
	char choice[20];//存放选择，及功能前的数字 
	recv(client_s, choice, sizeof(choice), 0);
	printf("The option received:%s\n",choice);
	if(choice[0]=='1'){
		printf("The option received:%s\tUpload Files\n",choice);
		ReceiveFromClient();
	}
	if(choice[0]=='2'){
		printf("The option received:%s\tSend All Files Name\n",choice);
		SendFileNameToClient();
	}	
	else if(choice[0]=='3'){
		printf("The option received:%s\tDownload Files\n",choice);
		SendToClient();
	}	
	else
		printf("Input Error!\n");
}

//主程序 
int main(){ 
	WORD wVersionRequested = MAKEWORD(1,1);  // Stuff for WSA functions
	WSADATA wsaData;                         // Stuff for WSA functions
	//初始化winsock
	WSAStartup(wVersionRequested, &wsaData);
	while(1)
	{
  		printf("**********************************************************\n");
  		printf("*                         Server                         *\n");
  		printf("**********************************************************\n");
  		printf("*                         Welcome                        *\n");
  		printf("**********************************************************\n");
  		SocketInit(); //初始化socket 
		Listen();
		Accept();
		ReceiveChoiceFromClient();
		//关闭sockets
		closesocket(server_s);
		closesocket(client_s);
	}
	//释放winsock
	WSACleanup();
	//解决程序结果一闪而过
	system("pause"); 
	return 0; 
}

