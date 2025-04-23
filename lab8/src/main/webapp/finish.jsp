<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="counterBean" class="zh.lab8.CounterBean" scope="session"/>
<jsp:setProperty name="counterBean" property="*"/>
<!DOCTYPE html>
<html>
<head>
  <title>Три</title>
</head>
<body>

<%
  // Получаем данные из формы
  String numbers = request.getParameter("numbers");
  String mParam = request.getParameter("m");
  boolean error = false;
  String message = "";
  if (numbers != null && mParam != null) {
    try {

      int m = Integer.parseInt(mParam);
      counterBean.setNumbers(numbers);
      counterBean.setM(m);
      // Вычисляем числа больше M
      List<Integer> result = new ArrayList<>();
      String[] numArray = numbers.split(",");
      for (String num : numArray) {
        try {
          int number = Integer.parseInt(num.trim());
          if (number > m) {
            result.add(number);
          }
        } catch (NumberFormatException e) {
          // Игнорируем некорректные значения
        }
      }

      // Сохраняем результат в Bean
      counterBean.setResult(result);
      counterBean.incrementCounter(); // Увеличиваем счетчик
    } catch (NumberFormatException e) {
      message = "Ошибка: Введите корректное число M";
      error = true;
      System.out.println("Ошибка: Введите корректное число M");
    }
  }
%>

<% if (error){ %>
  <p style='color:red;' ><%= message%></p>
<% }else { %>
<h1>Результат:</h1>
<table>
  <% for (Integer num : counterBean.getResult()) { %>
  <tr><td><%= num %></td></tr>
  <% } %>
</table>
<% } %>
<form action="main.jsp" method="GET">
  <input type="submit" value="Вернуться на главную">
</form>
</body>
</html>