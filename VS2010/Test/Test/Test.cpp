// ConsoleApplication3.cpp : 此文件包含 "main" 函数。程序执行将在此处开始并结束。
//

#include <iostream>
#include <iomanip>
#include <stdio.h> 
#include<string>
using namespace std;
double time1 = 0;		//已进行时间
int n = 0;			//总进程数
int rn = 0;         //就绪进程数
int fn = 0;         //完成进程数
class pcb
{
public:
	string name;
	double arrivetime;				//到达时间
	double alltime;                   //服务时间
	double starttime;				//开始时间
	double endtime;						//结束时间
	double dotime;				//已进行时间
	int priority;					//优先数
	int state;						//进程状态
	void print()
	{
		cout << name <<setw(17)<<alltime << setw(17) << dotime << setw(17) << priority  <<endl;
	}
	pcb()
	{
		dotime = 0;
		state = 0;
	}
};
void show(pcb* a, int n)
{
	cout << "========================================================================" << endl;
	cout << "进程名        到达时间         服务时间         开始时间         结束时间" << endl;
	for (int i = 0; i < n; i++)
	{
		cout << a[i].name << setw(17) << a[i].arrivetime << setw(17) << a[i].dotime << setw(17) << a[i].starttime << setw(17) << a[i].endtime << endl;
	}
	cout << "=========================================================================" << endl;
	cout << endl;
}
/*按到达时间对进程进行排序*/
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
				if (a[i].priority < a[j].priority)
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
/*对就绪的进程优先级排序*/
void sort1(pcb* a, int t)
{
	for (int i = 0; i < rn; i++)
	{
		for (int j = i; a[j].arrivetime <= t && j < n; j++)
		{
			if (a[i].priority < a[j].priority)
			{
				pcb t;
				t = a[i];
				a[i] = a[j];
				a[j] = t;
			}
		}
	}
}
/*判断当前运行进程是否运行完成，如果完成则从就绪队列中删除*/
void inspect(pcb* a,pcb* b)
{
	if (a[0].dotime == a[0].alltime)
	{
		a[0].state = -1;
		a[0].endtime = time1+1;
		b[fn] = a[0];
		for (int i = 1; i < n; i++)
		{
			a[i - 1] = a[i];
		}
		n--;
		fn++;
	}
}
/*运行函数*/
void run(pcb* a, int t,pcb* b)
{
	time1 = a[0].arrivetime;
	double m = a[0].arrivetime;
	for (double i = m; i < t; i++)
	{
		sort1(a, i);
		if (a[0].arrivetime == i)
		{
			a[0].state = 1;
			rn++;
		}
		if (a[0].dotime == 0)
		{
			a[0].starttime = i;
		}
		cout << "第"<<setw(3) << i+1 << "个时间片" << "正在执行" << a[0].name << "进程      ";
		a[0].dotime++;
		a[0].priority = a[0].priority - 3;
		for (int j = 1; j < n; j++)
		{
			a[j].priority++;
		}
		a[0].print();
		inspect(a,b);
		time1++;
	}
}
int main()
{
	int m;
	double t = 0;
	cout << "请输入要创建的进程个数：";
	cin >> m;
	n = m;
	pcb* p,* q;
	q = new pcb[m * sizeof(pcb)];
	p = new pcb[m * sizeof(pcb)];
	cout << "请输入进程名，到达时间，服务时间,优先数:" << endl;

	for (int i = 0; i < n; i++)
	{
		cin >> p[i].name >> p[i].arrivetime >> p[i].alltime >> p[i].priority;
	}
	for (int i = 0; i < n; i++)
	{
		t = t + p[i].alltime;
	}
	cout << "需要执行的进程状态:" << "           进程名         需要服务时间     已进行时间       优先数" << endl;
	sort(p, n);
	t = t + p[0].arrivetime;
	run(p, t,q);
	show(q,fn);
	delete[] p,q;
	return 0;
	system("pause");
}

