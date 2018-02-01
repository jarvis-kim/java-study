package kr.co.jarvisk.pattern.state.step3;

public class OffState implements RightState {

    private final static OffState instance = new OffState();

    private OffState() {}

    public static OffState getInstance() {
        return instance;
    }

    @Override
    public void onButtonPush(RightContext context) {
        System.out.println("execute : right on.");
        context.setState(OnState.getInstance());
    }

    @Override
    public void offButtonPush(RightContext context) {
        System.out.println("[Nothing]");
    }

    @Override
    public String getStateName() {
        return "OFF";
    }
}
