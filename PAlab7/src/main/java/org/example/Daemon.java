package org.example;

public class Daemon extends Thread {
    private final Game game;
    public Daemon(String name, Game game) {
        super(name);
        this.game = game;
    }
    //timekeeping daemon thread
    public void run() {
        while (true) {
            long time = game.getGameStartTime();
            long difference = System.currentTimeMillis() - time;
            System.out.println(getName() + ": " + difference + " ms");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(getName() + " interrupted.");
                break;
            }
        }
    }
}


