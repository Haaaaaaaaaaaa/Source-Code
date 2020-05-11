"""
Description:图像旋转变换
            智能系统理论 作业4
            处理真实的图片。
Version:    PyCharm 2017.1
Date:       2020/5/10
"""

import cv2
import numpy as np
from math import fabs
from math import sin, cos, radians

# 旋转图片无黑边
def ImageRotation():
    path = '1.jpg'
    img = cv2.imread(path)
    height, width = img.shape[:2]

    degree = 45#旋转角度

    # 旋转后的尺寸
    heightNew = int(width * fabs(sin(radians(degree))) + height * fabs(cos(radians(degree))))
    widthNew = int(height * fabs(sin(radians(degree))) + width * fabs(cos(radians(degree))))
    # print(heightNew)
    # print(widthNew)

    matRotation = cv2.getRotationMatrix2D((width / 2, height / 2), degree, 1)

    # print(matRotation[0, 2])
    # print(matRotation)
    matRotation[0, 2] += (widthNew - width) / 2
    matRotation[1, 2] += (heightNew - height) / 2

    imgRotation = cv2.warpAffine(img, matRotation, (widthNew, heightNew), borderValue=(255, 255, 255))

    cv2.imshow('img', imgRotation)
    cv2.waitKey(0)

if __name__ == '__main__':
    ImageRotation()