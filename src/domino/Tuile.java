package domino;

import java.util.Arrays;
import java.util.Random;

import static domino.Direction.*;

public class Tuile {
    Cote[] cotes = new Cote[4];

    public Tuile(){
        Random a = new Random();
        int b = a.nextInt(3);
        this.cotes[0] = new Cote(North);
        this.cotes[1] = new Cote(South);
        this.cotes[2] = new Cote(West);
        this.cotes[3] = new Cote(East);
    }

    public Cote getCoteAtDirection(Direction direction){
        for(Cote cote: this.cotes){
            if(cote.oriented==direction){
                return cote;
            }
        }
        return null;
    }

    public boolean joinable(Direction joinDirection, Tuile other){
        return this.getCoteAtDirection(joinDirection).estJoignable(other.getCoteAtDirection(joinDirection.opposed()));
    }

    public boolean joinable(Tuile other){
        return this.joinable(North, other) ||
                this.joinable(South, other) ||
                this.joinable(West, other) ||
                this.joinable(East, other);
    }

    public void spin(boolean droite){
        for(Cote cote: this.cotes){
            cote.oriented = droite ? cote.oriented.toRight() : cote.oriented.toLeft();
        }
        if(droite){
            Cote temp = this.cotes[3];
            this.cotes[3] = this.cotes[2];
            this.cotes[2] = this.cotes[1];
            this.cotes[1] = this.cotes[0];
            this.cotes[0] = temp;
        } else{
            Cote temp = this.cotes[0];
            this.cotes[0] = this.cotes[1];
            this.cotes[1] = this.cotes[2];
            this.cotes[2] = this.cotes[3];
            this.cotes[3] = temp;
        }
    }

    //not good because cannot chain multiple Tuile on a same line
    public String toString(){
        return "  " + this.cotes[0].cases[1] + " " + this.cotes[0].cases[1] + " " + this.cotes[0].cases[2] + "  \n" +
                this.cotes[3].cases[0] + "      " + this.cotes[1].cases[0] + "  \n" +
                this.cotes[3].cases[1] + "      " + this.cotes[1].cases[1] + "  \n" +
                this.cotes[3].cases[2] + "      " + this.cotes[1].cases[2] + "  \n" +
                "  " + this.cotes[2].cases[0] + " " + this.cotes[2].cases[1] + " " + this.cotes[2].cases[2] + "  \n";

    }
}
