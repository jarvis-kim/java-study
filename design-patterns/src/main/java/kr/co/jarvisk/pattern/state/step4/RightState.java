package kr.co.jarvisk.pattern.state.step4;

public interface RightState {

    void onButtonPush(RightContext context);

    void offButtonPush(RightContext context);

    String getStateName();
}
