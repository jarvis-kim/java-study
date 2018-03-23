package kr.co.jarvisk.pattern.strategy.code2;

/**
 * Created by korean4788 on 2015-05-07.
 */
public class Client {

    public static void main(String[] args) {
        Robot taekwonV = new TaekwonV("TaekwonV");
        Robot atom = new Atom("Atom");

        System.out.println("My name is " + taekwonV.getName());
        taekwonV.move();
        taekwonV.attack();

        System.out.println();

        System.out.println("My name is " + atom.getName());
        atom.move();
        atom.attack();
    }
}
