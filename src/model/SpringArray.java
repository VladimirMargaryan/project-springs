package model;

import java.util.*;

public class SpringArray {

    public static Spring equivalentSpring(String springExpr) {
        final Stack<Spring> stack = new Stack<>();

        for (char c : springExpr.toCharArray()) {
            if (c == '{') {
                stack.push(new Spring());
            } else if (c == '[') {
                stack.push(null);
            } else if (c == '}') {
                Spring s = stack.pop();
                while (stack.peek() != null) {
                    s = stack.pop().inSeries(s);
                }
                stack.pop();
                stack.push(s);
            } else if (c == ']') {
                Spring s = stack.pop();
                while (stack.peek() != null) {
                    s = stack.pop().inParallel(s);
                }
                stack.pop();
                stack.push(s);
            }
        }

        return stack.pop();
    }

    public static Spring equivalentSpring(String springExpr, Spring[] springs) {
        final HashMap<Character, Spring> map = new HashMap<>();
        final char[] chars = springExpr.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '{' || chars[i] == '[') {
                continue;
            } else if (chars[i] == '}' || chars[i] == ']') {
                continue;
            } else {
                map.put(chars[i], springs[i]);
            }
        }

        Stack<Spring> stack = new Stack<>();

        for (char c : chars) {
            if (c == '{') {
                stack.push(new Spring());
            } else if (c == '[') {
                stack.push(null);
            } else if (c == '}') {
                Spring s = stack.pop();
                while (stack.peek() != null) {
                    s = stack.pop().inSeries(s);
                }
                stack.pop();
                stack.push(s);
            } else if (c == ']') {
                Spring s = stack.pop();
                while (stack.peek() != null) {
                    s = stack.pop().inParallel(s);
                }
                stack.pop();
                stack.push(s);
            } else {
                stack.push(map.get(c));
            }
        }

        return stack.pop();
    }
}