package controller;

import lombok.Setter;
import model.User;
import service.UserService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutionException;

public class UserController {

    @Setter
    private UserService srv;

    public void updateUser(final User user) throws RemoteException, NotBoundException, ExecutionException, InterruptedException {
        srv.updateUser(user == null ? new User() : user);
    }

    public void removeUser(final Long id) throws RemoteException, NotBoundException, ExecutionException, InterruptedException {
        srv.removeUser(id);
    }
}
