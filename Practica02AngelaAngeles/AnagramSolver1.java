import java.util.*;

 class AnagramSolver{
    public static void anagramming(char [] word, int m, int n){ 
        if (m == n){
            System.out.println(word);
        }else{
            for(int i = m; i <= n; i++){
                word = swap(word,i,m);
                anagramming(word, m+1, n);
                word = swap(word, m, i);
            }
        }   
    }

    public static char [] swap(char [] word, int i, int j){ 
        char aux; 
        char[] swaped = word; 
        aux = swaped[i]; 
        swaped[i] = swaped[j]; 
        swaped[j] = aux; 
        return swaped; 
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        System.out.print("Ingresa una palabra y encontremos sus anagramas: ");  
        String str = sc.nextLine();              
        char[] inputArray = str.toCharArray();
        anagramming(inputArray, 0, inputArray.length -1);
    }
}