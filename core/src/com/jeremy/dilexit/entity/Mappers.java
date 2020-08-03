package com.jeremy.dilexit.entity;

import com.badlogic.ashley.core.ComponentMapper;
import com.jeremy.dilexit.entity.components.AnimationComponent;
import com.jeremy.dilexit.entity.components.ClickComponent;
import com.jeremy.dilexit.entity.components.HoverComponent;
import com.jeremy.dilexit.entity.components.PositionComponent;
import com.jeremy.dilexit.entity.components.SizeComponent;
import com.jeremy.dilexit.entity.components.TextureComponent;
import com.jeremy.dilexit.entity.components.TextureRegionComponent;

public class Mappers {

	public static final ComponentMapper<ClickComponent> CLICK = ComponentMapper.getFor(ClickComponent.class);
	public static final ComponentMapper<HoverComponent> HOVER = ComponentMapper.getFor(HoverComponent.class);
	public static final ComponentMapper<PositionComponent> POSITION = ComponentMapper.getFor(PositionComponent.class);
	public static final ComponentMapper<SizeComponent> SIZE = ComponentMapper.getFor(SizeComponent.class);
	public static final ComponentMapper<TextureComponent> TEXTURE = ComponentMapper.getFor(TextureComponent.class);
	public static final ComponentMapper<TextureRegionComponent> TEXTURE_REGION = ComponentMapper.getFor(TextureRegionComponent.class);
	public static final ComponentMapper<AnimationComponent> ANIMATION = ComponentMapper.getFor(AnimationComponent.class);
//	public static final ComponentMapper< >     = ComponentMapper.getFor(   .class);
//	public static final ComponentMapper< >     = ComponentMapper.getFor(   .class);

}
