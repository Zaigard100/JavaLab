import windows.ControlFrame;
import windows.DisplayFrame;
import world.World;

public class Main {
    public static void main(String[] args) {
        World world = new World(750,500,7);
        // Создаем демонстрационное окно
        DisplayFrame displayFrame = new DisplayFrame(world);
        displayFrame.setVisible(true);

        // Создаем управляющее окно
        ControlFrame controlFrame = new ControlFrame(world);
        controlFrame.setVisible(true);
    }
}