import java.util.Scanner;
public class Main {
    static Scanner scn = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
    System.out.println("Введите выражение: ");
    String Exp = scn.nextLine().replaceAll("\\s+","");
    System.out.println("Результат: " + calc(Exp));
    }
    public static String calc (String input) throws Exception{
        int operand0, operand1, result;
        char typeOperands = 'a';
        String[] operands = input.split("[+-/*]");
        if (operands.length != 2) {
            throw new Exception ("throws Exception //т.к. формат математической операции не удовлетворяет заданию");
        }
        char operator = input.charAt(operands[0].length());
        try {
            operand0 = Integer.parseInt(operands[0]);
            operand1 = Integer.parseInt(operands[1]);
        } catch (Exception e) {
            try {
                operand0 = Romans.valueOf(operands[0]).ordinal();
                operand1 = Romans.valueOf(operands[1]).ordinal();
                typeOperands = 'r';
            }
            catch (Exception e2) {
                throw new Exception("throws Exception //т.к. используются одновременно разные системы счисления");
            }
        }
        if ((operand0>10) || (operand1>10)) {
            throw new Exception ("throws Exception //т.к. значения вне допустимого диапазона");
        }  else {
            result = calcInt(operand0, operand1, operator);
            if (typeOperands == 'r' & result <= 0) {typeOperands = 'e';}
            return switch (typeOperands) {
                case 'a' -> " " + result;
                case 'r' -> " " + Romans.values()[result];
                default -> throw new Exception ("throws Exception //т.к. в римской системе нет отрицательных чисел");
            };
        }
    }
    static int calcInt(int oprnd0, int oprnd1, char oprtr) {
        return switch (oprtr) {
            case '+' -> oprnd0 + oprnd1;
            case '-' -> oprnd0 - oprnd1;
            case '*' -> oprnd0 * oprnd1;
            case '/' -> oprnd0 / oprnd1;
            default -> throw new IllegalStateException("Результат не определён в коде: " + oprtr);
        };
    }
}