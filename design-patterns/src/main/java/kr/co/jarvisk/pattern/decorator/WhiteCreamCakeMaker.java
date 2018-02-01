package kr.co.jarvisk.pattern.decorator;

/**
 * Created by korean4788 on 15. 6. 11..
 */

/**
 * 흰크림이 듬뿍 발라져있는 케익을 만드는 메이커
 */
public class WhiteCreamCakeMaker extends CakeMaker {

    /***
     * 케익를 만든다.
     * 이 흰크림 케익을 만든다.
     * @return 화이트 케익
     */
    @Override
    public String makeCake() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("HHHHHH").append("\n");
        buffer.append("H").append(super.makeCake()).append("H");

        return buffer.toString();
    }
}
