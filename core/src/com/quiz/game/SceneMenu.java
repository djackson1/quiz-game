package com.quiz.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class SceneMenu {

    Sprite btn_a_sprite;
    Sprite btn_game_sprite;

    Sprite btn_physics;


    Sprite btn_play_game;

    SceneMenu(){
        Gdx.app.log("Menu", "Created an instance of Menu");


        //Btn styles
        float menu_btn_width = Game.WORLD_WIDTH * 0.5f;

        btn_play_game = new Sprite(new Texture("btn-play_game.png"));
        btn_play_game.setSize(menu_btn_width, menu_btn_width * 0.25f);
        btn_play_game.setPosition(Game.WORLD_WIDTH_HALF, Game.WORLD_HEIGHT_HALF);

//        //Creating the [BTN a]
//        btn_a_sprite = new Sprite(new Texture("btn-a.png"));
//
//        btn_a_sprite.setSize(menu_btn_width, menu_btn_width * 0.25f);
//        btn_a_sprite.setPosition(Game.WORLD_WIDTH / 2 - (btn_a_sprite.getWidth() / 2), 3.0f);
//
//        //createSprite("btn-a.png", width, height, center_x, center_y)
//
//
//        //Creating the [BTN Game]
//        btn_game_sprite = new Sprite(new Texture("menu_btn_game.jpg"));
//
//        btn_game_sprite.setSize(menu_btn_width, menu_btn_width * 0.25f);
//        btn_game_sprite.setPosition(Game.WORLD_WIDTH / 2 - (btn_game_sprite.getWidth() / 2), 9.0f);
//
//
//        //Creaign the [BTN phsics]
//        btn_physics = new Sprite(new Texture("menu_btn_physics.png"));
//
//        btn_physics.setSize(menu_btn_width, menu_btn_width * 0.25f);
//        btn_physics.setPosition(Game.WORLD_WIDTH / 2 - (btn_physics.getWidth() / 2), 7.0f);

        Gdx.app.log("MenuSetup", "Btn [width]: "+Float.toString(menu_btn_width));
    }


    void update(){
//        if(Gdx.input.justTouched()){
//            Vector2 touch = Game.getWorldCoords();
//
//            Rectangle box = btn_a_sprite.getBoundingRectangle();
//
//            Gdx.app.log("Menu.update", "Touch {"+touch.x+","+touch.y+"}");
//
//            Gdx.app.log("box","{x: "+Float.toString(box.getX()) + ", y: " + Float.toString(box.getY()) + ", w: "+Float.toString(box.getWidth()) + ", h: "+Float.toString(box.getHeight()) + "}");
//
//            if(btn_game_sprite.getBoundingRectangle().contains(touch.x, touch.y)){
//                Gdx.app.log("Menu.update", "User touched [BTN - a]");
//                Game.changeState(Game.STATE_GAME);
//            }else if(btn_physics.getBoundingRectangle().contains(touch.x, touch.y)){
//                Game.changeState(Game.STATE_PHYSICS);
//            }
//        }
    }

    void render(SpriteBatch batch){
        Gdx.gl.glClearColor(0.4f, 0.6f, 0.7f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//        batch.draw(btn_a_sprite.getTexture(), btn_a_sprite.getX(), btn_a_sprite.getY(), btn_a_sprite.getWidth(), btn_a_sprite.getHeight());
//        batch.draw(btn_game_sprite.getTexture(), btn_game_sprite.getX(), btn_game_sprite.getY(), btn_game_sprite.getWidth(), btn_game_sprite.getHeight());
//        batch.draw(btn_game_sprite.getTexture(), btn_game_sprite.getX(), btn_game_sprite.getY());
//        Game.drawSprite(batch, btn_game_sprite);

//        Game.drawSprite(batch, btn_physics);

//        Game.drawSpriteX(batch, btn_game_sprite);
//        Game.drawSpriteX(batch, btn_physics);

        Game.drawSpriteX(batch, btn_play_game);

    }
}
