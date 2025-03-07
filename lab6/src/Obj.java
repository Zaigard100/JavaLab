import java.awt.*;

public class Obj {

    public enum Type{
        EMPTY,
        TEXT,
        IMG;
    }

    private int x,y;
    private int w,h;
    private int vX,vY;
    private int speed;
    private Type type;
    private String name;
    private Color color;
    /**
     * Носит информацию для обьекта
     * <br/>Если Type.TEXT то текст который будет отображатся
     * <br/>Если Type.IMG то ссылку на отоброжаемое изображение
     */
    private String parameter;

    public Obj() {
        x = -100;
        y = -100;
        w = 0;
        h = 0;
        vX = 0;
        vY = 0;
        type = Type.EMPTY;
        name = "No object";
        parameter = "";
    }

    public Obj(int x, int y, int w,int h, int vX,int vY, int speed, Type type, String name, Color color, String parameter) {
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
    }

    public void move(){
        x += vX;
        y += vY;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}
