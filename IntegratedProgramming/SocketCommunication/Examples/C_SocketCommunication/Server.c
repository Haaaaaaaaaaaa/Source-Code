#include <stdio.h> 
#include <string.h> 
#include <sys/socket.h> 
#include <netinet/in.h> 
#include <arpa/inet.h>

# define MAXDATASIZE 1024

# define SERVERPORT 8000
# define MAXCONN_NUM 10

int main( ) 
{ 
    char buf[ MAXDATASIZE] ; 
    int sockfd, new_fd, numbytes; 
    struct sockaddr_in server_addr; 
    struct sockaddr_in client_addr; 

    int sin_size; 

	/*定义socket*/
    if ( ( sockfd = socket ( AF_INET , SOCK_STREAM , 0) ) == - 1) { 
        perror ( "socket error" ) ; 
        return 1; 
    } 
    memset ( & client_addr, 0, sizeof ( struct sockaddr ) ) ; /*初始化客户端地址为空*/

	/*初始化服务器地址*/
    server_addr. sin_family = AF_INET ; 
    server_addr. sin_port = htons ( SERVERPORT) ; 
    server_addr. sin_addr. s_addr = INADDR_ANY ; 

	/*绑定服务器地址*/
    if ( bind ( sockfd, ( struct sockaddr * ) & server_addr, sizeof ( struct sockaddr ) ) == - 1) { 
        perror ( "bind error" ) ; 
        return 1; 
    } 
    if ( listen ( sockfd, MAXCONN_NUM) == - 1) { /*开始监听*/
        perror ( "listen error" ) ; 
        return 1; 
    } 

    while ( 1) { 
	printf("\nWaiting connection...\n");
        sin_size = sizeof ( struct sockaddr_in ) ; 
        if ( ( new_fd = accept ( sockfd, ( struct sockaddr * ) & client_addr, & sin_size) ) == - 1) { /*等待连接，堵塞式*/
            perror ( "accept error" ) ; 
            continue ; 
        } 
        printf ( "server: got connection from %s\n" , inet_ntoa( client_addr. sin_addr) ) ; /*接收到连接请求*/
        if ( ( numbytes = recv ( new_fd, buf, MAXDATASIZE, 0) ) == - 1) { /*接收数据*/
            perror ( "recv error" ) ; 
            return 1; 
        } 
        if ( numbytes) { 
            buf[ numbytes] = '\0' ; 
            printf ( "received: %s\n" , buf) ; 
            sleep ( 3) ; 
        } 
        printf ( "send: hi~~\n" ) ; 
        if ( send ( new_fd, "hi~~" , 5, 0) == - 1) { /*发送数据*/
            perror ( "send error" ) ; 
            return 1; 
        } 
        close ( new_fd) ; /*关闭连接*/
    } 
    return 0; 
}
