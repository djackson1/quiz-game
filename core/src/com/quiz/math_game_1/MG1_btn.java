package com.quiz.math_game_1;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Dan on 10/05/2016.
 */
public class MG1_btn {

    int _state;
    int _value;

    Vector2 _position;

    public MG1_btn(float x, float y, int value){
        _position = new Vector2(x, y);
        _state = 0;

        _value = value;
    }

    public void activate(){ _state = 1; }
    public void reset(){ _state = 0; }

    public void render(SpriteBatch batch){
        if(_state == 0) {
            batch.draw(MathGame1.btns_unclicked.get(_value - 1), _position.x, _position.y, MathGame1._btn_width, MathGame1._btn_width);
        }else if(_state == 1){
            batch.draw(MathGame1.btns_clicked.get(_value-1), _position.x, _position.y, MathGame1._btn_width, MathGame1._btn_width);
        }
    }
}
