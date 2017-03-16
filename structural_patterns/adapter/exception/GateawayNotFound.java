package exception;

public class GateawayNotFound extends Exception {

	private static final long serialVersionUID = 1L;	
	
    public GateawayNotFound(String msg) {
		super(msg);
	}

	public String getMessage() {
		return "Handler for "+super.getMessage()+" not found";    	
    }

}
