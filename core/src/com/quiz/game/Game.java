package com.quiz.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import com.quiz.scenes.*;

public class Game extends ApplicationAdapter {

	//World size
	public static float WORLD_WIDTH;
	public static float WORLD_HEIGHT;
	public static float WORLD_WIDTH_HALF;
	public static float WORLD_HEIGHT_HALF;

	public static float WORLD_RATIO;


	//Device screen size
	static float SCREEN_WIDTH;
	static float SCREEN_HEIGHT;


	//States
	static int state;

	static SceneMenu menu;
	static SceneGame game;
//	static ScenePhysics physics;

	public static int STATE_MENU = 0;
	public static int STATE_GAME = 1;
	static int STATE_PHYSICS = 2;

	SpriteBatch batch;
	Texture img;
	public static Camera camera;


//	static Vector

	@Override
	public void create () {
		SCREEN_WIDTH = Gdx.graphics.getWidth();
		SCREEN_HEIGHT = Gdx.graphics.getHeight();

		System.out.println("System w/h : "+Float.toString(SCREEN_WIDTH) + "/"+Float.toString(SCREEN_HEIGHT));

		WORLD_RATIO = SCREEN_HEIGHT / SCREEN_WIDTH;

		WORLD_WIDTH = 10;
		WORLD_HEIGHT = WORLD_WIDTH * WORLD_RATIO;

		WORLD_WIDTH_HALF = WORLD_WIDTH / 2;
		WORLD_HEIGHT_HALF = WORLD_HEIGHT / 2;

		//States
		state = 0;

		menu = new SceneMenu();
		game = new SceneGame();
//		physics = new ScenePhysics();

		//Setup the Helper class
//		init();

		//Create a new SpriteBatch
		batch = new SpriteBatch();

		//Adjust the camera to be in good proportions
		camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.update();

		System.out.println("w: " + WORLD_WIDTH + "; h: " + WORLD_HEIGHT);
		System.out.println("ratio: "+ WORLD_RATIO);
	}

	public static Vector2 getWorldCoords(){
		Vector2 world = new Vector2(Gdx.input.getX(), Gdx.input.getY());

		world.x = (world.x / SCREEN_WIDTH) * WORLD_WIDTH;
		world.y = WORLD_HEIGHT - (world.y / SCREEN_HEIGHT) * WORLD_HEIGHT;

		return world;
	}

	public static void changeState(int newState){
		state = newState;
	}

	public static void drawSpriteX(SpriteBatch batch, Sprite sprite) {
		batch.setColor(1f, 1f, 1f, sprite.getColor().a);
		batch.draw(sprite, sprite.getX() - sprite.getWidth()/2, sprite.getY() - sprite.getHeight()/2, sprite.getWidth(), sprite.getHeight());
	}

	public static boolean pointInsideSprite(Vector2 point, Sprite sprite){
		Sprite s = new Sprite();
		s.setSize(sprite.getWidth(), sprite.getHeight());
		s.setPosition(sprite.getX()-sprite.getWidth()/2, sprite.getY()-sprite.getHeight()/2);
		return s.getBoundingRectangle().contains(point.x, point.y);
	}

	@Override
	public void render () {
		camera.update();
		batch.setProjectionMatrix(camera.combined);
//		Gdx.gl.glClearColor(1, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//		float delta = Gdx.graphics.getDeltaTime();
//		Gdx.app.log("Game-render", Float.toString(delta));

		batch.begin();
		if(state == STATE_MENU){
			menu.update();
			menu.render(batch);
		}else if(state == STATE_GAME){
			game.update();
			game.render(batch);
		}
//		else if(state == STATE_PHYSICS){
//			physics.update();
//			physics.render(batch);
//		}
		batch.end();

	}
}
