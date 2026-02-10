package Internals;

import Internals.Errors.EspecificationException;

import java.util.*;

public class SymbolTable {
    /*-------------------------------------------------------------------
        functions
     */
    private final Vector<Instruction> instructions = new Vector<>();

    /**
     * Registers a new function
     *
     * @param functionName    the function name
     * @param functionArg     the function arguments
     * @param steps           the number of steps of the function
     * @param nEstimatedSteps
     * @return the id of the registered function
     */
    public int addInstruction(String functionName, String functionArg, int steps, Integer nEstimatedSteps) throws RuntimeException {
        for(Instruction fun: instructions){
            if(Objects.equals(fun.getName(), functionName)){
                throw new RuntimeException("Ya existe una función con este nombre");
            }
        }
        instructions.add(new Instruction(functionName,functionArg,steps, instructions.size(),nEstimatedSteps));
        return instructions.size()-1;
    }


    /**
     * Gets the number of steps of the given function
     * @param idInstruction the fuction we are referring
     * @return the number of steps
     */
    public int getSteps(int idInstruction) {
        return instructions.elementAt(idInstruction).getNSteps();
    }

    /**
     * Gets all the function defined
     * @return The functions
     */
    public  Vector<Instruction> getInstructions(){
        return instructions;
    }

    /**
     * Returns the type of argument given an instruction name
     * @param InstructionName the name of the instruction
     * @return type of argument
     */
    public InstructionArgumentTypeEnum getArgumentType(String InstructionName){
        for(Instruction fun: instructions){
            if(fun.getName().equals(InstructionName)){
                return fun.getParam();
            }
        }
        return null;
    }

    public Instruction getInstructionById(int id){
        for(Instruction fun: instructions){
            if(fun.getId()==id){
                return fun;
            }
        }
        return null;
    }

    public Instruction getInstructionByName(String name){
        for(Instruction fun: instructions){
            if(Objects.equals(fun.getName(), name)){
                return fun;
            }
        }
        return null;
    }


















    /*-----------------------------------------------------------------------------------------------------
        Variables
     */
    private List<Variable> variables = new ArrayList<>();

    /**
     * Adds a simple variable
     * @param variableName name of the variable
     * @param initializedValue initial value
     */
    public void addSimpleVariable(String variableName, int initializedValue ) throws RuntimeException {
        if(isVariable(variableName)){
            throw new EspecificationException("Ya existe una variable o label con el mismo nombre");
        }
        List<Integer> value = new ArrayList<>();
        value.add(initializedValue);
        variables.add(new Variable(variableName, getNextAvailablePos(),1,value));
    }

    /**
     * Adds a vector variable that is initialized with the same value
     * @param variableName the name of the variable
     * @param reservedSpace the size of the vector
     * @param initializedValue the value that all the vector spaces are initialized to
     */
    public void addVectorVariable(String variableName, int reservedSpace,int initializedValue) throws RuntimeException {
        if(isVariable(variableName)){
            throw new RuntimeException("Ya existe una variable o label con el mismo nombre");
        }
        List<Integer> values = new ArrayList<>(Collections.nCopies(reservedSpace, initializedValue));
        variables.add(new Variable(variableName, getNextAvailablePos(),reservedSpace,values));
    }

    /**
     * Adds a vector variable that is initialized specifying each value
     * @param variableName the name of the variable
     * @param reservedSpace the size of the vector
     * @param initializedValues A list with the same size as the size of the vector, filled with the initialized value as the vector
     */
    public void addVectorVariable(String variableName, int reservedSpace, List<Integer> initializedValues) throws RuntimeException {
        if(isVariable(variableName)){
            throw new EspecificationException("Ya existe una variable o label con el mismo nombre");
        }
        variables.add(new Variable(variableName, getNextAvailablePos(),reservedSpace,initializedValues));
    }

    /**
     * Gets the registered variables
     * @return registered variables
     */
    public List<Variable> getVariables(){
        return variables;
    }

    /**
     * Gets the postion of the variable in memory
     * @param variableName the name of the variable
     * @param offset 0 if simple value or the index in case it is a vector
     * @return the position in memory, -1 in case of error
     */
    public Integer getPosFromVariable(String variableName, int offset){
        for(Variable var:variables){
            if(var.name().equals(variableName)){
                    return var.getPosition(offset);
            }
        }
        return null;
    }

    /**
     * Checks if the given string corresponds to a variable
     * @param variableName the string to be checked
     * @return true if it corresponds to a variable
     */
    public boolean isVariable(String variableName) {
        if(getPosFromVariable(variableName,0)==null){
            return false;
        }
        return true;
    }

    /**
     * Gets the next available position in memory not reserved
     * @return next available position in memory not reserved
     */
    private int getNextAvailablePos(){
        if(variables.size()==0){
            return 0;
        } else {
            return variables.get(variables.size()-1).getEndPosition()+1;
        }
    }

    /**
     * When all the instructions have been reserved in memory, will return the position in memory in which the instructions can be written
     * @return the position in memory in which the instructions can start to be written
     */
    public int getStartOfInstruction(){
        return getNextAvailablePos();
    }
































    /*--------------------------------------------------------------------------------
        Labels
     */

    private final Map<String,Integer> labels = new HashMap<>();

    /**
     * Registers a new label
     * @param label name of the label
     * @param pos position in memory the label is referring to,Assummes the postion 0 refers to the first instruction
     */
    public void addLabel(String label,int pos){
        if(isLabel(label)){
            throw new EspecificationException("Ya existe una variable o label con el mismo nombre");
        }

        labels.put(label,pos);
    }

    /**
     * Gets the position in memory the label is referring to, taking to account the reserved positions of the variables
     * @param label
     * @return position that the label refers, or -1 in case of error
     */
    public int getPosFromLabel(String label){
        if(labels.get(label) == null) return -1;
        return labels.get(label) + getStartOfInstruction();
    }

    /**
     * Checks if the given string refers to a registered label
     * @param labelName the given string
     * @return true if it refers to a registered label, else no
     */

    public boolean isLabel(String labelName) {
        return labels.get(labelName) != null;
    }


    /*--------------------------------------------------------------------------
    Bifurcation Logic
     */
    private Vector<BifurcationLogic> bifurcationLogics = new Vector<>();
    public int addBifurcationLogic(String name,boolean needsArg) throws RuntimeException {
        if(bifurcationLogics.size()==16){
            return -1;
            //throw new RuntimeException("El número de reglas de bifurcación tiene un máximo de 16");
        }
        for(var bifLogic:bifurcationLogics){
            if(bifLogic.getName().equals(name)){
                return -2;
                //throw new RuntimeException("Ya existe una regla de bifurcacion con el mismo nombre");
            }
        }

        bifurcationLogics.add(new BifurcationLogic(bifurcationLogics.size(),name,needsArg));
        return bifurcationLogics.size()-1;
    }

    public boolean doesBifurcationLogicNeedArgument(String name) throws RuntimeException {
        for(var BifLogic:bifurcationLogics){
            if(BifLogic.getName().equals(name)){
                return BifLogic.needsArg();
            }
        }
        throw new RuntimeException("La logica de bifurcación que se refiere no ha sido definida");
    }

    public BifurcationLogic getBifurcationLogic(String name){
        for(var bifLogic: bifurcationLogics){
            if(bifLogic.getName().equals(name)){
                return bifLogic;
            }
        }
        return null;
    }

}
