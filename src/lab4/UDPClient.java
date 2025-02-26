package lab4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class UDPClient{
    public static final int LENGTH_PACKET = 60;
    private String host,logFile;
    private int port;
    public String name;
    public UDPClient(String host, int port, String logFile,String name){
        this.host = host;
        this.port = port;
        this.logFile = logFile;
        this.name = name;
    }
    public void run() {
        try{
            byte data[] = ("connect:"+name).getBytes();
            InetAddress addr = InetAddress.getByName(host);
            DatagramSocket socket = new DatagramSocket();
            DatagramPacket packet = new DatagramPacket(data, data.length, addr, port);
            socket.send(packet);

            byte data2[];
            data2 = new byte[LENGTH_PACKET];
            packet = new DatagramPacket(data2, data2.length);
            socket.receive(packet);
            System.out.println((new String(packet.getData())).trim());

            System.out.println("Сообщение на подключение отправлено...");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String command = "";
            while (true){
                System.out.print("Введите  команду: ");
                command = br.readLine();
                if(command.equals("exit")){
                    data = (name+":exit").getBytes();
                    packet = new DatagramPacket(data, data.length, addr, port);
                    socket.send(packet);

                    packet = new DatagramPacket(data2, data2.length);
                    socket.receive(packet);
                    System.out.println((new String(packet.getData())).trim());
                    break;
                }
                data = (name+":command:"+command).getBytes();
                packet = new DatagramPacket(data, data.length, addr, port);
                socket.send(packet);

                packet = new DatagramPacket(data2, data2.length);
                socket.receive(packet);
                System.out.println((new String(packet.getData())).trim());
                packet.setData(new byte[]{0});
            }
            socket.close();
            //in.close();
        }catch(SocketException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
