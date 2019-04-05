package com.quiz.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.quiz.game.Game;

/**
 * Created by Dan on 10/05/2016.
 */
public class ScenePhysics {

    Sprite box;
    Body _body;
    Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();

    World world;

    public ScenePhysics(){
        Gdx.app.log("Physics", "Init");
        world = new World(new Vector2(0, -9.8f), true);


        float width = Game.WORLD_WIDTH * 0.1f;

        box = new Sprite(new Texture("badlogic.jpg"));
        box.setSize(width, width);
        box.setPosition(0f, 0f);
        box.setOrigin(box.getWidth()/2, box.getHeight()/2);

        //ToDo: Create a physics object class using these properties
        // Sprite are created at from bottom-left
        // Bodies are created from origin

        BodyDef _bodyDef = new BodyDef();
        _bodyDef.type = BodyDef.BodyType.DynamicBody;
//        _bodyDef.position.set(box.getX() + box.getWidth()/2, box.getY() + box.getHeight()/2);
        _bodyDef.position.set(box.getOriginX(), box.getOriginY());

        _body = world.createBody(_bodyDef);


        PolygonShape shape = new PolygonShape();
        shape.setAsBox(box.getWidth()/2, box.getHeight()/2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

        _body.createFixture(fixtureDef);

        Fixture _fixture = _body.getFixtureList().get(0);
    }

    boolean is_touched = false;
    public void update(){

        is_touched = Gdx.input.isTouched();

        box.setPosition(_body.getPosition().x - box.getWidth()/2, _body.getPosition().y - box.getHeight()/2);
    }

    public void render(SpriteBatch batch) {
        //Clear the screen to a cornflower blue (errybody loves cornflower blue)
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.7f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (is_touched) {
            debugRenderer.render(world, Game.camera.combined);
        } else {
            batch.draw(box, box.getX(), box.getY(), box.getWidth(), box.getHeight());
        }
    }
}
