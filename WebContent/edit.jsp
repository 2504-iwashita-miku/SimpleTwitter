<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>編集画面</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
</head>
<body>
	<div class="form-area">
		<form action="edit" method="post">
		<input name="id" value="${messages.id}" id="id" type="hidden" />
			編集？<br />
		 <pre><textarea name="text" cols="100" rows="5" class="tweet-box">${ messages.text }</textarea></pre>
			<br /> <input type="submit" value="つぶやく">（140文字まで）
		</form>
	</div>

	<div class="copyright">Copyright(c)YourName</div>
</body>
</html>

