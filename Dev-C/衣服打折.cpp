#include <stdio.h>
int main(){
	int price=95;
	double sum_price;
	int num;
	scanf("%d",&num);
	if(num<=3)
		sum_price=num*price;
	else
		sum_price=num*price*0.85;
	printf("%.2f",sum_price);
} 
