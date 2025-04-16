package org.isep.cleancode.calculator;

import java.util.List;

/**
 * Classe représentant une expression mathématique sous forme structurée.
 * Encapsule une liste de nombres et d'opérateurs.
 */
public class Expression {
    private final List<Double> numbers;
    private final List<Character> operators;
    
    public Expression(List<Double> numbers, List<Character> operators) {
        this.numbers = numbers;
        this.operators = operators;
        validateExpression();
    }
    
    private void validateExpression() {
        if (numbers.size() != operators.size() + 1) {
            throw new IllegalArgumentException("Invalid expression: mismatch between numbers and operators");
        }
    }
    
    public List<Double> getNumbers() {
        return numbers;
    }
    
    public List<Character> getOperators() {
        return operators;
    }
}
