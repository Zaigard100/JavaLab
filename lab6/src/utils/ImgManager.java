package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ImgManager {

    private Map<String, BufferedImage> imageCache = new HashMap<>();

    public void loadImage(String path) {
        try {
            BufferedImage img;
            if (path.startsWith("http")) {
                URL url = new URL(path);
                img = ImageIO.read(url);
            } else {
                img = ImageIO.read(new File(path));
            }
            imageCache.put(path, img);
        } catch (IOException e) {
            System.err.println("Ошибка загрузки изображения: " + path);
            e.printStackTrace();
        }
    }

    public BufferedImage getImage(String path) {
        return imageCache.get(path);
    }

}
