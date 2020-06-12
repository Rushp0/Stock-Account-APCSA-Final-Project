package accounts;

public abstract class Account {
	
	private double balance=10000;
	
	public Account(){}
	public double returnBalance(){
		return balance;
	}
	public void deposit(double a){
		balance+=a;
	}
	public void withdraw(double a){
		balance-=a;
	}
	protected void setBalance(double d){
		balance = d;
	}
}