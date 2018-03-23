package kr.co.jarvisk.pattern.strategy.code2;

/**
 * Created by korean4788 on 2015-05-07.
 */
public class TaekwonV extends Robot {

    public TaekwonV(String name) {
        super(name);
    }

    @Override
    public void attack() {
        System.out.println("I have Missile and can attack with it.");
    }

    @Override
    public void move() {
        /* 태권V를 날개 만들자 */
        System.out.println("I can fly.");
    }
}
