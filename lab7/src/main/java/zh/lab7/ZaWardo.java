package zh.lab7;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/zaWardo")
public class ZaWardo extends HttpServlet {
    private int trigger = 0; // Триггер для подсчета обновлений
    private int fontSize = 16; // Размер текста в таблице (начальное значение)
    private static final int MIN_FONT_SIZE = 10; // Минимальный размер текста

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получение параметров из URL
        String resetFontSize = request.getParameter("resetFontSize");
        if ("true".equals(resetFontSize)) {
            fontSize = 16; // Сброс до значения по умолчанию
            String sN = request.getParameter("studentName");
            String gN = request.getParameter("groupNumber");
            String mP = request.getParameter("m");
            String nP = request.getParameter("numbers");
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            out.println("<html><body>");
            out.println("<h1>Вернитесь обратно</h1>");
            out.println("<a href='"+request.getContextPath()+"/zaWardo?" +
                    "studentName=" +sN+"&"
                    +"groupNumber=" +gN +"&"
                    +"m="+mP+"&"+
                    "numbers=" +nP +
                    "'>Сбросить размер текста</a>");
            out.println("</body></html>");
            return;
        }
        String studentName = request.getParameter("studentName");
        String groupNumber = request.getParameter("groupNumber");
        String mParam = request.getParameter("m");
        String numbersParam = request.getParameter("numbers");


        // Уменьшение размера текста при обновлении страницы
        if (fontSize > MIN_FONT_SIZE) {
            fontSize--;
        }

        // Увеличение триггера
        trigger++;

        // Преобразование строки чисел в список
        List<Integer> numbers = parseNumbers(numbersParam);
        int m = mParam != null ? Integer.parseInt(mParam) : 42; // Значение M по умолчанию
        List<Integer> result = findNumbersGreaterThanM(numbers, m);

        // Отправка ответа клиенту
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h1>Лабораторная работа</h1>");

        // Вывод ФИО и номера группы
        if (studentName != null && groupNumber != null) {
            out.println("<p><strong>Выполнил:</strong> " + studentName + "</p>");
            out.println("<p><strong>Группа:</strong> " + groupNumber + "</p>");
        }

        // Вывод триггера
        out.println("<p><strong>Триггер:</strong> " + trigger + "</p>");

        // Вывод таблицы с числами
        out.println("<table border='1' style='font-size:" + fontSize + "px;'>");
        out.println("<tr><th>Числа больше " + m + "</th></tr>");
        for (int num : result) {
            out.println("<tr><td>" + num + "</td></tr>");
        }
        out.println("</table>");

        // Информация о минимальном размере текста
        if (fontSize <= MIN_FONT_SIZE) {
            out.println("<p>Дальнейшее уменьшение текста невозможно.</p>");
        }

        // Кнопка для сброса размера текста
        out.println("<a href='"+request.getContextPath()+"/zaWardo?resetFontSize=true"+
                "&studentName=" +studentName+"&"
                        +"groupNumber=" +groupNumber +"&"
                        +"m="+mParam+"&"+
                        "numbers=" +numbersParam +"'>Сбросить размер текста</a>");

        out.println("</body></html>");

        out.close();
    }

    // Преобразование строки чисел в список
    private List<Integer> parseNumbers(String numbersParam) {
        List<Integer> numbers = new ArrayList<>();
        if (numbersParam != null && !numbersParam.isEmpty()) {
            String[] numberStrings = numbersParam.split(",");
            for (String numStr : numberStrings) {
                try {
                    numbers.add(Integer.parseInt(numStr.trim()));
                } catch (NumberFormatException e) {
                    // Пропустить некорректные значения
                }
            }
        }
        return numbers;
    }

    // Поиск чисел больше M
    private List<Integer> findNumbersGreaterThanM(List<Integer> numbers, int m) {
        List<Integer> result = new ArrayList<>();
        for (int num : numbers) {
            if (num > m) {
                result.add(num);
            }
        }
        return result;
    }
}