"""
Introduction:Roberts算子的实现
              题目：对下图（矩阵）进行Sobel算子、罗伯特算子和Prewitt算子边缘检测， 同时进行Laplacian算子处理，
              同时还能够进行处理真正的图像（通常情况下，边缘点置为白色，其它点置为黑色； 反之也可以试一下）。
              智能系统理论与应用 作业3
Version:      PyCharm 2017.1
Date:         2020/5/8
"""
import numpy as np

def Roberts(F):#F为待处理图（这里是矩阵）
    res = np.zeros((F.shape[0],F.shape[1])).astype(np.int_)#取一个和原图一样大小的图片，并在里面填充0,并转换为整型

    roberts_x =[[1, 0],[0, -1]]#Roberts模板
    roberts_y =[[0, 1],[-1, 0]]

    for x in range(F.shape[0]-1):
        for y in range(F.shape[0]-1):
            matrix=[[F[x,y],F[x,y+1]],[F[x+1,y],F[x+1,y+1]]]#x,y代表像素的位置
            matrix=np.array(matrix)#在python标准中list是不能做乘法，所以np.array()把list转就可以相乘
            roberts_x = np.array(roberts_x)
            roberts_y = np.array(roberts_y)

            # 核心处理过程
            var_x=sum(sum(matrix*roberts_x))#矩阵相乘，查看公式，我们要得到是一个值，所以对它进行两次相加
            var_y=sum(sum(matrix*roberts_y))
            var = abs(var_x) + abs(var_y)#绝对值相加
            res[x][y]=var

            # 处理没有改变的像素点，使之保持原来的值
            if y==F.shape[0]-2:
                res[x][y+1]=F[x][y+1]
            if x==F.shape[1]-2 :
                res[x+1][y]=F[x+1][y]
    print("Roberts算子处理后：")
    print(res)

f=np.array([[1,5,255,180,200,200],[1,7,254,190,170,9],[3,7,254,190,2,6],[1,0,8,7,2,1],[1,1,6,50,2,2],[2,3,9,7,2,0]])# 待处理图像
print("原始图像为：")
print(f)
Roberts(f)
