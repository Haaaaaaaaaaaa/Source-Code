/*
Description:三维变换
			1、实现对三维模型的绘制。比如：立方体、球体、或者更为复杂的模型。
			2、实现对三维模型基本几何变换（平移、旋转、缩放、对称、错切）。
			3、实现对三维模型的复合变换（比如：相对于任一点的变换、相对于任一方向的变换）。
Date:		2020/05/10
Version:	Visual Studio 2010
*/

#include "3DTransform.h"
#include <gl/glut.h>
#include <iostream>

using namespace std;

/*结构体数据结构*/
struct point //三维点的结构体
{
	float x; 
	float y;
	float z;
};

class color //颜色模型
{ 
public: GLfloat r, g, b; 
};

color colors[] = {//四个面的颜色设置
	{ 1.0,1.0,0.0}, { 0.0, 1.0, 0.0 }, { 0.0, 0.0, 1.0 }, { 0.0, 1.0, 1.0 }
};

int edge = 4;//用于记录多边形的边数
point *points;//三维点信息
point *transform_points;//变化后三维点信息
point init_points[] = {//初始点信息
	{ 1.0, 0.0, 0.0 }, { 0.0, 1.0, 0.0 }, { 0.0, 0.0, 1.0 },
	{ 0.0, 0.0, 0.0 }, { 0.0, 1.0, 0.0 }, { 1.0, 0.0, 0.0 },
	{ 0.0, 0.0, 0.0 }, { 0.0, 0.0, 1.0 }, { 0.0, 1.0, 0.0 },
	{ 0.0, 0.0, 0.0 }, { 0.0, 0.0, 1.0 }, { 1.0, 0.0, 0.0 }
};
/*函数声明*/
void Display(void);
void Reshape(int w, int h);
void TransformDisplay(void);
void Init(void);
void GlutInit(int argc, char* argv);

/*main函数*/
void main(int argc, char* argv)
{
	points = new point[12];//初始化
	transform_points = new point[12];

	for (int i = 0; i < 4; i++) {
		for (int k = i * 3; k < i * 3 + 3; k++) {
			transform_points[k].x = init_points[k].x;
			transform_points[k].y = init_points[k].y;
			transform_points[k].z = init_points[k].z;
		}
	}
	points = init_points;
	int choice=0;
	cout<<"*************************************************************************"<<endl;
	cout<<"*                                三维变换                               *"<<endl;
	cout<<"*************************************************************************"<<endl;
	cout<<"*                                欢迎使用                              *"<<endl;
	cout<<"*************************************************************************"<<endl;
	cout<<"请选择需要的的操作（输入数字）"<<endl;
	cout << "0、模型绘制" << endl << "1、平移变换" << endl << "2、旋转变换" << endl << "3、缩放变换" << endl << "4、对称变换" << endl << "5、错切变换" << endl;
	cout << "请输入您的选择：";
	cin >> choice;
	string axis;//轴或者平面
	switch (choice)
	{
		default:
			cout << "输入有误！" << endl;
			return;
			break;
		case 0: break;
		case 1:
			float Tx, Ty, Tz;//平移矢量
			cout << "请输入沿x方向平移量：";
			cin >> Tx;
			cout << "请输入沿y方向平移量：";
			cin >> Ty;
			cout << "请输入沿z方向平移量：";
			cin >> Tz;
			for (int i = 0; i < edge; i++)
				for (int j = i * 3; j < i * 3 + 3; j++)
					Translation(transform_points[j].x, transform_points[j].y, transform_points[j].z, Tx, Ty, Tz);
			break;
		case 2:
			float angle;
			cout << "请输入围绕哪一个轴进行旋转变换(输入x或y或z)：";
			cin >> axis;
			cout << "请输入旋转角度：";
			cin >> angle;
			for (int i = 0; i < edge; i++)
				for (int j = i * 3; j < i * 3 + 3; j++)
					Rotation(transform_points[j].x, transform_points[j].y, transform_points[j].z, angle, axis);
			break;
		case 3:
			float Sx, Sy, Sz;//缩放比例系数
			cout << "请输入x方向缩放比例系数：";
			cin >> Sx;
			cout << "请输入y方向缩放比例系数：";
			cin >> Sy;
			cout << "请输入z方向缩放比例系数：";
			cin >> Sz;
			for (int i = 0; i < edge; i++)
				for (int j = i * 3; j < i * 3 + 3; j++)
					Zoom(transform_points[j].x, transform_points[j].y, transform_points[j].z, Sx, Sy, Sz);
			break;
		case 4:
			cout << "1、原点对称" << endl << "2、X轴对称" << endl << "3、Y轴对称" << endl << "4、Z轴对称" << endl << "5、XOY平面对称" << endl << "6、XOZ平面对称" <<endl<<"7、YOZ平面对称"<< endl;
			cout << "请选择对称类型：";
			int T;
			cin >> T;
			if (T == 1) axis = "O";
			if (T == 2) axis = "x";
			if (T == 3) axis = "y";
			if (T == 4) axis = "z";
			if (T == 5) axis = "xoy";
			if (T == 6) axis = "xoz";
			if (T == 7) axis = "yoz";
			for (int i = 0; i < edge; i++)
				for (int j = i * 3; j < i * 3 + 3; j++)
					Symmetry(transform_points[j].x, transform_points[j].y, transform_points[j].z, axis);
			break;
		case 5:
			float dy, gz, bx, hz, cx, fy;
			cout << "请输入x方向y的系数d：";
			cin >> dy;
			cout << "请输入x方向z的系数g：";
			cin >> gz;
			cout << "请输入y方向x的系数b：";
			cin >> bx;
			cout << "请输入y方向z的系数h：";
			cin >> hz;
			cout << "请输入z方向x的系数c：";
			cin >> cx;
			cout << "请输入z方向y的系数f：";
			cin >> fy;
			for (int i = 0; i < edge; i++)
				for (int j = i * 3; j < i * 3 + 3; j++)
					Shear(transform_points[j].x, transform_points[j].y, transform_points[j].z, dy, gz, bx, hz, cx, fy);
			break;
	}
	GlutInit(argc, argv);
}

/*观看视角，以及基本窗口设置*/
void Display(void)
{
	glClear(GL_COLOR_BUFFER_BIT);//设置窗口颜色
	glColor3f(1.0, 1.0, 1.0); 
	glLoadIdentity();//恢复初始坐标系
	gluLookAt(5.0, 5.0, 5.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);//在（5，5，5）点看向（0，0，0），观看视角
	glutSolidOctahedron();
	glutSwapBuffers();
}

void Reshape(int w, int h)
{
	glViewport(0, 0, (GLsizei)w, (GLsizei)h);//展示能看到的范围
	glMatrixMode(GL_PROJECTION);//要对投影相关进行操作
	glLoadIdentity();//恢复初始坐标系
	gluPerspective(60.0, (GLfloat)w / (GLfloat)h, 1.0, 20.0);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
	gluLookAt(5.0, 5.0, 5.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);//在（5，5，5）点看向（0，0，0），观看视角
}

void TransformDisplay(void)
{
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	glRotatef(30, 0.0f, 1.0f, 0.0f);
	glBegin(GL_LINES);
	//坐标轴的建立
	glColor3f(1.0, 0.0, 0.0);//红色为X轴，从左到右，x递增
	glVertex3f(-5, 0, 0);
	glVertex3f(5, 0, 0);

	glColor3f(0.0, 1.0, 0.0);//绿色为Y轴，从下到上，y递增
	glVertex3f(0, -5, 0);
	glVertex3f(0, 5, 0);

	glColor3f(0.0, 0.0, 1.0);//蓝色为Z轴，从远到近，z递增
	glVertex3f(0, 0, -5);
	glVertex3f(0, 0, 5);
	glEnd();
	//给四个面设置颜色
	glBegin(GL_TRIANGLES);
	int j;
	for (int i = 0; i < 4; i++) {
		glColor3f(colors[i].r, colors[i].g, colors[i].b);
		for (j = i * 3; j < i * 3 + 3; j++) {
			glVertex3f(points[j].x, points[j].y, points[j].z);
		}
	}
	int k;
	for (int i = 0; i < 4; i++) {
		glColor3f(colors[i].r, colors[i].g, colors[i].b);
		for (k = i * 3; k < i * 3 + 3; k++) {
			glVertex3f(transform_points[k].x, transform_points[k].y, transform_points[k].z);
		}
	}
	glEnd();
	glFlush();
	glutSwapBuffers();
}

void Init(void)
{
	glClearColor(1.0, 1.0, 1.0, 0.0);

}

void GlutInit(int argc, char* argv)
{
	glutInit(&argc, &argv);//初始化GLUT库
	glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);//创建窗口的时候，指定其颜色模式和缓冲区类型
	glutInitWindowPosition(400,200);//图形界面在窗口的位置
	glutInitWindowSize(600, 600);//窗口大小
	glutCreateWindow("三维几何变换");//窗口名称设置
	Init();//初始化
	glutDisplayFunc(Display);//程序会自动调用Display函数重绘窗口
	glutReshapeFunc(Reshape);//当窗口尺寸改变时，图形比例不发生变化 
	glutDisplayFunc(TransformDisplay);//程序会自动调用TransformDisplay函数重绘窗口
	glutMainLoop();
}
