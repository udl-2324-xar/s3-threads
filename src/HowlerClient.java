import java.io.*;
import java.net.Socket;

public class HowlerClient {

    private static final int port = 1234;
    private static String host = "127.0.0.1";

    public static void main(String[] args) {
        if (args.length > 0){
            host = args[0];
        }
        System.out.println("Hello world!");
        try {
            InputStream consola = System.in;
//            DataInputStream consolaDIS = new DataInputStream(consola);

//            incorrecte, no retorna al fer el salt de línia
//            String entrada = consolaDIS.readUTF();

//            incorrecte, deprecated, no transforma bé bytes a chars
//            String entrada2 = consolaDIS.readLine();

            BufferedReader d = new BufferedReader(new InputStreamReader(consola));

            String entrada = "";
            String retorn;
            Socket socket = new Socket(host, port);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            System.out.println("Client: Comença a parlar amb el client.");
            System.out.println("Quan vulguis acabar, escriu FI");
            while (!entrada.equals("FI")){
                entrada = d.readLine();
                dos.writeUTF(entrada);
                dos.flush();
                retorn = dis.readUTF();
                System.out.print("He rebut del server: ");
                System.out.println(retorn);
            }
            System.out.println("Client: he acabat");

//            tancant els canals
            dos.close();
            dis.close();
            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}