//Program name	:���һ����N�����̽��̵��ȳ���
//Date        	:9/5/2019
//Version number:Dev-C++ 5.11
#include<stdio.h>
#include<malloc.h>
#include<time.h>
#include<windows.h>
#include<math.h>

typedef struct PCB          //����ṹ��PCB���̿��ƿ�
{
	char NAME[20];          //�ṹ�������������
	long ID;                //����id
	int RUNTIME;            //��������ʱ��
	char STATE[6];          //����״̬ ready  wait  run
	int PRIORITY;           //Ȩֵ 
}PCB;

typedef struct QNode		//������
{  
	PCB  pcb;
	struct QNode *next;
}QueuePtr;

typedef struct LinkQueue     //������
{  
	int prior;               //���ȼ�
	QueuePtr *front;         //�ṹ�����QueuePtr����ָ�������ָ������ȼ��Ľ��̵�ͷ���
	QueuePtr *rear;          //�ṹ�����QueuePtr����ָ�������ָ������ȼ��Ľ��̵�β���
	int PartTime;            //ʱ��Ƭ
}LinkQueue;

LinkQueue Readyqueue[10];		//�����еĵ�����
int N;                          //NΪ��ǰ������
void schedule();                //�������Ⱥ���
void display();                    //�����������

void InitQueue()				//  ���еĳ�ʼ������ÿ�����мӸ�ͷ���
{
	for(int i=0;i<10;i++)
	{   
		Readyqueue[i].PartTime=(int )pow(2.0,i+1);					//ÿ�����̵�ʱ��Ƭ
		Readyqueue[i].prior=9-i;									//ÿ���̵����ȼ�
		Readyqueue[i].front=(QueuePtr*)malloc(sizeof(QueuePtr));    //Ϊ��������ռ�
		Readyqueue[i].rear=Readyqueue[i].front;						//��ʼ��������ͷ����β���ָ��ͬһλ��
		Readyqueue[i].front->next=NULL;								//��ʼ��ʱReadyqueue[i].front->nextΪ��
	}
}

void Create()
{ 
	InitQueue();
	char name[20];  
	long id=123456;								//����ID�ͳ�ʼ��Ϊ201031101
	int m;   									//���̸��� 
	QueuePtr *p;
	int yunxingshijian,youxianji;				//����ʱ�䡢���ȼ�   
//	printf("\t����Ҫ�������̵���Ŀ��");
	fflush(stdin);
//	scanf("%d",&m);
	m=3 ;										//ֱ�ӳ����ж�����̸��� 
	for(int j=1;j<=m;j++)						//�����û��������m��
	{   
		printf("\t���������:");					//�û������û���
		scanf_s("%s",&name);
		srand((int)time(0));        
		yunxingshijian=1+(int)(rand()%30);      //�������һ��������������ʱ��
		printf("\t����ʱ�䣺%d",yunxingshijian);
		srand((int)time(0));   
		youxianji=1+(int)(rand()%9);					//�������һ��������1~9���������ȼ�
		printf("\t���ȼ���%d",youxianji);
		p=(QueuePtr *)malloc(sizeof(QueuePtr));			//�����������       
		QueuePtr *k;
		for(int i=0;i<9;i++)							//ͨ�����ȼ�Ѱ�Ҹý���Ӧ���õĶ���
		{
			if(youxianji==9-i)
			{
				k=Readyqueue[i].front;					//kΪ�ƶ�ָ�룬Ѱ�Ҷ���ĩβ����                               
				strcpy_s(p->pcb.NAME,name);				//�����ָ���PCB��
				strcpy_s(p->pcb.STATE,"Ready");			//������״̬����PCB��
				p->pcb.PRIORITY=youxianji;				//�����ȼ�����PCB��
				p->pcb.RUNTIME=yunxingshijian;			//������ʱ�丳��PCB��
				p->pcb.ID=id;							//��id�Ÿ���PCB��
				if(k->next!=NULL)
				{
					k=k->next;							//kָ����Ѱ�Ҷ���ĩβ����
				}
				k->next=p;								//��p�ӵ���β
				p->next=NULL;							//����β��next��Ϊ�� 
				Readyqueue[i].rear=p;
			}               
		}
		N++;											//���浱ǰ����������
		id++;											//ID�Զ���"1"
		printf("\n\t��%d�����̴����ɹ�!\n\n",N);   
	}   
	display();												//�����������show()
}

void schedule()
{
	QueuePtr *p;
	for(int i=0;i<9;i++)
	{
		while(Readyqueue[i].front->next!=NULL)
		{
			p=Readyqueue[i].front->next;						//pָ��Readyqueue[i].front->next�Ľ��
			Sleep((int )pow(2.0,i+1));							//���ú���Sleep()ʹ����i����
			p->pcb.RUNTIME=p->pcb.RUNTIME-(int )pow(2.0,i+1);		//���̵�ʱ���pow(2,i+1)
			strcpy_s(p->pcb.STATE,"run");							//����strcpy()��״̬run����p->pcb.STATE
			p->pcb.PRIORITY--;									//Ȩֵ����                                 
			display();
			if(p->pcb.RUNTIME<0||p->pcb.RUNTIME==0)				//�ж�p->pcb.RUNTIME�Ƿ�<0��=0
			{
				Readyqueue[i].front->next=p->next;				//pǰ��
				Readyqueue[9].rear->next=p;						//��p�Ƶ����н����Ķ���Readyqueue[9].rear
				p->next=NULL;
				Readyqueue[9].rear=p;							//ʹReadyqueue[9].rearָ�����һ�����               
				strcpy_s(p->pcb.STATE,"finish");					//����strcpy()��״̬finish����p->pcb.STATE
				display();											//�����������    show()               
			}
			else
			{
				Readyqueue[i].front->next=p->next;
				Readyqueue[i+1].rear->next=p;					//p�Ƶ���һ���еĶ�β
				Readyqueue[i+1].rear=p;							//ʹReadyqueue[i+1].rearָ�����һ�����
				p->next=NULL;           
				strcpy_s(p->pcb.STATE,"ready");					//����strcpy()��״̬ready����p->pcb.STATE
			}
		}
	}
}

void display()													// �����������
{   
	QueuePtr  *q=NULL;
	printf("\t����    ID  	����ʱ�� ���ȼ�  ״̬\n");
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
	printf("\t��ѡ���Ƿ�Ҫ�����Ͻ��̽��е���?(y(Y)��n(N))");
	scanf_s("%s",&choice);										//����choice
	while(1)
	{
		if(choice=='Y'||choice=='y')                            //�ж�choice��ֵ                       
		{
			schedule();											//���choice��ֵ��Y��y ����schedule()����
			printf("\t���н��� (%d��) ���н�����\n",N);
			break;                                              //���Ƚ������˳�����
		}
		if(choice=='N'||choice=='n')                            //���choice��ֵ��N��n
			exit(0);                                            //�˳�����   
		if(choice!='Y'&&choice!='y'&&choice!='N'&&choice!='n')	//���choice��ֵ����Y���N��n
		{
			printf("\t\t\t����ѡ����������������!\n");			//���������������
			scanf_s("%s",&choice);
		}
	}
	system("pause");
}

