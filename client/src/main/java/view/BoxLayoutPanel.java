package view;

import lombok.Setter;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Component;
import java.util.List;


public class BoxLayoutPanel extends JPanel {

    @Setter
    private List<Component> panelComponents;
    private int axis;

    public void setAxis(int axis) {
        this.axis = axis;
    }

    public void init() {
        setLayout(new BoxLayout(this, axis));

        for (Component component : panelComponents) {
            add(component);
        }
    }
}
