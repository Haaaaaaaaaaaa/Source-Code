'''
Introduce:  智能系统理论与应用第二次作业，作业题2。
            边界保持的中值和均值滤波。
            K近邻中值滤波器：
            1)以待处理像素为中心，作一个m×m的作用模板；
            2)在模板中，选择K个与待处理像素的灰度差为最小的像素；
            3)将这K个像素的灰度中值替换掉原来的像素值。
Date:       2020/5/5          
'''

import numpy as np
import cv2

# 寻找K个与待处理像素灰度差最小的像素的中值
def Kernel(center,matrix,k):  #center：要替换点的坐标，matrix：目标所在kernel矩阵，k：近邻数
    matrix = matrix.astype('int_')
    list1 = [[abs(i-center),i] for i in matrix.ravel()]   #对目标所在的矩阵平铺展开，然后相减，然后排序，前k个对应的
    list1.sort()    #排序
    # print(list1)  #打印出排序后的list
    print(np.sort(np.array(list1)[:k,1]))   #打印K个像素点
    return np.median(np.sort(np.array(list1)[:k,1]))  #取中位数

kernel = 3 #3*3模板
k = 5 #k近邻个数

# K近邻的中值滤波器
def BoundaryPreservingMedianFilter(f,kernel,k):
    result_f = f.copy()
    for i in range(result_f.shape[0]-kernel+1):  #（0,3）
        for j in range(result_f.shape[1]-kernel+1):
            result_f[i+int(kernel/2)][j+int(kernel/2)] = Kernel(result_f[i+int(kernel/2)][j+int(kernel/2)],f[i:i+kernel,j:j+kernel],5)
    print("边界保持的中值滤波处理结果：")
    return result_f

# K近邻的中值滤波器
# def BoundaryPreservingMedianFilter(F, num):
#     F_ = np.zeros(F.shape)
#     for i in range(F.shape[0]):
#         for j in range(F.shape[1]):
#             if i==0 or i == F.shape[0]-1 or j==0 or j==F.shape[1]-1:
#                 F_[i,j] = F[i,j]
#             else :
#                 F_[i, j] = np.median(F[i-1:i+2, j-1 : j+2])
#     return np.round(F_).astype(np.uint8)


f = np.array([[1,5,255,180,200,200],[1,7,254,190,170,9],[3,7,254,190,2,6],[1,0,8,7,2,1],[1,1,6,50,2,2],[2,3,9,7,2,0]])
print(BoundaryPreservingMedianFilter(f,kernel,k))