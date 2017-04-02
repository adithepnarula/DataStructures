import processing.core.*;

/**
 * This class is a blueprint for button   
 * @author adithepnarula
 *
 */
public class Button extends PApplet{
	
	ConsoleCalculatorFrontEnd_V3 parent;
	String myText;
	int xPos;
	int yPos;
	int width;
	int height; 
	PFont font;
	
	/**
	 * Constructor to set up the button
	 * @param consoleCalculatorFrontEnd_V2 console calculator object
	 * @param text string that will be written on the button
	 * @param xPos x location of the button
	 * @param yPos y location of the button
	 */
	public Button(ConsoleCalculatorFrontEnd_V3 consoleCalculatorFrontEnd_V2, String text, int xPos, int yPos) {
		this.parent = consoleCalculatorFrontEnd_V2;
		this.myText = text;
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = 50;
		this.height = 50;
		
	}
	
	
	
	/**
	 * Overloaded constructor to set up button
	  * @param consoleCalculatorFrontEnd_V2 console calculator object
	 * @param text string that will be written on the button
	 * @param xPos x location of the button
	 * @param yPos y location of the button
	 * @param width width of the button
	 * @param height height of the button
	 */
	public Button(ConsoleCalculatorFrontEnd_V3 consoleCalculatorFrontEnd_V2, String text, int xPos, int yPos, int width, int height) {
		this.parent = consoleCalculatorFrontEnd_V2;
		this.myText = text;
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * method that displays the button  
	 */
	public void display(){
		parent.fill(255,0,0);
		parent.rect(xPos, yPos, this.width , this.height);
	
	}
	
	/**
	 * This method detects whether mouse is within the button region
	 * @param x x location of the mouse
	 * @param y y location of the mouse
	 * @return returns true if in region, false otherwise 
	 */
	public boolean detectCollision(float x, float y) {
		if ( x > xPos && x < xPos + this.width && y > yPos && y < yPos + this.height) {
			return true;
		}
		
		return false;
		
	}
	

}
