package org.example;

import java.util.List;

public class Player implements Runnable {
    private String name;
    private Game game;
    private boolean running = true;
    private int score = 0;

    public Player(String name){
        this.name = name;
    }

    private synchronized boolean submitWord(){
        List<Tile> extracted = game.getBag().extractTiles(7);
        if (extracted.isEmpty()){
            this.setRunning(false);
            return false;
        }
        //create a word with all the extracted tiles;
        String word = createWord(extracted);
        game.getBoard().addWord(this, word);


        return true;

    }

    private String createWord(List<Tile> tiles){
        StringBuilder word = new StringBuilder();
        for (Tile tile : tiles){
            word.append(tile.getLetter());
            score += tile.getPoints();
        }
        return word.toString();
    }

    @Override
    public void run() {
        while (running){
            submitWord();
            //System.out.println(name + " submitted a word");
//            try {
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

        }

    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
