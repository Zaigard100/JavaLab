package lab3.sourses;

import lab3.observers.AECESourse;

import java.util.Observable;
import java.util.Observer;

public class ArrayElementCountEvent implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof String[]){
            if(((String[])arg).length == 15){
                ((AECESourse)o).pw.println("In Array 15 elements");
                System.out.println("In Array 15 elements");
            }
        }
    }
}
