package domino;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Jeu {

    int numberPlayers;

    // the number of tiles that user wants to have
    int numberTiles = 10;
    LinkedList<Tile> tileBag = new LinkedList<>();

    DominoBoard dominoBoard = new DominoBoard();
//    DominoBoard carcassoneBoard = new DominoBoard();
    private Player player1;
    private Player player2;


    public Jeu(int players, int wantedTileBag){
        this.numberPlayers = players;
        this.numberTiles = wantedTileBag;
        this.player1 = new Player();
        this.player2 = new Player();
    }

    public void fillBag(String game){
        while(tileBag.size() < numberTiles){
            if(game.matches("domino")){
                tileBag.add(new DominoTile());
            } else if (game.matches("carcassone")){
                tileBag.add(new Tile());
            }
        }
    }

    public Tile pickTile() {
        Random a = new Random();
        int i = a.nextInt(tileBag.size());
        Tile randomTile = tileBag.get(i);
        tileBag.remove(i);
//        this.player.tiles.add(randomTile);
        return randomTile;
    }

    public void playDomino(){
        fillBag("domino");
        this.dominoBoard = new DominoBoard();
        boolean isPlayer1Turn = true;
        Scanner actionScanner = new Scanner(System.in);

        while(this.tileBag.size() > 0){
            Tile pickedTile =  pickTile();
            System.out.println("The board current state is:\n" + Board.printableBoard(Board.increaseSizeOfBoard(Board.trimBoard(this.dominoBoard.tiles))));
            System.out.println("You picked this tile:\n" + pickedTile);

            if(this.dominoBoard.isUsable(pickedTile)){
                int previousDimensions = this.dominoBoard.tiles.length;
                int horizontalOffset = Board.findBoundary(this.dominoBoard.tiles, "west");
                int verticalOffset = Board.findBoundary(this.dominoBoard.tiles, "north");
                int x = 0;
                int y = 0;
                //while the board is not updated
                while(previousDimensions == this.dominoBoard.tiles.length){
                    String input;
                    //get action
                    while(true){
                        System.out.println("Type your action (\"place\" or \"spin\")");
                        input = actionScanner.nextLine();
                        if(input.matches("spin")){
                            pickedTile.spin("gauche");
                            System.out.println(pickedTile);
                        } else if(input.matches("place")){
                            //get coordinates
                            while(true){
                                System.out.println("give coordinates: (\"x y\")");
                                input = actionScanner.nextLine();
                                if(input.matches("\\d+\\s\\d+")){
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    x = Integer.parseInt(input.split("\\s")[0]) + verticalOffset-1;
                    y = Integer.parseInt(input.split("\\s")[1]) + horizontalOffset-1;
                    this.dominoBoard.addTile(pickedTile, x, y);
                }
                //once the tile placed, we display board and update scores
//                System.out.println(this.board.toString(Board.trimBoard(this.board.tiles)));
                if(isPlayer1Turn){
                    this.player1.points += this.dominoBoard.countPoints(x, y);
                } else {
                    this.player2.points += this.dominoBoard.countPoints(x, y);
                }
                isPlayer1Turn = !isPlayer1Turn;
                System.out.println("Player 1 score = " + this.player1.points);
                System.out.println("Player 2 score = " + this.player2.points);

            }
        }
    }

}
