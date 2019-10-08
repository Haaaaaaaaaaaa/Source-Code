//先来先服务 
#include <iostream>
#include <iomanip>
#include <stdio.h> 
#include<string>
using namespace std;
double time1 = 0;
class pcb
{
public:
	string name;
	double arrivetime;
	double alltime;
	double starttime;
	double endtime;
	int state;
	pcb()
	{
		starttime = -1;
		endtime = -1;
	}
	void print()
	{
		cout << name << setw(11) << arrivetime << setw(11) << alltime << setw(11) << starttime << setw(11) << endtime << endl;
	}
};
void show(pcb* a, int n)
{
	cout << "*************************************************************************" << endl;
	cout << "进程名   到达时间  服务时间  开始执行  完成时间" << endl;
	for (int i = 0; i < n; i++)
	{
		a[i].print();
	}
	cout << "*************************************************************************" << endl;
	cout << endl;
}
/*对进程按到达时间排序*/
void sort(pcb* a, int n)
{
	for (int i = 0; i < n; i++)
	{
		for (int j = i + 1; j < n; ++j)
		{
			if (a[i].arrivetime < a[j].arrivetime)
				continue;
			else if (a[i].arrivetime == a[j].arrivetime)
			{
				if (a[i].alltime > a[j].alltime)
				{
					pcb t;
					t = a[i];
					a[i] = a[j];
					a[j] = t;
				}
			}
			else if (a[i].arrivetime > a[j].arrivetime)
			{
				pcb t;
				t = a[i];
				a[i] = a[j];
				a[j] = t;
			}
		}
	}
}
/*运行函数*/
void run(pcb* a, int n)
{
	time1 = a[0].arrivetime;
	for (int i = 0; i < n; i++)
	{
		a[i].starttime = time1;
		a[i].endtime = a[i].starttime + a[i].alltime;
		time1 = a[i].endtime;
		cout << "正在执行第" << i + 1 << "个进程 " << a[i].name << " :" << endl;
		show(a, n);
	}
}
int main()
{
	int n;
	cout << "请输入要创建的进程个数：";
	cin >> n;
	pcb* p;
	p = new pcb[n * sizeof(pcb)];
	cout << "请输入进程名，到达时间，服务时间:" << endl;

	for (int i = 0; i < n; ++i)
	{
		cin >> p[i].name >> p[i].arrivetime >> p[i].alltime;
	}
	cout << "需要执行的进程状态:" << endl;
	sort(p, n);

	run(p, n);
	return 0;
}

