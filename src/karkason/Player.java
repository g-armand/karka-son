package karkason;

public class Player {
    boolean isHuman;
    String name;
    int points;

    public Player(String name){
        this.name = name;
        this.points = 0;
        setIsHuman(true);
    }

    public Player(String name, int points){
        this.name = name;
        this.points = points;
        setIsHuman(true);
    }

    public void setIsHuman(boolean value){
        this.isHuman = value;
    }


}
