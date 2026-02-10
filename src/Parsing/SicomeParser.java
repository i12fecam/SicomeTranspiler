// Generated from /home/abi/Proyectos/SicomeTranspiler/grammar/Sicome.g4 by ANTLR 4.13.2
package Parsing;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class SicomeParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, MEMORYVALUE=28, DECNUMBER=29, FLAG=30, IDENTIFIER=31, 
		MICRO_INSTR=32, LINE_COMMENT=33, COMMENT=34, NEWLINE=35, WS=36;
	public static final int
		RULE_program = 0, RULE_variablesBlock = 1, RULE_variableDeclaration = 2, 
		RULE_programBlock = 3, RULE_programLine = 4, RULE_instructionUseArgument = 5, 
		RULE_instructionBlockCable = 6, RULE_instructionCable = 7, RULE_fetchDefinitionCable = 8, 
		RULE_stepCable = 9, RULE_conditionalStepCable = 10, RULE_microIntr = 11, 
		RULE_permissibleLOADSRMicroIntr = 12, RULE_statusLogicBlock = 13, RULE_statusLogic = 14, 
		RULE_statusLogicOption = 15, RULE_instructionBlockMicro = 16, RULE_fetchDefinitionMicro = 17, 
		RULE_instructionMicro = 18, RULE_stepMicro = 19, RULE_bifLogicArgument = 20;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "variablesBlock", "variableDeclaration", "programBlock", "programLine", 
			"instructionUseArgument", "instructionBlockCable", "instructionCable", 
			"fetchDefinitionCable", "stepCable", "conditionalStepCable", "microIntr", 
			"permissibleLOADSRMicroIntr", "statusLogicBlock", "statusLogic", "statusLogicOption", 
			"instructionBlockMicro", "fetchDefinitionMicro", "instructionMicro", 
			"stepMicro", "bifLogicArgument"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'Variables'", "'{'", "'}'", "'='", "';'", "'('", "')'", "','", 
			"'Programa'", "'MARK'", "'@Cable'", "'Instrucciones'", "'Value'", "'Dir'", 
			"'Var'", "'Fetch'", "'|'", "':'", "'START'", "'L\\u00F3gica'", "'->'", 
			"'INCR'", "'BIF'", "'RTN'", "'DISABLE'", "'@Micro'", "'~'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, "MEMORYVALUE", "DECNUMBER", "FLAG", "IDENTIFIER", 
			"MICRO_INSTR", "LINE_COMMENT", "COMMENT", "NEWLINE", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Sicome.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SicomeParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	 
		public ProgramContext() { }
		public void copyFrom(ProgramContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CableProgramContext extends ProgramContext {
		public InstructionBlockCableContext instructionBlockCable() {
			return getRuleContext(InstructionBlockCableContext.class,0);
		}
		public VariablesBlockContext variablesBlock() {
			return getRuleContext(VariablesBlockContext.class,0);
		}
		public ProgramBlockContext programBlock() {
			return getRuleContext(ProgramBlockContext.class,0);
		}
		public CableProgramContext(ProgramContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).enterCableProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).exitCableProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SicomeVisitor ) return ((SicomeVisitor<? extends T>)visitor).visitCableProgram(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MicroProgramContext extends ProgramContext {
		public StatusLogicBlockContext statusLogicBlock() {
			return getRuleContext(StatusLogicBlockContext.class,0);
		}
		public InstructionBlockMicroContext instructionBlockMicro() {
			return getRuleContext(InstructionBlockMicroContext.class,0);
		}
		public VariablesBlockContext variablesBlock() {
			return getRuleContext(VariablesBlockContext.class,0);
		}
		public ProgramBlockContext programBlock() {
			return getRuleContext(ProgramBlockContext.class,0);
		}
		public MicroProgramContext(ProgramContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).enterMicroProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).exitMicroProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SicomeVisitor ) return ((SicomeVisitor<? extends T>)visitor).visitMicroProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			setState(63);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__10:
				_localctx = new CableProgramContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(42);
				instructionBlockCable();
				setState(49);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__0:
					{
					setState(43);
					variablesBlock();
					setState(44);
					programBlock();
					}
					break;
				case T__8:
					{
					setState(46);
					programBlock();
					setState(47);
					variablesBlock();
					}
					break;
				case EOF:
					break;
				default:
					break;
				}
				}
				break;
			case T__19:
				_localctx = new MicroProgramContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(51);
				statusLogicBlock();
				setState(53);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__25) {
					{
					setState(52);
					instructionBlockMicro();
					}
				}

				setState(61);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__0:
					{
					setState(55);
					variablesBlock();
					setState(56);
					programBlock();
					}
					break;
				case T__8:
					{
					setState(58);
					programBlock();
					setState(59);
					variablesBlock();
					}
					break;
				case EOF:
					break;
				default:
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariablesBlockContext extends ParserRuleContext {
		public List<VariableDeclarationContext> variableDeclaration() {
			return getRuleContexts(VariableDeclarationContext.class);
		}
		public VariableDeclarationContext variableDeclaration(int i) {
			return getRuleContext(VariableDeclarationContext.class,i);
		}
		public VariablesBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variablesBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).enterVariablesBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).exitVariablesBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SicomeVisitor ) return ((SicomeVisitor<? extends T>)visitor).visitVariablesBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariablesBlockContext variablesBlock() throws RecognitionException {
		VariablesBlockContext _localctx = new VariablesBlockContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_variablesBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			match(T__0);
			setState(66);
			match(T__1);
			setState(70);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IDENTIFIER) {
				{
				{
				setState(67);
				variableDeclaration();
				}
				}
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(73);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariableDeclarationContext extends ParserRuleContext {
		public VariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaration; }
	 
		public VariableDeclarationContext() { }
		public void copyFrom(VariableDeclarationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SimpleVariableDeclarationContext extends VariableDeclarationContext {
		public Token id;
		public Token value;
		public TerminalNode IDENTIFIER() { return getToken(SicomeParser.IDENTIFIER, 0); }
		public TerminalNode MEMORYVALUE() { return getToken(SicomeParser.MEMORYVALUE, 0); }
		public SimpleVariableDeclarationContext(VariableDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).enterSimpleVariableDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).exitSimpleVariableDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SicomeVisitor ) return ((SicomeVisitor<? extends T>)visitor).visitSimpleVariableDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VectorVariableDeclarationContext extends VariableDeclarationContext {
		public Token id;
		public Token size;
		public Token MEMORYVALUE;
		public List<Token> value = new ArrayList<Token>();
		public TerminalNode IDENTIFIER() { return getToken(SicomeParser.IDENTIFIER, 0); }
		public TerminalNode DECNUMBER() { return getToken(SicomeParser.DECNUMBER, 0); }
		public List<TerminalNode> MEMORYVALUE() { return getTokens(SicomeParser.MEMORYVALUE); }
		public TerminalNode MEMORYVALUE(int i) {
			return getToken(SicomeParser.MEMORYVALUE, i);
		}
		public VectorVariableDeclarationContext(VariableDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).enterVectorVariableDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).exitVectorVariableDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SicomeVisitor ) return ((SicomeVisitor<? extends T>)visitor).visitVectorVariableDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclarationContext variableDeclaration() throws RecognitionException {
		VariableDeclarationContext _localctx = new VariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_variableDeclaration);
		int _la;
		try {
			setState(95);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				_localctx = new SimpleVariableDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(75);
				((SimpleVariableDeclarationContext)_localctx).id = match(IDENTIFIER);
				setState(76);
				match(T__3);
				setState(77);
				((SimpleVariableDeclarationContext)_localctx).value = match(MEMORYVALUE);
				setState(78);
				match(T__4);
				}
				break;
			case 2:
				_localctx = new VectorVariableDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(79);
				((VectorVariableDeclarationContext)_localctx).id = match(IDENTIFIER);
				setState(80);
				match(T__5);
				setState(81);
				((VectorVariableDeclarationContext)_localctx).size = match(DECNUMBER);
				setState(82);
				match(T__6);
				setState(83);
				match(T__3);
				setState(84);
				match(T__1);
				setState(85);
				((VectorVariableDeclarationContext)_localctx).MEMORYVALUE = match(MEMORYVALUE);
				((VectorVariableDeclarationContext)_localctx).value.add(((VectorVariableDeclarationContext)_localctx).MEMORYVALUE);
				setState(90);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__7) {
					{
					{
					setState(86);
					match(T__7);
					setState(87);
					((VectorVariableDeclarationContext)_localctx).MEMORYVALUE = match(MEMORYVALUE);
					((VectorVariableDeclarationContext)_localctx).value.add(((VectorVariableDeclarationContext)_localctx).MEMORYVALUE);
					}
					}
					setState(92);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(93);
				match(T__2);
				setState(94);
				match(T__4);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramBlockContext extends ParserRuleContext {
		public List<ProgramLineContext> programLine() {
			return getRuleContexts(ProgramLineContext.class);
		}
		public ProgramLineContext programLine(int i) {
			return getRuleContext(ProgramLineContext.class,i);
		}
		public ProgramBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).enterProgramBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).exitProgramBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SicomeVisitor ) return ((SicomeVisitor<? extends T>)visitor).visitProgramBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramBlockContext programBlock() throws RecognitionException {
		ProgramBlockContext _localctx = new ProgramBlockContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_programBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			match(T__8);
			setState(98);
			match(T__1);
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9 || _la==IDENTIFIER) {
				{
				{
				setState(99);
				programLine();
				}
				}
				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(105);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramLineContext extends ParserRuleContext {
		public ProgramLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programLine; }
	 
		public ProgramLineContext() { }
		public void copyFrom(ProgramLineContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InstructionUseContext extends ProgramLineContext {
		public Token name;
		public InstructionUseArgumentContext arg;
		public TerminalNode IDENTIFIER() { return getToken(SicomeParser.IDENTIFIER, 0); }
		public InstructionUseArgumentContext instructionUseArgument() {
			return getRuleContext(InstructionUseArgumentContext.class,0);
		}
		public InstructionUseContext(ProgramLineContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).enterInstructionUse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).exitInstructionUse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SicomeVisitor ) return ((SicomeVisitor<? extends T>)visitor).visitInstructionUse(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MarkUseContext extends ProgramLineContext {
		public Token label;
		public TerminalNode IDENTIFIER() { return getToken(SicomeParser.IDENTIFIER, 0); }
		public MarkUseContext(ProgramLineContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).enterMarkUse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).exitMarkUse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SicomeVisitor ) return ((SicomeVisitor<? extends T>)visitor).visitMarkUse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramLineContext programLine() throws RecognitionException {
		ProgramLineContext _localctx = new ProgramLineContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_programLine);
		try {
			setState(114);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				_localctx = new InstructionUseContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(107);
				((InstructionUseContext)_localctx).name = match(IDENTIFIER);
				setState(108);
				((InstructionUseContext)_localctx).arg = instructionUseArgument();
				setState(109);
				match(T__4);
				}
				break;
			case T__9:
				_localctx = new MarkUseContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(111);
				match(T__9);
				setState(112);
				((MarkUseContext)_localctx).label = match(IDENTIFIER);
				setState(113);
				match(T__4);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InstructionUseArgumentContext extends ParserRuleContext {
		public Token var;
		public Token offset;
		public Token num;
		public TerminalNode IDENTIFIER() { return getToken(SicomeParser.IDENTIFIER, 0); }
		public TerminalNode DECNUMBER() { return getToken(SicomeParser.DECNUMBER, 0); }
		public TerminalNode MEMORYVALUE() { return getToken(SicomeParser.MEMORYVALUE, 0); }
		public InstructionUseArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instructionUseArgument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).enterInstructionUseArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).exitInstructionUseArgument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SicomeVisitor ) return ((SicomeVisitor<? extends T>)visitor).visitInstructionUseArgument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstructionUseArgumentContext instructionUseArgument() throws RecognitionException {
		InstructionUseArgumentContext _localctx = new InstructionUseArgumentContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_instructionUseArgument);
		int _la;
		try {
			setState(124);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(116);
				((InstructionUseArgumentContext)_localctx).var = match(IDENTIFIER);
				setState(120);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__5) {
					{
					setState(117);
					match(T__5);
					setState(118);
					((InstructionUseArgumentContext)_localctx).offset = match(DECNUMBER);
					setState(119);
					match(T__6);
					}
				}

				}
				break;
			case MEMORYVALUE:
				enterOuterAlt(_localctx, 2);
				{
				setState(122);
				((InstructionUseArgumentContext)_localctx).num = match(MEMORYVALUE);
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InstructionBlockCableContext extends ParserRuleContext {
		public FetchDefinitionCableContext fetchDefinitionCable() {
			return getRuleContext(FetchDefinitionCableContext.class,0);
		}
		public List<InstructionCableContext> instructionCable() {
			return getRuleContexts(InstructionCableContext.class);
		}
		public InstructionCableContext instructionCable(int i) {
			return getRuleContext(InstructionCableContext.class,i);
		}
		public InstructionBlockCableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instructionBlockCable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).enterInstructionBlockCable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).exitInstructionBlockCable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SicomeVisitor ) return ((SicomeVisitor<? extends T>)visitor).visitInstructionBlockCable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstructionBlockCableContext instructionBlockCable() throws RecognitionException {
		InstructionBlockCableContext _localctx = new InstructionBlockCableContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_instructionBlockCable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			match(T__10);
			setState(127);
			match(T__11);
			setState(128);
			match(T__1);
			setState(129);
			fetchDefinitionCable();
			setState(131); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(130);
				instructionCable();
				}
				}
				setState(133); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );
			setState(135);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InstructionCableContext extends ParserRuleContext {
		public Token arg;
		public Token nSteps;
		public TerminalNode IDENTIFIER() { return getToken(SicomeParser.IDENTIFIER, 0); }
		public List<StepCableContext> stepCable() {
			return getRuleContexts(StepCableContext.class);
		}
		public StepCableContext stepCable(int i) {
			return getRuleContext(StepCableContext.class,i);
		}
		public TerminalNode DECNUMBER() { return getToken(SicomeParser.DECNUMBER, 0); }
		public InstructionCableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instructionCable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).enterInstructionCable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).exitInstructionCable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SicomeVisitor ) return ((SicomeVisitor<? extends T>)visitor).visitInstructionCable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstructionCableContext instructionCable() throws RecognitionException {
		InstructionCableContext _localctx = new InstructionCableContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_instructionCable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			match(IDENTIFIER);
			setState(138);
			match(T__5);
			setState(140);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 57344L) != 0)) {
				{
				setState(139);
				((InstructionCableContext)_localctx).arg = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 57344L) != 0)) ) {
					((InstructionCableContext)_localctx).arg = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(142);
			match(T__6);
			setState(144);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DECNUMBER) {
				{
				setState(143);
				((InstructionCableContext)_localctx).nSteps = match(DECNUMBER);
				}
			}

			setState(146);
			match(T__1);
			setState(150);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1 || _la==T__16) {
				{
				{
				setState(147);
				stepCable();
				}
				}
				setState(152);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(153);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FetchDefinitionCableContext extends ParserRuleContext {
		public List<StepCableContext> stepCable() {
			return getRuleContexts(StepCableContext.class);
		}
		public StepCableContext stepCable(int i) {
			return getRuleContext(StepCableContext.class,i);
		}
		public FetchDefinitionCableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fetchDefinitionCable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).enterFetchDefinitionCable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).exitFetchDefinitionCable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SicomeVisitor ) return ((SicomeVisitor<? extends T>)visitor).visitFetchDefinitionCable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FetchDefinitionCableContext fetchDefinitionCable() throws RecognitionException {
		FetchDefinitionCableContext _localctx = new FetchDefinitionCableContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_fetchDefinitionCable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			match(T__15);
			setState(156);
			match(T__1);
			setState(158); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(157);
				stepCable();
				}
				}
				setState(160); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__1 || _la==T__16 );
			setState(162);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StepCableContext extends ParserRuleContext {
		public StepCableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stepCable; }
	 
		public StepCableContext() { }
		public void copyFrom(StepCableContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ConditionalCableStepBlockContext extends StepCableContext {
		public List<ConditionalStepCableContext> conditionalStepCable() {
			return getRuleContexts(ConditionalStepCableContext.class);
		}
		public ConditionalStepCableContext conditionalStepCable(int i) {
			return getRuleContext(ConditionalStepCableContext.class,i);
		}
		public ConditionalCableStepBlockContext(StepCableContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).enterConditionalCableStepBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).exitConditionalCableStepBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SicomeVisitor ) return ((SicomeVisitor<? extends T>)visitor).visitConditionalCableStepBlock(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SimpleCableStepContext extends StepCableContext {
		public PermissibleLOADSRMicroIntrContext linstr;
		public MicroIntrContext microIntr;
		public List<MicroIntrContext> rinstr = new ArrayList<MicroIntrContext>();
		public PermissibleLOADSRMicroIntrContext permissibleLOADSRMicroIntr() {
			return getRuleContext(PermissibleLOADSRMicroIntrContext.class,0);
		}
		public List<MicroIntrContext> microIntr() {
			return getRuleContexts(MicroIntrContext.class);
		}
		public MicroIntrContext microIntr(int i) {
			return getRuleContext(MicroIntrContext.class,i);
		}
		public SimpleCableStepContext(StepCableContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).enterSimpleCableStep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).exitSimpleCableStep(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SicomeVisitor ) return ((SicomeVisitor<? extends T>)visitor).visitSimpleCableStep(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StepCableContext stepCable() throws RecognitionException {
		StepCableContext _localctx = new StepCableContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_stepCable);
		int _la;
		try {
			setState(183);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__16:
				_localctx = new SimpleCableStepContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(164);
				match(T__16);
				setState(165);
				((SimpleCableStepContext)_localctx).linstr = permissibleLOADSRMicroIntr();
				setState(166);
				match(T__16);
				setState(170);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==MICRO_INSTR) {
					{
					{
					setState(167);
					((SimpleCableStepContext)_localctx).microIntr = microIntr();
					((SimpleCableStepContext)_localctx).rinstr.add(((SimpleCableStepContext)_localctx).microIntr);
					}
					}
					setState(172);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(173);
				match(T__4);
				}
				break;
			case T__1:
				_localctx = new ConditionalCableStepBlockContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(175);
				match(T__1);
				setState(177); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(176);
					conditionalStepCable();
					}
					}
					setState(179); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==FLAG );
				setState(181);
				match(T__2);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConditionalStepCableContext extends ParserRuleContext {
		public Token FLAG;
		public List<Token> flags = new ArrayList<Token>();
		public PermissibleLOADSRMicroIntrContext linstr;
		public MicroIntrContext microIntr;
		public List<MicroIntrContext> rinstr = new ArrayList<MicroIntrContext>();
		public PermissibleLOADSRMicroIntrContext permissibleLOADSRMicroIntr() {
			return getRuleContext(PermissibleLOADSRMicroIntrContext.class,0);
		}
		public List<TerminalNode> FLAG() { return getTokens(SicomeParser.FLAG); }
		public TerminalNode FLAG(int i) {
			return getToken(SicomeParser.FLAG, i);
		}
		public List<MicroIntrContext> microIntr() {
			return getRuleContexts(MicroIntrContext.class);
		}
		public MicroIntrContext microIntr(int i) {
			return getRuleContext(MicroIntrContext.class,i);
		}
		public ConditionalStepCableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionalStepCable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).enterConditionalStepCable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).exitConditionalStepCable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SicomeVisitor ) return ((SicomeVisitor<? extends T>)visitor).visitConditionalStepCable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionalStepCableContext conditionalStepCable() throws RecognitionException {
		ConditionalStepCableContext _localctx = new ConditionalStepCableContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_conditionalStepCable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(185);
				((ConditionalStepCableContext)_localctx).FLAG = match(FLAG);
				((ConditionalStepCableContext)_localctx).flags.add(((ConditionalStepCableContext)_localctx).FLAG);
				}
				}
				setState(188); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==FLAG );
			setState(190);
			match(T__17);
			setState(191);
			match(T__16);
			setState(192);
			((ConditionalStepCableContext)_localctx).linstr = permissibleLOADSRMicroIntr();
			setState(193);
			match(T__16);
			setState(197);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==MICRO_INSTR) {
				{
				{
				setState(194);
				((ConditionalStepCableContext)_localctx).microIntr = microIntr();
				((ConditionalStepCableContext)_localctx).rinstr.add(((ConditionalStepCableContext)_localctx).microIntr);
				}
				}
				setState(199);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(200);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MicroIntrContext extends ParserRuleContext {
		public Token arg;
		public TerminalNode MICRO_INSTR() { return getToken(SicomeParser.MICRO_INSTR, 0); }
		public TerminalNode DECNUMBER() { return getToken(SicomeParser.DECNUMBER, 0); }
		public MicroIntrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_microIntr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).enterMicroIntr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).exitMicroIntr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SicomeVisitor ) return ((SicomeVisitor<? extends T>)visitor).visitMicroIntr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MicroIntrContext microIntr() throws RecognitionException {
		MicroIntrContext _localctx = new MicroIntrContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_microIntr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			match(MICRO_INSTR);
			setState(206);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(203);
				match(T__5);
				setState(204);
				((MicroIntrContext)_localctx).arg = match(DECNUMBER);
				setState(205);
				match(T__6);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PermissibleLOADSRMicroIntrContext extends ParserRuleContext {
		public Token arg;
		public TerminalNode MICRO_INSTR() { return getToken(SicomeParser.MICRO_INSTR, 0); }
		public TerminalNode DECNUMBER() { return getToken(SicomeParser.DECNUMBER, 0); }
		public PermissibleLOADSRMicroIntrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_permissibleLOADSRMicroIntr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).enterPermissibleLOADSRMicroIntr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).exitPermissibleLOADSRMicroIntr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SicomeVisitor ) return ((SicomeVisitor<? extends T>)visitor).visitPermissibleLOADSRMicroIntr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PermissibleLOADSRMicroIntrContext permissibleLOADSRMicroIntr() throws RecognitionException {
		PermissibleLOADSRMicroIntrContext _localctx = new PermissibleLOADSRMicroIntrContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_permissibleLOADSRMicroIntr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			match(MICRO_INSTR);
			setState(212);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(209);
				match(T__5);
				setState(210);
				((PermissibleLOADSRMicroIntrContext)_localctx).arg = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__18 || _la==DECNUMBER) ) {
					((PermissibleLOADSRMicroIntrContext)_localctx).arg = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(211);
				match(T__6);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatusLogicBlockContext extends ParserRuleContext {
		public List<StatusLogicContext> statusLogic() {
			return getRuleContexts(StatusLogicContext.class);
		}
		public StatusLogicContext statusLogic(int i) {
			return getRuleContext(StatusLogicContext.class,i);
		}
		public StatusLogicBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statusLogicBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).enterStatusLogicBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).exitStatusLogicBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SicomeVisitor ) return ((SicomeVisitor<? extends T>)visitor).visitStatusLogicBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatusLogicBlockContext statusLogicBlock() throws RecognitionException {
		StatusLogicBlockContext _localctx = new StatusLogicBlockContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_statusLogicBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
			match(T__19);
			setState(215);
			match(T__1);
			setState(217); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(216);
				statusLogic();
				}
				}
				setState(219); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );
			setState(221);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatusLogicContext extends ParserRuleContext {
		public StatusLogicContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statusLogic; }
	 
		public StatusLogicContext() { }
		public void copyFrom(StatusLogicContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SimpleStatusLogicContext extends StatusLogicContext {
		public Token name;
		public Token option;
		public Token disable;
		public TerminalNode IDENTIFIER() { return getToken(SicomeParser.IDENTIFIER, 0); }
		public SimpleStatusLogicContext(StatusLogicContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).enterSimpleStatusLogic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).exitSimpleStatusLogic(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SicomeVisitor ) return ((SicomeVisitor<? extends T>)visitor).visitSimpleStatusLogic(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ComplexStatusLogicContext extends StatusLogicContext {
		public Token name;
		public TerminalNode IDENTIFIER() { return getToken(SicomeParser.IDENTIFIER, 0); }
		public List<StatusLogicOptionContext> statusLogicOption() {
			return getRuleContexts(StatusLogicOptionContext.class);
		}
		public StatusLogicOptionContext statusLogicOption(int i) {
			return getRuleContext(StatusLogicOptionContext.class,i);
		}
		public ComplexStatusLogicContext(StatusLogicContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).enterComplexStatusLogic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).exitComplexStatusLogic(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SicomeVisitor ) return ((SicomeVisitor<? extends T>)visitor).visitComplexStatusLogic(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatusLogicContext statusLogic() throws RecognitionException {
		StatusLogicContext _localctx = new StatusLogicContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_statusLogic);
		int _la;
		try {
			setState(241);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				_localctx = new SimpleStatusLogicContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(223);
				((SimpleStatusLogicContext)_localctx).name = match(IDENTIFIER);
				setState(224);
				match(T__20);
				setState(226);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 29360128L) != 0)) {
					{
					setState(225);
					((SimpleStatusLogicContext)_localctx).option = _input.LT(1);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 29360128L) != 0)) ) {
						((SimpleStatusLogicContext)_localctx).option = (Token)_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				setState(229);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__24) {
					{
					setState(228);
					((SimpleStatusLogicContext)_localctx).disable = match(T__24);
					}
				}

				}
				break;
			case 2:
				_localctx = new ComplexStatusLogicContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(231);
				((ComplexStatusLogicContext)_localctx).name = match(IDENTIFIER);
				setState(232);
				match(T__20);
				setState(233);
				match(T__1);
				setState(235); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(234);
					statusLogicOption();
					}
					}
					setState(237); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==FLAG );
				setState(239);
				match(T__2);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatusLogicOptionContext extends ParserRuleContext {
		public Token FLAG;
		public List<Token> flags = new ArrayList<Token>();
		public Token option;
		public Token disable;
		public List<TerminalNode> FLAG() { return getTokens(SicomeParser.FLAG); }
		public TerminalNode FLAG(int i) {
			return getToken(SicomeParser.FLAG, i);
		}
		public StatusLogicOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statusLogicOption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).enterStatusLogicOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).exitStatusLogicOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SicomeVisitor ) return ((SicomeVisitor<? extends T>)visitor).visitStatusLogicOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatusLogicOptionContext statusLogicOption() throws RecognitionException {
		StatusLogicOptionContext _localctx = new StatusLogicOptionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_statusLogicOption);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(243);
				((StatusLogicOptionContext)_localctx).FLAG = match(FLAG);
				((StatusLogicOptionContext)_localctx).flags.add(((StatusLogicOptionContext)_localctx).FLAG);
				}
				}
				setState(246); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==FLAG );
			setState(248);
			match(T__17);
			setState(250);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 29360128L) != 0)) {
				{
				setState(249);
				((StatusLogicOptionContext)_localctx).option = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 29360128L) != 0)) ) {
					((StatusLogicOptionContext)_localctx).option = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(253);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__24) {
				{
				setState(252);
				((StatusLogicOptionContext)_localctx).disable = match(T__24);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InstructionBlockMicroContext extends ParserRuleContext {
		public FetchDefinitionMicroContext fetchDefinitionMicro() {
			return getRuleContext(FetchDefinitionMicroContext.class,0);
		}
		public List<InstructionMicroContext> instructionMicro() {
			return getRuleContexts(InstructionMicroContext.class);
		}
		public InstructionMicroContext instructionMicro(int i) {
			return getRuleContext(InstructionMicroContext.class,i);
		}
		public InstructionBlockMicroContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instructionBlockMicro; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).enterInstructionBlockMicro(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).exitInstructionBlockMicro(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SicomeVisitor ) return ((SicomeVisitor<? extends T>)visitor).visitInstructionBlockMicro(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstructionBlockMicroContext instructionBlockMicro() throws RecognitionException {
		InstructionBlockMicroContext _localctx = new InstructionBlockMicroContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_instructionBlockMicro);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255);
			match(T__25);
			setState(256);
			match(T__11);
			setState(257);
			match(T__1);
			setState(258);
			fetchDefinitionMicro();
			setState(260); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(259);
				instructionMicro();
				}
				}
				setState(262); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );
			setState(264);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FetchDefinitionMicroContext extends ParserRuleContext {
		public List<StepMicroContext> stepMicro() {
			return getRuleContexts(StepMicroContext.class);
		}
		public StepMicroContext stepMicro(int i) {
			return getRuleContext(StepMicroContext.class,i);
		}
		public FetchDefinitionMicroContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fetchDefinitionMicro; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).enterFetchDefinitionMicro(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).exitFetchDefinitionMicro(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SicomeVisitor ) return ((SicomeVisitor<? extends T>)visitor).visitFetchDefinitionMicro(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FetchDefinitionMicroContext fetchDefinitionMicro() throws RecognitionException {
		FetchDefinitionMicroContext _localctx = new FetchDefinitionMicroContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_fetchDefinitionMicro);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(266);
			match(T__15);
			setState(267);
			match(T__1);
			setState(269); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(268);
				stepMicro();
				}
				}
				setState(271); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__16 );
			setState(273);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InstructionMicroContext extends ParserRuleContext {
		public Token arg;
		public Token nSteps;
		public TerminalNode IDENTIFIER() { return getToken(SicomeParser.IDENTIFIER, 0); }
		public List<StepMicroContext> stepMicro() {
			return getRuleContexts(StepMicroContext.class);
		}
		public StepMicroContext stepMicro(int i) {
			return getRuleContext(StepMicroContext.class,i);
		}
		public TerminalNode DECNUMBER() { return getToken(SicomeParser.DECNUMBER, 0); }
		public InstructionMicroContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instructionMicro; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).enterInstructionMicro(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).exitInstructionMicro(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SicomeVisitor ) return ((SicomeVisitor<? extends T>)visitor).visitInstructionMicro(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstructionMicroContext instructionMicro() throws RecognitionException {
		InstructionMicroContext _localctx = new InstructionMicroContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_instructionMicro);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(275);
			match(IDENTIFIER);
			setState(276);
			match(T__5);
			setState(278);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 57344L) != 0)) {
				{
				setState(277);
				((InstructionMicroContext)_localctx).arg = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 57344L) != 0)) ) {
					((InstructionMicroContext)_localctx).arg = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(280);
			match(T__6);
			setState(282);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DECNUMBER) {
				{
				setState(281);
				((InstructionMicroContext)_localctx).nSteps = match(DECNUMBER);
				}
			}

			setState(284);
			match(T__1);
			setState(288);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__16) {
				{
				{
				setState(285);
				stepMicro();
				}
				}
				setState(290);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(291);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StepMicroContext extends ParserRuleContext {
		public Token biflogic;
		public MicroIntrContext microIntr;
		public List<MicroIntrContext> instr = new ArrayList<MicroIntrContext>();
		public TerminalNode IDENTIFIER() { return getToken(SicomeParser.IDENTIFIER, 0); }
		public BifLogicArgumentContext bifLogicArgument() {
			return getRuleContext(BifLogicArgumentContext.class,0);
		}
		public List<MicroIntrContext> microIntr() {
			return getRuleContexts(MicroIntrContext.class);
		}
		public MicroIntrContext microIntr(int i) {
			return getRuleContext(MicroIntrContext.class,i);
		}
		public StepMicroContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stepMicro; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).enterStepMicro(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).exitStepMicro(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SicomeVisitor ) return ((SicomeVisitor<? extends T>)visitor).visitStepMicro(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StepMicroContext stepMicro() throws RecognitionException {
		StepMicroContext _localctx = new StepMicroContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_stepMicro);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			match(T__16);
			setState(294);
			((StepMicroContext)_localctx).biflogic = match(IDENTIFIER);
			setState(299);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(295);
				match(T__5);
				setState(296);
				bifLogicArgument();
				setState(297);
				match(T__6);
				}
			}

			setState(301);
			match(T__16);
			setState(305);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==MICRO_INSTR) {
				{
				{
				setState(302);
				((StepMicroContext)_localctx).microIntr = microIntr();
				((StepMicroContext)_localctx).instr.add(((StepMicroContext)_localctx).microIntr);
				}
				}
				setState(307);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(308);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BifLogicArgumentContext extends ParserRuleContext {
		public BifLogicArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bifLogicArgument; }
	 
		public BifLogicArgumentContext() { }
		public void copyFrom(BifLogicArgumentContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InstructionBifLogicArgumentContext extends BifLogicArgumentContext {
		public Token instr;
		public Token arg;
		public TerminalNode IDENTIFIER() { return getToken(SicomeParser.IDENTIFIER, 0); }
		public TerminalNode DECNUMBER() { return getToken(SicomeParser.DECNUMBER, 0); }
		public InstructionBifLogicArgumentContext(BifLogicArgumentContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).enterInstructionBifLogicArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).exitInstructionBifLogicArgument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SicomeVisitor ) return ((SicomeVisitor<? extends T>)visitor).visitInstructionBifLogicArgument(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StartBifLogicArgumentContext extends BifLogicArgumentContext {
		public TerminalNode DECNUMBER() { return getToken(SicomeParser.DECNUMBER, 0); }
		public StartBifLogicArgumentContext(BifLogicArgumentContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).enterStartBifLogicArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SicomeListener ) ((SicomeListener)listener).exitStartBifLogicArgument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SicomeVisitor ) return ((SicomeVisitor<? extends T>)visitor).visitStartBifLogicArgument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BifLogicArgumentContext bifLogicArgument() throws RecognitionException {
		BifLogicArgumentContext _localctx = new BifLogicArgumentContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_bifLogicArgument);
		try {
			setState(314);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				_localctx = new InstructionBifLogicArgumentContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(310);
				((InstructionBifLogicArgumentContext)_localctx).instr = match(IDENTIFIER);
				setState(311);
				match(T__26);
				setState(312);
				((InstructionBifLogicArgumentContext)_localctx).arg = match(DECNUMBER);
				}
				break;
			case DECNUMBER:
				_localctx = new StartBifLogicArgumentContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(313);
				match(DECNUMBER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001$\u013d\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0003\u0000"+
		"2\b\u0000\u0001\u0000\u0001\u0000\u0003\u00006\b\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0003\u0000>\b"+
		"\u0000\u0003\u0000@\b\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0005"+
		"\u0001E\b\u0001\n\u0001\f\u0001H\t\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0005\u0002Y\b\u0002\n\u0002\f\u0002\\\t\u0002\u0001\u0002\u0001"+
		"\u0002\u0003\u0002`\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0005"+
		"\u0003e\b\u0003\n\u0003\f\u0003h\t\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0003\u0004s\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0003\u0005y\b\u0005\u0001\u0005\u0001\u0005\u0003\u0005}\b\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0004\u0006"+
		"\u0084\b\u0006\u000b\u0006\f\u0006\u0085\u0001\u0006\u0001\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u008d\b\u0007\u0001\u0007\u0001"+
		"\u0007\u0003\u0007\u0091\b\u0007\u0001\u0007\u0001\u0007\u0005\u0007\u0095"+
		"\b\u0007\n\u0007\f\u0007\u0098\t\u0007\u0001\u0007\u0001\u0007\u0001\b"+
		"\u0001\b\u0001\b\u0004\b\u009f\b\b\u000b\b\f\b\u00a0\u0001\b\u0001\b\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0005\t\u00a9\b\t\n\t\f\t\u00ac\t\t\u0001\t"+
		"\u0001\t\u0001\t\u0001\t\u0004\t\u00b2\b\t\u000b\t\f\t\u00b3\u0001\t\u0001"+
		"\t\u0003\t\u00b8\b\t\u0001\n\u0004\n\u00bb\b\n\u000b\n\f\n\u00bc\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0005\n\u00c4\b\n\n\n\f\n\u00c7\t\n"+
		"\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003"+
		"\u000b\u00cf\b\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u00d5\b\f"+
		"\u0001\r\u0001\r\u0001\r\u0004\r\u00da\b\r\u000b\r\f\r\u00db\u0001\r\u0001"+
		"\r\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u00e3\b\u000e\u0001"+
		"\u000e\u0003\u000e\u00e6\b\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0004\u000e\u00ec\b\u000e\u000b\u000e\f\u000e\u00ed\u0001\u000e"+
		"\u0001\u000e\u0003\u000e\u00f2\b\u000e\u0001\u000f\u0004\u000f\u00f5\b"+
		"\u000f\u000b\u000f\f\u000f\u00f6\u0001\u000f\u0001\u000f\u0003\u000f\u00fb"+
		"\b\u000f\u0001\u000f\u0003\u000f\u00fe\b\u000f\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0004\u0010\u0105\b\u0010\u000b\u0010"+
		"\f\u0010\u0106\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0004\u0011\u010e\b\u0011\u000b\u0011\f\u0011\u010f\u0001\u0011\u0001"+
		"\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u0117\b\u0012\u0001"+
		"\u0012\u0001\u0012\u0003\u0012\u011b\b\u0012\u0001\u0012\u0001\u0012\u0005"+
		"\u0012\u011f\b\u0012\n\u0012\f\u0012\u0122\t\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0003\u0013\u012c\b\u0013\u0001\u0013\u0001\u0013\u0005\u0013\u0130\b"+
		"\u0013\n\u0013\f\u0013\u0133\t\u0013\u0001\u0013\u0001\u0013\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u013b\b\u0014\u0001\u0014"+
		"\u0000\u0000\u0015\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014"+
		"\u0016\u0018\u001a\u001c\u001e \"$&(\u0000\u0003\u0001\u0000\r\u000f\u0002"+
		"\u0000\u0013\u0013\u001d\u001d\u0001\u0000\u0016\u0018\u0151\u0000?\u0001"+
		"\u0000\u0000\u0000\u0002A\u0001\u0000\u0000\u0000\u0004_\u0001\u0000\u0000"+
		"\u0000\u0006a\u0001\u0000\u0000\u0000\br\u0001\u0000\u0000\u0000\n|\u0001"+
		"\u0000\u0000\u0000\f~\u0001\u0000\u0000\u0000\u000e\u0089\u0001\u0000"+
		"\u0000\u0000\u0010\u009b\u0001\u0000\u0000\u0000\u0012\u00b7\u0001\u0000"+
		"\u0000\u0000\u0014\u00ba\u0001\u0000\u0000\u0000\u0016\u00ca\u0001\u0000"+
		"\u0000\u0000\u0018\u00d0\u0001\u0000\u0000\u0000\u001a\u00d6\u0001\u0000"+
		"\u0000\u0000\u001c\u00f1\u0001\u0000\u0000\u0000\u001e\u00f4\u0001\u0000"+
		"\u0000\u0000 \u00ff\u0001\u0000\u0000\u0000\"\u010a\u0001\u0000\u0000"+
		"\u0000$\u0113\u0001\u0000\u0000\u0000&\u0125\u0001\u0000\u0000\u0000("+
		"\u013a\u0001\u0000\u0000\u0000*1\u0003\f\u0006\u0000+,\u0003\u0002\u0001"+
		"\u0000,-\u0003\u0006\u0003\u0000-2\u0001\u0000\u0000\u0000./\u0003\u0006"+
		"\u0003\u0000/0\u0003\u0002\u0001\u000002\u0001\u0000\u0000\u00001+\u0001"+
		"\u0000\u0000\u00001.\u0001\u0000\u0000\u000012\u0001\u0000\u0000\u0000"+
		"2@\u0001\u0000\u0000\u000035\u0003\u001a\r\u000046\u0003 \u0010\u0000"+
		"54\u0001\u0000\u0000\u000056\u0001\u0000\u0000\u00006=\u0001\u0000\u0000"+
		"\u000078\u0003\u0002\u0001\u000089\u0003\u0006\u0003\u00009>\u0001\u0000"+
		"\u0000\u0000:;\u0003\u0006\u0003\u0000;<\u0003\u0002\u0001\u0000<>\u0001"+
		"\u0000\u0000\u0000=7\u0001\u0000\u0000\u0000=:\u0001\u0000\u0000\u0000"+
		"=>\u0001\u0000\u0000\u0000>@\u0001\u0000\u0000\u0000?*\u0001\u0000\u0000"+
		"\u0000?3\u0001\u0000\u0000\u0000@\u0001\u0001\u0000\u0000\u0000AB\u0005"+
		"\u0001\u0000\u0000BF\u0005\u0002\u0000\u0000CE\u0003\u0004\u0002\u0000"+
		"DC\u0001\u0000\u0000\u0000EH\u0001\u0000\u0000\u0000FD\u0001\u0000\u0000"+
		"\u0000FG\u0001\u0000\u0000\u0000GI\u0001\u0000\u0000\u0000HF\u0001\u0000"+
		"\u0000\u0000IJ\u0005\u0003\u0000\u0000J\u0003\u0001\u0000\u0000\u0000"+
		"KL\u0005\u001f\u0000\u0000LM\u0005\u0004\u0000\u0000MN\u0005\u001c\u0000"+
		"\u0000N`\u0005\u0005\u0000\u0000OP\u0005\u001f\u0000\u0000PQ\u0005\u0006"+
		"\u0000\u0000QR\u0005\u001d\u0000\u0000RS\u0005\u0007\u0000\u0000ST\u0005"+
		"\u0004\u0000\u0000TU\u0005\u0002\u0000\u0000UZ\u0005\u001c\u0000\u0000"+
		"VW\u0005\b\u0000\u0000WY\u0005\u001c\u0000\u0000XV\u0001\u0000\u0000\u0000"+
		"Y\\\u0001\u0000\u0000\u0000ZX\u0001\u0000\u0000\u0000Z[\u0001\u0000\u0000"+
		"\u0000[]\u0001\u0000\u0000\u0000\\Z\u0001\u0000\u0000\u0000]^\u0005\u0003"+
		"\u0000\u0000^`\u0005\u0005\u0000\u0000_K\u0001\u0000\u0000\u0000_O\u0001"+
		"\u0000\u0000\u0000`\u0005\u0001\u0000\u0000\u0000ab\u0005\t\u0000\u0000"+
		"bf\u0005\u0002\u0000\u0000ce\u0003\b\u0004\u0000dc\u0001\u0000\u0000\u0000"+
		"eh\u0001\u0000\u0000\u0000fd\u0001\u0000\u0000\u0000fg\u0001\u0000\u0000"+
		"\u0000gi\u0001\u0000\u0000\u0000hf\u0001\u0000\u0000\u0000ij\u0005\u0003"+
		"\u0000\u0000j\u0007\u0001\u0000\u0000\u0000kl\u0005\u001f\u0000\u0000"+
		"lm\u0003\n\u0005\u0000mn\u0005\u0005\u0000\u0000ns\u0001\u0000\u0000\u0000"+
		"op\u0005\n\u0000\u0000pq\u0005\u001f\u0000\u0000qs\u0005\u0005\u0000\u0000"+
		"rk\u0001\u0000\u0000\u0000ro\u0001\u0000\u0000\u0000s\t\u0001\u0000\u0000"+
		"\u0000tx\u0005\u001f\u0000\u0000uv\u0005\u0006\u0000\u0000vw\u0005\u001d"+
		"\u0000\u0000wy\u0005\u0007\u0000\u0000xu\u0001\u0000\u0000\u0000xy\u0001"+
		"\u0000\u0000\u0000y}\u0001\u0000\u0000\u0000z}\u0005\u001c\u0000\u0000"+
		"{}\u0001\u0000\u0000\u0000|t\u0001\u0000\u0000\u0000|z\u0001\u0000\u0000"+
		"\u0000|{\u0001\u0000\u0000\u0000}\u000b\u0001\u0000\u0000\u0000~\u007f"+
		"\u0005\u000b\u0000\u0000\u007f\u0080\u0005\f\u0000\u0000\u0080\u0081\u0005"+
		"\u0002\u0000\u0000\u0081\u0083\u0003\u0010\b\u0000\u0082\u0084\u0003\u000e"+
		"\u0007\u0000\u0083\u0082\u0001\u0000\u0000\u0000\u0084\u0085\u0001\u0000"+
		"\u0000\u0000\u0085\u0083\u0001\u0000\u0000\u0000\u0085\u0086\u0001\u0000"+
		"\u0000\u0000\u0086\u0087\u0001\u0000\u0000\u0000\u0087\u0088\u0005\u0003"+
		"\u0000\u0000\u0088\r\u0001\u0000\u0000\u0000\u0089\u008a\u0005\u001f\u0000"+
		"\u0000\u008a\u008c\u0005\u0006\u0000\u0000\u008b\u008d\u0007\u0000\u0000"+
		"\u0000\u008c\u008b\u0001\u0000\u0000\u0000\u008c\u008d\u0001\u0000\u0000"+
		"\u0000\u008d\u008e\u0001\u0000\u0000\u0000\u008e\u0090\u0005\u0007\u0000"+
		"\u0000\u008f\u0091\u0005\u001d\u0000\u0000\u0090\u008f\u0001\u0000\u0000"+
		"\u0000\u0090\u0091\u0001\u0000\u0000\u0000\u0091\u0092\u0001\u0000\u0000"+
		"\u0000\u0092\u0096\u0005\u0002\u0000\u0000\u0093\u0095\u0003\u0012\t\u0000"+
		"\u0094\u0093\u0001\u0000\u0000\u0000\u0095\u0098\u0001\u0000\u0000\u0000"+
		"\u0096\u0094\u0001\u0000\u0000\u0000\u0096\u0097\u0001\u0000\u0000\u0000"+
		"\u0097\u0099\u0001\u0000\u0000\u0000\u0098\u0096\u0001\u0000\u0000\u0000"+
		"\u0099\u009a\u0005\u0003\u0000\u0000\u009a\u000f\u0001\u0000\u0000\u0000"+
		"\u009b\u009c\u0005\u0010\u0000\u0000\u009c\u009e\u0005\u0002\u0000\u0000"+
		"\u009d\u009f\u0003\u0012\t\u0000\u009e\u009d\u0001\u0000\u0000\u0000\u009f"+
		"\u00a0\u0001\u0000\u0000\u0000\u00a0\u009e\u0001\u0000\u0000\u0000\u00a0"+
		"\u00a1\u0001\u0000\u0000\u0000\u00a1\u00a2\u0001\u0000\u0000\u0000\u00a2"+
		"\u00a3\u0005\u0003\u0000\u0000\u00a3\u0011\u0001\u0000\u0000\u0000\u00a4"+
		"\u00a5\u0005\u0011\u0000\u0000\u00a5\u00a6\u0003\u0018\f\u0000\u00a6\u00aa"+
		"\u0005\u0011\u0000\u0000\u00a7\u00a9\u0003\u0016\u000b\u0000\u00a8\u00a7"+
		"\u0001\u0000\u0000\u0000\u00a9\u00ac\u0001\u0000\u0000\u0000\u00aa\u00a8"+
		"\u0001\u0000\u0000\u0000\u00aa\u00ab\u0001\u0000\u0000\u0000\u00ab\u00ad"+
		"\u0001\u0000\u0000\u0000\u00ac\u00aa\u0001\u0000\u0000\u0000\u00ad\u00ae"+
		"\u0005\u0005\u0000\u0000\u00ae\u00b8\u0001\u0000\u0000\u0000\u00af\u00b1"+
		"\u0005\u0002\u0000\u0000\u00b0\u00b2\u0003\u0014\n\u0000\u00b1\u00b0\u0001"+
		"\u0000\u0000\u0000\u00b2\u00b3\u0001\u0000\u0000\u0000\u00b3\u00b1\u0001"+
		"\u0000\u0000\u0000\u00b3\u00b4\u0001\u0000\u0000\u0000\u00b4\u00b5\u0001"+
		"\u0000\u0000\u0000\u00b5\u00b6\u0005\u0003\u0000\u0000\u00b6\u00b8\u0001"+
		"\u0000\u0000\u0000\u00b7\u00a4\u0001\u0000\u0000\u0000\u00b7\u00af\u0001"+
		"\u0000\u0000\u0000\u00b8\u0013\u0001\u0000\u0000\u0000\u00b9\u00bb\u0005"+
		"\u001e\u0000\u0000\u00ba\u00b9\u0001\u0000\u0000\u0000\u00bb\u00bc\u0001"+
		"\u0000\u0000\u0000\u00bc\u00ba\u0001\u0000\u0000\u0000\u00bc\u00bd\u0001"+
		"\u0000\u0000\u0000\u00bd\u00be\u0001\u0000\u0000\u0000\u00be\u00bf\u0005"+
		"\u0012\u0000\u0000\u00bf\u00c0\u0005\u0011\u0000\u0000\u00c0\u00c1\u0003"+
		"\u0018\f\u0000\u00c1\u00c5\u0005\u0011\u0000\u0000\u00c2\u00c4\u0003\u0016"+
		"\u000b\u0000\u00c3\u00c2\u0001\u0000\u0000\u0000\u00c4\u00c7\u0001\u0000"+
		"\u0000\u0000\u00c5\u00c3\u0001\u0000\u0000\u0000\u00c5\u00c6\u0001\u0000"+
		"\u0000\u0000\u00c6\u00c8\u0001\u0000\u0000\u0000\u00c7\u00c5\u0001\u0000"+
		"\u0000\u0000\u00c8\u00c9\u0005\u0005\u0000\u0000\u00c9\u0015\u0001\u0000"+
		"\u0000\u0000\u00ca\u00ce\u0005 \u0000\u0000\u00cb\u00cc\u0005\u0006\u0000"+
		"\u0000\u00cc\u00cd\u0005\u001d\u0000\u0000\u00cd\u00cf\u0005\u0007\u0000"+
		"\u0000\u00ce\u00cb\u0001\u0000\u0000\u0000\u00ce\u00cf\u0001\u0000\u0000"+
		"\u0000\u00cf\u0017\u0001\u0000\u0000\u0000\u00d0\u00d4\u0005 \u0000\u0000"+
		"\u00d1\u00d2\u0005\u0006\u0000\u0000\u00d2\u00d3\u0007\u0001\u0000\u0000"+
		"\u00d3\u00d5\u0005\u0007\u0000\u0000\u00d4\u00d1\u0001\u0000\u0000\u0000"+
		"\u00d4\u00d5\u0001\u0000\u0000\u0000\u00d5\u0019\u0001\u0000\u0000\u0000"+
		"\u00d6\u00d7\u0005\u0014\u0000\u0000\u00d7\u00d9\u0005\u0002\u0000\u0000"+
		"\u00d8\u00da\u0003\u001c\u000e\u0000\u00d9\u00d8\u0001\u0000\u0000\u0000"+
		"\u00da\u00db\u0001\u0000\u0000\u0000\u00db\u00d9\u0001\u0000\u0000\u0000"+
		"\u00db\u00dc\u0001\u0000\u0000\u0000\u00dc\u00dd\u0001\u0000\u0000\u0000"+
		"\u00dd\u00de\u0005\u0003\u0000\u0000\u00de\u001b\u0001\u0000\u0000\u0000"+
		"\u00df\u00e0\u0005\u001f\u0000\u0000\u00e0\u00e2\u0005\u0015\u0000\u0000"+
		"\u00e1\u00e3\u0007\u0002\u0000\u0000\u00e2\u00e1\u0001\u0000\u0000\u0000"+
		"\u00e2\u00e3\u0001\u0000\u0000\u0000\u00e3\u00e5\u0001\u0000\u0000\u0000"+
		"\u00e4\u00e6\u0005\u0019\u0000\u0000\u00e5\u00e4\u0001\u0000\u0000\u0000"+
		"\u00e5\u00e6\u0001\u0000\u0000\u0000\u00e6\u00f2\u0001\u0000\u0000\u0000"+
		"\u00e7\u00e8\u0005\u001f\u0000\u0000\u00e8\u00e9\u0005\u0015\u0000\u0000"+
		"\u00e9\u00eb\u0005\u0002\u0000\u0000\u00ea\u00ec\u0003\u001e\u000f\u0000"+
		"\u00eb\u00ea\u0001\u0000\u0000\u0000\u00ec\u00ed\u0001\u0000\u0000\u0000"+
		"\u00ed\u00eb\u0001\u0000\u0000\u0000\u00ed\u00ee\u0001\u0000\u0000\u0000"+
		"\u00ee\u00ef\u0001\u0000\u0000\u0000\u00ef\u00f0\u0005\u0003\u0000\u0000"+
		"\u00f0\u00f2\u0001\u0000\u0000\u0000\u00f1\u00df\u0001\u0000\u0000\u0000"+
		"\u00f1\u00e7\u0001\u0000\u0000\u0000\u00f2\u001d\u0001\u0000\u0000\u0000"+
		"\u00f3\u00f5\u0005\u001e\u0000\u0000\u00f4\u00f3\u0001\u0000\u0000\u0000"+
		"\u00f5\u00f6\u0001\u0000\u0000\u0000\u00f6\u00f4\u0001\u0000\u0000\u0000"+
		"\u00f6\u00f7\u0001\u0000\u0000\u0000\u00f7\u00f8\u0001\u0000\u0000\u0000"+
		"\u00f8\u00fa\u0005\u0012\u0000\u0000\u00f9\u00fb\u0007\u0002\u0000\u0000"+
		"\u00fa\u00f9\u0001\u0000\u0000\u0000\u00fa\u00fb\u0001\u0000\u0000\u0000"+
		"\u00fb\u00fd\u0001\u0000\u0000\u0000\u00fc\u00fe\u0005\u0019\u0000\u0000"+
		"\u00fd\u00fc\u0001\u0000\u0000\u0000\u00fd\u00fe\u0001\u0000\u0000\u0000"+
		"\u00fe\u001f\u0001\u0000\u0000\u0000\u00ff\u0100\u0005\u001a\u0000\u0000"+
		"\u0100\u0101\u0005\f\u0000\u0000\u0101\u0102\u0005\u0002\u0000\u0000\u0102"+
		"\u0104\u0003\"\u0011\u0000\u0103\u0105\u0003$\u0012\u0000\u0104\u0103"+
		"\u0001\u0000\u0000\u0000\u0105\u0106\u0001\u0000\u0000\u0000\u0106\u0104"+
		"\u0001\u0000\u0000\u0000\u0106\u0107\u0001\u0000\u0000\u0000\u0107\u0108"+
		"\u0001\u0000\u0000\u0000\u0108\u0109\u0005\u0003\u0000\u0000\u0109!\u0001"+
		"\u0000\u0000\u0000\u010a\u010b\u0005\u0010\u0000\u0000\u010b\u010d\u0005"+
		"\u0002\u0000\u0000\u010c\u010e\u0003&\u0013\u0000\u010d\u010c\u0001\u0000"+
		"\u0000\u0000\u010e\u010f\u0001\u0000\u0000\u0000\u010f\u010d\u0001\u0000"+
		"\u0000\u0000\u010f\u0110\u0001\u0000\u0000\u0000\u0110\u0111\u0001\u0000"+
		"\u0000\u0000\u0111\u0112\u0005\u0003\u0000\u0000\u0112#\u0001\u0000\u0000"+
		"\u0000\u0113\u0114\u0005\u001f\u0000\u0000\u0114\u0116\u0005\u0006\u0000"+
		"\u0000\u0115\u0117\u0007\u0000\u0000\u0000\u0116\u0115\u0001\u0000\u0000"+
		"\u0000\u0116\u0117\u0001\u0000\u0000\u0000\u0117\u0118\u0001\u0000\u0000"+
		"\u0000\u0118\u011a\u0005\u0007\u0000\u0000\u0119\u011b\u0005\u001d\u0000"+
		"\u0000\u011a\u0119\u0001\u0000\u0000\u0000\u011a\u011b\u0001\u0000\u0000"+
		"\u0000\u011b\u011c\u0001\u0000\u0000\u0000\u011c\u0120\u0005\u0002\u0000"+
		"\u0000\u011d\u011f\u0003&\u0013\u0000\u011e\u011d\u0001\u0000\u0000\u0000"+
		"\u011f\u0122\u0001\u0000\u0000\u0000\u0120\u011e\u0001\u0000\u0000\u0000"+
		"\u0120\u0121\u0001\u0000\u0000\u0000\u0121\u0123\u0001\u0000\u0000\u0000"+
		"\u0122\u0120\u0001\u0000\u0000\u0000\u0123\u0124\u0005\u0003\u0000\u0000"+
		"\u0124%\u0001\u0000\u0000\u0000\u0125\u0126\u0005\u0011\u0000\u0000\u0126"+
		"\u012b\u0005\u001f\u0000\u0000\u0127\u0128\u0005\u0006\u0000\u0000\u0128"+
		"\u0129\u0003(\u0014\u0000\u0129\u012a\u0005\u0007\u0000\u0000\u012a\u012c"+
		"\u0001\u0000\u0000\u0000\u012b\u0127\u0001\u0000\u0000\u0000\u012b\u012c"+
		"\u0001\u0000\u0000\u0000\u012c\u012d\u0001\u0000\u0000\u0000\u012d\u0131"+
		"\u0005\u0011\u0000\u0000\u012e\u0130\u0003\u0016\u000b\u0000\u012f\u012e"+
		"\u0001\u0000\u0000\u0000\u0130\u0133\u0001\u0000\u0000\u0000\u0131\u012f"+
		"\u0001\u0000\u0000\u0000\u0131\u0132\u0001\u0000\u0000\u0000\u0132\u0134"+
		"\u0001\u0000\u0000\u0000\u0133\u0131\u0001\u0000\u0000\u0000\u0134\u0135"+
		"\u0005\u0005\u0000\u0000\u0135\'\u0001\u0000\u0000\u0000\u0136\u0137\u0005"+
		"\u001f\u0000\u0000\u0137\u0138\u0005\u001b\u0000\u0000\u0138\u013b\u0005"+
		"\u001d\u0000\u0000\u0139\u013b\u0005\u001d\u0000\u0000\u013a\u0136\u0001"+
		"\u0000\u0000\u0000\u013a\u0139\u0001\u0000\u0000\u0000\u013b)\u0001\u0000"+
		"\u0000\u0000\'15=?FZ_frx|\u0085\u008c\u0090\u0096\u00a0\u00aa\u00b3\u00b7"+
		"\u00bc\u00c5\u00ce\u00d4\u00db\u00e2\u00e5\u00ed\u00f1\u00f6\u00fa\u00fd"+
		"\u0106\u010f\u0116\u011a\u0120\u012b\u0131\u013a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}