package kr.co.jarvisk.pattern.decorator;

/**
 * Created by korean4788 on 15. 6. 11..
 */
public class Client {

    public static void main(String[] args) {
        CakeMaker cakeMaker = new CakeMaker();
        WhiteCreamCakeMaker whiteCreamCakeMaker = new WhiteCreamCakeMaker();
        BlackCreamCakeMaker blackCreamCakeMaker = new BlackCreamCakeMaker();

        System.out.println("빵 케익");
        System.out.println(cakeMaker.makeCake());
        System.out.println("------------------------------");

        System.out.println("화이트 케익");
        System.out.println(whiteCreamCakeMaker.makeCake());
        System.out.println("------------------------------");


        System.out.println("초코 케익");
        System.out.println(blackCreamCakeMaker.makeCake());
        System.out.println("------------------------------");

    }
}
