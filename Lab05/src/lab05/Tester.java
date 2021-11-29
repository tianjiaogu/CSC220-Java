package lab05;

public class Tester {

	public static void main(String args[]) {
		BinarySearchSet test1 = new BinarySearchSet();
		double[] m = test1.storage;
		System.out.println(test1.size());
		m[0]=1;
		m[1]=2;
		m[2]=3;
		m[3]=4;
		m[4]=6;
		m[5]=0;
		double[] n= new double[] {3,4,5};
		System.out.println(test1.size());
		System.out.println(test1.isEmpty());
		System.out.println(test1.toString());
		//test1.grow();
		//test1.insert(5);//
		System.out.println(test1.remove(0));
		System.out.println(m);
		System.out.println(test1.findIndex(4));
		System.out.println(test1.findIndex(5));
		System.out.println(test1.containsAll(n));
		
		BinarySearchSet test2 = new BinarySearchSet();
		System.out.println(test2);
		long start = System.nanoTime();
		for(int i = 1; i < 100000; i++) {
			test2.insert(i);
			//System.out.println(test2);
		}
		long end = System.nanoTime();
		
	}
}
