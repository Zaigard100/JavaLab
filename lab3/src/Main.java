import observers.AECESourse;
    import observers.CIESourse;
    import observers.OEVESourse;
    import sourses.ArrayElementCountEvent;
    import sourses.ConsoleInputEvent;
    import sourses.ObjectEqualsVarableEvent;

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
