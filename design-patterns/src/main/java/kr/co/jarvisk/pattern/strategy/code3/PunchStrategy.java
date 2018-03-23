package kr.co.jarvisk.pattern.strategy.code3;

/**
 * Created by korean4788 on 2015-05-07.
 */
public class PunchStrategy implements AttackStrategy {
    @Override
    public void attack() {
        System.out.println("I have strong punch and can attack with it.");
    }
}
