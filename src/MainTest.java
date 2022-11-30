import domino.Tuile;

public class MainTest {
    public static void main(String[] args){
        testTourner();
    }
    public static void blyadstvo (String[] args){System.out.println("Блядство");}

    public static void testTourner() {
        Tuile testTuile = new Tuile();
        System.out.println(testTuile);
        testTuile.tourner(testTuile, "gauche");
        System.out.println(testTuile);
        testTuile.tourner(testTuile, "droite");
        System.out.println(testTuile);
        testTuile.tourner(testTuile, "droite");
        System.out.println(testTuile);
    }

}
