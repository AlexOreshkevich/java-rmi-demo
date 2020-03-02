package server;

import command.Command;
import command.CreateUserCommand;
import command.CreateUserCommandImpl;
import command.RemoveUserCommand;
import command.RemoveUserCommandImpl;
import command.ServerCommandManager;
import command.ServerCommandManagerImpl;
import dao.UserDao;
import dao.jdbc.UserJdbcDao;
import model.User;
import model.UserTO;
import support.jdbc.ConnectionPool;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

    public static final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static final ConnectionPool conPool = new ConnectionPool();
    public static Long currentUserId = 0L;
    public static List<UserTO> users;
    public static UserDao userDao = new UserJdbcDao();

    static {
        users = new ArrayList<>();
        User user = new User();
        user.setId(++currentUserId);
        user.setName("Pavel");
        user.setPasswd("MyNewPasswordIs******");
        users.add(user);
    }

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, InterruptedException {
        Registry registry = LocateRegistry.createRegistry(2005);
        UserTO user = new User();
        Random random = new Random();
        Long id = random.nextLong();
        user.setId(id);
        user.setName("Name " + id);
        user.setPasswd("Passwd" + id);
        Remote remoteUser = UnicastRemoteObject.exportObject(user, 2005);
        registry.bind("user", remoteUser);

        ServerCommandManagerImpl scm = new ServerCommandManagerImpl();

        Map<Class, Command> commands = new HashMap<>();
        commands.put(RemoveUserCommand.class, new RemoveUserCommandImpl());
        commands.put(CreateUserCommand.class, new CreateUserCommandImpl());
        scm.setCommands(commands);

        Remote remoteServerCommandManager = UnicastRemoteObject.exportObject(scm, 2005);
        registry.bind(ServerCommandManager.class.getName(), remoteServerCommandManager);
    }
}
