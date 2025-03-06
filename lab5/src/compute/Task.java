package compute;

import java.rmi.RemoteException;

public interface Task<T> {
    T execute();
}
