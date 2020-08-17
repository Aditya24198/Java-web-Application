<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<body>
<jsp:include page="/header.jsp"></jsp:include>
<h1>Ricky's Restaurant</h1>
<h2>Menu</h2>
<ul>
<c:forEach items="${menuItems}"  var="menuItem">
<li>${menuItem}</li>
</c:forEach>
</ul>
<jsp:include page="/footer.jsp"></jsp:include>
</body>

</html>
