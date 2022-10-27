import java.io.FileNotFoundException;

public class Hovedprogram {
    public static void main(String[] args)throws FileNotFoundException{
        String filnavn = "labyrinter/7.in";

        Labyrint labyrint = new Labyrint(filnavn);
        System.out.println(labyrint.toString());
        labyrint.finnUtVeiFra(1, 3);
    }
}
