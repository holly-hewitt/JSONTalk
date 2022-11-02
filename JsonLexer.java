// Generated from src/json.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class JsonLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		NUMBER=10, WS=11, STRING=12;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"INT", "NUMBER", "WS", "STRING", "EXPONENT", "HEX_DIGIT", "ESC_SEQ", 
			"UNICODE_ESC"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'false'", "'null'", "'true'", "'{'", "','", "'}'", "':'", "'['", 
			"']'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "NUMBER", 
			"WS", "STRING"
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


	public JsonLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "json.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\16\u0083\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4"+
		"\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\6\13C\n\13\r\13"+
		"\16\13D\3\f\5\fH\n\f\3\f\3\f\3\f\7\fM\n\f\f\f\16\fP\13\f\5\fR\n\f\3\f"+
		"\3\f\6\fV\n\f\r\f\16\fW\5\fZ\n\f\3\f\5\f]\n\f\3\r\3\r\3\r\3\r\3\16\3\16"+
		"\3\16\7\16f\n\16\f\16\16\16i\13\16\3\16\3\16\3\17\3\17\5\17o\n\17\3\17"+
		"\6\17r\n\17\r\17\16\17s\3\20\3\20\3\21\3\21\3\21\5\21{\n\21\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\2\2\23\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\2\27\f\31\r\33\16\35\2\37\2!\2#\2\3\2\b\5\2\13\f\17\17\"\"\4\2"+
		"$$^^\4\2GGgg\4\2--//\5\2\62;CHch\n\2$$\61\61^^ddhhppttvv\2\u0089\2\3\3"+
		"\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2"+
		"\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3"+
		"\2\2\2\3%\3\2\2\2\5+\3\2\2\2\7\60\3\2\2\2\t\65\3\2\2\2\13\67\3\2\2\2\r"+
		"9\3\2\2\2\17;\3\2\2\2\21=\3\2\2\2\23?\3\2\2\2\25B\3\2\2\2\27G\3\2\2\2"+
		"\31^\3\2\2\2\33b\3\2\2\2\35l\3\2\2\2\37u\3\2\2\2!z\3\2\2\2#|\3\2\2\2%"+
		"&\7h\2\2&\'\7c\2\2\'(\7n\2\2()\7u\2\2)*\7g\2\2*\4\3\2\2\2+,\7p\2\2,-\7"+
		"w\2\2-.\7n\2\2./\7n\2\2/\6\3\2\2\2\60\61\7v\2\2\61\62\7t\2\2\62\63\7w"+
		"\2\2\63\64\7g\2\2\64\b\3\2\2\2\65\66\7}\2\2\66\n\3\2\2\2\678\7.\2\28\f"+
		"\3\2\2\29:\7\177\2\2:\16\3\2\2\2;<\7<\2\2<\20\3\2\2\2=>\7]\2\2>\22\3\2"+
		"\2\2?@\7_\2\2@\24\3\2\2\2AC\4\62;\2BA\3\2\2\2CD\3\2\2\2DB\3\2\2\2DE\3"+
		"\2\2\2E\26\3\2\2\2FH\7/\2\2GF\3\2\2\2GH\3\2\2\2HQ\3\2\2\2IR\7\62\2\2J"+
		"N\4\63;\2KM\5\25\13\2LK\3\2\2\2MP\3\2\2\2NL\3\2\2\2NO\3\2\2\2OR\3\2\2"+
		"\2PN\3\2\2\2QI\3\2\2\2QJ\3\2\2\2RY\3\2\2\2SU\7\60\2\2TV\5\25\13\2UT\3"+
		"\2\2\2VW\3\2\2\2WU\3\2\2\2WX\3\2\2\2XZ\3\2\2\2YS\3\2\2\2YZ\3\2\2\2Z\\"+
		"\3\2\2\2[]\5\35\17\2\\[\3\2\2\2\\]\3\2\2\2]\30\3\2\2\2^_\t\2\2\2_`\3\2"+
		"\2\2`a\b\r\2\2a\32\3\2\2\2bg\7$\2\2cf\5!\21\2df\n\3\2\2ec\3\2\2\2ed\3"+
		"\2\2\2fi\3\2\2\2ge\3\2\2\2gh\3\2\2\2hj\3\2\2\2ig\3\2\2\2jk\7$\2\2k\34"+
		"\3\2\2\2ln\t\4\2\2mo\t\5\2\2nm\3\2\2\2no\3\2\2\2oq\3\2\2\2pr\4\62;\2q"+
		"p\3\2\2\2rs\3\2\2\2sq\3\2\2\2st\3\2\2\2t\36\3\2\2\2uv\t\6\2\2v \3\2\2"+
		"\2wx\7^\2\2x{\t\7\2\2y{\5#\22\2zw\3\2\2\2zy\3\2\2\2{\"\3\2\2\2|}\7^\2"+
		"\2}~\7w\2\2~\177\5\37\20\2\177\u0080\5\37\20\2\u0080\u0081\5\37\20\2\u0081"+
		"\u0082\5\37\20\2\u0082$\3\2\2\2\17\2DGNQWY\\egnsz\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}