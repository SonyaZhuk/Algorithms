package algo.lakman.xml;

public class Element {
    private String nameCode;
    private String value;
    private Element[] children;
    private Attribute[] attributes;

    public String getNameCode() {
        return nameCode;
    }

    public String getValue() {
        return value;
    }

    public Element[] getChildren() {
        return children;
    }
    public Attribute[] getAttributes() {
        return attributes;
    }
}
