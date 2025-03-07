import windows.ControlFrame;
import windows.DisplayFrame;

public class Main {
    public static void main(String[] args) {
        // Создаем демонстрационное окно
        DisplayFrame displayFrame = new DisplayFrame();
        displayFrame.setVisible(true);

        // Создаем управляющее окно
        ControlFrame controlFrame = new ControlFrame(displayFrame);
        controlFrame.setVisible(true);
    }
}