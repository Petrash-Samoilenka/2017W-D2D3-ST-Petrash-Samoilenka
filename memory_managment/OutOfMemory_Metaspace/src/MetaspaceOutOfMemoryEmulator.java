// TODO: JVM arguments -XX:MetaspaceSize=1m -XX:MaxMetaspaceSize=10m
public class MetaspaceOutOfMemoryEmulator {   	
	public static void main(String[] args) {
      for (int i = 1; i<=1000; i++) {
    	  Class c = null;
    	  try {
			c = Class.forName("Class"+i);
			Object o = c.newInstance();
			System.out.println("Class"+i+" loaded");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}    	  
      }
	}

}
