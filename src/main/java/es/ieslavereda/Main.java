package es.ieslavereda;

public class Main {

    //static Integer value = 0;
    static MiContador contador = new MiContador();
    static final int iteraciones=1000000;

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(()->{
            for(int i=0;i<iteraciones;i++)
                synchronized (contador) {
                    contador.value++;
                }
        });
        Thread t2 = new Thread(()->{
            for(int i=0;i<iteraciones;i++)
                synchronized (contador) {
                    contador.value--;
                }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(contador.value);
    }




    static class MiContador {
        private int value;
    }
}