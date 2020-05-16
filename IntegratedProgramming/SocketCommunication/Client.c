/*
Description:Socket �ͻ��˱����Ҫ�������£�
				1��socket() ����һ�� Socket
				2��connect() �����������
				3��write() �� read() ���лỰ
				4��close() �ر� Socket
Version:	Dev-C++ 5.11
Date:		2020/05/01
*/ 

#include <stdio.h>          // Needed for printf()
#include <string.h>         // Needed for memcpy() and strcpy()
#include <windows.h>      	// Needed for all Winsock stuff

//----- Defines --------------
#define  PORT_NUM     1050     		// �������˿ں�
#define  IP_ADDR  "192.168.0.106" 	// ������IP ��ַ��������

//===== Main program ======
int main(){
	WORD wVersionRequested = MAKEWORD(1,1);      
  	WSADATA wsaData;           

  	unsigned int         server_s;        // Server socket descriptor
  	struct sockaddr_in   server_addr;     // Server Internet address
  	char                 out_buf[100];    // 100-byte ���������
  	char                 in_buf[100];     // 100-byte ���ջ�����

	// ��ʼ�� winsock
  	WSAStartup(wVersionRequested, &wsaData);

  	// ����socket
    if((server_s=socket(AF_INET,SOCK_STREAM,0)) < 0) {
        perror("SocketError");
        exit(1);
    } else {
        printf("Client creat socket successfully!\n");
    }

  	// ����socket����������
  	server_addr.sin_family      = AF_INET;    // Address family to use
  	server_addr.sin_port        = htons(PORT_NUM);   //�˿ں�
  	server_addr.sin_addr.s_addr = inet_addr(IP_ADDR); // IP ��ַ
  	
  	//�������� 
  	printf("��������...\n");
	if(connect(server_s, (struct sockaddr *)(&server_addr), sizeof(server_addr)) < 0) {
        perror("ConnectError");
        exit(1);
    } else {
        printf("Connnect successfully!\n");
    }

	// ���շ���������Ϣ��
	if(recv(server_s, in_buf, sizeof(in_buf), 0)<0){
		perror("ReceiveError");
        exit(1);
	}else{
		printf("Received from server... data = '%s' \n", in_buf);
		printf("Received successfully!\n");
	}
	// �������������Ϣ
	printf ( "Send: Hello, world!\n" ) ; 
	strcpy(out_buf, "Message -- client to server");
    if ( send(server_s, out_buf, (strlen(out_buf) + 1), 0)<0) { 
        perror ( "Send error" ) ; 
        exit(1);
    } else{
    	printf("Send successfully!\n");
	}

  	// �ر�sockets
  	closesocket(server_s);

  	// �ͷ� winsock
  	WSACleanup();
  	return 0;
}

