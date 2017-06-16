import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HeapOutOfMemoryEmulator {
	
	public static int counter = 0;
	
	private static final String FILENAME = "datastorage.txt";
	private String holder;
	private HeapOutOfMemoryEmulator nestedObject;
	
	public HeapOutOfMemoryEmulator() {
		String path = this.getClass().getClassLoader().getResource("").getPath();
		holder = new String();
		try (BufferedReader br = new BufferedReader(new FileReader(path + "\\" +FILENAME))) {
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				holder = holder + sCurrentLine;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		holder = holder + counter;
		System.out.println(counter++ + " - created, Free memory = " + Runtime.getRuntime().freeMemory() / 1024 / 1024 + " Mb");
		nestedObject = new HeapOutOfMemoryEmulator();		
	}

	public static void main(String[] args) {		
		new HeapOutOfMemoryEmulator();
	}

}
