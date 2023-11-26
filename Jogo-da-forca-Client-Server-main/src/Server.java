import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket servidorSocket = new ServerSocket(6666);

        while (true) {
            Socket clienteSocket = servidorSocket.accept();

            // Cria um ObjectInputStream para receber objetos
            ObjectInputStream entradaObjetos = new ObjectInputStream(clienteSocket.getInputStream());

            // Cria um BufferedReader para receber mensagens de texto
            BufferedReader entradaTexto = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));

            // Receber um objeto do cliente (a string de resultado)
            String resultadoDoJogo = (String) entradaObjetos.readObject();
            System.out.println("Resultado do cliente: " + resultadoDoJogo);

            entradaObjetos.close();
            entradaTexto.close();
            clienteSocket.close();
        }
    }
}
