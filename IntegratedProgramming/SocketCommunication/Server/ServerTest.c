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
	else //if (entry->d_name[0] != '.') //�ļ�������'.'��'..'ʱ 
        {  
     		 while(entry=readdir(dir))  
      		{  
      			i++;
       			printf("File Name%d: %s\n",i,entry->d_name);  //����ļ�����Ŀ¼������  
     		}  
     		 closedir(dir);    
     }  
     return 0;    
    }  

