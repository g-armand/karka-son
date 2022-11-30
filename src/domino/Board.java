package domino;

public class Board {
    Tile[][] tiles = new Tile[1][1];
    static int tilesTotal = 0;
    public Board(){

    }

    public void addTile(Tile t, int x, int y){
        tilesTotal ++;
        Tile[][] tilesUpdated = new Tile[tilesTotal*2-1][tilesTotal*2-1];
        for(int i = 0; i<tilesTotal*2-1; i++){
            for(int j = 0; j<tilesTotal*2-1; i++){
                if(i == 0
                   || i == tilesTotal*2-2
                   || j == 0
                   || j == tilesTotal*2+1){
                    tilesUpdated[i][j] = new EmptyTile(i, j);
                }
                else{
                    tilesUpdated[i][j] = this.tiles[i-1][j-1];
                }
            }
        }
        //verifier si posable
        if(tilesUpdated[x][y] instanceof EmptyTile){

        }

        //recopie le this.tuiles dans tuilesUpdated

        //ajouter t dans tuilesUpdated

        this.tiles = tilesUpdated;

    }

    public String toString(){
        for(Tile[] line: this.tiles){
            for(int lineIndex = 0; lineIndex<5; lineIndex++){
                for(Tile t: line) {
                    //                    System.out.print(t.getLine(lineIndex));

                }
            }

        }
        return "";
    }

}
