#include <stdio.h>          // Needed for printf()
#include <string.h>         // Needed for memcpy() and strcpy()
#include <windows.h>      	// Needed for all Winsock stuff

//----- Defines --------------
#define  PORT_NUM     1050     		// �������˿ں�
#define  IP_ADDR  "192.168.0.106" 	// ������IP ��ַ��������

//===== Main program ======
void main(void)
{
  WORD wVersionRequested = MAKEWORD(1,1);      
  WSADATA wsaData;           

  unsigned int         server_s;        // Server socket descriptor
  struct sockaddr_in   server_addr;     // Server Internet address
  char                 out_buf[100];    // 100-byte ���������
  char                 in_buf[100];     // 100-byte ���ջ�����

// ��ʼ�� winsock
  WSAStartup(wVersionRequested, &wsaData);

  // ����socket
  server_s = socket(AF_INET, SOCK_STREAM, 0);

  // ����socket����������
  server_addr.sin_family      = AF_INET;    // Address family to use
  server_addr.sin_port        = htons(PORT_NUM);   //�˿ں�
  server_addr.sin_addr.s_addr = inet_addr(IP_ADDR); // IP ��ַ
  connect(server_s, (struct sockaddr *)&server_addr, sizeof(server_addr));

  // ���շ���������Ϣ��
  recv(server_s, in_buf, sizeof(in_buf), 0);
  printf("Received from server... data = '%s' \n", in_buf);

  // �������������Ϣ
  strcpy(out_buf, "Message -- client to server");
  send(server_s, out_buf, (strlen(out_buf) + 1), 0);

  // �ر�sockets
  closesocket(server_s);

  // �ͷ� winsock
  WSACleanup();
  return 0;
}

