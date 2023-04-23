package model;

import java.util.Stack;

public class SpringArray {

    private SpringArray() {
    }

    public static Spring equivalentSpring(String springExpr) {
        checkBalanced(springExpr);

        final boolean isParallel = springExpr.charAt(0) == '['
                && springExpr.charAt(springExpr.length() - 1) == ']';
        final String expression = springExpr.substring(1, springExpr.length() - 1);

        if (expression.isEmpty()) {
            return new Spring(0);
        }

        final Stack<Spring> stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            switch (expression.charAt(i)) {
                case '{':
                case '[':
                    stack.push(new Spring());
                    break;
                case '}':
                case ']':
                    push(stack, isParallel);
                    break;
            }
        }

        return stack.pop();
    }

    public static Spring equivalentSpring(String springExpr, Spring[] springs) {
        checkBalanced(springExpr);

        final boolean isParallel = springExpr.charAt(0) == '['
                && springExpr.charAt(springExpr.length() - 1) == ']';
        final String expression = springExpr.substring(1, springExpr.length() - 1);

        if (expression.isEmpty()) {
            return new Spring(0);
        }

        final Stack<Spring> stack = new Stack<>();

        for (int i = 0; i < springExpr.length(); i++) {
            switch (springExpr.charAt(i)) {
                case '{':
                case '[':
                    stack.push(springs[0]);
                    break;
                case '}':
                case ']':
                    push(stack, isParallel);
                    break;
            }
        }

        return stack.pop();
    }

    private static void push(Stack<Spring> stack, boolean isInParallel) {
        final Spring s1 = stack.pop();
        if (!stack.isEmpty()) {
            final Spring s2 = stack.peek();
            stack.pop();
            stack.push(isInParallel ? s2.inParallel(s1) : s2.inSeries(s1));
        } else {
            stack.push(s1);
        }
    }

    private static void checkBalanced(String springExpr) {
        while (springExpr.contains("[]") || springExpr.contains("{}")) {
            springExpr = springExpr
                    .replaceAll("\\[\\]", "")
                    .replaceAll("\\{\\}", "");
        }

        if (springExpr.length() != 0) {
            throw new IllegalArgumentException("Not balanced spring expression");
        }
    }
}