//Program name	:设计一个有N个进程进程调度程序
//Date        	:9/5/2019
//Version number:Dev-C++ 5.11
#include<stdio.h>
#include<malloc.h>
#include<time.h>
#include<windows.h>
#include<math.h>

typedef struct PCB          //定义结构体PCB进程控制块
{
	char NAME[20];          //结构体变量，进程名
	long ID;                //进程id
	int RUNTIME;            //进程运行时间
	char STATE[6];          //进程状态 ready  wait  run
	int PRIORITY;           //权值 
}PCB;

typedef struct QNode		//单链表
{  
	PCB  pcb;
	struct QNode *next;
}QueuePtr;

typedef struct LinkQueue     //链队列
{  
	int prior;               //优先级
	QueuePtr *front;         //结构体里的QueuePtr类型指针变量，指向该优先级的进程的头结点
	QueuePtr *rear;          //结构体里的QueuePtr类型指针变量，指向该优先级的进程的尾结点
	int PartTime;            //时间片
}LinkQueue;

LinkQueue Readyqueue[10];		//链队列的单链表
int N;                          //N为当前进程数
void schedule();                //声明调度函数
void display();                    //声明输出函数

void InitQueue()				//  队列的初始化、给每个队列加个头结点
{
	for(int i=0;i<10;i++)
	{   
		Readyqueue[i].PartTime=(int )pow(2.0,i+1);					//每个进程的时间片
		Readyqueue[i].prior=9-i;									//每进程的优先级
		Readyqueue[i].front=(QueuePtr*)malloc(sizeof(QueuePtr));    //为进程申请空间
		Readyqueue[i].rear=Readyqueue[i].front;						//初始化单链的头结点和尾结点指向同一位置
		Readyqueue[i].front->next=NULL;								//初始化时Readyqueue[i].front->next为空
	}
}

void Create()
{ 
	InitQueue();
	char name[20];  
	long id=123456;								//定义ID和初始化为201031101
	int m;   									//进程个数 
	QueuePtr *p;
	int yunxingshijian,youxianji;				//运行时间、优先级   
//	printf("\t输入要创建进程的数目：");
	fflush(stdin);
//	scanf("%d",&m);
	m=3 ;										//直接程序中定义进程个数 
	for(int j=1;j<=m;j++)						//创建用户所需进程m个
	{   
		printf("\t输入进程名:");					//用户输入用户名
		scanf_s("%s",&name);
		srand((int)time(0));        
		yunxingshijian=1+(int)(rand()%30);      //随机生成一个整数赋给运行时间
		printf("\t运行时间：%d",yunxingshijian);
		srand((int)time(0));   
		youxianji=1+(int)(rand()%9);					//随机生成一个整数（1~9）赋给优先级
		printf("\t优先级：%d",youxianji);
		p=(QueuePtr *)malloc(sizeof(QueuePtr));			//插入就绪队列       
		QueuePtr *k;
		for(int i=0;i<9;i++)							//通过优先级寻找该进程应放置的队列
		{
			if(youxianji==9-i)
			{
				k=Readyqueue[i].front;					//k为移动指针，寻找队列末尾进程                               
				strcpy_s(p->pcb.NAME,name);				//将名字赋给PCB块
				strcpy_s(p->pcb.STATE,"Ready");			//将进程状态赋给PCB块
				p->pcb.PRIORITY=youxianji;				//将优先级赋给PCB块
				p->pcb.RUNTIME=yunxingshijian;			//将运行时间赋给PCB块
				p->pcb.ID=id;							//将id号赋给PCB块
				if(k->next!=NULL)
				{
					k=k->next;							//k指针在寻找队列末尾进程
				}
				k->next=p;								//将p接到队尾
				p->next=NULL;							//将队尾的next置为空 
				Readyqueue[i].rear=p;
			}               
		}
		N++;											//保存当前就绪进程数
		id++;											//ID自动加"1"
		printf("\n\t第%d个进程创建成功!\n\n",N);   
	}   
	display();												//调用输出函数show()
}

void schedule()
{
	QueuePtr *p;
	for(int i=0;i<9;i++)
	{
		while(Readyqueue[i].front->next!=NULL)
		{
			p=Readyqueue[i].front->next;						//p指向Readyqueue[i].front->next的结点
			Sleep((int )pow(2.0,i+1));							//调用函数Sleep()使进程i休眠
			p->pcb.RUNTIME=p->pcb.RUNTIME-(int )pow(2.0,i+1);		//进程的时间减pow(2,i+1)
			strcpy_s(p->pcb.STATE,"run");							//调用strcpy()把状态run复给p->pcb.STATE
			p->pcb.PRIORITY--;									//权值减减                                 
			display();
			if(p->pcb.RUNTIME<0||p->pcb.RUNTIME==0)				//判断p->pcb.RUNTIME是否<0或=0
			{
				Readyqueue[i].front->next=p->next;				//p前移
				Readyqueue[9].rear->next=p;						//把p移到运行结束的队列Readyqueue[9].rear
				p->next=NULL;
				Readyqueue[9].rear=p;							//使Readyqueue[9].rear指向最后一个结点               
				strcpy_s(p->pcb.STATE,"finish");					//调用strcpy()把状态finish复给p->pcb.STATE
				display();											//调用输出函数    show()               
			}
			else
			{
				Readyqueue[i].front->next=p->next;
				Readyqueue[i+1].rear->next=p;					//p移到下一队列的队尾
				Readyqueue[i+1].rear=p;							//使Readyqueue[i+1].rear指向最后一个结点
				p->next=NULL;           
				strcpy_s(p->pcb.STATE,"ready");					//调用strcpy()把状态ready复给p->pcb.STATE
			}
		}
	}
}

void display()													// 队列输出函数
{   
	QueuePtr  *q=NULL;
	printf("\t名字    ID  	运行时间 优先级  状态\n");
	for(int j=0;j<10;j++)
	{   
		q=Readyqueue[j].front->next;
		while(q!=NULL)
		{
			printf("\t%s,\t%ld,\t%d,\t%d,\t%s\n",q->pcb.NAME,q->pcb.ID,q->pcb.RUNTIME,q->pcb.PRIORITY,q->pcb.STATE);
			q=q->next ;
		}   
	}   
}

int main()               
{ 
	Create();
	char choice;       
	printf("\t请选择是否要对以上进程进行调度?(y(Y)或n(N))");
	scanf_s("%s",&choice);										//输入choice
	while(1)
	{
		if(choice=='Y'||choice=='y')                            //判断choice的值                       
		{
			schedule();											//如果choice的值是Y或y 调度schedule()函数
			printf("\t所有进程 (%d个) 运行结束！\n",N);
			break;                                              //调度结束、退出程序
		}
		if(choice=='N'||choice=='n')                            //如果choice的值是N或n
			exit(0);                                            //退出程序   
		if(choice!='Y'&&choice!='y'&&choice!='N'&&choice!='n')	//如果choice的值不是Y或或N或n
		{
			printf("\t\t\t您的选择有误，请重新输入!\n");			//输出出错、重新输入
			scanf_s("%s",&choice);
		}
	}
	system("pause");
}

