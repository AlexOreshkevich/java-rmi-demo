package service;

import model.User;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface UserService {

    User getUser(final Long id);

    List<User> getUsers();

    void removeUser(final Long id) throws RemoteException, NotBoundException, ExecutionException, InterruptedException;

    void updateUser(final User user) throws RemoteException, NotBoundException, ExecutionException, InterruptedException;
}
