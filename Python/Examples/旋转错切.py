import numpy as np
import cv2
from PIL import Image
import math
judge = input('1 使用矩阵,其他 读取图片')
np.set_printoptions(precision=0)
if judge == '1':
    img = np.array([[59,60,58,57],[61,59,59,57],[62,59,60,58],[59,61,60,56]])
else:
    img = cv2.imread('1.jpg')
    img = cv2.cvtColor(img,cv2.COLOR_RGB2GRAY)

print(img.shape)
length,width= img.shape


rotation = np.zeros((2*length+1,2*width+1))
shear = np.zeros((2*length+1,2*width+1))
#旋转
for x in range(1,length+1):
    for y in range(1,width+1):
        x1=int(x*math.cos(math.pi/4)+y*math.sin(math.pi/4))
        y1=int(-x*math.sin(math.pi/4)+y*math.cos(math.pi/4))
        #rotation[length*2+x1-1][2*width-1+y1-1]=img[x-1][y-1]
        rotation[x1-1][width+y1-1]=img[x-1][y-1]

#错切
for x in range(1,length+1):
    for y in range(1,width+1):
        x1=int(x)
        y1=int(x*math.tan(math.pi/4)+y)
        #if x1<4 and y1<4:
        #shear[2*length+x1-1][2*width+y1-1]=img[x-1][y-1]
        shear[x1-1][y1-1]=img[x-1][y-1]


if judge == '1':
    print('rotation')
    print(rotation)
    print('shear')
    print(shear)
else:
    pic = Image.fromarray(rotation)
    pic.show()
    pic = Image.fromarray(shear)
    pic.show()





