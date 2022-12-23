package karkason;

import karkason.Tile;
import karkason.Cell;

public class EmptyTile extends Tile {
    public EmptyTile(){
        super();
        for(int i = 0; i<this.rows; i++){
            for(int j = 0; j<this.rows; j++) {
                this.content[i][j] = new Cell('.');
            }
        }
    }

    @Override
    public boolean joinable(Tile t, String orientation){
        return true;
    }
}
