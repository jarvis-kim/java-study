package kr.co.jarvisk.pattern.state.step2;

public class Right {
    private static final int ON = 1;
    private static final int OFF = 2;
    private static final int SLEEP = 3;

    private int state = OFF;

    public void onButtonPush() {
        if ( state == OFF ) {
            System.out.println("execute : right on.");
            state = ON;
        } else if ( state == ON ) {
            System.out.println("execute : sleep on.");
            state = SLEEP;
        } else if ( state == SLEEP ) {
            System.out.println("execute : right on.");
            state = ON;
        }
    }

    public void offButtonPush() {
        if ( state == ON ) {
            System.out.println("execute : right off.(from ON)");
            state = OFF;
        } else if ( state == SLEEP ) {
            System.out.println("execute : right off.(from SLEEP)");
            state = OFF;
        } else {
            System.out.println("[Nothing]");
        }
    }

    public String currentState() {
        return state == ON ? "ON" : state == OFF ? "OFF" : "SLEEP";
    }

    public static void main(String[] args) {
        Right right = new Right();
        right.onButtonPush();
        right.onButtonPush();
        right.offButtonPush();
        System.out.println("current : " + right.currentState());
    }
}
