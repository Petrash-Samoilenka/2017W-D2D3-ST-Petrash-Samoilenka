package adapter.integration;

public class YPay implements ThirdPartyInterface {

	@Override
	public boolean doPay(String cardNum, float price) {
		 System.out.println("Pay TYPE = YPAY" 
                   + " Card number=" + cardNum 
                   + " Price="+Float.toString(price)
                   + " -- done");		 
		return true;
	}

}
