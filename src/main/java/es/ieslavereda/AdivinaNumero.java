package es.ieslavereda;

import java.util.ArrayList;
import java.util.List;

public class AdivinaNumero {
    private static final int HILOS = 10;

    public static void main(String[] args) {

        NumeroOculto no = new NumeroOculto();

        List<Thread> hilos = new ArrayList<>();

        for (int i=0;i<HILOS;i++)
            hilos.add(new Thread(new Hilo(no,i)));

        for (Thread t : hilos)
            t.start();


    }


    static class Hilo implements Runnable{
        private NumeroOculto no;
        private int id;

        public Hilo(NumeroOculto no, int id) {
            this.no = no;
            this.id = id;
        }


        @Override
        public void run() {

            int resultado;
            int num;

            do{
                num = (int)(Math.random()*100001);

                synchronized (no){
                    resultado = no.propuestaNumero(num);
                }

            }while (resultado==0);

            if(resultado==1)
                System.out.println("El hilo " + id +" ha acertado.");
            else
                System.out.println("He perdido");

        }
    }


    static class NumeroOculto {

        private int value;
        private boolean acertado;

        public NumeroOculto(){
            value = (int)(Math.random()*100001);
        }

        public int propuestaNumero(int num){
            if(acertado) return -1;

            if(num==value){
                acertado=true;
                return 1;
            }

            return 0;
        }

    }

}
