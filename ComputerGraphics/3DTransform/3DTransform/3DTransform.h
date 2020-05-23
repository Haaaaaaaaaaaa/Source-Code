#include <iostream>
#include <math.h>
#include <string>

#define PI 3.14

using namespace std;

float** tMultiMatrix(float **a, float **b)
{
	float **c = new float*[1];
	c[0] = new float[4];
	for (int j = 0; j < 4; j++)
	{
		for (int k = 0; k < 4; k++)
		{
			c[0][j] += a[0][k] * b[k][j];
		}
		return c;
	}
}
/*平移变换*/
void Translation(float &x, float &y,float &z, float xi, float yi,float zi)
{
	x += xi;
	y += yi;
	z += zi;
}

/*旋转变换*/
void Rotation(float &x, float &y,float &z, int a,string type)
{
	//int b =(int) ((a / 180) * PI);
	float b =(float)((a / 180) * PI);
	if (type == "z"||type=="Z")
	{
		float temp = x*cos(b) - y*sin(b);
		y = x*sin(b) + y*cos(b);
		x = temp;
	}
	if (type == "x"||type=="X")
	{
		float temp = y*cos(b) - z*sin(b);
		z = y*sin((float)a) + z*cos(b);
		y = temp;
	}
	if (type == "y"||type=="Y")
	{
		float temp = z*sin(b) + x*cos(b);
		z = z*cos(b) - x*sin(b);
		x = temp;
	}
}

/*缩放变换*/
void Zoom(float &x, float &y,float &z, float Sx, float Sy,float Sz)
{
	x *= Sx;
	y *= Sy;
	z *= Sz;
}

/*对称变换*/
void Symmetry(float &x, float &y,float &z, string type)
{
	if (type == "x")
	{
		y = -y;
		z = -z;
	}
	if (type == "y")
	{
		x = -x;
		z = -z;
	}
	if (type == "z")
	{
		x = -x;
		y = -y;
	}
	if (type == "O")
	{
		x = -x;
		y = -y;
		z = -z;
		\
	}
	if (type == "xoy")
		z = -z;
	if (type == "yoz")
		x = -x;
	if (type == "xoz")
		y = -y;
}

/*错切变换*/
void Shear(float &x, float &y,float &z,float d,float g,float b,float h,float c,float f)
{
	float temp = x + d*y + g*z;
	float _temp = b*x + y + h*z;
	z = c*x + f*y + z;
	x = temp;
	y = _temp;
}