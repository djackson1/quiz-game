package com.quiz.math_game_1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
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

    MG1_btn[][] mg1_btns;

    int _width;
    int _height;

    public static float _btn_width;

    public static Vector2 _start_pos = new Vector2(0.4f, 1f);

    Random random = new Random();

    public MathGame1(){
        // The width and height of the MG1 buttons
        _btn_width = Game.WORLD_WIDTH * 200/1080; //also the height

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

        mg1_btns = new MG1_btn[w][h];

        for(int ww=0; ww<_width; ww++){
            for(int hh=0; hh<_height; hh++) {
                int n = random.nextInt(btn_count) + 1;
                Gdx.app.log("math1", Integer.toString(n));
                MG1_btn mg1_btn = new MG1_btn(_start_pos.x + ww * _btn_width, _start_pos.y + hh * _btn_width, n);
                mg1_btns[ww][hh] = mg1_btn;
            }
        }
    }

    Vector2 getTouchRelativeToBtns(){
        Vector2 touch = Game.getWorldCoords();
        touch.x -= _start_pos.x;
        touch.y -= _start_pos.y;
        touch.x /= _btn_width;
        touch.y /= _btn_width;
        return touch;
    }

    boolean pointIsntInsideArray(int px, int py, Array<GridPoint2> array){
        for(int i=0; i<array.size; i++){
            if(array.get(i).x == px && array.get(i).y == py) return false;
        }
        return true;
    }

    boolean isPointSecondToLastInArray(int px, int py, Array<GridPoint2> array){
        if(array.size < 2) return false;

        return (array.get(array.size-2).x == px && array.get(array.size-2).y == py);
    }

    int _state_of_touch = 0;
    // 0 = nothing
    // 1 = just touched
    // 2 = dragging
    Array<GridPoint2> which_points = new Array<GridPoint2>();

    public void update() {
//        Gdx.input.vibrate(100);

        if(_state_of_touch == 0){ if(Gdx.input.justTouched()) _state_of_touch = 1; }
        else if(_state_of_touch == 1 || _state_of_touch == 2){
            if(Gdx.input.isTouched()){_state_of_touch = 2;}
            else{_state_of_touch = 0;}
        }

        if(_state_of_touch == 2){
            if(which_points.size == 0){
                //find any btn and add it
                Vector2 touch = getTouchRelativeToBtns();

                int tx = (int)touch.x;
                int ty = (int)touch.y;

                if(tx >= 0 && tx < _width && ty >= 0 && ty < _height){
                    mg1_btns[tx][ty].activate();
                    GridPoint2 p = new GridPoint2(tx, ty);
                    which_points.add(p);
                }
            }else {
                //only adjacent btns
                Vector2 touch = getTouchRelativeToBtns();

                int tx = (int)touch.x;
                int ty = (int)touch.y;

                if (tx >= 0 && tx < _width && ty >= 0 && ty < _height) {
                    if (pointIsntInsideArray(tx, ty, which_points)) {
                        GridPoint2 last_point = which_points.get(which_points.size - 1);
                        int rx = Math.abs(tx - Math.round(last_point.x));
                        int ry = Math.abs(ty - Math.round(last_point.y));

                        if ((rx == 0 && ry == 1) || (ry == 0 && rx == 1)) {
                            //btn is within 1
                            mg1_btns[tx][ty].activate();
                            GridPoint2 p = new GridPoint2(tx, ty);
                            which_points.add(p);
                        }
                    } else if (isPointSecondToLastInArray(tx, ty, which_points)) {
                        GridPoint2 point = which_points.get(which_points.size - 1);
                        mg1_btns[point.x][point.y].reset();
                        which_points.pop();
                    }
                }
            }
        }else if(_state_of_touch == 0){
            if(which_points.size > 0){
                //Count all the points

                int count = 0;
                for(int i=0; i<which_points.size; i++){
                    int x = which_points.get(i).x;
                    int y = which_points.get(i).y;

                    count += mg1_btns[x][y]._value;
                    mg1_btns[x][y].reset();
                }

                if(count != 9){
                    Gdx.input.vibrate(500);
                }

                which_points.clear();
            }
        }
    }
    public void render(SpriteBatch batch){
        batch.draw(btn_total_9, Game.WORLD_WIDTH_HALF - btn_total_9.getWidth()/2, 11f, btn_total_9.getWidth(), btn_total_9.getHeight());

        for(int w=0; w<_width; w++){
            for(int h=0; h<_height; h++) {
                mg1_btns[w][h].render(batch);
            }
        }
    }
}