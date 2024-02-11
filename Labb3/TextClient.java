import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.net.Socket;

public class TextClient {
    public static void main(String[] args) {
        try {
            // localhost är alias för IP-adress för den lokala datorn d.v.s. den datorn 
            // du kör detta program (vilket i detta fall är samma dator som serverprogrammet körs på)
            Socket socket=new Socket("localhost",4713);
            BufferedReader in=new BufferedReader
                (new InputStreamReader(socket.getInputStream()));
            PrintWriter ut=new PrintWriter(socket.getOutputStream());
            BufferedReader keyboardInput=new BufferedReader
                (new InputStreamReader(System.in));
            while (true) {
                String input = keyboardInput.readLine();
                ut.println(input);
                ut.flush();
                System.out.println(in.readLine());
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
