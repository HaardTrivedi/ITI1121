/*Bhalachandra Malghan - Haard Trivedi
* ITI 1121-C
* Assignment 4
* 300034277  - 300021545 
*/
//@author Marcelle Turcotte
public class Q2 {

    public static void main(String[] args) {

        StudentInfo.display();

        LinkedList<String> alphabet;
        alphabet = new LinkedList<String>();

        alphabet.add("alpha");
        alphabet.add("bravo");
        alphabet.add("charlie");
        alphabet.add("delta");
        alphabet.add("echo");

        Iterator<String> i;
        i = alphabet.iterator();

        while (i.hasNext()) {
            System.out.println(i.nextIndex());
            System.out.println(i.next());
        }
        System.out.println(i.nextIndex());
    }
    
    
}
