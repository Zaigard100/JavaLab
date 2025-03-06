package server;

import compute.ICompute;
import compute.Task;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ComputeEngine implements ICompute {

    public ComputeEngine(){
        super();
    }

    @Override
    public <T> T executeTask(Task<T> task) throws RemoteException {
        return task.execute();
    }

    public static void main(String[] args){
        if(System.getSecurityManager() == null){
            System.setSecurityManager(new SecurityManager());
        }
        try{
            String name = "ICompute";
            ICompute compute = new ComputeEngine();
            ICompute stub = (ICompute) UnicastRemoteObject.exportObject(compute, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);
        }catch (Exception e){
            e.printStackTrace();
        }
        int i = 0;
        while(true){
            System.out.print(i+" ");
            i++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
