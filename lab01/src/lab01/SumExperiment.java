package lab01;

public class SumExperiment {
	public static int checkSum(int[] array){
		
		int p1 = 0;
		int p2 = 6;
	
		
		for(int i=0; i < p2; i++)
		{   
			if (array[p1] + array[p2] == 20)
				return p1;
			if(array[p1] + array[p2] > 20)
				 p2--;
			if(array[p1] + array[p2] < 20)
				p1++;
		}
		
		return -1;
	}
	
	public static void main(String[] args) {
		int[] array1 = new int[]{5, 7, 8, 9, 10, 15, 16};
		if (checkSum(array1) != 0)
			System.err.println("TEST1 FAILED");
		
		int[] array2 = new int[]{3, 5, 8, 9, 10, 15, 16};
		if (checkSum(array2) != 1)
			System.err.println("TEST2 FAILED");

		
		int[] array3 = new int[]{3, 4, 6, 9, 10, 14, 15};
		if (checkSum(array3) != 2)
			System.err.println("TEST3 FAILED");
		
		int[] array4 = new int[]{6, 7, 8, 9, 10, 15, 16};
		if (checkSum(array4) != -1)
			System.err.println("TEST4 FAILED");
		
		System.out.println("Done!!!");
	}
}
