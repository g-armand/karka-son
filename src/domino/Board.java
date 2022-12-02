package domino;

public class Board {
    public Tile[][] tiles = new Tile[3][3];

    static int tilesTotal = 0;
    public Board(){
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                this.tiles[i][j] = new EmptyTile();
            }
        }
        this.tiles[1][1] = new Tile();
    }

    public void addTile(Tile t, int x, int y){
        tilesTotal ++;
        //tilesUpdated is the future version of this.tiles
        Tile[][] tilesUpdated = increaseSizeOfBoard(this.tiles);

        //tilesUpdatedTemp is only used here to determine if t can be put a coordinates x and y
        //avoids IndexOutOfBoundsException
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
    public static String printableBoard(Tile[][] board){
        String res = "";
        for(Tile[] line: board){
            for(int lineIndex = 0; lineIndex<5; lineIndex++){
                for(Tile tile: line) {
                    res += tile.getLine(lineIndex);
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
        Tile[][] tilesUpdatedTemp = increaseSizeOfBoard(increaseSizeOfBoard(this.tiles));

        //try to find at least one valid position
        boolean usable = false;
        for(int x = 1; x<tilesUpdatedTemp.length-2; x++){
            for(int y = 1; y<tilesUpdatedTemp.length-2; y++){
                for(int spinIndex = 0; spinIndex<5; spinIndex++){
                    if(this.isUsable(t, tilesUpdatedTemp, x , y)) {
                        usable = true;
                    }
                    t.spin("droite");
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
    public static Tile[][] trimBoard(Tile[][] tiles){
        //define boundary indexes
        int startX = findBoundary(tiles, "north");
        int endX = findBoundary(tiles, "south");
        int startY = findBoundary(tiles, "west");
        int endY = findBoundary(tiles, "east");

        //create new Tile matrix
        Tile[][] tilesUpdated = new Tile[endX-startX+1][endY-startY+1];
        for(int i =startX; i<=endX; i++){
            for(int j = startY; j<=endY; j++){
                tilesUpdated[i-startX][j-startY] = tiles[i][j];
            }
        }
        return tilesUpdated;
    }

    /*
    returns the index of the first row ("west" "east") or column ("north" "south") that contains a not empty Tile
     */
    public static int findBoundary(Tile[][] board, String direction){
        if(board.length==1){
            return 0;
        }
        boolean stillEmpty = true;
        int boundaryIndex;
        if(direction.matches("north")) {
            boundaryIndex = 0;
            while(stillEmpty){
                for(int i = 0; i<board.length-1;i++){
                    if(!(board[boundaryIndex][i] instanceof EmptyTile)) stillEmpty = false;
                }
                if(stillEmpty){
                    boundaryIndex++;
                }
            }
        } else if(direction.matches("south")) {
            boundaryIndex = board.length-1;
            while(stillEmpty){
                for(int i = board.length-1; i>0; i--){
                    if (!(board[boundaryIndex][i] instanceof EmptyTile)) {
                        stillEmpty = false;
                    }
                }
                if(stillEmpty){
                    boundaryIndex--;
                }
            }
        } else if(direction.matches("west")){
            boundaryIndex = 0;
            while(stillEmpty){
                for(int i = 0; i<board[0].length-1;i++){
                    if(!(board[i][boundaryIndex] instanceof EmptyTile)) stillEmpty = false;
                }
                if(stillEmpty){
                    boundaryIndex++;
                }
            }
        } else if(direction.matches("east")){
            boundaryIndex = board[0].length-1;
            while(stillEmpty){
                for(int i = board[0].length-1; i>0; i--){
                    if(!(board[i][boundaryIndex] instanceof EmptyTile)) stillEmpty = false;
                }
                if(stillEmpty){
                    boundaryIndex--;
                }
            }
        } else{
            boundaryIndex = -1;
        }
        return boundaryIndex;
    }

    public static Tile[][] increaseSizeOfBoard(Tile[][] tiles){
        System.out.println("shape is " + tiles.length + " " + tiles[0].length);
        Tile[][] tilesUpdated = new Tile[tiles.length+2][tiles[0].length+2];
        for(int i = 0; i<tiles.length+2; i++){
            for(int j = 0; j<tiles[0].length+2; j++){
                if(  (i>=1 && i<tiles.length+1)
                   &&(j>=1 && j<tiles[0].length+1)){
                    tilesUpdated[i][j] = tiles[i-1][j-1];
                }
                else{
                    tilesUpdated[i][j] = new EmptyTile();
                }
            }
        }
        return tilesUpdated;
    }

    public int countPoints(int x, int y){
        Tile[][] extendedBoard = increaseSizeOfBoard(this.tiles);
        x++;
        x++;
        y++;
        y++;
        return Tile.sumSides(extendedBoard[x][y], extendedBoard[x-1][y], "north")
             + Tile.sumSides(extendedBoard[x][y], extendedBoard[x+1][y], "south")
             + Tile.sumSides(extendedBoard[x][y], extendedBoard[x][y+1], "east")
             + Tile.sumSides(extendedBoard[x][y], extendedBoard[x][y-1], "west");
    }

}
