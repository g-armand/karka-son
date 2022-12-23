package karkason;

public class DominoBoard extends Board {
    public DominoBoard(){
        super();
        this.tiles[1][1] = new DominoTile();
    }

    public int countPoints(int x, int y){
        Tile[][] extendedBoard = increaseSizeOfBoard(this.tiles);
        x+=2;
        y+=2;
        return DominoTile.sumSides(extendedBoard[x][y], extendedBoard[x-1][y], "north")
                + DominoTile.sumSides(extendedBoard[x][y], extendedBoard[x+1][y], "south")
                + DominoTile.sumSides(extendedBoard[x][y], extendedBoard[x][y+1], "east")
                + DominoTile.sumSides(extendedBoard[x][y], extendedBoard[x][y-1], "west");
    }
}
