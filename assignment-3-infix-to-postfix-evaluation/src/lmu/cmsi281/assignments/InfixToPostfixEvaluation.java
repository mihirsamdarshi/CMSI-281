package lmu.cmsi281.assignments;

/**
 * CMSI Assignment 2
 * 
 * @author <Samdarshi, Mihir>
 *
 */

import java.util.ArrayList;
import java.util.Stack;

public class InfixToPostfixEvaluation {

    public static int getPrecedence(String operator) {
        // Addition and subtraction holds least precedence
        if (operator.equals("+") || operator.equals("-")) {
            return 1;
        }
        // Multiplication and division hold higher precedence
        if (operator.equals("*") || operator.equals("/")) {
            return 2;
        }
        // Exponent holds highest precedence
        if (operator.equals("^")) {
            return 3;
        }
        // Invalid operand
        return -1;
    }

    public static boolean isDigit(String s) {
        try  {  
            toDigit(s);
        } catch (NumberFormatException e) {  
            return false;  
        }  

        return true;  
    }

    public static int toDigit(String s) throws NumberFormatException {
        return Integer.parseInt(s);  
    }

    public static boolean isValidSymbol(String s) {
        if (isDigit(s)) {
            return true;
        }

        switch (s) {
        case "+":
        case "-":
        case "*":
        case "/":
        case "^":
        case "(":
        case ")":
            return true;
        }
        return false;
    }

    public static ArrayList<String> infixToPostfix(String infix) throws RuntimeException {

        ArrayList<String> result = new ArrayList<String>();
        Stack<String> stack = new Stack<String>();

        // Splits the infix based on spaces: "1 + 2" --> [ 1, +, 2 ]
        String[] tokens = infix.split(" ");
        
        for (int i = 0; i < tokens.length; i++) {
            if (!isValidSymbol(tokens[i])) {
                throw new RuntimeException();
            }
        }

        int parentheses = 0;

        for (int i = 0; i < tokens.length; i++) {
            if (isDigit(tokens[i])) {
                result.add(tokens[i]);
            } else if (getPrecedence(tokens[i]) == -1) {
                if (tokens[i].equals("(")) {
                    stack.push(tokens[i]);
                    parentheses++;
                } else if (tokens[i].equals(")")) {
                    while (!stack.peek().equals("(")) {
                        result.add(stack.pop());
                    }
                    stack.pop();
                    parentheses--;
                }
            } else {
                if (stack.isEmpty()) {
                    stack.push(tokens[i]);
                } else {
                    while (!stack.isEmpty()) {
                        if (getPrecedence(stack.peek()) >= getPrecedence(tokens[i])) {
                            result.add(stack.pop());
                        }
                    }
                    stack.push(tokens[i]);
                }
                
            }
        }

        if (parentheses == 0) {
            while (!stack.empty()) {
                result.add(stack.pop());
            }
        } else {
            throw new RuntimeException();
        }

        return result;
    }

    public static Integer evaluatePostfix(ArrayList<String> postfix) throws RuntimeException {

        Stack<String> operands = new Stack<String>();

        for (int i = 0; i < postfix.size(); i++) {
            if (!isValidSymbol(postfix.get(i))) {
                throw new RuntimeException();
            } else if (isDigit(postfix.get(i))) {
                operands.add(postfix.get(i));
            } else {
                if (operands.size() >= 2) {
                    int b = Integer.parseInt(operands.pop());
                    int a = Integer.parseInt(operands.pop());
                    int output = evaluate(postfix.get(i), a, b);
                    operands.push(Integer.toString(output));
                }
            }
        }
        
        int result = Integer.parseInt(operands.pop());

        return result;
    }

    public static Integer evaluate(String operator, int a, int b) throws RuntimeException {

        if (operator.equals("+")) {
            return a + b;
        }

        if (operator.equals("-")) {
            return a - b;
        }

        if (operator.equals("*")) {
            return a * b;
        }

        if (operator.equals("/")) {
            return a / b;
        }

        if (operator.equals("^")) {
            return (int)Math.pow(a, b);
        }
        // Invalid operator
        throw new RuntimeException();
    }

    public static void main(String[] args) {
        String infix0 = "1 * ( 2 + 3 ) / 4 + 5";
        String infix1 = "1 + 2 * 3 / 4 - 5 + 6 / 7 ";
        String infix2 = "1 + 2 * ( 3 ^ 4 - 5 ) + ( 6 + 7 * 8 ) - 9";

        ArrayList<String> postfix0 = infixToPostfix(infix0);
        ArrayList<String> postfix1 = infixToPostfix(infix1);
        ArrayList<String> postfix2 = infixToPostfix(infix2);

        System.out.println(postfix0);	// [1, 2, 3, +, *, 4, /, 5, +]
        System.out.println(postfix1);	// [1, 2, 3, *, 4, /, +, 5, -, 6, 7, /, +]
        System.out.println(postfix2);	// [1, 2, 3, 4, ^, 5, -, *, +, 6, 7, 8, *, +, +, 9, -]

        System.out.println(evaluatePostfix(postfix0));	// 6
        System.out.println(evaluatePostfix(postfix1));	// -3
        System.out.println(evaluatePostfix(postfix2));	// 206
    }
}
