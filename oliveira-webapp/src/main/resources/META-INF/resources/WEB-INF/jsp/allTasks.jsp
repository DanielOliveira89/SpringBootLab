<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
	<div>
		<h1>Your Tasks</h1>
	</div>
	<table class="table">
		<thead>
			<tr>
				<th>Description</th>
				<th>Target Date</th>
				<th>Status</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${tasks}" var="task">
				<tr>
					<td>${task.description}</td>
					<td>${task.targetDate}</td>
					<td>${task.status}</td>
					<td><a href="delete-task?id=${task.id}" class="btn btn-warning">Delete</a></td>
					<td><a href="update-task?id=${task.id}" class="btn btn-success">Update</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="add-task" class="btn btn-success">Add Task</a>
</div>
<%@ include file="common/footer.jspf" %>