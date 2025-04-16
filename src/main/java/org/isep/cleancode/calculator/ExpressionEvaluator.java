package org.isep.cleancode.calculator;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsable de l'évaluation d'une expression mathématique.
 * Effectue les calculs en respectant l'ordre des opérations.
 */
public class ExpressionEvaluator {
    public double evaluate(Expression expression) {
        List<Double> numbers = new ArrayList<>(expression.getNumbers());
        List<Character> operators = new ArrayList<>(expression.getOperators());
        
        evaluateHighPriorityOperations(numbers, operators);
        return evaluateRemainingOperations(numbers, operators);
    }
    
    private void evaluateHighPriorityOperations(List<Double> numbers, List<Character> operators) {
        for (int i = 0; i < operators.size(); i++) {
            char operator = operators.get(i);
            if (operator == '*' || operator == '/') {
                double result = performOperation(numbers.get(i), numbers.get(i + 1), operator);
                numbers.set(i, result);
                numbers.remove(i + 1);
                operators.remove(i);
                i--;
            }
        }
    }
    
    private double evaluateRemainingOperations(List<Double> numbers, List<Character> operators) {
        double result = numbers.get(0);
        for (int i = 0; i < operators.size(); i++) {
            char operator = operators.get(i);
            result = performOperation(result, numbers.get(i + 1), operator);
        }
        return result;
    }
    
    private double performOperation(double left, double right, char operator) {
        return switch (operator) {
            case '+' -> left + right;
            case '-' -> left - right;
            case '*' -> left * right;
            case '/' -> {
                if (right == 0) {
                    throw new ArithmeticException("Division by zero is not allowed");
                }
                yield left / right;
            }
            default -> throw new IllegalArgumentException("Unsupported operator: " + operator);
        };
    }
}
