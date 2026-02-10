// Generated from /home/abi/Proyectos/SicomeTranspiler/grammar/Sicome.g4 by ANTLR 4.13.2
package Parsing;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SicomeParser}.
 */
public interface SicomeListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code cableProgram}
	 * labeled alternative in {@link SicomeParser#program}.
	 * @param ctx the parse tree
	 */
	void enterCableProgram(SicomeParser.CableProgramContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cableProgram}
	 * labeled alternative in {@link SicomeParser#program}.
	 * @param ctx the parse tree
	 */
	void exitCableProgram(SicomeParser.CableProgramContext ctx);
	/**
	 * Enter a parse tree produced by the {@code microProgram}
	 * labeled alternative in {@link SicomeParser#program}.
	 * @param ctx the parse tree
	 */
	void enterMicroProgram(SicomeParser.MicroProgramContext ctx);
	/**
	 * Exit a parse tree produced by the {@code microProgram}
	 * labeled alternative in {@link SicomeParser#program}.
	 * @param ctx the parse tree
	 */
	void exitMicroProgram(SicomeParser.MicroProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link SicomeParser#variablesBlock}.
	 * @param ctx the parse tree
	 */
	void enterVariablesBlock(SicomeParser.VariablesBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link SicomeParser#variablesBlock}.
	 * @param ctx the parse tree
	 */
	void exitVariablesBlock(SicomeParser.VariablesBlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simpleVariableDeclaration}
	 * labeled alternative in {@link SicomeParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterSimpleVariableDeclaration(SicomeParser.SimpleVariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simpleVariableDeclaration}
	 * labeled alternative in {@link SicomeParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitSimpleVariableDeclaration(SicomeParser.SimpleVariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code vectorVariableDeclaration}
	 * labeled alternative in {@link SicomeParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVectorVariableDeclaration(SicomeParser.VectorVariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code vectorVariableDeclaration}
	 * labeled alternative in {@link SicomeParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVectorVariableDeclaration(SicomeParser.VectorVariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SicomeParser#programBlock}.
	 * @param ctx the parse tree
	 */
	void enterProgramBlock(SicomeParser.ProgramBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link SicomeParser#programBlock}.
	 * @param ctx the parse tree
	 */
	void exitProgramBlock(SicomeParser.ProgramBlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code instructionUse}
	 * labeled alternative in {@link SicomeParser#programLine}.
	 * @param ctx the parse tree
	 */
	void enterInstructionUse(SicomeParser.InstructionUseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code instructionUse}
	 * labeled alternative in {@link SicomeParser#programLine}.
	 * @param ctx the parse tree
	 */
	void exitInstructionUse(SicomeParser.InstructionUseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code markUse}
	 * labeled alternative in {@link SicomeParser#programLine}.
	 * @param ctx the parse tree
	 */
	void enterMarkUse(SicomeParser.MarkUseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code markUse}
	 * labeled alternative in {@link SicomeParser#programLine}.
	 * @param ctx the parse tree
	 */
	void exitMarkUse(SicomeParser.MarkUseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SicomeParser#instructionUseArgument}.
	 * @param ctx the parse tree
	 */
	void enterInstructionUseArgument(SicomeParser.InstructionUseArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link SicomeParser#instructionUseArgument}.
	 * @param ctx the parse tree
	 */
	void exitInstructionUseArgument(SicomeParser.InstructionUseArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link SicomeParser#instructionBlockCable}.
	 * @param ctx the parse tree
	 */
	void enterInstructionBlockCable(SicomeParser.InstructionBlockCableContext ctx);
	/**
	 * Exit a parse tree produced by {@link SicomeParser#instructionBlockCable}.
	 * @param ctx the parse tree
	 */
	void exitInstructionBlockCable(SicomeParser.InstructionBlockCableContext ctx);
	/**
	 * Enter a parse tree produced by {@link SicomeParser#instructionCable}.
	 * @param ctx the parse tree
	 */
	void enterInstructionCable(SicomeParser.InstructionCableContext ctx);
	/**
	 * Exit a parse tree produced by {@link SicomeParser#instructionCable}.
	 * @param ctx the parse tree
	 */
	void exitInstructionCable(SicomeParser.InstructionCableContext ctx);
	/**
	 * Enter a parse tree produced by {@link SicomeParser#fetchDefinitionCable}.
	 * @param ctx the parse tree
	 */
	void enterFetchDefinitionCable(SicomeParser.FetchDefinitionCableContext ctx);
	/**
	 * Exit a parse tree produced by {@link SicomeParser#fetchDefinitionCable}.
	 * @param ctx the parse tree
	 */
	void exitFetchDefinitionCable(SicomeParser.FetchDefinitionCableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simpleCableStep}
	 * labeled alternative in {@link SicomeParser#stepCable}.
	 * @param ctx the parse tree
	 */
	void enterSimpleCableStep(SicomeParser.SimpleCableStepContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simpleCableStep}
	 * labeled alternative in {@link SicomeParser#stepCable}.
	 * @param ctx the parse tree
	 */
	void exitSimpleCableStep(SicomeParser.SimpleCableStepContext ctx);
	/**
	 * Enter a parse tree produced by the {@code conditionalCableStepBlock}
	 * labeled alternative in {@link SicomeParser#stepCable}.
	 * @param ctx the parse tree
	 */
	void enterConditionalCableStepBlock(SicomeParser.ConditionalCableStepBlockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code conditionalCableStepBlock}
	 * labeled alternative in {@link SicomeParser#stepCable}.
	 * @param ctx the parse tree
	 */
	void exitConditionalCableStepBlock(SicomeParser.ConditionalCableStepBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link SicomeParser#conditionalStepCable}.
	 * @param ctx the parse tree
	 */
	void enterConditionalStepCable(SicomeParser.ConditionalStepCableContext ctx);
	/**
	 * Exit a parse tree produced by {@link SicomeParser#conditionalStepCable}.
	 * @param ctx the parse tree
	 */
	void exitConditionalStepCable(SicomeParser.ConditionalStepCableContext ctx);
	/**
	 * Enter a parse tree produced by {@link SicomeParser#microIntr}.
	 * @param ctx the parse tree
	 */
	void enterMicroIntr(SicomeParser.MicroIntrContext ctx);
	/**
	 * Exit a parse tree produced by {@link SicomeParser#microIntr}.
	 * @param ctx the parse tree
	 */
	void exitMicroIntr(SicomeParser.MicroIntrContext ctx);
	/**
	 * Enter a parse tree produced by {@link SicomeParser#permissibleLOADSRMicroIntr}.
	 * @param ctx the parse tree
	 */
	void enterPermissibleLOADSRMicroIntr(SicomeParser.PermissibleLOADSRMicroIntrContext ctx);
	/**
	 * Exit a parse tree produced by {@link SicomeParser#permissibleLOADSRMicroIntr}.
	 * @param ctx the parse tree
	 */
	void exitPermissibleLOADSRMicroIntr(SicomeParser.PermissibleLOADSRMicroIntrContext ctx);
	/**
	 * Enter a parse tree produced by {@link SicomeParser#statusLogicBlock}.
	 * @param ctx the parse tree
	 */
	void enterStatusLogicBlock(SicomeParser.StatusLogicBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link SicomeParser#statusLogicBlock}.
	 * @param ctx the parse tree
	 */
	void exitStatusLogicBlock(SicomeParser.StatusLogicBlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simpleStatusLogic}
	 * labeled alternative in {@link SicomeParser#statusLogic}.
	 * @param ctx the parse tree
	 */
	void enterSimpleStatusLogic(SicomeParser.SimpleStatusLogicContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simpleStatusLogic}
	 * labeled alternative in {@link SicomeParser#statusLogic}.
	 * @param ctx the parse tree
	 */
	void exitSimpleStatusLogic(SicomeParser.SimpleStatusLogicContext ctx);
	/**
	 * Enter a parse tree produced by the {@code complexStatusLogic}
	 * labeled alternative in {@link SicomeParser#statusLogic}.
	 * @param ctx the parse tree
	 */
	void enterComplexStatusLogic(SicomeParser.ComplexStatusLogicContext ctx);
	/**
	 * Exit a parse tree produced by the {@code complexStatusLogic}
	 * labeled alternative in {@link SicomeParser#statusLogic}.
	 * @param ctx the parse tree
	 */
	void exitComplexStatusLogic(SicomeParser.ComplexStatusLogicContext ctx);
	/**
	 * Enter a parse tree produced by {@link SicomeParser#statusLogicOption}.
	 * @param ctx the parse tree
	 */
	void enterStatusLogicOption(SicomeParser.StatusLogicOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SicomeParser#statusLogicOption}.
	 * @param ctx the parse tree
	 */
	void exitStatusLogicOption(SicomeParser.StatusLogicOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SicomeParser#instructionBlockMicro}.
	 * @param ctx the parse tree
	 */
	void enterInstructionBlockMicro(SicomeParser.InstructionBlockMicroContext ctx);
	/**
	 * Exit a parse tree produced by {@link SicomeParser#instructionBlockMicro}.
	 * @param ctx the parse tree
	 */
	void exitInstructionBlockMicro(SicomeParser.InstructionBlockMicroContext ctx);
	/**
	 * Enter a parse tree produced by {@link SicomeParser#fetchDefinitionMicro}.
	 * @param ctx the parse tree
	 */
	void enterFetchDefinitionMicro(SicomeParser.FetchDefinitionMicroContext ctx);
	/**
	 * Exit a parse tree produced by {@link SicomeParser#fetchDefinitionMicro}.
	 * @param ctx the parse tree
	 */
	void exitFetchDefinitionMicro(SicomeParser.FetchDefinitionMicroContext ctx);
	/**
	 * Enter a parse tree produced by {@link SicomeParser#instructionMicro}.
	 * @param ctx the parse tree
	 */
	void enterInstructionMicro(SicomeParser.InstructionMicroContext ctx);
	/**
	 * Exit a parse tree produced by {@link SicomeParser#instructionMicro}.
	 * @param ctx the parse tree
	 */
	void exitInstructionMicro(SicomeParser.InstructionMicroContext ctx);
	/**
	 * Enter a parse tree produced by {@link SicomeParser#stepMicro}.
	 * @param ctx the parse tree
	 */
	void enterStepMicro(SicomeParser.StepMicroContext ctx);
	/**
	 * Exit a parse tree produced by {@link SicomeParser#stepMicro}.
	 * @param ctx the parse tree
	 */
	void exitStepMicro(SicomeParser.StepMicroContext ctx);
	/**
	 * Enter a parse tree produced by the {@code instructionBifLogicArgument}
	 * labeled alternative in {@link SicomeParser#bifLogicArgument}.
	 * @param ctx the parse tree
	 */
	void enterInstructionBifLogicArgument(SicomeParser.InstructionBifLogicArgumentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code instructionBifLogicArgument}
	 * labeled alternative in {@link SicomeParser#bifLogicArgument}.
	 * @param ctx the parse tree
	 */
	void exitInstructionBifLogicArgument(SicomeParser.InstructionBifLogicArgumentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code startBifLogicArgument}
	 * labeled alternative in {@link SicomeParser#bifLogicArgument}.
	 * @param ctx the parse tree
	 */
	void enterStartBifLogicArgument(SicomeParser.StartBifLogicArgumentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code startBifLogicArgument}
	 * labeled alternative in {@link SicomeParser#bifLogicArgument}.
	 * @param ctx the parse tree
	 */
	void exitStartBifLogicArgument(SicomeParser.StartBifLogicArgumentContext ctx);
}