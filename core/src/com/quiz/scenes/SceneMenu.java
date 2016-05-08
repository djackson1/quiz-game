package com.quiz.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.quiz.game.Game;

public class SceneMenu {

    Sprite top_bar;
    Sprite btn_play_game;

    public SceneMenu(){
        Gdx.app.log("Menu", "Created an instance of Menu");



        //Btn styles
        float menu_btn_width = Game.WORLD_WIDTH * 0.5f;

        //Setup the play game btn
        btn_play_game = new Sprite(new Texture("btn-play_game.png"));
        btn_play_game.setSize(menu_btn_width, menu_btn_width * 0.25f);
        btn_play_game.setPosition(Game.WORLD_WIDTH_HALF, Game.WORLD_HEIGHT_HALF);

        //Setup the top bar
        top_bar = new Sprite(new Texture("top_bar.png"));
        System.out.println(top_bar.getHeight());
        top_bar.setSize(Game.WORLD_WIDTH, Game.WORLD_WIDTH * 0.185f);
        top_bar.setPosition(Game.WORLD_WIDTH_HALF, Game.WORLD_HEIGHT - (top_bar.getHeight()/2));
    }

    public void update(){
        //Check if a user has touched the screen (and then for the btns)
        if(Gdx.input.justTouched()){
            Vector2 touch = Game.getWorldCoords();

            if(btn_play_game.getBoundingRectangle().contains(touch.x, touch.y)){
                //play game was pressed
            }
        }
    }

    public void render(SpriteBatch batch){
        //Clear the screen to a cornflower blue (errybody loves cornflower blue)
        Gdx.gl.glClearColor(0.4f, 0.6f, 0.7f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Draw the menu bar
        Game.drawSpriteX(batch, top_bar);

        //Draw the play game btn
        Game.drawSpriteX(batch, btn_play_game);

    }
}
