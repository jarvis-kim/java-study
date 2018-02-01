package kr.co.jarvisk.pattern.state.step1;

/**
 * 형광등 클래스
 */
public class Right {

    private static final int ON = 1;
    private static final int OFF = 2;

    private int state = OFF;

    public void onButtonPush() {
        if ( state == OFF ) {
            System.out.println("execute : right on.");
            state = ON;
        } else {
            System.out.println("[Nothing]");
        }
    }

    public void offButtonPush() {
        if ( state == ON ) {
            System.out.println("execute : right off.");
            state = OFF;
        } else {
            System.out.println("[Nothing]");
        }
    }

    public String currentState() {
        return state == ON ? "ON" : "OFF";
    }

    public static void main(String[] args) {
        Right right = new Right();
        right.onButtonPush();
        right.onButtonPush();
        right.offButtonPush();
        System.out.println("current : " + right.currentState());
    }
}
