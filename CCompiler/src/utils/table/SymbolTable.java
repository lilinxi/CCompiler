package utils.table;

import utils.tree.Node;
import utils.exception.CompileException;
import utils.exception.SymbolNoDefineException;
import utils.exception.SymbolRedefineException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class SymbolTable {
    public static final Stack<SymbolTable> SymbolStack = new Stack<>();

    static {
        SymbolStack.push(new SymbolTable("Globe", 0));
    }

    private String symbol;
    private int size;
    private SymbolTable parent;
    private int entry_index;// 在父符号表中的位置
    private ArrayList<Entry> entries;
    private int depth;

    private SymbolTable(String symbol) {
        this.symbol = symbol;
        this.size = 0;
        this.parent = null;
        this.entry_index = -1;
        this.entries = new ArrayList<>();
    }

    private SymbolTable(String symbol, int depth) {
        this(symbol);
        this.depth = depth;
    }

    private static final boolean addEntry(Entry entry) {
        SymbolTable table = SymbolStack.peek();
        table.entries.add(entry);
        entry.offset = table.size;
        table.size += entry.length;
        return true;
    }

    public static final boolean addEntry(String symbol, String type) throws SymbolRedefineException {
        SymbolTable table = SymbolStack.peek();
        if (table.find(symbol) != null) {
            throw new SymbolRedefineException(symbol);
        }
        Entry entry = new Entry(symbol, type);
        return addEntry(entry);
    }

    public static final void pushSymbolTable(String symbol) {
        SymbolTable table = SymbolStack.peek();
        SymbolTable child = new SymbolTable(symbol, table.depth + 1);
        child.parent = table;
        child.entry_index = table.entries.size();
        Entry entry = new Entry(symbol, Entry.SymbolTableType);
        entry.table = child;
        table.addEntry(entry);
        SymbolStack.push(child);
    }

    private final Entry find(String symbol) {
        for (Entry e : entries) {
            if (e.symbol.equals(symbol)) {
                return e;
            }
        }
        return null;
    }

    private final Entry find_r(String symbol) throws SymbolNoDefineException {
        Entry entry;
        if ((entry = find(symbol)) != null) {
            return entry;
        }
        if (parent != null) {
            return parent.find_r(symbol);
        }
        throw new SymbolNoDefineException(symbol);
    }

    /**
     * 符号表的回填，只回填父符号表
     */
    private final void backpatch() {
        if (parent != null) {
            Entry entry = parent.entries.get(entry_index);
            entry.length = size;
            parent.size += entry.length;
            for (; ++entry_index < parent.entries.size(); ) {
                parent.entries.get(entry_index).offset += size;
            }
        }
    }

    public static final void popSymbolTable() {
        SymbolStack.pop().backpatch();
    }

    /**
     * 符号表中的 Entry 与 Node 的适配，只获取符号表中的 Type 属性
     *
     * @param node
     * @return
     */
    public static final Node getNode_r(Node node) throws CompileException {
        if (!node.getDescription().equals(Node.Symbol)) {
            return node;
        }
        String symbol = (String) node.getAttribute(Node.Value);
        if (symbol == null) {
            throw new CompileException("Node：" + node + "中没有 value 属性");
        }
        SymbolTable table = SymbolStack.peek();
        Entry entry = table.find_r(symbol);
        return node.setAttribute(Node.Type, entry.type);
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append(String.format("%-10s %-5d\n", symbol, size));
        for (Entry e : entries) {
            buf.append(e);
            buf.append("\n");
        }
        return buf.toString();
    }

    public void tree() {
        tree(1);
    }

    private void tree(int depth) {
        for (int i = 0; i < depth-1; i++) {
            System.out.print("    ");
        }
        if (depth > 1) {
            System.out.print("|---");
        }
        System.out.println(String.format("%-10s %-5d", symbol, size));
        for (Entry e : entries) {
            for (int i = 0; i < depth-1; i++) {
                System.out.print("    ");
            }
            if (depth > 1) {
                System.out.print("|---");
            }
            System.out.println(e);
            if (e.isSymbolTable()) {
                e.table.tree(depth+1);
            }
        }
    }

    public static void main(String[] args) throws SymbolRedefineException {
        addEntry(new Entry("a", "int"));
        addEntry(new Entry("b", "int"));
        addEntry(new Entry("b", "int"));
        pushSymbolTable("new t");
        addEntry(new Entry("c", "int"));
        addEntry(new Entry("d", "int"));
        System.out.println(SymbolStack.pop());
        System.out.println(SymbolStack.pop());
    }
}

class Entry {
    private static final HashMap<String, Integer> TypeLengthMap = new HashMap<>();
    static final String SymbolTableType ="SymbolTable";

    static {
        TypeLengthMap.put("SymbolTable", 0);
        TypeLengthMap.put("int", 4);
        TypeLengthMap.put("float", 8);
        TypeLengthMap.put("double",8);
        TypeLengthMap.put("bool",4);
        TypeLengthMap.put("string",4);
        TypeLengthMap.put("char",2);
    }

    private static final int getTypeLength(String type) {
        return TypeLengthMap.get(type);
    }

    String symbol;
    String type;
    int offset;
    int length;
    SymbolTable table;

    Entry(String symbol, String type) {
        this.symbol = symbol;
        this.type = type;
        this.length = getTypeLength(type);
    }

    boolean isSymbolTable() {
        return type.equalsIgnoreCase(SymbolTableType);
    }

    @Override
    public String toString() {
        return String.format("%-10s  %-10s  %-5d", symbol, type, offset);
    }
}
