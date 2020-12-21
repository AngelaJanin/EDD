public class Ejercicio{
    public static boolean perteneceAux(Object objeto,Object[] arr, int head){
        if (arr.length == head) return false;  
        if (arr[head].equals(objeto)) {
            return true;
        }
        return perteneceAux(objeto, arr, head+1); 
    }

    public static boolean pertenece(Object objeto,Object[] arr) {
        return perteneceAux(objeto, arr, 0);
    }
    public static void main(String[] args) {
        Object[] arr = new Object[]{1,2,3,4,5};
        Object x = (int)10;  
        System.out.println(pertenece(x, arr));
    }
}


