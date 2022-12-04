package domino;

public class KarkasonBoard extends Board implements KarkasonTileTemplate{

    public KarkasonBoard(){
        super();
        this.tiles[1][1] = new KarkasonTile();

    }

}
