package engine;

import compute.Compute;
import compute.Task;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class ComputeEngine implements Compute {

  @Override
  public <T> T executeTask(Task<T> task) throws RemoteException {
    return task.execute();
  }

  public static void main(String[] args) {

    System.setProperty("java.security.policy", "file:///Users/neo/Projects/java-rmi-demo/src/main/resources/security.policy");

    // Create and install a security manager
    //
    // If an RMI program does not install a security manager, RMI will not download classes (other than from the local class path)
    // for objects received as arguments or return values of remote method invocations on anonymous port (0)
    if (System.getSecurityManager() == null) {
      System.setSecurityManager(new SecurityManager());
    }

    try {
      //  exports the supplied remote object so that
      //  it can receive invocations of its remote methods from remote clients
      Compute stub = (Compute) UnicastRemoteObject.exportObject(new ComputeEngine(), 5269);

      // rebind() invocation makes a remote call to the RMI registry on the local host
      String name = "Compute";
      LocateRegistry.getRegistry().rebind(name, stub);

      System.out.println("ComputeEngine bounding complete");

    } catch (Exception e) {
      System.err.println("ComputeEngine exception:");
      e.printStackTrace();
    }
  }
}
