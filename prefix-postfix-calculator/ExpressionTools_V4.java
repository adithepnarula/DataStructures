import java.util.*;

/**
 * This class converts the infix expression to postfix and evaluates the postfix expression
 * This class also throws postfix exceptions
 * @author adithepnarula
 *
 */
public class ExpressionTools_V4 {
	

	//make constructor private so user cannot create object
	private ExpressionTools_V4(String expression){
		
	
	}
	
	/**
	 * This method accepts user's infix expression, converts it to postfix, and returns it
	 * @param expression user's infix expression 
	 * @return return the postfix expression if no exceptions occur
	 * @throws PostFixException 
	 * 	If expression contains invalid token an exception will be thrown
	 * 
	 */
	public static String convertInfixToPostfix(String expression) throws PostFixException {
		
		StackObject<String> stackOperator = new StackObject<String>();
	
		Scanner expressionArray = new Scanner(expression);
	
		String postExpression = "";
		
	
	
		//loop through each token
		while(expressionArray.hasNext()){
			String token = expressionArray.next();
			
			//check if token is valid
			if(!isToken(token)) {
				throw new PostFixException("Invalid token");
			}
			
			//check if token is operand
			if (isOperand(token)){
				postExpression += token+" ";
			}
			
			//check if token is left brace
			else if (isLeftBrace(token)){
				stackOperator.push(token);
			}
			
			//check if token is an operator
			else if (isOperator(token)){
				
				//if stack is not empty, check for higher precedence and pop the current operator out
				if(!stackOperator.isEmpty()) {
					while (hasHigherPrecedence(token, stackOperator.peek(), stackOperator)) {
						String topStack = stackOperator.pop();
						postExpression += topStack + " ";	
									
					}//exit while loop	
				}
				
				//push token in the stack
				stackOperator.push(token);
			
			}
			
			//check if token is a right brace
			//if it is, then pop left brace
			else if (isRightBrace(token)){
				while(!stackOperator.isEmpty()){
					if (!isLeftBrace(stackOperator.peek())){
						String topStack = stackOperator.pop();
						postExpression += topStack + " ";
					}
					else {
						stackOperator.pop();
						break;
					}
				}//exit while loop
			}
		
		}//exit for loop
		
		//while stack is not empty, pop and add it to the postfix expression
		while (!stackOperator.isEmpty()){
			String topStack = stackOperator.pop();
			postExpression += topStack + " ";
		}
		
		return postExpression;
		
	}
	
	/**
	 * This method takes infix expression and checks whether brackets are matched
	 * @param expression user's infix expression
	 * @throws PostFixException 
	 * 	throw exception if bracket is not matched
	 */
	public static void testMatchingBrackets(String expression) throws PostFixException {
		String[] expressionArray = expression.split(" ");
		StackObject<String> myStack = new StackObject<String>();

		//loop through each token and ignore if token is not a brace
		for (int i = 0; i < expressionArray.length; i ++) {
			String token = expressionArray[i];
			
			//if token is left brace, push it into stack
			if(isLeftBrace(token)) {
				myStack.push(token);
			}
			
			//if token is right brace, pop left brace
			if(isRightBrace(token)) {
			
				if(!matchingBrackets(myStack.pop(), token)) {
					throw new PostFixException("Too many right brace");
				}
			}
			
			
		}//exit for loop
		if (!myStack.isEmpty()) {
			throw new PostFixException("Too many left brace");
		}

	}
	
	/**
	 * Helper method for testMatchingBrackets. Accepts left and right brace
	 * and check whether they match. 
	 * @param open the left brace that is on top of the stack
	 * @param close the right brace 
	 * @return
	 * 		return true if brackets match, false otherwise
	 */
	
	private static boolean matchingBrackets(String open, String close) {
		if (open == null || close == null) {
			return false;
		}
		else if (open.equals("(") && close.equals(")")){
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Checks whether token is valid
	 * @param letter a token from the expression
	 * @return 
	 * 		return true if token is valid, false otherwise
	 */
	private static boolean isToken(String letter) {
		//set flag to false
		boolean isToken = false;
		
		//call the four helper methods to check whether token is valid
		if (isOperator(letter) || isLeftBrace(letter) || isRightBrace(letter) ||
				isOperand(letter)) {
			isToken = true;
			}
		return isToken;
		
	}

	/**
	 * Helper method that checks whether a token is an operator
	 * @param letter a token from the expression
	 * @return
	 * 	return true if token is operator, false otherwise
	 */
	private static boolean isOperator(String letter){
		String[] operators = {"+","-","/","*"};
		for (int i = 0; i < operators.length; i++) {
			if(letter.equals(operators[i])){
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Helper method that checks whether a token is left brace
	 * @param letter a token from the expression
	 * @return
	 * 	return true if token is left brace, false otherwise
	 */
	private static boolean isLeftBrace(String letter){
		if (letter.equals("(")) {
			return true;
		}
		return false;
	}
	

	/**
	 * Helper method that checks whether a token is right brace
	 * @param letter a token from the expression  
	 * @return
	 * 	return true if token is right brace, false otherwise
	 */
	private static boolean isRightBrace(String letter){
		if (letter.equals(")")) {
			return true;
		}
		return false;
		
	}
	

	/**
	 * Helper method that checks whether a token is operand
	 * @param letter a token from the expression  
	 * @return
	 * 	return true if token is an operand, false otherwise
	 */
	
	private static boolean isOperand(String letter){
		boolean isValid = true;
		for (int i = 0; i < letter.length(); i++) {
			if (!Character.isDigit(letter.charAt(i))) {
				isValid = false;
			}
		}
		return isValid;
	}
	
	/**
	 * This method checks whether the operator on the top of stack or the current operator token
	 * has a higher precedence
	 * Note that I worked with Adisa Narula on this method
	 * @param token current token from the expression
	 * @param top operator from the top of the stack
	 * @param stackOperator stack that contains expression values
	 * @return
	 * 	return true if top of stack has higher precedence than current token
	 */
	private static boolean hasHigherPrecedence(String token, String top, StackObject<String> stackOperator) {
	 
		if (stackOperator.isEmpty()) {
			return false;
		}
		
		if ((isOperator(top))) {
		   if (operatorValue(top) >= operatorValue(token)) {
		    return true;
		   }
		   
		  }//exit out if
		  
		  return false;
		 }
	
	/**
	 * Helper method that determines the precedence value. 
	 * @param operator an operator argument from hasHigherPrecedence method 
	 * @return returns an int value for each operator
	 */
	private static int operatorValue(String operator) {
		  
		  if (operator.equals("+") || operator.equals("-")) {
		   return 0;
		  }
		  
		  else {
		   return 1;
		  }
		 }


	/**
	 * This method evaluates the given postfix expression
	 * @param expression postfix expression
	 * @return value of the evaluated expression
	 * @throws PostFixException
	 * 	exception will be thrown if there are not enough operands or too many operands 
	 * 	in the postfix expression
	 */
	public static int evaluatePostfix(String expression) throws PostFixException{
	
		StackObject<Integer> myStack = new StackObject<Integer>();
		String[] expressionArray = expression.split(" ");
	
		
		//loop through each token
		for (int i = 0; i < expressionArray.length; i++){
			String token = expressionArray[i];
			
			//if token is operand convert it to int and push it into stack
			if (isOperand(token)){
				int num = Integer.parseInt(token);
				myStack.push(num);
			}
			
			//if token is operator pop out two elements from stack and perform the operation
			//then push it back into the stack
			else if (isOperator(token)) {
				
				if (myStack.isEmpty()){
					throw new PostFixException("Not enough operands");
				}
				
				int operand2 = myStack.pop();
				
				if (myStack.isEmpty()) {
					throw new PostFixException("Not enough operands");
				}
				
				int operand1 = myStack.pop();
				
				
				int result = 0;
				
				switch (token) {
				case "+": result = operand1 + operand2;
					break;
				case "-": result = operand1 - operand2;
					break;
				case "*": result = operand1 * operand2;
					break;
				case "/": result = operand1 / operand2;
					break;
				}
				
		
				myStack.push(result);
				
				
				}
				
			}//exit for loop
		
		//obtain the result
		int finalResult = myStack.peek();
		myStack.pop();
		
		//if stack is not empty, then throw exception
		if (!myStack.isEmpty()) {
			throw new PostFixException("Too many operands");
		}
	
		
		return finalResult;
		
			
			
		}
	}


	

