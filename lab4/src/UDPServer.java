import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.HashMap;

public class UDPServer extends Thread{
    HashMap<String,Service> serviceHashMap;

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
        try( DatagramSocket socket = new DatagramSocket(port) ){

            try( PrintWriter log = new PrintWriter(new BufferedWriter(new FileWriter(logFile,true))) ){
                while(true){
                    byte[] buf = new byte[LENGTH_PACKET];
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    socket.receive(packet);
                    String message = new String(packet.getData(),0,packet.getLength()).trim();
                    InetAddress address = packet.getAddress();
                    int port = packet.getPort();
                    String key = address.getHostAddress()+":"+port;

                    log.println("["+ LocalDateTime.now()+"] "+key+": "+message);
                    log.flush();

                    if(message.equalsIgnoreCase("exit")){
                        serviceHashMap.remove(key);
                        System.out.println(key+" disconnected");
                    }else if(message.equalsIgnoreCase("connect")){
                        serviceHashMap.put(key,new Service());
                        sendMessage(socket,key+" conetsion's successful",address,port);
                        System.out.println(key+" conetsion's successful");
                    }else{
                        Service currentSession = serviceHashMap.getOrDefault(key, new Service());
                        sendMessage(socket,currentSession.exec(message),address,port);
                    }
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private void sendMessage(DatagramSocket socket, String message,InetAddress address,int port) throws IOException {
        byte[] response = message.getBytes();
        DatagramPacket responsePacket = new DatagramPacket(response, response.length, address, port);
        socket.send(responsePacket);
    }
}
