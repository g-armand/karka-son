package domino;
import java.util.LinkedList;
import java.util.Random;

public class Jeu {

    int players;

    // the number of tiles that user wants to have
    int wantedTileBag = 10;
    LinkedList<Tile> tileBag = new LinkedList<>();

    public void fillBag(){
        while(tileBag.size() < wantedTileBag){
            tileBag.add(new Tile(0,0));
        }
    }
    public Tile pickTile() {
        Random a = new Random();
        int i = a.nextInt();
        Tile randomTile = tileBag.get(i);
        tileBag.remove(i);
        return randomTile;
    }

}
