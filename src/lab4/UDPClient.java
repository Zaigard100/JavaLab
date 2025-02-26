package lab4;

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
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
        try( DatagramSocket socket = new DatagramSocket() ){
            socket.setSoTimeout(5000);
            try (PrintWriter log = new PrintWriter(new FileWriter(logFile, true))) {
                connect(socket, host, port, log);
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                while (true) {
                    System.out.print("Enter message (exit to quit): ");
                    String input = in.readLine();
                    if ("exit".equalsIgnoreCase(input)){
                        byte[] buffer = "exit".getBytes();
                        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(host), port);
                        socket.send(packet);
                        break;
                    }
                    byte[] buffer = input.getBytes();
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(host), port);
                    socket.send(packet);

                    byte[] response = new byte[LENGTH_PACKET];
                    DatagramPacket responsePacket = new DatagramPacket(response, response.length);
                    try {
                        socket.receive(responsePacket);
                        String res = new String(responsePacket.getData(), 0, responsePacket.getLength());
                        log.println("["+LocalDateTime.now() + "] Server: " + res);
                        log.flush();
                        System.out.println("Server: " + res);
                    } catch (SocketTimeoutException e) {
                        System.out.println("Timeout");
                    }
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void connect(DatagramSocket socket, String host, int port, PrintWriter log) throws IOException {
        byte[] buffer = "connect".getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(host), port);
        socket.send(packet);

        byte[] response = new byte[LENGTH_PACKET];
        DatagramPacket responsePacket = new DatagramPacket(response, response.length);
        try {
            socket.receive(responsePacket);
            String res = new String(responsePacket.getData(), 0, responsePacket.getLength());
            log.println("["+LocalDateTime.now() + "] Server: " + res);
            log.flush();
            System.out.println("Server: " + res);
        } catch (SocketTimeoutException e) {
            System.out.println("Timeout");
        }

    }

}
