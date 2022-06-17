package Principal;

import java.awt.Graphics;
import java.util.LinkedList;

import Objects.GameObject;


public class Handler 
{
	
	static public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	
	private static int objetivo;
	
	public void tick() 
	{
		//System.out.println("hendler tick");
		for(int i=0; i < object.size(); i++) {
			
			tempObject = object.get(i);
			
			tempObject.tick();
			//tempObject.
			
		}
		
	}
	
	public void render (Graphics g) {
		//System.out.println("hendler render");
		for(int i=0; i < object.size(); i++) {
			tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	public void clearAll() {
		this.object.clear();
	}
	
	public static int getObjetivo() {
		return objetivo;
	}
}
