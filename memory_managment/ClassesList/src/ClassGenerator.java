import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.IntStream;


public class ClassGenerator {	
	
	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer();
		String path = ClassGenerator.class.getResource("").getPath();		
		try (BufferedReader br = new BufferedReader(new FileReader(path + "\\" +"templateClass.txt"))) {
			String sCurrentLine; 
			while ((sCurrentLine = br.readLine()) != null) {
				sb.append(sCurrentLine).append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		IntStream
		   .rangeClosed(2, 1000)
		   .forEach(i -> {
			   try {
			     PrintWriter out = new PrintWriter(String.format("Class%d.java", i));
			     out.printf(sb.toString(), i, i);
			     out.close();
			   } catch (Exception e) {
				e.printStackTrace();
			   }
		   });
		
		

	}

}
