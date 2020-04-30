import cv2
import numpy as np

# 读入图片
image1 = cv2.imread('D:\Application Data\Git\SourceCode\Python\Image\images/1.jpg')
image2 = cv2.imread('D:\Application Data\Git\SourceCode\Python\Image\images/2.jpg')

# 预览原始图片
cv2.imshow('image1', image1)
cv2.waitKey(0)
cv2.imshow('image2', image2)
cv2.waitKey(0)

def logic_img(img1, img2):
    add_image = cv2.add(img1, img2)#图像相加
    cv2.imshow('add_image', add_image)
    cv2.waitKey(0)
    subtract_image = cv2.subtract(img1, img2)#图像相减
    cv2.imshow('subtract_image', subtract_image)
    cv2.waitKey(0)

logic_img(image1, image2)

