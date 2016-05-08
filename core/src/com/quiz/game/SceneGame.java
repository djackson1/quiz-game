package com.quiz.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by djackson on 25/04/16.
 */
public class SceneGame {

    Sprite btn_unclicked;
    Sprite btn_clicked;

    int btn_state;

    public SceneGame(){
        float btn_width = Game.WORLD_WIDTH * 200/1080; //also the height

//        btn_play_game = new Sprite(new Texture("btn-play_game.png"));
//        btn_play_game.setSize(menu_btn_width, menu_btn_width * 0.25f);
//        btn_play_game.setPosition(Game.WORLD_WIDTH_HALF, Game.WORLD_HEIGHT_HALF);

        btn_unclicked = new Sprite(new Texture("math-game-1/btn-unclicked-1.png"));
        btn_unclicked.setSize(btn_width, btn_width);
        btn_unclicked.setPosition(Game.WORLD_WIDTH_HALF, Game.WORLD_HEIGHT * 0.44f);


        btn_clicked = new Sprite(new Texture("math-game-1/btn-clicked-1.png"));
        btn_clicked.setSize(btn_width, btn_width);
        btn_clicked.setPosition(btn_unclicked.getX(), btn_unclicked.getY());
        btn_clicked.setAlpha(0.0f);

        btn_state = 0;
    }

    public void update(){

        if(Gdx.input.justTouched()){
            Gdx.app.log("GameUpdate", "Starting btn press process");

            if(btn_state == 0){
                btn_state = 1;
            }
        }

        int magic_number = 12;

        if(btn_state > 0){
            btn_state++;

            Gdx.app.log("BtnState", Integer.toString(btn_state));

            btn_clicked.setAlpha((btn_state + 0f) / (magic_number + 0f));


            if(btn_state == magic_number) {
                btn_state = 0;
            }
        }

        Gdx.app.log("alpha", Float.toString(btn_clicked.getColor().a));
    }

    public void render(SpriteBatch batch){
        Gdx.gl.glClearColor(0.6f, 0.5f, 0.4f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Game.drawSpriteX(batch, btn_unclicked);
        Game.drawSpriteX(batch, btn_clicked);

//        btn_unclicked.draw(batch);
//        btn_clicked.draw(batch);
    }
}
