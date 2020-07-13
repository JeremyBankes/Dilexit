package com.jeremy.dilexit.state.states;

import static java.lang.Math.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.TimeUtils;
import com.jeremy.dilexit.Dilexit;
import com.jeremy.dilexit.state.State;

public class TransitionState extends State {

	private OrthographicCamera camera;
	private Mesh mesh;

	private Texture testTexture0;
	private Texture testTexture1;

	private SpriteBatch batch;
	private ShaderProgram program;

	@Override
	protected void enter() {
		testTexture0 = Dilexit.getInstance().getAssetManager().get("busy.png");
		testTexture1 = Dilexit.getInstance().getAssetManager().get("busy2.png");

		int triangles = 2;
		int verticies = triangles * 3;
		mesh = new Mesh(true, verticies, 0, new VertexAttribute(Usage.Position, 3, "a_position"),
				new VertexAttribute(Usage.TextureCoordinates, 2, "a_texture_coordinates"));
		mesh.setVertices(new float[] { //
				0.0f, 0.0f, 0.0f, /* */ 0.0f, 0.0f, //
				1.0f, 0.0f, 0.0f, /* */ 1.0f, 0.0f, //
				0.0f, 1.0f, 0.0f, /* */ 0.0f, 1.0f, //
				1.0f, 0.0f, 0.0f, /* */ 1.0f, 0.0f, //
				1.0f, 1.0f, 0.0f, /* */ 1.0f, 1.0f, //
				0.0f, 1.0f, 0.0f, /* */ 0.0f, 1.0f //
		});
		batch = new SpriteBatch();
		program = new ShaderProgram(Gdx.files.internal("transition.vert"), Gdx.files.internal("transition.frag"));
	}

	@Override
	public void update(float deltaTime) {
		batch.begin();
		program.begin();
		testTexture0.bind(0);
		program.setUniformi("u_texture_0", 0);
		testTexture1.bind(1);
		program.setUniformi("u_texture_1", 1);
		program.setUniformf("u_transition", (float) sin((double) TimeUtils.millis() / 1000) / 2 + 0.5f);
		program.setUniformMatrix("u_projection", camera.combined);
		mesh.render(program, GL20.GL_TRIANGLES);
		program.end();
		batch.end();
	}

	@Override
	protected void exit() {
		mesh.dispose();
		batch.dispose();
	}

	@Override
	public void onResize(int width, int height) {
		camera = new OrthographicCamera();
		camera.setToOrtho(true, 1.0f, 1.0f);
	}

}
