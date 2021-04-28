//********************************************************************************************
// Лабораторная работа №1 по дисциплине ЛОИС
// Вариант A: Подсчитать количество подформул в формуле сокращенного языка логики высказываний.
// Выполнена студенткой группы 821701 БГУИР Жирко Марией Сергеевной
// Класс предназначен для исключений

package parser;



public class FormulaException extends Exception {
    private String message;
    private final int number;

    public String getMessage() {
        return message;
    }

    public FormulaException(int number) {
        this.number = number;
        switch (number) {
            case 1: {
                message = "More open brackets!";
                break;
            }
            case 2: {
                message = "More closing brackets!";
                break;
            }
            case 3: {
                message = "Syntax violated!";
                break;
            }
            case 4: {
                message = "Instead of a conjunction, another operation is found!";
                break;
            }
            case 6: {
                message = "Unknown character used!";
                break;
            }
            case 8: {
                message = "Elements are repeated in the disjunction!";
                break;
            }
            case 9: {
                message = "Repeated disjunction!";
                break;
            }
            case 10: {
                message = "Negation is not with the element!";
                break;
            }
            case 11: {
                message = "Not enough brackets";
                break;
            }
            case 12: {
                message = "Double negation";
                break;
            }
        }
    }

    public int getNumber() {
        return number;
    }
}