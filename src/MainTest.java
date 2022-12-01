import domino.Board;
import domino.Tile;

public class MainTest {
    public static void main(String[] args){
        boardTest();
    }

    public static void boardTest(){
        Tile t = new Tile(0,0);
        Board b = new Board();

        while(b.tiles.length !=3){
            b.addTile(new Tile(0,0),1,0);
            System.out.println(b);
        }
        while(b.tiles.length !=5){
            b.addTile(new Tile(0,0),1,2);
            System.out.println(b);
        }

        b.addTile(new Tile(0,0), 0,0);

        //test board trimming
        System.out.println(b);
        Tile[][] newTileMatrix = b.tiles;
        System.out.println(b.toString(newTileMatrix));

    }

    public static void blyadstvo (String[] args){System.out.println("Блядство");}

    public static void testTourner() {
        Tile testTuile = new Tile(0,0);
        System.out.println(testTuile);
        testTuile.spin(testTuile, "gauche");
        System.out.println(testTuile);
        testTuile.spin(testTuile, "droite");
        System.out.println(testTuile);
        testTuile.spin(testTuile, "droite");
        System.out.println(testTuile);
        joinableTest();

    }

    public static void joinableTest(){
        Tile testTuile = new Tile(0,0);
        Tile othertestTuile = new Tile(0,0);

        testTuile.content[0][1] = '1';
        testTuile.content[4][1] = '1';
        testTuile.content[0][2] = '2';
        testTuile.content[4][2] = '2';
        testTuile.content[0][3] = '3';
        testTuile.content[4][3] = '3';
        testTuile.content[1][0] = '4';
        testTuile.content[1][4] = '4';
        testTuile.content[2][0] = '5';
        testTuile.content[2][4] = '5';
        testTuile.content[3][0] = '6';
        testTuile.content[3][4] = '6';

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

        System.out.println(testTuile.joinable(othertestTuile, "north"));
        System.out.println(testTuile.joinable(othertestTuile, "south"));
        System.out.println(testTuile.joinable(othertestTuile, "east"));
        System.out.println(testTuile.joinable(othertestTuile, "west"));
    }

}
