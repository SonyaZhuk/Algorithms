package algo.lakman.hard.popularname;

import java.util.HashSet;
import java.util.Set;

public class NameSet {
    private Set<String> names = new HashSet<>();
    private String rootName;
    private int frequency;


    public NameSet(String name, int frequency) {
        this.frequency = frequency;
        this.rootName = name;
        names.add(name);
    }

    public void copyNamesWithFrequency(Set<String> more, int freq) {
        names.addAll(more);
        frequency += freq;
    }

    public Set<String> getNames() {
        return names;
    }

    public int getFrequency() {
        return frequency;
    }

    public String getRootName() {
        return rootName;
    }

    public int size() {
        return names.size();
    }
}
