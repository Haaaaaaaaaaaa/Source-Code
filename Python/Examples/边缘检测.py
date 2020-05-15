import numpy as np
import cv2
from PIL import Image

judge = input('1 使用矩阵，其他 读取图片')
np.set_printoptions(precision=0)
if judge == '1':
    img = np.array([[1,5,255,180,200,200],[1,7,254,190,170,9],[3,7,254,190,2,6],[1,0,8,7,2,1],[1,1,6,50,2,2],[2,3,9,7,2,0]])
else:
    img = cv2.imread('C:\\Users\\djr\\Desktop\\00.jpg')
    img = cv2.cvtColor(img,cv2.COLOR_RGB2GRAY)

print(img.shape)
length,width= img.shape


#sobel
edge = np.zeros((length,width))
for x in range(1,length-1):
    for y in range(1,width-1):
        Nx = img[x+1][y-1]+2*img[x+1][y]+img[x+1][y+1]-(img[x-1][y-1]+2*img[x-1][y]+img[x-1][y+1])
        Ny = img[x-1][y+1]+2*img[x][y+1]+img[x+1][y+1]-(img[x-1][y-1]+2*img[x][y-1]+img[x+1][y-1])
        edge[x][y] = (Nx*Nx+Ny*Ny)**0.5

if judge == '1':
    print('sobel')
    print(edge)
else:
    pic = Image.fromarray(edge)
    pic.show()




#roberts
edge = np.zeros((length,width))
for x in range(1,length-1):
    for y in range(1,width-1):
        Nx = int(img[x-1][y-1])-int(img[x+1][y+1])
        Ny = int(img[x-1][y+1])-int(img[x+1][y-1])
        edge[x][y] = (Nx*Nx+Ny*Ny)**0.5

if judge == '1':
    print('roberts')
    print(edge)
else:
    pic = Image.fromarray(edge)
    pic.show()


#prewitt
edge = np.zeros((length,width))
for x in range(1,length-1):
    for y in range(1,width-1):
        Nx = int(img[x+1][y-1])+int(img[x+1][y])+int(img[x+1][y+1])-(int(img[x-1][y-1])+int(img[x-1][y])+int(img[x-1][y+1]))
        Ny = int(img[x-1][y+1])+int(img[x][y+1])+int(img[x+1][y+1])-(int(img[x-1][y-1])+int(img[x][y-1])+int(img[x+1][y-1]))
        edge[x][y] = (Nx*Nx+Ny*Ny)**0.5

if judge == '1':
    print('prewitt')
    print(edge)
else:
    pic = Image.fromarray(edge)
    pic.show()


#Laplacian
edge = np.zeros((length,width))
for x in range(1,length-1):
    for y in range(1,width-1):
        edge[x][y] = int(img[x+1][y])+int(img[x-1][y])+int(img[x][y+1])+int(img[x][y-1])-4*int(img[x][y])

if judge == '1':
    print('Laplacian')
    print(edge)
else:
    pic = Image.fromarray(edge)
    pic.show()