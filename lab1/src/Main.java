public class Main {
    /**
    15.Дана последовательность целых чисел. Найти произведение только тех из них, которые
    больше заданного числа М. Если таких чисел нет, то вывести сообщение об этом
    */
    public static void main(String[] args) {
        int M = 42;
        int count = 0;
        for(int i = 0;i < args.length;++i){
            int n = -1;
            try{
                n = Integer.parseInt(args[i]);
            }catch(NumberFormatException e){
                System.out.println(args[i]+" is not digit");
            }
            if(n>M){
                System.out.println(n);
                count++;
            }
        }
        if(count==0){
            System.out.println("Number greader "+ M +" not found");
        }
    }
}