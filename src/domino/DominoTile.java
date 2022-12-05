package domino;

import java.util.Random;

public class DominoTile extends Tile{
    public DominoTile(){
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
