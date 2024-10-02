package es.ieslavereda.ejercicio7;

public class MiHilo implements Runnable{

    private Contadores contadores;

    public MiHilo(Contadores contadores) {
        this.contadores = contadores;
    }

    @Override
    public void run() {
        for(long i=0;i<10000000;i++)

                contadores.incrementar1();


        for(long i=0;i<10000000;i++)

                contadores.incrementar2();

    }
}
