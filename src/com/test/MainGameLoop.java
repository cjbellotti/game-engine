package com.test;

import models.RawModel;
import models.TexturedModel;

import org.lwjgl.opengl.Display;

import shaders.StaticShader;
import textures.ModelTexture;

import com.renderengine.DisplayManager;
import com.renderengine.Loader;
import com.renderengine.Renderer;

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		
		Renderer renderer = new Renderer();
		
		StaticShader shader = new StaticShader();
		
		/* float[] vertices1 = {
				
				-0.6f, 0.5f, 0f,
				-0.6f, -0.5f, 0.f,
				0.4f, -0.5f, 0f,
		};
		
		float[] vertices2 = {
				0.6f, -0.5f, 0f,
				0.6f, 0.5f, 0f,
				-0.4f, 0.5f, 0f

		};
		
		RawModel model1 = loader.loadToVAO(vertices1);
		RawModel model2 = loader.loadToVAO(vertices2); */
		
		float[] vertices = {
		
				-0.5f, 0.5f, 0,		// V0
				-0.5f, -0.5f, 0,	// V1
				0.5f, -0.5f, 0, 	// V2
				0.5f, 0.5f, 0, 		// V3
				0.7f, 0.8f, 0		// V4
				
		};
		
		int[] indices = {
				0, 1, 3,
				3, 1, 2 
		};
		
		float[] textureCoords = {
			
				0,0,	// V0
				0,0.1f,	// V1
				0.1f,0.1f,	// V2
				0.1f,0		// V3

		};

		RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
		ModelTexture texture = new ModelTexture(loader.loadTexture("textura"));
		TexturedModel texturedModel = new TexturedModel (model, texture);
		
		while(!Display.isCloseRequested()) {
			
			renderer.prepare();
			// Game logic
			shader.start();
			renderer.render(texturedModel);
			shader.stop();
			DisplayManager.updateDisplay();
			
		}
		
		loader.cleanUp();
		DisplayManager.closeDisplay();
		
	}

}
