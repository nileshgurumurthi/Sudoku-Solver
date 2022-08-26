package Model;

public class Level {

    String levelName;
    private int lowerBound;
    private int upperBound;

    public Level(String levelName,int lowerBound, int upperBound){
        this.levelName = levelName;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public int getLowerBound(){
        return lowerBound;
    }

    public int getUpperBound(){
        return upperBound;
    }

    public String getLevelName(){
        return levelName;
    }
}

