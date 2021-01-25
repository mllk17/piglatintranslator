import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Paths;
import java.io.*;
import java.lang.*;

public class mainone {
    public static void main(String[] args) {
        System.out.println("Hey peeps. Would you like to translate to or from Pig Latin?" +
                "\nType '1' to translate to Pig Latin. Type '2' to translate from Pig Latin.");
        Scanner sc = new Scanner(System.in);
        int whatToDo = 0;

        try {
            whatToDo = sc.nextInt();
            switch (whatToDo) {
                case 1:
                    System.out.println("Type '1' to enter a text or '2' to read text text from a file?");
                    whatToDo = sc.nextInt();

                    switch (whatToDo) {
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
                            System.out.println("file in" + myPath + "/" + ffile);
                            System.out.println("file out" + myPath + "/" + outfile);
                            BufferedReader reader = new BufferedReader(new FileReader(myPath + "/" + ffile));
                            BufferedWriter writer = new BufferedWriter(new FileWriter(myPath + "/" + outfile));
                            writer.write(toPL(reader.readLine()));
                            reader.close();
                            writer.close();
                            break;
                    }
                    break;

                case 2:
                    System.out.println("Please input your file.");
                    Scanner theFileIn = new Scanner(System.in);
                    String inFile = theFileIn.nextLine();
                    File outFile = new File("congratulations.txt");
                    String myPath2 = Paths.get(".").toAbsolutePath().normalize().toString();
                    System.out.println(myPath2 + "/" + inFile);
                    BufferedReader reader2 = new BufferedReader(new FileReader(myPath2 + "/" + inFile));
                    BufferedWriter writer2 = new BufferedWriter(new FileWriter(myPath2 + "/" + outFile));
                    writer2.write(fromPL(reader2.readLine()));
                    reader2.close();
                    writer2.close();
                    break;
            }

        } catch (Exception e) {
            System.out.println("Nice try, but no. Follow the directions." +
                    "\nType '1' to translate to Pig Latin. Type '2' to translate from Pig Latin.");
            whatToDo = sc.nextInt();
            //System.out.println(e);
        }
    }

    public static String toPL(String word) {
        String line = "";

        ArrayList<String> it = new ArrayList<>();
        String theline = "";

        //puts each word, separated by space into an arraylist
        for (int i = 0; i < word.length(); i++) {

            if ((word.substring(i, i + 1)).equals(" ")) {
                it.add(theline);
                theline = "";
            }
            else{
                theline = theline + word.substring(i, i + 1);
            }

            //if the last character was a space, it would not be added into the arraylist
            if ((i == (word.length() - 1)) && !(word.substring(i, i + 1).equals(" "))) {
                it.add(theline);
            }
        }
        //goes through list of words
        for (int y = 0; y < it.size(); y++) {

            //goes through characters in the word
            String firstLetter = (it.get(y).substring(0, 1)).toLowerCase();

            //if the first letter is a vowel, add -yay
            if (firstLetter.equals("a") || firstLetter.equals("e") || firstLetter.equals("i") || firstLetter.equals("o") || firstLetter.equals("u")) {

                if (y == 0) {
                    char fl = it.get(y).charAt(0);
                    char FL = Character.toUpperCase(fl);
                    line = line + FL + it.get(y).substring(1) + "-yay ";
                } else if (y == (it.size() - 1)) {
                    line = line + it.get(y) + "-yay";
                    break;
                } else {
                    line = line + it.get(y) + "-yay ";
                }

            } else {//if the first letter is a consonant, remove first letter to end of word and then add ay

                if (y == (it.size() - 1)) {
                    line = line + it.get(y).substring(1) + it.get(y).substring(0, 1) + "ay";
                    break;
                } else if (y == 0) {
                    char fL = it.get(y).charAt(1);
                    char Fl = Character.toUpperCase(fL);
                    line = line + Fl + it.get(y).substring(2) + it.get(y).substring(0, 1) + "ay ";
                } else {
                    line = line + it.get(y).substring(1) + it.get(y).substring(0, 1) + "ay ";
                }
            }
        }

        return line;
    }

    public static String fromPL(String word) {
        String line = "";

        ArrayList<String> it = new ArrayList<>();
        String theline = "";

        //puts each word, separated by space into an arraylist
        for (int i = 0; i < word.length(); i++) {

            if ((word.substring(i, i + 1)).equals(" ")) {
                it.add(theline);
                theline = "";
            } else {
                theline = theline + word.substring(i, i + 1);
            }

            //if the last character was a space, it would not be added into the arraylist
            if ((i == (word.length() - 1)) && !(word.substring(i, i+1).equals(" "))) {
                it.add(theline);

            }
        }

        //the yth word stored in the arraylist
        for (int y = 0; y < it.size(); y++) {

            int tl = it.get(y).length();

            //if the first letter starts with a vowel
            if (it.get(y).substring(tl - 4).equals("-yay")) {

                //previous word + rest of the word without the -yay
                if(y == 0) {
                    char fL = it.get(y).charAt(0);
                    char FL = Character.toUpperCase(fL);
                    line = line + FL + it.get(y).substring(1, (tl - 4));
                }
                else if(y != it.size()-1) {
                    line = line + it.get(y).substring(0, (tl - 4)) + " ";
                }
                else{
                    line = line + it.get(y).substring(0, (tl - 4));
                }
            //if the first letter starts with a consonant
            } else if (it.get(y).substring((tl) - 2).equals("ay")) {

                //previous word + first letter + rest of the word without the ay
                if(y == 0){
                    char fl = it.get(y).charAt(tl-3);
                    char Fl = Character.toUpperCase(fl);
                    line = line + Fl + it.get(y).substring(0, tl - 3) + " ";
                }
                if(y != it.size()-1) {
                    line = line + it.get(y).substring(tl - 3, tl - 2) + it.get(y).substring(0, tl - 3) + " ";
                }
                else {
                    line = line + it.get(y).substring(tl - 3, tl - 2) + it.get(y).substring(0, tl - 3);
                }

            } else {//loop back to nice try, but no?
                int a = 0;
            }
        }
        return line;
    }
}