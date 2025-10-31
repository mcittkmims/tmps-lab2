package com.tmps.client.action;

import com.tmps.domain.GameState;

public class QuitAction implements Action {
    @Override
    public void execute() {
        GameState.getInstance().setGameOver(true);
    }
}
