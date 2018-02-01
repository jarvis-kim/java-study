package kr.co.jarvisk.pattern.decorator.pattern;

/**
 * Created by korean4788 on 15. 6. 12..
 */

/***
 * 케익을 만들진 않고, 케익 위에 장식을 한다.
 */
public abstract class DecoratorCakeMaker extends CakeMaker {

    private CakeMaker cakeMaker;

    public DecoratorCakeMaker(CakeMaker cakeMaker) {
        this.cakeMaker = cakeMaker;
    }

    @Override
    public String makeCake() {
        return cakeMaker.makeCake();
    }
}
