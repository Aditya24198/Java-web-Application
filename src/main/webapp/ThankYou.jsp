<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script>
function updatestatus(){
var request = new XMLHttpRequest();
request.onreadystatechange=function(){
	if(this.readyState == 4){
		var json = JSON.parse(this.responseText);
		document.getElementById("status").innerHTML = json.status;
		document.getElementById("time").innerHTML = "Last Update date:" +json.time;
	}
}
request.open("GET","/updatestatus?id=${orderId}",true);
request.send();
}
window.setInterval(
		function(){
			updatestatus()	
		},1000);
</script>
</head>
<body>
<jsp:include page="/header.jsp"></jsp:include>
<h1>Ricky's Restaurant</h1>
<h2>Order Placed</h2>
<p>Thank you - your order has been received. You need to pay $    ${total}</p>
<p>Your Order current status is:<span id="status">${status}</span> <input type="button" value="refreshbutton" onclick="updatestatus()"/> </p>
<p id="time"></p>
<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>