package com.example.escapethedoctor.gamestates;


import com.example.escapethedoctor.Game;

public abstract class BaseState {

    protected Game game;

    public BaseState(Game game){
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
