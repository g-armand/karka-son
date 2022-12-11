package domino;
import java.util.Objects;
import java.util.Random;

public class Tile {

    public Cell[][] content = new Cell[5][5];
    int rows = 5; // nombre de rangs


    public boolean joinable(Tile other, String orientation){
        boolean result = false;
        if(orientation.equals("north")) {
            result = (this.content[0][1].getChar() == other.content[4][1].getChar()) &&
                    (this.content[0][2].getChar() == other.content[4][2].getChar()) &&
                    (this.content[0][3].getChar() == other.content[4][3].getChar());
        } else if (orientation.equals("south")) {
            result = (other.content[0][1].getChar() == this.content[4][1].getChar()) &&
                    (other.content[0][2].getChar() == this.content[4][2].getChar()) &&
                    (other.content[0][3].getChar() == this.content[4][3].getChar());
        } else if (orientation.equals("west")) {
            result = (this.content[1][0].getChar() == other.content[1][4].getChar()) &&
                    (this.content[2][0].getChar() == other.content[2][4].getChar()) &&
                    (this.content[3][0].getChar() == other.content[3][4].getChar());
        } else if (orientation.equals("east")) {
            result = (other.content[1][0].getChar() == this.content[1][4].getChar()) &&
                    (other.content[2][0].getChar() == this.content[2][4].getChar()) &&
                    (other.content[3][0].getChar() == this.content[3][4].getChar());
        } else {
            System.out.println("wrong orientation");
        }
        return result;
    }


    public String getLine(int index, boolean showOwnership){
        String carchain = "";
        for (int j = 0; j < this.rows; j++) {
            if(showOwnership){
                carchain = carchain + this.content[index][j].getOwner();
            } else{
                carchain = carchain + this.content[index][j].getChar();
            }
        }
        return carchain;
    }

    @Override
    public String toString() {
        return getLine(0, false) +'\n' +
                getLine(1, false) +'\n' +
                getLine(2, false) +'\n' +
                getLine(3, false) +'\n' +
                getLine(4, false) +'\n';
    }

    public void spin(String rotation){
        int n = this.rows;
        // TRANSPOSE
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                Cell temp = this.content[i][j];
                this.content[i][j] = this.content[j][i];
                this.content[j][i] = temp;
            }
        }

        //REVERSE ROWS
        for (int i = 0; i < n; i++) {
            int low = 0, high = n - 1;
            while (low < high) {
                if (Objects.equals(rotation, "droite")){
                    Cell temp = this.content[i][low];
                    this.content[i][low] = this.content[i][high];
                    this.content[i][high] = temp;
                    low++;
                    high--;
                }
                if (Objects.equals(rotation, "gauche")){
                    Cell temp = this.content[low][i];
                    this.content[low][i] = this.content[high][i];
                    this.content[high][i] = temp;
                    low++;
                    high--;
                }
            }
        }
    }

}
