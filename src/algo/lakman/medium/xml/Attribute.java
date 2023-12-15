package algo.lakman.medium.xml;

public class Attribute {
    private String tagCode;
    private String value;

    public Attribute(String tagCode, String value) {
        this.tagCode = tagCode;
        this.value = value;
    }

    public String getTagCode() {
        return tagCode;
    }

    public String getValue() {
        return value;
    }
}