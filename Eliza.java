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
// Persons: Gia-Phong Nguyen
// Online Sources:
//////////////////// https://docs.oracle.com/javase/8/docs/api/java/lang/Character.html#isLetterOrDigit-char-
//////////////////// https://docs.oracle.com/javase/8/docs/api/java/lang/String.html
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * The Eliza class holds the user input and response formation for a system that
 * collects user input and responds appropriately. Eliza is based off of a
 * computer program written at MIT in the 1960's by Joseph Weizenbaum. Eliza
 * uses keyword matching to respond to users in a way that displays interest in
 * the users and continues the conversation until instructed otherwise.
 */
public class Eliza {

    /*
     * This method does input and output with the user. It calls supporting methods
     * to read and write files and process each user input.
     * 
     * @param args (unused)
     */
    public static void main(String[] args) {
        
    	
        //Milestone 2
        //create a scanner for reading user input and a random number
        //generator with Config.SEED as the seed
        Scanner scnr = new Scanner(System.in);
        


        //Milestone 3
        //How the program starts depends on the command-line arguments.
        // Command-line arguments can be names of therapists for example:
        //   Eliza Joe Laura
        // If no command-line arguments then the therapists name is Eliza
        // and this reads a file with that name and the Config.RESPONSE_FILE_EXTENSION.
        // Example filename: Eliza.rsp
        // If only one command-line argument, then read the responses from 
        // the corresponding file with Config.RESPONSE_FILE_EXTENSION. 
        // If there is more than one command-line argument then offer them
        // as a list of people to talk with. For the three therapists above the prompt is
        //   "Would you like to speak with Eliza, Joe, or Laura?"
        // When a user types a name then read the responses from the file which
        // is the selected name and Config.RESPONSE_FILE_EXTENSION extension.
        // Whatever name the user types has the extension appended and
        // is read using loadResponseTable. If loadResponseTable can't load
        // the file then it will report an error.

        
        //Milestone 2
        //name prompt
        System.out.println("Hi I'm Eliza, what is your name?");
        String userName =scnr.nextLine();
        
        //Milestone 2
        //welcome prompt
        System.out.println("Nice to meet you " + userName + ". What is on your mind?");
        
        //Milestone 2
        //begin conversation loop
        while (scnr.hasNextLine()) {
            //Milestone 2
            //obtain user input
            String userInput = scnr.nextLine();

            	
            
            
            //Milestone 2
            //prepareInput
            String [] inputArray = prepareInput(userInput);
            	//System.out.println(Arrays.toString(inputArray));
            ArrayList<String> inputArrayList = new ArrayList<String>();
            ArrayList<ArrayList<String>> responseTable = loadResponseTable("Eliza.rsp");
            Random rand = new Random(Config.SEED);
            for (int i = 0; i < inputArray.length; ++i) {
                inputArrayList.add(inputArray[i]);
            }
            	//System.out.println(inputArrayList);
                //Milestone 3
                //if no quit words then prepareResponse
                if (foundQuitWord(inputArrayList) == false) {
                    String response = prepareResponse(inputArray, rand, responseTable);
                    //System.out.println(responseTable.get(130));
                    System.out.println(response);
                }

            //Milestone 2
            //end loop if quit word
                else {
                    System.out.println("Goodbye " + userName + ".");
                    break; }

        //Milestone 2
        //ending prompt
        
        //Milestone 3
        //Save all conversation (user and system responses) starting
        //with this program saying "Hi I'm..." and concludes with
        //"Goodbye <name>.".
        //Always prompt the user to see if they would like to save a 
        //record of the conversation.  If the user enters a y or Y as the 
        //first non-whitespace character then prompt for filename and save, 
        //otherwise don't save dialog.  After successfully saving a dialog 
        //print the message "Thanks again for talking! Our conversation is saved in: <filename>".
        //If saveDialog throws an IOException then catch it, print out the error:
        //  "Unable to save conversation to: " <name of file> 
        //Repeat the code prompting the user if they want to save the dialog.

        }  
    }
    

    /**
     * This method processes the user input, returning an ArrayList containing
     * Strings, where each String is a phrase from the user's input. This is done by
     * removing leading and trailing whitespace, making the user's input all lower
     * case, then going through each character of the user's input. When going
     * through each character this keeps all digits, alphabetic characters and '
     * (single quote). The characters ? ! , . signal the end of a phrase, and
     * possibly the beginning of the next phrase, but are not included in the
     * result. All other characters such as ( ) - " ] etc. should be replaced with a
     * space. This method makes sure that every phrase has some visible characters
     * but no leading or trailing whitespace and only a single space between words
     * of a phrase. If userInput is null then return null, if no characters then
     * return a 0 length list, otherwise return a list of phrases. Empty phrases and
     * phrases with just invalid/whitespace characters should NOT be added to the
     * list.
     * 
     * Example userInput: "Hi, I am! a big-fun robot!!!" Example returned: "hi", "i
     * am", "a big fun robot"
     * 
     * @param userInput text the user typed
     * @return the phrases from the user's input
     */
    public static ArrayList<String> separatePhrases (String userInput) {
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
                        if ((userInput.charAt(j) == '?') || (userInput.charAt(j) == '!') || (userInput.charAt(j) == ',')
                                || (userInput.charAt(j) == '.')) {
                            if (i > 0) {
                                s1 = userInput.substring(i - 1, j);
                            } else {
                                s1 = userInput.substring(i, j);
                            }
                            // System.out.print(s1);
                            list.add(s1.trim());
                            userInput = userInput.substring(s1.length() - 1, userInput.length());
                            if (userInput.charAt(0) == '?' || (userInput.charAt(0) == '!')
                                    || (userInput.charAt(0) == ',') || (userInput.charAt(0) == '.')) {
                                userInput = userInput.substring(1).trim();
                                // System.out.print(userInput);
                            }
                            break;
                        } else {
                            if (!userInput.contains("?") && !userInput.contains("!") && !userInput.contains(",")
                                    && !userInput.contains(".")) {
                                s1 = userInput.trim();
                                list.add(s1);
                                j = userInput.length() + 1;
                                i = userInput.length() + 1;
                            }

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
     * Checks whether any of the phrases in the parameter match a quit word from
     * Config.QUIT_WORDS. Note: complete phrases are matched, not individual words
     * within a phrase.
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
     * Iterates through the phrases of the user's input, finding the longest phrase
     * to which to respond. If two phrases are the same length, returns whichever
     * has the lower index in the list. If phrases parameter is null or size 0 then
     * return null.
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
     * Looks for a replacement word for the word parameter and if found, returns the
     * replacement word. Otherwise if the word parameter is not found then the word
     * parameter itself is returned. The wordMap parameter contains rows of match
     * and replacement strings. On a row, the element at the 0 index is the word to
     * match and if it matches return the string at index 1 in the same row. Some
     * example word maps that will be passed in are Config.INPUT_WORD_MAP and
     * Config.PRONOUN_MAP.
     * 
     * If word is null return null. If wordMap is null or wordMap length is 0 simply
     * return word parameter. For this implementation it is reasonable to assume
     * that if wordMap length is >= 1 then the number of elements in each row is at
     * least 2.
     * 
     * @param word    The word to look for in the map
     * @param wordMap The map of words to look in
     * @return the replacement string if the word parameter is found in the wordMap
     *         otherwise the word parameter itself.
     */
    public static String replaceWord(String word, String[][] wordMap) {
        if (word == null) {
            return null;
        }
        if (wordMap == null || wordMap.length == 0) {
            return word;
        }
        for (int i = 0; i < wordMap.length; ++i) {
            if (word.equals(wordMap[i][0])) {
                word = wordMap[i][1];
                return word;
            }

        }
        return word;
    }

    /**
     * Concatenates the elements in words parameter into a string with a single
     * space between each array element. Does not change any of the strings in the
     * words array. There are no leading or trailing spaces in the returned string.
     * 
     * @param words a list of words
     * @return a string containing all the words with a space between each.
     */
    public static String assemblePhrase(String[] words) {
        String allWords = "";
        for (int i = 0; i < words.length; ++i) {
            allWords = allWords.concat(words[i]).concat(" ");

        }
        allWords = allWords.trim();
        return allWords;
    }

    /**
     * Replaces words in phrase parameter if matching words are found in the mapWord
     * parameter. A word at a time from phrase parameter is looked for in wordMap
     * which may result in more than one word. For example: i'm => i am Uses the
     * replaceWord and assemblePhrase methods. Example wordMaps are
     * Config.PRONOUN_MAP and Config.INPUT_WORD_MAP. If wordMap is null then phrase
     * parameter is returned. Note: there will Not be a case where a mapping will
     * itself be a key to another entry. In other words, only one pass through
     * swapWords will ever be necessary.
     * 
     * @param phrase  The given phrase which contains words to swap
     * @param wordMap Pairs of corresponding match & replacement words
     * @return The reassembled phrase
     */
    public static String swapWords(String phrase, String[][] wordMap) {
        //System.out.println("x" + phrase + "x");
        if (wordMap == null) {
            return phrase;
        }
        if (phrase == null) {
            phrase = "";
            return phrase;
        }
        String[] words = phrase.split(" ");
        ArrayList<String> origArrayList = new ArrayList<String>();

        for (int i = 0; i < wordMap.length; ++i) {
            
        for (int j = 0; j < words.length; ++j) {
            if (words[j].equals(wordMap[i][0])) {
                words[j] = replaceWord(words[j], wordMap);
            }
        }
            
        }
        
        
        String newPhrase = assemblePhrase(words);
        return newPhrase;
    }

    /**
     * This prepares the user input. First, it separates input into phrases (using
     * separatePhrases). If a phrase is a quit word (foundQuitWord) then return
     * null. Otherwise, select a phrase (selectPhrase), swap input words (swapWords
     * with Config.INPUT_WORD_MAP) and return an array with each word its own
     * element in the array.
     * 
     * @param input The input from the user
     * @return words from the selected phrase
     */
    public static String[] prepareInput(String input) {
        ArrayList<String> arrayList = separatePhrases(input);
        // System.out.println(arrayList);
        // String phrase = String.join(" +", separatePhrases(input));
        if (foundQuitWord(arrayList) == true) {
            return null;
        }
        
        // for (int i = 0; i < phraseArray.length; ++i) {
        // arrayList.add(phraseArray[i]);
        // }

        String newPhrase = "";
        newPhrase = selectPhrase(arrayList);
        // System.out.println(newPhrase);
        if (swapWords(newPhrase, Config.INPUT_WORD_MAP) == "") {
            String[] phraseArray2 = {""};
            ArrayList<String> newArrayList = new ArrayList<String>();
            for (int i = 0; i < phraseArray2.length; ++i) {
                newArrayList.add(phraseArray2[i]);
            }
            return phraseArray2;
         }
        if (swapWords(newPhrase, Config.INPUT_WORD_MAP) != null || swapWords(newPhrase, Config.INPUT_WORD_MAP) != "") {
            String [] phraseArray = swapWords(newPhrase, Config.INPUT_WORD_MAP).split(" ");
            return phraseArray;
        }
        // System.out.println(Arrays.toString(phraseArray));

        return null;
    }

    /**
     * Reads a file that contains keywords and responses. A line contains either a
     * list of keywords or response, any blank lines are ignored. All leading and
     * trailing whitespace on a line is ignored. A keyword line begins with
     * "keywords" with all the following tokens on the line, the keywords. Each line
     * that follows a keyword line that is not blank is a possible response for the
     * keywords. For example (the numbers are for our description purposes here and
     * are not in the file):
     * 
     * 1 keywords computer 2 Do computers worry you? 3 Why do you mention computers?
     * 4 5 keywords i dreamed 6 Really, <3>? 7 Have you ever fantasized <3> while
     * you were awake? 8 9 Have you ever dreamed <3> before?
     *
     * In line 1 is a single keyword "computer" followed by two possible responses
     * on lines 2 and 3. Line 4 and 8 are ignored since they are blank (contain only
     * whitespace). Line 5 begins new keywords that are the words "i" and "dreamed".
     * This keyword list is followed by three possible responses on lines 6, 7 and
     * 9.
     * 
     * The keywords and associated responses are each stored in their own ArrayList.
     * The response table is an ArrayList of the keyword and responses lists. For
     * every keywords list there is an associated response list. They are added in
     * pairs into the list that is returned. There will always be an even number of
     * items in the returned list.
     * 
     * Note that in the event an IOException occurs when trying to read the file
     * then an error message "Error reading <fileName>", where <fileName> is the
     * parameter, is printed and a non-null reference is returned, which may or may
     * not have any elements in it.
     * 
     * @param fileName The name of the file to read
     * @return The response table
     * @throws FileNotFoundException
     */
    public static ArrayList<ArrayList<String>> loadResponseTable(String fileName) {
        ArrayList<ArrayList<String>> responseTable = new ArrayList<ArrayList<String>>();
        File file = new File(fileName);
        Scanner counterScnr;
        try {
            counterScnr = new Scanner(file);
            // System.out.print("File has been opened");

        } catch (IOException e) {
            System.out.print("Error reading " + fileName);
            return responseTable;
        }

        int counter = 0;
        while (counterScnr.hasNextLine()) {
            ++counter;
            counterScnr.nextLine();
        }

        Scanner keywordScnr;
        try {
            keywordScnr = new Scanner(file);
            // System.out.print("File has been opened");

        } catch (IOException e) {
            System.out.print("Error reading " + fileName);
            return responseTable;
        }
        ArrayList<ArrayList<String>> keywordArrayList = new ArrayList<ArrayList<String>>();
        ArrayList<String> keywords = new ArrayList<String>();
        String dummyString = "";
        while (keywordScnr.hasNextLine()) {
            dummyString = keywordScnr.nextLine();
            keywords = new ArrayList<String>();
            if (dummyString.contains("keyword")) {
                dummyString = dummyString.substring(9);
                while (dummyString.contains(" ")) {
                   int k = dummyString.indexOf(" ");
                   String yeet = dummyString.substring(0, k);
                   keywords.add(yeet.trim());
                   dummyString = dummyString.substring(k).trim();
                }
                keywords.add(dummyString);
                keywordArrayList.add(keywords);
            }
        } // System.out.print(keywordArrayList);
        Scanner responseScnr;
        try {
            responseScnr = new Scanner(file);
            // System.out.print("File has been opened");

        } catch (IOException e) {
            System.out.print("Error reading " + fileName);
            return responseTable;
        }
        ArrayList<ArrayList<String>> responseArrayList = new ArrayList<ArrayList<String>>();
        ArrayList<String> responses = new ArrayList<String>();
        String dummyString2 = "";
        int i = 0;
        while (responseScnr.hasNextLine()) {
            dummyString2 = responseScnr.nextLine();
            ++i;
            responses = new ArrayList<String>();
            while (!dummyString2.contains("keyword") && i <= counter) {
                if (dummyString2.length() != 0) {
                    responses.add(dummyString2);
                }
                if (i < counter) {
                    dummyString2 = responseScnr.nextLine();
                }
                ++i;
            }
            if (responses.size() != 0) {
                responseArrayList.add(responses);
            }
        }
        for (int y = 0; y < responseArrayList.size(); ++y) {
            responseTable.add(keywordArrayList.get(y));
            responseTable.add(responseArrayList.get(y));
        }
        // for (int z = 0; z < responseTable.size(); ++z) {
        // System.out.println(responseTable.get(z));
        // }

        return responseTable;
    }

    /**
     * Checks to see if the keywords match the sentence. In other words, checks to
     * see that all the words in the keyword list are in the sentence and in the
     * same order. If all the keywords match then this method returns an array with
     * the unmatched words before, between and after the keywords. If the keywords
     * do not match then null is returned.
     * 
     * When the phrase contains elements before, between, and after the keywords,
     * each set of the three is returned in its own element String[] keywords =
     * {"i", "dreamed"}; String[] phrase = {"do", "you", "know", that", "i", "have",
     * "dreamed", "of", "being", "an", "astronaut"};
     * 
     * toReturn[0] = "do you know that" toReturn[1] = "have" toReturn[2] = "of being
     * an astronaut"
     * 
     * In an example where there is a single keyword, the resulting List's first
     * element will be the the pre-sequence element and the second element will be
     * everything after the keyword, in the phrase String[] keywords = {"always"};
     * String[] phrase = {"I", "always", "knew"};
     * 
     * toReturn[0] = "I" toReturn[1] = "knew"
     * 
     * In an example where a keyword is not in the phrase in the correct order, null
     * is returned. String[] keywords = {"computer"}; String[] phrase = {"My","dog",
     * "is", "lost"};
     * 
     * return null
     * 
     * @param keywords The words to match, in order, in the sentence.
     * @param phrase   Each word in the sentence.
     * @return The unmatched words before, between and after the keywords or null if
     *         the keywords are not all matched in order in the phrase.
     */
    public static String[] findKeyWordsInPhrase(ArrayList<String> keywords, String[] phrase) {
        // see the algorithm presentation linked in Eliza.pdf.
        //the following code checks to make sure the keywords are in the correct order.
        for (int i = 0; i < keywords.size(); ++i) {
            if (!Arrays.asList(phrase).contains(keywords.get(i))) {
                return null;
            }
        }
        
        if (keywords.size() > 1) {
        int k = 0;
            for (int j = 0; j < phrase.length; ++j) {
                if (keywords.get(0) == phrase[j]) {
                    k = j;
            }
        }
        int l = 0;
            for (int j = 0; j < phrase.length; ++j) {
                if (keywords.get(1) == phrase[j]) {
                    l = j;
                }
            }
        if (l < k) {
            return null;
        } }
            //System.out.println(Arrays.toString(phrase));
            //System.out.println(keywords);
        ArrayList<String> unmatched = new ArrayList<String>();
        String s1 = "";
        for (int i = 0; i < keywords.size(); ++i) {
            for (int j = 0; j < phrase.length; ++j) {
               // System.out.println(phrase[j]);
               // System.out.println(keywords.get(i));
                if (phrase[j].equals(keywords.get(i))) {
                    //System.out.print(phrase[j]);
                    if (keywords.size()-1 > i) {
                    ++i;}
                    if (j == 0) {
                        unmatched.add(s1.trim());
                    } else {
                        unmatched.add(s1.trim());
                        s1 = "";
                        if (j == phrase.length - 1) {
                            unmatched.add(s1.trim());
                        }
                    }
                } else {
                    s1 += phrase[j] + " ";
                    if (i == keywords.size() - 1 && j == phrase.length - 1) {
                        unmatched.add(s1.trim());
                    }
                }
            }
        }
        // System.out.print(unmatched);
        String[] toReturn = new String[unmatched.size()];
        for (int i = 0; i < unmatched.size(); ++i) {
            toReturn[i] = unmatched.get(i);
        }
        
        //System.out.print(Arrays.toString(toReturn));
        return toReturn;
    }


    /**
     * Selects a randomly generated response within the list of possible responses
     * using the provided random number generator where the number generated
     * corresponds to the index of the selected response. Use Random nextInt(
     * responseList.size()) to generate the random number. If responseList is null
     * or 0 length then return null.
     * 
     * @param rand         A random number generator.
     * @param responseList A list of responses to choose from.
     * @return A randomly selected response
     */
    public static String selectResponse(Random rand, ArrayList<String> responseList) {
        if (responseList == null || responseList.size() == 0) {
        return null;
    }
        
    
    int randNum = rand.nextInt(responseList.size());
    String response = responseList.get(randNum);
    //System.out.print(response);
        return response;
    }

    /**
     * This method takes processed user input and forms a response. This looks
     * through the response table in order checking to see if each keyword pattern
     * matches the userWords. The first matching keyword pattern found determines
     * the list of responses to choose from. A keyword pattern matches the
     * userWords, if all the keywords are found, in order, but not necessarily
     * contiguous. This keyword matching is done by findKeyWordsInPhrase method. See
     * the findKeyWordsInPhrase algorithm in the Eliza.pdf.
     * 
     * If no keyword pattern matches then Config.NO_MATCH_RESPONSE is returned.
     * Otherwise one of possible responses for the matched keywords is selected with
     * selectResponse method. The response selected is checked for the replacement
     * symbol <n> where n is 1 to the length of unmatchedWords array returned by
     * findKeyWordsInPhrase. For each replacement symbol the corresponding unmatched
     * words element (index 0 for <1>, 1 for <2> etc.) has its pronouns swapped with
     * swapWords using Config.PRONOUN_MAP and then replaces the replacement symbol
     * in the response.
     * 
     * @param userWords     using input after preparing.
     * @param rand          A random number generator.
     * @param responseTable A table containing a list of keywords and response
     *                      pairs.
     * @return The generated response
     */
    public static String prepareResponse(String[] userWords, Random rand, ArrayList<ArrayList<String>> responseTable) {

        // Iterate through the response table.
        //System.out.println(Arrays.toString(userWords));
        
        String[] stringArray = null;
        int i = 0;
        //System.out.print(responseTable);
        for (i = 0; i < responseTable.size(); i += 2) {
        // The response table has paired rows. The first row is a list of key
        // words, the next a list of corresponding responses. The 3rd row another
        // list of keywords and 4th row the corresponding responses.
       ArrayList <String> yeet = new ArrayList<String>();

        stringArray = findKeyWordsInPhrase(responseTable.get(i), userWords);
            if (stringArray != null) {
            	if (responseTable.size() > 1 && responseTable.get(i).size() > 1) {
            		if (Arrays.asList(userWords).indexOf(responseTable.get(i).get(0)) > Arrays.asList(userWords).indexOf(responseTable.get(i).get(1))) {
            			continue;
            		}
            	}
             break; }
        } 
        //System.out.print(Arrays.toString(stringArray));
        if (stringArray == null) {
            return Config.NO_MATCH_RESPONSE; }
        // checks to see if the current keywords match the user's words
        // using findKeyWordsInPhrase.
        
        // if no keyword pattern was matched, return Config.NO_MATCH_RESPONSE
        // else, select a response using the appropriate list of responses for the
        // keywords
        
        String response = selectResponse(rand, responseTable.get(i + 1));
        //System.out.print(response);

       
         //System.out.println(response);
        // Look for <1>, <2> etc in the chosen response. The number starts with 1 and
        // there won't be more than the number of elements in unmatchedWords returned by
        // findKeyWordsInPhrase. Note the number of elements in unmatchedWords will be
        // 1 more than the number of keywords.
        // For each <n> found, find the corresponding unmatchedWords phrase (n-1) and
        // swap
        // its pronoun words (swapWords using Config.PRONOUN_MAP). Then use the
        // result to replace the <n> in the chosen response.
        
        int num = stringArray.length;
        //System.out.println(num);
        for (int n = 1; n <= num; ++n) {
             //System.out.print(n);
            if (response.contains("<" + n + ">")) {
                //System.out.println(stringArray[n-1]);
                String yeet = swapWords(stringArray[n - 1], Config.PRONOUN_MAP);
                // System.out.print("l");
                response = response.replaceAll("<" + n + ">", yeet);
            }
        }
        
//System.out.print(response);

        // in the selected echo, swap pronouns

        // inserts the new phrase with pronouns swapped, into the response
        return response;
         
    }

    /**
     * Creates a file with the given name, and fills that file line-by-line with the
     * tracked conversation. Every line ends with a newline. Throws an IOException
     * if a writing error occurs.
     * 
     * @param dialog   the complete conversation
     * @param fileName The file in which to write the conversation
     * @throws IOException
     */
    public static void saveDialog(ArrayList<String> dialog, String fileName) throws IOException {

        try {
            File file = new File(fileName);
            boolean boo = file.createNewFile();
            if (boo) {
                System.out.println("File open.");
            } else {
                System.out.println("File already exists.");
            }
            PrintWriter writer = new PrintWriter(fileName);
            for (int i = 0; i < dialog.size(); ++i) {
                writer.println(dialog.get(i));
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("File not opened.");
        }
    
        
    }
    
}