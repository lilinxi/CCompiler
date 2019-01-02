package utils.exception;

public class TypeMismatchException extends CompileException {
    public TypeMismatchException(String typeGiven,String typeNeeded) {
        super(String.format("%s 与 %s 类型不匹配", typeGiven, typeNeeded));
    }
}
