package kr.co.jarvisk.pattern.decorator.pattern;

/**
 * Created by korean4788 on 15. 6. 12..
 */

/**
 * 빵 케익을 만드는 메이커
 */
public class BreadCakeMaker extends CakeMaker {
    /***
     * 빵케익을 만든다.
     * @return 케익
     */
    @Override
    public String makeCake() {
        return "Cake";
    }
}
