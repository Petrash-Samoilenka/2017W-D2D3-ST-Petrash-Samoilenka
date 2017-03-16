package adapter;

import static adapter.pay.Payment.PaymentType;

import adapter.pay.PayCore;
import adapter.pay.Payment;

public class AdapterRunner {

	public static void main(String[] args) {
		System.out.println("Adapter pattern implementation");
		
		try {
			
			Payment xpay = new Payment(PaymentType.XPAY, 100, 1, "4555 96xx xxxx 9512");
			Payment ypay = new Payment(PaymentType.YPAY, 200, 2, "6777 88xx xxxx 7453");
			Payment zpay = new Payment(PaymentType.ZPAY, 300, 3, "5264 74xx xxxx 6517");
			
			PayCore.getInstance().processPayment(xpay);
			PayCore.getInstance().processPayment(ypay);
			PayCore.getInstance().processPayment(zpay);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
