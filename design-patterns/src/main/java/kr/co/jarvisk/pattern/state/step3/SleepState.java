package kr.co.jarvisk.pattern.state.step3;

public class SleepState implements RightState {

    private final static SleepState instance = new SleepState();

    private SleepState() {}

    public static SleepState getInstance() {
        return instance;
    }

    @Override
    public void onButtonPush(RightContext context) {
        System.out.println("execute : right on.");
        context.setState(OnState.getInstance());
    }

    @Override
    public void offButtonPush(RightContext context) {
        System.out.println("execute : right off.(from SLEEP)");
        context.setState(OffState.getInstance());
    }

    @Override
    public String getStateName() {
        return "SLEEP";
    }
}
