import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Proc {
    public void setInput(char input) {
        this.input = input;
    }

    char input;

    String output;
    String randomStreet = "dupa";
    boolean isChar;
    String hiddenWord = "";


    public String getOutput() {
        return output;
    }

    public boolean gameTurn(){
        StringBuilder sb = new StringBuilder(hiddenWord);

        int x = randomStreet.indexOf(input);

        if (x == -1) {
            isChar = false;
            return false;
        } else {
            while (x > -1) {
                sb.setCharAt(x, input);
                x = randomStreet.indexOf(input, x + 1);
            }
           output = ("Jest litera!  " + sb);
            hiddenWord = sb.toString();
        return true;
        }

    }

   /* public boolean validate() {
        if (input.equals(randomStreet)) {
            return true;
        } else {
            return false;
        }
    }*/

    public void chooseStreet() {
        File file = new File("ulice.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<String> streets = new ArrayList<String>();


        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            streets.add(line);
        }
        int random = (int) (Math.random() * streets.size());
        randomStreet = streets.get(random);

    }

    public void generateHiddenWord(){
        StringBuilder sb = new StringBuilder();
        String unknown = "";
        for (int i = 0; i < randomStreet.length(); i++) {
            sb.append("*");
        }
        hiddenWord = sb.toString();
    }

}


