"""
Description:图像旋转变换
            智能系统理论 作业4
            处理矩阵。
Version:    PyCharm 2017.1
Date:       2020/5/10
"""


import numpy as np
import math


# 旋转
def ImageMatrixRotation(F):
    height=F.shape[0]
    width = F.shape[1]

    # 旋转后的尺寸
    rotation = np.zeros((2 * height, 2 * width),dtype="uint8")

    # 旋转
    for x in range(1, height + 1):
        for y in range(1, width + 1):
            x1 = int(x * math.cos(math.pi / 4) + y * math.sin(math.pi / 4))
            y1 = int(-x * math.sin(math.pi / 4) + y * math.cos(math.pi / 4))
            rotation[x1 - 1][width + y1 - 1] = F[x - 1][y - 1]
    print("图像旋转结果：")
    print(rotation)

if __name__ == '__main__':
    F=np.array([[59,60,58,57],[61,59,59,57],[62,59,60,58],[59,61,60,56]])
    print(F)
    ImageMatrixRotation(F)
