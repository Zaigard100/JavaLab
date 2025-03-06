import observers.AECESourse;
import observers.CIESourse;
import observers.OEVESourse;

import java.io.*;
import java.util.Scanner;

public class Task {
    PrintWriter pw;

    AECESourse a;
    CIESourse c;
    OEVESourse o;

    public Task(AECESourse aeses, CIESourse cies, OEVESourse oeves) {
        a = aeses;
        c = cies;
        o = oeves;
    }

    public void uberMegaDrive(String[] args) {
        System.out.println("StaRT");
        c.notifyObs(pw,("Console print:"+"StaRT"));
        int count = 0;
        a.notifyObs(pw,args);
        int M = 42;
        for (String arg : args) {
            int n = -1;
            try {
                n = Integer.parseInt(arg);
            } catch (NumberFormatException e) {
                System.out.println("\""+arg+"\""+" is not digit");
                c.notifyObs(pw,("Console print:"+"\""+arg+"\""+" is not digit"));
            }
            if (n > M) {
                System.out.println(n);
                c.notifyObs(pw,("Console print:"+n));
                count++;
            }
            o.notifyObs(pw,n);
        }
        if(count==0){
            System.out.println("Number greader "+ M +" not found");
            c.notifyObs(pw,("Console print:"+"Number greader "+ M +" not found"));
        }
    }

    public void run(){
        Scanner in = new Scanner(System.in);
        System.out.print("Input a file: ");
        String filePath = in.nextLine();
        in.close();
        File f = new File(filePath);
        String arguments;
        String fileOutput;
        if(f.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(f.getAbsolutePath()));
                try {
                    arguments = br.readLine();
                    fileOutput = br.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            File j = new File(fileOutput);
            if(!j.exists()){
                try {
                    f.createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                pw = new PrintWriter(j.getAbsolutePath());

                c.notifyObs(pw,("Console print:"+"Input a file: "));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            uberMegaDrive(arguments.split(" "));
            pw.close();
        }
    }

}
