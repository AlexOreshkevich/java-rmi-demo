package command;

import lombok.Setter;
import model.TransferObject;
import server.App;

import java.rmi.RemoteException;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ServerCommandManagerImpl implements ServerCommandManager {

    @Setter
    private Map<Class, Command> commands;

    @Override
    public <T, D extends TransferObject> D execute(final Class<T> clazz, D obj) throws RemoteException, ExecutionException, InterruptedException {
        System.out.println(commands.get(clazz));
        Future<D> future = App.executorService.submit(new Worker<D>(commands.get(clazz), obj));
        return future.get();
    }
}
