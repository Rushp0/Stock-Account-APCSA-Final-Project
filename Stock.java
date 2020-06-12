package stocks;

public class Stock {
		
	String symbol;
	int amount;
	double price;
	
	public Stock(String symbol, int amount, double price) {
		this.symbol = symbol;
		this.amount = amount;
		this.price = price;
	}
	public String getSymbol() {
		return this.symbol;
	}
	public int getAmount() {
		return this.amount;
	}
	public double getPrice() {
		return this.price;
	}
	public void IncreaseAmount(int a) {
		this.amount+=a;
	}
	public void DecreaseAmount(int a) {
		this.amount-=a;
	}
	
	
}
