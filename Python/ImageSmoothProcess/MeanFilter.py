'''
Introduce:  智能系统理论与应用第二次作业，作业题1。
            编一个程序，用n×n的均值滤波器实现对图像的平滑处理。
Date:       2020/5/5          
'''

import numpy as np
import cv2

# 均值滤波器
def MeanFilter(F, M,N):
    # F_result为了存放结果
    F_result= np.zeros(F.shape)
    for i in range(F.shape[0]-M+1):
        for j in range(F.shape[1]-N+1):
            F_result[i+1, j+1] = (F[i:i+3, j:j+3] * 1.0/(M*N)).sum()
    # 边框保留不变（最外边的值不变）
    for i in range(F.shape[0]):
        for j in range(F.shape[1]):
            if i==0 or i == F.shape[0]-1 or j==0 or j==F.shape[1]-1:
                F_result[i,j] = F[i,j]
    # 四舍五入,转换为默认整型
    return np.round(F_result).astype(np.int_)

if __name__ == "__main__":
    F = np.array([[2,4,6,2,1,9],[6,8,6,7,7,2],[1,3,6,6,8,8],[3,4,5,6,6,6],[1,4,6,6,2,3],[1,3,6,4,6,6]])
    # OpenCV提供的均值滤波函数
    result=cv2.blur(F,(3,3))
    print("调用OpenCV库函数处理结果：")
    print(result)
    print("均值滤波器实现对图像的平滑处理结果:")
    print(MeanFilter(F,3,3))





