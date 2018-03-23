package kr.co.jarvisk.pattern.strategy.code2;

/**
 * Created by korean4788 on 2015-05-07.
 */
public abstract class Robot {

    private String name;

    public Robot(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void attack();

    public abstract void move();

}
