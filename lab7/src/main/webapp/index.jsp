<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Лаба7" %></h1>
<br/>
<h1>Добро пожаловать!</h1>
<p>Это стартовая страница для тестирования сервлета.</p>

<!-- Форма для отправки параметров -->
<form action="zaWardo" method="get">
  <label for="studentName">ФИО студента:</label><br>
  <input type="text" id="studentName" name="studentName" value="Хасимов Зольфат Зольфарович" required><br><br>

  <label for="groupNumber">Номер группы:</label><br>
  <input type="text" id="groupNumber" name="groupNumber" value="4310" required><br><br>

  <label for="m">Пороговое значение (M):</label><br>
  <input type="number" id="m" name="m" value="42"><br><br>

  <label for="numbers">Список чисел (через запятую):</label><br>
  <input type="text" id="numbers" name="numbers" placeholder="Пример: 10,20,30,40"><br><br>

  <input type="submit" value="Перейти к сервлету">


</form>

<!-- Простая ссылка для быстрого доступа -->
<p>Или перейдите напрямую: <a href="zaWardo?studentName=Хасимов+Зольфат+Зольфарович&groupNumber=4310&m=42&numbers=10,50,30,140">Ссылка на сервлет</a></p>

</body>
</html>