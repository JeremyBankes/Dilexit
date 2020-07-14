package com.jeremy.dilexit.entity.systems;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class DebugRenderingSystem extends EntitySystem {

	private World world;
	private Camera camera;
	private Box2DDebugRenderer renderer;

	public DebugRenderingSystem(World world, Camera camera) {
		renderer = new Box2DDebugRenderer();
		renderer.setDrawVelocities(true);
		this.world = world;
		this.camera = camera;
	}

	@Override
	public void update(float deltaTime) {
		renderer.render(world, camera.combined);
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

}
