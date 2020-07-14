package com.jeremy.dilexit.state.states;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.jeremy.dilexit.Dilexit;
import com.jeremy.dilexit.entity.EntityPresenceListener;
import com.jeremy.dilexit.entity.EntityType;
import com.jeremy.dilexit.entity.components.AnimationComponent;
import com.jeremy.dilexit.entity.components.PhysicsComponent;
import com.jeremy.dilexit.entity.components.PlayerComponent;
import com.jeremy.dilexit.entity.components.TransformComponent;
import com.jeremy.dilexit.entity.components.TypeComponent;
import com.jeremy.dilexit.entity.systems.AnimationSystem;
import com.jeremy.dilexit.entity.systems.DebugRenderingSystem;
import com.jeremy.dilexit.entity.systems.PhysicSystem;
import com.jeremy.dilexit.entity.systems.PlayerControlSystem;
import com.jeremy.dilexit.entity.systems.RenderingSystem;
import com.jeremy.dilexit.state.State;

public class GameState extends State {

	private SpriteBatch batch;

	private World world;
	private Engine engine;
	private OrthographicCamera camera;

	public GameState() {
		batch = new SpriteBatch(100);
	}

	@Override
	protected void enter() {
		world = new World(new Vector2(), true);
		engine = new Engine();

		engine.addEntityListener(Family.all(TypeComponent.class, PhysicsComponent.class).get(), new EntityPresenceListener(world));
		engine.addSystem(new PlayerControlSystem());
		engine.addSystem(new AnimationSystem());
		engine.addSystem(new RenderingSystem());
		engine.addSystem(new PhysicSystem());
		engine.addSystem(new DebugRenderingSystem(world, camera));

		final AssetManager assets = Dilexit.getInstance().getAssetManager();

		Entity player = new Entity();
		player.add(new PlayerComponent());
		player.add(new TypeComponent(EntityType.PLAYER));
		player.add(new PhysicsComponent());
		player.add(new TransformComponent());
		player.add(new AnimationComponent());

		TextureAtlas atlas = assets.get("textures/atlases/wit.atlas");
		AnimationSystem.addAnimations(player, "idle", atlas.findRegion("wit-idle"), 4, 10, 32, 32);
		AnimationSystem.addAnimations(player, "walk", atlas.findRegion("wit-walk"), 7, 10, 32, 32);
		AnimationSystem.addAnimations(player, "fall", atlas.findRegion("wit-fall"), 4, 10, 32, 32);
		AnimationSystem.addAnimations(player, "attack", atlas.findRegion("wit-attack"), 8, 12, 32, 32);

		engine.addEntity(player);

		Entity enya = new Entity();
		enya.add(new TypeComponent(EntityType.NPC));
		enya.add(new PhysicsComponent());
		enya.add(new TransformComponent());
		enya.add(new AnimationComponent());

		atlas = assets.get("textures/atlases/enya.atlas");
		AnimationSystem.addAnimations(enya, "idle", atlas.findRegion("enya-idle"), 4, 6, 32, 32);

		engine.addEntity(enya);
	}

	@Override
	public void update(float deltaTime) {
		batch.setProjectionMatrix(camera.combined);
		engine.update(deltaTime);
		world.step(deltaTime, 1, 1);
	}

	@Override
	protected void exit() {
		batch.dispose();
	}

	@Override
	public void onResize(int width, int height) {
		final float screenAspectRatio = (float) width / height;
		final float across = 5;
		if (screenAspectRatio > 16.0f / 9) camera = new OrthographicCamera(across, across / screenAspectRatio);
		else camera = new OrthographicCamera(across * screenAspectRatio, across);
		engine.getSystem(DebugRenderingSystem.class).setCamera(camera);
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

}
