package command;

import model.User;
import model.UserTO;
import server.App;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CreateUserCommandImpl extends UnicastRemoteObject implements CreateUserCommand, Remote {

    public CreateUserCommandImpl() throws RemoteException {
    }

    @Override
    public UserTO execute(final UserTO user) throws RemoteException {
        try {
            user.setId(++App.currentUserId);

            System.out.println(App.users.size());
        } catch (RuntimeException ex) {
            throw new RemoteException(ex.getMessage(), ex);
        }
        return user;
    }
}
