#include <stdio.h>          // Needed for printf()
#include <string.h>         // Needed for memcpy() and strcpy()
#include <windows.h>      	// Needed for all Winsock stuff

//----- Defines --------------
#define  PORT_NUM     1050     		// 服务器端口号
#define  IP_ADDR  "192.168.0.106" 	// 服务器IP 地址（本机）

//===== Main program ======
void main(void)
{
  WORD wVersionRequested = MAKEWORD(1,1);      
  WSADATA wsaData;           

  unsigned int         server_s;        // Server socket descriptor
  struct sockaddr_in   server_addr;     // Server Internet address
  char                 out_buf[100];    // 100-byte 输出缓冲区
  char                 in_buf[100];     // 100-byte 接收缓冲区

// 初始化 winsock
  WSAStartup(wVersionRequested, &wsaData);

  // 建立socket
  server_s = socket(AF_INET, SOCK_STREAM, 0);

  // 配置socket，建立连接
  server_addr.sin_family      = AF_INET;    // Address family to use
  server_addr.sin_port        = htons(PORT_NUM);   //端口号
  server_addr.sin_addr.s_addr = inet_addr(IP_ADDR); // IP 地址
  connect(server_s, (struct sockaddr *)&server_addr, sizeof(server_addr));

  // 接收服务器的消息、
  recv(server_s, in_buf, sizeof(in_buf), 0);
  printf("Received from server... data = '%s' \n", in_buf);

  // 向服务器发送消息
  strcpy(out_buf, "Message -- client to server");
  send(server_s, out_buf, (strlen(out_buf) + 1), 0);

  // 关闭sockets
  closesocket(server_s);

  // 释放 winsock
  WSACleanup();
  return 0;
}

