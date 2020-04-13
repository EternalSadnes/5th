<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post">
    Введите промежуток: <input type="text" name="interval" placeholder="${sessionScope.interval!=null? sessionScope.interval:"1-5"}">
    <c:if test="${not empty sessionScope.intervalError}">
        <strong>${sessionScope.intervalError}</strong>
    </c:if>
    <br>
    Введите шаг: <input type="text" name="delta" placeholder="${sessionScope.delta!=null? sessionScope.delta:0.01}">
    <c:if test="${not empty sessionScope.deltaError}">
        <strong>${sessionScope.deltaError}</strong>
    </c:if>
    <br>
    <input type="submit" value="Submit"/>
</form>
<c:if test="${not empty function}">
    <br>
    <strong>Максимальный элемент: </strong> ${function.maxFuncValue}
    <br>
    <strong>Минимальный элемент: </strong> ${function.minFuncValue}
    <br>
    <strong>Сумма элементов: </strong> ${function.sumOfFuncValues}
    <br>
    <strong>Среднее значение функции: </strong> ${function.avgOfFuncValues}
</c:if>
</body>
</html>
