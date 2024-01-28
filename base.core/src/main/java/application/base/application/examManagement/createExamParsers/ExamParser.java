// Generated from C:/Users/pedro/OneDrive/Ambiente de Trabalho/universidade/sem4pi-22-23-6/base.core/src/main/java/application/base/application/examManagement/examParsers\Exam.g4 by ANTLR 4.12.0
package application.base.application.examManagement.createExamParsers;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class ExamParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, INIT=14, INITQUESTION=15, SEPARATOR=16, 
		SECTIONOPEN=17, SECTIONCLOSE=18, NUM=19, TEXT=20, SLASH=21, ARROW=22, 
		GAPSPACE=23, QUESTION=24, WS=25;
	public static final int
		RULE_stat = 0, RULE_title = 1, RULE_description = 2, RULE_openDate = 3, 
		RULE_closeDate = 4, RULE_date = 5, RULE_language = 6, RULE_header = 7, 
		RULE_headerDescription = 8, RULE_feedbackType = 9, RULE_gradeType = 10, 
		RULE_structure = 11, RULE_section = 12, RULE_sectionStructure = 13, RULE_question = 14, 
		RULE_questiontype = 15, RULE_quotation = 16, RULE_matching = 17, RULE_matchingText = 18, 
		RULE_matchingOption = 19, RULE_matchingAnswers = 20, RULE_matchingAnswer = 21, 
		RULE_missingWord = 22, RULE_missingWordText = 23, RULE_mwText = 24, RULE_missingWordOptions = 25, 
		RULE_missingWordOption = 26, RULE_missingWordAnswer = 27, RULE_multipleChoice = 28, 
		RULE_multipleChoiceText = 29, RULE_multipleChoiceOption = 30, RULE_multipleChoiceAnswer = 31, 
		RULE_numeric = 32, RULE_numericText = 33, RULE_numericAnswer = 34, RULE_short = 35, 
		RULE_shortText = 36, RULE_shortAnswer = 37, RULE_trueOrFalse = 38, RULE_trueOrFalseText = 39, 
		RULE_trueOrFalseAnswer = 40;
	private static String[] makeRuleNames() {
		return new String[] {
			"stat", "title", "description", "openDate", "closeDate", "date", "language", 
			"header", "headerDescription", "feedbackType", "gradeType", "structure", 
			"section", "sectionStructure", "question", "questiontype", "quotation", 
			"matching", "matchingText", "matchingOption", "matchingAnswers", "matchingAnswer", 
			"missingWord", "missingWordText", "mwText", "missingWordOptions", "missingWordOption", 
			"missingWordAnswer", "multipleChoice", "multipleChoiceText", "multipleChoiceOption", 
			"multipleChoiceAnswer", "numeric", "numericText", "numericAnswer", "short", 
			"shortText", "shortAnswer", "trueOrFalse", "trueOrFalseText", "trueOrFalseAnswer"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'NONE'", "'ON_SUBMISSION'", "'AFTER_CLOSING'", "'MatchingQuestion'", 
			"','", "'MissingWordQuestion'", "'-'", "'MultipleChoiceQuestion'", "'NumericQuestion'", 
			"'ShortQuestion'", "'TrueFalseQuestion'", "'True'", "'False'", "'\"'", 
			"'??'", "';'", "'<<'", "'>>'", null, null, "'/'", "'->'", "'***'", "'?'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "INIT", "INITQUESTION", "SEPARATOR", "SECTIONOPEN", "SECTIONCLOSE", 
			"NUM", "TEXT", "SLASH", "ARROW", "GAPSPACE", "QUESTION", "WS"
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
	public String getGrammarFileName() { return "Exam.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ExamParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatContext extends ParserRuleContext {
		public List<TerminalNode> INIT() { return getTokens(ExamParser.INIT); }
		public TerminalNode INIT(int i) {
			return getToken(ExamParser.INIT, i);
		}
		public TitleContext title() {
			return getRuleContext(TitleContext.class,0);
		}
		public List<TerminalNode> SEPARATOR() { return getTokens(ExamParser.SEPARATOR); }
		public TerminalNode SEPARATOR(int i) {
			return getToken(ExamParser.SEPARATOR, i);
		}
		public DescriptionContext description() {
			return getRuleContext(DescriptionContext.class,0);
		}
		public OpenDateContext openDate() {
			return getRuleContext(OpenDateContext.class,0);
		}
		public CloseDateContext closeDate() {
			return getRuleContext(CloseDateContext.class,0);
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
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_stat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(INIT);
			setState(83);
			title();
			setState(84);
			match(SEPARATOR);
			setState(85);
			description();
			setState(86);
			match(SEPARATOR);
			setState(87);
			openDate();
			setState(88);
			match(SEPARATOR);
			setState(89);
			closeDate();
			setState(90);
			match(SEPARATOR);
			setState(91);
			language();
			setState(92);
			match(SEPARATOR);
			setState(93);
			header();
			setState(94);
			match(SEPARATOR);
			setState(95);
			structure();
			setState(96);
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
		public TerminalNode TEXT() { return getToken(ExamParser.TEXT, 0); }
		public TitleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_title; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterTitle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitTitle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitTitle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TitleContext title() throws RecognitionException {
		TitleContext _localctx = new TitleContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_title);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
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
		public TerminalNode TEXT() { return getToken(ExamParser.TEXT, 0); }
		public DescriptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_description; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterDescription(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitDescription(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitDescription(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DescriptionContext description() throws RecognitionException {
		DescriptionContext _localctx = new DescriptionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_description);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
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
	public static class OpenDateContext extends ParserRuleContext {
		public DateContext date() {
			return getRuleContext(DateContext.class,0);
		}
		public OpenDateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_openDate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterOpenDate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitOpenDate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitOpenDate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OpenDateContext openDate() throws RecognitionException {
		OpenDateContext _localctx = new OpenDateContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_openDate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			date();
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
	public static class CloseDateContext extends ParserRuleContext {
		public DateContext date() {
			return getRuleContext(DateContext.class,0);
		}
		public CloseDateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_closeDate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterCloseDate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitCloseDate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitCloseDate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CloseDateContext closeDate() throws RecognitionException {
		CloseDateContext _localctx = new CloseDateContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_closeDate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			date();
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
	public static class DateContext extends ParserRuleContext {
		public List<TerminalNode> NUM() { return getTokens(ExamParser.NUM); }
		public TerminalNode NUM(int i) {
			return getToken(ExamParser.NUM, i);
		}
		public List<TerminalNode> SLASH() { return getTokens(ExamParser.SLASH); }
		public TerminalNode SLASH(int i) {
			return getToken(ExamParser.SLASH, i);
		}
		public DateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_date; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterDate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitDate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitDate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DateContext date() throws RecognitionException {
		DateContext _localctx = new DateContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_date);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			match(NUM);
			setState(107);
			match(SLASH);
			setState(108);
			match(NUM);
			setState(109);
			match(SLASH);
			setState(110);
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
	public static class LanguageContext extends ParserRuleContext {
		public TerminalNode TEXT() { return getToken(ExamParser.TEXT, 0); }
		public LanguageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_language; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterLanguage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitLanguage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitLanguage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LanguageContext language() throws RecognitionException {
		LanguageContext _localctx = new LanguageContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_language);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
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
		public List<TerminalNode> SEPARATOR() { return getTokens(ExamParser.SEPARATOR); }
		public TerminalNode SEPARATOR(int i) {
			return getToken(ExamParser.SEPARATOR, i);
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
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterHeader(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitHeader(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitHeader(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeaderContext header() throws RecognitionException {
		HeaderContext _localctx = new HeaderContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_header);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			headerDescription();
			setState(115);
			match(SEPARATOR);
			setState(116);
			feedbackType();
			setState(117);
			match(SEPARATOR);
			setState(118);
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
		public TerminalNode TEXT() { return getToken(ExamParser.TEXT, 0); }
		public HeaderDescriptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_headerDescription; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterHeaderDescription(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitHeaderDescription(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitHeaderDescription(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeaderDescriptionContext headerDescription() throws RecognitionException {
		HeaderDescriptionContext _localctx = new HeaderDescriptionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_headerDescription);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
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
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterFeedbackType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitFeedbackType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitFeedbackType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FeedbackTypeContext feedbackType() throws RecognitionException {
		FeedbackTypeContext _localctx = new FeedbackTypeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_feedbackType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 14L) != 0)) ) {
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

	@SuppressWarnings("CheckReturnValue")
	public static class GradeTypeContext extends ParserRuleContext {
		public GradeTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gradeType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterGradeType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitGradeType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitGradeType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GradeTypeContext gradeType() throws RecognitionException {
		GradeTypeContext _localctx = new GradeTypeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_gradeType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 14L) != 0)) ) {
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
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterStructure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitStructure(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitStructure(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StructureContext structure() throws RecognitionException {
		StructureContext _localctx = new StructureContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_structure);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(126);
				section();
				}
				}
				setState(129); 
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
		public TerminalNode SECTIONOPEN() { return getToken(ExamParser.SECTIONOPEN, 0); }
		public TerminalNode TEXT() { return getToken(ExamParser.TEXT, 0); }
		public TerminalNode SEPARATOR() { return getToken(ExamParser.SEPARATOR, 0); }
		public SectionStructureContext sectionStructure() {
			return getRuleContext(SectionStructureContext.class,0);
		}
		public TerminalNode SECTIONCLOSE() { return getToken(ExamParser.SECTIONCLOSE, 0); }
		public SectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_section; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SectionContext section() throws RecognitionException {
		SectionContext _localctx = new SectionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_section);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			match(SECTIONOPEN);
			setState(132);
			match(TEXT);
			setState(133);
			match(SEPARATOR);
			setState(134);
			sectionStructure();
			setState(135);
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
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterSectionStructure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitSectionStructure(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitSectionStructure(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SectionStructureContext sectionStructure() throws RecognitionException {
		SectionStructureContext _localctx = new SectionStructureContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_sectionStructure);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(137);
				question();
				}
				}
				setState(140); 
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
		public List<TerminalNode> INITQUESTION() { return getTokens(ExamParser.INITQUESTION); }
		public TerminalNode INITQUESTION(int i) {
			return getToken(ExamParser.INITQUESTION, i);
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
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_question);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			match(INITQUESTION);
			setState(143);
			questiontype();
			setState(144);
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
		public TerminalNode NUM() { return getToken(ExamParser.NUM, 0); }
		public TerminalNode SEPARATOR() { return getToken(ExamParser.SEPARATOR, 0); }
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
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterQuestiontype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitQuestiontype(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitQuestiontype(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestiontypeContext questiontype() throws RecognitionException {
		QuestiontypeContext _localctx = new QuestiontypeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_questiontype);
		try {
			setState(164);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(146);
				match(NUM);
				setState(147);
				match(SEPARATOR);
				setState(148);
				matching();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(149);
				match(NUM);
				setState(150);
				match(SEPARATOR);
				setState(151);
				missingWord();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(152);
				match(NUM);
				setState(153);
				match(SEPARATOR);
				setState(154);
				multipleChoice();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(155);
				match(NUM);
				setState(156);
				match(SEPARATOR);
				setState(157);
				numeric();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(158);
				match(NUM);
				setState(159);
				match(SEPARATOR);
				setState(160);
				short_();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(161);
				match(NUM);
				setState(162);
				match(SEPARATOR);
				setState(163);
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
	public static class QuotationContext extends ParserRuleContext {
		public TerminalNode NUM() { return getToken(ExamParser.NUM, 0); }
		public QuotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quotation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterQuotation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitQuotation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitQuotation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuotationContext quotation() throws RecognitionException {
		QuotationContext _localctx = new QuotationContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_quotation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
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
	public static class MatchingContext extends ParserRuleContext {
		public List<TerminalNode> SEPARATOR() { return getTokens(ExamParser.SEPARATOR); }
		public TerminalNode SEPARATOR(int i) {
			return getToken(ExamParser.SEPARATOR, i);
		}
		public MatchingTextContext matchingText() {
			return getRuleContext(MatchingTextContext.class,0);
		}
		public MatchingAnswersContext matchingAnswers() {
			return getRuleContext(MatchingAnswersContext.class,0);
		}
		public QuotationContext quotation() {
			return getRuleContext(QuotationContext.class,0);
		}
		public MatchingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matching; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterMatching(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitMatching(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitMatching(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchingContext matching() throws RecognitionException {
		MatchingContext _localctx = new MatchingContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_matching);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			match(T__3);
			setState(169);
			match(SEPARATOR);
			setState(170);
			matchingText();
			setState(171);
			match(SEPARATOR);
			setState(172);
			matchingAnswers();
			setState(173);
			match(SEPARATOR);
			setState(174);
			quotation();
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
		public TerminalNode TEXT() { return getToken(ExamParser.TEXT, 0); }
		public List<TerminalNode> SEPARATOR() { return getTokens(ExamParser.SEPARATOR); }
		public TerminalNode SEPARATOR(int i) {
			return getToken(ExamParser.SEPARATOR, i);
		}
		public List<MatchingOptionContext> matchingOption() {
			return getRuleContexts(MatchingOptionContext.class);
		}
		public MatchingOptionContext matchingOption(int i) {
			return getRuleContext(MatchingOptionContext.class,i);
		}
		public MatchingTextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matchingText; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterMatchingText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitMatchingText(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitMatchingText(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchingTextContext matchingText() throws RecognitionException {
		MatchingTextContext _localctx = new MatchingTextContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_matchingText);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			match(TEXT);
			setState(177);
			match(SEPARATOR);
			setState(178);
			matchingOption();
			setState(179);
			match(SEPARATOR);
			setState(180);
			matchingOption();
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
	public static class MatchingOptionContext extends ParserRuleContext {
		public TerminalNode TEXT() { return getToken(ExamParser.TEXT, 0); }
		public MatchingOptionContext matchingOption() {
			return getRuleContext(MatchingOptionContext.class,0);
		}
		public MatchingOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matchingOption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterMatchingOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitMatchingOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitMatchingOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchingOptionContext matchingOption() throws RecognitionException {
		MatchingOptionContext _localctx = new MatchingOptionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_matchingOption);
		try {
			setState(186);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(182);
				match(TEXT);
				setState(183);
				match(T__4);
				setState(184);
				matchingOption();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(185);
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
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterMatchingAnswers(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitMatchingAnswers(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitMatchingAnswers(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchingAnswersContext matchingAnswers() throws RecognitionException {
		MatchingAnswersContext _localctx = new MatchingAnswersContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_matchingAnswers);
		try {
			setState(193);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(188);
				matchingAnswer();
				setState(189);
				match(T__4);
				setState(190);
				matchingAnswer();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(192);
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
		public List<TerminalNode> TEXT() { return getTokens(ExamParser.TEXT); }
		public TerminalNode TEXT(int i) {
			return getToken(ExamParser.TEXT, i);
		}
		public TerminalNode ARROW() { return getToken(ExamParser.ARROW, 0); }
		public MatchingAnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matchingAnswer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterMatchingAnswer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitMatchingAnswer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitMatchingAnswer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchingAnswerContext matchingAnswer() throws RecognitionException {
		MatchingAnswerContext _localctx = new MatchingAnswerContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_matchingAnswer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(195);
			match(TEXT);
			setState(196);
			match(ARROW);
			setState(197);
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
		public List<TerminalNode> SEPARATOR() { return getTokens(ExamParser.SEPARATOR); }
		public TerminalNode SEPARATOR(int i) {
			return getToken(ExamParser.SEPARATOR, i);
		}
		public MissingWordTextContext missingWordText() {
			return getRuleContext(MissingWordTextContext.class,0);
		}
		public MissingWordAnswerContext missingWordAnswer() {
			return getRuleContext(MissingWordAnswerContext.class,0);
		}
		public QuotationContext quotation() {
			return getRuleContext(QuotationContext.class,0);
		}
		public MissingWordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_missingWord; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterMissingWord(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitMissingWord(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitMissingWord(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MissingWordContext missingWord() throws RecognitionException {
		MissingWordContext _localctx = new MissingWordContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_missingWord);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			match(T__5);
			setState(200);
			match(SEPARATOR);
			setState(201);
			missingWordText();
			setState(202);
			match(SEPARATOR);
			setState(203);
			missingWordAnswer();
			setState(204);
			match(SEPARATOR);
			setState(205);
			quotation();
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
		public TerminalNode SEPARATOR() { return getToken(ExamParser.SEPARATOR, 0); }
		public MissingWordOptionsContext missingWordOptions() {
			return getRuleContext(MissingWordOptionsContext.class,0);
		}
		public MissingWordTextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_missingWordText; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterMissingWordText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitMissingWordText(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitMissingWordText(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MissingWordTextContext missingWordText() throws RecognitionException {
		MissingWordTextContext _localctx = new MissingWordTextContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_missingWordText);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
			mwText();
			setState(208);
			match(SEPARATOR);
			setState(209);
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
		public TerminalNode GAPSPACE() { return getToken(ExamParser.GAPSPACE, 0); }
		public List<TerminalNode> TEXT() { return getTokens(ExamParser.TEXT); }
		public TerminalNode TEXT(int i) {
			return getToken(ExamParser.TEXT, i);
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
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterMwText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitMwText(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitMwText(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MwTextContext mwText() throws RecognitionException {
		MwTextContext _localctx = new MwTextContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_mwText);
		try {
			setState(224);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(211);
				match(GAPSPACE);
				setState(212);
				match(TEXT);
				setState(213);
				mwText();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(214);
				match(TEXT);
				setState(215);
				match(GAPSPACE);
				setState(216);
				mwText();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(217);
				match(TEXT);
				setState(218);
				match(GAPSPACE);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(219);
				match(GAPSPACE);
				setState(220);
				match(TEXT);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(221);
				match(TEXT);
				setState(222);
				match(GAPSPACE);
				setState(223);
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
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterMissingWordOptions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitMissingWordOptions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitMissingWordOptions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MissingWordOptionsContext missingWordOptions() throws RecognitionException {
		MissingWordOptionsContext _localctx = new MissingWordOptionsContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_missingWordOptions);
		try {
			setState(231);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(226);
				missingWordOption();
				setState(227);
				match(T__4);
				setState(228);
				missingWordOption();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(230);
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
		public TerminalNode TEXT() { return getToken(ExamParser.TEXT, 0); }
		public MissingWordOptionContext missingWordOption() {
			return getRuleContext(MissingWordOptionContext.class,0);
		}
		public MissingWordOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_missingWordOption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterMissingWordOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitMissingWordOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitMissingWordOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MissingWordOptionContext missingWordOption() throws RecognitionException {
		MissingWordOptionContext _localctx = new MissingWordOptionContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_missingWordOption);
		try {
			setState(237);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(233);
				match(TEXT);
				setState(234);
				match(T__6);
				setState(235);
				missingWordOption();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(236);
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
		public TerminalNode TEXT() { return getToken(ExamParser.TEXT, 0); }
		public MissingWordAnswerContext missingWordAnswer() {
			return getRuleContext(MissingWordAnswerContext.class,0);
		}
		public MissingWordAnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_missingWordAnswer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterMissingWordAnswer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitMissingWordAnswer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitMissingWordAnswer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MissingWordAnswerContext missingWordAnswer() throws RecognitionException {
		MissingWordAnswerContext _localctx = new MissingWordAnswerContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_missingWordAnswer);
		try {
			setState(243);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(239);
				match(TEXT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(240);
				match(TEXT);
				setState(241);
				match(T__4);
				setState(242);
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
		public List<TerminalNode> SEPARATOR() { return getTokens(ExamParser.SEPARATOR); }
		public TerminalNode SEPARATOR(int i) {
			return getToken(ExamParser.SEPARATOR, i);
		}
		public MultipleChoiceTextContext multipleChoiceText() {
			return getRuleContext(MultipleChoiceTextContext.class,0);
		}
		public MultipleChoiceAnswerContext multipleChoiceAnswer() {
			return getRuleContext(MultipleChoiceAnswerContext.class,0);
		}
		public QuotationContext quotation() {
			return getRuleContext(QuotationContext.class,0);
		}
		public MultipleChoiceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multipleChoice; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterMultipleChoice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitMultipleChoice(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitMultipleChoice(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultipleChoiceContext multipleChoice() throws RecognitionException {
		MultipleChoiceContext _localctx = new MultipleChoiceContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_multipleChoice);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
			match(T__7);
			setState(246);
			match(SEPARATOR);
			setState(247);
			multipleChoiceText();
			setState(248);
			match(SEPARATOR);
			setState(249);
			multipleChoiceAnswer();
			setState(250);
			match(SEPARATOR);
			setState(251);
			quotation();
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
		public TerminalNode TEXT() { return getToken(ExamParser.TEXT, 0); }
		public TerminalNode SEPARATOR() { return getToken(ExamParser.SEPARATOR, 0); }
		public MultipleChoiceOptionContext multipleChoiceOption() {
			return getRuleContext(MultipleChoiceOptionContext.class,0);
		}
		public TerminalNode QUESTION() { return getToken(ExamParser.QUESTION, 0); }
		public MultipleChoiceTextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multipleChoiceText; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterMultipleChoiceText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitMultipleChoiceText(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitMultipleChoiceText(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultipleChoiceTextContext multipleChoiceText() throws RecognitionException {
		MultipleChoiceTextContext _localctx = new MultipleChoiceTextContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_multipleChoiceText);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(253);
			match(TEXT);
			setState(255);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==QUESTION) {
				{
				setState(254);
				match(QUESTION);
				}
			}

			setState(257);
			match(SEPARATOR);
			setState(258);
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
		public TerminalNode TEXT() { return getToken(ExamParser.TEXT, 0); }
		public MultipleChoiceOptionContext multipleChoiceOption() {
			return getRuleContext(MultipleChoiceOptionContext.class,0);
		}
		public MultipleChoiceOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multipleChoiceOption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterMultipleChoiceOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitMultipleChoiceOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitMultipleChoiceOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultipleChoiceOptionContext multipleChoiceOption() throws RecognitionException {
		MultipleChoiceOptionContext _localctx = new MultipleChoiceOptionContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_multipleChoiceOption);
		try {
			setState(264);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(260);
				match(TEXT);
				setState(261);
				match(T__4);
				setState(262);
				multipleChoiceOption();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(263);
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
		public TerminalNode TEXT() { return getToken(ExamParser.TEXT, 0); }
		public MultipleChoiceAnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multipleChoiceAnswer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterMultipleChoiceAnswer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitMultipleChoiceAnswer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitMultipleChoiceAnswer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultipleChoiceAnswerContext multipleChoiceAnswer() throws RecognitionException {
		MultipleChoiceAnswerContext _localctx = new MultipleChoiceAnswerContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_multipleChoiceAnswer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(266);
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
	public static class NumericContext extends ParserRuleContext {
		public List<TerminalNode> SEPARATOR() { return getTokens(ExamParser.SEPARATOR); }
		public TerminalNode SEPARATOR(int i) {
			return getToken(ExamParser.SEPARATOR, i);
		}
		public NumericTextContext numericText() {
			return getRuleContext(NumericTextContext.class,0);
		}
		public NumericAnswerContext numericAnswer() {
			return getRuleContext(NumericAnswerContext.class,0);
		}
		public QuotationContext quotation() {
			return getRuleContext(QuotationContext.class,0);
		}
		public NumericContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numeric; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterNumeric(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitNumeric(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitNumeric(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumericContext numeric() throws RecognitionException {
		NumericContext _localctx = new NumericContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_numeric);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(268);
			match(T__8);
			setState(269);
			match(SEPARATOR);
			setState(270);
			numericText();
			setState(271);
			match(SEPARATOR);
			setState(272);
			numericAnswer();
			setState(273);
			match(SEPARATOR);
			setState(274);
			quotation();
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
		public TerminalNode TEXT() { return getToken(ExamParser.TEXT, 0); }
		public TerminalNode QUESTION() { return getToken(ExamParser.QUESTION, 0); }
		public NumericTextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numericText; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterNumericText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitNumericText(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitNumericText(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumericTextContext numericText() throws RecognitionException {
		NumericTextContext _localctx = new NumericTextContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_numericText);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276);
			match(TEXT);
			setState(278);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==QUESTION) {
				{
				setState(277);
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
		public TerminalNode NUM() { return getToken(ExamParser.NUM, 0); }
		public NumericAnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numericAnswer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterNumericAnswer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitNumericAnswer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitNumericAnswer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumericAnswerContext numericAnswer() throws RecognitionException {
		NumericAnswerContext _localctx = new NumericAnswerContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_numericAnswer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280);
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
		public List<TerminalNode> SEPARATOR() { return getTokens(ExamParser.SEPARATOR); }
		public TerminalNode SEPARATOR(int i) {
			return getToken(ExamParser.SEPARATOR, i);
		}
		public ShortTextContext shortText() {
			return getRuleContext(ShortTextContext.class,0);
		}
		public ShortAnswerContext shortAnswer() {
			return getRuleContext(ShortAnswerContext.class,0);
		}
		public QuotationContext quotation() {
			return getRuleContext(QuotationContext.class,0);
		}
		public ShortContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_short; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterShort(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitShort(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitShort(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ShortContext short_() throws RecognitionException {
		ShortContext _localctx = new ShortContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_short);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282);
			match(T__9);
			setState(283);
			match(SEPARATOR);
			setState(284);
			shortText();
			setState(285);
			match(SEPARATOR);
			setState(286);
			shortAnswer();
			setState(287);
			match(SEPARATOR);
			setState(288);
			quotation();
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
		public TerminalNode TEXT() { return getToken(ExamParser.TEXT, 0); }
		public TerminalNode QUESTION() { return getToken(ExamParser.QUESTION, 0); }
		public ShortTextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shortText; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterShortText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitShortText(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitShortText(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ShortTextContext shortText() throws RecognitionException {
		ShortTextContext _localctx = new ShortTextContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_shortText);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(290);
			match(TEXT);
			setState(292);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==QUESTION) {
				{
				setState(291);
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
		public TerminalNode TEXT() { return getToken(ExamParser.TEXT, 0); }
		public ShortAnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shortAnswer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterShortAnswer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitShortAnswer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitShortAnswer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ShortAnswerContext shortAnswer() throws RecognitionException {
		ShortAnswerContext _localctx = new ShortAnswerContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_shortAnswer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(294);
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
		public List<TerminalNode> SEPARATOR() { return getTokens(ExamParser.SEPARATOR); }
		public TerminalNode SEPARATOR(int i) {
			return getToken(ExamParser.SEPARATOR, i);
		}
		public TrueOrFalseTextContext trueOrFalseText() {
			return getRuleContext(TrueOrFalseTextContext.class,0);
		}
		public TrueOrFalseAnswerContext trueOrFalseAnswer() {
			return getRuleContext(TrueOrFalseAnswerContext.class,0);
		}
		public QuotationContext quotation() {
			return getRuleContext(QuotationContext.class,0);
		}
		public TrueOrFalseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_trueOrFalse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterTrueOrFalse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitTrueOrFalse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitTrueOrFalse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TrueOrFalseContext trueOrFalse() throws RecognitionException {
		TrueOrFalseContext _localctx = new TrueOrFalseContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_trueOrFalse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(296);
			match(T__10);
			setState(297);
			match(SEPARATOR);
			setState(298);
			trueOrFalseText();
			setState(299);
			match(SEPARATOR);
			setState(300);
			trueOrFalseAnswer();
			setState(301);
			match(SEPARATOR);
			setState(302);
			quotation();
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
		public TerminalNode TEXT() { return getToken(ExamParser.TEXT, 0); }
		public TerminalNode QUESTION() { return getToken(ExamParser.QUESTION, 0); }
		public TrueOrFalseTextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_trueOrFalseText; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterTrueOrFalseText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitTrueOrFalseText(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitTrueOrFalseText(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TrueOrFalseTextContext trueOrFalseText() throws RecognitionException {
		TrueOrFalseTextContext _localctx = new TrueOrFalseTextContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_trueOrFalseText);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(304);
			match(TEXT);
			setState(306);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==QUESTION) {
				{
				setState(305);
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
	public static class TrueOrFalseAnswerContext extends ParserRuleContext {
		public TrueOrFalseAnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_trueOrFalseAnswer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterTrueOrFalseAnswer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitTrueOrFalseAnswer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitTrueOrFalseAnswer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TrueOrFalseAnswerContext trueOrFalseAnswer() throws RecognitionException {
		TrueOrFalseAnswerContext _localctx = new TrueOrFalseAnswerContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_trueOrFalseAnswer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(308);
			_la = _input.LA(1);
			if ( !(_la==T__11 || _la==T__12) ) {
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
		"\u0004\u0001\u0019\u0137\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007"+
		"\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007"+
		"\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007"+
		"\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007"+
		"\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007"+
		"\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007"+
		"\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007"+
		"\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007"+
		"\'\u0002(\u0007(\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\t\u0001"+
		"\t\u0001\n\u0001\n\u0001\u000b\u0004\u000b\u0080\b\u000b\u000b\u000b\f"+
		"\u000b\u0081\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0004"+
		"\r\u008b\b\r\u000b\r\f\r\u008c\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0003\u000f\u00a5\b\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u00bb"+
		"\b\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0003"+
		"\u0014\u00c2\b\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0003\u0018\u00e1\b\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0003\u0019\u00e8\b\u0019\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0001\u001a\u0003\u001a\u00ee\b\u001a\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0003\u001b\u00f4\b\u001b\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001d\u0001\u001d\u0003\u001d\u0100\b\u001d\u0001\u001d\u0001\u001d\u0001"+
		"\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0003\u001e\u0109"+
		"\b\u001e\u0001\u001f\u0001\u001f\u0001 \u0001 \u0001 \u0001 \u0001 \u0001"+
		" \u0001 \u0001 \u0001!\u0001!\u0003!\u0117\b!\u0001\"\u0001\"\u0001#\u0001"+
		"#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001$\u0001$\u0003$\u0125"+
		"\b$\u0001%\u0001%\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001"+
		"&\u0001\'\u0001\'\u0003\'\u0133\b\'\u0001(\u0001(\u0001(\u0000\u0000)"+
		"\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a"+
		"\u001c\u001e \"$&(*,.02468:<>@BDFHJLNP\u0000\u0002\u0001\u0000\u0001\u0003"+
		"\u0001\u0000\f\r\u0122\u0000R\u0001\u0000\u0000\u0000\u0002b\u0001\u0000"+
		"\u0000\u0000\u0004d\u0001\u0000\u0000\u0000\u0006f\u0001\u0000\u0000\u0000"+
		"\bh\u0001\u0000\u0000\u0000\nj\u0001\u0000\u0000\u0000\fp\u0001\u0000"+
		"\u0000\u0000\u000er\u0001\u0000\u0000\u0000\u0010x\u0001\u0000\u0000\u0000"+
		"\u0012z\u0001\u0000\u0000\u0000\u0014|\u0001\u0000\u0000\u0000\u0016\u007f"+
		"\u0001\u0000\u0000\u0000\u0018\u0083\u0001\u0000\u0000\u0000\u001a\u008a"+
		"\u0001\u0000\u0000\u0000\u001c\u008e\u0001\u0000\u0000\u0000\u001e\u00a4"+
		"\u0001\u0000\u0000\u0000 \u00a6\u0001\u0000\u0000\u0000\"\u00a8\u0001"+
		"\u0000\u0000\u0000$\u00b0\u0001\u0000\u0000\u0000&\u00ba\u0001\u0000\u0000"+
		"\u0000(\u00c1\u0001\u0000\u0000\u0000*\u00c3\u0001\u0000\u0000\u0000,"+
		"\u00c7\u0001\u0000\u0000\u0000.\u00cf\u0001\u0000\u0000\u00000\u00e0\u0001"+
		"\u0000\u0000\u00002\u00e7\u0001\u0000\u0000\u00004\u00ed\u0001\u0000\u0000"+
		"\u00006\u00f3\u0001\u0000\u0000\u00008\u00f5\u0001\u0000\u0000\u0000:"+
		"\u00fd\u0001\u0000\u0000\u0000<\u0108\u0001\u0000\u0000\u0000>\u010a\u0001"+
		"\u0000\u0000\u0000@\u010c\u0001\u0000\u0000\u0000B\u0114\u0001\u0000\u0000"+
		"\u0000D\u0118\u0001\u0000\u0000\u0000F\u011a\u0001\u0000\u0000\u0000H"+
		"\u0122\u0001\u0000\u0000\u0000J\u0126\u0001\u0000\u0000\u0000L\u0128\u0001"+
		"\u0000\u0000\u0000N\u0130\u0001\u0000\u0000\u0000P\u0134\u0001\u0000\u0000"+
		"\u0000RS\u0005\u000e\u0000\u0000ST\u0003\u0002\u0001\u0000TU\u0005\u0010"+
		"\u0000\u0000UV\u0003\u0004\u0002\u0000VW\u0005\u0010\u0000\u0000WX\u0003"+
		"\u0006\u0003\u0000XY\u0005\u0010\u0000\u0000YZ\u0003\b\u0004\u0000Z[\u0005"+
		"\u0010\u0000\u0000[\\\u0003\f\u0006\u0000\\]\u0005\u0010\u0000\u0000]"+
		"^\u0003\u000e\u0007\u0000^_\u0005\u0010\u0000\u0000_`\u0003\u0016\u000b"+
		"\u0000`a\u0005\u000e\u0000\u0000a\u0001\u0001\u0000\u0000\u0000bc\u0005"+
		"\u0014\u0000\u0000c\u0003\u0001\u0000\u0000\u0000de\u0005\u0014\u0000"+
		"\u0000e\u0005\u0001\u0000\u0000\u0000fg\u0003\n\u0005\u0000g\u0007\u0001"+
		"\u0000\u0000\u0000hi\u0003\n\u0005\u0000i\t\u0001\u0000\u0000\u0000jk"+
		"\u0005\u0013\u0000\u0000kl\u0005\u0015\u0000\u0000lm\u0005\u0013\u0000"+
		"\u0000mn\u0005\u0015\u0000\u0000no\u0005\u0013\u0000\u0000o\u000b\u0001"+
		"\u0000\u0000\u0000pq\u0005\u0014\u0000\u0000q\r\u0001\u0000\u0000\u0000"+
		"rs\u0003\u0010\b\u0000st\u0005\u0010\u0000\u0000tu\u0003\u0012\t\u0000"+
		"uv\u0005\u0010\u0000\u0000vw\u0003\u0014\n\u0000w\u000f\u0001\u0000\u0000"+
		"\u0000xy\u0005\u0014\u0000\u0000y\u0011\u0001\u0000\u0000\u0000z{\u0007"+
		"\u0000\u0000\u0000{\u0013\u0001\u0000\u0000\u0000|}\u0007\u0000\u0000"+
		"\u0000}\u0015\u0001\u0000\u0000\u0000~\u0080\u0003\u0018\f\u0000\u007f"+
		"~\u0001\u0000\u0000\u0000\u0080\u0081\u0001\u0000\u0000\u0000\u0081\u007f"+
		"\u0001\u0000\u0000\u0000\u0081\u0082\u0001\u0000\u0000\u0000\u0082\u0017"+
		"\u0001\u0000\u0000\u0000\u0083\u0084\u0005\u0011\u0000\u0000\u0084\u0085"+
		"\u0005\u0014\u0000\u0000\u0085\u0086\u0005\u0010\u0000\u0000\u0086\u0087"+
		"\u0003\u001a\r\u0000\u0087\u0088\u0005\u0012\u0000\u0000\u0088\u0019\u0001"+
		"\u0000\u0000\u0000\u0089\u008b\u0003\u001c\u000e\u0000\u008a\u0089\u0001"+
		"\u0000\u0000\u0000\u008b\u008c\u0001\u0000\u0000\u0000\u008c\u008a\u0001"+
		"\u0000\u0000\u0000\u008c\u008d\u0001\u0000\u0000\u0000\u008d\u001b\u0001"+
		"\u0000\u0000\u0000\u008e\u008f\u0005\u000f\u0000\u0000\u008f\u0090\u0003"+
		"\u001e\u000f\u0000\u0090\u0091\u0005\u000f\u0000\u0000\u0091\u001d\u0001"+
		"\u0000\u0000\u0000\u0092\u0093\u0005\u0013\u0000\u0000\u0093\u0094\u0005"+
		"\u0010\u0000\u0000\u0094\u00a5\u0003\"\u0011\u0000\u0095\u0096\u0005\u0013"+
		"\u0000\u0000\u0096\u0097\u0005\u0010\u0000\u0000\u0097\u00a5\u0003,\u0016"+
		"\u0000\u0098\u0099\u0005\u0013\u0000\u0000\u0099\u009a\u0005\u0010\u0000"+
		"\u0000\u009a\u00a5\u00038\u001c\u0000\u009b\u009c\u0005\u0013\u0000\u0000"+
		"\u009c\u009d\u0005\u0010\u0000\u0000\u009d\u00a5\u0003@ \u0000\u009e\u009f"+
		"\u0005\u0013\u0000\u0000\u009f\u00a0\u0005\u0010\u0000\u0000\u00a0\u00a5"+
		"\u0003F#\u0000\u00a1\u00a2\u0005\u0013\u0000\u0000\u00a2\u00a3\u0005\u0010"+
		"\u0000\u0000\u00a3\u00a5\u0003L&\u0000\u00a4\u0092\u0001\u0000\u0000\u0000"+
		"\u00a4\u0095\u0001\u0000\u0000\u0000\u00a4\u0098\u0001\u0000\u0000\u0000"+
		"\u00a4\u009b\u0001\u0000\u0000\u0000\u00a4\u009e\u0001\u0000\u0000\u0000"+
		"\u00a4\u00a1\u0001\u0000\u0000\u0000\u00a5\u001f\u0001\u0000\u0000\u0000"+
		"\u00a6\u00a7\u0005\u0013\u0000\u0000\u00a7!\u0001\u0000\u0000\u0000\u00a8"+
		"\u00a9\u0005\u0004\u0000\u0000\u00a9\u00aa\u0005\u0010\u0000\u0000\u00aa"+
		"\u00ab\u0003$\u0012\u0000\u00ab\u00ac\u0005\u0010\u0000\u0000\u00ac\u00ad"+
		"\u0003(\u0014\u0000\u00ad\u00ae\u0005\u0010\u0000\u0000\u00ae\u00af\u0003"+
		" \u0010\u0000\u00af#\u0001\u0000\u0000\u0000\u00b0\u00b1\u0005\u0014\u0000"+
		"\u0000\u00b1\u00b2\u0005\u0010\u0000\u0000\u00b2\u00b3\u0003&\u0013\u0000"+
		"\u00b3\u00b4\u0005\u0010\u0000\u0000\u00b4\u00b5\u0003&\u0013\u0000\u00b5"+
		"%\u0001\u0000\u0000\u0000\u00b6\u00b7\u0005\u0014\u0000\u0000\u00b7\u00b8"+
		"\u0005\u0005\u0000\u0000\u00b8\u00bb\u0003&\u0013\u0000\u00b9\u00bb\u0005"+
		"\u0014\u0000\u0000\u00ba\u00b6\u0001\u0000\u0000\u0000\u00ba\u00b9\u0001"+
		"\u0000\u0000\u0000\u00bb\'\u0001\u0000\u0000\u0000\u00bc\u00bd\u0003*"+
		"\u0015\u0000\u00bd\u00be\u0005\u0005\u0000\u0000\u00be\u00bf\u0003*\u0015"+
		"\u0000\u00bf\u00c2\u0001\u0000\u0000\u0000\u00c0\u00c2\u0003*\u0015\u0000"+
		"\u00c1\u00bc\u0001\u0000\u0000\u0000\u00c1\u00c0\u0001\u0000\u0000\u0000"+
		"\u00c2)\u0001\u0000\u0000\u0000\u00c3\u00c4\u0005\u0014\u0000\u0000\u00c4"+
		"\u00c5\u0005\u0016\u0000\u0000\u00c5\u00c6\u0005\u0014\u0000\u0000\u00c6"+
		"+\u0001\u0000\u0000\u0000\u00c7\u00c8\u0005\u0006\u0000\u0000\u00c8\u00c9"+
		"\u0005\u0010\u0000\u0000\u00c9\u00ca\u0003.\u0017\u0000\u00ca\u00cb\u0005"+
		"\u0010\u0000\u0000\u00cb\u00cc\u00036\u001b\u0000\u00cc\u00cd\u0005\u0010"+
		"\u0000\u0000\u00cd\u00ce\u0003 \u0010\u0000\u00ce-\u0001\u0000\u0000\u0000"+
		"\u00cf\u00d0\u00030\u0018\u0000\u00d0\u00d1\u0005\u0010\u0000\u0000\u00d1"+
		"\u00d2\u00032\u0019\u0000\u00d2/\u0001\u0000\u0000\u0000\u00d3\u00d4\u0005"+
		"\u0017\u0000\u0000\u00d4\u00d5\u0005\u0014\u0000\u0000\u00d5\u00e1\u0003"+
		"0\u0018\u0000\u00d6\u00d7\u0005\u0014\u0000\u0000\u00d7\u00d8\u0005\u0017"+
		"\u0000\u0000\u00d8\u00e1\u00030\u0018\u0000\u00d9\u00da\u0005\u0014\u0000"+
		"\u0000\u00da\u00e1\u0005\u0017\u0000\u0000\u00db\u00dc\u0005\u0017\u0000"+
		"\u0000\u00dc\u00e1\u0005\u0014\u0000\u0000\u00dd\u00de\u0005\u0014\u0000"+
		"\u0000\u00de\u00df\u0005\u0017\u0000\u0000\u00df\u00e1\u0005\u0014\u0000"+
		"\u0000\u00e0\u00d3\u0001\u0000\u0000\u0000\u00e0\u00d6\u0001\u0000\u0000"+
		"\u0000\u00e0\u00d9\u0001\u0000\u0000\u0000\u00e0\u00db\u0001\u0000\u0000"+
		"\u0000\u00e0\u00dd\u0001\u0000\u0000\u0000\u00e11\u0001\u0000\u0000\u0000"+
		"\u00e2\u00e3\u00034\u001a\u0000\u00e3\u00e4\u0005\u0005\u0000\u0000\u00e4"+
		"\u00e5\u00034\u001a\u0000\u00e5\u00e8\u0001\u0000\u0000\u0000\u00e6\u00e8"+
		"\u00034\u001a\u0000\u00e7\u00e2\u0001\u0000\u0000\u0000\u00e7\u00e6\u0001"+
		"\u0000\u0000\u0000\u00e83\u0001\u0000\u0000\u0000\u00e9\u00ea\u0005\u0014"+
		"\u0000\u0000\u00ea\u00eb\u0005\u0007\u0000\u0000\u00eb\u00ee\u00034\u001a"+
		"\u0000\u00ec\u00ee\u0005\u0014\u0000\u0000\u00ed\u00e9\u0001\u0000\u0000"+
		"\u0000\u00ed\u00ec\u0001\u0000\u0000\u0000\u00ee5\u0001\u0000\u0000\u0000"+
		"\u00ef\u00f4\u0005\u0014\u0000\u0000\u00f0\u00f1\u0005\u0014\u0000\u0000"+
		"\u00f1\u00f2\u0005\u0005\u0000\u0000\u00f2\u00f4\u00036\u001b\u0000\u00f3"+
		"\u00ef\u0001\u0000\u0000\u0000\u00f3\u00f0\u0001\u0000\u0000\u0000\u00f4"+
		"7\u0001\u0000\u0000\u0000\u00f5\u00f6\u0005\b\u0000\u0000\u00f6\u00f7"+
		"\u0005\u0010\u0000\u0000\u00f7\u00f8\u0003:\u001d\u0000\u00f8\u00f9\u0005"+
		"\u0010\u0000\u0000\u00f9\u00fa\u0003>\u001f\u0000\u00fa\u00fb\u0005\u0010"+
		"\u0000\u0000\u00fb\u00fc\u0003 \u0010\u0000\u00fc9\u0001\u0000\u0000\u0000"+
		"\u00fd\u00ff\u0005\u0014\u0000\u0000\u00fe\u0100\u0005\u0018\u0000\u0000"+
		"\u00ff\u00fe\u0001\u0000\u0000\u0000\u00ff\u0100\u0001\u0000\u0000\u0000"+
		"\u0100\u0101\u0001\u0000\u0000\u0000\u0101\u0102\u0005\u0010\u0000\u0000"+
		"\u0102\u0103\u0003<\u001e\u0000\u0103;\u0001\u0000\u0000\u0000\u0104\u0105"+
		"\u0005\u0014\u0000\u0000\u0105\u0106\u0005\u0005\u0000\u0000\u0106\u0109"+
		"\u0003<\u001e\u0000\u0107\u0109\u0005\u0014\u0000\u0000\u0108\u0104\u0001"+
		"\u0000\u0000\u0000\u0108\u0107\u0001\u0000\u0000\u0000\u0109=\u0001\u0000"+
		"\u0000\u0000\u010a\u010b\u0005\u0014\u0000\u0000\u010b?\u0001\u0000\u0000"+
		"\u0000\u010c\u010d\u0005\t\u0000\u0000\u010d\u010e\u0005\u0010\u0000\u0000"+
		"\u010e\u010f\u0003B!\u0000\u010f\u0110\u0005\u0010\u0000\u0000\u0110\u0111"+
		"\u0003D\"\u0000\u0111\u0112\u0005\u0010\u0000\u0000\u0112\u0113\u0003"+
		" \u0010\u0000\u0113A\u0001\u0000\u0000\u0000\u0114\u0116\u0005\u0014\u0000"+
		"\u0000\u0115\u0117\u0005\u0018\u0000\u0000\u0116\u0115\u0001\u0000\u0000"+
		"\u0000\u0116\u0117\u0001\u0000\u0000\u0000\u0117C\u0001\u0000\u0000\u0000"+
		"\u0118\u0119\u0005\u0013\u0000\u0000\u0119E\u0001\u0000\u0000\u0000\u011a"+
		"\u011b\u0005\n\u0000\u0000\u011b\u011c\u0005\u0010\u0000\u0000\u011c\u011d"+
		"\u0003H$\u0000\u011d\u011e\u0005\u0010\u0000\u0000\u011e\u011f\u0003J"+
		"%\u0000\u011f\u0120\u0005\u0010\u0000\u0000\u0120\u0121\u0003 \u0010\u0000"+
		"\u0121G\u0001\u0000\u0000\u0000\u0122\u0124\u0005\u0014\u0000\u0000\u0123"+
		"\u0125\u0005\u0018\u0000\u0000\u0124\u0123\u0001\u0000\u0000\u0000\u0124"+
		"\u0125\u0001\u0000\u0000\u0000\u0125I\u0001\u0000\u0000\u0000\u0126\u0127"+
		"\u0005\u0014\u0000\u0000\u0127K\u0001\u0000\u0000\u0000\u0128\u0129\u0005"+
		"\u000b\u0000\u0000\u0129\u012a\u0005\u0010\u0000\u0000\u012a\u012b\u0003"+
		"N\'\u0000\u012b\u012c\u0005\u0010\u0000\u0000\u012c\u012d\u0003P(\u0000"+
		"\u012d\u012e\u0005\u0010\u0000\u0000\u012e\u012f\u0003 \u0010\u0000\u012f"+
		"M\u0001\u0000\u0000\u0000\u0130\u0132\u0005\u0014\u0000\u0000\u0131\u0133"+
		"\u0005\u0018\u0000\u0000\u0132\u0131\u0001\u0000\u0000\u0000\u0132\u0133"+
		"\u0001\u0000\u0000\u0000\u0133O\u0001\u0000\u0000\u0000\u0134\u0135\u0007"+
		"\u0001\u0000\u0000\u0135Q\u0001\u0000\u0000\u0000\u000e\u0081\u008c\u00a4"+
		"\u00ba\u00c1\u00e0\u00e7\u00ed\u00f3\u00ff\u0108\u0116\u0124\u0132";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}