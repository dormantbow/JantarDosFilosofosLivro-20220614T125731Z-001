import java.util.Random;
import java.util.concurrent.Semaphore;

class Filosofos implements Runnable{
    // usando para variar quanto tempo um filosofo pensa antes de comer
    // e quanto tempo ele come
    private Random number = new Random();

    // Utilitarios dos filosofos
    private final int id;
    private final Semaphore garfo_esquerdo;
    private final Semaphore garfo_direito;
    

    public Filosofos(int id, Semaphore garfo_esquerdo, Semaphore garfo_direito){
        this.id = id;
        this.garfo_esquerdo = garfo_esquerdo;
        this.garfo_direito = garfo_direito;

    }

    // Ciclo infinito de cada filosofo executando em threads separadas
    @Override
    public void run(){
        //Clausula de erro obrigatoria
        try {
            while (true) {
                pensar();
                pegarGarfo_esquerdo();
                pegarGarfo_direito();
                comer();
                devolver_garfo();
            }
        } catch (Exception e) {
            
            System.out.println("Filosofo" + id + " foi interrompido.\n");
        }
    }

    //Modelo de pensamento, definindo um tempo aleatorio para o filosofo executar
    private void pensar() throws InterruptedException{
        System.out.println("Filosofo" + id + " esta Pensando.\n");
        Thread.sleep(number.nextInt(5));
    }

    //Analisa a quantidade de permissões para poder pegar o garfo 
    private void pegarGarfo_esquerdo() throws InterruptedException{
        if(garfo_esquerdo.availablePermits() == 0){
            System.out.println("Filosofo " + id +" esta Esperando o garfo esquerdo\n");
        }
        garfo_esquerdo.acquire();
        System.out.println("Filosofo " + id +" esta Segurando o garfo esquerdo.\n");
    }
    private void pegarGarfo_direito() throws InterruptedException{
        if(garfo_direito.availablePermits()==0){
            System.out.println("Filosofo " + id +" esta Esperando o garfo direito.\n");
       }
       garfo_direito.acquire();
       System.out.println("Filosofo " + id + " esta Segurando o garfo esquerdo.\n");
    }
     int comeu =0;
    private void comer() throws InterruptedException{
        
        System.out.println("Filosofo "+ id + " esta COMENDO.\n");
        Thread.sleep(number.nextInt(5));
       // comeu = comeu +1;
       // System.out.println("O filosofo "+id+" comeu : "+comeu);
    }
    //Libera os garfos
    private void devolver_garfo(){
        garfo_esquerdo.release();
        garfo_direito.release();
        System.out.println("Filosofo " + id +" soltou os garfos.\n");
}
}