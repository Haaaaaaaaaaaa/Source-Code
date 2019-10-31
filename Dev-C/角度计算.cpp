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
//	输入
	cout<<"输入上丝读数:";
	cin>>a1;
	cout<<"输入下丝读数:";
	cin>>a2;
	cout<<"输入仪器高:";
	cin>>i_1;
	cout<<"输入控制点高程:";
	cin>>H1;
	cout<<"输入竖盘读数的度:";
	cin>>L_d;
	cout<<"输入竖盘读数的分:";
	cin>>L_f;
	cout<<"输入竖盘读数的秒:";
	cin>>L_m;

	sum_d1=90*3600;
	sum_d2=L_d*3600+L_f*60+L_m;
	sum_d=sum_d1-sum_d2;
	cout<<"sum_d:"<<sum_d<<endl;
	fin_d=sum_d/3600;
	fin_f=(sum_d%3600)/60;
	fin_m=(sum_d%3600)%60;
//	显示角度90-L（角度） 
	cout<<"显示角度90-L（角度)"<<fin_d<<" "<<fin_f<<" "<<fin_m<<endl;
//	得到最终的角度(度，分，秒) 
	angle = fin_d + fin_f/60.0 + fin_m/3600.0;
	cout<<"显示角度90-L（只有度)"<<angle<<endl; 
	result =(angle*PI)/180;
	cout<<"显示角度90-L（弧度）:"<<result<<endl;
	degree_cos=cos(result);
	degree_tan=tan(result);
	cout<<"cos(90-L):"<<degree_cos<<endl;
	cout<<"tan(90-L):"<<degree_tan<<endl;
//	计算A
	B=(a1-a2)/1000.0;
	i_2=(a1+a2)/2000.0;
	A=100*B*pow(degree_cos,2);
	H=H1+A*degree_tan+i_1-i_2;
	fin=A*degree_tan+i_1-i_2;
//	输出
	cout<<"*************************************"<<endl;
	cout<<"尺间隔="<<B<<endl;
	cout<<"中丝读数="<<i_2<<endl;
	cout<<"竖直角(90-L)="<<fin_d<<"度"<<fin_f<<"分"<<fin_m<<"秒"<<endl;
	cout<<"水平距离="<<A<<endl;
	cout<<"高差="<<fin<<endl; 
	cout<<"高程="<<H<<endl; 
	cout<<"*************************************"<<endl;
}
} 
