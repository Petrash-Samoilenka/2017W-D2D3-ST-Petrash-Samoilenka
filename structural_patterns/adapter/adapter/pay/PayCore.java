package adapter.pay;

public class PayCore {
	private static PayCore instance;
	
	private PayCore() {
		
	}
	
	public static PayCore getInstance() {
		if (instance == null) {
			instance = new PayCore();
		}
		return instance;
	}
	
	public boolean processPayment(Payment payment) throws Exception {
		PayProcessor processor = null;
		
		if (payment.getType() == Payment.PaymentType.XPAY) {
		  processor = new XPay();	
		} else {
		  processor = new ThirdPartyPayAdapter();
		}			
		
		return processor.doPay(payment);
	}
	
}
