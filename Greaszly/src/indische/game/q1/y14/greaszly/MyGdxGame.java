package indische.game.q1.y14.greaszly;

import Proses.GamePlay;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	//-------------------------------
	GamePlay gamePlay = new GamePlay();	

	@Override
	public void create() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();
		//---------------------------------
		gamePlay.Create();
	}

	@Override
	public void dispose() {
		batch.dispose();
		//------------------
		System.gc();
		gamePlay.Dispose();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		camera.update();	
		batch.setProjectionMatrix(camera.combined);
		//----------------------------------------------
		gamePlay.Update(batch, camera);
	}
}
