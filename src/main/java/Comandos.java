import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Comandos {
    public static final String COMMAND1 = "bash";
    public static final String COMMAND2 = "-c";

    public void crearProceso() throws IOException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce el comando que quieres ejecutar");
        String leer = teclado.nextLine();
        ProcessBuilder pb = new ProcessBuilder(COMMAND1, COMMAND2, leer);
        Process process = pb.start();
        try (InputStream stdout = process.getInputStream();) {

            try (BufferedReader br = new BufferedReader(new InputStreamReader(stdout));) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                }
                br.close();
                teclado.close();
                process.waitFor();
                System.out.println("valor de salida " + process.exitValue());

            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
    }
}
