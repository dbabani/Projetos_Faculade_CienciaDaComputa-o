public class App {
    public static void main(String[] args){

        for(int i = 0; i<=100; i+=5){
            int aux = f1(i);

            System.out.println(i +" ; "+ aux);
        }
    }

    // Algoritmo 1 Check
    public static int f1(int n){
        int i, j, k, res = 0;
        int cont_op = 0;
        for( i = 1; i <= n+1; i += 1 ){
           for( j = 1; j <= i*i; j += i+1 ){
               for( k = i/2; k <= n+j; k += 2 ) {
                 res = res + n-1;
                 cont_op++;
                }
            }
        }
        return cont_op;
    }


    // Algoritmo 2 Check
    public static int f2( int n ) {
        int i, j, k, res = 0;
        int cont_op = 0;
        for( i = n; i <= n; i += i/2+1 ){
            for( j = i/2; j <= i*i; j += i+1 ){
                for( k = n; k <= 2*n; k += i+1 ) {
                    res = res + n;
                    cont_op++;
                }
            }
        }
        return cont_op;
    }

    // Algoritmo 3 check
    public static int f3( int n ) {
        int i, j, k, res = 0;
        int cont_op = 0;
        for(i = 1;i <= n*n; i+= 2){
            for(j= i/2;j <= 2*i; j+= i/2+1){
                for(k = j+1;k<= n+j;k += k/2+1){
                    res = res +  Math.abs(j-i);
                    cont_op++;
                }
            }
        }
        return cont_op;
    }

    // Algoritmo 4 check
    public static int f4( int n ) {
        int i, j, k, res = 0;
        int cont_op = 0;
        for( i = n; i <= n*n; i += 2 ){
            for( j = n+1; j <= n*n; j += 2 ){
                 for( k = j; k <= 2*j; k += 2 ) {
        res = res + 1;
        cont_op++;
    
                 }
            }
        
        }
        return cont_op;
        }
 
    // Algoritmo 5 check
    public static  int f5( int n ) {
        int i, j, k, res = 0;
        int cont_op = 0;
        for( i = 1; i <= n*n; i += 1 ){
        for( j = 1; j <= i; j += 2 ){
        for( k = n+1; k <= 2*i; k += i*j ) {
        res = res + k+1;
        cont_op++;
        }
    }
}
        return cont_op;
        }
}