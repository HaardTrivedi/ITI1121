package Q1;

public class ITIStringBuffer {
    private SinglyLinkedList <Character> list;
    private String temp;

    public ITIStringBuffer() {
        this.list = new SinglyLinkedList<Character>();
        this.temp = null;
    }

    public ITIStringBuffer(String  firstString){
        this.append(firstString);
    }

    public void append(String nextString){
        temp = null;
        for (int i=0; i < nextString.length();i++){
            this.list.add(nextString.charAt(i));
        }
    }

    @Override
    public String toString(){
        if (temp == null){
            char [] characters = new char[list.size()];
            
//            int i = 0;
//            for (char c : list) {
//		        characters[i++] = c;
//	        }   
//            temp = new String (characters);
            
            System.out.println(list.get(0));
            char x = list.get(20); System.out.println(x);
            
            for (int i = 0; i < list.size(); i++){
                char c = list.get(i);
                characters [i] = c;
            }
            temp = new String (characters);
            
        }
        return temp;
    }

}
