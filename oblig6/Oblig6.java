import java.io.FileNotFoundException;
import java.util.Scanner;

public class Oblig6 {
    public static void main(String[] args) throws FileNotFoundException{
        String mappe = "labyrinter/"; 
        String filnavn = args[0];
        Labyrint labyrint = new Labyrint(mappe + filnavn);
        System.out.println(labyrint);

        Scanner input = new Scanner(System.in);
        boolean start = true;

        while(start){
            System.out.println("Skriv inn koordinater <rad> <kolonne> (-1 for aa avslutte).");

            String velg = input.nextLine();

            switch(velg){
                case "-1":
                    start = false;
                    break;
                default:
                    String[] koordinater = velg.split(" ");
                    System.out.println("Aapninger:\n");
                    labyrint.finnUtVeiFra(Integer.parseInt(koordinater[0]), Integer.parseInt(koordinater[1]));
                    break;
            }
        }
        input.close();
    }
}
