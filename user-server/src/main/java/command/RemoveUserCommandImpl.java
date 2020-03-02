package command;

import model.UserTO;
import server.App;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoveUserCommandImpl extends UnicastRemoteObject implements RemoveUserCommand {

    public RemoveUserCommandImpl() throws RemoteException {
    }

    @Override
    public UserTO execute(final UserTO userTO) throws RemoteException {
        UserTO removedUser = null;
        for (UserTO user : App.users) {
            if (user.getId().equals(userTO.getId())) {
                removedUser = user;
                break;
            }
        }
        App.users.remove(removedUser);
        System.out.println(App.users.size());
        return removedUser;
    }
}
