package com.quiz.math_game_1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.quiz.game.Game;

/**
 * Created by Dan on 10/05/2016.
 */
public class MathGame1 {

    public static Sprite btn_unclicked;
    public static Sprite btn_clicked;

    Array<MG1_btn> mg1_btns;

    int _width;
    int _height;

    public static float _btn_width;

    public MathGame1(){

        _btn_width = Game.WORLD_WIDTH * 200/1080; //also the height

        btn_unclicked = new Sprite(new Texture("math-game-1/btn-unclicked-1.png"));
        btn_unclicked.setSize(_btn_width, _btn_width);
//        btn_unclicked.setPosition(Game.WORLD_WIDTH_HALF - btn_width/2, Game.WORLD_HEIGHT * 0.44f - btn_width/2);


        btn_clicked = new Sprite(new Texture("math-game-1/btn-clicked-1.png"));
        btn_clicked.setSize(_btn_width, _btn_width);
//        btn_clicked.setPosition(btn_unclicked.getX(), btn_unclicked.getY());
        btn_clicked.setAlpha(0.0f);

//        btn_state = 0;
        mg1_btns = new Array<MG1_btn>();
    }

    public void addGrid(int w, int h){
        _width = w;
        _height = h;


        Vector2 start = new Vector2(0.4f, 1f);


        mg1_btns.clear();

        for(int hh=0; hh<_height; hh++){
            for(int ww=0; ww<_width; ww++){
                MG1_btn mg1_btn = new MG1_btn(start.x + _btn_width*ww, start.y + _btn_width*hh);
                mg1_btns.add(mg1_btn);
            }
        }
    }

    public void update(){
        Vector2 start = new Vector2(0.4f, 1f);

        for(int i=0; i<mg1_btns.size; i++) {
            mg1_btns.get(i).update();
        }
    }

    public void render(SpriteBatch batch){

        Vector2 start = new Vector2(0.4f, 1f);

        for(int i=0; i<mg1_btns.size; i++){
            mg1_btns.get(i).render(batch);
        }
    }
}