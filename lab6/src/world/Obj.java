package world;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Obj {

    private int x,y;
    private int w,h;
    private int vX,vY;
    private ObjType type;
    private String name;
    private String color;
    private int speed;
    BufferedImage img;
    /**
     * Носит информацию для обьекта
     * <br/>Если world.ObjType.TEXT то текст который будет отображатся
     * <br/>Если world.ObjType.IMG то ссылку на отоброжаемое изображение
     */
    private String parameter;
    World world;

    public Obj(World world) {
        x = -100;
        y = -100;
        w = 0;
        h = 0;
        vX = 1;
        vY = 1;
        type = ObjType.EMPTY;
        name = "No object";
        parameter = "";
        this.world = world;
    }

    public Obj(int x, int y, int w, int h, int vX, int vY, int speed, ObjType type, String name, String color, String parameter,World world) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.vX = vX;
        this.vY = vY;
        this.speed = speed;
        this.type = type;
        this.name = name;
        this.color = color;
        this.parameter = parameter;
        this.world = world;
    }

    public void init(){
        img = null;
        if(type == ObjType.IMG) {
            world.imgManager.loadImage(parameter);
            img = world.imgManager.getImage(parameter);
        }
    }

    public void draw(Graphics g) {
        if (type == ObjType.EMPTY) return;
        if (type == ObjType.TEXT) renderText(g);
        if (type == ObjType.IMG) renderImage(g);
    }

    private void renderImage(Graphics g) {
        if (img != null) {
            g.drawImage(img, x, y, w, h, null);
        }
    }

    private void renderText(Graphics g) {
        g.setColor(parseColor(color));
        g.setFont(new Font("Arial", Font.PLAIN, h));
        g.drawString(parameter, x, y + h);
    }

    private Color parseColor(String color) {
        //"Red", "Green", "Blue", "Yellow", "Cyan"
        switch (color) {
            case "Red":
                return Color.red;
            case "Green":
                return Color.green;
            case "Blue":
                return Color.blue;
            case "Yellow":
                return Color.yellow;
            case "Cyan":
                return Color.cyan;
        }
        return null;
    }

    public void move(){
        if(type == ObjType.EMPTY) return;
        x += vX * speed;
        y += vY * speed;
        if(x < 0||x+w>world.getW()) vX = -vX;
        if(y < 0||y+h>world.getH()) vY = -vY;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getvX() {
        return vX;
    }

    public void setvX(int vX) {
        this.vX = vX;
    }

    public int getvY() {
        return vY;
    }

    public void setvY(int vY) {
        this.vY = vY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public ObjType getType() {
        return type;
    }

    public void setType(ObjType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public void clear(){
        x = -100;
        y = -100;
        w = 0;
        h = 0;
        vX = 0;
        vY = 0;
        type = ObjType.EMPTY;
        name = "No object";
        parameter = "";
    }

    @Override
    public String toString() {
        return getName();
    }
}
