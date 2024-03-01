import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ConcurrentServer
{
    private static final int port = 1234;

    public static void main (String[] args)
    {
        int id = 0;
        try
        {
            ServerSocket ss = new ServerSocket (port);

            for (;;)
            {
                Socket s = ss.accept();
                Thread t = new Thread (new Server (s), "Servidor-" + (++id));
                t.start();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static class Server implements Runnable
    {
        private Socket s;

        public Server (Socket s)
        {
            this.s = s;
        }

        public void run()
        {
            try
            {
                String name = Thread.currentThread().getName();
                System.out.println (name + ": Connexió acceptada.");
                DataInputStream  dis = new DataInputStream  (s.getInputStream());
                DataOutputStream dos = new DataOutputStream (s.getOutputStream());
                String str = "";
                String strUpper;

                while (!str.equals ("FI"))
                {
                    str = dis.readUTF();
                    System.out.println (name + ": He rebut el missatge \"" + str + "\"");
                    strUpper = str.toUpperCase();
                    dos.writeUTF (strUpper);
                    dos.flush();
                }
                dis.close();
                dos.close();
                s.close();
                System.out.println (name + ": Connexió tancada.");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

}
