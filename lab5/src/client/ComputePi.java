package client;

import compute.ICompute;

import java.math.BigDecimal;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ComputePi {
    public static void main(String[] args) {
        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "ICompute";
            Registry registry = LocateRegistry.getRegistry(args[0]);
            ICompute compute = (ICompute) registry.lookup(name);
            Pi task = new Pi(args);
            String pi = compute.executeTask(task);
            System.out.println(pi);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
