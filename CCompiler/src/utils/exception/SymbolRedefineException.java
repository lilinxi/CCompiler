package utils.exception;

public class SymbolRedefineException extends CompileException {
    public SymbolRedefineException(String symbol) {
        super(String.format("标识符 %s 重定义", symbol));
    }
}
