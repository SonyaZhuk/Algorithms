package algo.lakman.medium.xml;

/**
 * Decode XML to compressed form.
 * <p>
 * See Lakman p. 516
 */
public class Parser {

    public String parseXML(Element root) {
        StringBuilder sb = new StringBuilder();
        encode(root, sb);
        return sb.toString();
    }

    private void encode(Element root, StringBuilder sb) {
        encode(root.getNameCode(), sb);
        for (Attribute atr : root.getAttributes()) {
            encode(atr, sb);
        }
        encode("0", sb);

        String val = root.getValue();
        if (val != null && !val.isBlank()) {
            encode(val, sb);
        } else {
            for (Element el : root.getChildren()) {
                encode(el, sb);
            }
        }
        encode("0", sb);
    }

    private void encode(String v, StringBuilder sb) {
        sb.append(v);
        sb.append(" ");
    }

    private void encode(Attribute attr, StringBuilder sb) {
        encode(attr.getTagCode(), sb);
        encode(attr.getValue(), sb);
    }
}
