#include <iostream>
using namespace std;

int main() {
    int numArray[8];//������������λ��Ч���ֵ����� 
	int b=0;		//��Ч���� 
	int delNumber;		//ָ��ɾ����һ����Ч���� 
	int temp;		//�ù��⽻�����м��� 
    //����Ч���ַ������� 
	for(int i=0;i<8;i++){
    	cin>>b;
    	numArray[i]=b;
	}
	//������Ҫɾ����һ����Ч���� 
	cin>>delNumber;
	//ɾ��ָ����Ч���� 
	if(delNumber<8){
		for(int j=delNumber-1;j<8;j++){
			numArray[j]=numArray[j+1];
		}
		//ð�����򣬴Ӵ�С���� 
		for(int i=0;i<7-1;i++)
			for(int j=0;j<7-1-i;j++)
				if(numArray[j]<numArray[j+1]){
					temp=numArray[j];
					numArray[j]=numArray[j+1];
					numArray[j+1]=temp;
				}
		//��ӡ����֮�������			
		for(int i=0;i<7;i++){
			cout<<numArray[i];
		}
	}else{
		cout<<"error"<<endl; 
	} 
	
     return 0;   
}
