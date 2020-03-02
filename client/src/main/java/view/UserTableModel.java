package view;

import controller.UserController;
import lombok.Setter;
import model.User;

import javax.swing.table.AbstractTableModel;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserTableModel extends AbstractTableModel {

    @Setter
    List<String> columns;
    @Setter
    List<User> usersList;
    @Setter
    private UserController controller;

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public String getColumnName(int column) {
        return columns.get(column);
    }

    @Override
    public int getRowCount() {
        return usersList.size();
    }

    @Override
    public void setValueAt(final Object value, final int rowIndex, final int columnIndex) {
        User user = usersList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                user.setId(Long.class.cast(value));
                break;
            case 1:
                user.setName(value.toString());
                break;
            case 2:
                user.setPasswd(value.toString());
                break;
        }
        try {
            controller.updateUser(user);
        } catch (RemoteException | NotBoundException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = usersList.get(rowIndex);
        Object value = null;
        switch (columnIndex) {
            case 0:
                value = user.getId();
                break;
            case 1:
                value = user.getName();
                break;
            case 2:
                value = user.getPasswd();
                break;
        }
        return value;
    }
}
