"""
Description:图像错切变换
            智能系统理论 作业4
            处理矩阵。
Version:    PyCharm 2017.1
Date:       2020/5/11
"""

import numpy as np
import math

# 错切
def ImageMatrixShear(F):
    height=F.shape[0]
    width = F.shape[1]

    # 错切后的尺寸
    shear = np.zeros((2 * height, 2 * width),dtype="uint8")

    # 错切处理
    for x in range(1,  height+ 1):
        for y in range(1, width + 1):
            x1 = int(x)
            y1 = int(x * math.tan(math.pi / 4) + y)
            shear[x1 - 1][y1 - 1] = F[x - 1][y - 1]
    print("图像错切结果：")
    print(shear)

if __name__ == '__main__':
    F=np.array([[59,60,58,57],[61,59,59,57],[62,59,60,58],[59,61,60,56]])
    print(F)
    ImageMatrixShear(F)
