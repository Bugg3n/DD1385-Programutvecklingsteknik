package human;

public abstract class Human {
    String name;
    String ssn;

    protected Human(String name, String ssn) {
        this.name = name;
        this.ssn = ssn;
    }

    public static Human create(String name, String ssn) {
        int n = Integer.parseInt(ssn.substring(9,10));
        if (n == 0) {
            return new NonBinary(name, ssn);
        } else if (n % 2 == 0) {
            return new Woman(name, ssn);
        } else {
            return new Man(name, ssn);
        }
    }

    public abstract String getGender();

    public String toString() {
        return "Jag är " + this.getGender() + " och heter " + this.name;
    }
}
