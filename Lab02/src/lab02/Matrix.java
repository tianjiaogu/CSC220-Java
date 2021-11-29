package lab02;

public class Matrix {
	int numRows;
	int numColumns;
	int data[][];
	
	// default constructor
	//public Matrix(){}
	
	// constructor 1 - Constructor for new zero matrix
	public Matrix(int rowDim, int colDim){
		
		data = new int[rowDim][colDim];
		numRows= rowDim;
		numColumns=colDim;
		
	}
	
	
	// constructor 2 - Constructor with data for new matrix (automatically determines dimensions)
	public Matrix(int d[][])
	{   
		numRows = d.length;
		numColumns = d[0].length;
		data = new int[numRows][numColumns];
		for(int i=0; i<numRows;i++)
		   for(int j=0;j<numColumns;j++) {
			   data[i][j] = d[i][j];
		   }
		  
		/* 1) put the numRows to be the number of 1D arrays in the 2D array
		*  2) specify the numColumns and set it
		*  3) be careful of special cases you are supposed to handle them properly
		*  4) create a new matrix to hold the data
		*  5) copy the data over
		*/
	}	
	
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public String toString()
	{
		String s= "\n";
		
		for(int i=0; i< numRows; i++) {
			for( int j=0; j< numColumns; j++) {
				s += data[i][j] + " ";
				}
		s += "\n";
		}
		return s;
	}
	
	
	
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public boolean equals(Object o)
	{
		if(!(o instanceof Matrix)) // make sure the Object we're comparing to is a Matrix
			return false;
		Matrix m = (Matrix) o;
		int c = 0;
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j< numColumns; j++)
			{
			   if( data[i][j] == m.data[i][j] )
						c=0;
				else c=1;
				
			}
		}
		if (c == 0) 
			return true;
		
			/*
			}
		 * TODO: replace the below return statement with the correct code, you must return the correct value
		 * 			after determining if this matrix is equal to the input matrix
		 */
		return false; // placeholder
	}
	

	public Matrix times(Matrix m)
	{	if( this.numColumns != m.numRows )
			return null;
		Matrix multi = new Matrix(new int[this.numRows][m.numColumns]);
		for(int i=0; i<this.numRows ; i++) 
			for(int j=0; j<m.numColumns ; j++)
				for(int k=0; k< this.numColumns ; k++)
				{
					multi.data[i][j] += this.data[i][k] * m.data[k][j];
				}
		
		return multi;
		
		/*
		 * TODO: replace the below return statement with the correct code, 
		 *  This function must check if the two matrices are compatible for multiplication, if not, return null.
		 *  If they are compatible, determine the dimensions of the resulting matrix, and fill it in with
		 *  the correct values for matrix multiplication
		 */
		 // placeholder
	}
	
	public Matrix plus(Matrix m)
	{
		if (this.numColumns != m.numColumns || this.numRows != m.numRows)
			return null;
		Matrix add = new Matrix(new int[this.numRows][this.numColumns]);
		for(int i=0; i<this.numRows; i++ )
			for(int j=0; j<this.numColumns; j++)
			{
				add.data[i][j] += this.data[i][j] + m.data[i][j];
			}
				
			/*
		 * TODO: replace the below return statement with the correct code, 
		 *  This function must check if the two matrices are compatible for addition, if not, return null.
		 *  If they are compatible, create a new matrix and fill it in with
		 *  the correct values for matrix addition
		 */
		return add; // placeholder
	}
    
    public Matrix transpose()
    {
       Matrix trans =  new Matrix(new int[this.numColumns][this.numRows]);
       for(int i=0; i<this.numRows; i++)
    	   for(int j=0; j<this.numColumns; j++)
    	   {
    		   trans.data[i][j]=this.data[j][i];
    	   }/*
    	   * TODO: replace the below return statement with the correct code,
         */
        return trans; // placeholder
    }
}
