#include<stdio.h>
#include<stdlib.h>

typedef struct pcb      //pcb�ṹ�嶨��;
{
	int ID;				//���̵�ID��
	int Priority;		//���̵����ȼ�     //����ÿ��pcb�����ȼ���һ��;
	int Cometime;		//���̵ĵ���ʱ��
	int AllTime;		//���̻���Ҫ������ʱ��;
	int CPUTime;		//������ʹ��CPU��ʱ��;
	int StartBlock;	    //��������StartBlockʱ��Ƭ���������;
	int StartTime;      //��������StartTimeʱ��Ƭ��������; //����Ϊ�ɱ䣬ÿ����̬һ��ʱ��Ƭ��-1��
	char State;		    //���̵�״̬��R,N,B,F;
	struct pcb* Next;	//���̶��е���һ�����̵�ָ��;
} PCB;

int num = 5;			//Ĭ�ϴ���5������;
int TheTime = 0;		//����ʱ�䣬����ʼʱ��Ϊ0����һ��ʱ��ƬΪ��λ;
int dxTime = 5;			//ʱ��Ƭ��С;   //���鱻���ĺͲ��ֱ�����
PCB* Rpcb = NULL; //��������ͷָ��;
//PCB* Bpcb = NULL; //��������ͷָ��;
//PCB* Npcb = NULL; //����ָ��;
PCB* Fpcb = NULL; //��ɶ���ͷָ��;

PCB* CreateApcb(int id,int Priority,int ctime,int atime,int cputime,int sblock,int stime,char state,PCB* Next); //�����������̣�
PCB* CreateReadyPcb();		//�����������е�pcb,�������Զ��������;
PCB* GetHighPrio(PCB* head); //���һ�����������ȼ���ߵĽ��̣�����ָ��ý��̵�ָ��
void DiaoDuReadyList();  //����̬���ȼ������ȣ�ÿ��һ��ʱ��Ƭ�����ȼ�-3��û�������������
void FCFS();				//�����ȷ����㷨; 
PCB* GetShortPcb();			//�����̵Ľ���; 
void SJF();					//�ν��������㷨; 
void RR();					//��ת�����㷨; 
void InsertList(PCB** head,PCB* pnew);//��һ��pcb���뵽������;
void InsertFinList(PCB* fp)	;//����ɵĽ��̲��뵽��ɶ�����
void displayPCB(PCB* p);  //��ʾ���̶��еĽ���״̬;


int main()
{

	Rpcb = CreateReadyPcb();     //������������;
	displayPCB(Rpcb);
	//DiaoDuReadyList(Rpcb);		//��̬���ȼ�;
	//FCFS();					//�����ȷ������;
	//SJF();					//�̽�������
	RR();						//��ת����; 
	system("pause");
	return 0;
}

//�����������̣�
PCB* CreateApcb(int id,int Priority,int ctime,int atime,int cputime,int sblock,int stime,char state,PCB* Next)
{
	PCB* p =  (PCB*)malloc(sizeof(PCB));
	p->ID = id;
	p->Priority = Priority;
	p->Cometime = ctime;
	p->AllTime = atime;
	p->CPUTime = cputime;
	p->StartBlock = sblock;//��ʱ�Ȳ�����;
	p->StartTime = stime;//
	p->State = state;
	p->Next  = Next;
	return p;
}

PCB* CreateReadyPcb()  	//�����������е�pcb,�������Զ��������;
{
	PCB* rPCB = NULL;       //�������е�ͷָ��;
	PCB* p1 = NULL;			//�����м�ת��ָ��;
	PCB* p2 = NULL;
	p1 = p2 = (PCB*)malloc(sizeof(PCB));
	//����1;
	p1 = CreateApcb(1,3,0,15,0,20,10,'R',NULL);	//��������Ȳ�����;
	rPCB  = p1;
	//����2��
	p2 = CreateApcb(2,5,10,55,0,60,10,'R',NULL);
	p1->Next = p2;
	p1 = p2;
	//����3;
	p2 = CreateApcb(3,1,20,15,0,60,10,'R',NULL);
	p1->Next = p2;
	p1 = p2;
	//����4;
	p2 = CreateApcb(4,4,30,25,0,60,10,'R',NULL);
	p1->Next = p2;
	p1 = p2;
	//����5;
	p2 = CreateApcb(5,2,40,10,0,60,10,'R',NULL);
	p1->Next = p2;
	p1 = p2;
	return rPCB;

}

/*���һ�����������ȼ���ߵĽ��̣�����ָ��ý��̵�ָ��*/
PCB* GetHighPrio(PCB* head)
{
	PCB* p = head;    			//�������б�ͷ�������Ȳ���ӵĽ���;
	PCB* q = p->Next;

	// while(q != NULL&&(q->Cometime<TheTime))    		//ʱ��˳������;
	while(q!=NULL)
	{
		if(q->Cometime<=TheTime)
		{
			if(p->Priority < q->Priority)
			{
				p = q;
			}
		}
		q=q->Next;
	}
	return p;
}


void DiaoDuReadyList()   //����̬���ȼ������ȣ�ÿ��һ��ʱ��Ƭ�����ȼ�-3��û�������������
{
	PCB* q = Rpcb;
	PCB* front = Rpcb;
	PCB* old;
	q = GetHighPrio(Rpcb);    				//Ѱ�ҵ�ǰ�������������ȼ���ߵ�����;
	q->State = 'N';    						//��������̬���޸�״̬��
	TheTime+=dxTime;
	q->CPUTime+=dxTime;
	q->AllTime-=dxTime;
	q->State = 'R';						//����һ��ʱ��Ƭ���޸�״̬Ϊ����;
	q->Priority = (q->Priority) - 3;
	if(q->AllTime == 0)    				//�ý����������,�Ӿ���������ɾ��;
		//if(q->AllTime<=0) 				//����ʱ��Ƭ�Ƚϴ�ʱ
	{
		q->State = 'F';					//����������ɣ��޸�״̬Ϊ'F';
		old = q;    					//ȡ��q;
		if(q==Rpcb)						//�����ͷָ��;
			Rpcb = q->Next;
		else if(q->Next != NULL)    			//������м�ָ��;
		{
			while(front->Next != q)
			{
				front = front->Next;
			}
			front->Next = q->Next;
			q = NULL;
		}
		else              					//�����βָ��;
		{
			while(front->Next != q)
			{
				front = front->Next;
			}
			front->Next = NULL;
			q = NULL;
		}
		InsertFinList(old);						//����ɵĽ��̲��뵽��ɶ�����;
	}
	printf("\n%d ��ʱ��Ƭ��,\n",TheTime);
	if(Rpcb!=NULL)
	{
		printf("\n������Ľ���״̬��\n");
		displayPCB(Rpcb);
		printf("\n��ɱ�Ľ���״̬��\n",TheTime);
		displayPCB(Fpcb);
		DiaoDuReadyList();
	}
	else
	{
		printf("\n�����Ľ��̶���������.\n\n");    //��ֹ����;
		printf("\n��ɱ�Ľ���״̬(����������������Ľ���)��\n",TheTime);
		displayPCB(Fpcb);
		return;
	}
}

void InsertList(PCB** head,PCB* pnew)
{
//��˳���С�������;
//	PCB* fp = NULL;   //ǰһ��ָ��ĵ�ַ��
//	PCB* cp = *head;   //��ǰָ��ĵ�ַ;
//	while(cp!=NULL&&cp->ID<pnew->ID)
//	{
//		fp = cp;
//		cp = cp->Next;
//	}
//	pnew->Next = cp;
//	if(fp==NULL)
//	{
//		*head  = pnew;
//	}
//	else
//	{
//		fp->Next  =pnew;
//	}
//	//���뵽�����;
	PCB* t = *head;
	if(Fpcb==NULL)
	{
		Fpcb = pnew;
		Fpcb->Next = NULL;
	}
	else
	{
		while(t->Next!=NULL)
		{
			t = t->Next;
		}
		t->Next = pnew;
		pnew->Next = NULL;
	}
}

void InsertFinList(PCB* pnew)  			//����ɵĽ��̼��뵽��ɶ�����;
{
	InsertList(&Fpcb,pnew);
}


void displayPCB(PCB* p)   //������еĽ���״̬��Ϣ;
{
	if(p!=NULL)
	{
		PCB* fp = p;
		printf("����ID,���ȼ�, ����ʱ��, ����Ҫ���е�ʱ��,CPU��ʱ��,����ʱ��1,����ʱ��2,�̵�״̬\n");
		while(fp!=NULL)
		{
			printf("%4d  %4d     %5d      %8d  %10d    %8d    %6d  %6c\n",fp->ID,fp->Priority,fp->Cometime,fp->AllTime,fp->CPUTime,fp->StartBlock,fp->StartTime,fp->State);
			fp = fp->Next;
		}
	}
	else
		printf("������û�н���");
}

//��������;
void FCFS()						//�����ȷ���;
{
	PCB* q = Rpcb;
	PCB* front = Rpcb;
	q = Rpcb;    					//Ѱ�ҵ�ǰ�������������ȼ���ߵ�����;
	q->State = 'N';    						//��������̬���޸�״̬��
	TheTime+=dxTime;
	q->CPUTime+=dxTime;
	q->AllTime-=dxTime;
//	q->Priority = (q->Priority) - 3;
	if(q->AllTime == 0)    				//�ý����������,�Ӿ���������ɾ��;
		//if(q->AllTime<=0) 			//����ʱ��Ƭ�Ƚϴ�ʱ
	{
		q->State = 'F';					//����������ɣ��޸�״̬Ϊ'F';
		Rpcb=Rpcb->Next;
		InsertFinList(q);				//����ɵĽ��̲��뵽��ɶ�����;
	}
	else								//����һ��ʱ��Ƭ���޸�״̬Ϊ����;
		q->State='R';
	printf("\n%d ��ʱ��Ƭ��,\n",TheTime);
	if(Rpcb!=NULL)
	{
		printf("\n������Ľ���״̬��\n");
		displayPCB(Rpcb);
		printf("\n��ɱ�Ľ���״̬��\n",TheTime);
		displayPCB(Fpcb);
		FCFS();
	}
	else
	{
		printf("\n�����Ľ��̶���������.\n\n");    //��ֹ����;
		printf("\n��ɱ�Ľ���״̬(����������������Ľ���)��\n",TheTime);
		displayPCB(Fpcb);
		return;
	}

}
PCB* GetShortPcb()		//Ѱ�ҵ�ǰ��������������ʱ����̵Ľ���;
{
	PCB* p = Rpcb;    			//�������б�ͷ�������Ȳ���ӵĽ���;
	PCB* q = p->Next;

	// while(q != NULL&&(q->Cometime<TheTime))    		//ʱ��˳������;
	while(q!=NULL)
	{
		if(q->Cometime<=TheTime)
		{
			if(p->AllTime > q->AllTime)
			{
				p = q;
			}
		}
		q=q->Next;
	}
	return p;
}

void SJF()			//�̽�������
{
	PCB* q = Rpcb;
	PCB* front = Rpcb;
	PCB* old;
	q = GetShortPcb();	   				//Ѱ�ҵ�ǰ���������л���Ҫ���е�ʱ����̵Ľ���;
	q->State = 'N';    						//��������̬���޸�״̬��
	TheTime+=dxTime;
	q->CPUTime+=dxTime;
	q->AllTime-=dxTime;
	//q->Priority = (q->Priority) - 3;
	if(q->AllTime == 0)    				//�ý����������,�Ӿ���������ɾ��;
		//if(q->AllTime<=0) 				//����ʱ��Ƭ�Ƚϴ�ʱ
	{
		q->State = 'F';					//����������ɣ��޸�״̬Ϊ'F';
		old = q;    					//ȡ��q;
		if(q==Rpcb)						//�����ͷָ��;
			Rpcb = q->Next;
		else if(q->Next != NULL)    			//������м�ָ��;
		{
			while(front->Next != q)
			{
				front = front->Next;
			}
			front->Next = q->Next;
			q = NULL;
		}
		else              					//�����βָ��;
		{
			while(front->Next != q)
			{
				front = front->Next;
			}
			front->Next = NULL;
			q = NULL;
		}
		InsertFinList(old);						//����ɵĽ��̲��뵽��ɶ�����;
	}
	else
		q->State = 'R';						//����һ��ʱ��Ƭ���޸�״̬Ϊ����;
	printf("\n%d ��ʱ��Ƭ��,\n",TheTime);
	if(Rpcb!=NULL)
	{
		printf("\n������Ľ���״̬��\n");
		displayPCB(Rpcb);
		printf("\n��ɱ�Ľ���״̬��\n",TheTime);
		displayPCB(Fpcb);
		SJF();
	}
	else
	{
		printf("\n�����Ľ��̶���������.\n\n");    //��ֹ����;
		printf("\n��ɱ�Ľ���״̬(����������������Ľ���)��\n",TheTime);
		displayPCB(Fpcb);
		return;
	}
}
void RR()			//��ת����(ÿ1��ʱ��Ƭ�ж�1��)
{
	PCB* q = Rpcb;						//Rpcb��ʼ��ΪRpcb
	PCB* front = Rpcb;
	PCB* old;
	q->State = 'N';    					//��������̬���޸�״̬��
	TheTime+=dxTime;
	q->CPUTime+=dxTime;
	q->AllTime-=dxTime;
	q->State = 'R';						//����һ��ʱ��Ƭ���޸�״̬Ϊ����;
	//q->Priority = (q->Priority) - 3;
	if(q->AllTime == 0)    				//�ý����������,�Ӿ���������ɾ��;
		//if(q->AllTime<=0) 				//����ʱ��Ƭ�Ƚϴ�ʱ
	{
		q->State = 'F';					//����������ɣ��޸�״̬Ϊ'F';
		old = q;    					//ȡ��q;
		if(q==Rpcb)						//�����ͷָ��;
			Rpcb = q->Next;
		else if(q->Next != NULL)    			//������м�ָ��;
		{
			while(front->Next != q)
			{
				front = front->Next;
			}
			front->Next = q->Next;
			q = NULL;
		}
		else              					//�����βָ��;
		{
			while(front->Next != q)
			{
				front = front->Next;
			}
			front->Next = NULL;
			q = NULL;
		}
		InsertFinList(old);						//����ɵĽ��̲��뵽��ɶ�����;
	}
	else										//��δ��ɵĲ��뵽��������ĩβ; 
		{
			PCB* tp=Rpcb;
			while(tp->Next!=NULL)
				tp=tp->Next;
			Rpcb = Rpcb->Next;
			tp->Next=q;
			q->Next=NULL; 
		} 
	printf("\n%d ��ʱ��Ƭ��,\n",TheTime);
	if(Rpcb!=NULL)
	{
		printf("\n������Ľ���״̬��\n");
		displayPCB(Rpcb);
		printf("\n��ɱ�Ľ���״̬��\n",TheTime);
		displayPCB(Fpcb);
		SJF();
	}
	else
	{
		printf("\n�����Ľ��̶���������.\n\n");    //��ֹ����;
		printf("\n��ɱ�Ľ���״̬(����������������Ľ���)��\n",TheTime);
		displayPCB(Fpcb);
		return;
	}
}



