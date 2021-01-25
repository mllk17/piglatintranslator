import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Paths;
import java.io.*;

public class second {
    public static void main(String[] args) {
        System.out.println("Hey peeps. Would you like to translate to or from Pig Latin?" +
                "\nType '1' to translate to Pig Latin. Type '2' to translate from Pig Latin.");
        Scanner sc = new Scanner(System.in);
        //String input = whatyoutype.nextLine();
        int whatToDo = 0;

        //Scanner theWordin = new Scanner(System.in);
        //String theWord = theWordin.nextLine();

        try {
            whatToDo = sc.nextInt();
            switch (whatToDo) {
                case 1:
                    System.out.println("Type '1' to enter a text or '2' to read text text from a file?");
                    whatToDo = sc.nextInt();
                    switch(whatToDo){
                        case 1:
                            System.out.println("What word(s) would you like to translate to Pig Latin?");
                            Scanner theWordins = new Scanner(System.in);
                            String theWords = theWordins.nextLine();
                            System.out.println(toPL(theWords));
                            break;

                        case 2://create another to pig latin method with the input as the file?
                            System.out.println("Please input your file.");
                            Scanner fileIn = new Scanner(System.in);
                            String ffile = fileIn.nextLine();
                            System.out.println(ffile);
                            File outfile = new File("congrats.txt");
                            String myPath = Paths.get(".").toAbsolutePath().normalize().toString();
                            System.out.println(myPath+"/"+ffile);
                            BufferedReader reader = new BufferedReader(new FileReader(myPath+"/"+ffile));
                            BufferedWriter writer = new BufferedWriter(new FileWriter(myPath+"/"+outfile));
                            writer.write(toPL(reader.readLine()));
                            reader.close();
                            writer.close();
                            break;
                    }
                    break;
                case 2:
                    System.out.println("What word(s) would you like to translate from Pig Latin?");
                    Scanner theWordin = new Scanner(System.in);
                    String theWord = theWordin.nextLine();
                    System.out.println(fromPL(theWord));
                    break;


            }


        } catch (Exception e) {
            System.out.println("Nice try, but no. Follow the directions." +
                    "\nType '1' to translate to Pig Latin. Type '2' to translate from Pig Latin.");
            whatToDo = sc.nextInt();
        }

    }

    public static String toPL(String word) {
        String line = "";
        String anotherLine = "";

        String punctuation = ";.,()&^!-=+/|";
        ArrayList<String> punc = new ArrayList<>();
        ArrayList index = new ArrayList();

        //stores all the words from word into an arraylist, separating by space
        ArrayList<String> it = new ArrayList<>();
        String theline = "";

        for(int i = 0; i < word.length(); i++) {
            if ((word.substring(i, i + 1)).equals(" ")) {
                it.add(theline);
                theline = "";
            } else if (word.substring(i, i + 1).contains(punctuation)) {
                punc.add(word.substring(i, i + 1));
                index.add(i);
            } else {
                theline = theline + word.substring(i, i + 1);
            }

            if (i == (word.length() - 1)) {
                it.add(theline);

                if (word.substring(i, i + 1).contains(punctuation)) {
                    punc.add(word.substring(i, i + 1));
                    index.add(i);
                }
            }
        }

        for(int y = 0; y < it.size(); y++) {
            switch ((it.get(y).substring(0,1)).toLowerCase()) {
                case "a":
                    line = it.get(y) + "-yay";
                    break;

                case "e":
                    line = it.get(y) + "-yay";
                    break;

                case "i":
                    line = it.get(y) + "-yay";
                    break;

                case "o":
                    line = it.get(y) + "-yay";
                    break;

                case "u":
                    line = it.get(y) + "-yay";
                    break;

                default:
                    line = it.get(y).substring(1) + it.get(y).substring(0, 1) + "ay";
            }
            System.out.println(line);

            if(y == (it.size() - 1)){
                anotherLine = anotherLine + line;
            }
            else{
                anotherLine = anotherLine + line + " ";
                System.out.println(anotherLine);
            }
        }

        System.out.println(anotherLine);
        return anotherLine;
    }

    public static String fromPL(String word){
        String line = "";

        ArrayList<String> it = new ArrayList<>();
        String theline = "";

        for(int i = 0; i < word.length(); i++) {
            if((word.substring(i, i+1)).equals(" ")) {
                it.add(theline);
                theline = "";
            }
            else{
                theline = theline + word.substring(i, i+1);
            }
            if(i == (word.length() - 1)){
                it.add(theline);
            }
        }

        //the yth word stored in the arraylist
        for(int y = 0; y < it.size(); y++) {
            //for each word, goes through each individual character
            int tl = it.get(y).length();
            if(it.get(y).substring(tl-4).equals("-yay")){
                line = line + it.get(y).substring(0, (tl-4)) + " ";
            }
            else if(it.get(y).substring((tl)-2).equals("ay")){
                line = line + it.get(y).substring(tl-3, tl-2) + it.get(y).substring(0, tl-3) + " ";

                /*for (int x = 0; x < it.get(y).length() - 1; x++) {
                    //System.out.println(line);
                    if ((it.get(y).substring(x, x + 2).equals("ay"))) {// && ((x + 2) == it.get(y).length())){
                        line = line + it.get(y).substring(x - 1, x) + it.get(y).substring(0, x - 1) + " ";
                    }

                    if ((it.get(y).substring(x, x + 4).equals("-yay"))) {// && ((x + 5) == it.get(y).length())) {
                        line = line + it.get(y).substring(0, x) + " ";
                    }
                }*/
            }
            else{//loop back to nice try, but no?
                int x = 0;
            }
        }
        return line;
    }
}
