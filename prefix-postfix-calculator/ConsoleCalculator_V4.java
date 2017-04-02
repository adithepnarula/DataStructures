import java.util.ArrayList;
import java.util.Scanner;
import java.beans.Expression;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * This class contains the main logic of the program.
 * It accepts users infix expression in a file from the command line, 
 * converts expression to postfix, evaluates it, and the write the results in an output file
 * 
 * @author adithepnarula
 * @version 4
 */

public class ConsoleCalculator_V4 {
	
	/**
	 * The main method obtains file name from the command line and uses try/catch clauses if exceptions occur.
	 * Main method also checks whether each expression in the file is valid.
	 * It writes the result to another output file.
	 * @param args array that contains file names from command line
	 */
	
	public static void main(String[] args){
	
		
		
		//if user didn't enter a file name for input file, program exits
		if (args.length <= 1) {
			System.err.println("File name not included");
			System.exit(0);
		}
		
		
				try {
					String[] expressionArray = readFile(args[0]);
					PrintWriter writer = new PrintWriter(args[1], "UTF-8");
				
					//loop through each expression
					for(int i = 0; i < expressionArray.length; i ++ ) {
						
						
						try {
							
							//if expression is blank throw an expression
							if (expressionArray[i].equals("")) {
								throw new PostFixException("User entered empty expression");
							}	
							
							
						//test whether there are matching brackets, method will throw exception if not
						//below statements will not execute for this iteration if brackets don't match, 
						//and next expression will be evaluated
						ExpressionTools_V4.testMatchingBrackets(expressionArray[i]);
						
						//obtain postfix expression
						String postFixExpression = ExpressionTools_V4.convertInfixToPostfix(expressionArray[i]);
					
						//obtain evaluated result
						int evaluatedExpression = ExpressionTools_V4.evaluatePostfix(postFixExpression);
				
						//write evaluated result to file    
						writer.println(evaluatedExpression);
						
					
				
						}//exit try
					
						//catch exception if expression has a divided by 0
						catch(ArithmeticException e) {
							writer.println("INVALID");
								
						}
						
						//catch postfix exceptions thrown from expression tools class
						catch(PostFixException e) {
							writer.println("INVALID");
						}
						
						//catch other exceptions
						catch (Exception e) {
							writer.println("INVALID");
						}
					
					
				}//exit for loop
				
					//close writer 
					writer.close();
	
				}//exit try clause
				
				//catch file exceptions
				catch (FileNotFoundException e) {
					System.err.println("Error. File name entered is not found.");
					System.exit(0);
				
				} catch (IOException e){
					System.err.println("File not found!");
					System.exit(0);
				}
				
				catch (Exception e) {
					System.err.println(e.getMessage());
					System.exit(0);
				}
				
				
	
	}
	
	
	/**
	 * This method reads the words from the input file and adds it into in an array
	 * @param file the input file name entered by the user in command line 
	 * @return returns an array with each element as the expression in the input file.
	 * @throws IOException an exception will be thrown if the file is not found
	 */

	public static String[] readFile(String file) throws IOException {
		String[] expressionArray;
		String line;

		ArrayList<String> expressionTempArray = new ArrayList<String>();
		
		FileReader theFile = new FileReader(file);
		BufferedReader reader = new BufferedReader(theFile);
		
		line = reader.readLine();
		while (line != null) {
			expressionTempArray.add(line);
			line = reader.readLine();
		}
		
		reader.close();
		expressionArray = expressionTempArray.toArray(new String[expressionTempArray.size()]);
		return expressionArray;
		
	}
	
	
}
