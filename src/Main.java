import java.util.Random;

public class Main {
    public static final int MAX_6_BIT_HEX_NUM = 16777216 - 1;
    public static void main(String[] args) throws Exception {
        System.out.println(generateID());
    }

    public static String generateID() {
        Random rand = new Random();
        int n = rand.nextInt(MAX_6_BIT_HEX_NUM);
        String ID = String.format("%06x", n);
        
        return ID;
    }
}
