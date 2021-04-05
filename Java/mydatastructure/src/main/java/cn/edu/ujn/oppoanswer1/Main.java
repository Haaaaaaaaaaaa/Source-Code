package cn.edu.ujn.oppoanswer1;


/**
 * @description: 三个线程循环打印ABC
 * @author: 蔡涛
 * @date: 2021-3-25
 */
public class Main {
    public static void main(String[] args) {
        PrintABC printA = new PrintABC();
        PrintABC printB = new PrintABC();
        PrintABC printC = new PrintABC();

        while (true) {
            new Thread((Runnable) printA).start();
            new Thread((Runnable) printB).start();
            new Thread((Runnable) printC).start();
        }

    }
}

class PrintABC {
    private boolean flag = false;

    /**
     * 线程一打印A
     */
    public synchronized void printA() {
        try {
            while (flag) {
                wait();
            }
            System.out.println("A");
            flag = true;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 线程二打印B
     */
    private synchronized void printB() {
        try {
            while (!flag) {
                wait();
            }
            System.out.println("B");
            flag = false;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 线程三打印C
     */
    private synchronized void printC() {
        try {
            while (flag) {
                wait();
            }
            System.out.println("C");
            flag = true;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


