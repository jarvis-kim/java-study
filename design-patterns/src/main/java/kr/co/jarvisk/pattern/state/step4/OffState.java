package kr.co.jarvisk.pattern.state.step4;

public class OffState implements RightState {

    private final static OffState instance = new OffState();

    private OffState() {}

    public static OffState getInstance() {
        return instance;
    }

    @Override
    public void onButtonPush(RightContext context) {
        System.out.println("execute : right level 1 ");
        context.setState(new LevelOnState(1));
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
