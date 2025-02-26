package lab4;

import java.io.*;
import java.util.Scanner;

public class Main {
    static boolean clientStart = false;
    static boolean serverStart = false;
    static String serverHost,clientHost,serverPort,clientPort,serverLog,clientLog;
    static String configFile = "C:\\Users\\zaiga\\IdeaProjects\\untitled2\\src\\lab4\\config";
    static String clientName = "Unnumed"+Double.hashCode(Math.random());
    public static void main(String[] arg){
        parseSetting(arg);
        try {
            parseConfig(configFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        consoleConfig();
        if(serverStart) {
            UDPServer server = new UDPServer(serverHost,Integer.parseInt(serverPort),serverLog);
            server.start();
        }
        if(clientStart) {
            UDPClient client = new UDPClient(clientHost,Integer.parseInt(clientPort),clientLog,clientName);
            client.run();
        }
    }

    static void parseSetting(String[] arg){
        for(int i = 0;i<arg.length;i++){
            switch (arg[i]){
                case "-server","-s":
                    serverStart = true;
                    break;
                case "-client","-c":
                    clientStart = true;
                    break;
                case "-test","-t":
                    serviceTest();
                    break;
                case "-name","-n":
                    clientName = arg[i+1];
                    break;
                case "-serverHost","-sH":
                    if(serverHost==null) serverHost = arg[i+1];
                    break;
                case "-clientHost","-cH":
                    if(clientHost==null) clientHost = arg[i+1];
                    break;
                case "-serverPort","-sP":
                    if(serverPort==null) serverPort = arg[i+1];
                    break;
                case "-clientPort","-cP":
                    if(clientPort==null) clientPort = arg[i+1];
                    break;
                case "-serverLog","-sL":
                    if(serverLog==null) serverLog = arg[i+1];
                    break;
                case "-clientLog","-cL":
                    if(clientLog==null) clientLog = arg[i+1];
                    break;
            }
        }
    }
    static void parseConfig(String configFile) throws IOException {
        File file = new File(configFile);
        StringBuilder args = new StringBuilder();
        String s;
        BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
        while ((s = br.readLine())!=null){
            args.append(s).append(" ");
        }
        parseSetting(args.toString().split(" "));
    }
    static void consoleConfig(){
        Scanner in = new Scanner(System.in);
        if(serverHost==null&&serverStart) {
            System.out.println("Server Host: ");
            serverHost = in.nextLine();
        }
        if(clientHost==null&&clientStart){
            System.out.println("Client Host: ");
            clientHost = in.nextLine();
        }
        if(serverPort==null&&serverStart){
            System.out.println("Server Port: ");
            serverPort = in.nextLine();
        }
        if(clientPort==null&&clientStart) {
            System.out.println("Client Port: ");
            clientPort = in.nextLine();
        }
        if(serverLog==null&&serverStart) {
            System.out.println("Server Log File: ");
            serverLog = in.nextLine();
        }
        if(clientLog==null&&clientStart) {
            System.out.println("Client Log File: ");
            serverLog = in.nextLine();
        }
        //in.close();
    }
    static void serviceTest(){
        Service service = new Service();
        String ret = "";
        String c;
        Scanner in = new Scanner(System.in);
        while (true){
            c = in.nextLine();
            System.out.println(service.exec(c));
        }
    }
}


