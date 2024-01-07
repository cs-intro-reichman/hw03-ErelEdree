/**
 * Computes the periodical payment necessary to re-pay a given loan.
 */
public class LoanCalc {

	static double epsilon = 0.001; // The computation tolerance (estimation error)
	static int iterationCounter; // Monitors the efficiency of the calculation

	/**
	 * Gets the loan data and computes the periodical payment.
	 * Expects to get three command-line arguments: sum of the loan (double),
	 * interest rate (double, as a percentage), and number of payments (int).
	 */
	public static void main(String[] args) {
		// Gets the loan data
		double loan = Double.parseDouble(args[0]); // getting the loan from the user
		double rate = Double.parseDouble(args[1]); // getting the rate from the user
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan sum = " + loan + ", interest rate = " + rate + "%, periods = " + n);

		// Computes the periodical payment using brute force search
		System.out.print("Periodical payment, using brute force: ");
		System.out.printf("%.2f", bruteForceSolver(loan, rate, n, epsilon));
		System.out.println();
		System.out.println("number of iterations: " + iterationCounter);

		// Computes the periodical payment using bisection search
		System.out.print("Periodical payment, using bi-section search: ");
		System.out.printf("%.2f", bisectionSolver(loan, rate, n, epsilon));
		System.out.println();
		System.out.println("number of iterations: " + iterationCounter);
	}

	/**
	 * Uses a sequential search method ("brute force") to compute an approximation
	 * of the periodical payment that will bring the ending balance of a loan close
	 * to 0.
	 * Given: the sum of the loan, the periodical interest rate (as a percentage),
	 * the number of periods (n), and epsilon, a tolerance level.
	 */
	// Side effect: modifies the class variable iterationCounter.
	public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
		// Replace the following statement with your code
		iterationCounter = 0;
		double test_loan = loan;
		double search = loan / n;
		{
			while (test_loan > epsilon) {
				test_loan = endBalance(loan, rate, n, search);
				iterationCounter++;
				search += epsilon;
			}
			iterationCounter--;
		}
		return search;

	}

	/**
	 * Uses bisection search to compute an approximation of the periodical payment
	 * that will bring the ending balance of a loan close to 0.
	 * Given: the sum of theloan, the periodical interest rate (as a percentage),
	 * the number of periods (n), and epsilon, a tolerance level.
	 */
	// Side effect: modifies the class variable iterationCounter.
	public static double bisectionSolver(double loan, double rate, int n, double epsilon) {
		// Replace the following statement with your code
		double L = loan / n;
		double H = loan;
		double middle = (L + H) / 2;
		iterationCounter = 0;
		while (H - L > epsilon) {
			if (endBalance(loan, rate, n, (middle)) * endBalance(loan, rate, n, (L)) > 0) {
				L = middle;
			} else {
				H = middle;
			}
			middle = (L + H) / 2;
			iterationCounter += 1;
		}
		return (middle);
	}

	/**
	 * Computes the ending balance of a loan, given the sum of the loan, the
	 * periodical
	 * interest rate (as a percentage), the number of periods (n), and the
	 * periodical payment.
	 */
	private static double endBalance(double loan, double rate, int n, double payment) {
		// Replace the following statement with your code
		double test_loan = loan;
		rate = rate / 100;
		for (int i = 0; i < n; i++) {
			test_loan = test_loan - payment;
			test_loan = test_loan * (1 + rate);
		}
		return test_loan;
	}
}