package windows.panels;

import windows.ControlFrame;
import world.Obj;
import world.ObjType;

import javax.swing.*;
import java.awt.*;


public class CreatePanel extends JPanel {

    JTextField nameField = new JTextField(15);
    JComboBox<String> colorBox = new JComboBox<>(new String[]{"Red", "Green", "Blue", "Yellow", "Cyan"});
    JComboBox<ObjType> typeBox = new JComboBox<>(new ObjType[]{ObjType.IMG, ObjType.TEXT});
    JComboBox<Integer> speedBox = new JComboBox<>(new Integer[]{1,2,3,4,5,6});
    JTextField parameterField = new JTextField(255);
    JTextField xField = new JTextField(5);
    JTextField yField = new JTextField(5);
    JTextField wField = new JTextField(5);
    JTextField hField = new JTextField(5);
    JButton createBtn = new JButton("Create");

    ControlFrame mainFrame;

    Obj curent;

    public CreatePanel(ControlFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));

        addFieldPanel(fieldsPanel, "Name:", nameField);
        addFieldPanel(fieldsPanel, "Color:", colorBox);
        addFieldPanel(fieldsPanel, "ObjType:", typeBox);
        addFieldPanel(fieldsPanel, "Speed:", speedBox);
        addFieldPanel(fieldsPanel, "Parameter:", parameterField);

        // Панель с координатами и размерами
        JPanel coordPanel = new JPanel(new GridLayout(1, 4, 5, 5));
        coordPanel.add(createLabeledField("X:", xField));
        coordPanel.add(createLabeledField("Y:", yField));
        coordPanel.add(createLabeledField("Width:", wField));
        coordPanel.add(createLabeledField("Height:", hField));
        fieldsPanel.add(coordPanel);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(createBtn);

        add(fieldsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        createBtn.addActionListener(e -> createObject());
    }

    private void addFieldPanel(JPanel container, String labelText, JComponent field) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        panel.add(new JLabel(labelText), BorderLayout.WEST);
        panel.add(field, BorderLayout.CENTER);

        container.add(panel);
    }
    private JPanel createLabeledField(String label, JTextField field) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(label), BorderLayout.WEST);
        panel.add(field, BorderLayout.CENTER);
        return panel;
    }
    private void createObject() {
        // Логика создания объекта из введенных данных
        curent.setName(nameField.getText());
        curent.setColor(colorBox.getSelectedItem().toString());
        curent.setType((ObjType) typeBox.getSelectedItem());
        curent.setSpeed(speedBox.getSelectedIndex());
        curent.setParameter(parameterField.getText());
        curent.setX(Integer.parseInt(xField.getText()));
        curent.setY(Integer.parseInt(yField.getText()));
        curent.setW(Integer.parseInt(wField.getText()));
        curent.setH(Integer.parseInt(hField.getText()));
        curent.init();
        mainFrame.updateObjectList();
    }

    public void setCurent(Obj curent) {
        nameField.setText("");
        colorBox.setSelectedIndex(0);
        typeBox.setSelectedIndex(0);
        speedBox.setSelectedIndex(0);
        parameterField.setText("");
        xField.setText("");
        yField.setText("");
        wField.setText("");
        hField.setText("");
        this.curent = curent;
    }
}
