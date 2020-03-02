package service;

import command.CreateUserCommand;
import command.RemoveUserCommand;
import lombok.Setter;
import model.User;
import support.CommandManager;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class UserServiceImpl implements UserService {

    @Setter
    private List<User> userList;

    @Setter
    private CommandManager commandManager;

    @Setter
    private Random random;

    private static Long currentId = 1L;

    @Override
    public User getUser(final Long id) {
        return userList.stream().filter(val -> val.getId().equals(id)).findFirst().orElse(new User());
    }

    @Override
    public List<User> getUsers() {
        return userList;
    }

    @Override
    public void removeUser(final Long id) throws RemoteException, NotBoundException, ExecutionException, InterruptedException {
        User removedUser = commandManager.startCommand(RemoveUserCommand.class, new User(id));
        System.out.println(removedUser);
        System.out.println(userList);
        userList.remove(removedUser);
    }

    @Override
    public void updateUser(final User user) throws RemoteException, NotBoundException, ExecutionException, InterruptedException {
        User tempUser = getUser(user.getId());
        if (tempUser.getId() == null) {
            tempUser.setName(String.format("UserName%02d", random.nextInt(99)));
            tempUser.setPasswd(String.format("UserPasswd%04d", random.nextInt(9999)));
            tempUser = commandManager.startCommand(CreateUserCommand.class, tempUser);
            userList.add(tempUser);
        } else {
            tempUser.setName(user.getName());
            tempUser.setPasswd(user.getPasswd());
        }
    }
}
