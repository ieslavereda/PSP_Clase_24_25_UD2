package es.ieslavereda.ejercicio7;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Contadores c=new Contadores();

        List<Thread> threadList = new ArrayList<>();
        for(int i=0;i<10;i++)
            threadList.add(new Thread(new MiHilo(c)));

        for(Thread t : threadList)
            t.start();

        for(Thread t : threadList)
            t.join();

        System.out.println(c.getContador1());
        System.out.println(c.getContador2());

    }
}
