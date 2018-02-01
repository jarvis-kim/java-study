package kr.co.jarvisk.pattern.state.step4;

public class Right implements RightContext {

    private RightState state = OffState.getInstance();

    public void on() {
        state.onButtonPush(this);
    }

    public void off() {
        state.offButtonPush(this);
    }

    @Override
    public void setState(RightState next) {
        this.state = next;
    }

    public String currentState() {
        return state.getStateName();
    }

    public static void main(String[] args) {
        Right right = new Right();
        right.on();
        right.on();
        right.on();
        right.on();
        right.on();
        right.on();
        right.off();
        right.off();
        right.off();
        System.out.println("current : " + right.currentState());
    }
}
