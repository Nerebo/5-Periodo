
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.util.Scanner;

public class Cliente implements Runnable {

    int type;
    NetworkInterface net;
    String message;
    String name;
    InetAddress iaddress;

    public Cliente(int type, InetAddress iaddress, NetworkInterface net, String messaString, String name) {
        this.type = type;
        this.net = net;
        this.message = messaString;
        this.name = name;
        this.iaddress = iaddress;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            int type = sc.nextInt();
            String msg = "";
            String nome = "";
            InetAddress ia = InetAddress.getByName("230.0.0.0");
            NetworkInterface ni = NetworkInterface.getByInetAddress(ia);

            Connect(type, ni, msg, nome, ia);
        } catch (Exception e) {

        }

    }

    @Override
    public void run() {
        try {
            if (type == 1) {
                Connect(1, net, message, name, iaddress);
            }
            if (type == 2) {
                Connect(2, net, message, name, iaddress);
            }

        } catch (Exception IOException) {
        }
    }

    public static void Connect(int type, NetworkInterface ni, String msg, String nome, InetAddress ia)
            throws IOException {
        MulticastSocket socket = new MulticastSocket(4323);
        InetSocketAddress grupo = new InetSocketAddress(ia, 4323);
        byte[] envio = new byte[1024];
        socket.joinGroup(grupo, ni);
        Scanner sc = new Scanner(System.in);

        if (nome.equals("")) {
            nome = sc.nextLine();
            envio = nome.getBytes();
            DatagramPacket pacote = new DatagramPacket(envio, envio.length, ia, 4323);
            socket.send(pacote);
            socket.leaveGroup(grupo, ni);
            socket.close();
        }
        if (type == 1) {
            grupo = new InetSocketAddress(ia, 4321);
            socket = new MulticastSocket(4321);
            socket.joinGroup(grupo, ni);
        } else if (type == 2) {
            grupo = new InetSocketAddress(ia, 4322);
            socket = new MulticastSocket(4322);
            socket.joinGroup(grupo, ni);
        }
        if (type == 3) {
            Thread a1 = new Thread(new Cliente(1, InetAddress.getByName("230.0.0.0"),
                    NetworkInterface.getByInetAddress(ia), msg, nome));
            Thread a2 = new Thread(new Cliente(2, InetAddress.getByName("230.0.0.0"),
                    NetworkInterface.getByInetAddress(ia), msg, nome));
            a1.start();
            a2.start();
        }

        if (type != 3) {
            while (!msg.contains("Servidor Encerrando")) {
                System.out.println("[Cliente] Esperando por mensagem Multicast...");
                byte[] buffer = new byte[1024];

                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                msg = new String(packet.getData());
                System.out.println("[Cliente] Mensagem recebida do Servidor: " + msg);

            }
            System.out.println("[Cliente] Conexao Encerrada!");
            socket.leaveGroup(grupo, ni);
            socket.close();
        }
    }
}
