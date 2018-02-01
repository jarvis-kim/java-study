package kr.co.jarvisk.pattern.decorator;

/**
 * Created by korean4788 on 15. 6. 11..
 */
/**
 * 초코 크림이 듬뿍 발라져있는 케익을 만드는 메이커
 */
public class BlackCreamCakeMaker extends CakeMaker {

    /***
     * 케익를 만든다.
     * 초코 크림이 발라져있는 케익이다.
     * @return 화이트 케익
     */
    @Override
    public String makeCake() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("BBBBBB").append("\n");
        buffer.append("B").append(super.makeCake()).append("B");

        return buffer.toString();
    }
}
