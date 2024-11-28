package com.example;

import java.util.List;

public class Lion {

    boolean hasMane;

    private Feline feline;

    public Lion(Feline feline, String sex) throws Exception {
        if ("Самец".equals(sex)) {
            hasMane = true;
        } else if ("Самка".equals(sex)) {
            hasMane = false;
        } else {
            throw new Exception("Используйте допустимые значения пола животного - самец или самка");
        }
        this.feline = feline;
    }

    public boolean doesHaveMane() {
        return hasMane;
    }


    public int getKittens() {
        return feline.getKittens();
    }

    public List<String> getFood() throws Exception {
        return feline.getFood("Хищник");
    }
}
