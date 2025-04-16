package org.isep.cleancode.calculator;

/**
 * Classe principale pour l'évaluation des expressions mathématiques.
 * Coordonne le processus d'analyse et d'évaluation.
 */
public class Calculator {
    private final ExpressionParser parser;
    private final ExpressionEvaluator evaluator;

    public Calculator() {
        this.parser = new ExpressionParser();
        this.evaluator = new ExpressionEvaluator();
    }

    public double evaluateMathExpression(String expression) {
        if (expression == null || expression.isEmpty()) {
            throw new IllegalArgumentException("Expression cannot be null or empty");
        }

        String cleanExpression = removeWhitespace(expression);
        Expression parsedExpression = parser.parse(cleanExpression);
        return evaluator.evaluate(parsedExpression);
    }

    private String removeWhitespace(String expression) {
        return expression.replaceAll("\\s+", "");
    }
}