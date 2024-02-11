import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.net.Socket;

public class RPSClient {

    BufferedReader in;
    PrintWriter ut;

    RPSClient(String name) {
        try {
            // localhost är alias för IP-adress för den lokala datorn d.v.s. den datorn 
            // du kör detta program (vilket i detta fall är samma dator som serverprogrammet körs på)
            Socket socket=new Socket("localhost",4713);
            in=new BufferedReader
                (new InputStreamReader(socket.getInputStream()));
            ut=new PrintWriter(socket.getOutputStream());
            ut.println(name);
            ut.flush();
            in.readLine();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void send(String input) {
        ut.println(input);
        ut.flush(); 
    }

    public String receive() {
        try {
            return in.readLine();
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }
}
