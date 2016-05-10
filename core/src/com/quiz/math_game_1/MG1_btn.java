package com.quiz.math_game_1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.quiz.game.Game;

/**
 * Created by Dan on 10/05/2016.
 */
public class MG1_btn {

    float _x;
    float _y;

    int _state;

    Vector2 _position;
    Rectangle _bb;

    public MG1_btn(){}
    public MG1_btn(float x, float y){
        _position = new Vector2(x, y);
        _state = 0;

        _bb = new Rectangle(x,y, MathGame1._btn_width, MathGame1._btn_width);
    }

    public void update(){
        if(Gdx.input.justTouched()) {
            Vector2 touch = Game.getWorldCoords();

            if (_bb.contains(touch.x, touch.y)) {
                _state = 1;
            }
        }
    }

    public void render(SpriteBatch batch){
        batch.draw(MathGame1.btn_unclicked, _position.x, _position.y, MathGame1._btn_width, MathGame1._btn_width);

        if(_state == 1){
            batch.draw(MathGame1.btn_clicked, _position.x, _position.y, MathGame1._btn_width, MathGame1._btn_width);
        }
    }
}
