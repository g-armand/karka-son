package karkason;

public class Cell {

    private char content;
    private int owner;

    //when owner is -1, no owner is possible
     public Cell(char content){
        this.content = content;
        this.owner = 0;
    }

    public Cell(char content, int owner){
        this.content = content;
        this.owner = owner;
    }

    public char getChar(){
        return this.content;
    }

    public int getInt(){
        return Character.getNumericValue(this.getChar());
    }

    public int getOwner(){
        return this.owner;
    }

    public void setOwner(int newOwner){
        if(this.owner!=-1){
            this.owner = newOwner;
        }
    }

    public static Cell[][] toCellMatrix(char[][] characterMatrix){
        Cell[][] newCellMatrix = new Cell[characterMatrix.length][characterMatrix[0].length];
        for(int i=0; i<characterMatrix.length; i++){
            for(int j=0;j<characterMatrix[0].length; j++){
                newCellMatrix[i][j] = new Cell(characterMatrix[i][j]);
            }
        }
        return newCellMatrix;
    }

    @Override
    public Object clone(){
        return new Cell(this.getChar(), this.getOwner());
    }

    public boolean equals(Cell other) {
        return other.content==this.content &&
                (other.owner==this.owner || other.owner == 0);
    }
}
