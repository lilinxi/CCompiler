package utils.abstractcode;

import java.util.ArrayList;

public class FourTermStyle {
    public static final String BLANK = "0";
    public static final String ASSIGN = "=";
    public static final String JMP = "j";
    private static final String TEMP = "t";
    private static final String RETURN= "RETURN";
    private static final String FUNC= "fun";
    private static final String MAIN= "main";
    private static int TEMP_COUNT = 1;
    private static final ArrayList<FourTermStyle> BUFFER = new ArrayList<>();
    private static final ArrayList<FourTermStyle> CURRENT = new ArrayList<>();

    private int id;
    private String op;
    private Object arg1;
    private Object arg2;
    private Object result;

    private FourTermStyle(String op, Object arg1, Object arg2, Object result) {
        this.op = op;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.result = result;
    }

    public static final FourTermStyle addCurrent(String op, Object arg1, Object arg2, Object result) {
        FourTermStyle ret = new FourTermStyle(op, arg1, arg2, result);
        ret.id = CURRENT.size();
        CURRENT.add(ret);
        return ret;
    }

    public static final FourTermStyle addCurrent(String op, Object arg1, Object result) {
        return addCurrent(op, arg1, BLANK, result);
    }

    public static final FourTermStyle addCurrent(String op, Object result) {
       return addCurrent(op, BLANK, BLANK, result);
    }

    public static final FourTermStyle addCurrentJmp(Object arg1) {
        return addCurrent(JMP, arg1, BLANK, BLANK);
    }

    public static final FourTermStyle addCurrentJmp() {
        return addCurrent(JMP, BLANK, BLANK, BLANK);
    }

    public int getId() {
        return id;
    }

//    只有第一次回填有效
    public void backpatchResult(Object result) {
        if (this.result.equals(BLANK)&&result!=null) {
            this.result = result;
        }
    }

    public static int nextId() {
        return CURRENT.size();
    }

    public static final String newTemp() {
        return TEMP + String.valueOf(TEMP_COUNT++);
    }

    public static final void fresh() {
        for (FourTermStyle style : BUFFER) {
            style.id = CURRENT.size();
            CURRENT.add(style);
        }
        BUFFER.clear();
    }

    public static final void show() {
        for (FourTermStyle style : CURRENT) {
            System.out.println(style);
        }
    }

    public static final void start() {
        FourTermStyle.addCurrent(FUNC, BLANK, BLANK, MAIN);
    }

    public static final void finish() {
        String temp = newTemp();
        FourTermStyle.addCurrent(ASSIGN, "#0", BLANK, temp);
        FourTermStyle.addCurrent(RETURN, BLANK, BLANK, temp);
    }

    @Override
    public String toString() {
        return String.format("%2d: (%s,%5s,%5s,%5s)", id,op, arg1, arg2, result);
    }
}
