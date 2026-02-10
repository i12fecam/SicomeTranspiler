// Generated from /home/abi/Proyectos/SicomeTranspiler/grammar/Sicome.g4 by ANTLR 4.13.2
package Parsing;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SicomeParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SicomeVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code cableProgram}
	 * labeled alternative in {@link SicomeParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCableProgram(SicomeParser.CableProgramContext ctx);
	/**
	 * Visit a parse tree produced by the {@code microProgram}
	 * labeled alternative in {@link SicomeParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMicroProgram(SicomeParser.MicroProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link SicomeParser#variablesBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariablesBlock(SicomeParser.VariablesBlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simpleVariableDeclaration}
	 * labeled alternative in {@link SicomeParser#variableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleVariableDeclaration(SicomeParser.SimpleVariableDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code vectorVariableDeclaration}
	 * labeled alternative in {@link SicomeParser#variableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVectorVariableDeclaration(SicomeParser.VectorVariableDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SicomeParser#programBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgramBlock(SicomeParser.ProgramBlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code instructionUse}
	 * labeled alternative in {@link SicomeParser#programLine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstructionUse(SicomeParser.InstructionUseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code markUse}
	 * labeled alternative in {@link SicomeParser#programLine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMarkUse(SicomeParser.MarkUseContext ctx);
	/**
	 * Visit a parse tree produced by {@link SicomeParser#instructionUseArgument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstructionUseArgument(SicomeParser.InstructionUseArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link SicomeParser#instructionBlockCable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstructionBlockCable(SicomeParser.InstructionBlockCableContext ctx);
	/**
	 * Visit a parse tree produced by {@link SicomeParser#instructionCable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstructionCable(SicomeParser.InstructionCableContext ctx);
	/**
	 * Visit a parse tree produced by {@link SicomeParser#fetchDefinitionCable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFetchDefinitionCable(SicomeParser.FetchDefinitionCableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simpleCableStep}
	 * labeled alternative in {@link SicomeParser#stepCable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleCableStep(SicomeParser.SimpleCableStepContext ctx);
	/**
	 * Visit a parse tree produced by the {@code conditionalCableStepBlock}
	 * labeled alternative in {@link SicomeParser#stepCable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionalCableStepBlock(SicomeParser.ConditionalCableStepBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link SicomeParser#conditionalStepCable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionalStepCable(SicomeParser.ConditionalStepCableContext ctx);
	/**
	 * Visit a parse tree produced by {@link SicomeParser#microIntr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMicroIntr(SicomeParser.MicroIntrContext ctx);
	/**
	 * Visit a parse tree produced by {@link SicomeParser#permissibleLOADSRMicroIntr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPermissibleLOADSRMicroIntr(SicomeParser.PermissibleLOADSRMicroIntrContext ctx);
	/**
	 * Visit a parse tree produced by {@link SicomeParser#statusLogicBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatusLogicBlock(SicomeParser.StatusLogicBlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simpleStatusLogic}
	 * labeled alternative in {@link SicomeParser#statusLogic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleStatusLogic(SicomeParser.SimpleStatusLogicContext ctx);
	/**
	 * Visit a parse tree produced by the {@code complexStatusLogic}
	 * labeled alternative in {@link SicomeParser#statusLogic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComplexStatusLogic(SicomeParser.ComplexStatusLogicContext ctx);
	/**
	 * Visit a parse tree produced by {@link SicomeParser#statusLogicOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatusLogicOption(SicomeParser.StatusLogicOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SicomeParser#instructionBlockMicro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstructionBlockMicro(SicomeParser.InstructionBlockMicroContext ctx);
	/**
	 * Visit a parse tree produced by {@link SicomeParser#fetchDefinitionMicro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFetchDefinitionMicro(SicomeParser.FetchDefinitionMicroContext ctx);
	/**
	 * Visit a parse tree produced by {@link SicomeParser#instructionMicro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstructionMicro(SicomeParser.InstructionMicroContext ctx);
	/**
	 * Visit a parse tree produced by {@link SicomeParser#stepMicro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStepMicro(SicomeParser.StepMicroContext ctx);
	/**
	 * Visit a parse tree produced by the {@code instructionBifLogicArgument}
	 * labeled alternative in {@link SicomeParser#bifLogicArgument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstructionBifLogicArgument(SicomeParser.InstructionBifLogicArgumentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code startBifLogicArgument}
	 * labeled alternative in {@link SicomeParser#bifLogicArgument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStartBifLogicArgument(SicomeParser.StartBifLogicArgumentContext ctx);
}