package com.quiz.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Dan on 02/06/2016.
 */
public class Helper {

    public static Sprite createSpriteAtCenter(String path, float x, float y, float w, float h){
        Texture texture = new Texture(Gdx.files.internal(path), true);
        //Allow mip mapping so the texture doesn't look pixelated
        texture.setFilter(Texture.TextureFilter.MipMapLinearLinear, Texture.TextureFilter.MipMapLinearLinear);

        Sprite sprite = new Sprite(texture);
        sprite.setSize(w, h);
        sprite.setPosition(x - w/2, y - h/2); //Set the draw position to the bottom left
        sprite.setOrigin(w/2, h/2); //When attaching a physics body it's best to have a center point available

        return sprite;
    }

    public static void drawSpriteX(SpriteBatch batch, Sprite sprite) {
        batch.draw(sprite, sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }
}
