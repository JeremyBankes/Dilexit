package com.jeremy.dilexit.entity.systems;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class DebugRenderSystem extends EntitySystem {

	private Box2DDebugRenderer renderer;

	public DebugRenderSystem() {
		renderer = new Box2DDebugRenderer();
		renderer.setDrawAABBs(true);
		renderer.setDrawBodies(true);
		renderer.setDrawContacts(true);
		renderer.setDrawInactiveBodies(true);
		renderer.setDrawJoints(true);
		renderer.setDrawVelocities(true);
	}

	@Override
	public void update(float deltaTime) {
		final World world = getEngine().getSystem(PhysicsSystem.class).getWorld();
		final Camera camera = getEngine().getSystem(RenderSystem.class).getCamera();
		renderer.render(world, camera.projection);
	}

}
