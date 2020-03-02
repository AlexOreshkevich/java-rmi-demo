package view.button;

import lombok.Setter;
import model.User;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.List;

public abstract class ListTableActionListener implements ActionListener {

    @Setter
    protected JTable table;
    @Setter
    protected List<User> list;
}
