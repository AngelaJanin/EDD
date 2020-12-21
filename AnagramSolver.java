import java.util.*;

/**
* Clase que muestra los anagramas de una palabra.
* @author Ángeles Martínez Ángela Janín. 
* No. cuenta: 314201009
* @version 1.0 Octubre 2020.
*/

public class AnagramSolver {

    public static int size = 0;
    public static char [] word = new char[size];  

    /**
	* This method rotates the last r chars in the array to the left by one position
	* @param r the r last chars to rotate to the left
	*/

    private static void rotate(int rotator){
        int pos = 0;
        char [] sub = Arrays.copyOfRange(word, size-rotator, size);
        char head = sub[0];

        for(int i = 0; i < rotator; i++){
            int next = Math.floorMod((i+1), rotator);
            sub[i] = sub[next]; 
        }

        sub[rotator-1] = head;

        for(int i = size-rotator; i <= size-1; i++) {
            word[i] = sub[pos];
            pos += 1;
        }
    }

    /**
    * Iterates the char array, position by position, concatenating 
    * the values until get the complete word into String
	*/
    private static void displayWord(){
        String displayed = "";
        for(int i = 0; i < size; i++){
            displayed = displayed + word[i];
        }
        System.out.println(displayed);
    }

    /**
    * This method creates all the anagrams of a word by rotating the last 
    * newSize positions through recursive calls ans iterations
    * @param newSize for an initial value, represents the length of the char array
	*/
    public static void doAnagram(int newSize){
        if(newSize == 1){
            return;
        }
        for (int i = 0; i < newSize; i++){
            doAnagram(newSize-1);
            if(newSize == 2){
                displayWord();
            }
            rotate(newSize);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        System.out.println("Ingresa una palabra:");  
        String str = sc.nextLine();              
        char[] inputArray = str.toCharArray();
        AnagramSolver.word = inputArray;
        AnagramSolver.size = AnagramSolver.word.length;
        System.out.println("\nLos anagramas de '"+ str +"' son los siguientes:");  
        doAnagram(AnagramSolver.size);
    }
}
