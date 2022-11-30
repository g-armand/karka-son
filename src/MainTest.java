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
        joinableTest();
    }

    public static void joinableTest(){
        Tuile testTuile = new Tuile();
        Tuile othertestTuile = new Tuile();

        testTuile.content[0][1] = '1';
        testTuile.content[4][1] = '1';
        testTuile.content[0][2] = '2';
        testTuile.content[4][2] = '2';
        testTuile.content[0][3] = '3';
        testTuile.content[4][3] = '3';
        testTuile.content[1][0] = '4';
        testTuile.content[1][0] = '4';
        testTuile.content[2][0] = '5';
        testTuile.content[2][0] = '5';
        testTuile.content[3][0] = '6';
        testTuile.content[3][0] = '6';

        othertestTuile.content[0][1] = '1';
        othertestTuile.content[4][1] = '1';
        othertestTuile.content[0][2] = '2';
        othertestTuile.content[4][2] = '2';
        othertestTuile.content[0][3] = '3';
        othertestTuile.content[4][3] = '3';
        othertestTuile.content[1][0] = '4';
        othertestTuile.content[1][4] = '4';
        othertestTuile.content[2][0] = '5';
        othertestTuile.content[2][4] = '5';
        othertestTuile.content[3][0] = '6';
        othertestTuile.content[3][4] = '6';

        System.out.println(testTuile.joinable(othertestTuile));
        System.out.println(testTuile.joinable(othertestTuile, "north"));
        System.out.println(testTuile.joinable(othertestTuile, "south"));
        System.out.println(testTuile.joinable(othertestTuile, "east"));
        System.out.println(testTuile.joinable(othertestTuile, "west"));
    }

}
