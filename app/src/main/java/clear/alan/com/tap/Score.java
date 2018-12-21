package clear.alan.com.tap;


/**
 * Created by Alan on 12/13/2016.
 */
//@JsonIgnoreProperties(ignoreUnknown=true)
public class Score {
    private String name;
    private Long score;
    public Score() {
    }
    public Score(String name,Long score){
        this.name=name;
        this.score=score;
    }
    public String getName(){
        return name;
    }
    public Long getScore(){
        return score;
    }
}
