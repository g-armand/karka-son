package domino;

public class Board {
    public Tile[][] tiles = new Tile[1][1];

    static int tilesTotal = 0;
    public Board(){
        this.tiles[0][0] = new EmptyTile(0,0);
    }

    public void addTile(Tile t, int x, int y){
        tilesTotal ++;
        Tile[][] tilesUpdated = new Tile[this.tiles.length+2][this.tiles.length+2];
        for(int i = 0; i<this.tiles.length+2; i++){
            for(int j = 0; j<this.tiles.length+2; j++){
                if(  (i>1 && i<this.tiles.length+1)
                   &&(j>1 && j<this.tiles.length+1)){
                    tilesUpdated[i][j] = this.tiles[i-1][j-1];
                }
                else{
                    tilesUpdated[i][j] = new EmptyTile(i, j);
                }
            }
        }
        x+=1;
        y+=1;
        //verifier si posable
        if(tilesUpdated[x][y] instanceof EmptyTile
        && (x == tilesUpdated.length-1 || tilesUpdated[x+1][y].joinable(t, "north"))&&
           (x== 0 || tilesUpdated[x-1][y].joinable(t, "south"))&&
           (y == tilesUpdated.length-1 || tilesUpdated[x][y+1].joinable(t, "west"))&&
           (y == 0 || tilesUpdated[x][y-1].joinable(t, "east"))){
            tilesUpdated[x][y] = t;
            this.tiles = tilesUpdated;
        }
    }

    /*
    might be deleted later,
     */
    public String toString(Tile[][] t){
        Tile[][] temp = this.tiles;

        //Board.toString actually reads this.tiles
        this.tiles = this.trimBoard(t);
        String result = this.toString();

        //reset to normal values and return result
        this.tiles = temp;
        return result;
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

    /*
    returns a Tile[][] with empty rows and columns removed
     */
    public Tile[][] trimBoard(Tile[][] tiles){
        int startX = 0;
        int endX = tiles.length-1;
        int startY = 0;
        int endY = tiles.length-1;

        //find boundaries for rows
        boolean stillEmpty = true;
        while(stillEmpty){
            for(int i = 0; i<tiles.length-1;i++){
                 if(!(tiles[startX][i] instanceof EmptyTile)) stillEmpty = false;
            }
            if(stillEmpty){
                startX++;
            }
        }
        stillEmpty = true;
        while(stillEmpty){
            for(int i = tiles.length-1; i>0; i--){
                if(!(tiles[endX][i] instanceof EmptyTile)) stillEmpty = false;
            }
            if(stillEmpty){
                endX--;
            }
        }

        //find boundaries for y
        stillEmpty = true;
        while(stillEmpty){
            for(int i = 0; i<tiles.length-1;i++){
                if(!(tiles[i][startY] instanceof EmptyTile)) stillEmpty = false;
            }
            if(stillEmpty){
                startY++;
            }
        }
        stillEmpty = true;
        while(stillEmpty){
            for(int i = tiles.length-1; i>0; i--){
                if(!(tiles[i][endY] instanceof EmptyTile)) stillEmpty = false;
            }
            if(stillEmpty){
                endY--;
            }
        }

        //create new Tile matrix
        Tile[][] tilesUpdated = new Tile[endX-startX+1][endY-startY+1];
        for(int i =startX; i<=endX; i++){
            for(int j = startY; j<=endY; j++){
                tilesUpdated[i-startX][j-startY] = tiles[i][j];
            }
        }
        return tilesUpdated;
    }

}
