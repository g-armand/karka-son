import domino.*;

public class MainTest {

    public static void main(String[] args) {
        MainMenu menu = new MainMenu();
    }

    public static void boardTest(){
        Tile t = new Tile();
        Board b = new Board();

        while(b.tiles.length !=3){
            b.addTile(new Tile(),1,0);
            System.out.println(Board.printableBoard(b.tiles));
        }

        while(b.tiles.length !=5){
            b.addTile(new Tile(),1,2);
            System.out.println(Board.printableBoard(b.tiles));
        }

        b.addTile(new Tile(), 0,0);

        //test board trimming
        System.out.println(b);
        Tile[][] newTileMatrix = b.tiles;
        newTileMatrix = Board.trimBoard(newTileMatrix);
        System.out.println(Board.printableBoard(newTileMatrix));

    }

    public static void blyadstvo (){
        System.out.println("Блядство");
        Jeu domino = new Jeu(2,20);
        domino.playDomino();
    }

    public static void testTourner() {
        Tile testTuile = new Tile();
        System.out.println(testTuile);
        testTuile.spin("gauche");
        System.out.println(testTuile);
        testTuile.spin("droite");
        System.out.println(testTuile);
        testTuile.spin("droite");
        System.out.println(testTuile);
        joinableTest();
    }

    public static void joinableTest(){
        Tile testTuile = new Tile();
        Tile othertestTuile = new Tile();

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
