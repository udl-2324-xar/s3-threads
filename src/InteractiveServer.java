import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class InteractiveServer {
    private static final int port = 1234;

    public static void main(String[] args){
        try
        {
            ServerSocket ss = new ServerSocket (port);

            for (;;)
            {
                Socket s = ss.accept();
                System.err.println ("Connexió acceptada.");
                DataInputStream dis = new DataInputStream  (s.getInputStream());
                DataOutputStream dos = new DataOutputStream (s.getOutputStream());
                String str = "";
                String strUpper;

                while (!str.equals ("FI"))
                {
                    str = dis.readUTF();
                    System.out.println ("He rebut el missatge \"" + str + "\"");
                    strUpper = str.toUpperCase();
                    dos.writeUTF (strUpper);
                    dos.flush();
                }
                dis.close();
                dos.close();
                s.close();
                System.out.println ("Connexió tancada.");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
