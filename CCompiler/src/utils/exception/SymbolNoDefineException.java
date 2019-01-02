package utils.exception;

public class SymbolNoDefineException extends CompileException {
    public SymbolNoDefineException(String symbol) {
        super(String.format("未找到标识符 %s", symbol));
    }
}
