/* Generated By:JavaCC: Do not edit this line. CCompilerConstants.java */
package ccompiler;


/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface CCompilerConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int LINE_COMMIT = 6;
  /** RegularExpression Id. */
  int BLOCK_COMMIT = 9;
  /** RegularExpression Id. */
  int SEMIC = 10;
  /** RegularExpression Id. */
  int COMMA = 11;
  /** RegularExpression Id. */
  int ASSIGN = 12;
  /** RegularExpression Id. */
  int MACRO = 13;
  /** RegularExpression Id. */
  int LBR = 14;
  /** RegularExpression Id. */
  int RBR = 15;
  /** RegularExpression Id. */
  int RCBR = 16;
  /** RegularExpression Id. */
  int LCBR = 17;
  /** RegularExpression Id. */
  int PLUS = 18;
  /** RegularExpression Id. */
  int MINUS = 19;
  /** RegularExpression Id. */
  int MULT = 20;
  /** RegularExpression Id. */
  int DIV = 21;
  /** RegularExpression Id. */
  int MOD = 22;
  /** RegularExpression Id. */
  int INC = 23;
  /** RegularExpression Id. */
  int DEC = 24;
  /** RegularExpression Id. */
  int EXLM = 25;
  /** RegularExpression Id. */
  int LOGIC_EQUAL = 26;
  /** RegularExpression Id. */
  int LOGIC_NOT = 27;
  /** RegularExpression Id. */
  int LOGIC_AND = 28;
  /** RegularExpression Id. */
  int LOGIC_OR = 29;
  /** RegularExpression Id. */
  int LEFT = 30;
  /** RegularExpression Id. */
  int RIGHT = 31;
  /** RegularExpression Id. */
  int LEFT_EQUAL = 32;
  /** RegularExpression Id. */
  int RIGHT_EQUAL = 33;
  /** RegularExpression Id. */
  int BOOL = 34;
  /** RegularExpression Id. */
  int DO = 35;
  /** RegularExpression Id. */
  int ELSE = 36;
  /** RegularExpression Id. */
  int IF = 37;
  /** RegularExpression Id. */
  int INT = 38;
  /** RegularExpression Id. */
  int FLOAT = 39;
  /** RegularExpression Id. */
  int DOUBLE = 40;
  /** RegularExpression Id. */
  int RETURN = 41;
  /** RegularExpression Id. */
  int STRING = 42;
  /** RegularExpression Id. */
  int VOID = 43;
  /** RegularExpression Id. */
  int CHAR = 44;
  /** RegularExpression Id. */
  int WHILE = 45;
  /** RegularExpression Id. */
  int INCLUDE = 46;
  /** RegularExpression Id. */
  int DEFINE = 47;
  /** RegularExpression Id. */
  int GOTO = 48;
  /** RegularExpression Id. */
  int FOR = 49;
  /** RegularExpression Id. */
  int SWITCH = 50;
  /** RegularExpression Id. */
  int CASE = 51;
  /** RegularExpression Id. */
  int CASE_DEFAULT = 52;
  /** RegularExpression Id. */
  int BREAK = 53;
  /** RegularExpression Id. */
  int CONTINUE = 54;
  /** RegularExpression Id. */
  int IDENTIFIER = 55;
  /** RegularExpression Id. */
  int INT_VALUE = 56;
  /** RegularExpression Id. */
  int FLOAT_VALUE = 57;
  /** RegularExpression Id. */
  int DOUBLE_VALUE = 58;
  /** RegularExpression Id. */
  int BOOL_VALUE = 59;
  /** RegularExpression Id. */
  int STRING_VALUE = 64;
  /** RegularExpression Id. */
  int CHAR_VALUE = 69;
  /** RegularExpression Id. */
  int OTHER = 70;

  /** Lexical state. */
  int DEFAULT = 0;
  /** Lexical state. */
  int IN_BLOCK_COMMIT = 1;
  /** Lexical state. */
  int IN_STRING = 2;
  /** Lexical state. */
  int IN_CHARACTER = 3;
  /** Lexical state. */
  int CHARACTER_TERM = 4;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\" \"",
    "\"\\t\"",
    "\"\\n\"",
    "\"\\r\"",
    "\"\\f\"",
    "<LINE_COMMIT>",
    "\"/*\"",
    "<token of kind 8>",
    "\"*/\"",
    "\";\"",
    "\",\"",
    "\"=\"",
    "\"#\"",
    "\"(\"",
    "\")\"",
    "\"}\"",
    "\"{\"",
    "\"+\"",
    "\"-\"",
    "\"*\"",
    "\"/\"",
    "\"%\"",
    "\"++\"",
    "\"--\"",
    "\"!\"",
    "\"==\"",
    "\"!=\"",
    "\"&&\"",
    "\"||\"",
    "\"<\"",
    "\">\"",
    "\"<=\"",
    "\">=\"",
    "\"bool\"",
    "\"do\"",
    "\"else\"",
    "\"if\"",
    "\"int\"",
    "\"float\"",
    "\"double\"",
    "\"return\"",
    "\"string\"",
    "\"void\"",
    "\"char\"",
    "\"while\"",
    "\"include\"",
    "\"define\"",
    "\"goto\"",
    "\"for\"",
    "\"switch\"",
    "\"case\"",
    "\"default\"",
    "\"break\"",
    "\"continue\"",
    "<IDENTIFIER>",
    "<INT_VALUE>",
    "<FLOAT_VALUE>",
    "<DOUBLE_VALUE>",
    "<BOOL_VALUE>",
    "\"\\\"\"",
    "<token of kind 61>",
    "<token of kind 62>",
    "<token of kind 63>",
    "\"\\\"\"",
    "\"\\\'\"",
    "<token of kind 66>",
    "<token of kind 67>",
    "<token of kind 68>",
    "\"\\\'\"",
    "<OTHER>",
  };

}
