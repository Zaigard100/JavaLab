package windows.panels;

import windows.ControlFrame;
import world.Obj;

import javax.swing.*;
import java.awt.*;

public class EditPanel  extends JPanel {
    JTextField nameField = new JTextField(15);
    JComboBox<String> colorBox = new JComboBox<>(new String[]{"Red", "Green", "Blue", "Yellow", "Cyan"});
    JTextField speedField = new JTextField(5);
    JTextField parameterField = new JTextField(255);
    JTextField xField = new JTextField(5);
    JTextField yField = new JTextField(5);
    JTextField wField = new JTextField(5);
    JTextField hField = new JTextField(5);
    JButton deleteBtn = new JButton("Delete");
    JButton saveBtn = new JButton("Save");

    ControlFrame mainFrame;

    Obj curent;

    public EditPanel(ControlFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));

        addFieldPanel(fieldsPanel, "Name:", nameField);
        addFieldPanel(fieldsPanel, "Color:", colorBox);
        addFieldPanel(fieldsPanel, "Speed:", speedField);
        addFieldPanel(fieldsPanel, "Parameter:", parameterField);

        // Панель с координатами и размерами
        JPanel coordPanel = new JPanel(new GridLayout(1, 4, 5, 5));
        coordPanel.add(createLabeledField("X:", xField));
        coordPanel.add(createLabeledField("Y:", yField));
        coordPanel.add(createLabeledField("Width:", wField));
        coordPanel.add(createLabeledField("Height:", hField));
        fieldsPanel.add(coordPanel);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(deleteBtn);
        buttonPanel.add(saveBtn);

        add(fieldsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        deleteBtn.addActionListener(e -> deleteObject());
        saveBtn.addActionListener(e -> saveObject());
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

    private void deleteObject() {
        // Логика удаления объекта
        curent.clear();
        curent.init();
        mainFrame.updateObjectList();
    }

    private void saveObject() {
        // Логика сохранения изменений
        curent.setName(nameField.getText());
        curent.setColor(colorBox.getSelectedItem().toString());
        curent.setSpeed(Integer.parseInt(speedField.getText()));
        curent.setParameter(parameterField.getText());
        curent.setX(Integer.parseInt(xField.getText()));
        curent.setY(Integer.parseInt(yField.getText()));
        curent.setW(Integer.parseInt(wField.getText()));
        curent.setH(Integer.parseInt(hField.getText()));
        curent.init();
        mainFrame.updateObjectList();
    }

    public void setCurentObj(Obj curent) {
        this.curent = curent;
        nameField.setText(curent.getName());
        colorBox.setSelectedItem(curent.getColor());
        speedField.setText(String.valueOf(curent.getSpeed()));
        parameterField.setText(String.valueOf(curent.getParameter()));
        xField.setText(String.valueOf(curent.getX()));
        yField.setText(String.valueOf(curent.getY()));
        wField.setText(String.valueOf(curent.getW()));
        hField.setText(String.valueOf(curent.getH()));
    }
}
