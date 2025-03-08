package windows.panels;

import world.Obj;
import world.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class DrawPanel extends JPanel{
    private Timer gameTimer;
    World world;
    public DrawPanel(World world) {
        this.world = world;
        setPreferredSize(new Dimension(world.getW(), world.getH()));
        setBackground(Color.WHITE);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension newSize = getSize();
                world.setW(newSize.width);
                world.setH(newSize.height);
            }
        });
        // Запускаем игровой цикл
        gameTimer = new Timer(16, e -> {  // ~60 FPS
            for (Obj obj : world.getObjs()) {
                obj.move();
            }
            repaint();
        });
        gameTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (Obj obj : world.getObjs()) {
            obj.draw(g2d);
        }
    }
}
