package Internals;

public class Instruction {


    private final String _functionName;
    private final int _id;
    private InstructionArgumentTypeEnum _params;
    private int _nSteps=0;
    private int _estimatedSteps;
    /**
     *
     * @param functionName
     * @param functionArg Can be "value","dir","var" or ""
     * @return FunctionNumber
     */
    public Instruction(String functionName, String functionArg, int nSteps , int id,Integer estimatedSteps){
        this._functionName = functionName;
        switch (functionArg){
            case "Dir": _params= InstructionArgumentTypeEnum.Dir; break;
            case "Value": _params= InstructionArgumentTypeEnum.Value; break;
            case "Var" : _params= InstructionArgumentTypeEnum.Var;break;
            case "" : _params= InstructionArgumentTypeEnum.None; break;
            default: _params= InstructionArgumentTypeEnum.None;break;
        }
        _id=id;
        _nSteps=nSteps;
        if(estimatedSteps == null){
            this._estimatedSteps=nSteps;
        }else{
            this._estimatedSteps=estimatedSteps;
        }
    }

    public String getName(){
        return _functionName;
    }

    public int getId(){
        return _id;
    }

    public Boolean getBooleanParam(){
        if(_params.equals(InstructionArgumentTypeEnum.None)){
            return Boolean.FALSE;
        }else{
            return Boolean.TRUE;
        }
    }

    public InstructionArgumentTypeEnum getParam(){
        return _params;
    }

    public int getNSteps() {
        return _nSteps;
    }

    public int getEstimatedSteps(){
        return _estimatedSteps;
    }

}
