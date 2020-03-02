package action;

import controller.UserController;
import lombok.Setter;
import view.button.ListTableActionListener;

import java.awt.event.ActionEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutionException;

public class AddNewButtonActionListener extends ListTableActionListener {

    @Setter
    private UserController controller;

    public void actionPerformed(ActionEvent e) {
        try {
            controller.updateUser(null);
        } catch (RemoteException | NotBoundException | InterruptedException | ExecutionException e1) {
            e1.printStackTrace();
        }
        table.revalidate();
    }
}
