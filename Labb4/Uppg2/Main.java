import human.Human;

public class Main {
    public static void main(String[] args) {
        Human billie = Human.create("Billie", "xxxxxx-560x");
        Human anna = Human.create("Anna", "xxxxxx-642x");
        Human magnus = Human.create("Magnus", "xxxxxx-011x");
        System.out.println(billie);
        System.out.println(anna);
        System.out.println(magnus);
    }
}
