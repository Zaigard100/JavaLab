package sourses;

import observers.OEVESourse;

import java.util.Observable;
import java.util.Observer;

public class ObjectEqualsVarableEvent implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        if(arg.equals(42)){
            ((OEVESourse)o).pw.println("Object \""+arg.getClass()+"("+arg+")\" equals 42");
            System.out.println("Object \""+arg.getClass()+"("+arg+")\" equals 42");
        }
    }
}
