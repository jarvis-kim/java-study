package kr.co.jarvisk.pattern.strategy.code2;

/**
 * Created by korean4788 on 2015-05-07.
 */
public class Sungard extends Robot {
    public Sungard(String name) {
        super(name);
    }

    @Override
    public void attack() {
        /* 태권V attack 복사 */
        System.out.println("I have Missile and can attack with it.");
    }

    @Override
    public void move() {
        /* 아톰 move 복사 */
        System.out.println("I can fly.");
        /* 태권V move 복사해서 조금 수정*/
        System.out.println("I can walk.");
    }
}
