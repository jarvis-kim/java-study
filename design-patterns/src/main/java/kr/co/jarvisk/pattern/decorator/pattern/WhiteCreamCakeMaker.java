package kr.co.jarvisk.pattern.decorator.pattern;

/**
 * Created by korean4788 on 15. 6. 12..
 */

/**
 * 흰 크림이 듬뿍 발라져있는 케익을 만드는 메이커
 */
public class WhiteCreamCakeMaker extends DecoratorCakeMaker {

    public WhiteCreamCakeMaker(CakeMaker cakeMaker) {
        super(cakeMaker);
    }

    /**
     * 케익에 흰크림 장식을 한다.
     * @return 흰크림 장식 케익
     */
    @Override
    public String makeCake() {
        String cake = super.makeCake();
        StringBuffer buffer = new StringBuffer();
        String[] cakes = cake.split("\n");

        int col = cakes[ 0 ].length() + 2;
        for ( int index = 0; index < col; index++ ) {
            buffer.append("H");
        }
        buffer.append("\n");
        int index = 0;
        for ( ; index < cakes.length - 1; index++ ) {
            buffer.append("H").append(cakes[ index ]).append("H").append("\n");
        }
        buffer.append("H").append(cakes[index]).append("H");

        return buffer.toString();
    }

}
