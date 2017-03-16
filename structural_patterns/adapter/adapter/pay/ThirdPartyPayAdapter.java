package adapter.pay;

import adapter.integration.ThirdPartyInterface;
import adapter.integration.YPay;
import exception.GateawayNotFound;

public class ThirdPartyPayAdapter implements PayProcessor {

	@Override
	public boolean doPay(Payment payment) throws GateawayNotFound {
		ThirdPartyInterface handler = null;
		
		if (Payment.PaymentType.YPAY == payment.getType()) {
			handler = new YPay();
		}
		
		if (handler == null) {
			throw new GateawayNotFound(payment.getType().toString());
		}
		
		return handler.doPay(payment.getCardNum(), payment.getPrice());
	}

}
