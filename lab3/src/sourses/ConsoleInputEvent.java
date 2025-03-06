package sourses;

import observers.CIESourse;

import java.util.Observable;
import java.util.Observer;

public class ConsoleInputEvent implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        if(!(arg instanceof String)) return;
        if(((String)arg).contains("Console print:")){
            ((CIESourse)o).pw.println(arg);
            System.out.println(arg);
        }
    }
}
