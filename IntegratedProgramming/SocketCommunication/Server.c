/*
Description:Socket �������˱����Ҫ�������£�
				1��socket() ����һ�� Socket
    			2��bind()
    			3��listen() ����
    			4��accept() �������ӵ�����
    			5�����лỰ
    			6��close() �ر� Socket
Version:	Dev-C++ 5.11
Date:		2020/05/01
*/ 

#include <stdio.h>          // Needed for printf()
#include <string.h>         // Needed for memcpy() and strcpy()
#include <windows.h>        // Needed for all Winsock stuff
#include<stdlib.h>			//���������һ������ 
//-----------------------------------------------------
#define  PORT_NUM   5000      // �������˿ں�
#define  MAX_LISTEN    3      // ��������

unsigned int         server_s;        // Server socket ��ʾ��
struct sockaddr_in   server_addr;     // Server ��ַ
unsigned int         client_s;        // Client socket��ʾ��
struct sockaddr_in   client_addr;     // Client Internet address
struct in_addr       client_ip_addr;  // Client IP address
int                  addr_len;        // Internet address length
char                 out_buf[1024*4];    // 100-byte ���������
char                 in_buf[1024*4];     // 100-byte ���ջ�����

/*��ʼ��������Socket */
void SocketInit(){
	// ����socket
	if((server_s = socket(AF_INET, SOCK_STREAM, 0))<0){
		perror("SocketError");
		exit(1);
	}else{
		printf("Server create socket successfully!\n");
	}
//	server_s = socket(AF_INET, SOCK_STREAM, 0);

	// ����socket
	server_addr.sin_family = AF_INET;          // Address family to use
	server_addr.sin_port = htons(PORT_NUM);    // ʹ�ö˿ں�
	server_addr.sin_addr.s_addr = htonl(INADDR_ANY); //������һIP��ַ
	bind(server_s,(struct sockaddr *)&server_addr, sizeof(server_addr));
} 

/*��ʼ����,�ȴ��ͻ������� */
void Listen (){
	// ��������
	printf("��ʼ����...\n");
//	while(1){
//		if(listen(server_s, MAX_LISTEN))listen(server_s, MAX_LISTEN);
//	}
	listen(server_s, MAX_LISTEN);
}

/*�������ӣ���ʾIP�Ͷ˿�*/
void Accept(){
	// ��������
	addr_len = sizeof(client_addr);
//	client_s = accept (server_s, (struct sockaddr *)&client_addr, &addr_len);
	if((client_s = accept (server_s, (struct sockaddr *)&client_addr, &addr_len))<0){
		perror("AcceptError");
		exit(1);
	}else{
		printf("Accept successfully!\n");
	}
	memcpy(&client_ip_addr, &client_addr.sin_addr.s_addr, 4);
	
	// ���һ��������ɵ���Ϣ
	printf("Accept completed!!!  IP address of client = %s  port = %d \n", inet_ntoa(client_ip_addr), ntohs(client_addr.sin_port));
}

/*��ͻ��˷�������*/
void SendToClient(){
	// ��ͻ��˷���һ����Ϣ
	strcpy(out_buf, "Message -- server to client");
	send(client_s, out_buf, (strlen(out_buf) + 1), 0);
} 

/*���ܴӿͻ��˷�����������*/ 
void ReceiveFromClient(){
	// ���մӿͻ��˷��ص���Ϣ
	recv(client_s, in_buf, sizeof(in_buf), 0);
	printf("Received from client... data = '%s' \n", in_buf);
} 

//������ 
int main(){ 
	WORD wVersionRequested = MAKEWORD(1,1);  // Stuff for WSA functions
	WSADATA wsaData;                         // Stuff for WSA functions
	// ��ʼ�� winsock
	WSAStartup(wVersionRequested, &wsaData);
	
	SocketInit(); 
	Listen();
	Accept();
	SendToClient();
	ReceiveFromClient();

	// �ر� sockets
	closesocket(server_s);
	closesocket(client_s);
	
	// �ͷ�winsock
	WSACleanup();
	system("pause");//���������һ������ 
	return 0; 
}

