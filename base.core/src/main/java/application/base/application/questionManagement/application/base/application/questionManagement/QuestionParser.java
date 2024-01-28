// Generated from /Users/mariainessilvapinto/sem4pi-22-23-6/base.core/src/main/java/application/base/application/questionManagement/Question.g4 by ANTLR 4.12.0
package application.base.application.questionManagement;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class QuestionParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, INITQUESTION=11, QUESTION=12, SEPARATOR=13, NUM=14, TEXT=15, 
		ARROW=16, GAPSPACE=17, WS=18;
	public static final int
		RULE_question = 0, RULE_quotation = 1, RULE_questionType = 2, RULE_matching = 3, 
		RULE_matchingText = 4, RULE_matchingOptions = 5, RULE_matchingAnswers = 6, 
		RULE_matchingAnswer = 7, RULE_missingWord = 8, RULE_missingWordText = 9, 
		RULE_mwText = 10, RULE_missingWordOptions = 11, RULE_missingWordOption = 12, 
		RULE_missingWordAnswer = 13, RULE_multipleChoice = 14, RULE_multipleChoiceText = 15, 
		RULE_multipleChoiceOption = 16, RULE_multipleChoiceAnswer = 17, RULE_numeric = 18, 
		RULE_numericText = 19, RULE_numericAnswer = 20, RULE_short = 21, RULE_shortText = 22, 
		RULE_shortAnswer = 23, RULE_trueOrFalse = 24, RULE_trueOrFalseText = 25, 
		RULE_trueOrFalseAnswer = 26;
	private static String[] makeRuleNames() {
		return new String[] {
			"question", "quotation", "questionType", "matching", "matchingText", 
			"matchingOptions", "matchingAnswers", "matchingAnswer", "missingWord", 
			"missingWordText", "mwText", "missingWordOptions", "missingWordOption", 
			"missingWordAnswer", "multipleChoice", "multipleChoiceText", "multipleChoiceOption", 
			"multipleChoiceAnswer", "numeric", "numericText", "numericAnswer", "short", 
			"shortText", "shortAnswer", "trueOrFalse", "trueOrFalseText", "trueOrFalseAnswer"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'MatchingQuestion'", "','", "'MissingWordQuestion'", "'-'", "'MultipleChoiceQuestion'", 
			"'NumericQuestion'", "'ShortQuestion'", "'TrueFalseQuestion'", "'True'", 
			"'False'", "'??'", "'?'", "';'", null, null, "'->'", "'***'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, "INITQUESTION", 
			"QUESTION", "SEPARATOR", "NUM", "TEXT", "ARROW", "GAPSPACE", "WS"
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
	public String getGrammarFileName() { return "Question.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public QuestionParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QuestionContext extends ParserRuleContext {
		public List<TerminalNode> INITQUESTION() { return getTokens(QuestionParser.INITQUESTION); }
		public TerminalNode INITQUESTION(int i) {
			return getToken(QuestionParser.INITQUESTION, i);
		}
		public QuestionTypeContext questionType() {
			return getRuleContext(QuestionTypeContext.class,0);
		}
		public TerminalNode SEPARATOR() { return getToken(QuestionParser.SEPARATOR, 0); }
		public QuotationContext quotation() {
			return getRuleContext(QuotationContext.class,0);
		}
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_question);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(INITQUESTION);
			setState(55);
			questionType();
			setState(56);
			match(SEPARATOR);
			setState(57);
			quotation();
			setState(58);
			match(INITQUESTION);
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
	public static class QuotationContext extends ParserRuleContext {
		public TerminalNode NUM() { return getToken(QuestionParser.NUM, 0); }
		public QuotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quotation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterQuotation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitQuotation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitQuotation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuotationContext quotation() throws RecognitionException {
		QuotationContext _localctx = new QuotationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_quotation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			match(NUM);
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
	public static class QuestionTypeContext extends ParserRuleContext {
		public MatchingContext matching() {
			return getRuleContext(MatchingContext.class,0);
		}
		public MissingWordContext missingWord() {
			return getRuleContext(MissingWordContext.class,0);
		}
		public MultipleChoiceContext multipleChoice() {
			return getRuleContext(MultipleChoiceContext.class,0);
		}
		public NumericContext numeric() {
			return getRuleContext(NumericContext.class,0);
		}
		public ShortContext short_() {
			return getRuleContext(ShortContext.class,0);
		}
		public TrueOrFalseContext trueOrFalse() {
			return getRuleContext(TrueOrFalseContext.class,0);
		}
		public QuestionTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterQuestionType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitQuestionType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitQuestionType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionTypeContext questionType() throws RecognitionException {
		QuestionTypeContext _localctx = new QuestionTypeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_questionType);
		try {
			setState(68);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(62);
				matching();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				setState(63);
				missingWord();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(64);
				multipleChoice();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 4);
				{
				setState(65);
				numeric();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 5);
				{
				setState(66);
				short_();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 6);
				{
				setState(67);
				trueOrFalse();
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
	public static class MatchingContext extends ParserRuleContext {
		public List<TerminalNode> SEPARATOR() { return getTokens(QuestionParser.SEPARATOR); }
		public TerminalNode SEPARATOR(int i) {
			return getToken(QuestionParser.SEPARATOR, i);
		}
		public MatchingTextContext matchingText() {
			return getRuleContext(MatchingTextContext.class,0);
		}
		public MatchingAnswersContext matchingAnswers() {
			return getRuleContext(MatchingAnswersContext.class,0);
		}
		public MatchingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matching; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterMatching(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitMatching(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitMatching(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchingContext matching() throws RecognitionException {
		MatchingContext _localctx = new MatchingContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_matching);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(T__0);
			setState(71);
			match(SEPARATOR);
			setState(72);
			matchingText();
			setState(73);
			match(SEPARATOR);
			setState(74);
			matchingAnswers();
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
	public static class MatchingTextContext extends ParserRuleContext {
		public TerminalNode TEXT() { return getToken(QuestionParser.TEXT, 0); }
		public List<TerminalNode> SEPARATOR() { return getTokens(QuestionParser.SEPARATOR); }
		public TerminalNode SEPARATOR(int i) {
			return getToken(QuestionParser.SEPARATOR, i);
		}
		public List<MatchingOptionsContext> matchingOptions() {
			return getRuleContexts(MatchingOptionsContext.class);
		}
		public MatchingOptionsContext matchingOptions(int i) {
			return getRuleContext(MatchingOptionsContext.class,i);
		}
		public MatchingTextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matchingText; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterMatchingText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitMatchingText(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitMatchingText(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchingTextContext matchingText() throws RecognitionException {
		MatchingTextContext _localctx = new MatchingTextContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_matchingText);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			match(TEXT);
			setState(77);
			match(SEPARATOR);
			setState(78);
			matchingOptions();
			setState(79);
			match(SEPARATOR);
			setState(80);
			matchingOptions();
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
	public static class MatchingOptionsContext extends ParserRuleContext {
		public TerminalNode TEXT() { return getToken(QuestionParser.TEXT, 0); }
		public MatchingOptionsContext matchingOptions() {
			return getRuleContext(MatchingOptionsContext.class,0);
		}
		public MatchingOptionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matchingOptions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterMatchingOptions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitMatchingOptions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitMatchingOptions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchingOptionsContext matchingOptions() throws RecognitionException {
		MatchingOptionsContext _localctx = new MatchingOptionsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_matchingOptions);
		try {
			setState(86);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(82);
				match(TEXT);
				setState(83);
				match(T__1);
				setState(84);
				matchingOptions();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(85);
				match(TEXT);
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
	public static class MatchingAnswersContext extends ParserRuleContext {
		public List<MatchingAnswerContext> matchingAnswer() {
			return getRuleContexts(MatchingAnswerContext.class);
		}
		public MatchingAnswerContext matchingAnswer(int i) {
			return getRuleContext(MatchingAnswerContext.class,i);
		}
		public MatchingAnswersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matchingAnswers; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterMatchingAnswers(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitMatchingAnswers(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitMatchingAnswers(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchingAnswersContext matchingAnswers() throws RecognitionException {
		MatchingAnswersContext _localctx = new MatchingAnswersContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_matchingAnswers);
		try {
			setState(93);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(88);
				matchingAnswer();
				setState(89);
				match(T__1);
				setState(90);
				matchingAnswer();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(92);
				matchingAnswer();
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
	public static class MatchingAnswerContext extends ParserRuleContext {
		public List<TerminalNode> TEXT() { return getTokens(QuestionParser.TEXT); }
		public TerminalNode TEXT(int i) {
			return getToken(QuestionParser.TEXT, i);
		}
		public TerminalNode ARROW() { return getToken(QuestionParser.ARROW, 0); }
		public MatchingAnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matchingAnswer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterMatchingAnswer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitMatchingAnswer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitMatchingAnswer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchingAnswerContext matchingAnswer() throws RecognitionException {
		MatchingAnswerContext _localctx = new MatchingAnswerContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_matchingAnswer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			match(TEXT);
			setState(96);
			match(ARROW);
			setState(97);
			match(TEXT);
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
	public static class MissingWordContext extends ParserRuleContext {
		public List<TerminalNode> SEPARATOR() { return getTokens(QuestionParser.SEPARATOR); }
		public TerminalNode SEPARATOR(int i) {
			return getToken(QuestionParser.SEPARATOR, i);
		}
		public MissingWordTextContext missingWordText() {
			return getRuleContext(MissingWordTextContext.class,0);
		}
		public MissingWordAnswerContext missingWordAnswer() {
			return getRuleContext(MissingWordAnswerContext.class,0);
		}
		public MissingWordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_missingWord; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterMissingWord(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitMissingWord(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitMissingWord(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MissingWordContext missingWord() throws RecognitionException {
		MissingWordContext _localctx = new MissingWordContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_missingWord);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			match(T__2);
			setState(100);
			match(SEPARATOR);
			setState(101);
			missingWordText();
			setState(102);
			match(SEPARATOR);
			setState(103);
			missingWordAnswer();
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
	public static class MissingWordTextContext extends ParserRuleContext {
		public MwTextContext mwText() {
			return getRuleContext(MwTextContext.class,0);
		}
		public TerminalNode SEPARATOR() { return getToken(QuestionParser.SEPARATOR, 0); }
		public MissingWordOptionsContext missingWordOptions() {
			return getRuleContext(MissingWordOptionsContext.class,0);
		}
		public MissingWordTextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_missingWordText; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterMissingWordText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitMissingWordText(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitMissingWordText(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MissingWordTextContext missingWordText() throws RecognitionException {
		MissingWordTextContext _localctx = new MissingWordTextContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_missingWordText);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			mwText();
			setState(106);
			match(SEPARATOR);
			setState(107);
			missingWordOptions();
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
	public static class MwTextContext extends ParserRuleContext {
		public TerminalNode GAPSPACE() { return getToken(QuestionParser.GAPSPACE, 0); }
		public List<TerminalNode> TEXT() { return getTokens(QuestionParser.TEXT); }
		public TerminalNode TEXT(int i) {
			return getToken(QuestionParser.TEXT, i);
		}
		public MwTextContext mwText() {
			return getRuleContext(MwTextContext.class,0);
		}
		public MwTextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mwText; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterMwText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitMwText(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitMwText(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MwTextContext mwText() throws RecognitionException {
		MwTextContext _localctx = new MwTextContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_mwText);
		try {
			setState(122);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(109);
				match(GAPSPACE);
				setState(110);
				match(TEXT);
				setState(111);
				mwText();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(112);
				match(TEXT);
				setState(113);
				match(GAPSPACE);
				setState(114);
				mwText();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(115);
				match(TEXT);
				setState(116);
				match(GAPSPACE);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(117);
				match(GAPSPACE);
				setState(118);
				match(TEXT);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(119);
				match(TEXT);
				setState(120);
				match(GAPSPACE);
				setState(121);
				match(TEXT);
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
	public static class MissingWordOptionsContext extends ParserRuleContext {
		public List<MissingWordOptionContext> missingWordOption() {
			return getRuleContexts(MissingWordOptionContext.class);
		}
		public MissingWordOptionContext missingWordOption(int i) {
			return getRuleContext(MissingWordOptionContext.class,i);
		}
		public MissingWordOptionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_missingWordOptions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterMissingWordOptions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitMissingWordOptions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitMissingWordOptions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MissingWordOptionsContext missingWordOptions() throws RecognitionException {
		MissingWordOptionsContext _localctx = new MissingWordOptionsContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_missingWordOptions);
		try {
			setState(129);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(124);
				missingWordOption();
				setState(125);
				match(T__1);
				setState(126);
				missingWordOption();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(128);
				missingWordOption();
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
	public static class MissingWordOptionContext extends ParserRuleContext {
		public TerminalNode TEXT() { return getToken(QuestionParser.TEXT, 0); }
		public MissingWordOptionContext missingWordOption() {
			return getRuleContext(MissingWordOptionContext.class,0);
		}
		public MissingWordOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_missingWordOption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterMissingWordOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitMissingWordOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitMissingWordOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MissingWordOptionContext missingWordOption() throws RecognitionException {
		MissingWordOptionContext _localctx = new MissingWordOptionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_missingWordOption);
		try {
			setState(135);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(131);
				match(TEXT);
				setState(132);
				match(T__3);
				setState(133);
				missingWordOption();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(134);
				match(TEXT);
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
	public static class MissingWordAnswerContext extends ParserRuleContext {
		public TerminalNode TEXT() { return getToken(QuestionParser.TEXT, 0); }
		public MissingWordAnswerContext missingWordAnswer() {
			return getRuleContext(MissingWordAnswerContext.class,0);
		}
		public MissingWordAnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_missingWordAnswer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterMissingWordAnswer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitMissingWordAnswer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitMissingWordAnswer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MissingWordAnswerContext missingWordAnswer() throws RecognitionException {
		MissingWordAnswerContext _localctx = new MissingWordAnswerContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_missingWordAnswer);
		try {
			setState(141);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(137);
				match(TEXT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(138);
				match(TEXT);
				setState(139);
				match(T__1);
				setState(140);
				missingWordAnswer();
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
	public static class MultipleChoiceContext extends ParserRuleContext {
		public List<TerminalNode> SEPARATOR() { return getTokens(QuestionParser.SEPARATOR); }
		public TerminalNode SEPARATOR(int i) {
			return getToken(QuestionParser.SEPARATOR, i);
		}
		public MultipleChoiceTextContext multipleChoiceText() {
			return getRuleContext(MultipleChoiceTextContext.class,0);
		}
		public MultipleChoiceAnswerContext multipleChoiceAnswer() {
			return getRuleContext(MultipleChoiceAnswerContext.class,0);
		}
		public MultipleChoiceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multipleChoice; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterMultipleChoice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitMultipleChoice(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitMultipleChoice(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultipleChoiceContext multipleChoice() throws RecognitionException {
		MultipleChoiceContext _localctx = new MultipleChoiceContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_multipleChoice);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			match(T__4);
			setState(144);
			match(SEPARATOR);
			setState(145);
			multipleChoiceText();
			setState(146);
			match(SEPARATOR);
			setState(147);
			multipleChoiceAnswer();
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
	public static class MultipleChoiceTextContext extends ParserRuleContext {
		public TerminalNode TEXT() { return getToken(QuestionParser.TEXT, 0); }
		public TerminalNode SEPARATOR() { return getToken(QuestionParser.SEPARATOR, 0); }
		public MultipleChoiceOptionContext multipleChoiceOption() {
			return getRuleContext(MultipleChoiceOptionContext.class,0);
		}
		public TerminalNode QUESTION() { return getToken(QuestionParser.QUESTION, 0); }
		public MultipleChoiceTextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multipleChoiceText; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterMultipleChoiceText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitMultipleChoiceText(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitMultipleChoiceText(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultipleChoiceTextContext multipleChoiceText() throws RecognitionException {
		MultipleChoiceTextContext _localctx = new MultipleChoiceTextContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_multipleChoiceText);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			match(TEXT);
			setState(151);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==QUESTION) {
				{
				setState(150);
				match(QUESTION);
				}
			}

			setState(153);
			match(SEPARATOR);
			setState(154);
			multipleChoiceOption();
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
	public static class MultipleChoiceOptionContext extends ParserRuleContext {
		public TerminalNode TEXT() { return getToken(QuestionParser.TEXT, 0); }
		public MultipleChoiceOptionContext multipleChoiceOption() {
			return getRuleContext(MultipleChoiceOptionContext.class,0);
		}
		public MultipleChoiceOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multipleChoiceOption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterMultipleChoiceOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitMultipleChoiceOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitMultipleChoiceOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultipleChoiceOptionContext multipleChoiceOption() throws RecognitionException {
		MultipleChoiceOptionContext _localctx = new MultipleChoiceOptionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_multipleChoiceOption);
		try {
			setState(160);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(156);
				match(TEXT);
				setState(157);
				match(T__1);
				setState(158);
				multipleChoiceOption();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(159);
				match(TEXT);
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
	public static class MultipleChoiceAnswerContext extends ParserRuleContext {
		public TerminalNode TEXT() { return getToken(QuestionParser.TEXT, 0); }
		public MultipleChoiceAnswerContext multipleChoiceAnswer() {
			return getRuleContext(MultipleChoiceAnswerContext.class,0);
		}
		public MultipleChoiceAnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multipleChoiceAnswer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterMultipleChoiceAnswer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitMultipleChoiceAnswer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitMultipleChoiceAnswer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultipleChoiceAnswerContext multipleChoiceAnswer() throws RecognitionException {
		MultipleChoiceAnswerContext _localctx = new MultipleChoiceAnswerContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_multipleChoiceAnswer);
		try {
			setState(166);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(162);
				match(TEXT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(163);
				match(TEXT);
				setState(164);
				match(T__1);
				setState(165);
				multipleChoiceAnswer();
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
	public static class NumericContext extends ParserRuleContext {
		public List<TerminalNode> SEPARATOR() { return getTokens(QuestionParser.SEPARATOR); }
		public TerminalNode SEPARATOR(int i) {
			return getToken(QuestionParser.SEPARATOR, i);
		}
		public NumericTextContext numericText() {
			return getRuleContext(NumericTextContext.class,0);
		}
		public NumericAnswerContext numericAnswer() {
			return getRuleContext(NumericAnswerContext.class,0);
		}
		public NumericContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numeric; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterNumeric(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitNumeric(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitNumeric(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumericContext numeric() throws RecognitionException {
		NumericContext _localctx = new NumericContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_numeric);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			match(T__5);
			setState(169);
			match(SEPARATOR);
			setState(170);
			numericText();
			setState(171);
			match(SEPARATOR);
			setState(172);
			numericAnswer();
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
	public static class NumericTextContext extends ParserRuleContext {
		public TerminalNode TEXT() { return getToken(QuestionParser.TEXT, 0); }
		public TerminalNode QUESTION() { return getToken(QuestionParser.QUESTION, 0); }
		public NumericTextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numericText; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterNumericText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitNumericText(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitNumericText(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumericTextContext numericText() throws RecognitionException {
		NumericTextContext _localctx = new NumericTextContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_numericText);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			match(TEXT);
			setState(176);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==QUESTION) {
				{
				setState(175);
				match(QUESTION);
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
	public static class NumericAnswerContext extends ParserRuleContext {
		public TerminalNode NUM() { return getToken(QuestionParser.NUM, 0); }
		public NumericAnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numericAnswer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterNumericAnswer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitNumericAnswer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitNumericAnswer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumericAnswerContext numericAnswer() throws RecognitionException {
		NumericAnswerContext _localctx = new NumericAnswerContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_numericAnswer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			match(NUM);
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
	public static class ShortContext extends ParserRuleContext {
		public List<TerminalNode> SEPARATOR() { return getTokens(QuestionParser.SEPARATOR); }
		public TerminalNode SEPARATOR(int i) {
			return getToken(QuestionParser.SEPARATOR, i);
		}
		public ShortTextContext shortText() {
			return getRuleContext(ShortTextContext.class,0);
		}
		public ShortAnswerContext shortAnswer() {
			return getRuleContext(ShortAnswerContext.class,0);
		}
		public ShortContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_short; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterShort(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitShort(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitShort(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ShortContext short_() throws RecognitionException {
		ShortContext _localctx = new ShortContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_short);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			match(T__6);
			setState(181);
			match(SEPARATOR);
			setState(182);
			shortText();
			setState(183);
			match(SEPARATOR);
			setState(184);
			shortAnswer();
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
	public static class ShortTextContext extends ParserRuleContext {
		public TerminalNode TEXT() { return getToken(QuestionParser.TEXT, 0); }
		public TerminalNode QUESTION() { return getToken(QuestionParser.QUESTION, 0); }
		public ShortTextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shortText; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterShortText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitShortText(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitShortText(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ShortTextContext shortText() throws RecognitionException {
		ShortTextContext _localctx = new ShortTextContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_shortText);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			match(TEXT);
			setState(188);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==QUESTION) {
				{
				setState(187);
				match(QUESTION);
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
	public static class ShortAnswerContext extends ParserRuleContext {
		public TerminalNode TEXT() { return getToken(QuestionParser.TEXT, 0); }
		public ShortAnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shortAnswer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterShortAnswer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitShortAnswer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitShortAnswer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ShortAnswerContext shortAnswer() throws RecognitionException {
		ShortAnswerContext _localctx = new ShortAnswerContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_shortAnswer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(190);
			match(TEXT);
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
	public static class TrueOrFalseContext extends ParserRuleContext {
		public List<TerminalNode> SEPARATOR() { return getTokens(QuestionParser.SEPARATOR); }
		public TerminalNode SEPARATOR(int i) {
			return getToken(QuestionParser.SEPARATOR, i);
		}
		public TrueOrFalseTextContext trueOrFalseText() {
			return getRuleContext(TrueOrFalseTextContext.class,0);
		}
		public TrueOrFalseAnswerContext trueOrFalseAnswer() {
			return getRuleContext(TrueOrFalseAnswerContext.class,0);
		}
		public TerminalNode QUESTION() { return getToken(QuestionParser.QUESTION, 0); }
		public TrueOrFalseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_trueOrFalse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterTrueOrFalse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitTrueOrFalse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitTrueOrFalse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TrueOrFalseContext trueOrFalse() throws RecognitionException {
		TrueOrFalseContext _localctx = new TrueOrFalseContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_trueOrFalse);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			match(T__7);
			setState(193);
			match(SEPARATOR);
			setState(194);
			trueOrFalseText();
			setState(196);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==QUESTION) {
				{
				setState(195);
				match(QUESTION);
				}
			}

			setState(198);
			match(SEPARATOR);
			setState(199);
			trueOrFalseAnswer();
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
	public static class TrueOrFalseTextContext extends ParserRuleContext {
		public TerminalNode TEXT() { return getToken(QuestionParser.TEXT, 0); }
		public TerminalNode QUESTION() { return getToken(QuestionParser.QUESTION, 0); }
		public TrueOrFalseTextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_trueOrFalseText; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterTrueOrFalseText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitTrueOrFalseText(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitTrueOrFalseText(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TrueOrFalseTextContext trueOrFalseText() throws RecognitionException {
		TrueOrFalseTextContext _localctx = new TrueOrFalseTextContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_trueOrFalseText);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			match(TEXT);
			setState(203);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(202);
				match(QUESTION);
				}
				break;
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
	public static class TrueOrFalseAnswerContext extends ParserRuleContext {
		public TrueOrFalseAnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_trueOrFalseAnswer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterTrueOrFalseAnswer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitTrueOrFalseAnswer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitTrueOrFalseAnswer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TrueOrFalseAnswerContext trueOrFalseAnswer() throws RecognitionException {
		TrueOrFalseAnswerContext _localctx = new TrueOrFalseAnswerContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_trueOrFalseAnswer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			_la = _input.LA(1);
			if ( !(_la==T__8 || _la==T__9) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static final String _serializedATN =
		"\u0004\u0001\u0012\u00d0\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007"+
		"\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007"+
		"\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007"+
		"\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007"+
		"\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0003\u0002E\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0003\u0005W\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0003\u0006^\b\u0006\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n{\b"+
		"\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b"+
		"\u0082\b\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u0088\b\f\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0003\r\u008e\b\r\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0003"+
		"\u000f\u0098\b\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u00a1\b\u0010\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u00a7\b\u0011\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001"+
		"\u0013\u0003\u0013\u00b1\b\u0013\u0001\u0014\u0001\u0014\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001"+
		"\u0016\u0003\u0016\u00bd\b\u0016\u0001\u0017\u0001\u0017\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0003\u0018\u00c5\b\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0003\u0019\u00cc\b\u0019\u0001"+
		"\u001a\u0001\u001a\u0001\u001a\u0000\u0000\u001b\u0000\u0002\u0004\u0006"+
		"\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,."+
		"024\u0000\u0001\u0001\u0000\t\n\u00c9\u00006\u0001\u0000\u0000\u0000\u0002"+
		"<\u0001\u0000\u0000\u0000\u0004D\u0001\u0000\u0000\u0000\u0006F\u0001"+
		"\u0000\u0000\u0000\bL\u0001\u0000\u0000\u0000\nV\u0001\u0000\u0000\u0000"+
		"\f]\u0001\u0000\u0000\u0000\u000e_\u0001\u0000\u0000\u0000\u0010c\u0001"+
		"\u0000\u0000\u0000\u0012i\u0001\u0000\u0000\u0000\u0014z\u0001\u0000\u0000"+
		"\u0000\u0016\u0081\u0001\u0000\u0000\u0000\u0018\u0087\u0001\u0000\u0000"+
		"\u0000\u001a\u008d\u0001\u0000\u0000\u0000\u001c\u008f\u0001\u0000\u0000"+
		"\u0000\u001e\u0095\u0001\u0000\u0000\u0000 \u00a0\u0001\u0000\u0000\u0000"+
		"\"\u00a6\u0001\u0000\u0000\u0000$\u00a8\u0001\u0000\u0000\u0000&\u00ae"+
		"\u0001\u0000\u0000\u0000(\u00b2\u0001\u0000\u0000\u0000*\u00b4\u0001\u0000"+
		"\u0000\u0000,\u00ba\u0001\u0000\u0000\u0000.\u00be\u0001\u0000\u0000\u0000"+
		"0\u00c0\u0001\u0000\u0000\u00002\u00c9\u0001\u0000\u0000\u00004\u00cd"+
		"\u0001\u0000\u0000\u000067\u0005\u000b\u0000\u000078\u0003\u0004\u0002"+
		"\u000089\u0005\r\u0000\u00009:\u0003\u0002\u0001\u0000:;\u0005\u000b\u0000"+
		"\u0000;\u0001\u0001\u0000\u0000\u0000<=\u0005\u000e\u0000\u0000=\u0003"+
		"\u0001\u0000\u0000\u0000>E\u0003\u0006\u0003\u0000?E\u0003\u0010\b\u0000"+
		"@E\u0003\u001c\u000e\u0000AE\u0003$\u0012\u0000BE\u0003*\u0015\u0000C"+
		"E\u00030\u0018\u0000D>\u0001\u0000\u0000\u0000D?\u0001\u0000\u0000\u0000"+
		"D@\u0001\u0000\u0000\u0000DA\u0001\u0000\u0000\u0000DB\u0001\u0000\u0000"+
		"\u0000DC\u0001\u0000\u0000\u0000E\u0005\u0001\u0000\u0000\u0000FG\u0005"+
		"\u0001\u0000\u0000GH\u0005\r\u0000\u0000HI\u0003\b\u0004\u0000IJ\u0005"+
		"\r\u0000\u0000JK\u0003\f\u0006\u0000K\u0007\u0001\u0000\u0000\u0000LM"+
		"\u0005\u000f\u0000\u0000MN\u0005\r\u0000\u0000NO\u0003\n\u0005\u0000O"+
		"P\u0005\r\u0000\u0000PQ\u0003\n\u0005\u0000Q\t\u0001\u0000\u0000\u0000"+
		"RS\u0005\u000f\u0000\u0000ST\u0005\u0002\u0000\u0000TW\u0003\n\u0005\u0000"+
		"UW\u0005\u000f\u0000\u0000VR\u0001\u0000\u0000\u0000VU\u0001\u0000\u0000"+
		"\u0000W\u000b\u0001\u0000\u0000\u0000XY\u0003\u000e\u0007\u0000YZ\u0005"+
		"\u0002\u0000\u0000Z[\u0003\u000e\u0007\u0000[^\u0001\u0000\u0000\u0000"+
		"\\^\u0003\u000e\u0007\u0000]X\u0001\u0000\u0000\u0000]\\\u0001\u0000\u0000"+
		"\u0000^\r\u0001\u0000\u0000\u0000_`\u0005\u000f\u0000\u0000`a\u0005\u0010"+
		"\u0000\u0000ab\u0005\u000f\u0000\u0000b\u000f\u0001\u0000\u0000\u0000"+
		"cd\u0005\u0003\u0000\u0000de\u0005\r\u0000\u0000ef\u0003\u0012\t\u0000"+
		"fg\u0005\r\u0000\u0000gh\u0003\u001a\r\u0000h\u0011\u0001\u0000\u0000"+
		"\u0000ij\u0003\u0014\n\u0000jk\u0005\r\u0000\u0000kl\u0003\u0016\u000b"+
		"\u0000l\u0013\u0001\u0000\u0000\u0000mn\u0005\u0011\u0000\u0000no\u0005"+
		"\u000f\u0000\u0000o{\u0003\u0014\n\u0000pq\u0005\u000f\u0000\u0000qr\u0005"+
		"\u0011\u0000\u0000r{\u0003\u0014\n\u0000st\u0005\u000f\u0000\u0000t{\u0005"+
		"\u0011\u0000\u0000uv\u0005\u0011\u0000\u0000v{\u0005\u000f\u0000\u0000"+
		"wx\u0005\u000f\u0000\u0000xy\u0005\u0011\u0000\u0000y{\u0005\u000f\u0000"+
		"\u0000zm\u0001\u0000\u0000\u0000zp\u0001\u0000\u0000\u0000zs\u0001\u0000"+
		"\u0000\u0000zu\u0001\u0000\u0000\u0000zw\u0001\u0000\u0000\u0000{\u0015"+
		"\u0001\u0000\u0000\u0000|}\u0003\u0018\f\u0000}~\u0005\u0002\u0000\u0000"+
		"~\u007f\u0003\u0018\f\u0000\u007f\u0082\u0001\u0000\u0000\u0000\u0080"+
		"\u0082\u0003\u0018\f\u0000\u0081|\u0001\u0000\u0000\u0000\u0081\u0080"+
		"\u0001\u0000\u0000\u0000\u0082\u0017\u0001\u0000\u0000\u0000\u0083\u0084"+
		"\u0005\u000f\u0000\u0000\u0084\u0085\u0005\u0004\u0000\u0000\u0085\u0088"+
		"\u0003\u0018\f\u0000\u0086\u0088\u0005\u000f\u0000\u0000\u0087\u0083\u0001"+
		"\u0000\u0000\u0000\u0087\u0086\u0001\u0000\u0000\u0000\u0088\u0019\u0001"+
		"\u0000\u0000\u0000\u0089\u008e\u0005\u000f\u0000\u0000\u008a\u008b\u0005"+
		"\u000f\u0000\u0000\u008b\u008c\u0005\u0002\u0000\u0000\u008c\u008e\u0003"+
		"\u001a\r\u0000\u008d\u0089\u0001\u0000\u0000\u0000\u008d\u008a\u0001\u0000"+
		"\u0000\u0000\u008e\u001b\u0001\u0000\u0000\u0000\u008f\u0090\u0005\u0005"+
		"\u0000\u0000\u0090\u0091\u0005\r\u0000\u0000\u0091\u0092\u0003\u001e\u000f"+
		"\u0000\u0092\u0093\u0005\r\u0000\u0000\u0093\u0094\u0003\"\u0011\u0000"+
		"\u0094\u001d\u0001\u0000\u0000\u0000\u0095\u0097\u0005\u000f\u0000\u0000"+
		"\u0096\u0098\u0005\f\u0000\u0000\u0097\u0096\u0001\u0000\u0000\u0000\u0097"+
		"\u0098\u0001\u0000\u0000\u0000\u0098\u0099\u0001\u0000\u0000\u0000\u0099"+
		"\u009a\u0005\r\u0000\u0000\u009a\u009b\u0003 \u0010\u0000\u009b\u001f"+
		"\u0001\u0000\u0000\u0000\u009c\u009d\u0005\u000f\u0000\u0000\u009d\u009e"+
		"\u0005\u0002\u0000\u0000\u009e\u00a1\u0003 \u0010\u0000\u009f\u00a1\u0005"+
		"\u000f\u0000\u0000\u00a0\u009c\u0001\u0000\u0000\u0000\u00a0\u009f\u0001"+
		"\u0000\u0000\u0000\u00a1!\u0001\u0000\u0000\u0000\u00a2\u00a7\u0005\u000f"+
		"\u0000\u0000\u00a3\u00a4\u0005\u000f\u0000\u0000\u00a4\u00a5\u0005\u0002"+
		"\u0000\u0000\u00a5\u00a7\u0003\"\u0011\u0000\u00a6\u00a2\u0001\u0000\u0000"+
		"\u0000\u00a6\u00a3\u0001\u0000\u0000\u0000\u00a7#\u0001\u0000\u0000\u0000"+
		"\u00a8\u00a9\u0005\u0006\u0000\u0000\u00a9\u00aa\u0005\r\u0000\u0000\u00aa"+
		"\u00ab\u0003&\u0013\u0000\u00ab\u00ac\u0005\r\u0000\u0000\u00ac\u00ad"+
		"\u0003(\u0014\u0000\u00ad%\u0001\u0000\u0000\u0000\u00ae\u00b0\u0005\u000f"+
		"\u0000\u0000\u00af\u00b1\u0005\f\u0000\u0000\u00b0\u00af\u0001\u0000\u0000"+
		"\u0000\u00b0\u00b1\u0001\u0000\u0000\u0000\u00b1\'\u0001\u0000\u0000\u0000"+
		"\u00b2\u00b3\u0005\u000e\u0000\u0000\u00b3)\u0001\u0000\u0000\u0000\u00b4"+
		"\u00b5\u0005\u0007\u0000\u0000\u00b5\u00b6\u0005\r\u0000\u0000\u00b6\u00b7"+
		"\u0003,\u0016\u0000\u00b7\u00b8\u0005\r\u0000\u0000\u00b8\u00b9\u0003"+
		".\u0017\u0000\u00b9+\u0001\u0000\u0000\u0000\u00ba\u00bc\u0005\u000f\u0000"+
		"\u0000\u00bb\u00bd\u0005\f\u0000\u0000\u00bc\u00bb\u0001\u0000\u0000\u0000"+
		"\u00bc\u00bd\u0001\u0000\u0000\u0000\u00bd-\u0001\u0000\u0000\u0000\u00be"+
		"\u00bf\u0005\u000f\u0000\u0000\u00bf/\u0001\u0000\u0000\u0000\u00c0\u00c1"+
		"\u0005\b\u0000\u0000\u00c1\u00c2\u0005\r\u0000\u0000\u00c2\u00c4\u0003"+
		"2\u0019\u0000\u00c3\u00c5\u0005\f\u0000\u0000\u00c4\u00c3\u0001\u0000"+
		"\u0000\u0000\u00c4\u00c5\u0001\u0000\u0000\u0000\u00c5\u00c6\u0001\u0000"+
		"\u0000\u0000\u00c6\u00c7\u0005\r\u0000\u0000\u00c7\u00c8\u00034\u001a"+
		"\u0000\u00c81\u0001\u0000\u0000\u0000\u00c9\u00cb\u0005\u000f\u0000\u0000"+
		"\u00ca\u00cc\u0005\f\u0000\u0000\u00cb\u00ca\u0001\u0000\u0000\u0000\u00cb"+
		"\u00cc\u0001\u0000\u0000\u0000\u00cc3\u0001\u0000\u0000\u0000\u00cd\u00ce"+
		"\u0007\u0000\u0000\u0000\u00ce5\u0001\u0000\u0000\u0000\u000eDV]z\u0081"+
		"\u0087\u008d\u0097\u00a0\u00a6\u00b0\u00bc\u00c4\u00cb";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}