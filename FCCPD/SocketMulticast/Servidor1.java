import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Servidor1 implements Runnable {

    ArrayList<String> lista_nomes = new ArrayList<String>();

    public static void main(String[] args) throws IOException, InterruptedException {
        Thread servidor = new Thread(new Servidor1());
        servidor.start();

        MulticastSocket socket = new MulticastSocket();
        InetAddress grupo = InetAddress.getByName("230.0.0.0");
        byte[] envio = new byte[1024];

        String mensagem = "";
        Scanner sc = new Scanner(System.in);

        String val = "";

        while (!val.equals("encerrar")) {
            System.out.println("[SERVIDOR] Insira a próxima mensagem: ");
            mensagem = sc.nextLine();
            val = mensagem;

            if (mensagem.equals("encerrar")) {
                mensagem = "Servidor Encerrando";
                envio = mensagem.getBytes();
            } else {
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                mensagem = LocalDateTime.now().format(myFormatObj) + " " + "Avisos Gerais:" + " " + val;
                envio = mensagem.getBytes();
            }
            DatagramPacket pacote = new DatagramPacket(envio, envio.length, grupo, 4321);
            socket.send(pacote);
        }
        socket.close();
        System.exit(0);
    }

    @Override
    public void run() {
        try {
            String msg = "";
            MulticastSocket socket = new MulticastSocket(4323);
            InetAddress ia = InetAddress.getByName("230.0.0.0");
            InetSocketAddress grupo = new InetSocketAddress(ia, 4323);
            NetworkInterface ni = NetworkInterface.getByInetAddress(ia);

            socket.joinGroup(grupo, ni);

            while (!msg.contains("Servidor Encerrado")) {
                byte[] buffer = new byte[1024];

                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                msg = new String(packet.getData());
                System.out.println("[SERVIDOR] Cliente cadastrado: " + msg);
                lista_nomes.add(msg);
                System.out.println(lista_nomes);
            }
            System.out.println("[SERVIDOR] Conexao Encerrada!");
            socket.leaveGroup(grupo, ni);
            socket.close();

        } catch (

        IOException e) {

        }

    }
}