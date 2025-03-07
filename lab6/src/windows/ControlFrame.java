package windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlFrame extends JFrame {
    private DisplayFrame displayFrame;

    static int objCount = 7;

    public ControlFrame(DisplayFrame displayFrame) {
        this.displayFrame = displayFrame;

        setTitle("Управляющее окно");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Центрирование окна

        // Создаем панель с кнопками
        JPanel panel = new JPanel();

        JButton button1 = new JButton("Показать сообщение 1");
        JButton button2 = new JButton("Показать сообщение 2");
        JButton button3 = new JButton("Очистить");

        // Добавляем обработчики событий для кнопок
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayFrame.updateText("Сообщение 1");
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayFrame.updateText("Сообщение 2");
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayFrame.updateText("");
            }
        });

        // Добавляем кнопки на панель
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);

        // Добавляем панель в окно
        add(panel);
    }
}
