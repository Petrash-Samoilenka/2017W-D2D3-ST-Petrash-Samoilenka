package adapter.pay;

public interface PayProcessor  {

	boolean doPay(Payment payment) throws Exception;
}
