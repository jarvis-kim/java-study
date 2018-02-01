package kr.co.jarvisk.pattern.state.step4;

public class LevelOnState implements RightState {

    private static final int FIRST_LEVEL = 0;

    private static final int LAST_LEVEL = 3;

    private int level;

    public LevelOnState(int level) {
        if ( !(1 <= level && level <= LAST_LEVEL) ) {
            throw new IllegalArgumentException("level in 1-3 plz");
        }

        this.level = level - 1;
    }

    @Override
    public void onButtonPush(RightContext context) {
        if ( level + 1 >= LAST_LEVEL ) {
            context.setState(SleepState.getInstance());
            System.out.println("execute : sleep on.");
            return;
        }
        level = ++level % LAST_LEVEL;
        System.out.println("execute : level up. current : " + displayLevel());
    }

    @Override
    public void offButtonPush(RightContext context) {
        if ( level <= FIRST_LEVEL ) {
            context.setState(OffState.getInstance());
            System.out.println("execute : right off.(from ON)");
            return;
        }

        level = level % LAST_LEVEL - 1;
        System.out.println("execute : level down. current : " + displayLevel());
    }

    @Override
    public String getStateName() {
        return "ON_" + displayLevel();
    }

    private int displayLevel() {
        return level + 1;
    }
}
