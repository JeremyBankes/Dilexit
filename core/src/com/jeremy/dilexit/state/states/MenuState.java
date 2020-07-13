package com.jeremy.dilexit.state.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jeremy.dilexit.Dilexit;
import com.jeremy.dilexit.state.State;
import static com.badlogic.gdx.math.MathUtils.*;

public class MenuState extends State {

	private AssetManager assetManager;

	private Viewport viewport;

	private String title;
	private BitmapFont normalFont;
	private BitmapFont fancyFont;
	private Texture background;

	private String[] menu = { "Play", "Credits", "Exit" };
	private int highlighted = 0;
	private boolean selected = false;
	private float selectCountdown = 1.0f;

	private float bobble = 0.0f;

	public MenuState() {
		assetManager = Dilexit.getInstance().getAssetManager();
	}

	@Override
	protected void enter() {
		title = "Esse Delexit";
		normalFont = assetManager.get("normal.fnt");
		fancyFont = assetManager.get("fancy.fnt");
		fancyFont.getData().setScale(2.0f);
		background = assetManager.get("backgrounds.png");
	}

	@Override
	public void update(float deltaTime) {
		final Dilexit game = Dilexit.getInstance();
		final SpriteBatch batch = game.getBatch();

		if (!selected) {
			if (Gdx.input.isKeyJustPressed(Keys.UP) || Gdx.input.isKeyJustPressed(Keys.W)) {
				if (highlighted > 0) highlighted--;
				bobble = 1.0f;
			} else if (Gdx.input.isKeyJustPressed(Keys.DOWN) || Gdx.input.isKeyJustPressed(Keys.S)) {
				if (highlighted < menu.length - 1) highlighted++;
				bobble = 1.0f;
			}
		}
		if (Gdx.input.isKeyJustPressed(Keys.SPACE) || Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			selected = true;
		}

		batch.draw(background, 0, 0);

		GlyphLayout layout = new GlyphLayout(fancyFont, title);
		float x = Dilexit.WIDTH * 1 / 10;
		float y = Dilexit.HEIGHT * 8 / 10;

		batch.setProjectionMatrix(viewport.getCamera().combined);

		float age = Dilexit.getInstance().getAge();
		float shadowX = 4.0f * MathUtils.sin(age * 10);
		float shadowY = 2.0f * MathUtils.sin(age * 3);

		fancyFont.setColor(Color.DARK_GRAY);
		fancyFont.draw(batch, title, x - shadowX, y - shadowY);
		fancyFont.setColor(Color.GRAY);
		fancyFont.draw(batch, title, x, y);

		y -= layout.height * 2;
		x += layout.height;
		float offset;
		for (int i = 0; i < menu.length; i++) {
			String item = menu[i];
			layout = new GlyphLayout(normalFont, item);
			if (highlighted == i) {
				offset = 5.0f * (bobble * sin(10 * PI * bobble / 2) + bobble) / 2;
				if (selected) {
					float fadeFactor = 1.2f;
					normalFont.setColor(selectCountdown, 0, 0, fadeFactor * selectCountdown - fadeFactor + 1.0f);
					float backstep = 0.2f;
					offset += 100 * ((1 - selectCountdown - backstep) * (1 - selectCountdown - backstep) - backstep * backstep);
				} else {
					if (bobble > 0) {
						normalFont.setColor(1.0f, 1.0f - bobble / 2, 1.0f - bobble / 2, 1.0f);
					} else {
						normalFont.setColor(Color.WHITE);
					}
				}
			} else {
				normalFont.setColor(Color.GRAY);
				offset = 0.0f;
			}
			normalFont.draw(batch, item, x + offset, y);
			y -= layout.height * 2;
		}

		if (bobble > 0) {
			bobble -= deltaTime * 3;
			if (bobble < 0) bobble = 0;
		}
		if (selected) {
			if (selectCountdown > 0.0f) {
				selectCountdown -= deltaTime;
			} else {
				select(menu[highlighted]);
				selectCountdown = 1.0f;
				selected = false;
			}
		}
	}

	@Override
	protected void exit() {}

	@Override
	public void onResize(int width, int height) {
		viewport = new FitViewport(Dilexit.WIDTH, Dilexit.HEIGHT);
		viewport.update(width, height);
		viewport.apply(true);
	}

	private void select(String option) {
		switch (option) {
		case "Play":
			Dilexit.getInstance().getStateManager().enter(GameState.class);
			break;
		case "Credits":
			break;
		case "Exit":
			Gdx.app.exit();
			break;
		default:
			assert true;
			break;
		}
	}

}
