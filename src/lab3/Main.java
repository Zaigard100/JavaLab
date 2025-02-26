    package lab3;

    import lab3.observers.AECESourse;
    import lab3.observers.CIESourse;
    import lab3.observers.OEVESourse;
    import lab3.sourses.ArrayElementCountEvent;
    import lab3.sourses.ConsoleInputEvent;
    import lab3.sourses.ObjectEqualsVarableEvent;

    public class Main {
        public static void main(String[] arg) {

            ConsoleInputEvent cie = new ConsoleInputEvent();
            ObjectEqualsVarableEvent oeve = new ObjectEqualsVarableEvent();
            ArrayElementCountEvent aece = new ArrayElementCountEvent();
            AECESourse aeses = new AECESourse();
            CIESourse cies = new CIESourse();
            OEVESourse oeves = new OEVESourse();
            oeves.addObserver(oeve);
            cies.addObserver(cie);
            aeses.addObserver(aece);
            Task t = new Task(aeses,cies,oeves);
            t.run();
        }
    }
