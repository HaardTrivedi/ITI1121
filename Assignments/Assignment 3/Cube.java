/**@Author: HaardTrivedi
 * @StudentNumber:300021545
 * @Course: ITI 1121 - C
 * @Assignment: 3
 */

public class Cube {

    private Color[] faces = new Color[6];
    private final Color[] facesInitial = new Color[6];
    private int count;

    public Cube(Color[] faces) {
        count = 0;
        for (int i = 0; i < faces.length; i++) {
            this.faces[i] = faces[i];
            this.facesInitial[i] = faces[i];
        }
        //[Up, Front, Right, Back, Left, Down]
    }
    
    public Cube(Cube other){
        count = 0;
        this.faces[0] = other.getUp();
        this.faces[1] = other.getFront();
        this.faces[2] = other.getRight();
        this.faces[3] = other.getBack();
        this.faces[4] = other.getLeft();
        this.faces[5] = other.getDown();
        
        for (int i = 0; i < faces.length; i++) {
            this.facesInitial[i] = this.faces[i];
        }
    }

    public Color getUp() {
        return this.faces[0];
    }

    public Color getFront() {
        return this.faces[1];
    }

    public Color getRight() {
        return this.faces[2];
    }

    public Color getBack() {
        return this.faces[3];
    }

    public Color getLeft() {
        return this.faces[4];
    }

    public Color getDown() {
        return this.faces[5];
    }

    @Override
    public String toString() {
        String colours = "";
        for (int i = 0; i < this.faces.length; i++) {
            if (i == faces.length - 1) {
                colours += faces[i].toString();
            } else {
                colours += (faces[i].toString() + ", ");
            }
        }
        return "[" + colours + "]";
    }

    public boolean hasNext() {
        if (count<24){
            return true;
        } return false;
    }

    public void next() {
        count++;
        switch (count) {
            case 0:
                identity();
                break;

            case 1:
                rotate();
                break;

            case 2:
                rotate();
                break;

            case 3:
                rotate();
                break;

            case 4:
                rightRoll();
                break;

            case 5:
                rotate();
                break;

            case 6:
                rotate();
                break;

            case 7:
                rotate();
                break;

            case 8:
                rightRoll();
                break;

            case 9:
                rotate();
                break;

            case 10:
                rotate();
                break;

            case 11:
                rotate();
                break;

            case 12:
                leftRoll();
                break;

            case 13:
                rotate();
                break;

            case 14:
                rotate();
                break;

            case 15:
                rotate();
                break;

            case 16:
                leftRoll();
                break;

            case 17:
                rotate();
                break;

            case 18:
                rotate();
                break;

            case 19:
                rotate();
                break;

            case 20:
                rightRoll();
                break;

            case 21:
                rotate();
                break;

            case 22:
                rotate();
                break;

            case 23:
                rotate();
                break;
                
        }        
        

    }

    public void reset() {
        for (int i = 0; i < facesInitial.length; i++) {
            faces[i] = facesInitial[i];
        }
        count = -1;
    }

    public void rotate() {
        Color[] temp = new Color[6];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = faces[i];
        }

        faces[1] = temp[4];
        faces[2] = temp[1];
        faces[3] = temp[2];
        faces[4] = temp[3];
    }

    public void rightRoll() {
        Color[] temp = new Color[6];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = faces[i];
        }

        faces[0] = temp[4];
        faces[2] = temp[0];
        faces[4] = temp[2];
        faces[5] = temp[5];
    }

    public void leftRoll() {
        Color[] temp = new Color[6];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = faces[i];
        }

        faces[0] = temp[2];
        faces[2] = temp[5];
        faces[4] = temp[0];
        faces[5] = temp[4];
    }

    public void identity() {
        for (int i = 0; i < facesInitial.length; i++) {
            faces[i] = facesInitial[i];
        }
    }
    
    public Cube copy(){
        Cube temp = new Cube(this.faces);
        return temp;
    }
    
    

}
