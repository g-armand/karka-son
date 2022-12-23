package karkason;

import java.util.Random;

public class DominoTile extends Tile {
    public DominoTile(){
        Random a = new Random();
        for (int i = 0; i<5; i++){
            for(int j = 0; j<5; j++){
                //north and south sides
                if((i==0||i==4) && j<4 && j>0 ){
                    this.content[i][j] = new Cell((char)(a.nextInt(2)+'0'));
                }
                //west and east sides
                else if((j==0 || j == 4) && i<4 && i>0 ){
                    this.content[i][j] = new Cell((char)(a.nextInt(2)+'0'));
                } else {
                    this.content[i][j] = new Cell(' ');
                }
            }
        }
    }

    public static int sumSides(Tile centerTile, Tile sideTile, String side){
        int result = 0;
        if(!(sideTile instanceof EmptyTile)){
            switch (side) {
                case "north":
                    result = centerTile.content[0][1].getInt() + sideTile.content[4][1].getInt() +
                            centerTile.content[0][2].getInt() + sideTile.content[4][2].getInt() +
                            centerTile.content[0][3].getInt() + sideTile.content[4][3].getInt();
                    break;
                case "south":
                    result = centerTile.content[4][1].getInt()+ sideTile.content[0][1].getInt()+
                            centerTile.content[4][2].getInt()+ sideTile.content[0][2].getInt()+
                            centerTile.content[4][3].getInt()+ sideTile.content[0][3].getInt();
                    break;
                case "east":
                    result = centerTile.content[1][4].getInt()+ sideTile.content[1][0].getInt()+
                            centerTile.content[2][4].getInt()+ sideTile.content[2][0].getInt()+
                            centerTile.content[3][4].getInt()+ sideTile.content[3][0].getInt();
                    break;
                case "west":
                    result = centerTile.content[1][0].getInt()+ sideTile.content[1][4].getInt()+
                            centerTile.content[2][0].getInt()+ sideTile.content[2][4].getInt()+
                            centerTile.content[3][0].getInt()+ sideTile.content[3][4].getInt();
                    break;
            }
        }
        return result;
    }
}
