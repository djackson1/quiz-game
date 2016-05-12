package com.quiz.math_game_1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.quiz.game.Game;

import java.util.Random;

/**
 * Created by Dan on 10/05/2016.
 */
public class MathGame1 {

    public static Array<Sprite> btns_unclicked;
    public static Array<Sprite> btns_clicked;

    public static Sprite btn_total_9;

    public static int btn_count = 3;

    public static Sprite btn_unclicked_1;
    public static Sprite btn_clicked_1;
    public static Sprite btn_unclicked_2;
    public static Sprite btn_clicked_2;

    Array<MG1_btn> mg1_btns;

    int _width;
    int _height;

    public static float _btn_width;

    Random random = new Random();

    public MathGame1(){
        // The width and height of the MG1 buttons
        _btn_width = Game.WORLD_WIDTH * 200/1080; //also the height

        mg1_btns = new Array<MG1_btn>();

        setup_btns();
    }

    private void setup_btns(){
        btns_unclicked = new Array<Sprite>();
        btns_clicked = new Array<Sprite>();

        int temp = 2;
        String path_unclicked = "math-game-1/btn-unclicked-";
        String path_clicked = "math-game-1/btn-clicked-";
        String path_end = ".png";

        for(int i=0; i<btn_count; i++){
            // [0,1]
            //Unclicked btn
            String unclicked = path_unclicked + Integer.toString(i+1) + path_end;
            Sprite btn_unclicked = new Sprite(new Texture(unclicked));
            btn_unclicked.setSize(_btn_width, _btn_width);

            btns_unclicked.add(btn_unclicked);

            //Clicked btn
            String clicked = path_clicked + Integer.toString(i+1) + path_end;
            Sprite btn_clicked = new Sprite(new Texture(clicked));
            btn_clicked.setSize(_btn_width, _btn_width);

            btns_clicked.add(btn_clicked);
        }

        btn_total_9 = new Sprite(new Texture("math-game-1/total-9.png"));
        btn_total_9.setSize(_btn_width, _btn_width);
    }

    public void addGrid(int w, int h){
        _width = w;
        _height = h;


        Vector2 start = new Vector2(0.4f, 1f);


        mg1_btns.clear();

        for(int hh=0; hh<_height; hh++){
            for(int ww=0; ww<_width; ww++){
                int n = random.nextInt(btn_count) + 1;
                Gdx.app.log("math1", Integer.toString(n));
                MG1_btn mg1_btn = new MG1_btn(start.x + _btn_width*ww, start.y + _btn_width*hh,n);
                mg1_btns.add(mg1_btn);
            }
        }
    }

    int _state_of_touch = 0;

    public void update(){
        Vector2 start = new Vector2(0.4f, 1f);

//        if(Gdx.input.justTouched()) Gdx.input.vibrate(100);

        for(int i=0; i<mg1_btns.size; i++) {
            mg1_btns.get(i).update();
        }

        if(_state_of_touch == 1){
            if(Gdx.input.isTouched()){
//                Gdx.app.log("MG1-update", "still touched ["+Float.toString(Gdx.input.getX())+","+Float.toString(Gdx.input.getY()));
            }else{
                _state_of_touch = 0;
//                Gdx.app.log("MG1-update", "touch released");

                for(int i=0; i<mg1_btns.size; i++) {
                    mg1_btns.get(i).reset();
                }
            }
        }
        if(Gdx.input.justTouched()){
//            Gdx.app.log("MG1-update", "just touched");
            _state_of_touch = 1;
        }

        if(_state_of_touch == 1){
            for(int i=0; i<mg1_btns.size; i++) {
                Vector2 touch = Game.getWorldCoords();
                mg1_btns.get(i).updateTouch(touch.x, touch.y);
            }
        }



    }

    public void render(SpriteBatch batch){
        Vector2 start = new Vector2(0.4f, 1f);

        batch.draw(btn_total_9, Game.WORLD_WIDTH_HALF - btn_total_9.getWidth()/2, 11f, btn_total_9.getWidth(), btn_total_9.getHeight());

        for(int i=0; i<mg1_btns.size; i++){
            mg1_btns.get(i).render(batch);
        }
    }
}