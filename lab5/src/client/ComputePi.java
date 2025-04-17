package client;

import compute.ICompute;
import compute.Task;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

public class ComputePi {
    public static void main(String[] args) {
        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "ICompute";
            Registry registry = LocateRegistry.getRegistry(args[0]);
            ICompute compute = (ICompute) registry.lookup(name);
            List<Integer> nums = new ArrayList<>();
            for (int i = 1; i < args.length; i++) {
                nums.add(Integer.parseInt(args[i]));
            }
            Task<String> task = new Pi(nums);
            String pi = compute.executeTask(task);
            System.out.println(pi);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
