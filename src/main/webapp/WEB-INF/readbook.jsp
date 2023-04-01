<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" errorPage="error.jsp" isELIgnored="false"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <!-- Global -->
    <c:import url="sharedView/global.html" />
    <!-- <link rel="stylesheet" href="./css/bootstrap.css" /> -->
    <!-- Jquery validate -->
    <script
      src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"
      type="text/javascript"></script>
    <!-- Local -->
    <link rel="stylesheet" href="./css/productItem.css" />
<title>Read book</title>
</head>
<body>
	<c:import url="sharedView/header.jsp"></c:import>
	<br>
	<div class="grid">
		<div class="grid_row">
			<div class="grid_column_8" style="margin: 0 auto;">
				<iframe src="https://drive.google.com/file/d/1z7RCIuoi8pFhA7mGQakqG8tCLyRNel4d/preview" width="640" height="480" allow="autoplay"></iframe>
			</div>
		</div>
	</div>
	<br>
	<c:import url="sharedView/footer.jsp"></c:import>
</body>
</html>