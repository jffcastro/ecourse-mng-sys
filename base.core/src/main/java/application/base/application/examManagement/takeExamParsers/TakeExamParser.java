// Generated from C:/Users/pedro/OneDrive/Ambiente de Trabalho/universidade/sem4pi-22-23-6/base.core/src/main/java/application/base/application/examManagement/takeExamParsers\TakeExam.g4 by ANTLR 4.12.0
package application.base.application.examManagement.takeExamParsers;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class TakeExamParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, INIT=2, INITQUESTION=3, SEPARATOR=4, NUM=5, TEXT=6, ARROW=7, SECTIONOPEN=8, 
		SECTIONCLOSE=9, TWO_DOTS=10, DOT=11, WS=12;
	public static final int
		RULE_stat = 0, RULE_structure = 1, RULE_section = 2, RULE_sectionStructure = 3, 
		RULE_question = 4, RULE_questionAnswer = 5;
	private static String[] makeRuleNames() {
		return new String[] {
			"stat", "structure", "section", "sectionStructure", "question", "questionAnswer"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "','", "'\"'", "'??'", "';'", null, null, "'->'", "'<<'", "'>>'", 
			"':'", "'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, "INIT", "INITQUESTION", "SEPARATOR", "NUM", "TEXT", "ARROW", 
			"SECTIONOPEN", "SECTIONCLOSE", "TWO_DOTS", "DOT", "WS"
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
	public String getGrammarFileName() { return "TakeExam.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TakeExamParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatContext extends ParserRuleContext {
		public List<TerminalNode> INIT() { return getTokens(TakeExamParser.INIT); }
		public TerminalNode INIT(int i) {
			return getToken(TakeExamParser.INIT, i);
		}
		public StructureContext structure() {
			return getRuleContext(StructureContext.class,0);
		}
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TakeExamListener ) ((TakeExamListener)listener).enterStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TakeExamListener ) ((TakeExamListener)listener).exitStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TakeExamVisitor ) return ((TakeExamVisitor<? extends T>)visitor).visitStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_stat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(12);
			match(INIT);
			setState(13);
			structure();
			setState(14);
			match(INIT);
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
	public static class StructureContext extends ParserRuleContext {
		public List<SectionContext> section() {
			return getRuleContexts(SectionContext.class);
		}
		public SectionContext section(int i) {
			return getRuleContext(SectionContext.class,i);
		}
		public StructureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_structure; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TakeExamListener ) ((TakeExamListener)listener).enterStructure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TakeExamListener ) ((TakeExamListener)listener).exitStructure(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TakeExamVisitor ) return ((TakeExamVisitor<? extends T>)visitor).visitStructure(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StructureContext structure() throws RecognitionException {
		StructureContext _localctx = new StructureContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_structure);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(17); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(16);
				section();
				}
				}
				setState(19); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==TEXT );
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
	public static class SectionContext extends ParserRuleContext {
		public TerminalNode TEXT() { return getToken(TakeExamParser.TEXT, 0); }
		public TerminalNode TWO_DOTS() { return getToken(TakeExamParser.TWO_DOTS, 0); }
		public SectionStructureContext sectionStructure() {
			return getRuleContext(SectionStructureContext.class,0);
		}
		public SectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_section; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TakeExamListener ) ((TakeExamListener)listener).enterSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TakeExamListener ) ((TakeExamListener)listener).exitSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TakeExamVisitor ) return ((TakeExamVisitor<? extends T>)visitor).visitSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SectionContext section() throws RecognitionException {
		SectionContext _localctx = new SectionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_section);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(21);
			match(TEXT);
			setState(22);
			match(TWO_DOTS);
			setState(23);
			sectionStructure();
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
	public static class SectionStructureContext extends ParserRuleContext {
		public List<QuestionContext> question() {
			return getRuleContexts(QuestionContext.class);
		}
		public QuestionContext question(int i) {
			return getRuleContext(QuestionContext.class,i);
		}
		public SectionStructureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sectionStructure; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TakeExamListener ) ((TakeExamListener)listener).enterSectionStructure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TakeExamListener ) ((TakeExamListener)listener).exitSectionStructure(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TakeExamVisitor ) return ((TakeExamVisitor<? extends T>)visitor).visitSectionStructure(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SectionStructureContext sectionStructure() throws RecognitionException {
		SectionStructureContext _localctx = new SectionStructureContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_sectionStructure);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(25);
				question();
				}
				}
				setState(28); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NUM );
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
	public static class QuestionContext extends ParserRuleContext {
		public TerminalNode NUM() { return getToken(TakeExamParser.NUM, 0); }
		public TerminalNode DOT() { return getToken(TakeExamParser.DOT, 0); }
		public QuestionAnswerContext questionAnswer() {
			return getRuleContext(QuestionAnswerContext.class,0);
		}
		public TerminalNode SEPARATOR() { return getToken(TakeExamParser.SEPARATOR, 0); }
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TakeExamListener ) ((TakeExamListener)listener).enterQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TakeExamListener ) ((TakeExamListener)listener).exitQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TakeExamVisitor ) return ((TakeExamVisitor<? extends T>)visitor).visitQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_question);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			match(NUM);
			setState(31);
			match(DOT);
			setState(32);
			questionAnswer(0);
			setState(33);
			match(SEPARATOR);
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
	public static class QuestionAnswerContext extends ParserRuleContext {
		public TerminalNode TEXT() { return getToken(TakeExamParser.TEXT, 0); }
		public TerminalNode ARROW() { return getToken(TakeExamParser.ARROW, 0); }
		public List<QuestionAnswerContext> questionAnswer() {
			return getRuleContexts(QuestionAnswerContext.class);
		}
		public QuestionAnswerContext questionAnswer(int i) {
			return getRuleContext(QuestionAnswerContext.class,i);
		}
		public TerminalNode NUM() { return getToken(TakeExamParser.NUM, 0); }
		public QuestionAnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionAnswer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TakeExamListener ) ((TakeExamListener)listener).enterQuestionAnswer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TakeExamListener ) ((TakeExamListener)listener).exitQuestionAnswer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TakeExamVisitor ) return ((TakeExamVisitor<? extends T>)visitor).visitQuestionAnswer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionAnswerContext questionAnswer() throws RecognitionException {
		return questionAnswer(0);
	}

	private QuestionAnswerContext questionAnswer(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		QuestionAnswerContext _localctx = new QuestionAnswerContext(_ctx, _parentState);
		QuestionAnswerContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_questionAnswer, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(36);
				match(TEXT);
				}
				break;
			case 2:
				{
				setState(37);
				match(TEXT);
				setState(38);
				match(ARROW);
				setState(39);
				questionAnswer(4);
				}
				break;
			case 3:
				{
				setState(40);
				match(TEXT);
				setState(41);
				match(T__0);
				setState(42);
				questionAnswer(2);
				}
				break;
			case 4:
				{
				setState(43);
				match(NUM);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(51);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new QuestionAnswerContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_questionAnswer);
					setState(46);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(47);
					match(T__0);
					setState(48);
					questionAnswer(4);
					}
					} 
				}
				setState(53);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 5:
			return questionAnswer_sempred((QuestionAnswerContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean questionAnswer_sempred(QuestionAnswerContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\f7\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0004\u0001\u0012\b\u0001\u000b\u0001\f\u0001\u0013\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0004\u0003\u001b\b\u0003"+
		"\u000b\u0003\f\u0003\u001c\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005-\b\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u00052\b\u0005\n\u0005\f\u0005"+
		"5\t\u0005\u0001\u0005\u0000\u0001\n\u0006\u0000\u0002\u0004\u0006\b\n"+
		"\u0000\u00006\u0000\f\u0001\u0000\u0000\u0000\u0002\u0011\u0001\u0000"+
		"\u0000\u0000\u0004\u0015\u0001\u0000\u0000\u0000\u0006\u001a\u0001\u0000"+
		"\u0000\u0000\b\u001e\u0001\u0000\u0000\u0000\n,\u0001\u0000\u0000\u0000"+
		"\f\r\u0005\u0002\u0000\u0000\r\u000e\u0003\u0002\u0001\u0000\u000e\u000f"+
		"\u0005\u0002\u0000\u0000\u000f\u0001\u0001\u0000\u0000\u0000\u0010\u0012"+
		"\u0003\u0004\u0002\u0000\u0011\u0010\u0001\u0000\u0000\u0000\u0012\u0013"+
		"\u0001\u0000\u0000\u0000\u0013\u0011\u0001\u0000\u0000\u0000\u0013\u0014"+
		"\u0001\u0000\u0000\u0000\u0014\u0003\u0001\u0000\u0000\u0000\u0015\u0016"+
		"\u0005\u0006\u0000\u0000\u0016\u0017\u0005\n\u0000\u0000\u0017\u0018\u0003"+
		"\u0006\u0003\u0000\u0018\u0005\u0001\u0000\u0000\u0000\u0019\u001b\u0003"+
		"\b\u0004\u0000\u001a\u0019\u0001\u0000\u0000\u0000\u001b\u001c\u0001\u0000"+
		"\u0000\u0000\u001c\u001a\u0001\u0000\u0000\u0000\u001c\u001d\u0001\u0000"+
		"\u0000\u0000\u001d\u0007\u0001\u0000\u0000\u0000\u001e\u001f\u0005\u0005"+
		"\u0000\u0000\u001f \u0005\u000b\u0000\u0000 !\u0003\n\u0005\u0000!\"\u0005"+
		"\u0004\u0000\u0000\"\t\u0001\u0000\u0000\u0000#$\u0006\u0005\uffff\uffff"+
		"\u0000$-\u0005\u0006\u0000\u0000%&\u0005\u0006\u0000\u0000&\'\u0005\u0007"+
		"\u0000\u0000\'-\u0003\n\u0005\u0004()\u0005\u0006\u0000\u0000)*\u0005"+
		"\u0001\u0000\u0000*-\u0003\n\u0005\u0002+-\u0005\u0005\u0000\u0000,#\u0001"+
		"\u0000\u0000\u0000,%\u0001\u0000\u0000\u0000,(\u0001\u0000\u0000\u0000"+
		",+\u0001\u0000\u0000\u0000-3\u0001\u0000\u0000\u0000./\n\u0003\u0000\u0000"+
		"/0\u0005\u0001\u0000\u000002\u0003\n\u0005\u00041.\u0001\u0000\u0000\u0000"+
		"25\u0001\u0000\u0000\u000031\u0001\u0000\u0000\u000034\u0001\u0000\u0000"+
		"\u00004\u000b\u0001\u0000\u0000\u000053\u0001\u0000\u0000\u0000\u0004"+
		"\u0013\u001c,3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}