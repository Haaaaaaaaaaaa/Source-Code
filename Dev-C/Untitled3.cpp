#include <iostream>
using namespace std;
class building{
public:
	building();
	float get_floor_area();
	int get_number_of_windoes();
private:
	float floor_area;
	int number_of_windows;
}; 
building::building(){
	cout<<"enter floor area:"<<endl;
	cin>>floor_area;
	cout<<"enter number_of_windows:"<<endl;
	cin>>number_of_windows;
}
float building::get_floor_area(){
	return floor_area;
}
int building::get_number_of_windoes(){
	return number_of_windows;
}
class college:public building{
public:
	college();
	int get_number_of_A();
	int get_number_of_B();
private:
	int number_of_A;
	int number_of_B;
};
college::college(){
	cout<<"enter number of A:"<<endl;
	cin>>number_of_A;
	cout<<"enter number of B:"<<endl;
	cin>>number_of_B;
}
int college::get_number_of_A(){
	return number_of_A;
}

int college::get_number_of_B(){
	return number_of_B;
}

int main(){
	college c1;
	cout<<"floor area is:"<<c1.get_floor_area()<<endl;
	cout<<"number of windows:"<<c1.get_number_of_windoes()<<endl;
	return 0;
}



