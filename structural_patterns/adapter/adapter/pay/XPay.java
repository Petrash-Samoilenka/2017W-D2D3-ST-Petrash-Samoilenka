package adapter.pay;

public class XPay implements PayProcessor {

	@Override
	public boolean doPay(Payment payment) {
        System.out.println("Pay TYPE = " + payment.getType()
                         + " Card number=" + payment.getCardNum() 
	                     + " Price="+Float.toString(payment.getPrice())
	                     + " -- done");
		return true;
	}

}
