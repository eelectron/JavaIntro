/****************************************************************************
 *  Compilation:  	javac PercolationStats.java
 *  Execution:  	java PercolationStats 200 100
 *  Dependencies: 	Percolation.java StdStats.java StdRandom.java StdIn.java StdOut.java
 *  
 *  Statistical Info about given percolation system.
 ****************************************************************************/

/**
 * This class calculates the average no. of sites which needs to be opened
 * so that system percolates by performing T no. of experiment.
 * This class takes grid size N and no. of experiment T as input.
 * 
 * @author 	Prashant Singh
 * Written:	09-Feb-2015
 */
public class PercolationStats {
	private int N;								//grid size
	private int T;								//no. of experiment
	private int opened;							//no. opened sites
	private double[] expvalue;					//stores value of opened of each exp
	private double mean, stddev;
	public PercolationStats(int N, int T) {		//perform T independent experiment
		if(N <= 0 || T<= 0)
			throw new IllegalArgumentException("N and T must be greater than 0.");
		this.N = N;
		this.T = T;
		expvalue = new double[T];
		exp();									//Start experiment
	}
	
	/**
     * @return probability of a site to be open.
     */
	public double mean() {
		mean = StdStats.mean(expvalue);
		return mean;
	}
	
	/**
     * @return deviation from mean.
     */
	public double stddev() {
		stddev = StdStats.stddev(expvalue);
		return stddev;
	}
	
	/**
     * @return lower bound of mean.
     */
	public double confidenceLo() {
		return (mean - (1.96*stddev)/Math.sqrt(T));
	}
	
	/**
     * @return upper bound of  mean.
     */
	public double confidenceHi() {
		return  (mean + (1.96*stddev)/Math.sqrt(T));
	}
	
	/**
     * @return deviation from mean.
     */
	private void exp() {
		int i, j, x;
		for (int expNo = 0; expNo < T; expNo++) {	//no. of times experiment should be done.
			opened = 0;
			Percolation perc = new Percolation(N);					//get a N-by-N percolation system model
			
			//Run until the system percolates
			while(opened < N*N && !perc.percolates()) {
				x = (int) (N*N*StdRandom.uniform()); 		//random no. between [0,N*N)
				i = x/N + 1;					//1D index to 2D index. 
				j = x % N + 1;                  // Adding 1 as isOpen() expecting i,j in [1,N]
				if (!perc.isOpen(i, j)) {		//open a site only if not already open
					perc.open(i, j);			//opens a random site
					opened++;					//counts open site
				}
			}
			expvalue[expNo] =  (double) opened/(N*N);		//probability of a site
		}
	}
	

	//Test client
	public static void main(String[] args) {
		StdOut.println("Enter grid size and no. exp:");
		int N = StdIn.readInt();
		int T = StdIn.readInt();
		PercolationStats ps = new PercolationStats(N, T);
		
		StdOut.println("mean							="+ps.mean());
		StdOut.println("stddev							="+ps.stddev());
		StdOut.println("95% confidence interval			="+ps.confidenceLo()+", "+ps.confidenceHi());
	}
}
