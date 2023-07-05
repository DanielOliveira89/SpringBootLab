<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
		<title>Tasks Repository</title>
	</head>
	<body>
		<div class="container">
			<div>
				<h1>Your Tasks</h1>
			</div>
			<table class="table">
				<thead>
					<tr>
						<th>id</th>
						<th>Description</th>
						<th>Target Date</th>
						<th>Status</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${tasks}" var="task">
						<tr>
							<td>${task.id}</td>
							<td>${task.description}</td>
							<td>${task.targetDate}</td>
							<td>${task.status}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<a href="add-task" class="btn btn-success">Add Task</a>
		</div>
		<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
		<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
	</body>
</html>