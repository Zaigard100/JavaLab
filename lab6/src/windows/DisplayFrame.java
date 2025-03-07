package windows;

import javax.swing.*;
import java.awt.*;

public class DisplayFrame extends JFrame {
    private JLabel displayLabel;

    public DisplayFrame() {
        setTitle("Демонстрационное окно");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Центрирование окна

        // Создаем метку для отображения текста
        displayLabel = new JLabel("Здесь будет информация", SwingConstants.CENTER);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        add(displayLabel, BorderLayout.CENTER);
    }

    // Метод для обновления текста в демонстрационном окне
    public void updateText(String text) {
        displayLabel.setText(text);
    }
}
