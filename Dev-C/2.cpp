#include <iostream>
#include <iomanip>
using namespace std;
class bank_account
{
	public:
		bank_account(int acc_no,double initial_balance);
		void deposit(double account);
		void withdraw(double account);
		void display_balance();
	private:
		int account_number;
		double balance;
}; 

bank_account::bank_account(int acc_no,double initial_balance):account_number(acc_no),balance(initial_balance) 
{
}

void bank_account::deposit(double account){
	balance+=account;
}

void bank_account::withdraw(double account){
	balance-=account;
}
void bank_account::display_balance(){
	cout<<"Balance in Account"<<account_number<<"is"<<fixed<<setprecision(2)<<balance<<endl;
}
