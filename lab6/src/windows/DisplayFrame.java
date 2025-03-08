package windows;

import windows.panels.DrawPanel;
import world.World;

import javax.swing.*;

public class DisplayFrame extends JFrame {
    private Timer gameTimer;
    public DisplayFrame(World world) {
        setTitle("Демонстрационное окно");
        setSize(world.getW(), world.getH());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Центрирование окна
        DrawPanel drawPanel = new DrawPanel(world);
        add(drawPanel);
    }
}
