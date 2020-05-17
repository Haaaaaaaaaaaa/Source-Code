    #include <stdio.h> 
    #include "dirent.h"  
	 
    #define  FilePath "./"
    int main()  
    {     
    int i = 0;
	int filesize = 0;  
     	DIR *dir = NULL;  
        struct dirent *entry;  
      
        if((dir = opendir(FilePath))==NULL)  
        {  
      		printf("opendir failed!");  
      		return -1;  
        }
	else //if (entry->d_name[0] != '.') //文件名不是'.'或'..'时 
        {  
     		 while(entry=readdir(dir))  
      		{  
      			i++;
       			printf("File Name%d: %s\n",i,entry->d_name);  //输出文件或者目录的名称  
     		}  
     		 closedir(dir);    
     }  
     return 0;    
    }  

