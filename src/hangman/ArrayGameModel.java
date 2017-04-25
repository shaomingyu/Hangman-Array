package hangman;

/**
* The Array implementation of the GameModel interface.
*/
public class ArrayGameModel implements GameModel {

    /** The acceptable characters. */
    private static final String ALPHABET="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    /** hung state */
    private int state;
    private int guessCounter = 0;
    private String word;
    private char[] prevGuesses = new char [ALPHABET.length()];
    private char[] boardState;
    /**
        * Creates a new ArrayGameModel object.
        * 
        * guessWord the word to guess
        */

    public ArrayGameModel(String guessWord) {
    	word = guessWord; 
    	boardState = new char[guessWord.length()];
    	for(int i = 0; i < boardState.length; i++) {
    		boardState[i] = '_';
    	}
    	state = 0;
    }

    public boolean isPriorGuess(char guess) {
    	for(int i = 0; i < prevGuesses.length; i++) {
    		if(prevGuesses[i] == guess) {
    			return true;
    		}
    	}
    	return false;
    }

    public int numberOfGuesses() {
        return guessCounter;
    }

    public boolean isCorrectGuess(char guess) {
    	return word.contains(String.valueOf(guess));
    }

    public boolean doMove(char guess) { 
    	if(isPriorGuess(guess)) {
        	return false;
        }
        else if(!isCorrectGuess(guess))  {
        	state++;
        	prevGuesses[guessCounter] = guess;
        	guessCounter++;
        	return false;
        }
        else {
        	prevGuesses[guessCounter] = guess;
        	guessCounter++;
        	int currentIndex = 0;
        	while(word.indexOf(guess, currentIndex) != -1) {
        		boardState[word.indexOf(guess, currentIndex)] = guess;
        		currentIndex = word.indexOf(guess, currentIndex) + 1;
        	}
        	return true;
        }
        
     }

    public boolean inWinningState() {
    	return String.valueOf(boardState).equals(word);
    }

    public boolean inLosingState() {
    	return (!String.valueOf(boardState).equals(word) && state == 10);
    }

    public String toString() {
    	String str = "";
    	for(int i = 0; i < boardState.length; i++) {
    		str += String.valueOf(boardState[i]);
    		if(i != boardState.length - 1) {
        		str += " ";	
    		}
    	}
    	return str;
    }  

    public String previousGuessString() {
    	if (guessCounter == 0) {
    		return "[]";
    	}
    	String str = "[";
        for(int i = 0; i < guessCounter; i++) {
        	str += prevGuesses[i];
        	if(i < guessCounter - 1) {
        		str += ", ";
        	}
        	else str += "]";
        }
        return str;
     }

    public int getState() {
        return state;
    }
    

    public String getWord() {
    	return word;
    }
}
