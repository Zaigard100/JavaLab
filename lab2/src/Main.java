/**
 * 37 Вариант<br> 010000101 Исключения 1 3 8<br>
 * 1.Описать два интерфейса, которые должны содержать необходимые
 * для последующей реализации класса функции и константы. Если в задании
 * фигурируют какие-либо константы, то они должны находиться в описании
 * интерфейсов.<br>
 * 2.Описать класс, осуществляющий интерфейсы<br>
 * 3.При осуществлении функций разрабатываемого класса нужно описать собственные
 * классы обработчики исключительных ситуаций, а также функции, которые будут
 * выбрасывать собственные исключения.<br>
 * Исключения<br>
 * 1.В массиве число элементов меньше указанного<br>
 * 3.Больше, чем некоторое число<br>
 * 8.Равенство некоторому числу<br>
 */
interface Constantinos{
    int M = 42;
}
interface Functionality{
    static void uberMegaDrive(String[] args){};
}
class SmallArrayException extends Exception{
    Object[] array;
    public SmallArrayException(Object[] array){this.array=array;}
    @Override
    public String toString() {
        return "Small array "+array.length+" element";
    }
}
class NumberLargeException extends Exception{
    int num;
    public NumberLargeException(int num){ this.num=num;}
    @Override
    public String toString() {
        return "Number "+num+" large 52";
    }
}
class NumberEqualsException extends Exception{
    int num;
    public NumberEqualsException(int num){ this.num=num;}
    @Override
    public String toString() {
        return "Number "+num+" equals 3";
    }
}
public class Main implements Constantinos,Functionality{
    static int count = 0;
    public static void uberMegaDrive(String[] args) throws SmallArrayException,NumberLargeException,NumberEqualsException {
        if(args.length<2) throw new SmallArrayException(args);
        for(int i = 0;i < args.length;++i){
            int n = -1;
            try{
                n = Integer.parseInt(args[i]);
            }catch(NumberFormatException e){
                System.out.println(args[i]+" is not digit");
            }
            if(n>52) throw new NumberLargeException(n);
            if(n>M){
                System.out.println(n);
                count++;
            }
        }
        if(count == 3) throw new NumberEqualsException(count);
        if(count==0){
            System.out.println("Number greader "+ M +" not found");
        }
    }
    public static void main(String[] args)  {
        try {
            uberMegaDrive(args);
        } catch (SmallArrayException | NumberLargeException | NumberEqualsException e) {
            throw new RuntimeException(e);
        }
    }
}
