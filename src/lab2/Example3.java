package lab2;

interface IConst31 {
    static final int verConst= 101;
}
interface IConst32 {
    int verConst= 102;
}
interface IConst3 extends IConst31, IConst32 {
    static final int verConst= 100;
    int get (boolean b);
}
class ClassInt3 implements IConst3 {
    public int get (boolean b){ return b ? IConst31.verConst : IConst32.verConst;}
}

public class Example3 {
    public static void main (String[] args) {
        ClassInt3 cI= new ClassInt3();
        System.out.print ("cI.verConst= " + cI.verConst);
        System.out.println (" ClassInt.verConst= " + ClassInt3.verConst);
        //-----
        IConst31 iC1= cI;
        System.out.print ("iC1.verConst= " + iC1.verConst);
        IConst32 iC2= cI;
        System.out.print (" iC2.verConst= " + iC2.verConst);
        IConst3 iC= cI;
        System.out.println (" iC.verConst= " + iC.verConst);
        //--------------
        System.out.print ("IConst1.verConst= " + IConst31.verConst);
        System.out.print (" IConst2.verConst= " + IConst32.verConst);
        System.out.println (" IConst.verConst= " + IConst3.verConst);
        //--------------
        System.out.println ("cI.get(true)= " + cI.get(true) + " cI.get(false)= "+ cI.get(false));
    }

}
