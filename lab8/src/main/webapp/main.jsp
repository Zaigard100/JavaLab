
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="counterBean" class="zh.lab8.CounterBean" scope="session"/>
<jsp:setProperty name="counterBean" property="*"/>
<!DOCTYPE html>
<html>
<head>
  <title>Два</title>
</head>
<body>
<h1>Введите данные:</h1>
<form action="finish.jsp" method="POST">
  <label for="numbers">Список чисел (через запятую):</label><br>
  <input type="text" id="numbers" name="numbers"><br><br>
  <label for="m">Число M:</label><br>
  <input type="number" id="m" name="m"><br><br>
  <input type="submit" value="Вычислить">
</form>
<% counterBean.incrementCounter(); %>
<p>Количество переходов: ${counterBean.counter}</p>
</body>
</html>