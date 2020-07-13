#ifdef GL_ES
precision mediump float;
#endif

uniform sampler2D u_texture_0;
uniform sampler2D u_texture_1;
uniform float u_transition;

varying vec2 v_texture_coordinates;
varying vec2 v_position;

void main() {
	vec4 primary_color = texture2D(u_texture_0, v_texture_coordinates);
	vec4 secondary_color = texture2D(u_texture_1, v_texture_coordinates);
	
    gl_FragColor = mix(primary_color, secondary_color, u_transition * v_position.x);
}