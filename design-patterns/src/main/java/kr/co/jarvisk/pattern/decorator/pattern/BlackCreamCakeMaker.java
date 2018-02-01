package kr.co.jarvisk.pattern.decorator.pattern;

/**
 * Created by korean4788 on 15. 6. 12..
 */

/**
 * 초코 크림이 듬뿍 발라져있는 케익을 만드는 메이커
 */
public class BlackCreamCakeMaker extends DecoratorCakeMaker {

    public BlackCreamCakeMaker(CakeMaker cakeMaker) {
        super(cakeMaker);
    }

    /**
     * 케익에 초코 장식을 한다. 초코로 케익을 덮어버리면 되겠다
     * @return 초코 장식 케익
     */
    @Override
    public String makeCake() {
        String cake = super.makeCake();
        StringBuffer buffer = new StringBuffer();
        String[] cakes = cake.split("\n");

        int col = cakes[ 0 ].length() + 2;
        for ( int index = 0; index < col; index++ ) {
            buffer.append("B");
        }
        buffer.append("\n");
        int index = 0;
        for ( ; index < cakes.length - 1; index++ ) {
            buffer.append("B").append(cakes[ index ]).append("B").append("\n");
        }
        buffer.append("B").append(cakes[index]).append("B");

        return buffer.toString();
    }

}
