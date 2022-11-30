package domino;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Tuile {

    char[][] content = new char [5][5];
    int n = 3; // nombre de rangs

    public Tuile(){
        Random a = new Random();
        for (int i = 0; i<5; i++){
            for(int j = 0; j<5; j++){
                //north and south sides
                if((i==0||i==4) && j<4 && j>0 ){
                    this.content[i][j] = (char)(a.nextInt(3)+'0');
                }
                //west and east sides
                else if((j==0 || j == 4) && i<4 && i>0 ){
                    this.content[i][j] = (char)(a.nextInt(3)+'0');
                } else {
                    this.content[i][j] = '.';
                }
            }
        }
    }


    //not finished
    public boolean joinable(Tuile other){
        return (this.content[0][1] == other.content[4][1] &&
                this.content[0][2] == other.content[4][2]);
    }

    public void spin(boolean droite){
    }
// on a besoin de ça?


    //not good because cannot chain multiple Tuile on a same line
    public String toString(){
        return "";
    }

    public void tourner_droite(Tuile tuile, int n, String rotation){

        // TRANSPOSE
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                char temp = tuile.content[i][j];
                tuile.content[i][j] = tuile.content[j][i];
                tuile.content[j][i] = temp;
            }
        }

        //REVERSE ROWS
        for (int i = 0; i < n; i++) {
            int low = 0, high = n - 1;
            while (low < high) {
                if (Objects.equals(rotation, "droite")){
                    char temp = tuile.content[i][low];
                    tuile.content[i][low] = tuile.content[i][high];
                    tuile.content[i][high] = temp;
                    low++;
                    high--;
                }
                if (Objects.equals(rotation, "gauche")){
                    char temp = tuile.content[low][i];
                    tuile.content[low][i] = tuile.content[high][i];
                    tuile.content[high][i] = temp;
                    low++;
                    high--;
                }
            }
        }
    }
}
