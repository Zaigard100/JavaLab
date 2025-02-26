package lab2;

interface IFunc2 {
    public int getF();
}
interface IConst2 {
    public static final int verConst= 100;
}
class ClassInt2 implements IFunc2, IConst2 {
    public int getF (){ return verConst;}
}

/**Интерфейсы<br>
 * Ключевое слово public перед словом interface делает интерфейс доступным вне
 * пакета (пользователю-программисту), в котором он определяется.
 * Следующий пример иллюстрирует создание и применение интерфейсов.
 */
public class Example2 {
    public static void main (String[] args) {
        ClassInt2 cI= new ClassInt2();
        System.out.println ("verConst= " + cI.getF());
        IFunc2 iF= cI;
        IConst2 iC= cI;
        System.out.println ("verConst= " + iF.getF());
        // System.out.println ("verConst= " + iC.getF()); // Error
        // Cannot find 'getF()' in 'IConst'
        System.out.println ("verConst= " + iC.verConst);
        System.out.println ("verConst= " + ClassInt2.verConst);
    }
}
