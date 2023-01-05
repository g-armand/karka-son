import java.util.Objects;

public class MainTest {

    public static void main(String[] args) {
        if(args.length==0){
            MainMenu menu = new MainMenu();
        } else if (String.valueOf(args[0]).matches("commandlines")) {
            Jeu jeu = new Jeu(2, 72);
            jeu.play("domino");
        } else if (String.valueOf(args[0]).matches("dynamicUI")){
            MainMenu menu = new MainMenu();
        }
    }



    public static void boardTest(){
        Tile t = new Tile();
        Board b = new Board();

        while(b.tiles.length !=3){
            b.addTile(new Tile(),1,0);
            System.out.println(Board.printableBoard(b.tiles, false));
        }

        while(b.tiles.length !=5){
            b.addTile(new Tile(),1,2);
            System.out.println(Board.printableBoard(b.tiles, false));
        }

        b.addTile(new Tile(), 0,0);

        //test board trimming
        System.out.println(b);
        Tile[][] newTileMatrix = b.tiles;
        newTileMatrix = Board.trimBoard(newTileMatrix);
        System.out.println(Board.printableBoard(newTileMatrix, false));
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

        testTuile.content[0][1] = new Cell('1');
        testTuile.content[4][1] = new Cell('1');
        testTuile.content[0][2] = new Cell('2');
        testTuile.content[4][2] = new Cell('2');
        testTuile.content[0][3] = new Cell('3');
        testTuile.content[4][3] = new Cell('3');
        testTuile.content[1][0] = new Cell('4');
        testTuile.content[1][4] = new Cell('4');
        testTuile.content[2][0] = new Cell('5');
        testTuile.content[2][4] = new Cell('5');
        testTuile.content[3][0] = new Cell('6');
        testTuile.content[3][4] = new Cell('6');

        othertestTuile.content[0][1] = new Cell('1');
        othertestTuile.content[4][1] = new Cell('1');
        othertestTuile.content[0][2] = new Cell('2');
        othertestTuile.content[4][2] = new Cell('2');
        othertestTuile.content[0][3] = new Cell('3');
        othertestTuile.content[4][3] = new Cell('3');
        othertestTuile.content[1][0] = new Cell('4');
        othertestTuile.content[1][4] = new Cell('4');
        othertestTuile.content[2][0] = new Cell('5');
        othertestTuile.content[2][4] = new Cell('5');
        othertestTuile.content[3][0] = new Cell('6');
        othertestTuile.content[3][4] = new Cell('6');


        System.out.println(testTuile.joinable(othertestTuile, "north"));
        System.out.println(testTuile.joinable(othertestTuile, "south"));
        System.out.println(testTuile.joinable(othertestTuile, "east"));
        System.out.println(testTuile.joinable(othertestTuile, "west"));
    }

}
