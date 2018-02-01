package kr.co.jarvisk.pattern.state.step3;

public interface RightState {

    void onButtonPush(RightContext context);

    void offButtonPush(RightContext context);

    String getStateName();
}
