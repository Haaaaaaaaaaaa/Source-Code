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
#define  PORT_NUM           5000     			//�������˿ں�
#define  IP_ADDR            "192.168.137.1" 	//������IP ��ַ��������
#define FILE_NAME_MAX_SIZE  512  	        	//�ļ�����󳤶� 
#define BUFFER_SIZE         1024*4				//��������С 

unsigned int        server_s;        			//Server socket descriptor
struct sockaddr_in	server_addr;     			//Server Internet address
char                out_buf[BUFFER_SIZE];    	//1024*4-byte ���������
char                in_buf[BUFFER_SIZE];     	//1024*4-byte ���ջ�����

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
  	server_addr.sin_family      = AF_INET;    			// Address family to use
  	server_addr.sin_port        = htons(PORT_NUM);   	//�˿ں�
  	server_addr.sin_addr.s_addr = inet_addr(IP_ADDR); 	// IP ��ַ
} 

/*��Server��������*/
void Connect(){
	//�������� 
  	printf("��������...\n");
	if((connect(server_s, (struct sockaddr *)(&server_addr), sizeof(server_addr))) < 0) {
        perror("ConnectError");							//��ӡ������Ϣ 
        exit(1);
    } else {
        printf("Connnect successfully!\n");
    }
}

/*���ܷ��������������ļ�*/
void ReceiveFromServer(){
	char file_name[FILE_NAME_MAX_SIZE + 1];  	//����ļ��� 
	memset(file_name,'\0',sizeof(file_name));	//��file_name��գ���������ļ��� 
    printf("Please input file name on server to download:\t");  
    scanf("%s", file_name);  					//�����ļ��� 
	//���ļ�������in_buf�� 
	strncpy(in_buf, file_name, strlen(file_name) > BUFFER_SIZE ? BUFFER_SIZE : strlen(file_name));  
    // �����������in_buf�е����ݣ���ʱin_buf�д�ŵ��ǿͻ�����Ҫ���ص��ļ�������  
    send(server_s, in_buf, (strlen(in_buf) + 1), 0);  
	//�ļ����� 
	FILE *fp = fopen(file_name, "w");  
    if (fp == NULL)  
    {  
        printf("File:\t%s Can not open to write!\n", file_name);  
        exit(1);  
    }   
	memset(in_buf,'\0',sizeof(in_buf));			//��in_buf��գ������������� 
    int length = 0;
	// �ӷ������˽����ļ����ݵ�in_buf��    
    while(length = recv(server_s, in_buf, sizeof(in_buf), 0))  
    {  
        if (length < 0)	//�ж��Ƿ���գ�recv���ɹ� 
        {  
            printf("Recieve data from server failed!\n");  
            break;  
        }  
        int write_length = fwrite(in_buf, sizeof(char), length, fp);//���յ����ļ�����д���ļ� 
        if (write_length < length)  								//�ж��Ƿ�д��ɹ� 
        {  
            printf("File:\t%s write failed!\n", file_name);  
            break;  
        }  
        memset(in_buf,'\0',sizeof(in_buf));							//��in_buf��գ��Ա��´�ʹ�� 
    }
    fclose(fp); 													//�ر��ļ� 
	printf("Recieve file:\t %s from server finished!\n", file_name);  
}

/*��������˷����ļ�*/ 
void SendToServer(){
	char file_name[FILE_NAME_MAX_SIZE + 1];   	//����ļ��� 
	memset(file_name,'\0',sizeof(file_name));	//��file_name��գ���������ļ��� 
    printf("Please input file name on server to upload:\t");  
    scanf("%s", file_name);
	
	memset(out_buf,'\0',sizeof(out_buf));		//��out_buf��գ��������Ҫ���͵��ļ������� 
	//���ļ�������out_buf���Ա㷢�� 
	strncpy(out_buf, file_name, strlen(file_name) > BUFFER_SIZE ? BUFFER_SIZE : strlen(file_name));  
    // �����������out_buf�е����ݣ���ʱout_buf�д�ŵ��ǿͻ�����Ҫ�ϴ����ļ�������  
    send(server_s, out_buf, (strlen(out_buf) + 1), 0); //�Ƚ��ļ������͸�server  
    //�ļ����� 
    FILE *fp = fopen(file_name, "r");
    if (fp == NULL)
    {
        printf("File:\t%s not found!\n", file_name);
    }
	else
    {
        memset(out_buf,'\0',sizeof(out_buf));	//��out_buf��գ��������Ҫ���͵�����
        int file_block_length = 0;				//�ļ����� 
        //���ļ�������ݶ��뵽out_buf�� 
        while( (file_block_length = fread(out_buf, sizeof(char), BUFFER_SIZE, fp)) > 0)
        {
            printf("File length = %d\n", file_block_length);
            // ����out_buf�е��ַ�����server
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
	// ��ʼ��winsock
  	WSAStartup(wVersionRequested, &wsaData);
  	while(1){
  		printf("**********************************************************\n");
  		printf("*                         Client                         *\n");
  		printf("**********************************************************\n");
  		printf("*                         �����嵥��                     *\n");
  		printf("*                         1���ϴ��ļ�                    *\n");
  		printf("*                         2���б��ļ�                    *\n");
  		printf("*                         3�������ļ�                    *\n");
  		printf("**********************************************************\n");
  		//��ʼ��socket
		SocketInit();
		//���� 
		Connect();
		printf("�����빦��ǰ�����֣�ѡ����Ҫ���еĲ���\n"); 
		
//  	ReceiveFromServer();
  		SendToServer();
  	
  		// �ر�sockets
  		closesocket(server_s);
	} 

  	// �ͷ� winsock
  	WSACleanup();
  	return 0;
}

