/*
Description:��ά�任
			1��ʵ�ֶ���άģ�͵Ļ��ơ����磺�����塢���塢���߸�Ϊ���ӵ�ģ�͡�
			2��ʵ�ֶ���άģ�ͻ������α任��ƽ�ơ���ת�����š��Գơ����У���
			3��ʵ�ֶ���άģ�͵ĸ��ϱ任�����磺�������һ��ı任���������һ����ı任����
Date:		2020/05/10
Version:	Visual Studio 2010
*/

#include "3DTransform.h"
#include <gl/glut.h>
#include <iostream>

using namespace std;

/*�ṹ�����ݽṹ*/
struct point //��ά��Ľṹ��
{
	float x; 
	float y;
	float z;
};

class color //��ɫģ��
{ 
public: GLfloat r, g, b; 
};

color colors[] = {//�ĸ������ɫ����
	{ 1.0,1.0,0.0}, { 0.0, 1.0, 0.0 }, { 0.0, 0.0, 1.0 }, { 0.0, 1.0, 1.0 }
};

int edge = 4;//���ڼ�¼����εı���
point *points;//��ά����Ϣ
point *transform_points;//�仯����ά����Ϣ
point init_points[] = {//��ʼ����Ϣ
	{ 1.0, 0.0, 0.0 }, { 0.0, 1.0, 0.0 }, { 0.0, 0.0, 1.0 },
	{ 0.0, 0.0, 0.0 }, { 0.0, 1.0, 0.0 }, { 1.0, 0.0, 0.0 },
	{ 0.0, 0.0, 0.0 }, { 0.0, 0.0, 1.0 }, { 0.0, 1.0, 0.0 },
	{ 0.0, 0.0, 0.0 }, { 0.0, 0.0, 1.0 }, { 1.0, 0.0, 0.0 }
};
/*��������*/
void Display(void);
void Reshape(int w, int h);
void TransformDisplay(void);
void Init(void);
void GlutInit(int argc, char* argv);

/*main����*/
void main(int argc, char* argv)
{
	points = new point[12];//��ʼ��
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
	cout<<"*                                ��ά�任                               *"<<endl;
	cout<<"*************************************************************************"<<endl;
	cout<<"*                                ��ӭʹ��                              *"<<endl;
	cout<<"*************************************************************************"<<endl;
	cout<<"��ѡ����Ҫ�ĵĲ������������֣�"<<endl;
	cout << "0��ģ�ͻ���" << endl << "1��ƽ�Ʊ任" << endl << "2����ת�任" << endl << "3�����ű任" << endl << "4���ԳƱ任" << endl << "5�����б任" << endl;
	cout << "����������ѡ��";
	cin >> choice;
	string axis;//�����ƽ��
	switch (choice)
	{
		default:
			cout << "��������" << endl;
			return;
			break;
		case 0: break;
		case 1:
			float Tx, Ty, Tz;//ƽ��ʸ��
			cout << "��������x����ƽ������";
			cin >> Tx;
			cout << "��������y����ƽ������";
			cin >> Ty;
			cout << "��������z����ƽ������";
			cin >> Tz;
			for (int i = 0; i < edge; i++)
				for (int j = i * 3; j < i * 3 + 3; j++)
					Translation(transform_points[j].x, transform_points[j].y, transform_points[j].z, Tx, Ty, Tz);
			break;
		case 2:
			float angle;
			cout << "������Χ����һ���������ת�任(����x��y��z)��";
			cin >> axis;
			cout << "��������ת�Ƕȣ�";
			cin >> angle;
			for (int i = 0; i < edge; i++)
				for (int j = i * 3; j < i * 3 + 3; j++)
					Rotation(transform_points[j].x, transform_points[j].y, transform_points[j].z, angle, axis);
			break;
		case 3:
			float Sx, Sy, Sz;//���ű���ϵ��
			cout << "������x�������ű���ϵ����";
			cin >> Sx;
			cout << "������y�������ű���ϵ����";
			cin >> Sy;
			cout << "������z�������ű���ϵ����";
			cin >> Sz;
			for (int i = 0; i < edge; i++)
				for (int j = i * 3; j < i * 3 + 3; j++)
					Zoom(transform_points[j].x, transform_points[j].y, transform_points[j].z, Sx, Sy, Sz);
			break;
		case 4:
			cout << "1��ԭ��Գ�" << endl << "2��X��Գ�" << endl << "3��Y��Գ�" << endl << "4��Z��Գ�" << endl << "5��XOYƽ��Գ�" << endl << "6��XOZƽ��Գ�" <<endl<<"7��YOZƽ��Գ�"<< endl;
			cout << "��ѡ��Գ����ͣ�";
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
			cout << "������x����y��ϵ��d��";
			cin >> dy;
			cout << "������x����z��ϵ��g��";
			cin >> gz;
			cout << "������y����x��ϵ��b��";
			cin >> bx;
			cout << "������y����z��ϵ��h��";
			cin >> hz;
			cout << "������z����x��ϵ��c��";
			cin >> cx;
			cout << "������z����y��ϵ��f��";
			cin >> fy;
			for (int i = 0; i < edge; i++)
				for (int j = i * 3; j < i * 3 + 3; j++)
					Shear(transform_points[j].x, transform_points[j].y, transform_points[j].z, dy, gz, bx, hz, cx, fy);
			break;
	}
	GlutInit(argc, argv);
}

/*�ۿ��ӽǣ��Լ�������������*/
void Display(void)
{
	glClear(GL_COLOR_BUFFER_BIT);//���ô�����ɫ
	glColor3f(1.0, 1.0, 1.0); 
	glLoadIdentity();//�ָ���ʼ����ϵ
	gluLookAt(5.0, 5.0, 5.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);//�ڣ�5��5��5���㿴��0��0��0�����ۿ��ӽ�
	glutSolidOctahedron();
	glutSwapBuffers();
}

void Reshape(int w, int h)
{
	glViewport(0, 0, (GLsizei)w, (GLsizei)h);//չʾ�ܿ����ķ�Χ
	glMatrixMode(GL_PROJECTION);//Ҫ��ͶӰ��ؽ��в���
	glLoadIdentity();//�ָ���ʼ����ϵ
	gluPerspective(60.0, (GLfloat)w / (GLfloat)h, 1.0, 20.0);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
	gluLookAt(5.0, 5.0, 5.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);//�ڣ�5��5��5���㿴��0��0��0�����ۿ��ӽ�
}

void TransformDisplay(void)
{
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	glRotatef(30, 0.0f, 1.0f, 0.0f);
	glBegin(GL_LINES);
	//������Ľ���
	glColor3f(1.0, 0.0, 0.0);//��ɫΪX�ᣬ�����ң�x����
	glVertex3f(-5, 0, 0);
	glVertex3f(5, 0, 0);

	glColor3f(0.0, 1.0, 0.0);//��ɫΪY�ᣬ���µ��ϣ�y����
	glVertex3f(0, -5, 0);
	glVertex3f(0, 5, 0);

	glColor3f(0.0, 0.0, 1.0);//��ɫΪZ�ᣬ��Զ������z����
	glVertex3f(0, 0, -5);
	glVertex3f(0, 0, 5);
	glEnd();
	//���ĸ���������ɫ
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
	glutInit(&argc, &argv);//��ʼ��GLUT��
	glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);//�������ڵ�ʱ��ָ������ɫģʽ�ͻ���������
	glutInitWindowPosition(400,200);//ͼ�ν����ڴ��ڵ�λ��
	glutInitWindowSize(600, 600);//���ڴ�С
	glutCreateWindow("��ά���α任");//������������
	Init();//��ʼ��
	glutDisplayFunc(Display);//������Զ�����Display�����ػ洰��
	glutReshapeFunc(Reshape);//�����ڳߴ�ı�ʱ��ͼ�α����������仯 
	glutDisplayFunc(TransformDisplay);//������Զ�����TransformDisplay�����ػ洰��
	glutMainLoop();
}
