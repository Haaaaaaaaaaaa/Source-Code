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
//	输入
	cout<<"输入B:";
	cin>>B;
//	cout<<"输入X的度:";
//	cin>>X_d;
//	cout<<"输入X的分:";
//	cin>>X_f;
//	cout<<"输入X的秒:";
//	cin>>X_m;
	cout<<"输入L的度:";
	cin>>L_d;
	cout<<"输入L的分:";
	cin>>L_f;
	cout<<"输入L的秒:";
	cin>>L_m;
	cout<<"输入H1:";
	cin>>H1;
	cout<<"输入i_1:";
	cin>>i_1;
	cout<<"输入i_2:";
	cin>>i_2;
//	换算角度：90+X-L （全部换算为秒，运算后，在换算成需要的形式） 
//	sum_d1=90*3600+X_d*3600+X_f*60+X_m;
//	sum_d2=L_d*3600+L_f*60+L_m;
//	sum_d=sum_d1-sum_d2;
//	fin_d=sum_d/3600;
//	fin_f=(sum_d%3600)/60;
//	fin_m=sum_d-fin_d*3600-fin_f*60; 
//	换算角度：90-L （全部换算为秒，运算后，在换算成需要的形式） 
	sum_d1=90*3600;
	sum_d2=L_d*3600+L_f*60+L_m;
	sum_d=sum_d1-sum_d2;
	fin_d=sum_d/3600;
	fin_f=abs((sum_d%3600)/60);
	fin_m=abs(((sum_d%3600)%60));
//	显示角度90-L（角度） 
	cout<<"显示角度90-L（角度)"<<fin_d<<" "<<fin_f<<" "<<fin_m<<endl;
//	得到最终的角度(度，分，秒) 
	angle = fin_d + fin_f/60 + fin_m/3600;
	result =(angle*PI)/180;
	cout<<"显示角度90-L（弧度）:"<<result<<endl;
	degree_cos=cos(result);
	degree_tan=tan(result);
	cout<<"cos(90-L):"<<degree_cos<<endl;
	cout<<"tan(90-L):"<<degree_tan<<endl;
//	计算A
	A=100*B*pow(degree_cos,2);
	H=H1+A*degree_tan+i_1+i_2;
	fin=A*degree_tan+i_1+i_2;
//	输出
	cout<<"*************************************"<<endl;
	cout<<"水平距离="<<A<<endl;
	cout<<"高程="<<H<<endl; 
	cout<<"高差="<<fin<<endl; 
	cout<<"*************************************"<<endl;
}
} 
