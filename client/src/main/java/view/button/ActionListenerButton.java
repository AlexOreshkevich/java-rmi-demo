package view.button;

import javax.swing.JButton;
import java.awt.event.ActionListener;

public class ActionListenerButton extends JButton {

    private ActionListener actionListener;

    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public void init() {
        this.addActionListener(actionListener);
    }
}
