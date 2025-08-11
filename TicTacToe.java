package org.example;
import java.util.*;

public class TicTacToe {
    static Scanner sc = new Scanner(System.in);

public static void main(String[] args) {
    String line = sc.nextLine();
    String symb = TicTacToe_AllLog(line);

    if (symb.equals("НИЧЬЯ")){
        System.out.println(symb);
    }else{
        System.out.println("Победили: " + symb);
    }
}

    static String TicTacToe_AllLog(String line){
        List<String> line_LIST = Arrays.asList(line.split(""));

        if (Objects.equals(line_LIST.get(0), line_LIST.get(1)) & Objects.equals(line_LIST.get(1), line_LIST.get(2))) {
            return line_LIST.get(1)+", верхняя строка";
        }
        if (Objects.equals(line_LIST.get(3), line_LIST.get(4)) & Objects.equals(line_LIST.get(3), line_LIST.get(5))) {
            return line_LIST.get(4)+", средняя строка";
        }
        if (Objects.equals(line_LIST.get(6), line_LIST.get(7)) & Objects.equals(line_LIST.get(6), line_LIST.get(8))) {
            return line_LIST.get(1)+", нижняя строка";
        }
        if (Objects.equals(line_LIST.get(0), line_LIST.get(4)) & Objects.equals(line_LIST.get(0), line_LIST.get(8))) {
            return line_LIST.get(4)+", горизонталь слева направо";
        }
        if (Objects.equals(line_LIST.get(2), line_LIST.get(4)) & (Objects.equals(line_LIST.get(2), line_LIST.get(6)))) {
            return line_LIST.get(2)+", горизонталь справа налево";
        }
        if (Objects.equals(line_LIST.get(0), line_LIST.get(3)) & (Objects.equals(line_LIST.get(0), line_LIST.get(6)))) {
            return line_LIST.get(2)+", левый ряд";
        }
        if (Objects.equals(line_LIST.get(1), line_LIST.get(4)) & (Objects.equals(line_LIST.get(1), line_LIST.get(7)))) {
            return line_LIST.get(2)+", средний ряд";
        }
        if (Objects.equals(line_LIST.get(2), line_LIST.get(5)) & (Objects.equals(line_LIST.get(2), line_LIST.get(8)))) {
            return line_LIST.get(2)+", правый ряд";
        }
        return "НИЧЬЯ";
    }
}

