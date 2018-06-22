package com.firstexample.emarkova.session4;

public class StateManager {
   public static State currentState = State.READY;
    public static enum State {
        READY, STEADY, GO;
    }

    public static State getState() {
        return currentState;
    }
    public static void changeState() {
        switch (currentState)
        {
            case GO:
                currentState = State.READY;
                break;
            case READY:
                currentState = State.STEADY;
                break;
            case STEADY:
                currentState = State.GO;
        }
    }
}
