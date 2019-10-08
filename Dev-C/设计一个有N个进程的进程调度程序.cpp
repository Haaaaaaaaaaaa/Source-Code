#include<stdio.h>
#include<stdlib.h>

typedef struct pcb      //pcb结构体定义;
{
	int ID;				//进程的ID；
	int Priority;		//进程的优先级     //假设每个pcb的优先级不一样;
	int Cometime;		//进程的到达时间
	int AllTime;		//进程还需要的运行时间;
	int CPUTime;		//进程已使用CPU的时间;
	int StartBlock;	    //进程运行StartBlock时间片后进入阻塞;
	int StartTime;      //进程阻塞StartTime时间片后进入就绪; //设置为可变，每阻塞态一个时间片就-1；
	char State;		    //进程的状态，R,N,B,F;
	struct pcb* Next;	//进程队列的下一个进程的指针;
} PCB;

int num = 5;			//默认创建5个进程;
int TheTime = 0;		//定义时间，设起始时间为0，以一个时间片为单位;
int dxTime = 5;			//时间片大小;   //整块被消耗和部分被消耗
PCB* Rpcb = NULL; //就绪队列头指针;
//PCB* Bpcb = NULL; //阻塞队列头指针;
//PCB* Npcb = NULL; //运行指针;
PCB* Fpcb = NULL; //完成队列头指针;

PCB* CreateApcb(int id,int Priority,int ctime,int atime,int cputime,int sblock,int stime,char state,PCB* Next); //创建单个进程；
PCB* CreateReadyPcb();		//创建就绪队列的pcb,创建后自动插入队列;
PCB* GetHighPrio(PCB* head); //查找获得链表中优先级最高的进程，返回指向该进程的指针
void DiaoDuReadyList();  //按动态优先级来调度，每过一个时间片，优先级-3，没考虑阻塞情况：
void FCFS();				//先来先服务算法; 
PCB* GetShortPcb();			//获得最短的进程; 
void SJF();					//段进程优先算法; 
void RR();					//轮转调度算法; 
void InsertList(PCB** head,PCB* pnew);//将一个pcb插入到链表中;
void InsertFinList(PCB* fp)	;//把完成的进程插入到完成对列中
void displayPCB(PCB* p);  //显示进程队列的进程状态;


int main()
{

	Rpcb = CreateReadyPcb();     //创建就绪队列;
	displayPCB(Rpcb);
	//DiaoDuReadyList(Rpcb);		//动态优先级;
	//FCFS();					//先来先服务调度;
	//SJF();					//短进程优先
	RR();						//轮转调度; 
	system("pause");
	return 0;
}

//创建单个进程；
PCB* CreateApcb(int id,int Priority,int ctime,int atime,int cputime,int sblock,int stime,char state,PCB* Next)
{
	PCB* p =  (PCB*)malloc(sizeof(PCB));
	p->ID = id;
	p->Priority = Priority;
	p->Cometime = ctime;
	p->AllTime = atime;
	p->CPUTime = cputime;
	p->StartBlock = sblock;//暂时先不考虑;
	p->StartTime = stime;//
	p->State = state;
	p->Next  = Next;
	return p;
}

PCB* CreateReadyPcb()  	//创建就绪队列的pcb,创建后自动插入队列;
{
	PCB* rPCB = NULL;       //就绪队列的头指针;
	PCB* p1 = NULL;			//两个中间转换指针;
	PCB* p2 = NULL;
	p1 = p2 = (PCB*)malloc(sizeof(PCB));
	//进程1;
	p1 = CreateApcb(1,3,0,15,0,20,10,'R',NULL);	//阻塞情况先不考虑;
	rPCB  = p1;
	//进程2；
	p2 = CreateApcb(2,5,10,55,0,60,10,'R',NULL);
	p1->Next = p2;
	p1 = p2;
	//进程3;
	p2 = CreateApcb(3,1,20,15,0,60,10,'R',NULL);
	p1->Next = p2;
	p1 = p2;
	//进程4;
	p2 = CreateApcb(4,4,30,25,0,60,10,'R',NULL);
	p1->Next = p2;
	p1 = p2;
	//进程5;
	p2 = CreateApcb(5,2,40,10,0,60,10,'R',NULL);
	p1->Next = p2;
	p1 = p2;
	return rPCB;

}

/*查找获得链表中优先级最高的进程，返回指向该进程的指针*/
PCB* GetHighPrio(PCB* head)
{
	PCB* p = head;    			//就绪队列表头的是最先插入队的进程;
	PCB* q = p->Next;

	// while(q != NULL&&(q->Cometime<TheTime))    		//时间顺序问题;
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


void DiaoDuReadyList()   //按动态优先级来调度，每过一个时间片，优先级-3，没考虑阻塞情况：
{
	PCB* q = Rpcb;
	PCB* front = Rpcb;
	PCB* old;
	q = GetHighPrio(Rpcb);    				//寻找当前就绪队列中优先级最高的任务;
	q->State = 'N';    						//进入运行态，修改状态；
	TheTime+=dxTime;
	q->CPUTime+=dxTime;
	q->AllTime-=dxTime;
	q->State = 'R';						//运行一个时间片后，修改状态为就绪;
	q->Priority = (q->Priority) - 3;
	if(q->AllTime == 0)    				//该进程运行完成,从就绪队列中删除;
		//if(q->AllTime<=0) 				//给的时间片比较大时
	{
		q->State = 'F';					//进程运行完成，修改状态为'F';
		old = q;    					//取出q;
		if(q==Rpcb)						//如果是头指针;
			Rpcb = q->Next;
		else if(q->Next != NULL)    			//如果是中间指针;
		{
			while(front->Next != q)
			{
				front = front->Next;
			}
			front->Next = q->Next;
			q = NULL;
		}
		else              					//如果是尾指针;
		{
			while(front->Next != q)
			{
				front = front->Next;
			}
			front->Next = NULL;
			q = NULL;
		}
		InsertFinList(old);						//把完成的进程插入到完成队列中;
	}
	printf("\n%d 个时间片后,\n",TheTime);
	if(Rpcb!=NULL)
	{
		printf("\n就绪表的进程状态：\n");
		displayPCB(Rpcb);
		printf("\n完成表的进程状态：\n",TheTime);
		displayPCB(Fpcb);
		DiaoDuReadyList();
	}
	else
	{
		printf("\n就绪的进程都运行完了.\n\n");    //终止条件;
		printf("\n完成表的进程状态(包含和所有运行完的进程)：\n",TheTime);
		displayPCB(Fpcb);
		return;
	}
}

void InsertList(PCB** head,PCB* pnew)
{
//按顺序从小到大插入;
//	PCB* fp = NULL;   //前一个指向的地址：
//	PCB* cp = *head;   //当前指向的地址;
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
//	//插入到最后面;
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

void InsertFinList(PCB* pnew)  			//让完成的进程加入到完成队列中;
{
	InsertList(&Fpcb,pnew);
}


void displayPCB(PCB* p)   //输出队列的进程状态信息;
{
	if(p!=NULL)
	{
		PCB* fp = p;
		printf("进程ID,优先级, 到达时间, 还需要运行的时间,CPU的时间,阻塞时间1,阻塞时间2,程的状态\n");
		while(fp!=NULL)
		{
			printf("%4d  %4d     %5d      %8d  %10d    %8d    %6d  %6c\n",fp->ID,fp->Priority,fp->Cometime,fp->AllTime,fp->CPUTime,fp->StartBlock,fp->StartTime,fp->State);
			fp = fp->Next;
		}
	}
	else
		printf("队列中没有进程");
}

//其他调度;
void FCFS()						//先来先服务;
{
	PCB* q = Rpcb;
	PCB* front = Rpcb;
	q = Rpcb;    					//寻找当前就绪队列中优先级最高的任务;
	q->State = 'N';    						//进入运行态，修改状态；
	TheTime+=dxTime;
	q->CPUTime+=dxTime;
	q->AllTime-=dxTime;
//	q->Priority = (q->Priority) - 3;
	if(q->AllTime == 0)    				//该进程运行完成,从就绪队列中删除;
		//if(q->AllTime<=0) 			//给的时间片比较大时
	{
		q->State = 'F';					//进程运行完成，修改状态为'F';
		Rpcb=Rpcb->Next;
		InsertFinList(q);				//把完成的进程插入到完成队列中;
	}
	else								//运行一个时间片后，修改状态为就绪;
		q->State='R';
	printf("\n%d 个时间片后,\n",TheTime);
	if(Rpcb!=NULL)
	{
		printf("\n就绪表的进程状态：\n");
		displayPCB(Rpcb);
		printf("\n完成表的进程状态：\n",TheTime);
		displayPCB(Fpcb);
		FCFS();
	}
	else
	{
		printf("\n就绪的进程都运行完了.\n\n");    //终止条件;
		printf("\n完成表的进程状态(包含和所有运行完的进程)：\n",TheTime);
		displayPCB(Fpcb);
		return;
	}

}
PCB* GetShortPcb()		//寻找当前就绪队列中运行时间最短的进程;
{
	PCB* p = Rpcb;    			//就绪队列表头的是最先插入队的进程;
	PCB* q = p->Next;

	// while(q != NULL&&(q->Cometime<TheTime))    		//时间顺序问题;
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

void SJF()			//短进程优先
{
	PCB* q = Rpcb;
	PCB* front = Rpcb;
	PCB* old;
	q = GetShortPcb();	   				//寻找当前就绪队列中还需要运行的时间最短的进程;
	q->State = 'N';    						//进入运行态，修改状态；
	TheTime+=dxTime;
	q->CPUTime+=dxTime;
	q->AllTime-=dxTime;
	//q->Priority = (q->Priority) - 3;
	if(q->AllTime == 0)    				//该进程运行完成,从就绪队列中删除;
		//if(q->AllTime<=0) 				//给的时间片比较大时
	{
		q->State = 'F';					//进程运行完成，修改状态为'F';
		old = q;    					//取出q;
		if(q==Rpcb)						//如果是头指针;
			Rpcb = q->Next;
		else if(q->Next != NULL)    			//如果是中间指针;
		{
			while(front->Next != q)
			{
				front = front->Next;
			}
			front->Next = q->Next;
			q = NULL;
		}
		else              					//如果是尾指针;
		{
			while(front->Next != q)
			{
				front = front->Next;
			}
			front->Next = NULL;
			q = NULL;
		}
		InsertFinList(old);						//把完成的进程插入到完成队列中;
	}
	else
		q->State = 'R';						//运行一个时间片后，修改状态为就绪;
	printf("\n%d 个时间片后,\n",TheTime);
	if(Rpcb!=NULL)
	{
		printf("\n就绪表的进程状态：\n");
		displayPCB(Rpcb);
		printf("\n完成表的进程状态：\n",TheTime);
		displayPCB(Fpcb);
		SJF();
	}
	else
	{
		printf("\n就绪的进程都运行完了.\n\n");    //终止条件;
		printf("\n完成表的进程状态(包含和所有运行完的进程)：\n",TheTime);
		displayPCB(Fpcb);
		return;
	}
}
void RR()			//轮转调度(每1个时间片中断1次)
{
	PCB* q = Rpcb;						//Rpcb初始化为Rpcb
	PCB* front = Rpcb;
	PCB* old;
	q->State = 'N';    					//进入运行态，修改状态；
	TheTime+=dxTime;
	q->CPUTime+=dxTime;
	q->AllTime-=dxTime;
	q->State = 'R';						//运行一个时间片后，修改状态为就绪;
	//q->Priority = (q->Priority) - 3;
	if(q->AllTime == 0)    				//该进程运行完成,从就绪队列中删除;
		//if(q->AllTime<=0) 				//给的时间片比较大时
	{
		q->State = 'F';					//进程运行完成，修改状态为'F';
		old = q;    					//取出q;
		if(q==Rpcb)						//如果是头指针;
			Rpcb = q->Next;
		else if(q->Next != NULL)    			//如果是中间指针;
		{
			while(front->Next != q)
			{
				front = front->Next;
			}
			front->Next = q->Next;
			q = NULL;
		}
		else              					//如果是尾指针;
		{
			while(front->Next != q)
			{
				front = front->Next;
			}
			front->Next = NULL;
			q = NULL;
		}
		InsertFinList(old);						//把完成的进程插入到完成队列中;
	}
	else										//把未完成的插入到就绪队列末尾; 
		{
			PCB* tp=Rpcb;
			while(tp->Next!=NULL)
				tp=tp->Next;
			Rpcb = Rpcb->Next;
			tp->Next=q;
			q->Next=NULL; 
		} 
	printf("\n%d 个时间片后,\n",TheTime);
	if(Rpcb!=NULL)
	{
		printf("\n就绪表的进程状态：\n");
		displayPCB(Rpcb);
		printf("\n完成表的进程状态：\n",TheTime);
		displayPCB(Fpcb);
		SJF();
	}
	else
	{
		printf("\n就绪的进程都运行完了.\n\n");    //终止条件;
		printf("\n完成表的进程状态(包含和所有运行完的进程)：\n",TheTime);
		displayPCB(Fpcb);
		return;
	}
}



