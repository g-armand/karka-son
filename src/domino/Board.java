package domino;

public class Board {
    Tile[][] tiles = new Tile[1][1];
    static int tilesTotal = 0;
    public Board(){

    }

    public void addTile(Tile t, int x, int y){
        tilesTotal ++;
        Tile[][] tilesUpdated = new Tile[tilesTotal*2-1][tilesTotal*2-1];
        //verifier si posable

        //recopie le this.tuiles dans tuilesUpdated

        //ajouter t dans tuilesUpdated

        this.tiles = tilesUpdated;

    }

}
