attribute vec4 a_position;
attribute vec2 a_texture_coordinates;

uniform mat4 u_projection;

varying vec2 v_texture_coordinates;
varying vec2 v_position;

void main() {
	v_texture_coordinates = a_texture_coordinates;
	v_position = a_position.xy;
    gl_Position = u_projection * a_position;
}