package domino;

import java.util.Arrays;
import java.util.Random;

public class Tuile {

    char[][] content = new char [5][5];

    public Tuile(){
        Random a = new Random();
        for (int i = 0; i<5; i++){
            for(int j = 0; j<5; j++){
                //north and south sides
                if((i==0||i==4) && j<5 && j>0 ){
                    this.content[i][j] = (char)a.nextInt();
                }
            }
        }

    }


    public boolean joinable(Tuile other){
//        return this.getCoteAtDirection(joinDirection).estJoignable(other.getCoteAtDirection(joinDirection.opposed()));
        return true;
    }


    public void spin(boolean droite){
    }


    //not good because cannot chain multiple Tuile on a same line
    public String toString(){
        return "";
    }
}
