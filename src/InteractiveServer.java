import java.io.IOException;
import java.net.ServerSocket;

public class InteractiveServer {
    private static final int port = 1234;

    public static void main(String[] args){
        try {
            ServerSocket ss = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
