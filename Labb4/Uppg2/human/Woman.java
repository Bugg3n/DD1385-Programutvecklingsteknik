package human;

class Woman extends Human {
    protected Woman (String name, String ssn) {
        super(name, ssn);
    }

    public String getGender() {
        return "kvinna";
    }
}