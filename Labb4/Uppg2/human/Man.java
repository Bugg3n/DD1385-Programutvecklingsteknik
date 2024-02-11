package human;

class Man extends Human {
    protected Man (String name, String ssn) {
        super(name, ssn);
    }

    public String getGender() {
        return "man";
    }
}