#include<iostream> 
#include<math.h> 
using namespace std;
#define PI 3.1415926
int main(){
	double A,B,H1,i_1,i_2,H,angle,result,degree_cos,degree_tan,fin,a1,a2;
	int L_d,L_f,L_m;
	int sum_d,sum_d1,sum_d2;
	int fin_d,fin_f,fin_m;
	while(1){ 
//	����
	cout<<"������˿����:";
	cin>>a1;
	cout<<"������˿����:";
	cin>>a2;
	cout<<"����������:";
	cin>>i_1;
	cout<<"������Ƶ�߳�:";
	cin>>H1;
	cout<<"�������̶����Ķ�:";
	cin>>L_d;
	cout<<"�������̶����ķ�:";
	cin>>L_f;
	cout<<"�������̶�������:";
	cin>>L_m;

	sum_d1=90*3600;
	sum_d2=L_d*3600+L_f*60+L_m;
	sum_d=sum_d1-sum_d2;
	cout<<"sum_d:"<<sum_d<<endl;
	fin_d=sum_d/3600;
	fin_f=(sum_d%3600)/60;
	fin_m=(sum_d%3600)%60;
//	��ʾ�Ƕ�90-L���Ƕȣ� 
	cout<<"��ʾ�Ƕ�90-L���Ƕ�)"<<fin_d<<" "<<fin_f<<" "<<fin_m<<endl;
//	�õ����յĽǶ�(�ȣ��֣���) 
	angle = fin_d + fin_f/60.0 + fin_m/3600.0;
	cout<<"��ʾ�Ƕ�90-L��ֻ�ж�)"<<angle<<endl; 
	result =(angle*PI)/180;
	cout<<"��ʾ�Ƕ�90-L�����ȣ�:"<<result<<endl;
	degree_cos=cos(result);
	degree_tan=tan(result);
	cout<<"cos(90-L):"<<degree_cos<<endl;
	cout<<"tan(90-L):"<<degree_tan<<endl;
//	����A
	B=(a1-a2)/1000.0;
	i_2=(a1+a2)/2000.0;
	A=100*B*pow(degree_cos,2);
	H=H1+A*degree_tan+i_1-i_2;
	fin=A*degree_tan+i_1-i_2;
//	���
	cout<<"*************************************"<<endl;
	cout<<"�߼��="<<B<<endl;
	cout<<"��˿����="<<i_2<<endl;
	cout<<"��ֱ��(90-L)="<<fin_d<<"��"<<fin_f<<"��"<<fin_m<<"��"<<endl;
	cout<<"ˮƽ����="<<A<<endl;
	cout<<"�߲�="<<fin<<endl; 
	cout<<"�߳�="<<H<<endl; 
	cout<<"*************************************"<<endl;
}
} 
