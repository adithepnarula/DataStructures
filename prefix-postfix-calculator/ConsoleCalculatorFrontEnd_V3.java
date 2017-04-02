
import java.io.PrintWriter;

import processing.core.*;

/**
 * This class provides a graphical front end of a calculator 
 * @author adithepnarula
 *
 */
public class ConsoleCalculatorFrontEnd_V3 extends PApplet {
	
	Button[] buttons;
	boolean buttonPressed = false;
	String currentToken;
	String userExpression = "";
	boolean evaluated = false;
	int state = 0;
	
	public void setup() {
		
		size(400, 750);
		buttons = new Button[19];
		
		int xPos = 100;
		int yPos = 100;
		int count = 0;
		
		//loop through each button and add it to the calculator
		for (int i = 0; i < buttons.length; i ++, count++) {
			
			if (count % 3 == 0) {
				xPos = 50;
				yPos += 80;
			
			}
			
			if ( i < 10) {
				buttons[i] = new Button(this, "" + i, xPos, yPos);
			}
			else if (i == 10) {
				buttons[i] = new Button(this, "+", xPos, yPos);
			}
			else if (i == 11){
				buttons[i] = new Button(this, "-", xPos, yPos);
			}
			else if (i == 12) {
				buttons[i] = new Button(this, "*", xPos, yPos);
			}
			else if (i == 13) {
				buttons[i] = new Button(this, "/", xPos, yPos);	
			}
			else if (i == 14) {
				buttons[i] = new Button(this, "(", xPos, yPos);
			}
			else if (i == 15) {
				buttons[i] = new Button(this, ")", xPos, yPos);
			}
			else if (i == 16) {
				buttons[i] = new Button(this, "=", xPos, yPos);
			}
			else if (i == 17) {
				buttons[i] = new Button(this, "C", xPos, yPos);
			}
			else {
				buttons[i] = new Button(this, "SPACE", xPos, yPos, 300, 50);
			}
		
			
			xPos += 120;
		}
		
	}
		
		
		
	
	
	public void draw() {
		
		//set up state, if user press anything move to live state
		if (state == 0) {
			
			fill(255);
			rect(50,50,300,100);
			textSize(20);

			fill(0);
			text("Add space after every token", 75, 80);
			text("Press C to delete everything", 75, 100);
			text("Press any button to begin", 75, 120);
			for (int i = 0; i < buttons.length; i ++ ) {
				buttons[i].display();
				
				fill(0);
				text(buttons[i].myText, buttons[i].xPos + 15, buttons[i].yPos + 30);

				if(mousePressed){
					state = 1;
				}
			
			}//exit for loop
					
				
		}
		
		//live state
		else {
		
		fill(255);
		rect(50,50,300,100);
		textSize(20);

		fill(0);
		text(this.userExpression, 100, 100);
		for (int i = 0; i < buttons.length; i ++ ) {
			buttons[i].display();
			
			fill(0);
			text(buttons[i].myText, buttons[i].xPos + 15, buttons[i].yPos + 30);

			if (buttons[i].detectCollision(mouseX, mouseY)) {
				buttonPressed = true;
				currentToken = buttons[i].myText;
				break;
			}
		}//exit for loop
			
			
		}//end of else
		
	
		
	}
	
	public void mousePressed(){
		
		if (buttonPressed == true) {
			
			if (currentToken.equals("C")) {
				//delete the last element 
				deleteLastToken();
			}
			
			else if (currentToken.equals("SPACE")) {
				this.userExpression += " ";
			}
			
			else if(currentToken.equals("=")) {
				finalizeExpression();
			}
			
			else {
				this.userExpression += currentToken;
			 
				
			}
			
			
		}
		
		
		buttonPressed = false;

	
		
	}
	
	public void deleteLastToken() {
	

		userExpression = "";
		
	
	}
	
	//add space, add expression to ArrayList, reset userExpression
	public void finalizeExpression(){
		evaluateExpression(userExpression);
	}
	
	public void evaluateExpression(String expression) {
	
				try {

					
				if (expression.equals("")) {
					throw new PostFixException("User entered empty expression");
				}
			
				ExpressionTools_V4.testMatchingBrackets(expression);
				
				//obtain postfix expression
				String postFixExpression = ExpressionTools_V4.convertInfixToPostfix(expression);
			
				//obtain evaluated result
				int evaluatedExpression = ExpressionTools_V4.evaluatePostfix(postFixExpression);
				

				this.userExpression = ""+evaluatedExpression;
				

				}
			

			
			catch(ArithmeticException e) {
				this.userExpression = "INVALID";
					
			}
				
			catch(PostFixException e) {
				this.userExpression = "INVALID";
			
			}
				
			catch (Exception e) {
				this.userExpression = "INVALID";
			}
				
	}


}
