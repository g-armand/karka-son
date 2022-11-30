package domino;
import java.util.LinkedList;


public class Jeu {

    // the number of tiles that user wants to have
    int wantedTileBag = 10;
    LinkedList<Tile> tileBag = new LinkedList<>();

    public void fillBag(){
        while(tileBag.size() < wantedTileBag){
            tileBag.add(new Tile(0,0));
        }
    }
}
