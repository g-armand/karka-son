package domino;

public class Board {
    public Tile[][] tiles = new Tile[1][1];

    static int tilesTotal = 0;
    public Board(){
        this.tiles[0][0] = new Tile(0,0);
    }

    public void addTile(Tile t, int x, int y){
        tilesTotal ++;
        //tilesUpdated is the future version of this.tiles
        Tile[][] tilesUpdated = increaseSizeOfBoard(this.tiles);

        //tilesUpdatedTemp is only used here to determine if t can be put a coordinates x and y
        //we increase the board size by two avoids IndexOutOfBoundsException
        Tile[][] tilesUpdatedTemp = increaseSizeOfBoard(tilesUpdated);
        x+=2;
        y+=2;

        //verifier si posable
        if(isUsable(t, tilesUpdatedTemp, x , y)){
            tilesUpdated[x-1][y-1] = t;
            this.tiles = tilesUpdated;
        }
    }

    /*
    might be deleted later
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
    checks if a Tile can be played on the current board
     */
    public boolean isUsable(Tile t){
        //add empty tiles around the board (avoids IndexOutOfBoundException)
        Tile[][] tilesUpdatedTemp = this.increaseSizeOfBoard(this.tiles);

        //try to find at least one valid position
        boolean usable = false;
        for(int x = 0; x<this.tiles.length-1; x++){
            for(int y = 0; y<this.tiles.length; y++){
                if(this.isUsable(t, tilesUpdatedTemp, x , y)) {
                    usable = true;
                }
            }
        }
        return usable;
    }

    public boolean isUsable(Tile tile, Tile[][] board, int x, int y){
        boolean areNeighborsJoinable = (
                board[x+1][y].joinable(tile, "north") &&
                board[x-1][y].joinable(tile, "south") &&
                board[x][y+1].joinable(tile, "west") &&
                board[x][y-1].joinable(tile, "east"));
        boolean isTileEmpty = board[x][y] instanceof EmptyTile;
        boolean isTileLinkedToNeighbor = !(
                board[x+1][y] instanceof EmptyTile &&
                board[x-1][y] instanceof EmptyTile &&
                board[x][y+1] instanceof EmptyTile &&
                board[x][y-1] instanceof EmptyTile);
        return areNeighborsJoinable && isTileEmpty && isTileLinkedToNeighbor;
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

    public Tile[][] increaseSizeOfBoard(Tile[][] tiles){
        Tile[][] tilesUpdated = new Tile[tiles.length+2][tiles.length+2];
        for(int i = 0; i<tiles.length+2; i++){
            for(int j = 0; j<tiles.length+2; j++){
                if(  (i>=1 && i<tiles.length+1)
                   &&(j>=1 && j<tiles.length+1)){
                    tilesUpdated[i][j] = tiles[i-1][j-1];
                }
                else{
                    tilesUpdated[i][j] = new EmptyTile(i, j);
                }
            }
        }
        return tilesUpdated;
    }
}
