package kr.co.jarvisk.pattern.decorator.pattern;

/**
 * Created by korean4788 on 15. 6. 12..
 */
public class Client {

    public static void main(String[] args) {
        System.out.println();
        CakeMaker cakeMaker = new BlackCreamCakeMaker(new WhiteCreamCakeMaker(new BreadCakeMaker()));
        System.out.println(cakeMaker.makeCake());
        System.out.println();
    }
}
