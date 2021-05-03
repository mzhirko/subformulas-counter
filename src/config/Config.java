//********************************************************************************************
// Лабораторная работа №1 по дисциплине ЛОИС
// Вариант A: Подсчитать количество подформул в формуле сокращенного языка логики высказываний на заданном уровне.
// Выполнена студенткой группы 821701 БГУИР Жирко Марией Сергеевной
// Класс предназначен для указания файла с формулой для проверки

package config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Config {

    public static final String FILE_NAME = "1";
    public static final String FILE_FORMAT = "txt";
    public static final String IN_FILE_PATH = System.getProperty("user.dir") + "/files/in/" + FILE_NAME + "." + FILE_FORMAT;
    public static final List<String> SYMBOLS = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "1", "0"));
    public static final List<String> NO_CONST = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"));
    public static final List<String> SIGNS = new ArrayList<>(Arrays.asList("!", "/\\", "\\/", "(", ")", "->", "~"));

}