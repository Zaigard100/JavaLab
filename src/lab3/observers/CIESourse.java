package lab3.observers;

import java.io.PrintWriter;
import java.util.Observable;

public class CIESourse extends Observable {
    public PrintWriter pw;
    public void notifyObs(PrintWriter pw, Object args){
        this.pw = pw;
        setChanged();
        notifyObservers(args);
    }
}
