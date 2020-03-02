package action;

import controller.UserController;
import lombok.Setter;
import view.button.ListTableActionListener;

import java.awt.event.ActionEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutionException;


public class DeleteButtonActionListener extends ListTableActionListener {

    @Setter
    private UserController controller;

    public void actionPerformed(ActionEvent e) {
        if (table.getSelectedRow() == -1) {
            return;
        }
        Long id = Long.class.cast(table.getValueAt(table.getSelectedRow(), 0));
        try {
            controller.removeUser(id);
        } catch (RemoteException | NotBoundException | ExecutionException | InterruptedException e1) {
            e1.printStackTrace();
        }
        table.revalidate();
    }
}