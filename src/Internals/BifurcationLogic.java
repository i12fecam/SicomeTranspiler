package Internals;

public class BifurcationLogic {
    private int id;
    private String name;

    private boolean needsArg;
    public BifurcationLogic(int id, String name,boolean needsArg ){
        this.id=id;
        this.name=name;
        this.needsArg=needsArg;
    }

    public boolean needsArg(){
        return needsArg;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }


}
