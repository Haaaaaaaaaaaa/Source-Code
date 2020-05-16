/*
Description:Socket �ͻ��˱����Ҫ�������£�
				1��socket() ����һ�� Socket
				2��connect() �����������
				3�����лỰ
				4��close() �ر� Socket
Version:	Dev-C++ 5.11
Date:		2020/05/01
*/ 

#include <stdio.h>          // Needed for printf()
#include <string.h>         // Needed for memcpy() and strcpy()
#include <windows.h>      	// Needed for all Winsock stuff

//----- Defines --------------
#define  PORT_NUM          5000     		// �������˿ں�
#define  IP_ADDR           "192.168.137.1" 	// ������IP ��ַ��������
#define FILE_NAME_MAX_SIZE 512  	        //�ļ�����󳤶� 
#define BUFFER_SIZE        1024*4			//��������С 

unsigned int         server_s;        // Server socket descriptor
struct sockaddr_in   server_addr;     // Server Internet address
char                 out_buf[BUFFER_SIZE];    // 1024*4-byte ���������
char                 in_buf[BUFFER_SIZE];     // 1024*4-byte ���ջ�����

/*��ʼ��������Socket */
void SocketInit(){
	// ����socket
    if((server_s=socket(AF_INET,SOCK_STREAM,0)) < 0) {
        perror("SocketError");
        exit(1);
    } else {
        printf("Client create socket successfully!\n");
    }

  	// ����socket����������
  	server_addr.sin_family      = AF_INET;    // Address family to use
  	server_addr.sin_port        = htons(PORT_NUM);   //�˿ں�
  	server_addr.sin_addr.s_addr = inet_addr(IP_ADDR); // IP ��ַ
} 

/*��Server��������*/
void Connect(){
	//�������� 
  	printf("��������...\n");
	if((connect(server_s, (struct sockaddr *)(&server_addr), sizeof(server_addr))) < 0) {
        perror("ConnectError");
        exit(1);
    } else {
        printf("Connnect successfully!\n");
    }
}

/*���ܷ��������������ļ�*/
void ReceiveFromServer(){
	// ���շ���������Ϣ��
//	recv(server_s, in_buf, sizeof(in_buf), 0);
//	printf("Received from server... data = '%s' \n", in_buf);
//	if(recv(server_s, in_buf, sizeof(in_buf), 0)<0){
//		perror("ReceiveError");
//        exit(1);
//	}else{
//		printf("Received from server... data = '%s' \n", in_buf);
//		printf("Received successfully!\n");
//	}

	char file_name[FILE_NAME_MAX_SIZE + 1];   
	memset(file_name,'\0',sizeof(file_name));//��file_name��գ���������ļ��� 
    printf("Please input file name on server to download:\t");  
    scanf("%s", file_name);  

	strncpy(in_buf, file_name, strlen(file_name) > BUFFER_SIZE ? BUFFER_SIZE : strlen(file_name));  
    // �����������in_buf�е����ݣ���ʱin_buf�д�ŵ��ǿͻ�����Ҫ���յ��ļ�������  
    send(server_s, in_buf, (strlen(in_buf) + 1), 0);  
	
	FILE *fp = fopen(file_name, "w");  
    if (fp == NULL)  
    {  
        printf("File:\t%s Can not open to write!\n", file_name);  
        exit(1);  
    }
	// �ӷ������˽������ݵ�buffer��     
	memset(in_buf,'\0',sizeof(in_buf));//��in_buf��գ������������� 
    int length = 0;   
    while(length = recv(server_s, in_buf, sizeof(in_buf), 0))  
    {  
        if (length < 0)  
        {  
            printf("Recieve data from server failed!\n");  
            break;  
        }  
        int write_length = fwrite(in_buf, sizeof(char), length, fp);  
        if (write_length < length)  
        {  
            printf("File:\t%s write failed!\n", file_name);  
            break;  
        }  
        memset(in_buf,'\0',sizeof(in_buf));//��in_buf��գ��Ա��´�ʹ�� 
    }
    fclose(fp); //�ر��ļ� 
	printf("Recieve file:\t %s from server finished!\n", file_name);  
}

/*��������˷����ļ�*/ 
void SendToServer(){
	// �������������Ϣ
//	printf ( "Send: Hello, world!\n" ) ; 
//	strcpy(out_buf, "Message -- client to server");
////	send(server_s, out_buf, (strlen(out_buf) + 1), 0);
//    if ( send(server_s, out_buf, (strlen(out_buf) + 1), 0)<0) { 
//        perror ( "Send error" ) ; 
//        exit(1);
//    } else{
//    	printf("Send successfully!\n");
//	}

	char file_name[FILE_NAME_MAX_SIZE + 1];   
	memset(file_name,'\0',sizeof(file_name));//��file_name��գ���������ļ��� 
    printf("Please input file name on server to upwnload:\t");  
    scanf("%s", file_name);
	
	memset(out_buf,'\0',sizeof(out_buf));//��out_buf��գ��������Ҫ���͵��ļ������� 
	strncpy(out_buf, file_name, strlen(file_name) > BUFFER_SIZE ? BUFFER_SIZE : strlen(file_name));  
    // �����������out_buf�е����ݣ���ʱout_buf�д�ŵ��ǿͻ�����Ҫ���͵��ļ�������  
    send(server_s, out_buf, (strlen(out_buf) + 1), 0);   

//	memset(out_buf,'\0',sizeof(out_buf));//��out_buf��գ�������Ž�Ҫ���͸�server������ 
//	socklen_t length = sizeof(client_addr);
//    length = recv(client_s, in_buf, sizeof(in_buf), 0);
//    if (length < 0)
//    {
//        printf("Server recieve data failed!\n");
//        exit(1);
//    }
//    char file_name[FILE_NAME_MAX_SIZE + 1];
//    bzero(file_name, sizeof(file_name));
//	memset(file_name,'\0',sizeof(file_name));//��file_name��գ���������ļ��� 
//    strncpy(file_name, out_buf,strlen(out_buf) > FILE_NAME_MAX_SIZE ? FILE_NAME_MAX_SIZE : strlen(out_buf));
    
    FILE *fp = fopen(file_name, "r");
    if (fp == NULL)
    {
        printf("File:\t%s not found!\n", file_name);
    }
	else
    {
        memset(out_buf,'\0',sizeof(out_buf));//��out_buf��գ��������Ҫ���͵�����
        int file_block_length = 0;
        while( (file_block_length = fread(out_buf, sizeof(char), BUFFER_SIZE, fp)) > 0)
        {
            printf("file_block_length = %d\n", file_block_length);

            // ����buffer�е��ַ�����client_s,ʵ���Ͼ��Ƿ��͸��ͻ���
            if (send(server_s, out_buf, (strlen(out_buf) + 1), 0) < 0)
            {
                printf("Send file:\t%s failed!\n", file_name);
                break;
            }
        	memset(out_buf,'\0',sizeof(out_buf));//��out_buf���
        }
        fclose(fp);
        printf("File:\t%s transfer finished!\n", file_name);
    }  
} 
//===== Main program ======
int main(){
	WORD wVersionRequested = MAKEWORD(1,1);     // Stuff for WSA functions
  	WSADATA wsaData;           					// Stuff for WSA functions
	// ��ʼ�� winsock
  	WSAStartup(wVersionRequested, &wsaData);
	SocketInit();
	Connect();
//  	ReceiveFromServer();
  	SendToServer();
  	
  	// �ر�sockets
  	closesocket(server_s);

  	// �ͷ� winsock
  	WSACleanup();
  	return 0;
}

