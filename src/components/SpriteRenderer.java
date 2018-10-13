package components;

import java.awt.Graphics;
import java.awt.Image;

import coreEngine.ResourceManager;
import misc.Vector2;

//Sprinte render is a component that can be added to an entity
public class SpriteRenderer extends Component{
	
	//The Image that the sprite renderer draws
	private Image image;
	//The size of the image drawn
	private Vector2 size;
	
	//Default constructor
	public SpriteRenderer() {
		
	}
	
	//Make a sprite renderer with an image parameter
	public SpriteRenderer(Image image) {
		this.image = image;
	}
	
	//Set the sprite of the Sprite Renderer
	public void setSprite(String spriteName) {
		image = ResourceManager.GetImage(spriteName);
	}
	
	//Set the default size
	@Override
	public void initialize() {
		size = new Vector2(30,30);
	}
	
	@Override
	public void update() {

	}
	
	//Draw the image on screen
	@Override
	public void draw(Graphics graphics) {
		graphics.drawImage(image, (int)entity.getPosition().x - ((int)size.x/2), (int)entity.getPosition().y - ((int)size.y/2), (int)size.x, (int)size.y, null);
	}
	
	//Accesors
	

	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	
	public Vector2 getSize() {
		return size;
	}
	public void setSize(Vector2 size) {
		this.size = size;
	}
	
}
