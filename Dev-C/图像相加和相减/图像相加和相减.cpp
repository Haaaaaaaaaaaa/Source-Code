#include<opencv2>
#include<iostream>
using namespace std;
using namespace cv;
int main(int argc, char** argv)
{
	Mat src1, src2, dst;
	src1 = imread("../../../lena.jpg");
	src2 = imread("../../../rabbit.jpg");
	if (!src1.data) {
		cout << "could not load src1..." << endl;
		return -1;
	}
	if (!src2.data) {
		cout << "could not load src2..." << endl;
		return -1;
	}
	double alpha = 0.5;//权重值
	if (src1.rows == src2.rows&&src2.cols == src2.cols&&src1.type() == src2.type()) {
		//图像混合addWeighted权重方式
		addWeighted(src1, alpha, src2, 1.0 - alpha, 0.0, dst);
		//add(src1, src2, dst);//像素相加
		//subtract(src1, src2, dst);//像素相减
		//multiply(src1, src2, dst, 1.0);//像素相乘
		//divide(src1, src2, dst);//像素相除
		imshow("src1", src1);
		imshow("src2", src2);
		imshow("混合后的图像", dst);
	}
	else {
		printf("could not blend imgaes,the size of images is not same...\n");
		return -1;
	}
	waitKey(0);
	return 0;
}
