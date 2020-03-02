package model;

import java.rmi.RemoteException;

public interface UserTO extends TransferObject {

    Long getId() throws RemoteException;

    void setId(final Long id) throws RemoteException;

    String getName() throws RemoteException;

    void setName(final String name) throws RemoteException;

    String getPasswd() throws RemoteException;

    void setPasswd(final String passwd) throws RemoteException;
}
