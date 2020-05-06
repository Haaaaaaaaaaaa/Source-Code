'''
Introduce:  智能系统理论与应用第二次作业，作业题2。
            边界保持的中值和均值滤波。
            K近邻均值滤波器：
            1)以待处理像素为中心，作一个m×m的作用模板；
            2)在模板中，选择K个与待处理像素的灰度差为最小的像素；
            3)将这K个像素的灰度均值替换掉原来的像素值。
Date:       2020/5/5          
'''

import numpy as np
import cv2


# K近邻的均值滤波器
def BoundaryPreservingMeanFilter(F, num):
    F_result = np.zeros(F.shape)
    M = 1.0/num/num * np.ones((num, num))
    for i in range(F.shape[0]):
        for j in range(F.shape[1]):
            if i==0 or i == F.shape[0]-1 or j==0 or j==F.shape[1]-1:
                F_result[i,j] = F[i,j]
            else :
                F_result[i, j] = (F[i-1:i+2, j-1 : j+2] * M).sum()
    return np.round(F_result).astype(np.int_)

f = np.array([[1,5,255,180,200,200],[1,7,254,190,170,9],[3,7,254,190,2,6],[1,0,8,7,2,1],[1,1,6,50,2,2],[2,3,9,7,2,0]]).astype(np.uint16)
print(BoundaryPreservingMeanFilter(f,3))
# print(keepEdgeMedian(f,3))