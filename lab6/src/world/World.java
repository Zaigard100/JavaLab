package world;

import utils.ImgManager;

public class World {
    private int w;
    private int h;
    Obj[] objs;
    ImgManager imgManager;
    public World(int width,int height, int objCount) {
        objs = new Obj[objCount];
        for (int i = 0; i < objCount; i++) {
            objs[i] = new Obj(this);
        }
        w = width;
        h = height;
        imgManager = new ImgManager();
    }

    public String[] getNameList() {
        String[] names = new String[objs.length];
        for (int i = 0; i < objs.length; i++) {
            names[i] = objs[i].getName();
        }
        return names;
    }

    public Obj getObjByName(String name) {
        for (int i = 0; i < objs.length; i++) {
            if (objs[i].getName().equals(name)) {
                return objs[i];
            }
        }
        return null;
    }
    public int getIndex(String name){
        for (int i = 0; i < objs.length; i++) {
            if (objs[i].getName().equals(name)) {
                return i;
            }
        }
        return -1;
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

    public Obj[] getObjs() {
        return objs;
    }

    public void setObjs(Obj[] objs) {
        this.objs = objs;
    }
}
