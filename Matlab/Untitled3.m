clear;
clc;
close all;
n=4;
b=10;
v=[1 3 5 9];
w=[2 3 4 7];

f=zeros(5,11);
i=zeros(5,11);
y=0:b;

f(2,:)=floor(y/w(1)*v(1));
i(2,:)=f(2,:)>0;

for k=2:n
    for y=0:b
        if y-w(k)>=0&&(f(k,y+1)<f(k+1,y+1-w(k))+v(k))
             f(k+1,y+1)=f(k+1,y+1-w(k))+v(k);
           i(k+1,y+1)=k;
        else
             f(k+1,y+1)=f(k,y+1);
            i(k+1,y+1)=i(k,y+1);
        end
    end
end

x=zeros(n,1);
y=b;
j=n;
while i(j+1,y+1)~=0
    j=i(j+1,y+1);
    x(j)=1;
    y=y-w(j);
    while i(j+1,y+1)==j
       y=y-w(j);
    x(j)=x(j)+1;
    end
end
for i=1:n
    fprintf('put %d item%d in the bag\n',x(i),i);
end

