package lab07;

import java.util.*;

//Extension of Chapter 14.4 Case Study: Expression Evaluator

public class Postfixer {

	/**
	 * Determines if the first operator has same or greater precedence than the
	 * second
	 *
	 * @param op1 the first operator
	 * @param op2 the second operator
	 * @return the boolean result
	 */
	private static boolean hasPrecedence(String op1, String op2) {
		if (opToPrcd(op1) >= opToPrcd(op2))
			return true;
		else
			return false;
	}

	/**
	 * Converts an operator to its precedence priority
	 *
	 * We expect you to be able to handle +, -, *, /, ^, and ( (why don't you need
	 * ")" as well? see algorithm in part 4)
	 *
	 * The order of these is as follows: ^, * and /, + and -, (
	 *
	 * @param op a string representing an operator, e.g. "+" or "-"
	 * @return an integer value reflecting its precedence
	 */
	private static int opToPrcd(String op) {
		if (op.equals("^"))
			return 5;
		if (op.equals("*") || op.equals("/"))
			return 4;
		if (op.equals("+") || op.equals("-"))
			return 3;
		if (op.equals("("))
			return 2;
		else
			return 0;
	}

	/**
	 * determines if the input token is an operator
	 *
	 * @param token the string token to check
	 * @return a boolean reflecting the result
	 */
	private static boolean isOperator(String token) {
		if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("^")
				|| token.equals("(") || token.equals(")"))
			return true;
		else
			return false;
	}

	/**
	 * Evaluates an expression
	 *
	 * NOTE Beware the order of pop and evaluation when receiving operand1 and
	 * operand2 as input.
	 *
	 * @param operand1 the first operand
	 * @param operator the operator to apply
	 * @param operand2 the second operand
	 * @return a double expressing the result
	 * @throws IllegalArgumentException if operator passed is not one of "+", "-",
	 *                                  "*", "/", or "^"
	 *
	 */
	private static double evaluate(double operand1, String operator, double operand2) {

		if (operator.equals("+"))
			return operand1 + operand2;
		if (operator.equals("-"))
			return operand2 - operand1;
		if (operator.equals("*"))
			return operand1 * operand2;
		if (operator.equals("/"))
			return operand2 / operand1;
		if (operator.equals("^"))
			return Math.pow(operand2, operand1);
		else
			throw new IllegalArgumentException();
	}

	/**
	 * give a description of the purpose of this method
	 * 
	 * @param line fill in
	 * @return fill in
	 */
	public static double infixEvaluator(String line) {

		StringSplitter data = new StringSplitter(line);
		Stack<String> operators = new Stack<String>();
		Stack<Double> operands = new Stack<Double>();

		while (data.hasNext() == true) {

			String token = data.next();
			if (token.equals("(")) {
				operators.push(token);
			}
			else if (token.equals(")")) {
				while (!operators.peek().equals("(")) {
					String op1 = operators.pop();
					double val1 = operands.pop();
					double val2 = operands.pop();

					double result = evaluate(val1, op1, val2);
					operands.push(result);
				}
				operators.pop();
			} else if (isOperator(token) == true) {
				while (!operators.empty() 
						&& opToPrcd(operators.peek()) >= opToPrcd(token)) {

					String op2 = operators.pop();
					double val3 = operands.pop();
					double val4 = operands.pop();

					double result2 = evaluate(val3, op2, val4);
					operands.push(result2);
				} 
				operators.push(token);
			} else {
				operands.push(Double.parseDouble(token));
			}

		}

		while (operators.empty() == false) {
			String op5 = operators.pop();
			double val5 = operands.pop();
			double val6 = operands.pop();

			double result5 = evaluate(val5, op5, val6);
			operands.push(result5);
		}
		return operands.pop();
	}

	/**
	 * give a description of the purpose of this method
	 * 
	 * @param line fill in
	 * @return fill in
	 */
	public static String toPostfix(String line) {

		StringSplitter data = new StringSplitter(line);
		Stack<String> operators = new Stack<String>();
		StringBuilder postfix = new StringBuilder();

		while (data.hasNext() == true) {

			String token = data.next();
			if (isOperator(token) == false) {
				postfix.append(token);
			} else if (token.equals("(")) {
				operators.push(token);
			}

			else if (token.equals(")")) {

				while (!operators.peek().equals("(")) {

					postfix.append(operators.pop());

				}
				operators.pop();
			}

			else if (!operators.empty() && opToPrcd(operators.peek()) >= opToPrcd(token)) {

				String op2 = operators.pop();
				postfix.append(op2);
				operators.push(token);
			} else {
				operators.push(token);
			}

		}

		return postfix.toString();
	}

	public static void main(String[] args) {

		if (!toPostfix(new String("(4+5)")).equals("45+"))
			System.err.println("test1 failed --> should have been 45+");

		if (!toPostfix(new String("((4+5)*6)")).equals("45+6*"))
			System.err.println("test2 failed --> should have been 45+6*");

		// System.out.println(toPostfix(new String("((4+((5*6)/7))-8)")));
		// System.out.println(toPostfix(new String("((4+5*(6-7))/8)")));
		// System.out.println(toPostfix(new String("(9+(8*7-(6/5^4)*3)*2)")));

		if (!toPostfix(new String("((4+((5*6)/7))-8)")).equals("456*7/+8-"))
			System.err.println("test3 failed --> should have been 456*7/+8-");

		if (!toPostfix(new String("((4+5*(6-7))/8)")).equals("4567-*+8/"))
			System.err.println("test4 failed --> should have been 4567-*+8/");

		if (!toPostfix(new String("(9+(8*7-(6/5^4)*3)*2)")).equals("987*654^/3*-2*+"))
			System.err.println("test5 failed --> should have been 987*654^/3*-2*+");

		System.out.println("Assignment Testing Done!!!");

		System.out.println(infixEvaluator("10 + 2"));
		System.out.println(infixEvaluator("10 - 2 * 2 + 1"));
		System.out.println(infixEvaluator("100 * 2 + 12"));

		if (infixEvaluator("10 + 2") != 12)
			System.err.println("test1 failed --> your answer should have been 12");

		if (infixEvaluator("10 - 2 * 2 + 1") != 7)
			System.err.println("test1 failed --> your answer should have been 12");

		if (infixEvaluator("100 * 2 + 12") != 212)
			System.err.println("test2 failed --> your answer should have been 212");

		if (infixEvaluator("100 * ( 2 + 12 )") != 1400)
			System.err.println("test3 failed --> your answer should have been 1400");

		if (infixEvaluator("100 * ( 2 + 12 ) / 14") != 100)
			System.err.println("test4 failed --> your answer should have been 100");

		System.out.println("Lab Testing Done!!!");

	}

}
