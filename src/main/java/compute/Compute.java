package compute;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * the Compute interface identifies itself as an interface whose methods can be invoked from another Java virtual machine. Any object that implements
 * this interface can be a remote object.
 */
public interface Compute extends Remote {

  /**
   * Remote method
   *
   * @param task
   * @param <T> the result type of the task's computation
   * @return
   * @throws RemoteException when either a communication failure or a protocol error has occurred
   */
  <T> T executeTask(Task<T> task) throws RemoteException;
}
