package adapter.pay;

public class Payment {
	
	public static enum PaymentType {
		XPAY,
		YPAY,
		ZPAY
    };
	
	private PaymentType type;
	private float price;
	private long itemId;
	private String cardNum;
	
	public Payment(PaymentType type, float price, long itemId, String cardNum) {
	  this.type = type;
	  this.price = price;
	  this.itemId = itemId;
	  this.cardNum = cardNum;
	}

	public PaymentType getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	
	

}
