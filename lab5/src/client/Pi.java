package client;

import compute.Task;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Pi implements Task<String>, Serializable {

    private static final long serialVersionUID = 228L;

    private final List<Integer> args;

    public Pi(List<Integer> args) {
        this.args = args;
    }

    @Override
    public String execute() {
        return computePi(args);
    }

    public static String computePi(List<Integer> args) {
        StringBuilder r = new StringBuilder("\n");
        int M = 42;
        int count = 0;
        for (Integer i : args) {
            if(i>M){
                r.append(i).append(" is greater than ").append(M).append("\n");
                count++;
            }
        }
        if(count==0) r.append("Number greader ").append(M).append(" not found\n");
        else r.append("Found " ).append(count).append(" number(s) greader ").append(M).append("\n");
        System.out.println(r);
        return r.toString();
    }
}
