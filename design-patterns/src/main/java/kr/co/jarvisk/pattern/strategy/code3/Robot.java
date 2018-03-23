package kr.co.jarvisk.pattern.strategy.code3;

/**
 * Created by korean4788 on 2015-05-07.
 */
public abstract class Robot {

    private String name;
    private MovingStrategy movingStrategy;
    private AttackStrategy attackStrategy;

    public Robot(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void attack() {
        attackStrategy.attack();
    }

    public void move() {
        movingStrategy.move();
    }

    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }

    public void setMovingStrategy(MovingStrategy movingStrategy) {
        this.movingStrategy = movingStrategy;
    }
}
