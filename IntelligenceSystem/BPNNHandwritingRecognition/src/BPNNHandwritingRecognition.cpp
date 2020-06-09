/*
Description:智能系统理论与应用实验
			题目：基于BP神经网络的手写体识别
			1、提取数据（使用MNIST数据集）
			2、特征提取
			3、训练BP神经网络
				1）权值和神经元偏置随机赋值
				2）前向求出隐含层和输出层的输出
				3）求出输出层与预期输出的误差
				4）反向传播误差，求出隐含层误差
				5）调整权值和神经元偏置
				6）结束（文件读取完毕） 
			4、数据测试，输出识别率 
Date:		2020/05/25
Version:	Dev-C++ 5.11
*/

#include <iostream>
#include <fstream>
#include <vector>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <cmath>
#include <time.h>

using namespace std;

/*全局变量*/ 
const int first = 784;			//输入层到隐藏层，输入层是784维输入向量（数据集中照片是28*28） 
const int second = 100;			//隐含层节点数取100 
const int third = 10;			//输出层是一个10维的向量，每一位分别对应数字0~9 
const double alpha = 0.35;		//神经网络的学习率 
int input[first];				//784维输入向量					
int target[third];				//10维输出向量					
double weight1[first][second];	//输入层到隐含层权重		
double weight2[second][third];	//隐含层到输出层权重		
double output1[second];			//隐含层输出	
double output2[third];			//输出层输出，即目标输出		
double delta1[second];			//隐含层误差 
double delta2[third];			//输出层与预期输出的误差 
double b1[second];				//隐含层神经元偏置，偏置的存在是为了更好的拟合数据	
double b2[third];				//输出层神经元偏置，偏置的存在是为了更好的拟合数据		
double test_num = 0.0;			//测试次数 
double test_success_count = 0.0;//测试成功次数 

/*激活函数*/ 
//sigmoid函数的作用是将输入映射到一个(0,1)的输出范围 
double Sigmoid(double x){
	return 1.0 / (1.0 + exp(-x));
}

/*初始化权重矩阵和神经元偏置*/ 
void Initialize(){
	srand((int)time(0) + rand());//算法的随机种子数，有这个数以后才可以产生随机数 ，只调用rand，每次出来的东西是一样的 
	for (int i = 0; i < first; i++){
		for (int j = 0; j < second; j++){
			weight1[i][j] = rand()%1000 * 0.001 - 0.5;//输入层到隐含层权值 
		}
	}	
	for (int j = 0; j < second; j++){
		for (int k = 0; k < third; k++){
			weight2[j][k] = rand()%1000 * 0.001 - 0.5;//隐含层到输出层权值 
		}
	}
	for (int j = 0; j < second; j++){
		b1[j] = rand()%1000 * 0.001 - 0.5;//隐含层神经元偏置 
	}
	for (int k = 0; k < third; k++){
		b2[k] = rand()%1000 * 0.001 - 0.5;//输出层神经元偏置 
	}
}
/*隐含层输出*/ 
void HiddenLayerOutput(){
	for (int j = 0; j < second; j++){
		double sigma = 0;
		for (int i = 0; i < first; i++){
			sigma += input[i] * weight1[i][j]; //输入和权值乘积的累加和 
		}
		double x = sigma + b1[j];//加上神经元偏置 
		output1[j] = Sigmoid(x);//激活函数激活，得到隐含层输出 
	}
}

/*输出层输出*/ 
void OutputLayerOutput(){
	for (int k = 0; k < third; k++){
		double sigma = 0;
		for (int j = 0; j < second; j++){
			sigma += output1[j] * weight2[j][k];//隐含层输出和权值乘积的累加和 
		}
		double x = sigma + b2[k];//加上神经元偏置 
		output2[k] = Sigmoid(x);//激活函数激活，得到输出层输出 
	}
}

/*计算输出层与预期输出的误差*/ 
void OutputTargetError(){
	for (int k = 0; k < third; k++){
		delta2[k] = (output2[k]) * (1.0 - output2[k]) * (output2[k] - target[k]);
	}
}

/*反向传播误差，求出隐含层误差*/ 
void HiddenLayerError(){
	for (int j = 0; j < second; j++){
		double sigma = 0;
		for (int k = 0; k < third; k++){
			sigma += weight2[j][k] * delta2[k];
		}
		delta1[j] = (output1[j]) * (1.0 - output1[j]) * sigma;
	}
}

/*调整隐含层到输出层的权值和神经元偏置*/
void FeedbackThirdLayer(){
	for (int k = 0; k < third; k++){
		b2[k] = b2[k] - alpha * delta2[k];//调整神经元偏置 
		for (int j = 0; j < second; j++){
			weight2[j][k] = weight2[j][k] - alpha * output1[j] * delta2[k];//调整权值 
		}
	}
}

/*调整输入层到隐含层的权值和神经元偏置*/ 
void FeedbackSecondLayer(){
	for (int j = 0; j < second; j++){
		b1[j] = b1[j] - alpha * delta1[j];//调整神经元偏置 
		for (int i = 0; i < first; i++){
			weight1[i][j] = weight1[i][j] - alpha * input[i] * delta1[j];//调整权值 
		}
	}
}

/*训练*/ 
void Training(){
	FILE *image_train;
	FILE *image_label;
	image_train = fopen("../data/train-images.idx3-ubyte", "rb");
	image_label = fopen("../data/train-labels.idx1-ubyte", "rb");
	if (image_train == NULL || image_label == NULL){
		cout << "Can't open the file!" << endl;
		exit(0);
	}

	unsigned char image_buf[784];
	unsigned char label_buf[10];
	
	int useless[1000];//存放测试文件中开头无用的数据 
	fread(useless, 1, 16, image_train);//跳过开头16字节 
	fread(useless, 1, 8, image_label);//跳过开头8字节 

	int count = 0;//计算图片数量 
	cout << "Start training..." << endl;
	//循环60000次 
	while (!feof(image_train) && !feof(image_label)){
		memset(image_buf, 0, 784);
		memset(label_buf, 0, 10);
		fread(image_buf, 1, 784, image_train);//每784个字节为一张图片 
		fread(label_buf, 1, 1, image_label);//读取对应数字标签 

		//处理成一个28 * 28 的0-1矩阵，其中1代表数字的笔画着色部分，0则代表空白
		for (int i = 0; i < 784; i++){
			if ((unsigned int)image_buf[i] < 128){
				input[i] = 0;
			}
			else{
				input[i] = 1;
			}
		}

		//初始化目标输出 
		int target_value = (unsigned int)label_buf[0];
		for (int k = 0; k < third; k++){
			target[k] = 0;
		}
		target[target_value] = 1;

		//训练
		HiddenLayerOutput();
		OutputLayerOutput();
		OutputTargetError();
		HiddenLayerError();
		FeedbackSecondLayer();
		FeedbackThirdLayer();

		count ++;
		//每1000张显示一次 
		if (count % 1000 == 0){
			cout << "Training number: " << count << endl;
		}
	}
	cout << endl;
}

/*测试*/
void Testing(){
	FILE *image_test;
	FILE *image_test_label;
	image_test = fopen("../data/t10k-images.idx3-ubyte", "rb");
	image_test_label = fopen("../data/t10k-labels.idx1-ubyte", "rb");
	if (image_test == NULL || image_test_label == NULL){
		cout << "can't open the file!" << endl;
		exit(0);
	}

	unsigned char image_buf[784];
	unsigned char label_buf[10];
	
	int useless[1000];//存放测试文件中开头无用的数据 
	fread(useless, 1, 16, image_test);//跳过开头16字节 
	fread(useless, 1, 8, image_test_label);//跳过开头8字节 

	while (!feof(image_test) && !feof(image_test_label)){
		memset(image_buf, 0, 784);
		memset(label_buf, 0, 10);
		fread(image_buf, 1, 784, image_test);//每784个字节为一张图片 
		fread(label_buf, 1, 1, image_test_label);//读取对应数字标签 

		//处理成一个28 * 28 的0-1矩阵，其中1代表数字的笔画着色部分，0则代表空白
		for (int i = 0; i < 784; i++){
			if ((unsigned int)image_buf[i] < 128){
				input[i] = 0;
			}
			else{
				input[i] = 1;
			}
		}

		//初始化目标输出 
		for (int k = 0; k < third; k++){
			target[k] = 0;
		}
		int target_value = (unsigned int)label_buf[0];
		target[target_value] = 1;
		
		//得到输出 
		HiddenLayerOutput();
		OutputLayerOutput();

		double max_value = -99999;
		int max_index = 0;
		for (int k = 0; k < third; k++){
			if (output2[k] > max_value){
				max_value = output2[k];
				max_index = k;
			}
		}

		//系统输出和预期输出对比 
		if (target[max_index] == 1){
			test_success_count ++;
		}
		
		test_num ++;

		if ((int)test_num % 1000 == 0){
			cout << "Testing number: " << test_num << "  Success number: " << test_success_count << endl;
		}
	}
	cout << endl;
	cout << "The success rate: " << test_success_count / test_num << endl;
}
/*主函数*/
int main(){
	cout<<"******************************************************************"<<endl; 
	cout<<"*                     智能系统理论与应用实验                     *"<<endl; 
	cout<<"*                   基于BP神经网络的手写体识别                   *"<<endl; 
	cout<<"******************************************************************"<<endl; 
	Initialize();
	Training();
	Testing();
	system("pause");
}
