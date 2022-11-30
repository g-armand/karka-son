package domino;

public class Board {
    Tile[][] tiles = new Tile[3][3];
    static int tilesTotal = 1;
    public Board(){

    }

    public void addTile(Tile t, int x, int y){
        tilesTotal ++;
        Tile[][] tilesUpdated = new Tile[tilesTotal*2-1][tilesTotal*2-1];
        for(int i = 0; i<tilesTotal*2-1; i++){
            for(int j = 0; j<tilesTotal*2-1; j++){
                if(i == 0
                   || i == tilesTotal*2-2
                   || j == 0
                   || j == tilesTotal*2-2){
                    tilesUpdated[i][j] = new EmptyTile(i, j);
                }
                else{
                    tilesUpdated[i][j] = this.tiles[i-1][j-1];
                }
            }
        }
        //verifier si posable
        if(tilesUpdated[x][y] instanceof EmptyTile
//        && tilesUpdated[x+1][y].joinable(t, "north")&&
//           tilesUpdated[x-1][y].joinable(t, "south")&&
//           tilesUpdated[x][y+1].joinable(t, "west")&&
//           tilesUpdated[x][y-1].joinable(t, "east")
        ){
            tilesUpdated[x][y] = t;
            this.tiles = tilesUpdated;
        }
    }

    public String toString(){
        String res = "";
        for(Tile[] line: this.tiles){
            for(int lineIndex = 0; lineIndex<5; lineIndex++){
                for(Tile t: line) {
                    res += t.getLine(lineIndex);
                }
                res += "\n";
            }
        }
        return res;
    }

}
