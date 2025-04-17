package server;

import compute.ICompute;
import compute.Task;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ComputeEngine extends UnicastRemoteObject implements ICompute {

    public ComputeEngine() throws RemoteException {
        super();
    }

    @Override
    public <T> T executeTask(Task<T> task) throws RemoteException {
        System.out.println("Выполняю задачу: " + task.getClass().getSimpleName());
        return task.execute();
    }

    public static void main(String[] args){
        if(System.getSecurityManager() == null){
            System.setSecurityManager(new SecurityManager());
        }
        try{
            String name = "ICompute";
            ICompute compute = new ComputeEngine();
            //ICompute stub = (ICompute) UnicastRemoteObject.exportObject(compute, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, compute);
            System.out.println("Сервер готов принимать задачи");
        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
