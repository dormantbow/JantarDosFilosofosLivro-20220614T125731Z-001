import java.util.concurrent.Semaphore;

/**
 * Autor: Matheus H Moreno da Silva - 12/06/2022
 */


public class JantarDosFilosofos {
    //Numero de Filosofos
    private static final int N= 5;

    public static void main(String[] args){
       
        // Semáforo dos garfos
        Semaphore[] garfo = new Semaphore[N];

        for (int i=0; i< N; i++){
            garfo[i] = new Semaphore(1);
        }

        // Cria os filosofos e inicia cada um executando sua thread
        Filosofos[] filosofo = new Filosofos[N];

        for(int i=0; i< N; i++){
            // filosofo X pega o garfo X e pega o garfo direito
            filosofo[i] = new Filosofos(i, garfo[i], garfo[(i+1) % N]);
            
            new Thread(filosofo[i]).start();
        }
    }
}
