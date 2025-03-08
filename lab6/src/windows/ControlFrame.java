package windows;

import windows.panels.CreatePanel;
import windows.panels.EditPanel;
import world.Obj;
import world.ObjType;
import world.World;

import javax.swing.*;
import java.awt.*;

public class ControlFrame extends JFrame {
    private JComboBox<Obj> objectList;
    private JPanel cardPanel;
    private CreatePanel creationPanel;
    private EditPanel editPanel;
    private World world;

    int currentCard = 0;

    public ControlFrame(World world) {
        this.world = world;
        setTitle("Управляющее окно");
        setSize(world.getW(), world.getH());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        objectList = new JComboBox<>(world.getObjs());
        objectList.addActionListener(e -> showPanelForSelected());

        cardPanel = new JPanel(new CardLayout());
        creationPanel = new CreatePanel(this);
        editPanel = new EditPanel(this);

        cardPanel.add(creationPanel, "CREATE");
        cardPanel.add(editPanel, "EDIT");

        add(objectList, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);

    }

    private void showPanelForSelected() {
        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
        Obj selectedObj = (Obj) objectList.getSelectedItem();
        if(selectedObj.getType() == ObjType.EMPTY) {
            cardLayout.show(cardPanel, "CREATE");
            creationPanel.setCurent(selectedObj);
        }else {
            cardLayout.show(cardPanel, "EDIT");
            editPanel.setCurentObj(selectedObj);
        }
    }

    public void updateObjectList() {

    }

}
