package com.quiz.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.quiz.game.Game;
import com.quiz.helper.Timer;

public class SceneMenu {

    Sprite top_bar;
    Sprite btn_play_game;

    Sprite timer_btn;

    Timer timer;

    public SceneMenu(){
        Gdx.app.log("Menu", "Created an instance of Menu");

        //Btn styles
        float menu_btn_width = Game.WORLD_WIDTH * 0.5f;

        //Setup the play game btn
        btn_play_game = new Sprite(new Texture("btn-play_game.png"));
        btn_play_game.setSize(menu_btn_width, menu_btn_width * 0.25f);
        btn_play_game.setPosition(Game.WORLD_WIDTH_HALF - menu_btn_width/2, Game.WORLD_HEIGHT_HALF - menu_btn_width/2 * 0.5f);

        //Setup the top bar
        top_bar = new Sprite(new Texture("top_bar.png"));
        System.out.println(top_bar.getHeight());
        top_bar.setSize(Game.WORLD_WIDTH, Game.WORLD_WIDTH * 0.185f);
        top_bar.setPosition(0f, Game.WORLD_HEIGHT - top_bar.getHeight());

        timer_btn = new Sprite(new Texture("badlogic.jpg"));
        timer_btn.setSize(Game.WORLD_WIDTH * 0.4f, Game.WORLD_WIDTH * 0.4f);
        timer_btn.setPosition(2f, 2f);

        timer = new Timer();
    }

    public void update(){
        timer.tick();

        if(timer.isRunning()){
            Gdx.app.log("timer %", Float.toString(timer.getPercentage()));

            if(timer.isNewlyFinished()){
                Gdx.app.log("timer", "finished!");
            }
        }

        //Check if a user has touched the screen (and then for the btns)
        if(Gdx.input.justTouched()){
            Vector2 touch = Game.getWorldCoords();

//            Gdx.app.log("MenuUpdate", "touch x/y: "+Float.toString(touch.x)+"/"+Float.toString(touch.y));
//            Rectangle box = btn_play_game.getBoundingRectangle();

//            Gdx.app.log("box","{x: "+Float.toString(box.getX()) + ", y: " + Float.toString(box.getY()) + ", w: "+Float.toString(box.getWidth()) + ", h: "+Float.toString(box.getHeight()) + "}");

            if(Game.pointInsideSpriteLL(touch, btn_play_game)){
                Game.changeState(Game.STATE_GAME);
            }else if(Game.pointInsideSpriteLL(touch, timer_btn)){
                Gdx.app.log("menuTouch", "timer debug");

                //todo: Create a timer class with (start, running?, stop, finished?)
                if(timer.isRunning()){
                    Gdx.app.log("timer", "is running");
                    timer.startTimer(7f);
                }else{
                    Gdx.app.log("timer", "started a new timer for 4 seconds!");
                    timer.startTimer(4f);
                }
            }
        }
    }

    public void render(SpriteBatch batch){
        //Clear the screen to a cornflower blue
        Gdx.gl.glClearColor(0.4f, 0.6f, 0.7f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Draw the menu bar
        Game.drawSpriteAtLL(batch, top_bar);

        //Draw the play game btn
        Game.drawSpriteAtLL(batch, btn_play_game);

        //Timer debug btn
        Game.drawSpriteAtLL(batch, timer_btn);

    }
}
