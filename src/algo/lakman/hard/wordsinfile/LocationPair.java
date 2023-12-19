package algo.lakman.hard.wordsinfile;

public class LocationPair {
    private int loc1;
    private int loc2;

    public LocationPair(int loc1, int loc2) {
        this.loc1 = loc1;
        this.loc2 = loc2;
    }

    public int getLoc1() {
        return loc1;
    }

    public int getLoc2() {
        return loc2;
    }

    public void setLoc1(int loc1) {
        this.loc1 = loc1;
    }

    public void setLoc2(int loc2) {
        this.loc2 = loc2;
    }

    public void setLocations(int loc1, int loc2) {
        this.loc1 = loc1;
        this.loc2 = loc2;
    }

    public void setLocations(LocationPair loc) {
        setLocations(loc.loc1, loc.loc2);
    }

    public int distance() {
        return Math.abs(this.loc1 - this.loc2);
    }

    public boolean isValid() {
        return this.loc1 >= 0 && this.loc2 >= 0;
    }

    public void updateWithMin(LocationPair loc) {
        if (!isValid() || loc.distance() < distance()) {
            setLocations(loc);
        }
    }
}
