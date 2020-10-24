#include <iostream>
using namespace std;

int main() {
    int numArray[8];//用来存放这个八位有效数字的数组 
	int b=0;		//有效数字 
	int delNumber;		//指定删除哪一个有效数字 
	int temp;		//用故意交换的中间量 
    //将有效数字放入数组 
	for(int i=0;i<8;i++){
    	cin>>b;
    	numArray[i]=b;
	}
	//输入需要删除哪一个有效数字 
	cin>>delNumber;
	//删除指定有效数字 
	if(delNumber<8){
		for(int j=delNumber-1;j<8;j++){
			numArray[j]=numArray[j+1];
		}
		//冒泡排序，从大到小排序 
		for(int i=0;i<7-1;i++)
			for(int j=0;j<7-1-i;j++)
				if(numArray[j]<numArray[j+1]){
					temp=numArray[j];
					numArray[j]=numArray[j+1];
					numArray[j+1]=temp;
				}
		//打印排序之后的数组			
		for(int i=0;i<7;i++){
			cout<<numArray[i];
		}
	}else{
		cout<<"error"<<endl; 
	} 
	
     return 0;   
}
