package dw.randomizer.model.util;



import java.util.Random;

public class Rolls {

    public static String rollTwice(String[] a){
        int n;
        String s1;
        String s2;
        do{
            n = Rolls.UniversalRoll(a);
            s1 = a[n];
        } while (s1.equals("roll twice"));

        do{
            n=Rolls.UniversalRoll(a);
            s2 = a[n];
        } while (s1.equals(s2) || s2.equals("roll twice"));

        return s1+" + "+s2;
    }

    public static String rollTwice(String[] a,int i){
        try {
            int n;
            String s1;
            String s2;

            do {
                n = Rolls.CustomRoll(i);
                s1 = a[n];
            } while (s1.equals("roll twice") || s1.equals("ROLL TWICE"));

            do {
                n = Rolls.CustomRoll(i);
                s2 = a[n];
            } while (s1.equals(s2) || s2.equals("roll twice") || s2.equals("ROLL TWICE"));

            return s1 + " + " + s2;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public static int CustomRoll(int i){
        Random azar = new Random();
        return azar.nextInt(i);
    }

    public static int UniversalRoll(String[] a){
     return (int) (Math.random()*a.length);
    }

    public static String PickFrom(String[] a){
        return a[Rolls.UniversalRoll(a)];
    }

    public static boolean rollBoolean(){
        return java.util.concurrent.ThreadLocalRandom.current().nextBoolean();
    }

    public static int Roll1d2() {
        Random azar = new Random();
        return azar.nextInt(2);
    }

    public static int Roll1d4() {
        Random azar = new Random();
        return azar.nextInt(4);
    }
    public static int Roll1d6() {
        Random azar = new Random();
        return azar.nextInt(6);
    }

    public static int Roll1d8() {
        Random azar = new Random();
        return azar.nextInt(8);
    }

    public static int Roll1d12() {
        Random azar = new Random();
        return azar.nextInt(12);
    }

    public static int Roll1d10(){
        Random azar = new Random();
        return azar.nextInt(10);
    }
}
