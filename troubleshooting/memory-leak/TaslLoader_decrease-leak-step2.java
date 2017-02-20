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
    Reader r = new InputStreamReader(in);
    char[] buf = new char[3];         
    int i=0;
    List<char[]> items = new ArrayList<>();
    while (i != -1) {
      i = r.read(buf);      
      if (i==-1) {
        break;
      }
      items.add(buf);
      char n;
      do
      {
        n = (char)r.read();
      }
      while (n != '\n' && n != -1);
      i = n==-1 ? -1 : i;      
    }    
    r.close();
    
    return ;
  }

}
