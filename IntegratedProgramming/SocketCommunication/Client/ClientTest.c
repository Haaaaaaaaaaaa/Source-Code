    #include <stdio.h> 

	
    int main()  
    {     
	char x='9';
	int i=(int)x-'0';
	printf("int i=%d\n",i);
	
	
	int j=9;
	char y[10];
	y[10]=(char)j+'0';
//	char y=(char)j;
	printf("char y=%s\n",y);

	int z=(int)y[10]-'0';
	printf("int z=%d\n",z);
     return 0;    
    }  

