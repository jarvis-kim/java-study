package kr.co.jarvisk.pattern.state.step3;

public class OnState implements RightState {

    private final static OnState instance = new OnState();

    private OnState() {}

    public static OnState getInstance() {
        return instance;
    }

    @Override
    public void onButtonPush(RightContext context) {
        System.out.println("execute : sleep on.");
        context.setState(SleepState.getInstance());
    }

    @Override
    public void offButtonPush(RightContext context) {
        System.out.println("execute : right off.(from ON)");
        context.setState(OffState.getInstance());
    }

    @Override
    public String getStateName() {
        return "ON";
    }
}
