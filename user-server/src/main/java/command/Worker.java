package command;

import model.TransferObject;

import java.util.concurrent.Callable;

public class Worker<T extends TransferObject> implements Callable<T> {

    private Command<T> command;
    private T transferObject;

    public Worker(final Command<T> command, final T transferObject) {
        this.command = command;
        this.transferObject = transferObject;
    }

    @Override
    public T call() throws Exception {
        return command.execute(transferObject);
    }
}
