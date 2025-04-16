package org.isep.cleancode.calculator;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsable de l'analyse des expressions mathématiques.
 * Convertit une chaîne d'expression en objet Expression.
 */
public class ExpressionParser {
    public Expression parse(String expression) {
        List<Double> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();
        
        int index = 0;
        while (index < expression.length()) {
            index = parseNextToken(expression, index, numbers, operators);
        }
        
        return new Expression(numbers, operators);
    }
    
    private int parseNextToken(String expression, int currentIndex, List<Double> numbers, List<Character> operators) {
        char currentChar = expression.charAt(currentIndex);
        
        if (currentChar == '(') {
            return handleParenthesis(expression, currentIndex, numbers);
        }
        
        if (isOperator(currentChar)) {
            operators.add(currentChar);
            return currentIndex + 1;
        }
        
        if (Character.isDigit(currentChar) || currentChar == '.') {
            return parseNumberToken(expression, currentIndex, numbers);
        }
        
        throw new IllegalArgumentException("Invalid character in expression: " + currentChar);
    }
    
    private int handleParenthesis(String expression, int startIndex, List<Double> numbers) {
        int endIndex = findClosingParenthesisIndex(expression, startIndex);
        String subExpression = expression.substring(startIndex + 1, endIndex);
        
        Calculator subCalculator = new Calculator();
        double result = subCalculator.evaluateMathExpression(subExpression);
        numbers.add(result);
        
        return endIndex + 1;
    }
    
    private int findClosingParenthesisIndex(String expression, int openIndex) {
        int depth = 1;
        int index = openIndex + 1;
        
        while (depth > 0 && index < expression.length()) {
            if (expression.charAt(index) == '(') {
                depth++;
            } else if (expression.charAt(index) == ')') {
                depth--;
            }
            
            if (depth > 0) {
                index++;
            }
        }
        
        if (depth != 0) {
            throw new IllegalArgumentException("Missing closing parenthesis");
        }
        
        return index;
    }
    
    private int parseNumberToken(String expression, int startIndex, List<Double> numbers) {
        StringBuilder numberStr = new StringBuilder();
        int currentIndex = startIndex;
        
        while (currentIndex < expression.length() && 
               (Character.isDigit(expression.charAt(currentIndex)) || expression.charAt(currentIndex) == '.')) {
            numberStr.append(expression.charAt(currentIndex));
            currentIndex++;
        }
        
        if (numberStr.length() == 0) {
            throw new IllegalArgumentException("Invalid number at index " + startIndex);
        }
        
        numbers.add(Double.parseDouble(numberStr.toString()));
        return currentIndex;
    }
    
    private boolean isOperator(char c) {
        return "+-*/".indexOf(c) != -1;
    }
}
