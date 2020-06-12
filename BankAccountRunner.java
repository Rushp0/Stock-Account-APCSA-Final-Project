package accounts;

public class BankAccountRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BankAccount b = new BankAccount();
		//should display empty history
		b.printHistory();
		
		//Testing depositing $100
		b.deposit(100.0);
		b.printHistory();
		
		//Testing withdrawing $100
		b.withdraw(100.0);
		b.printHistory();
		
		//Testing withdrawing more money than having
		b.withdraw(1000000); //1 million
		b.printHistory();
		
		
	}

}