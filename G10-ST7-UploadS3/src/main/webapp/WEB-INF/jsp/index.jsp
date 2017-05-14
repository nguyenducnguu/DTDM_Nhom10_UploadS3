<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>DTDM - G10 - Amazon</title>
	<link href="static/css/bootstrap.min.css" rel="stylesheet">
	<link href="static/css/style.css" rel="stylesheet">
	<script src="static/ckeditor/ckeditor.js" type="text/javascript"></script>
</head>
<body>
	<div class="container">
		<div role="navigation">
			<div class="navbar navbar-inverse">
				<a href="/" class="navbar-brand">DTDM - G10</a>
				<div class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li><a href="all-post">All Post</a></li>
						<li><a href="add-post">Add Post</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<c:choose>
		<c:when test="${mode == 'MODE_HOME'}">
			<div class="container" id="homeDiv">
				<div class="jumbotron text-center">
					<h1>Bai tap Amazon</h1>
					<h2>Nhom 10 - Sang Thu 7</h2>
				</div>
			</div>
		</c:when>
		<c:when test="${mode == 'MODE_ALL'}">
			<div class="container text-center">
				<h3>List Post</h3>
				<hr>
				<div class="panel-group">
					<c:forEach var="post" items="${lstPost}">
						<div class="panel panel-info">
							<div class="panel-heading">
								<b>${post.title}</b>
								<div class="navbar-right">
									<a href="update-post?id=${post.id}"><span class="glyphicon glyphicon-pencil"></span></a>
									<a href="delete-post?id=${post.id}"><span class="glyphicon glyphicon-trash"></span></a>
								</div>		
							</div>
							<div class="panel-body">${post.content}</div>
							<div class="panel-footer"><a href="${post.file}">${post.file}</a></div>
						</div>
					</c:forEach>
				</div>
			</div>
		</c:when>
		<c:when test="${mode == 'MODE_ADD'|| mode == 'MODE_UPDATE'}">
			<div class="container text-center">
				<h3>Manage Post</h3>
				<hr>
				<form class="form-horizontal" method="POST" enctype="multipart/form-data" action="save-post">
					<input type="hidden" name="id" value="${post.id}"/>
					<div class="form-group">
						<label class="control-label col-md-3">Title</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="title" value="${post.title}"/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Content</label>
						<div class="col-md-7">
							<textarea rows="10" class="form-control ckeditor" name="content">${post.content}</textarea>
							<script>
								CKEDITOR.replace('content');
							</script>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">File</label>
						<div class="col-md-7">
							<input type="file" class="form-control" name="file" value="${post.file}"/>
						</div>
					</div>
					<div class="form-group">
						<input type="submit" class="btn btn-primary" value="Save post" />
					</div>
				</form>
			</div>
		</c:when>
	</c:choose>
	<script src="static/js/jquery-1.11.1.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
</body>
</html>
