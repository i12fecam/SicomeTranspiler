package Internals;

import java.util.List;

public class Variable {
    String name;
    int startPosition;
    int endPosition;

    List<Integer> _values;
    //Para vectores
    public Variable(String variableName,int startInMemory, int reservedSpace, List<Integer> initializedValues) {
        assert(variableName!=null);
        assert(initializedValues!=null);
        assert(initializedValues.size() == reservedSpace);

        name =variableName;
        startPosition =startInMemory;
        endPosition =startInMemory+reservedSpace-1;
        _values= initializedValues;
        assert(capacity()==reservedSpace);
    }


    public int capacity(){
        return endPosition - startPosition +1;
    }
    public int getStartPosition(){
        return startPosition;
    }
    public  int getEndPosition(){
        return endPosition;
    }

    public int getPosition(int offset) throws RuntimeException {
        if(offset >= capacity() || offset < 0){
            return -1;
        }
        return startPosition +offset;
    }
    public int getInitialValue(int offset){
        if(offset >= capacity() || offset < 0){
            throw new RuntimeException("El index que desea se encuentra fuera del rango del vector");
        }
        return _values.get(offset);
    }

    public String name() {
        return name;
    }
}
