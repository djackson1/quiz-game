package com.quiz.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by djackson on 25/04/16.
 */
public class SceneGame {

    SceneGame(){

    }

    void update(){

    }

    void render(SpriteBatch batch){
        Gdx.gl.glClearColor(0.6f, 0.5f, 0.4f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    }
}
