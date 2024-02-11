package human;

class NonBinary extends Human {
    protected NonBinary(String name, String ssn) {
        super(name, ssn);
    }

    public String getGender() {
        return "icke-binär";
    }
}