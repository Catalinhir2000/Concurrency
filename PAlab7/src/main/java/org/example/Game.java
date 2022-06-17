package org.example;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Bag bag = new Bag();
    private final Board board = new Board();
    private final Dictionary dictionary = new Dictionary();
    private final List<Player> players = new ArrayList<>();
    private long gameStartTime;


    public void addPlayer (Player player){
        players.add(player);
        player.setGame(this);
    }

    public void play() {
        gameStartTime = System.currentTimeMillis();
        //create a thread for each player
        List<Thread> threads = new ArrayList<>();
        for (Player player : players) {
            Thread thread = new Thread(player);
            threads.add(thread);
        }


        //start the threads
        for (Thread thread : threads) {
            thread.start();
            //wait for the thread to finish
//            try {
//                thread.join();
//            }
//            catch (InterruptedException e) {
//                e.printStackTrace();
//            }

        }

        while (!bag.getTiles().isEmpty()) {

        }
        int scor = 0;
        Player winner = new Player("Winner");
        for (Player player : players) {
            if (player.getScore() > scor) {
                scor = player.getScore();
                winner = player;
            }
        }
        System.out.println("The winner is " + winner.getName() + " with " + winner.getScore() + " points!");
    }

    public static void main(String args[]) {
        Game game = new Game();
        System.out.println("Welcome to Scrabble!");
        game.addPlayer(new Player("Player 1"));
        game.addPlayer(new Player("Player 2"));
        game.addPlayer(new Player("Player 3"));
        Daemon daemon = new Daemon("Daemon", game);
        game.play();
        daemon.setDaemon(true);
        daemon.start();

    }


    public Bag getBag() {
        return bag;
    }

    public Board getBoard() {
        return board;
    }

    public long getGameStartTime() {
        return gameStartTime;
    }


}
