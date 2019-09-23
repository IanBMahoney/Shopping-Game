package shopping;

/**
 *
 * @author Max
 */
public class Tile {

    String fileName = "";
    //int r, g, b;
    int[] rgb = new int[3];
    
    public Tile(int r, int g, int b) {
        rgb[0] = r;
        rgb[1] = g;
        rgb[2] = b;
        ////////System.out.println("t");
        setFileName();
    }

    public void setFileName() {
        fileName = "/tiles/" + rgb[0] + ".png";
    }
    
}
