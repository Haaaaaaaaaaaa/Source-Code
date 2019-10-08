// ConsoleApplication3.cpp : ���ļ����� "main" ����������ִ�н��ڴ˴���ʼ��������
//

#include <iostream>
#include <iomanip>
#include <stdio.h> 
#include<string>
using namespace std;
double time1 = 0;		//�ѽ���ʱ��
int n = 0;			//�ܽ�����
int rn = 0;         //����������
int fn = 0;         //��ɽ�����
class pcb
{
public:
	string name;
	double arrivetime;				//����ʱ��
	double alltime;                   //����ʱ��
	double starttime;				//��ʼʱ��
	double endtime;						//����ʱ��
	double dotime;				//�ѽ���ʱ��
	int priority;					//������
	int state;						//����״̬
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
	cout << "������        ����ʱ��         ����ʱ��         ��ʼʱ��         ����ʱ��" << endl;
	for (int i = 0; i < n; i++)
	{
		cout << a[i].name << setw(17) << a[i].arrivetime << setw(17) << a[i].dotime << setw(17) << a[i].starttime << setw(17) << a[i].endtime << endl;
	}
	cout << "=========================================================================" << endl;
	cout << endl;
}
/*������ʱ��Խ��̽�������*/
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
/*�Ծ����Ľ������ȼ�����*/
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
/*�жϵ�ǰ���н����Ƿ�������ɣ���������Ӿ���������ɾ��*/
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
/*���к���*/
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
		cout << "��"<<setw(3) << i+1 << "��ʱ��Ƭ" << "����ִ��" << a[0].name << "����      ";
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
	cout << "������Ҫ�����Ľ��̸�����";
	cin >> m;
	n = m;
	pcb* p,* q;
	q = new pcb[m * sizeof(pcb)];
	p = new pcb[m * sizeof(pcb)];
	cout << "�����������������ʱ�䣬����ʱ��,������:" << endl;

	for (int i = 0; i < n; i++)
	{
		cin >> p[i].name >> p[i].arrivetime >> p[i].alltime >> p[i].priority;
	}
	for (int i = 0; i < n; i++)
	{
		t = t + p[i].alltime;
	}
	cout << "��Ҫִ�еĽ���״̬:" << "           ������         ��Ҫ����ʱ��     �ѽ���ʱ��       ������" << endl;
	sort(p, n);
	t = t + p[0].arrivetime;
	run(p, t,q);
	show(q,fn);
	delete[] p,q;
	return 0;
	system("pause");
}

