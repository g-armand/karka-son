package domino;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {

    String name;
    int points;

    List <DominoFrame.Tile_GUI> tiles = new ArrayList<DominoFrame.Tile_GUI>() {
    };

    public Player(String name){
        this.name = name;
        this.points = 0;
    }


}
