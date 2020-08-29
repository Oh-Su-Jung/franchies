package java_swing_franchise.dto;

public class Product {
	private int num;
	private String code;
	private String name;
	private int price;
	private int length;
	private int profit;
	
	public Product() {}

	public Product(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public Product(String code, int price, int length, int profit) {
		this.code = code;
		this.price = price;
		this.length = length;
		this.profit = profit;
	}

	public Product(String code, String name, int price, int length, int profit) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.length = length;
		this.profit = profit;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getProfit() {
		return profit;
	}

	public void setProfit(int profit) {
		this.profit = profit;
	}
	
	public int getTotalPrice() {
		return getPrice() * getLength();
	}
	
	public int getVat() {
		return (int) Math.ceil(getTotalPrice() / 11D);
	}
	
	public int getSupply() {
		return getTotalPrice() - getVat();
	}
	
	public int getProfitPrice() {
		return (int)Math.round((getSupply() * getProfit()) / 100D);
	}

	@Override
	public String toString() {
		return String.format("제품순위 : %d 제품코드 : %s 제품명 : %s 제품단가 : %,d 판매수량 : %s 마진율 : %s 판매금액 : %,d 부가세액 : %,d 공급가액 : %,d 마진액 : %,d", 
				num, code, name, price, length, profit, getTotalPrice(), getVat(), getSupply(), getProfitPrice());
	}
	
	
	
	
	
}
