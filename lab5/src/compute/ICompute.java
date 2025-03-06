package compute;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICompute extends Remote {
    <T> T executeTask(Task<T> task) throws RemoteException;
}
