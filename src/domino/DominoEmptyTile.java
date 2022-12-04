package domino;

public class DominoEmptyTile extends DominoTile{
    public DominoEmptyTile(){
        super();
        for(int i = 0; i<this.rows; i++){
            for(int j = 0; j<this.rows; j++) {
                this.content[i][j] = '.';
            }
        }
    }

    @Override
    public boolean joinable(Tile t, String orientation){
        return true;
    }
}
