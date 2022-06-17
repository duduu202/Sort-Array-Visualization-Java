package Objects;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject 
{
	//protected ObjectId id;
	
	public GameObject()
	{
		System.out.println("GameObject");
		//this.id = id;
		
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);

//	public  ObjectId getId() {
//		return id;
//	}
	
	
}