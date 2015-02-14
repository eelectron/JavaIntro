/****************************************************************************
 *  Compilation:  javac Percolation.java
 *  Execution:  java Percolation
 *  Dependencies: WeightedQuickUnionUF.java
 *  Percolation Model.
 ****************************************************************************/

/**
 * The <tt>Percolation</tt> class represents a system which finds the
 * percolation threshold. ex: electricity flow through material,fluid flow
 * through material,etc. It supports the <em>open</em>,<em>isOpen</em>,
 * <em>isFull</em>,<em>percolates</em> operations.
 * <p>
 * This class expects a no. N( >=1) by user to create a N-by-N sites. We keep on
 * opening sites until system percolates.
 * </p>
 * 
 * @author Prashant Singh Written: 09-Feb-2015
 */
public class Percolation {
	private final static int OPEN = 1;
	private final static int BLOCK = 0;
	private int N; // size of sites will be N-by-N
	private int[][] sites; // every location stores 0(site blocked) or 1(site
							// open)
	private int virtualTop; // sites[0][0]
	private int virtualBottom; // sites[N-1][0]
	private WeightedQuickUnionUF wqu; // To connect to sites

	public Percolation(int N) {
		if (N <= 0)
			throw new IllegalArgumentException(
					"Grid Size must be greater than 0.");
		this.N = N;
		virtualTop = 0;
		virtualBottom = (N - 1) * N;
		wqu = new WeightedQuickUnionUF(N * N); // sites have N*N sites
		sites = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sites[i][j] = BLOCK; // initially all sites are blocked(0)
			}
		}
	}

	/**
	 * Opens a site if not already open and connect it with its adjacent open
	 * sites. Virtual top = sites[0][0] ie first site in FIRST row. Virtual
	 * bottom = sites[N-1][0] ie first site in LAST row. This method assumes the
	 * position of site from 1 <= i,j <= N that's why it decrement i,j by 1 to
	 * get its index.
	 * 
	 * @param Integer
	 *            i, j the location of a site in 2D array
	 * @throws java.lang.IndexOutOfBoundsException
	 *             unless 0 <= i,j < N
	 */
	public void open(int x, int y) {
		int i = x - 1; // Converts a position (i,j) to index in 2D array
		int j = y - 1;
		if (!isOpen1(i, j)) {
			sites[i][j] = OPEN; // opens(1) a site
			int site = xyTo1D(i, j); // Map 2D location to 1D

			// Connect all open site of top row with sites[0][0]
			if (i == 0)
				wqu.union(virtualTop, site);

			// Connect all bottom sites with sites[N-1][0]
			if (i == N - 1)
				wqu.union(virtualBottom, site);

			if (isValidIndex(i, j - 1) && isOpen1(i, j - 1)) // left
				wqu.union(site, i * N + j - 1);
			if (isValidIndex(i, j + 1) && isOpen1(i, j + 1)) // right
				wqu.union(site, i * N + j + 1);
			if (isValidIndex(i + 1, j) && isOpen1(i + 1, j)) // below
				wqu.union(site, (i + 1) * N + j);
			if (isValidIndex(i - 1, j) && isOpen1(i - 1, j)) // above
				wqu.union(site, (i - 1) * N + j);
		}
	}

	/**
	 * This method assumes the position of site from 1 <= i,j <= N that's why it
	 * decrement i,j by 1 to get its index.
	 * 
	 * @return true if site is open
	 * @param Integer
	 *            i, j the location of a site in 2D array
	 * @throws java.lang.IndexOutOfBoundsException
	 *             unless 0 <= i,j < N
	 */
	public boolean isOpen(int i, int j) {
		int x = i - 1;
		int y = j - 1;
		return sites[x][y] == OPEN;
	}

	/**
	 * This method assumes the position of site from 1 <= i,j <= N that's why it
	 * decrement i,j by 1 to get its index.
	 * 
	 * @return true if site is open and connected to VIRTUALTOP
	 * @param Integer
	 *            i, j the location of a site in 2D array
	 * @throws java.lang.IndexOutOfBoundsException
	 *             unless 0 <= i,j < N
	 */
	public boolean isFull(int x, int y) {
		int i = x - 1;
		int j = y - 1;
		int site = xyTo1D(i, j);
		if (isOpen1(i, j) && wqu.connected(site, virtualTop))
			return true;
		return false;
	}

	/**
	 * @return true if virtualTop is connected with virtualBottom
	 * @param Integer
	 *            i, j the location of a site in 2D array
	 */
	public boolean percolates() {
		return wqu.connected(virtualTop, virtualBottom);
	}

	/**
	 * This is helper function which expects index value of site not physical
	 * location.
	 * 
	 * @return true if site is open
	 * @param Integer
	 *            i, j the location of a site in 2D array sites[][].
	 * @throws java.lang.IndexOutOfBoundsException
	 *             unless 0 <= i,j < N
	 */
	private boolean isOpen1(int i, int j) {
		return sites[i][j] == OPEN;
	}

	/**
	 * This is helper function which expects index value of site not physical
	 * location.
	 * 
	 * @return true if index i,j lies between [0,N)
	 * @param Integer
	 *            i, j the location of a site in 2D array sites[][].
	 */
	private boolean isValidIndex(int i, int j) {
		if (i < 0 || i >= N || j < 0 || j >= N)
			return false;
		return true;
	}

	/**
	 * This is helper function which expects index value of site not physical
	 * location.
	 * 
	 * @return integer,1D array index which denotes a site.
	 * @param int x, y the index of a site in 2D array sites[][].
	 */
	private int xyTo1D(int x, int y) {
		return x * N + y;
	}
}
