package es.ieslavereda;

import java.util.ArrayList;
import java.util.List;

public class Ejemplo1 {



    private static final int HILOS = 10;
    private static final int TOTAL = 1000000;

    public static void main(String[] args) throws InterruptedException {
        Contador contador = new Contador();

        List<Thread> threadList = new ArrayList<>();
        for(int i=0;i<HILOS;i++)
            threadList.add(new Thread(new Hilo(contador,TOTAL/HILOS)));

        for(Thread t : threadList) {
            t.start();
        }

        for(Thread t : threadList)
            t.join();

        System.out.println(contador.valor);

    }

    static class Hilo implements Runnable{
        private Contador contador;
        private int cantidad;
        public Hilo(Contador contador, int cantidad) {
            this.contador = contador;
            this.cantidad = cantidad;
        }

        @Override
        public void run() {
            for (int i=0;i<cantidad;i++)
                contador.incrementar();
        }
    }

    static class Contador {
        private int valor;
        synchronized public void incrementar(){

                valor++;

        }
    }
}
