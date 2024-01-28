// Generated from C:/Repositories/sem4pi-22-23-6/base.core/src/main/java/application/base/application/examManagement/examParsers\AutoExam.g4 by ANTLR 4.12.0
package application.base.application.examManagement.createAutoExamParsers;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class AutoExamParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, INIT=8, INITQUESTION=9, 
		SEPARATOR=10, SECTIONOPEN=11, SECTIONCLOSE=12, NUM=13, TEXT=14, SLASH=15, 
		ARROW=16, GAPSPACE=17, QUESTION=18, WS=19;
	public static final int
		RULE_stat = 0, RULE_title = 1, RULE_description = 2, RULE_language = 3, 
		RULE_header = 4, RULE_headerDescription = 5, RULE_feedbackType = 6, RULE_gradeType = 7, 
		RULE_structure = 8, RULE_section = 9, RULE_sectionStructure = 10, RULE_question = 11, 
		RULE_questiontype = 12, RULE_matching = 13, RULE_missingWord = 14, RULE_multipleChoice = 15, 
		RULE_numeric = 16, RULE_short = 17, RULE_trueOrFalse = 18;
	private static String[] makeRuleNames() {
		return new String[] {
			"stat", "title", "description", "language", "header", "headerDescription", 
			"feedbackType", "gradeType", "structure", "section", "sectionStructure", 
			"question", "questiontype", "matching", "missingWord", "multipleChoice", 
			"numeric", "short", "trueOrFalse"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'ON_SUBMISSION'", "'MatchingQuestion'", "'MissingWordQuestion'", 
			"'MultipleChoiceQuestion'", "'NumericQuestion'", "'ShortQuestion'", "'TrueFalseQuestion'", 
			"'\"'", "'??'", "';'", "'<<'", "'>>'", null, null, "'/'", "'->'", "'***'", 
			"'?'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, "INIT", "INITQUESTION", 
			"SEPARATOR", "SECTIONOPEN", "SECTIONCLOSE", "NUM", "TEXT", "SLASH", "ARROW", 
			"GAPSPACE", "QUESTION", "WS"
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
	public String getGrammarFileName() { return "AutoExam.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public AutoExamParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatContext extends ParserRuleContext {
		public List<TerminalNode> INIT() { return getTokens(AutoExamParser.INIT); }
		public TerminalNode INIT(int i) {
			return getToken(AutoExamParser.INIT, i);
		}
		public TitleContext title() {
			return getRuleContext(TitleContext.class,0);
		}
		public List<TerminalNode> SEPARATOR() { return getTokens(AutoExamParser.SEPARATOR); }
		public TerminalNode SEPARATOR(int i) {
			return getToken(AutoExamParser.SEPARATOR, i);
		}
		public DescriptionContext description() {
			return getRuleContext(DescriptionContext.class,0);
		}
		public LanguageContext language() {
			return getRuleContext(LanguageContext.class,0);
		}
		public HeaderContext header() {
			return getRuleContext(HeaderContext.class,0);
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
			if ( listener instanceof AutoExamListener ) ((AutoExamListener)listener).enterStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutoExamListener ) ((AutoExamListener)listener).exitStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutoExamVisitor ) return ((AutoExamVisitor<? extends T>)visitor).visitStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_stat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			match(INIT);
			setState(39);
			title();
			setState(40);
			match(SEPARATOR);
			setState(41);
			description();
			setState(42);
			match(SEPARATOR);
			setState(43);
			language();
			setState(44);
			match(SEPARATOR);
			setState(45);
			header();
			setState(46);
			match(SEPARATOR);
			setState(47);
			structure();
			setState(48);
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
	public static class TitleContext extends ParserRuleContext {
		public TerminalNode TEXT() { return getToken(AutoExamParser.TEXT, 0); }
		public TitleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_title; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutoExamListener ) ((AutoExamListener)listener).enterTitle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutoExamListener ) ((AutoExamListener)listener).exitTitle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutoExamVisitor ) return ((AutoExamVisitor<? extends T>)visitor).visitTitle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TitleContext title() throws RecognitionException {
		TitleContext _localctx = new TitleContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_title);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
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
	public static class DescriptionContext extends ParserRuleContext {
		public TerminalNode TEXT() { return getToken(AutoExamParser.TEXT, 0); }
		public DescriptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_description; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutoExamListener ) ((AutoExamListener)listener).enterDescription(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutoExamListener ) ((AutoExamListener)listener).exitDescription(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutoExamVisitor ) return ((AutoExamVisitor<? extends T>)visitor).visitDescription(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DescriptionContext description() throws RecognitionException {
		DescriptionContext _localctx = new DescriptionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_description);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
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
	public static class LanguageContext extends ParserRuleContext {
		public TerminalNode TEXT() { return getToken(AutoExamParser.TEXT, 0); }
		public LanguageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_language; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutoExamListener ) ((AutoExamListener)listener).enterLanguage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutoExamListener ) ((AutoExamListener)listener).exitLanguage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutoExamVisitor ) return ((AutoExamVisitor<? extends T>)visitor).visitLanguage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LanguageContext language() throws RecognitionException {
		LanguageContext _localctx = new LanguageContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_language);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
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
	public static class HeaderContext extends ParserRuleContext {
		public HeaderDescriptionContext headerDescription() {
			return getRuleContext(HeaderDescriptionContext.class,0);
		}
		public List<TerminalNode> SEPARATOR() { return getTokens(AutoExamParser.SEPARATOR); }
		public TerminalNode SEPARATOR(int i) {
			return getToken(AutoExamParser.SEPARATOR, i);
		}
		public FeedbackTypeContext feedbackType() {
			return getRuleContext(FeedbackTypeContext.class,0);
		}
		public GradeTypeContext gradeType() {
			return getRuleContext(GradeTypeContext.class,0);
		}
		public HeaderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_header; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutoExamListener ) ((AutoExamListener)listener).enterHeader(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutoExamListener ) ((AutoExamListener)listener).exitHeader(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutoExamVisitor ) return ((AutoExamVisitor<? extends T>)visitor).visitHeader(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeaderContext header() throws RecognitionException {
		HeaderContext _localctx = new HeaderContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_header);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			headerDescription();
			setState(57);
			match(SEPARATOR);
			setState(58);
			feedbackType();
			setState(59);
			match(SEPARATOR);
			setState(60);
			gradeType();
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
	public static class HeaderDescriptionContext extends ParserRuleContext {
		public TerminalNode TEXT() { return getToken(AutoExamParser.TEXT, 0); }
		public HeaderDescriptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_headerDescription; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutoExamListener ) ((AutoExamListener)listener).enterHeaderDescription(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutoExamListener ) ((AutoExamListener)listener).exitHeaderDescription(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutoExamVisitor ) return ((AutoExamVisitor<? extends T>)visitor).visitHeaderDescription(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeaderDescriptionContext headerDescription() throws RecognitionException {
		HeaderDescriptionContext _localctx = new HeaderDescriptionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_headerDescription);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
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
	public static class FeedbackTypeContext extends ParserRuleContext {
		public FeedbackTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_feedbackType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutoExamListener ) ((AutoExamListener)listener).enterFeedbackType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutoExamListener ) ((AutoExamListener)listener).exitFeedbackType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutoExamVisitor ) return ((AutoExamVisitor<? extends T>)visitor).visitFeedbackType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FeedbackTypeContext feedbackType() throws RecognitionException {
		FeedbackTypeContext _localctx = new FeedbackTypeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_feedbackType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(T__0);
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
	public static class GradeTypeContext extends ParserRuleContext {
		public GradeTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gradeType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutoExamListener ) ((AutoExamListener)listener).enterGradeType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutoExamListener ) ((AutoExamListener)listener).exitGradeType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutoExamVisitor ) return ((AutoExamVisitor<? extends T>)visitor).visitGradeType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GradeTypeContext gradeType() throws RecognitionException {
		GradeTypeContext _localctx = new GradeTypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_gradeType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			match(T__0);
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
			if ( listener instanceof AutoExamListener ) ((AutoExamListener)listener).enterStructure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutoExamListener ) ((AutoExamListener)listener).exitStructure(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutoExamVisitor ) return ((AutoExamVisitor<? extends T>)visitor).visitStructure(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StructureContext structure() throws RecognitionException {
		StructureContext _localctx = new StructureContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_structure);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(68);
				section();
				}
				}
				setState(71); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==SECTIONOPEN );
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
		public TerminalNode SECTIONOPEN() { return getToken(AutoExamParser.SECTIONOPEN, 0); }
		public TerminalNode TEXT() { return getToken(AutoExamParser.TEXT, 0); }
		public TerminalNode SEPARATOR() { return getToken(AutoExamParser.SEPARATOR, 0); }
		public SectionStructureContext sectionStructure() {
			return getRuleContext(SectionStructureContext.class,0);
		}
		public TerminalNode SECTIONCLOSE() { return getToken(AutoExamParser.SECTIONCLOSE, 0); }
		public SectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_section; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutoExamListener ) ((AutoExamListener)listener).enterSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutoExamListener ) ((AutoExamListener)listener).exitSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutoExamVisitor ) return ((AutoExamVisitor<? extends T>)visitor).visitSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SectionContext section() throws RecognitionException {
		SectionContext _localctx = new SectionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_section);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			match(SECTIONOPEN);
			setState(74);
			match(TEXT);
			setState(75);
			match(SEPARATOR);
			setState(76);
			sectionStructure();
			setState(77);
			match(SECTIONCLOSE);
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
			if ( listener instanceof AutoExamListener ) ((AutoExamListener)listener).enterSectionStructure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutoExamListener ) ((AutoExamListener)listener).exitSectionStructure(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutoExamVisitor ) return ((AutoExamVisitor<? extends T>)visitor).visitSectionStructure(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SectionStructureContext sectionStructure() throws RecognitionException {
		SectionStructureContext _localctx = new SectionStructureContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_sectionStructure);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(79);
				question();
				}
				}
				setState(82); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==INITQUESTION );
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
		public List<TerminalNode> INITQUESTION() { return getTokens(AutoExamParser.INITQUESTION); }
		public TerminalNode INITQUESTION(int i) {
			return getToken(AutoExamParser.INITQUESTION, i);
		}
		public QuestiontypeContext questiontype() {
			return getRuleContext(QuestiontypeContext.class,0);
		}
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutoExamListener ) ((AutoExamListener)listener).enterQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutoExamListener ) ((AutoExamListener)listener).exitQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutoExamVisitor ) return ((AutoExamVisitor<? extends T>)visitor).visitQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_question);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(INITQUESTION);
			setState(85);
			questiontype();
			setState(86);
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
	public static class QuestiontypeContext extends ParserRuleContext {
		public TerminalNode NUM() { return getToken(AutoExamParser.NUM, 0); }
		public TerminalNode SEPARATOR() { return getToken(AutoExamParser.SEPARATOR, 0); }
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
		public QuestiontypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questiontype; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutoExamListener ) ((AutoExamListener)listener).enterQuestiontype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutoExamListener ) ((AutoExamListener)listener).exitQuestiontype(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutoExamVisitor ) return ((AutoExamVisitor<? extends T>)visitor).visitQuestiontype(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestiontypeContext questiontype() throws RecognitionException {
		QuestiontypeContext _localctx = new QuestiontypeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_questiontype);
		try {
			setState(106);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(88);
				match(NUM);
				setState(89);
				match(SEPARATOR);
				setState(90);
				matching();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(91);
				match(NUM);
				setState(92);
				match(SEPARATOR);
				setState(93);
				missingWord();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(94);
				match(NUM);
				setState(95);
				match(SEPARATOR);
				setState(96);
				multipleChoice();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(97);
				match(NUM);
				setState(98);
				match(SEPARATOR);
				setState(99);
				numeric();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(100);
				match(NUM);
				setState(101);
				match(SEPARATOR);
				setState(102);
				short_();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(103);
				match(NUM);
				setState(104);
				match(SEPARATOR);
				setState(105);
				trueOrFalse();
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
	public static class MatchingContext extends ParserRuleContext {
		public MatchingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matching; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutoExamListener ) ((AutoExamListener)listener).enterMatching(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutoExamListener ) ((AutoExamListener)listener).exitMatching(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutoExamVisitor ) return ((AutoExamVisitor<? extends T>)visitor).visitMatching(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchingContext matching() throws RecognitionException {
		MatchingContext _localctx = new MatchingContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_matching);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			match(T__1);
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
		public MissingWordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_missingWord; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutoExamListener ) ((AutoExamListener)listener).enterMissingWord(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutoExamListener ) ((AutoExamListener)listener).exitMissingWord(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutoExamVisitor ) return ((AutoExamVisitor<? extends T>)visitor).visitMissingWord(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MissingWordContext missingWord() throws RecognitionException {
		MissingWordContext _localctx = new MissingWordContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_missingWord);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
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
	public static class MultipleChoiceContext extends ParserRuleContext {
		public MultipleChoiceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multipleChoice; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutoExamListener ) ((AutoExamListener)listener).enterMultipleChoice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutoExamListener ) ((AutoExamListener)listener).exitMultipleChoice(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutoExamVisitor ) return ((AutoExamVisitor<? extends T>)visitor).visitMultipleChoice(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultipleChoiceContext multipleChoice() throws RecognitionException {
		MultipleChoiceContext _localctx = new MultipleChoiceContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_multipleChoice);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			match(T__3);
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
		public NumericContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numeric; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutoExamListener ) ((AutoExamListener)listener).enterNumeric(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutoExamListener ) ((AutoExamListener)listener).exitNumeric(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutoExamVisitor ) return ((AutoExamVisitor<? extends T>)visitor).visitNumeric(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumericContext numeric() throws RecognitionException {
		NumericContext _localctx = new NumericContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_numeric);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
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
	public static class ShortContext extends ParserRuleContext {
		public ShortContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_short; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutoExamListener ) ((AutoExamListener)listener).enterShort(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutoExamListener ) ((AutoExamListener)listener).exitShort(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutoExamVisitor ) return ((AutoExamVisitor<? extends T>)visitor).visitShort(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ShortContext short_() throws RecognitionException {
		ShortContext _localctx = new ShortContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_short);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			match(T__5);
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
		public TrueOrFalseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_trueOrFalse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutoExamListener ) ((AutoExamListener)listener).enterTrueOrFalse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutoExamListener ) ((AutoExamListener)listener).exitTrueOrFalse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutoExamVisitor ) return ((AutoExamVisitor<? extends T>)visitor).visitTrueOrFalse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TrueOrFalseContext trueOrFalse() throws RecognitionException {
		TrueOrFalseContext _localctx = new TrueOrFalseContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_trueOrFalse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			match(T__6);
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
		"\u0004\u0001\u0013y\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\b\u0004\bF\b\b\u000b\b\f\bG\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\n\u0004\nQ\b\n\u000b\n\f\nR\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0003\fk\b\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e"+
		"\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0000\u0000\u0013\u0000\u0002\u0004"+
		"\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \""+
		"$\u0000\u0000l\u0000&\u0001\u0000\u0000\u0000\u00022\u0001\u0000\u0000"+
		"\u0000\u00044\u0001\u0000\u0000\u0000\u00066\u0001\u0000\u0000\u0000\b"+
		"8\u0001\u0000\u0000\u0000\n>\u0001\u0000\u0000\u0000\f@\u0001\u0000\u0000"+
		"\u0000\u000eB\u0001\u0000\u0000\u0000\u0010E\u0001\u0000\u0000\u0000\u0012"+
		"I\u0001\u0000\u0000\u0000\u0014P\u0001\u0000\u0000\u0000\u0016T\u0001"+
		"\u0000\u0000\u0000\u0018j\u0001\u0000\u0000\u0000\u001al\u0001\u0000\u0000"+
		"\u0000\u001cn\u0001\u0000\u0000\u0000\u001ep\u0001\u0000\u0000\u0000 "+
		"r\u0001\u0000\u0000\u0000\"t\u0001\u0000\u0000\u0000$v\u0001\u0000\u0000"+
		"\u0000&\'\u0005\b\u0000\u0000\'(\u0003\u0002\u0001\u0000()\u0005\n\u0000"+
		"\u0000)*\u0003\u0004\u0002\u0000*+\u0005\n\u0000\u0000+,\u0003\u0006\u0003"+
		"\u0000,-\u0005\n\u0000\u0000-.\u0003\b\u0004\u0000./\u0005\n\u0000\u0000"+
		"/0\u0003\u0010\b\u000001\u0005\b\u0000\u00001\u0001\u0001\u0000\u0000"+
		"\u000023\u0005\u000e\u0000\u00003\u0003\u0001\u0000\u0000\u000045\u0005"+
		"\u000e\u0000\u00005\u0005\u0001\u0000\u0000\u000067\u0005\u000e\u0000"+
		"\u00007\u0007\u0001\u0000\u0000\u000089\u0003\n\u0005\u00009:\u0005\n"+
		"\u0000\u0000:;\u0003\f\u0006\u0000;<\u0005\n\u0000\u0000<=\u0003\u000e"+
		"\u0007\u0000=\t\u0001\u0000\u0000\u0000>?\u0005\u000e\u0000\u0000?\u000b"+
		"\u0001\u0000\u0000\u0000@A\u0005\u0001\u0000\u0000A\r\u0001\u0000\u0000"+
		"\u0000BC\u0005\u0001\u0000\u0000C\u000f\u0001\u0000\u0000\u0000DF\u0003"+
		"\u0012\t\u0000ED\u0001\u0000\u0000\u0000FG\u0001\u0000\u0000\u0000GE\u0001"+
		"\u0000\u0000\u0000GH\u0001\u0000\u0000\u0000H\u0011\u0001\u0000\u0000"+
		"\u0000IJ\u0005\u000b\u0000\u0000JK\u0005\u000e\u0000\u0000KL\u0005\n\u0000"+
		"\u0000LM\u0003\u0014\n\u0000MN\u0005\f\u0000\u0000N\u0013\u0001\u0000"+
		"\u0000\u0000OQ\u0003\u0016\u000b\u0000PO\u0001\u0000\u0000\u0000QR\u0001"+
		"\u0000\u0000\u0000RP\u0001\u0000\u0000\u0000RS\u0001\u0000\u0000\u0000"+
		"S\u0015\u0001\u0000\u0000\u0000TU\u0005\t\u0000\u0000UV\u0003\u0018\f"+
		"\u0000VW\u0005\t\u0000\u0000W\u0017\u0001\u0000\u0000\u0000XY\u0005\r"+
		"\u0000\u0000YZ\u0005\n\u0000\u0000Zk\u0003\u001a\r\u0000[\\\u0005\r\u0000"+
		"\u0000\\]\u0005\n\u0000\u0000]k\u0003\u001c\u000e\u0000^_\u0005\r\u0000"+
		"\u0000_`\u0005\n\u0000\u0000`k\u0003\u001e\u000f\u0000ab\u0005\r\u0000"+
		"\u0000bc\u0005\n\u0000\u0000ck\u0003 \u0010\u0000de\u0005\r\u0000\u0000"+
		"ef\u0005\n\u0000\u0000fk\u0003\"\u0011\u0000gh\u0005\r\u0000\u0000hi\u0005"+
		"\n\u0000\u0000ik\u0003$\u0012\u0000jX\u0001\u0000\u0000\u0000j[\u0001"+
		"\u0000\u0000\u0000j^\u0001\u0000\u0000\u0000ja\u0001\u0000\u0000\u0000"+
		"jd\u0001\u0000\u0000\u0000jg\u0001\u0000\u0000\u0000k\u0019\u0001\u0000"+
		"\u0000\u0000lm\u0005\u0002\u0000\u0000m\u001b\u0001\u0000\u0000\u0000"+
		"no\u0005\u0003\u0000\u0000o\u001d\u0001\u0000\u0000\u0000pq\u0005\u0004"+
		"\u0000\u0000q\u001f\u0001\u0000\u0000\u0000rs\u0005\u0005\u0000\u0000"+
		"s!\u0001\u0000\u0000\u0000tu\u0005\u0006\u0000\u0000u#\u0001\u0000\u0000"+
		"\u0000vw\u0005\u0007\u0000\u0000w%\u0001\u0000\u0000\u0000\u0003GRj";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}