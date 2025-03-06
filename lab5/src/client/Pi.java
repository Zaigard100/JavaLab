package client;

import compute.Task;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Pi implements Task<String>, Serializable {

    private static final long serialVersionUID = 228L;

    private static final BigDecimal FOUR = BigDecimal.valueOf(4);
    private static final int roundingMode = RoundingMode.HALF_EVEN.ordinal();
    private final String[] args;

    public Pi(String[] args) {
        this.args = args;
    }

    @Override
    public String execute() {
        return computePi(args);
    }

    public static String computePi(String[] args) {
        StringBuilder r = new StringBuilder("\n");
        int M = 42;
        int count = 0;
        for(int i = 1;i < args.length;++i){
            int n = -1;
            try{
                n = Integer.parseInt(args[i]);
            }catch(NumberFormatException e){
                r.append(args[i]).append(" is not digit\n");
            }
            if(n>M){
                r.append(args[i]).append(" is greater than m\n");
                count++;
            }
        }
        if(count==0){
            r.append("Number greader ").append(M).append(" not found\n");
        }
        //System.out.println(r.toString());
        return r.append("Found " ).append(count).append(" number(s) greader ").append(M).toString();
    }
}
