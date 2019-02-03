package _1Fundamentals.Bag;

import java.util.Stack;

import base.stdlib.StdOut;

/**
 * 双栈算术表达式求值
 */
public class Evaluate {

    public static void main(String[] args) {
        String expression = "(1+((2+3)*(4*5)))";

        Stack<Character> ops = new Stack<Character>();
        Stack<Double> vals = new Stack<Double>();

        for (char c : expression.toCharArray()) {
            switch (c) {
                case '+':
                case '-':
                case '*':
                case '/': {
                    ops.push(c);
                    break;
                }
                case '(': {
                    break;
                }
                case ')': {
                    char op = ops.pop();
                    //Todo :
                    double v = 0.0f, val1 = vals.pop(), val2 = vals.pop();
                    switch (op) {
                        case '+': {
                            v = val2 + val1;
                            break;
                        }
                        case '-': {
                            v = val2 - val1;
                            break;
                        }
                        case '*': {
                            v = val2 * val1;
                            break;
                        }
                        case '/': {
                            v = val2 / val1;
                            break;
                        }
                    }
                    vals.push(v);
                    break;
                }
                default: {
                    vals.push(Double.valueOf(String.valueOf(c)));
                }
            }
        }

        StdOut.println(" final val  is : " + vals.pop());
    }

}
