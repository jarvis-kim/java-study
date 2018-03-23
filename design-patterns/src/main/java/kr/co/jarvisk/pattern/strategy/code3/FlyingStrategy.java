package kr.co.jarvisk.pattern.strategy.code3;

/**
 * Created by korean4788 on 2015-05-07.
 */
public class FlyingStrategy implements MovingStrategy {
    @Override
    public void move() {
        System.out.println("I can fly.");
    }
}
