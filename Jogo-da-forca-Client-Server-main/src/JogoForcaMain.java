import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.*;
public class JogoForcaMain {

    public static void main(String[] args) throws IOException {
        try {
            Socket cliente = new Socket("localhost", 6666);
            ObjectOutputStream saidaObjetos = new ObjectOutputStream(cliente.getOutputStream());
            PrintWriter saidaTexto = new PrintWriter(cliente.getOutputStream(), true);
            OperacoesForca forca = new OperacoesForca();
            forca.novoJogo();
            String forcaString = forca.toString();
            saidaObjetos.writeObject(forcaString);

            // Enviar o resultado do jogo para o servidor
            saidaObjetos.writeObject(forcaString);

            saidaObjetos.close();
            saidaTexto.close();
            cliente.close();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
