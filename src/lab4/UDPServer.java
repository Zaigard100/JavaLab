package lab4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;

public class UDPServer extends Thread{
    HashMap<String,Client> serviceHashMap;

    public static final int LENGTH_PACKET = 30;
    private String host,logFile;
    private int port;
    public UDPServer(String host,int port,String logFile){
        this.host = host;
        this.port = port;
        this.logFile = logFile;
        serviceHashMap = new HashMap<>();
    }
    public  void run() {
        DatagramSocket servSocket = null;
        DatagramPacket datagram;
        InetAddress clientAddr;
        int clientPort;
        byte[] data;
        try{
            servSocket = new DatagramSocket(port);
        }catch(SocketException e){
            System.err.println("Не удаётся открыть сокет : " + e.toString());
        }

        while(true){
            try{
            data = new byte[LENGTH_PACKET];
            datagram = new DatagramPacket(data, data.length);
            servSocket.receive(datagram);
            String textData = new String(datagram.getData()).trim();
            String[] spl = textData.split(":");
            Client client;
            if(spl.length<2) continue;
            if(spl[0].equals("connect")&&spl.length==2){
                client = new Client(spl[1],datagram.getAddress(),datagram.getPort());
                serviceHashMap.put(spl[1],client);
                System.out.println(client.name+":"+spl[1]+" conetsion's successful");
                datagram.setData(("server: "+spl[1]+" conetsion's successful").getBytes());
                data = ("server: "+spl[1]+" conetsion's successful").getBytes();
                datagram = new DatagramPacket(data, data.length, client.getAddress(), client.getPort());
            }else{
                client = serviceHashMap.get(spl[0]);
                if(spl[1].equals("command")&&spl.length==3){
                    String r = client.service.exec(spl[2]);
                    System.out.println(client.name+":"+r);
                    datagram.setData(("server: "+ r).getBytes());
                    data = ("server: "+ r).getBytes();
                    datagram = new DatagramPacket(data, data.length, client.getAddress(), client.getPort());
                }
                if(spl[1].equals("exit")&&spl.length==2){
                    serviceHashMap.remove(spl[0]);
                    System.out.println(client.name+" disconect");
                    datagram.setData(("server: "+ "disconnect").getBytes());
                    data = ("server: "+ "disconnect").getBytes();
                    datagram = new DatagramPacket(data, data.length, client.getAddress(), client.getPort());
                }
            }
            servSocket.send(datagram);
            datagram.setData(new byte[]{0});
        }catch(IOException e){
                System.err.println("io исключение : " + e.toString());
            }
        }
    }

    private class Client{
        String name;
        InetAddress address;
        int port;
        Service service;

        public Client(String name, InetAddress address, int port) {
            this.name = name;
            this.address = address;
            this.port = port;
            service = new Service();
        }

        public boolean eq(InetAddress addr,int port) {
            return port == this.port && addr.equals(this.address);
        }

        public void setName(String name) {
            this.name = name;
        }

        public InetAddress getAddress() {
            return address;
        }

        public void setAddress(InetAddress address) {
            this.address = address;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

    }

}
