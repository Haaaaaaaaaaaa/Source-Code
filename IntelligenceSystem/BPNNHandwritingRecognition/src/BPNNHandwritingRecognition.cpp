/*
Description:����ϵͳ������Ӧ��ʵ��
			��Ŀ������BP���������д��ʶ��
			1����ȡ���ݣ�ʹ��MNIST���ݼ���
			2��������ȡ
			3��ѵ��BP������
				1��Ȩֵ����Ԫƫ�������ֵ
				2��ǰ���������������������
				3������������Ԥ����������
				4�����򴫲���������������
				5������Ȩֵ����Ԫƫ��
				6���������ļ���ȡ��ϣ� 
			4�����ݲ��ԣ����ʶ���� 
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

/*ȫ�ֱ���*/ 
const int first = 784;			//����㵽���ز㣬�������784ά�������������ݼ�����Ƭ��28*28�� 
const int second = 100;			//������ڵ���ȡ100 
const int third = 10;			//�������һ��10ά��������ÿһλ�ֱ��Ӧ����0~9 
const double alpha = 0.35;		//�������ѧϰ�� 
int input[first];				//784ά��������					
int target[third];				//10ά�������					
double weight1[first][second];	//����㵽������Ȩ��		
double weight2[second][third];	//�����㵽�����Ȩ��		
double output1[second];			//���������	
double output2[third];			//������������Ŀ�����		
double delta1[second];			//��������� 
double delta2[third];			//�������Ԥ���������� 
double b1[second];				//��������Ԫƫ�ã�ƫ�õĴ�����Ϊ�˸��õ��������	
double b2[third];				//�������Ԫƫ�ã�ƫ�õĴ�����Ϊ�˸��õ��������		
double test_num = 0.0;			//���Դ��� 
double test_success_count = 0.0;//���Գɹ����� 

/*�����*/ 
//sigmoid�����������ǽ�����ӳ�䵽һ��(0,1)�������Χ 
double Sigmoid(double x){
	return 1.0 / (1.0 + exp(-x));
}

/*��ʼ��Ȩ�ؾ������Ԫƫ��*/ 
void Initialize(){
	srand((int)time(0) + rand());//�㷨���������������������Ժ�ſ��Բ�������� ��ֻ����rand��ÿ�γ����Ķ�����һ���� 
	for (int i = 0; i < first; i++){
		for (int j = 0; j < second; j++){
			weight1[i][j] = rand()%1000 * 0.001 - 0.5;//����㵽������Ȩֵ 
		}
	}	
	for (int j = 0; j < second; j++){
		for (int k = 0; k < third; k++){
			weight2[j][k] = rand()%1000 * 0.001 - 0.5;//�����㵽�����Ȩֵ 
		}
	}
	for (int j = 0; j < second; j++){
		b1[j] = rand()%1000 * 0.001 - 0.5;//��������Ԫƫ�� 
	}
	for (int k = 0; k < third; k++){
		b2[k] = rand()%1000 * 0.001 - 0.5;//�������Ԫƫ�� 
	}
}
/*���������*/ 
void HiddenLayerOutput(){
	for (int j = 0; j < second; j++){
		double sigma = 0;
		for (int i = 0; i < first; i++){
			sigma += input[i] * weight1[i][j]; //�����Ȩֵ�˻����ۼӺ� 
		}
		double x = sigma + b1[j];//������Ԫƫ�� 
		output1[j] = Sigmoid(x);//���������õ���������� 
	}
}

/*��������*/ 
void OutputLayerOutput(){
	for (int k = 0; k < third; k++){
		double sigma = 0;
		for (int j = 0; j < second; j++){
			sigma += output1[j] * weight2[j][k];//�����������Ȩֵ�˻����ۼӺ� 
		}
		double x = sigma + b2[k];//������Ԫƫ�� 
		output2[k] = Sigmoid(x);//���������õ��������� 
	}
}

/*�����������Ԥ����������*/ 
void OutputTargetError(){
	for (int k = 0; k < third; k++){
		delta2[k] = (output2[k]) * (1.0 - output2[k]) * (output2[k] - target[k]);
	}
}

/*���򴫲���������������*/ 
void HiddenLayerError(){
	for (int j = 0; j < second; j++){
		double sigma = 0;
		for (int k = 0; k < third; k++){
			sigma += weight2[j][k] * delta2[k];
		}
		delta1[j] = (output1[j]) * (1.0 - output1[j]) * sigma;
	}
}

/*���������㵽������Ȩֵ����Ԫƫ��*/
void FeedbackThirdLayer(){
	for (int k = 0; k < third; k++){
		b2[k] = b2[k] - alpha * delta2[k];//������Ԫƫ�� 
		for (int j = 0; j < second; j++){
			weight2[j][k] = weight2[j][k] - alpha * output1[j] * delta2[k];//����Ȩֵ 
		}
	}
}

/*��������㵽�������Ȩֵ����Ԫƫ��*/ 
void FeedbackSecondLayer(){
	for (int j = 0; j < second; j++){
		b1[j] = b1[j] - alpha * delta1[j];//������Ԫƫ�� 
		for (int i = 0; i < first; i++){
			weight1[i][j] = weight1[i][j] - alpha * input[i] * delta1[j];//����Ȩֵ 
		}
	}
}

/*ѵ��*/ 
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
	
	int useless[1000];//��Ų����ļ��п�ͷ���õ����� 
	fread(useless, 1, 16, image_train);//������ͷ16�ֽ� 
	fread(useless, 1, 8, image_label);//������ͷ8�ֽ� 

	int count = 0;//����ͼƬ���� 
	cout << "Start training..." << endl;
	//ѭ��60000�� 
	while (!feof(image_train) && !feof(image_label)){
		memset(image_buf, 0, 784);
		memset(label_buf, 0, 10);
		fread(image_buf, 1, 784, image_train);//ÿ784���ֽ�Ϊһ��ͼƬ 
		fread(label_buf, 1, 1, image_label);//��ȡ��Ӧ���ֱ�ǩ 

		//�����һ��28 * 28 ��0-1��������1�������ֵıʻ���ɫ���֣�0�����հ�
		for (int i = 0; i < 784; i++){
			if ((unsigned int)image_buf[i] < 128){
				input[i] = 0;
			}
			else{
				input[i] = 1;
			}
		}

		//��ʼ��Ŀ����� 
		int target_value = (unsigned int)label_buf[0];
		for (int k = 0; k < third; k++){
			target[k] = 0;
		}
		target[target_value] = 1;

		//ѵ��
		HiddenLayerOutput();
		OutputLayerOutput();
		OutputTargetError();
		HiddenLayerError();
		FeedbackSecondLayer();
		FeedbackThirdLayer();

		count ++;
		//ÿ1000����ʾһ�� 
		if (count % 1000 == 0){
			cout << "Training number: " << count << endl;
		}
	}
	cout << endl;
}

/*����*/
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
	
	int useless[1000];//��Ų����ļ��п�ͷ���õ����� 
	fread(useless, 1, 16, image_test);//������ͷ16�ֽ� 
	fread(useless, 1, 8, image_test_label);//������ͷ8�ֽ� 

	while (!feof(image_test) && !feof(image_test_label)){
		memset(image_buf, 0, 784);
		memset(label_buf, 0, 10);
		fread(image_buf, 1, 784, image_test);//ÿ784���ֽ�Ϊһ��ͼƬ 
		fread(label_buf, 1, 1, image_test_label);//��ȡ��Ӧ���ֱ�ǩ 

		//�����һ��28 * 28 ��0-1��������1�������ֵıʻ���ɫ���֣�0�����հ�
		for (int i = 0; i < 784; i++){
			if ((unsigned int)image_buf[i] < 128){
				input[i] = 0;
			}
			else{
				input[i] = 1;
			}
		}

		//��ʼ��Ŀ����� 
		for (int k = 0; k < third; k++){
			target[k] = 0;
		}
		int target_value = (unsigned int)label_buf[0];
		target[target_value] = 1;
		
		//�õ���� 
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

		//ϵͳ�����Ԥ������Ա� 
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
/*������*/
int main(){
	cout<<"******************************************************************"<<endl; 
	cout<<"*                     ����ϵͳ������Ӧ��ʵ��                     *"<<endl; 
	cout<<"*                   ����BP���������д��ʶ��                   *"<<endl; 
	cout<<"******************************************************************"<<endl; 
	Initialize();
	Training();
	Testing();
	system("pause");
}
