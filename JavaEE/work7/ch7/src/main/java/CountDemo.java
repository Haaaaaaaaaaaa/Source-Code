public class CountDemo {

	private int total = 0;
//	synchronized可以保证线程安全，但是运算量比较大的时候效率很低
	private synchronized void count() {
		total++;
	}

	public void runTest() {
		new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					try {
						Thread.sleep(15);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					count();
					System.out.println("Thread 1: " + total);
				}
				
			}
		}.start();
		new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					count();					
					System.out.println("Thread 2: " + total);
				}
				
			}
		}.start();
	}
	
	public static void main(String[] args) {
        new CountDemo().runTest();
    }

}
