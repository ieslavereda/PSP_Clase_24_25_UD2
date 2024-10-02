package es.ieslavereda.ejercicio6;

import java.util.ArrayList;
import java.util.List;

public class AdivinaNumero2 {

    private static final int PARTIDAS = 100;
    private static final int HILOS = 10;

    public static void main(String[] args) {

        NumeroOculto no = new NumeroOculto();

        List<Thread> hilos = new ArrayList<>();

        for (int i = 0; i < HILOS; i++)
            hilos.add(new Thread(new Hilo(no, i)));

        for (Thread t : hilos)
            t.start();


    }

    public static int obtenerAleatorio() {
        return (int) (Math.random() * 1000);
    }


    static class Hilo implements Runnable {
        private NumeroOculto no;
        private int id;
        private int partida;
        private List<Integer> partidasGanadas;

        public Hilo(NumeroOculto no, int id) {
            this.no = no;
            this.id = id;
            partida = 0;
            partidasGanadas= new ArrayList<>();
        }


        @Override
        public void run() {

            int resultado;
            int num;

            do {
                do {
                    num = (int) (Math.random() * 100001);

                    synchronized (no) {
                        resultado = no.propuestaNumero(num, partida);
                        partida = no.getPartida();
                    }

                } while (resultado == 0);

                if (resultado == 1) {
                    System.out.println("El hilo " + id + " ha acertado.");
                    partidasGanadas.add(partida);
                    partida++;
                }else
                    System.out.println("He perdido");

            } while (partida < PARTIDAS);

            System.out.println("El hilo " + id+" ha ganado "+partidasGanadas.size()+" y son: " + partidasGanadas);

        }
    }


    static class NumeroOculto {

        private int[] value;
        private int partida;

        public NumeroOculto() {
            value = new int[PARTIDAS];
            for (int i = 0; i < value.length; i++)
                value[i] = obtenerAleatorio();
            partida = 0;
        }

        synchronized public int propuestaNumero(int num, int partida) {
            if (this.partida != partida) return -1;

            if (num == value[partida]) {
                this.partida++;
                return 1;
            }

            return 0;
        }

        synchronized public int getPartida() {
            return partida;
        }
    }

}

