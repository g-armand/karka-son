package domino;
import java.util.LinkedList;
import java.util.Random;

public class Jeu {

    int num_players;

    // the number of tiles that user wants to have
    int wantedTileBag = 10;
    LinkedList<Tile> tileBag = new LinkedList<>();
    Board board = new Board();
    private Player player;

    public Jeu(int players, int wantedTileBag, Player player){
        this.num_players = players;
        this.wantedTileBag = wantedTileBag;
        this.player = player;
        Player player1 = new Player();
    }

    public void fillBag(){
        while(tileBag.size() < wantedTileBag){
            tileBag.add(new Tile(0,0));
        }
    }

    public Tile pickTile(Player player) {
        Random a = new Random();
        int i = a.nextInt();
        Tile randomTile = tileBag.get(i);
        tileBag.remove(i);
        this.player.tiles.add(randomTile);
        return randomTile;
    }



}
