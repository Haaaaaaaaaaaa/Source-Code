/*
Description:Socket 客户端编程主要步骤如下：
				1、socket() 创建一个 Socket
				2、connect() 与服务器连接
				3、进行会话
				4、close() 关闭 Socket
Version:	Dev-C++ 5.11
Date:		2020/05/01
*/ 

#include <stdio.h>          // Needed for printf()
#include <string.h>         // Needed for memcpy() and strcpy()
#include <windows.h>      	// Needed for all Winsock stuff

//----- Defines --------------
#define  PORT_NUM           5000     			//服务器端口号
#define  IP_ADDR            "192.168.137.1" 	//服务器IP 地址（本机）
#define FILE_NAME_MAX_SIZE  512  	        	//文件名最大长度 
#define BUFFER_SIZE         1024*4				//缓冲区大小 

unsigned int        server_s;        			//Server socket descriptor
struct sockaddr_in	server_addr;     			//Server Internet address
char                out_buf[BUFFER_SIZE];    	//1024*4-byte 输出缓冲区
char                in_buf[BUFFER_SIZE];     	//1024*4-byte 接收缓冲区

/*初始化，建立Socket */
void SocketInit(){
	// 建立socket
    if((server_s=socket(AF_INET,SOCK_STREAM,0)) < 0) {
        perror("SocketError");
        exit(1);
    } else {
        printf("Client create socket successfully!\n");
    }
  	// 配置socket，建立连接
  	server_addr.sin_family      = AF_INET;    			// Address family to use
  	server_addr.sin_port        = htons(PORT_NUM);   	//端口号
  	server_addr.sin_addr.s_addr = inet_addr(IP_ADDR); 	// IP 地址
} 

/*和Server建立连接*/
void Connect(){
	//建立连接 
  	printf("请求连接...\n");
	if((connect(server_s, (struct sockaddr *)(&server_addr), sizeof(server_addr))) < 0) {
        perror("ConnectError");							//打印错误信息 
        exit(1);
    } else {
        printf("Connnect successfully!\n");
    }
}

/*接受服务器发送来的文件*/
void ReceiveFromServer(){
	char file_name[FILE_NAME_MAX_SIZE + 1];  	//存放文件名 
	memset(file_name,'\0',sizeof(file_name));	//将file_name清空，用来存放文件名 
    printf("Please input file name on server to download:\t");  
    scanf("%s", file_name);  					//输入文件名 
	//将文件名放入in_buf中 
	strncpy(in_buf, file_name, strlen(file_name) > BUFFER_SIZE ? BUFFER_SIZE : strlen(file_name));  
    // 向服务器发送in_buf中的数据，此时in_buf中存放的是客户端需要下载的文件的名字  
    send(server_s, in_buf, (strlen(in_buf) + 1), 0);  
	//文件操作 
	FILE *fp = fopen(file_name, "w");  
    if (fp == NULL)  
    {  
        printf("File:\t%s Can not open to write!\n", file_name);  
        exit(1);  
    }   
	memset(in_buf,'\0',sizeof(in_buf));			//将in_buf清空，用来接受数据 
    int length = 0;
	// 从服务器端接收文件数据到in_buf中    
    while(length = recv(server_s, in_buf, sizeof(in_buf), 0))  
    {  
        if (length < 0)	//判断是否接收（recv）成功 
        {  
            printf("Recieve data from server failed!\n");  
            break;  
        }  
        int write_length = fwrite(in_buf, sizeof(char), length, fp);//将收到的文件数据写入文件 
        if (write_length < length)  								//判断是否写入成功 
        {  
            printf("File:\t%s write failed!\n", file_name);  
            break;  
        }  
        memset(in_buf,'\0',sizeof(in_buf));							//将in_buf清空，以便下次使用 
    }
    fclose(fp); 													//关闭文件 
	printf("Recieve file:\t %s from server finished!\n", file_name);  
}

/*向服务器端发送文件*/ 
void SendToServer(){
	char file_name[FILE_NAME_MAX_SIZE + 1];   	//存放文件名 
	memset(file_name,'\0',sizeof(file_name));	//将file_name清空，用来存放文件名 
    printf("Please input file name on server to upload:\t");  
    scanf("%s", file_name);
	
	memset(out_buf,'\0',sizeof(out_buf));		//将out_buf清空，用来存放要发送的文件的名字 
	//将文件名放入out_buf，以便发送 
	strncpy(out_buf, file_name, strlen(file_name) > BUFFER_SIZE ? BUFFER_SIZE : strlen(file_name));  
    // 向服务器发送out_buf中的数据，此时out_buf中存放的是客户端需要上传的文件的名字  
    send(server_s, out_buf, (strlen(out_buf) + 1), 0); //先将文件名发送给server  
    //文件操作 
    FILE *fp = fopen(file_name, "r");
    if (fp == NULL)
    {
        printf("File:\t%s not found!\n", file_name);
    }
	else
    {
        memset(out_buf,'\0',sizeof(out_buf));	//将out_buf清空，用来存放要发送的数据
        int file_block_length = 0;				//文件长度 
        //将文件里的数据读入到out_buf中 
        while( (file_block_length = fread(out_buf, sizeof(char), BUFFER_SIZE, fp)) > 0)
        {
            printf("File length = %d\n", file_block_length);
            // 发送out_buf中的字符串到server
            if (send(server_s, out_buf, (strlen(out_buf) + 1), 0) < 0)
            {
                printf("Send file:\t%s failed!\n", file_name);
                break;
            }
        	memset(out_buf,'\0',sizeof(out_buf));//将out_buf清空
        }
        fclose(fp);
        printf("File:\t%s transfer finished!\n", file_name);
    }  
} 

//===== Main program ======
int main(){
	WORD wVersionRequested = MAKEWORD(1,1);     // Stuff for WSA functions
  	WSADATA wsaData;           					// Stuff for WSA functions
	// 初始化winsock
  	WSAStartup(wVersionRequested, &wsaData);
  	while(1){
  		printf("**********************************************************\n");
  		printf("*                         Client                         *\n");
  		printf("**********************************************************\n");
  		printf("*                         功能清单：                     *\n");
  		printf("*                         1、上传文件                    *\n");
  		printf("*                         2、列表文件                    *\n");
  		printf("*                         3、下载文件                    *\n");
  		printf("**********************************************************\n");
  		//初始化socket
		SocketInit();
		//连接 
		Connect();
		printf("请输入功能前的数字，选择需要进行的操作\n"); 
		
//  	ReceiveFromServer();
  		SendToServer();
  	
  		// 关闭sockets
  		closesocket(server_s);
	} 

  	// 释放 winsock
  	WSACleanup();
  	return 0;
}

