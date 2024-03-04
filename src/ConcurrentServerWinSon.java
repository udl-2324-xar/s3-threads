import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ConcurrentServerWinSon
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
                Thread t = new Thread (new Server (s, true), "Servidor-" + (++id));
                t.start();
                t.join();
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
        private Boolean creaFill;

        public Server (Socket s, Boolean creaFill)
        {
            this.s = s;
            this.creaFill = creaFill;
        }

        public void run()
        {
            try
            {
                String name = Thread.currentThread().getName();
                System.out.println (name + ": Connexió acceptada.");
                if (this.creaFill){
                    Thread fill = new Thread (new Server (s, false), "Fill de " + name);
                    fill.start();
                    return;
                }
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
