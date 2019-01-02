package utils.tree;

import utils.exception.TypeMismatchException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Node {
    public static final String Type = "type";
    public static final String Value = "value";
    public static final String Symbol = "symbol";
    public static final String StyleId = "style-id";
    public static final String TerminalValue = "#";
    public static final String[] NumberType = {"int", "float", "double"};

    protected Node parent;
    protected ArrayList<Node> children;
    protected String description;
    protected Map<String, Object> attributes;
    protected int id;
    protected static int ID = 0;
    //TODO
//    protected int offset;
//    protected int beginLine;
//    protected int beginColumn;
//    protected int endLine;
//    protected int endColumn;

    public Node(String description) {
        this.id = ID++;
        this.description = description;
        this.attributes = new HashMap<>();
        this.parent = null;
        this.children = new ArrayList<>();
    }

    public String getDescription() {
        return description;
    }

    public Node addChildren(Node... nodes) {
        for (Node node : nodes) {
            if (node != null) {
                node.parent = this;
                children.add(node);
            }
        }
        return this;
    }

    public Node setAttribute(String key, Object value) {
        attributes.put(key, value);
        return this;
    }

    public Node setAttribute(String key, Node node) {
        attributes.put(key, node.getAttribute(key));
        return this;
    }

    public Node setAttributeOnce(String key, Object value) {
        if (!attributes.keySet().contains(key)) {
            attributes.put(key, value);
        }
        return this;
    }

    public Node setAttributeOnce(String key, Node node) {
        if (!attributes.keySet().contains(key)) {
            attributes.put(key, node.getAttribute(key));
        }
        return this;
    }

    public Object getAttribute(String key)
    {
        return attributes.get(key);
    }

    public void tree() {
        tree(1);
    }

    private void tree(int depth) {
        for (int i = 0; i < depth - 1; i++) {
            System.out.print("  ");
        }
        if (depth > 1) {
            System.out.print("|-- ");
        }
        System.out.printf("%-10s   %-15s\n", description, attributes);
        for (Node child : children) {
            child.tree(depth + 1);
        }
    }

    public void dump() {
        StringBuilder childrenId = new StringBuilder();
        for (Node child : children) {
            if (child != null) {
                childrenId.append(child.id);
                childrenId.append(' ');
            }
        }
        System.out.printf("%3s: %-10s   %-15s    Children: %s\n",
                id, description, attributes, childrenId);
        for (Node child : children) {
            if (child != null) {
                child.dump();
            }
        }
    }

    /**
     * 节点最简化
     *
     * @return
     */
    public Node simplify() {
        if (children.size() == 1) {
            Node node = children.get(0);
            for (String key : attributes.keySet()) {
                node.setAttribute(key, attributes.get(key));
            }
            return children.get(0);
        } else {
            return this;
        }
    }

    //TODO 强制类型转换修改 Node.typeMatch 和 SymbolTable.getNode_r
    public boolean typeMatch(Node node) throws TypeMismatchException {
        String typeL = (String) getAttribute(Node.Type);
        String typeR = (String) node.getAttribute(Node.Type);
        if (typeL.equals(typeR)) {
            return true;
        }
        System.out.println(this);
        System.out.println(node);
        throw new TypeMismatchException(typeR, typeL);
    }

    public boolean typeMatch(String... types) throws TypeMismatchException {
        String type = (String) getAttribute(Node.Type);
        for (String t : types) {
            if (type.equals(t)) {
                return true;
            }
        }
        StringBuilder buf = new StringBuilder(types[0]);
        for (int i = 1; i < types.length; i++) {
            buf.append(',');
            buf.append(types[i]);
        }
        throw new TypeMismatchException(type, buf.toString());
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "Node{" +
                "description='" + description + '\'' +
                ", attributes=" + attributes +
                ", id=" + id +
                '}';
    }

    public static void main(String[] args) {
        Node node = new Node("type");
        node.setAttribute("key", "value");
        node.setAttribute("key", "value1");
        node.dump();
        System.out.println(node.getAttribute("key1"));
    }
}
