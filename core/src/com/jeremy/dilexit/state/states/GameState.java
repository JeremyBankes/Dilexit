package com.jeremy.dilexit.state.states;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.jeremy.dilexit.entity.DebugRenderingSystem;
import com.jeremy.dilexit.entity.EntityPresenceListener;
import com.jeremy.dilexit.entity.EntityType;
import com.jeremy.dilexit.entity.PhysicsComponent;
import com.jeremy.dilexit.entity.PlayerComponent;
import com.jeremy.dilexit.entity.PlayerControlSystem;
import com.jeremy.dilexit.entity.TypeComponent;
import com.jeremy.dilexit.state.State;

public class GameState extends State {

	private World world;
	private Engine engine;
	private OrthographicCamera camera;

	@Override
	protected void enter() {
		world = new World(new Vector2(), true);
		engine = new Engine();

		engine.addEntityListener(Family.all(TypeComponent.class, PhysicsComponent.class).get(), new EntityPresenceListener(world));
		engine.addSystem(new DebugRenderingSystem(world, camera));
		engine.addSystem(new PlayerControlSystem());

		Entity player = new Entity();
		player.add(new PlayerComponent());
		player.add(new TypeComponent(EntityType.PLAYER));
		player.add(new PhysicsComponent());
		engine.addEntity(player);
	}

	@Override
	public void update(float deltaTime) {
		engine.update(deltaTime);
		world.step(deltaTime, 3, 6);
	}

	@Override
	protected void exit() {

	}

	@Override
	public void onResize(int width, int height) {
		final float screenAspectRatio = (float) width / height;
		if (screenAspectRatio > 16.0f / 9) camera = new OrthographicCamera(10 * screenAspectRatio, 10);
		else camera = new OrthographicCamera(10, 10 / screenAspectRatio);
		engine.getSystem(DebugRenderingSystem.class).setCamera(camera);
	}

}
