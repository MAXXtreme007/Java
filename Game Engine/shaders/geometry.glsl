#version 120
#extension GL_ARB_geometry_shader4 : enable
//UNIFORM
uniform mat4 ProjectionMatrix;
//VARYING
varying in vec2 VTexCoord0[3];   //[3] because this makes a triangle
varying in vec3 VHalfVector0[3];
varying in vec3 VEyeNormal[3];
varying in vec3 VEyeVertex[3];
varying out vec2 TexCoord0;
varying out vec3 HalfVector0;
varying out vec3 EyeNormal;
//GLOBALFUNCTION
void main()
{
	int i;
	vec3 newVertex;
	//Pass through the original vertex
	for(i = 0; i < gl_VerticesIn; i++)
	{
		gl_Position = gl_PositionIn[i];
		TexCoord0 = VTexCoord0[i];
		HalfVector0 = VHalfVector0[i];
		EyeNormal = VEyeNormal[i];
		EmitVertex();
	}
	EndPrimitive();
	//Push the vertex out a little using the normal
	for(i = 0; i < gl_VerticesIn; i++)
	{
		newVertex = VEyeNormal[i] + VEyeVertex[i];
		gl_Position = ProjectionMatrix * vec4(newVertex, 1.0);
		TexCoord0 = VTexCoord0[i];
		HalfVector0 = VHalfVector0[i];
		EyeNormal = VEyeNormal[i];
		EmitVertex();
	}
	EndPrimitive();
}