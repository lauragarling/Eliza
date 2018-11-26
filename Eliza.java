//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Eliza
// Files: Eliza.java, ElizaTests.java, Config.java
// Course: CS 200 Fall 2018
//
// Author: Laura Garling
// Email: garling@wisc.edu
// Lecturer's Name: Marc Renault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: NONE
// Partner Email: NONE
// Lecturer's Name: NONE
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates
// strangers, etc do. If you received no outside help from either type of
// source, then please explicitly indicate NONE.
//
// Persons: NONE
// Online Sources:
//////////////////// https://docs.oracle.com/javase/8/docs/api/java/lang/Character.html#isLetterOrDigit-char-
//////////////////// https://docs.oracle.com/javase/8/docs/api/java/lang/String.html
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * The Eliza class holds the user input and response formation for a system that collects user input
 * and responds appropriately. Eliza is based off of a computer program written at MIT in the 1960's
 * by Joseph Weizenbaum. Eliza uses keyword matching to respond to users in a way that displays
 * interest in the users and continues the conversation until instructed otherwise.
 */
public class Eliza {

    /*
     * This method does input and output with the user. It calls supporting methods to read and
     * write files and process each user input.
     * 
     * @param args (unused)
     */
    public static void main(String[] args) {

        // System.out.print((separatePhrases("No. I'm talking to my dog! Bye.")));

        // ArrayList<String> list = new ArrayList<String>();
        // String[] list = {"hello", "how are you", "quit"};
        // list.add("hello");
        // list.add("how are you");
        // list.add("quit");
        // System.out.print(replaceWord("mom", Config.INPUT_WORD_MAP));
        // assemblePhrase(list);
        // System.out.print(foundQuitWord(list));

        // Milestone 2
        // System.out.print(replaceWord("i'm", Config.INPUT_WORD_MAP));

        // System.out.print(swapWords("i'm cant recollect", Config.PRONOUN_MAP));
         //System.out.print((prepareInput("I am happy")));
        /*
         * Scanner scan = new Scanner(System.in); String userInput = scan.next(); int randNum =
         * scan.nextInt(); Random rand = new Random(); int n = rand.nextInt(randNum);
         */
        // create a scanner for reading user input and a random number
        // generator with Config.SEED as the seed



        // Milestone 3
        // How the program starts depends on the command-line arguments.
        // Command-line arguments can be names of therapists for example:
        // Eliza Joe Laura
        // If no command-line arguments then the therapists name is Eliza
        // and this reads a file with that name and the Config.RESPONSE_FILE_EXTENSION.
        // Example filename: Eliza.rsp
        // If only one command-line argument, then read the responses from
        // the corresponding file with Config.RESPONSE_FILE_EXTENSION.
        // If there is more than one command-line argument then offer them
        // as a list of people to talk with. For the three therapists above the prompt is
        // "Would you like to speak with Eliza, Joe, or Laura?"
        // When a user types a name then read the responses from the file which
        // is the selected name and Config.RESPONSE_FILE_EXTENSION extension.
        // Whatever name the user types has the extension appended and
        // is read using loadResponseTable. If loadResponseTable can't load
        // the file then it will report an error.


        // Milestone 2
        // name prompt
        System.out.println("Hi I'm Eliza, what is your name?");
        Scanner scnr = new Scanner(System.in);
        String userName = scnr.next();

        // Milestone 2
        // welcome prompt
        System.out.println("Nice to meet you " + userName + ". What is on your mind?");
        String userInput = scnr.nextLine();
        System.out.print(userInput);

        // Milestone 2
        // begin conversation loop

        // Milestone 2
        // obtain user input


        // Milestone 2
        // prepareInput
        ArrayList<String> userInputArrayList = separatePhrases(userInput);
        String[] userInputArray = new String[(userInputArrayList).size()];
        userInputArray = prepareInput(userInput);

        // Milestone 3
        // if no quit words then prepareResponse
        if (foundQuitWord(userInputArrayList) == false) {
            //userInput = prepareResponse(userInput);
        }


        // Milestone 2
        // end loop if quit word
        if (foundQuitWord(userInputArrayList) == true) {



            // Milestone 2
            // ending prompt
            System.out.print("Goodbye " + userName + ".");
        }

        // Milestone 3
        // Save all conversation (user and system responses) starting
        // with this program saying "Hi I'm..." and concludes with
        // "Goodbye <name>.".
        // Always prompt the user to see if they would like to save a
        // record of the conversation. If the user enters a y or Y as the
        // first non-whitespace character then prompt for filename and save,
        // otherwise don't save dialog. After successfully saving a dialog
        // print the message "Thanks again for talking! Our conversation is saved in: <filename>".
        // If saveDialog throws an IOException then catch it, print out the error:
        // "Unable to save conversation to: " <name of file>
        // Repeat the code prompting the user if they want to save the dialog.


    }

    /**
     * This method processes the user input, returning an ArrayList containing Strings, where each
     * String is a phrase from the user's input. This is done by removing leading and trailing
     * whitespace, making the user's input all lower case, then going through each character of the
     * user's input. When going through each character this keeps all digits, alphabetic characters
     * and ' (single quote). The characters ? ! , . signal the end of a phrase, and possibly the
     * beginning of the next phrase, but are not included in the result. All other characters such
     * as ( ) - " ] etc. should be replaced with a space. This method makes sure that every phrase
     * has some visible characters but no leading or trailing whitespace and only a single space
     * between words of a phrase. If userInput is null then return null, if no characters then
     * return a 0 length list, otherwise return a list of phrases. Empty phrases and phrases with
     * just invalid/whitespace characters should NOT be added to the list.
     * 
     * Example userInput: "Hi, I am! a big-fun robot!!!" Example returned: "hi", "i am", "a big fun
     * robot"
     * 
     * @param userInput text the user typed
     * @return the phrases from the user's input
     */
    public static ArrayList<String> separatePhrases(String userInput) {
        userInput = userInput.trim().toLowerCase();
        userInput = userInput.replace('(', ' ');
        userInput = userInput.replace(')', ' ');
        userInput = userInput.replace('-', ' ');
        userInput = userInput.replace('"', ' ');
        userInput = userInput.replace(']', ' ');
        userInput = userInput.replace(']', ' ');

        ArrayList<String> list = new ArrayList<String>();

        if (userInput == null) {
            return null;
        }
        if (userInput.length() == 0) {
            return list;
        }

        if (userInput.contains("  ")) {
            userInput = userInput.replaceAll(" +", " ");
        }

        String s1 = "yeet";


        if (userInput.length() > 0) {
            for (int i = 0; i < userInput.length(); ++i) {
                if (Character.isLetterOrDigit(userInput.charAt(i))) {
                    for (int j = 1; j < userInput.length(); ++j) {
                        while (j < i) {
                            ++j;
                        }
                        if ((userInput.charAt(j) == '?') || (userInput.charAt(j) == '!')
                            || (userInput.charAt(j) == ',') || (userInput.charAt(j) == '.')) {
                            s1 = userInput.substring(i, j);
                            list.add(s1);
                            userInput = userInput.substring(s1.length(), userInput.length());
                            userInput = userInput.trim();
                            break;
                        }

                    }
                } else {
                    continue;
                }
            }
            if (s1.equals("yeet")) {
                for (int i = 0; i <= userInput.length(); ++i) {
                    if (i == userInput.length()) {
                        s1 = userInput.substring(0, i);
                        list.add(s1);
                    }
                }
            }

        }
        return list;

    }

    /**
     * Checks whether any of the phrases in the parameter match a quit word from Config.QUIT_WORDS.
     * Note: complete phrases are matched, not individual words within a phrase.
     * 
     * @param phrases List of user phrases
     * @return true if any phrase matches a quit word, otherwise false
     */
    public static boolean foundQuitWord(ArrayList<String> phrases) {
        for (int i = 0; i < phrases.size(); ++i) {
            for (int j = 0; j < Config.QUIT_WORDS.length; ++j) {

                if (phrases.get(i).equals(Config.QUIT_WORDS[j])) {
                    return true;
                }

            }
        }

        return false;
    }

    /**
     * Iterates through the phrases of the user's input, finding the longest phrase to which to
     * respond. If two phrases are the same length, returns whichever has the lower index in the
     * list. If phrases parameter is null or size 0 then return null.
     * 
     * @param phrases List of user phrases
     * @return the selected phrase
     */
    public static String selectPhrase(ArrayList<String> phrases) {
        if (phrases == null) {
            return null;
        }
        if (phrases.size() == 0) {
            return null;
        }
        String longest = "";
        for (int i = 0; i < phrases.size(); ++i) {
            if (phrases.get(i).length() < longest.length()) {
                continue;
            }
            if (phrases.get(i).length() > longest.length()) {
                longest = phrases.get(i);
            }
            if (phrases.get(i).length() == longest.length()) {
                longest = longest; // longest stays assigned to longest because longest would have
                                   // had a lower index
            }
        }
        return longest;
    }

    /**
     * Looks for a replacement word for the word parameter and if found, returns the replacement
     * word. Otherwise if the word parameter is not found then the word parameter itself is
     * returned. The wordMap parameter contains rows of match and replacement strings. On a row, the
     * element at the 0 index is the word to match and if it matches return the string at index 1 in
     * the same row. Some example word maps that will be passed in are Config.INPUT_WORD_MAP and
     * Config.PRONOUN_MAP.
     * 
     * If word is null return null. If wordMap is null or wordMap length is 0 simply return word
     * parameter. For this implementation it is reasonable to assume that if wordMap length is >= 1
     * then the number of elements in each row is at least 2.
     * 
     * @param word The word to look for in the map
     * @param wordMap The map of words to look in
     * @return the replacement string if the word parameter is found in the wordMap otherwise the
     *         word parameter itself.
     */
    public static String replaceWord(String word, String[][] wordMap) {
        if (word == null) {
            return null;
        }
        if (wordMap == null || wordMap.length == 0) {
            return word;
        }
        for (int i = 0; i < wordMap.length; ++i) {
            if (word == wordMap[i][0]) {
                word = wordMap[i][1];
                return word;
            }

        }
        return word;
    }



    /**
     * Concatenates the elements in words parameter into a string with a single space between each
     * array element. Does not change any of the strings in the words array. There are no leading or
     * trailing spaces in the returned string.
     * 
     * @param words a list of words
     * @return a string containing all the words with a space between each.
     */
    public static String assemblePhrase(String[] words) {
        String allWords = "";
        for (String word : words) {
            allWords += word + " ";

        }
        allWords = allWords.trim();
        return allWords;
    }

    /**
     * Replaces words in phrase parameter if matching words are found in the mapWord parameter. A
     * word at a time from phrase parameter is looked for in wordMap which may result in more than
     * one word. For example: i'm => i am Uses the replaceWord and assemblePhrase methods. Example
     * wordMaps are Config.PRONOUN_MAP and Config.INPUT_WORD_MAP. If wordMap is null then phrase
     * parameter is returned. Note: there will Not be a case where a mapping will itself be a key to
     * another entry. In other words, only one pass through swapWords will ever be necessary.
     * 
     * @param phrase The given phrase which contains words to swap
     * @param wordMap Pairs of corresponding match & replacement words
     * @return The reassembled phrase
     */
    public static String swapWords(String phrase, String[][] wordMap) {

        String[] origArray = new String[separatePhrases(phrase).size()];
        for (int i = 0; i < separatePhrases(phrase).size(); ++i) {
            origArray[i] = separatePhrases(phrase).get(i);
        }

        phrase = assemblePhrase(origArray);

        ArrayList<String> arrayList = new ArrayList<String>();
        String s1 = "";
        if (phrase.length() > 0) {
            for (int i = 0; i < phrase.length(); ++i) {
                if (Character.isLetterOrDigit(phrase.charAt(i))) {
                    for (int j = 1; j < phrase.length(); ++j) {
                        if (phrase.charAt(j) == ' ') {
                            s1 = phrase.substring(0, j);
                            if (s1.contains(" ")) {
                                for (int i1 = 0; i1 < s1.length(); ++i1) {
                                    if (s1.charAt(i1) == ' ') {
                                        s1 = phrase.substring(0, i1);
                                    }
                                }
                            }
                            arrayList.add(s1);
                            phrase = phrase.substring(s1.length(), phrase.length());
                            phrase = phrase.trim();
                            if (!phrase.contains(" ")) {
                                arrayList.add(phrase);
                            }
                        }
                    }

                }
            }


            System.out.println(arrayList);


            if (wordMap == null) {
                return phrase;
            }

            String[] stringArray = new String[arrayList.size()];
            for (int i = 0; i < arrayList.size(); ++i) {
                stringArray[i] = arrayList.get(i);
            }
            for (int i = 0; i < stringArray.length; ++i) {
                for (int j = 0; j < wordMap.length; ++j) {
                    if (stringArray[i].equals(wordMap[j][0])) {
                        stringArray[i] = wordMap[j][1];
                    }
                }
            }
            String phrase2 = assemblePhrase(stringArray);
            return phrase2;
        }
        return null;
    }

    /**
     * This prepares the user input. First, it separates input into phrases (using separatePhrases).
     * If a phrase is a quit word (foundQuitWord) then return null. Otherwise, select a phrase
     * (selectPhrase), swap input words (swapWords with Config.INPUT_WORD_MAP) and return an array
     * with each word its own element in the array.
     * 
     * @param input The input from the user
     * @return words from the selected phrase
     */
    public static String[] prepareInput(String input) {
        ArrayList<String> inputArrayList = new ArrayList<String>();
        inputArrayList = separatePhrases(input);
        if (foundQuitWord(inputArrayList) == true) {
            return null;
        }
        String newInput = selectPhrase(inputArrayList);
        newInput = swapWords(newInput, Config.INPUT_WORD_MAP);
        newInput = swapWords(newInput, Config.PRONOUN_MAP);
        // System.out.print("x" + newInput + "x");
        ArrayList<String> inputArrayList2 = new ArrayList<String>();
        String s1 = "";
        if (newInput.length() > 0) {
            for (int i = 0; i < newInput.length(); ++i) {
                if (Character.isLetterOrDigit(newInput.charAt(i))) {
                    for (int j = 1; j < newInput.length(); ++j) {
                        if (newInput.charAt(j) == ' ') {
                            s1 = newInput.substring(0, j);
                            inputArrayList2.add(s1);
                            newInput = newInput.substring(s1.length(), newInput.length());
                            newInput = newInput.trim();
                            if (!newInput.contains(" ")) {
                                inputArrayList2.add(newInput);
                            }
                        }



                    }

                }
            }
            if (s1.equals("")) {
                for (int i = 0; i <= newInput.length(); ++i) {
                    if (i == newInput.length()) {
                        s1 = newInput.substring(0, i);
                        inputArrayList2.add(s1);
                        // System.out.print(s1);
                    }
                }
            }
        }
        String[] inputArray = new String[inputArrayList2.size()];
        for (int i = 0; i < inputArrayList2.size(); ++i) {
            inputArray[i] = inputArrayList2.get(i);
        }
        return inputArray;
    }

    /**
     * Reads a file that contains keywords and responses. A line contains either a list of keywords
     * or response, any blank lines are ignored. All leading and trailing whitespace on a line is
     * ignored. A keyword line begins with "keywords" with all the following tokens on the line, the
     * keywords. Each line that follows a keyword line that is not blank is a possible response for
     * the keywords. For example (the numbers are for our description purposes here and are not in
     * the file):
     * 
     * 1 keywords computer 2 Do computers worry you? 3 Why do you mention computers? 4 5 keywords i
     * dreamed 6 Really, <3>? 7 Have you ever fantasized <3> while you were awake? 8 9 Have you ever
     * dreamed <3> before?
     *
     * In line 1 is a single keyword "computer" followed by two possible responses on lines 2 and 3.
     * Line 4 and 8 are ignored since they are blank (contain only whitespace). Line 5 begins new
     * keywords that are the words "i" and "dreamed". This keyword list is followed by three
     * possible responses on lines 6, 7 and 9.
     * 
     * The keywords and associated responses are each stored in their own ArrayList. The response
     * table is an ArrayList of the keyword and responses lists. For every keywords list there is an
     * associated response list. They are added in pairs into the list that is returned. There will
     * always be an even number of items in the returned list.
     * 
     * Note that in the event an IOException occurs when trying to read the file then an error
     * message "Error reading <fileName>", where <fileName> is the parameter, is printed and a
     * non-null reference is returned, which may or may not have any elements in it.
     * 
     * @param fileName The name of the file to read
     * @return The response table
     */
    public static ArrayList<ArrayList<String>> loadResponseTable(String fileName) {

        return null;
    }

    /**
     * Checks to see if the keywords match the sentence. In other words, checks to see that all the
     * words in the keyword list are in the sentence and in the same order. If all the keywords
     * match then this method returns an array with the unmatched words before, between and after
     * the keywords. If the keywords do not match then null is returned.
     * 
     * When the phrase contains elements before, between, and after the keywords, each set of the
     * three is returned in its own element String[] keywords = {"i", "dreamed"}; String[] phrase =
     * {"do", "you", "know", that", "i", "have", "dreamed", "of", "being", "an", "astronaut"};
     * 
     * toReturn[0] = "do you know that" toReturn[1] = "have" toReturn[2] = "of being an astronaut"
     * 
     * In an example where there is a single keyword, the resulting List's first element will be the
     * the pre-sequence element and the second element will be everything after the keyword, in the
     * phrase String[] keywords = {"always"}; String[] phrase = {"I", "always", "knew"};
     * 
     * toReturn[0] = "I" toReturn[1] = "knew"
     * 
     * In an example where a keyword is not in the phrase in the correct order, null is returned.
     * String[] keywords = {"computer"}; String[] phrase = {"My","dog", "is", "lost"};
     * 
     * return null
     * 
     * @param keywords The words to match, in order, in the sentence.
     * @param phrase Each word in the sentence.
     * @return The unmatched words before, between and after the keywords or null if the keywords
     *         are not all matched in order in the phrase.
     */
    public static String[] findKeyWordsInPhrase(ArrayList<String> keywords, String[] phrase) {
        // see the algorithm presentation linked in Eliza.pdf.

        return null;
    }

    /**
     * Selects a randomly generated response within the list of possible responses using the
     * provided random number generator where the number generated corresponds to the index of the
     * selected response. Use Random nextInt( responseList.size()) to generate the random number. If
     * responseList is null or 0 length then return null.
     * 
     * @param rand A random number generator.
     * @param responseList A list of responses to choose from.
     * @return A randomly selected response
     */
    public static String selectResponse(Random rand, ArrayList<String> responseList) {
        return null;
    }

    /**
     * This method takes processed user input and forms a response. This looks through the response
     * table in order checking to see if each keyword pattern matches the userWords. The first
     * matching keyword pattern found determines the list of responses to choose from. A keyword
     * pattern matches the userWords, if all the keywords are found, in order, but not necessarily
     * contiguous. This keyword matching is done by findKeyWordsInPhrase method. See the
     * findKeyWordsInPhrase algorithm in the Eliza.pdf.
     * 
     * If no keyword pattern matches then Config.NO_MATCH_RESPONSE is returned. Otherwise one of
     * possible responses for the matched keywords is selected with selectResponse method. The
     * response selected is checked for the replacement symbol <n> where n is 1 to the length of
     * unmatchedWords array returned by findKeyWordsInPhrase. For each replacement symbol the
     * corresponding unmatched words element (index 0 for <1>, 1 for <2> etc.) has its pronouns
     * swapped with swapWords using Config.PRONOUN_MAP and then replaces the replacement symbol in
     * the response.
     * 
     * @param userWords using input after preparing.
     * @param rand A random number generator.
     * @param responseTable A table containing a list of keywords and response pairs.
     * @return The generated response
     */
    public static String prepareResponse(String[] userWords, Random rand,
        ArrayList<ArrayList<String>> responseTable) {

        // Iterate through the response table.
        // The response table has paired rows. The first row is a list of key
        // words, the next a list of corresponding responses. The 3rd row another
        // list of keywords and 4th row the corresponding responses.

        // checks to see if the current keywords match the user's words
        // using findKeyWordsInPhrase.

        // if no keyword pattern was matched, return Config.NO_MATCH_RESPONSE
        // else, select a response using the appropriate list of responses for the keywords

        // Look for <1>, <2> etc in the chosen response. The number starts with 1 and
        // there won't be more than the number of elements in unmatchedWords returned by
        // findKeyWordsInPhrase. Note the number of elements in unmatchedWords will be
        // 1 more than the number of keywords.
        // For each <n> found, find the corresponding unmatchedWords phrase (n-1) and swap
        // its pronoun words (swapWords using Config.PRONOUN_MAP). Then use the
        // result to replace the <n> in the chosen response.

        // in the selected echo, swap pronouns

        // inserts the new phrase with pronouns swapped, into the response

        return null;
    }

    /**
     * Creates a file with the given name, and fills that file line-by-line with the tracked
     * conversation. Every line ends with a newline. Throws an IOException if a writing error
     * occurs.
     * 
     * @param dialog the complete conversation
     * @param fileName The file in which to write the conversation
     * @throws IOException
     */
    public static void saveDialog(ArrayList<String> dialog, String fileName) throws IOException {

    }
}
