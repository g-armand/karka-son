package domino;

import java.util.Objects;
import java.util.Random;

public class Tile {

    public char[][] content = new char [5][5];
    int rows = 5; // nombre de rangs

    public Tile(){
        Random a = new Random();
        for (int i = 0; i<5; i++){
            for(int j = 0; j<5; j++){
                //north and south sides
                if((i==0||i==4) && j<4 && j>0 ){
                    this.content[i][j] = (char)(a.nextInt(2)+'0');
                }
                //west and east sides
                else if((j==0 || j == 4) && i<4 && i>0 ){
                    this.content[i][j] = (char)(a.nextInt(2)+'0');
                } else {
                    this.content[i][j] = ' ';
                }
            }
        }
    }

    public boolean joinable(Tile other, String orientation){
        boolean result = false;
        if(orientation.equals("north")) {
            result = (this.content[0][1] == other.content[4][1] &&
                    this.content[0][2] == other.content[4][2] &&
                    this.content[0][3] == other.content[4][3]);
        } else if (orientation.equals("south")) {
            result = (other.content[0][1] == this.content[4][1] &&
                    other.content[0][2] == this.content[4][2] &&
                    other.content[0][3] == this.content[4][3]);
        } else if (orientation.equals("west")) {
            result = (this.content[1][0] == other.content[1][4] &&
                    this.content[2][0] == other.content[2][4] &&
                    this.content[3][0] == other.content[3][4]);
        } else if (orientation.equals("east")) {
            result = (other.content[1][0] == this.content[1][4] &&
                    other.content[2][0] == this.content[2][4] &&
                    other.content[3][0] == this.content[3][4]);
        } else {
            System.out.println("wrong orientation");
        }
        return result;
    }


    public String getLine(int index){
        String carchain = "";
        for (int j = 0; j < this.rows; j++) {
            carchain = carchain + this.content[index][j];
        }
        return carchain;
    }

    @Override
    public String toString() {
        return getLine(0) +'\n' +
                getLine(1) +'\n' +
                getLine(2) +'\n' +
                getLine(3) +'\n' +
                getLine(4) +'\n';
    }

    public void spin(String rotation){
        int n = this.rows;
        // TRANSPOSE
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                char temp = this.content[i][j];
                this.content[i][j] = this.content[j][i];
                this.content[j][i] = temp;
            }
        }

        //REVERSE ROWS
        for (int i = 0; i < n; i++) {
            int low = 0, high = n - 1;
            while (low < high) {
                if (Objects.equals(rotation, "droite")){
                    char temp = this.content[i][low];
                    this.content[i][low] = this.content[i][high];
                    this.content[i][high] = temp;
                    low++;
                    high--;
                }
                if (Objects.equals(rotation, "gauche")){
                    char temp = this.content[low][i];
                    this.content[low][i] = this.content[high][i];
                    this.content[high][i] = temp;
                    low++;
                    high--;
                }
            }
        }
    }

    public static int sumSides(Tile centerTile, Tile sideTile, String side){
        int result = 0;
        if(!(sideTile instanceof EmptyTile)){
            switch (side) {
                case "north":
                    result = Character.getNumericValue(centerTile.content[0][1]) + Character.getNumericValue(sideTile.content[4][1]) +
                            Character.getNumericValue(centerTile.content[0][2]) + Character.getNumericValue(sideTile.content[4][2]) +
                            Character.getNumericValue(centerTile.content[0][3]) + Character.getNumericValue(sideTile.content[4][3]);
                    break;
                case "south":
                    result = Character.getNumericValue(centerTile.content[4][1]) + Character.getNumericValue(sideTile.content[0][1]) +
                            Character.getNumericValue(centerTile.content[4][2]) + Character.getNumericValue(sideTile.content[0][2]) +
                            Character.getNumericValue(centerTile.content[4][3]) + Character.getNumericValue(sideTile.content[0][3]);
                    break;
                case "east":
                    result = Character.getNumericValue(centerTile.content[1][4]) + Character.getNumericValue(sideTile.content[1][0]) +
                            Character.getNumericValue(centerTile.content[2][4]) + Character.getNumericValue(sideTile.content[2][0]) +
                            Character.getNumericValue(centerTile.content[3][4]) + Character.getNumericValue(sideTile.content[3][0]);
                    break;
                case "west":
                    result = Character.getNumericValue(centerTile.content[1][0]) + Character.getNumericValue(sideTile.content[1][4]) +
                            Character.getNumericValue(centerTile.content[2][0]) + Character.getNumericValue(sideTile.content[2][4]) +
                            Character.getNumericValue(centerTile.content[3][0]) + Character.getNumericValue(sideTile.content[3][4]);
                    break;
            }
        }
        return result;
    }
}
