<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "com.virtualpairprogrammers.domain.MenuItem"%>
<%@ page import = "java.util.List"%>
<!DOCTYPE html>
<html>
<body>
<jsp:include page="/header.jsp"></jsp:include>
<h1>Ricky's Restaurant</h1>
<h2>Menu Search Results: </h2><ul>
<%
List<MenuItem> menuItems = (List<MenuItem>)request.getAttribute("menuItems");
for (MenuItem menuItem : menuItems) {
	%>
	<li><%=menuItem %></li>
	<% } 
	%>

</ul>
<jsp:include page="/footer.jsp"></jsp:include>
</body>

</html>