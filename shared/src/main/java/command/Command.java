package command;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Command<T> extends Remote, Serializable {

    T execute(T obj) throws RemoteException;
}
