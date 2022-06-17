package org.example;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bag {
    private final List<Tile> tiles = new ArrayList<>();
    private Lock lock;
    private boolean occupied;

    public Bag() {
        occupied = true;
//        Random random = new Random();
//        for (char c = 'a'; c < 'z'; c++) {
//            int punctaj = random.nextInt(10);
//            for (int i = 0; i < 10; i++) {
//                tiles.add(new Tile(c, punctaj));
//            }
//        }
        for(int i = 0; i < 9; i++){
            tiles.add(new Tile('a', 1));
        }
        for(int i = 0; i < 2; i++){
            tiles.add(new Tile('b', 3));
        }
        for(int i = 0; i < 2; i++){
            tiles.add(new Tile('c', 3));
        }
        for(int i = 0; i < 4; i++){
            tiles.add(new Tile('d', 2));
        }
        for(int i = 0; i < 12; i++){
            tiles.add(new Tile('e', 1));
        }
        for(int i = 0; i < 2; i++){
            tiles.add(new Tile('f', 4));
        }
        for(int i = 0; i < 3; i++){
            tiles.add(new Tile('g', 2));
        }
        for(int i = 0; i < 2; i++){
            tiles.add(new Tile('h', 4));
        }
        for(int i = 0; i < 9; i++){
            tiles.add(new Tile('i', 1));
        }
        for(int i = 0; i < 1; i++){
            tiles.add(new Tile('j', 8));
        }
        for(int i = 0; i < 1; i++){
            tiles.add(new Tile('k', 5));
        }
        for(int i = 0; i < 4; i++){
            tiles.add(new Tile('l', 1));
        }
        for(int i = 0; i < 2; i++){
            tiles.add(new Tile('m', 3));
        }
        for(int i = 0; i < 6; i++){
            tiles.add(new Tile('n', 1));
        }
        for(int i = 0; i < 8; i++){
            tiles.add(new Tile('o', 1));
        }
        for(int i = 0; i < 2; i++){
            tiles.add(new Tile('p', 3));
        }
        for(int i = 0; i < 1; i++){
            tiles.add(new Tile('q', 10));
        }
        for(int i = 0; i < 6; i++){
            tiles.add(new Tile('r', 1));
        }
        for(int i = 0; i < 4; i++){
            tiles.add(new Tile('s', 1));
        }
        for(int i = 0; i < 6; i++){
            tiles.add(new Tile('t', 1));
        }
        for(int i = 0; i < 4; i++){
            tiles.add(new Tile('u', 1));
        }
        for(int i = 0; i < 2; i++){
            tiles.add(new Tile('v', 4));
        }
        for(int i = 0; i < 2; i++){
            tiles.add(new Tile('w', 4));
        }
        for(int i = 0; i < 1; i++){
            tiles.add(new Tile('x', 8));
        }
        for(int i = 0; i < 2; i++){
            tiles.add(new Tile('y', 4));
        }
        for(int i = 0; i < 1; i++){
            tiles.add(new Tile('z', 10));
        }
        Collections.shuffle(tiles);


        System.out.println(tiles);
    }
    //extrage un tile din sac, o adauga la extracted si o scoate din sac.
    public synchronized List<Tile> extractTiles(int howMany) {
        List<Tile> extracted = new ArrayList<>();
        //System.out.println(Thread.currentThread().getName() + " is waiting for the bag to be available");
        while (!occupied) {
            try {
                occupied = true;
                wait(1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (tiles.size() >= howMany) {
            for (int i = 0; i < howMany; i++) {
                if (tiles.isEmpty())
                    break;
                else {
                    extracted.add(tiles.get(0));
                    tiles.remove(tiles.get(0));
                }
            }
            notifyAll();
            occupied = false;
        }
        //make the thread wait until the bag is not occupied and notify all the threads that are waiting



        return extracted;




    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public List<Tile> getTiles() {
        return tiles;
    }
}
