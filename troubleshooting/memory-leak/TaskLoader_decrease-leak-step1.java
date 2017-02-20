package ts.task5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaslLoader {

  public static void main(String[] args) throws IOException {
    System.out.println("Troubleshooting: Task #5 - Memory leaks");
    InputStream in = new FileInputStream(new File(args[0]));         
    List<char[]> list = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new InputStreamReader(in));    
    String line = null;    
    while ((line = reader.readLine()) != null) {           
      list.add(line.substring(0, 3).toCharArray());
    }    
    reader.close();
    
    return ;
  }

}
