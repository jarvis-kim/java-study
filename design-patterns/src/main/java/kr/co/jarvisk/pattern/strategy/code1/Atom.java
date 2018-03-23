package kr.co.jarvisk.pattern.strategy.code1;

/**
 * Created by korean4788 on 2015-05-07.
 */
public class Atom extends Robot {

    public Atom(String name) {
        super(name);
    }

    @Override
    public void attack() {
        System.out.println("I have strong punch and can attack with it.");
    }

    @Override
    public void move() {
        System.out.println("I can fly.");
    }
}
