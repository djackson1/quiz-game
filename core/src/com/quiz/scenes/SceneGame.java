package com.quiz.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.quiz.game.Game;
import com.quiz.math_game_1.MathGame1;

/**
 * Created by djackson on 25/04/16.
 */
public class SceneGame {

    Sprite btn_unclicked;
    Sprite btn_clicked;

    int btn_state;

    MathGame1 mg1;

    public SceneGame(){
        mg1 = new MathGame1();

        mg1.addGrid(5,5);
    }

    public void update(){
        mg1.update();
    }

    public void activate(){
        mg1.activate();
    }

    public void render(SpriteBatch batch){
        Gdx.gl.glClearColor(0.5f, 0.55f, 0.7f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mg1.render(batch);
    }
}
