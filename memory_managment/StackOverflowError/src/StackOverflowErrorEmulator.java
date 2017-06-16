// TODO: JVM arguments - -Xss1m -XX:MaxNewSize=3m -XX:SurvivorRatio=1
public class StackOverflowErrorEmulator {
	public static int counter = 0;
	String val;
	
	public String toString() {
		System.out.println("Iteration #"+ ++counter);
		return val + this;
	}
	
	public static void main(String[] args) {	  	  
		System.out.println(new StackOverflowErrorEmulator());
	}			
}
