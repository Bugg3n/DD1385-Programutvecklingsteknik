import java.util.Iterator;

public class Suitcase {
    public static void main(String[] args) {
        // Pack the suitcase
        Composite suitcase = new Composite(8, "Suitcase");
        Leaf tshirt = new Leaf(2, "T-shirt");
        Leaf hoodie = new Leaf(2, "Hoodie");
        Composite computerBag = new Composite(1, "Computer bag");
        Leaf computer = new Leaf(2, "Computer");
        Leaf charger = new Leaf(2, "Charger");
        Composite headphoneCase = new Composite(1, "Headphone case");
        Leaf headphones = new Leaf(1, "Headphones");
        Leaf cord = new Leaf(1, "Cord");
        Composite toiletryBag = new Composite(2, "Toiletry bag");
        Composite plasticBag = new Composite(1, "Plastic bag");
        Leaf hairPin = new Leaf(1, "Hair pin");
        Leaf soap = new Leaf(1, "Soap");
        Leaf shampoo = new Leaf(1, "Shampoo");

        suitcase.add(hoodie);
        suitcase.add(tshirt);
        suitcase.add(computerBag);
        suitcase.add(toiletryBag);

        computerBag.add(computer);
        computerBag.add(charger);
        computerBag.add(headphoneCase);

        headphoneCase.add(headphones);
        headphoneCase.add(cord);
    
        toiletryBag.add(plasticBag);
        toiletryBag.add(soap);
        toiletryBag.add(shampoo);
        
        plasticBag.add(hairPin);

        // Print the suitcase information
        System.out.println(suitcase.toString());
        System.out.println("Total weight: " + suitcase.getWeight());

        // Print the suitcase contents
        // for (int i = 0; i < 4; i++) {
        //     System.out.println(suitcase.getChild(i).toString());
        // }

        // Remove some items
        suitcase.remove(suitcase.getChild(2)); // child number 2 is computer bag
        suitcase.remove(tshirt);
        System.out.println("Removed computer bag and t-shirt");

        // Print the suitcase information
        System.out.println(suitcase.toString());
        System.out.println("Total weight: " + suitcase.getWeight());


        System.out.println("---------------------");
        System.out.println("Print contents DFS: ");
        Composite.setIteratorType("DFS");
        for (Component component : suitcase) {
            System.out.println(component.getName());
        }
        System.out.println("---------------------");
        System.out.println("Print contents DFS (while loop): ");
        Iterator<Component> iterator = suitcase.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getName());
        }

        System.out.println("---------------------");
        System.out.println("Print contents BFS: ");
        Composite.setIteratorType("BFS");
        for (Component component : suitcase) {
            System.out.println(component.getName());
        }
        System.out.println("---------------------");
        System.out.println("Print contents BFS (while loop): ");
        iterator = suitcase.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getName());
        }
    }
}
// Djupet först
// Suitcase
//   Hoodie
//   T-shirt
//   Computer bag
//     Computer
//     Charger
//     Headphone case
//       Headphones
//       Cord
//   Toiletry bag   
//     Soap
//     Shampoo
//     Plastic bag
//       Hair pin

// Bredden först
//   Suitcase
//   Hoodie
//   T-shirt
//   Computer bag
//   Toiletry bag
//   Computer
//   Charger
//   Headphone case
//   Soap
//   Shampoo
//   Plastic bag
//   Headphones
//   Hair pin