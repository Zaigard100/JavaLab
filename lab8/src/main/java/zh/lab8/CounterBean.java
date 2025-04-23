package zh.lab8;

import java.util.ArrayList;
import java.util.List;

public class CounterBean {
    private String numbers; // Список чисел, введенный пользователем
    private int m;          // Число M
    private List<Integer> result; // Результат вычисления
    private int counter;    // Счетчик переходов

    public CounterBean() {
        this.numbers = "";
        this.m = 0;
        this.result = new ArrayList<>();
        this.counter = 0;
    }

    // Геттеры и сеттеры
    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public List<Integer> getResult() {
        return result;
    }

    public void setResult(List<Integer> result) {
        System.out.println("Setting result in CounterBean: " + result);
        this.result = result;
    }

    public int getCounter() {
        System.out.println("Getting result from CounterBean: " + result);
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    // Метод для увеличения счетчика
    public void incrementCounter() {
        this.counter++;
    }
}