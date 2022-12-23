package karkason;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Jeu {

    int numberPlayers;

    // the number of tiles that user wants to have
    int numberTiles = 10;
    LinkedList<Tile> tileBag;

    Board gameBoard;

    private Player player1;
    private Player player2;


    public Jeu(int players, int wantedTileBag){
        this.numberPlayers = players;
        this.numberTiles = wantedTileBag;
        this.player1 = new Player("a");
        this.player2 = new Player("b");
    }

    public void fillBag(String game){
        this.tileBag = new LinkedList<>();
        while(tileBag.size() < numberTiles){
            if(game.matches("domino")){
                tileBag.add(new DominoTile());
            } else if (game.matches("karkason")){
                tileBag.add(new KarkasonTile());
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

    public void play(String game){
        if(game.matches("karkason")){
            fillBag("karkason");
            this.gameBoard = new KarkasonBoard();
        } else{
            fillBag("karkason");
            this.gameBoard = new DominoBoard();
        }

        boolean isPlayer1Turn = true;
        Scanner actionScanner = new Scanner(System.in);

        while(this.tileBag.size() > 0){
            Tile pickedTile =  pickTile();
            System.out.println("The board current state is:\n" + Board.printableBoard(Board.trimBoard(this.gameBoard.tiles), true));
            System.out.println("The board current state is:\n" + Board.printableBoard(Board.trimBoard(this.gameBoard.tiles), false));
            System.out.println("You picked this tile:\n" + pickedTile);

            if(this.gameBoard.isUsable(pickedTile)){
                int previousDimensions = this.gameBoard.tiles.length;
                int horizontalOffset = Board.findBoundary(this.gameBoard.tiles, "west");
                int verticalOffset = Board.findBoundary(this.gameBoard.tiles, "north");
                int x = 0;
                int y = 0;
                //while the board is not updated
                while(previousDimensions == this.gameBoard.tiles.length){
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
                    this.gameBoard.addTile(pickedTile, x, y);
                }
                if(game.matches("karkason")){
                    System.out.println("wanna place minion ?");
                    String input = actionScanner.nextLine();
                    if(input.matches("yes|y")){
                        System.out.println("The board current state is:\n" + Board.printableBoard(Board.trimBoard(this.gameBoard.tiles), true));
                        System.out.println("The board current state is:\n" + Board.printableBoard(Board.trimBoard(this.gameBoard.tiles), false));

                        //get coordinates
                        while(true){
                            System.out.println("give coordinates: (\"x y\")");
                            input = actionScanner.nextLine();
                            if(input.matches("\\d+\\s\\d+")){
                                break;
                            }
                        }
                        horizontalOffset = Board.findBoundary(this.gameBoard.tiles, "west")-1;
                        verticalOffset = Board.findBoundary(this.gameBoard.tiles, "north")-1;
                        x = Integer.parseInt(input.split("\\s")[0]) + verticalOffset*5;
                        y = Integer.parseInt(input.split("\\s")[1]) + horizontalOffset*5;
                        this.gameBoard.tiles[x/5][y/5].content[x%5][y%5].setOwner(1);
                        ((KarkasonBoard) this.gameBoard).updateTerritoriesForEach();
                        System.out.println("The board current state is:\n" + Board.printableBoard(Board.trimBoard(this.gameBoard.tiles), true));
                        int pointsScored = ((KarkasonBoard) this.gameBoard).getScoreOfTerritory(x, y);
                        System.out.println("points scored: " + pointsScored);
                    }
                } else if(game.matches("domino")){
                    //once the tile placed, we display board and update scores
                System.out.println(Board.printableBoard(Board.trimBoard(this.gameBoard.tiles), false));
                if(isPlayer1Turn){
                    this.player1.points += ((DominoBoard) this.gameBoard).countPoints(x, y);
                } else {
                    this.player2.points += ((DominoBoard) this.gameBoard).countPoints(x, y);
                }
                isPlayer1Turn = !isPlayer1Turn;
                System.out.println("Player 1 score = " + this.player1.points);
                System.out.println("Player 2 score = " + this.player2.points);

                }

            }
        }
    }

}
