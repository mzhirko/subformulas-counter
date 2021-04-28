//********************************************************************************************
// Лабораторная работа №1 по дисциплине ЛОИС
// Вариант A: Подсчитать количество подформул в формуле сокращенного языка логики высказываний.
// Выполнена студенткой группы 821701 БГУИР Жирко Марией Сергеевной
// Класс предназначен для парсинга формул

package parser;

import config.Config;

import java.util.*;

public class Parser {
    private final String EXPRESSION;

    private String message;

    private final List<String> subFormulas;
    private final List<String> uniqueSubFormulas;
    private final Set<String> ELEMENTS;
    private final List<String> ATOMS;

    public Parser(String expression) throws Exception{
        this.EXPRESSION = expression;
        ELEMENTS = new HashSet<>();
        ATOMS = new ArrayList<>();
        subFormulas = new ArrayList<>();
        uniqueSubFormulas = new ArrayList<>();
        message = "";
        try {

            if (expression.contains("!!")){
                System.out.println("Invalid syntax!");
                System.exit(0);
            }
            // checks if bracket placement is correct
            checkBrackets();

            // checks if all symbols are correct
            checkSymbols();

            // create parse tree of the expression
            ExpressionTree tree = new ExpressionTree(expression, this);

            checkBrackets();


            if (ELEMENTS.size() == 0) {
                System.out.println("Invalid syntax!");
                System.exit(0);
            }

            // finds
            searchSubFormulas(tree);
            if (ATOMS.size() != ATOMS.stream().distinct().count()) {
                throw new FormulaException(9);
            }

            for (int i = 1; i<subFormulas.size(); i++) {
                if (!uniqueSubFormulas.contains(subFormulas.get(i))) {
                    uniqueSubFormulas.add(subFormulas.get(i));
                }
            }

            int i = 0;

            System.out.println("The number of subformulas: " + (uniqueSubFormulas.size() + 1));
            System.out.println("1. " + expression);
            for (i = 0; i < uniqueSubFormulas.size(); i ++){
                System.out.println((i + 2) + ". " + uniqueSubFormulas.get(i));
            }

        } catch (FormulaException FormulaException) {
            message = "Formula Error: ";
            message += FormulaException.getMessage();
        }
    }

    private void checkSymbols() throws FormulaException {
        if (EXPRESSION.length() == 1) {
            if (!Config.SYMBOLS.contains(EXPRESSION)) {
                throw new FormulaException(6);
            }
        }
        for (int i = 0; i < EXPRESSION.length(); i++) {
            if (!(Config.SYMBOLS.contains("" + EXPRESSION.charAt(i)) || Config.SIGNS.contains("" + EXPRESSION.charAt(i)))) {
                String sign = searchSign(EXPRESSION, i);
                if (!Config.SIGNS.contains(sign)) {
                    throw new FormulaException(6);
                } else {
                    if (sign.length() == 2) {
                        i++;
                    }
                }
            }
        }
    }

    private String searchSign(String expression, int pointer) {
        if (expression.charAt(pointer) == '!' || expression.charAt(pointer) == '~')
            return expression.charAt(pointer) + "";
        return "" + expression.charAt(pointer) + expression.charAt(pointer + 1);
    }

    // Автор: Клевцевич А.В, гр 821701.
    private void checkBrackets() throws FormulaException {
        if (EXPRESSION.contains(")(")) {
            throw new FormulaException(3);
        }
        if (EXPRESSION.charAt(0) == ')') {
            throw new FormulaException(3);
        }
        if (EXPRESSION.charAt(0) != '(' && EXPRESSION.length() != 1) {
            throw new FormulaException(3);
        }
        if (EXPRESSION.charAt(0) == '(' && EXPRESSION.charAt(EXPRESSION.length() - 1) != ')') {
            throw new FormulaException(3);
        }
        int checkOpen = 0;
        int checkClose = 0;
        for (int i = 0; i < EXPRESSION.length(); i++) {
            if (EXPRESSION.charAt(i) == '(') {
                checkOpen++;
            } else if (EXPRESSION.charAt(i) == ')') {
                checkClose++;
            }
        }
        if (checkOpen > checkClose) {
            throw new FormulaException(1);
        }
        if (checkClose > checkOpen) {
            throw new FormulaException(2);
        }
    }

    private void searchSubFormulas(ExpressionTree tree) {
        if (!"!".equals(tree.getOperation())) {
            if (!tree.getExpression().equals("1") && !tree.getExpression().equals("0")){
                subFormulas.add(tree.getExpression());
            }
            if (tree.getOperation() != null) {
                searchSubFormulas(tree.getLeft());
            }
            if (tree.getOperation() != null) {
                searchSubFormulas(tree.getRight());
            }
        } else {
            if (!tree.getExpression().equals("1") && !tree.getExpression().equals("0")){
                subFormulas.add(tree.getExpression());
            }
            subFormulas.add(tree.getExpression());
            if (tree.getOperation() != null) {
                searchSubFormulas(tree.getLeft());
            }
        }

    }

    // Автор: Клевцевич А.В, гр 821701.
    private void checkNegation(ExpressionTree tree, int code) throws FormulaException {
        if (code == 0) {
            if ("!".equals(tree.getOperation()) || "~".equals(tree.getOperation())) {
                if (Objects.isNull(tree.getRight())) {
                    checkNegation(tree.getLeft(), 1);
                    return;
                } else {
                    throw new FormulaException(10);
                }
            }
            if (Objects.nonNull(tree.getLeft())) checkNegation(tree.getLeft(), 0);
            if (Objects.nonNull(tree.getRight())) checkNegation(tree.getRight(), 0);
        } else {
            if (Objects.nonNull(tree.getRight())) {
                throw new FormulaException(10);
            }
            if (Objects.nonNull(tree.getLeft())) checkNegation(tree.getLeft(), 1);
        }
    }


    public String getMessage() {
        return message;
    }

    public void addElements(String element) {
        if (Config.SYMBOLS.contains(element)) {
            ELEMENTS.add(element);
        }
    }

}