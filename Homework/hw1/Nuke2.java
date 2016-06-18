
import java.io.*;

public class Nuke2
{
   public static void main(String[] arg) throws Exception {
       
       BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
       
       System.out.print("Please enter a string: ");
       System.out.flush();
       String inputLine = keyboard.readLine();
       
       String result = inputLine.substring(0, 1) + inputLine.substring(2);
       System.out.println(result);
   }
}
