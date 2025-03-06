package observers;

import java.io.PrintWriter;
import java.util.Observable;

public class AECESourse extends Observable {
    public PrintWriter pw;
    public void notifyObs(PrintWriter pw, Object o){
        this.pw = pw;
        setChanged();
        notifyObservers(o);
    }
}
