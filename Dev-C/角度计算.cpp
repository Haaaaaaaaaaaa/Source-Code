#include<iostream> 
#include<math.h> 
using namespace std;
#define PI 3.1415926
int main(){
	double A,B,H1,i_1,i_2,H,angle,result,degree_cos,degree_tan,fin;
	int X_d,X_f,X_m,L_d,L_f,L_m;
	int sum_d,sum_d1,sum_d2;
	int fin_d,fin_f,fin_m;
	while(1){ 
//	����
	cout<<"����B:";
	cin>>B;
//	cout<<"����X�Ķ�:";
//	cin>>X_d;
//	cout<<"����X�ķ�:";
//	cin>>X_f;
//	cout<<"����X����:";
//	cin>>X_m;
	cout<<"����L�Ķ�:";
	cin>>L_d;
	cout<<"����L�ķ�:";
	cin>>L_f;
	cout<<"����L����:";
	cin>>L_m;
	cout<<"����H1:";
	cin>>H1;
	cout<<"����i_1:";
	cin>>i_1;
	cout<<"����i_2:";
	cin>>i_2;
//	����Ƕȣ�90+X-L ��ȫ������Ϊ�룬������ڻ������Ҫ����ʽ�� 
//	sum_d1=90*3600+X_d*3600+X_f*60+X_m;
//	sum_d2=L_d*3600+L_f*60+L_m;
//	sum_d=sum_d1-sum_d2;
//	fin_d=sum_d/3600;
//	fin_f=(sum_d%3600)/60;
//	fin_m=sum_d-fin_d*3600-fin_f*60; 
//	����Ƕȣ�90-L ��ȫ������Ϊ�룬������ڻ������Ҫ����ʽ�� 
	sum_d1=90*3600;
	sum_d2=L_d*3600+L_f*60+L_m;
	sum_d=sum_d1-sum_d2;
	fin_d=sum_d/3600;
	fin_f=abs((sum_d%3600)/60);
	fin_m=abs(((sum_d%3600)%60));
//	��ʾ�Ƕ�90-L���Ƕȣ� 
	cout<<"��ʾ�Ƕ�90-L���Ƕ�)"<<fin_d<<" "<<fin_f<<" "<<fin_m<<endl;
//	�õ����յĽǶ�(�ȣ��֣���) 
	angle = fin_d + fin_f/60 + fin_m/3600;
	result =(angle*PI)/180;
	cout<<"��ʾ�Ƕ�90-L�����ȣ�:"<<result<<endl;
	degree_cos=cos(result);
	degree_tan=tan(result);
	cout<<"cos(90-L):"<<degree_cos<<endl;
	cout<<"tan(90-L):"<<degree_tan<<endl;
//	����A
	A=100*B*pow(degree_cos,2);
	H=H1+A*degree_tan+i_1+i_2;
	fin=A*degree_tan+i_1+i_2;
//	���
	cout<<"*************************************"<<endl;
	cout<<"ˮƽ����="<<A<<endl;
	cout<<"�߳�="<<H<<endl; 
	cout<<"�߲�="<<fin<<endl; 
	cout<<"*************************************"<<endl;
}
} 
