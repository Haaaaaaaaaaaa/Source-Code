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

#include <stdio.h>          //Needed for printf()
#include <string.h>         //Needed for memcpy() and strcpy()
#include <winsock2.h>
#include <windows.h>        //Needed for all Winsock stuff
#include <ws2tcpip.h>		//Needed forsocklen_t 
#include <stdlib.h>			//���������һ������ 
#include "dirent.h"   		//��ѯ�ļ�����Ҫ 

//-----------------------------------------------------
#define PORT_NUM   			5000      //�������˿ں�
#define MAX_LISTEN    		3      	  //��������
#define FILE_NAME_MAX_SIZE	512  	  //�ļ�����󳤶� 
#define BUFFER_SIZE         1024*4	  //��������С 
#define FilePath            "./"	  //�ļ�Ŀ¼���ṩĿ¼�������ļ�����client 

unsigned int        server_s;         //Server socket ��ʾ��
struct sockaddr_in  server_addr;      //Server ��ַ
unsigned int        client_s;         //Client socket��ʾ��
struct sockaddr_in	client_addr;      //Client Internet address
struct in_addr      client_ip_addr;   //Client IP address
int                 addr_len;         //Internet address length
char                out_buf[BUFFER_SIZE];    // 1024*4-byte ���������
char                in_buf[BUFFER_SIZE];     // 1024*4-byte ���ջ�����

/*��ʼ��������Socket */
void SocketInit(){
	// ����socket
	if((server_s = socket(AF_INET, SOCK_STREAM, 0))<0){
		perror("SocketError");
		exit(1);
	}else{
		printf("Server create socket successfully!\n");
	}
	// ����socket
	server_addr.sin_family 		= AF_INET;          	// Address family to use
	server_addr.sin_port 		= htons(PORT_NUM);    	// ʹ�ö˿ں�
	server_addr.sin_addr.s_addr = htonl(INADDR_ANY); 	//������һIP��ַ
	//bind������socket�뱾��Э��˿ڰ� 
	bind(server_s,(struct sockaddr *)&server_addr, sizeof(server_addr));
} 

/*��ʼ����,�ȴ��ͻ������� */
void Listen (){
	// ��������
	printf("Start listening...\n");
	listen(server_s, MAX_LISTEN);
}

/*�������ӣ���ʾIP�Ͷ˿�*/
void Accept(){
	// ��������
	addr_len = sizeof(client_addr);
	if((client_s = accept (server_s, (struct sockaddr *)&client_addr, &addr_len))<0){
		perror("AcceptError");
		exit(1);
	}else{
		printf("Accept successfully!\n");
	}
	memcpy(&client_ip_addr, &client_addr.sin_addr.s_addr, 4);
	//���һ��������ɵ���Ϣ
	//inet_ntoa��һ��ʮ���������ֽ���ת��Ϊ���ʮ����IP��ʽ���ַ���
	printf("Accept completed!!!  IP address of client = %s  port = %d \n", inet_ntoa(client_ip_addr), ntohs(client_addr.sin_port));
}

/*��ͻ��˷�������*/
void SendToClient(){
	memset(in_buf,'\0',sizeof(in_buf));					//��in_buf��գ������������� 
	socklen_t length = sizeof(client_addr);
    length = recv(client_s, in_buf, sizeof(in_buf), 0); //����client���͵��ļ��� 
    if (length < 0)
    {
        printf("Server recieve data failed!\n");
        exit(1);
    }
    char file_name[FILE_NAME_MAX_SIZE + 1];				//����ļ��� 
	memset(file_name,'\0',sizeof(file_name));			//��file_name��գ���������ļ��� 
	//�����յ����ļ�������file_name
    strncpy(file_name, in_buf,strlen(in_buf) > FILE_NAME_MAX_SIZE ? FILE_NAME_MAX_SIZE : strlen(in_buf));
    //�ļ����� 
    FILE *fp = fopen(file_name, "r");
    if (fp == NULL)
    {
        printf("File:\t%s not found!\n", file_name);
    }
	else
    {
        memset(out_buf,'\0',sizeof(out_buf));			//��out_buf��գ��������Ҫ���͵�����
        int file_block_length = 0;						//�ļ����� 
        //���ļ����ݴ��뵽out_buf��׼������ 
        while( (file_block_length = fread(out_buf, sizeof(char), BUFFER_SIZE, fp)) > 0)
        {
            printf("File length = %d\n", file_block_length);
            // ����buffer�е��ַ�����client_s,ʵ���Ͼ��Ƿ��͸��ͻ���
            if (send(client_s, out_buf, (strlen(out_buf) + 1), 0) < 0)
            {
                printf("Send file:\t%s failed!\n", file_name);
                break;
            }
        	memset(out_buf,'\0',sizeof(out_buf));		//��out_buf��գ��Ա��´�ʹ�� 
        }
        fclose(fp);
        printf("File:\t%s transfer finished!\n", file_name);
    }    
} 

/*���մӿͻ��˷�����������*/ 
void ReceiveFromClient(){
	char file_name[FILE_NAME_MAX_SIZE + 1];  	//����ļ��� 
	memset(file_name,'\0',sizeof(file_name)); 	//��file_name��գ���������ļ��� 
	memset(in_buf,'\0',sizeof(in_buf));			//��in_buf��գ����������յ����ļ���  
	recv(client_s, in_buf, sizeof(in_buf), 0);	//����client���͹������ļ��� 
	//�����յ����ļ�������file_name 
	strncpy(file_name, in_buf, strlen(in_buf) > BUFFER_SIZE ? BUFFER_SIZE : strlen(in_buf));  
   	//�ļ����� 
	FILE *fp = fopen(file_name, "w");  
    if (fp == NULL)  
    {  
        printf("File:\t%s Can not open to write!\n", file_name);  
        exit(1);  
    }
	// �ӷ������˽������ݵ�in_buf��     
	memset(in_buf,'\0',sizeof(in_buf));			//��in_buf��գ������������� 
    int length = 0;  
	//����client������������ 
    while(length = recv(client_s, in_buf, sizeof(in_buf), 0))  
    {  
        if (length < 0)  
        {  
            printf("Recieve data from server failed!\n");  
            break;  
        }  
        //�����յ�����д���ļ� 
        int write_length = fwrite(in_buf, sizeof(char), length, fp);  
        if (write_length < length)  
        {  
            printf("File:\t%s write failed!\n", file_name);  
            break;  
        }  
        memset(in_buf,'\0',sizeof(in_buf));		//��in_buf��գ��Ա��´�ʹ�� 
    }
    fclose(fp); //�ر��ļ� 
	printf("Recieve file:\t %s from client finished!\n", file_name);  
}

/*
���������ļ�����client�Ա��б��server�����ļ�
���ҳ�.��..����Ŀ¼��û�а취���� 
*/
void SendFileNameToClient(){
	int i = 0;				//��¼�ļ���Ŀ 
	char stop[4]="Stop";	//�ļ���������ϱ�־ 
    DIR *dir = NULL;  
    struct dirent *entry;  
    if((dir = opendir(FilePath))==NULL)  
    {  
      	printf("opendir failed!");  
      //return -1;  
    }
	else //if (entry->d_name[0] != '.') //�ļ�������'.'��'..'ʱ,û�취���� 
    {  
     	while(entry=readdir(dir))  
      	{  
      		i++;
       		printf("File Name%d: %s\n",i,entry->d_name);	//����ļ�����Ŀ¼������
       		memset(out_buf,'\0',sizeof(out_buf));			//��out_buf��գ��������Ҫ���͵��ļ���
			//�����յ����ļ�������file_name 
			strncpy(out_buf, entry->d_name, strlen(entry->d_name) > BUFFER_SIZE ? BUFFER_SIZE : strlen(entry->d_name)); 
			send(client_s, out_buf, (strlen(out_buf) + 1), 0); 
     	}  
     	closedir(dir);
     	//���ͽ�����־���ļ���ȫ��������ϡ�
		//�׶˾���Ҫ���ļ�����Stop...�������ͻ����BUG 
		send(client_s, stop, (strlen(stop) + 1), 0); 
     }	   
} 
 
/*����client�������Ĺ���ѡ��*/
void ReceiveChoiceFromClient(){
	char choice[20];//���ѡ�񣬼�����ǰ������ 
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

//������ 
int main(){ 
	WORD wVersionRequested = MAKEWORD(1,1);  // Stuff for WSA functions
	WSADATA wsaData;                         // Stuff for WSA functions
	//��ʼ��winsock
	WSAStartup(wVersionRequested, &wsaData);
	while(1)
	{
  		printf("**********************************************************\n");
  		printf("*                         Server                         *\n");
  		printf("**********************************************************\n");
  		printf("*                         Welcome                        *\n");
  		printf("**********************************************************\n");
  		SocketInit(); //��ʼ��socket 
		Listen();
		Accept();
		ReceiveChoiceFromClient();
		//�ر�sockets
		closesocket(server_s);
		closesocket(client_s);
	}
	//�ͷ�winsock
	WSACleanup();
	//���������һ������
	system("pause"); 
	return 0; 
}

