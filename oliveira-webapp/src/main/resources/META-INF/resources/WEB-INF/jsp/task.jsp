<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
	<div>
		<h1>Task Details</h1>
		<form:form method="post" modelAttribute="newTask">
			<fieldset class="mb-3">
				<form:label path="description">Description</form:label>
				<form:input type="text" path="description" name="description" required="required"/>
				<form:errors path="description" cssClass="text-warning"/>
			</fieldset>
			<fieldset class="mb-3">
				<form:label path="targetDate">Target Date</form:label>
				<form:input type="text" path="targetDate" name="description" required="required"/>
				<form:errors path="targetDate" cssClass="text-warning"/>
			</fieldset>
			
			<form:input type="hidden" path="id"/>
			<form:input type="hidden" path="status"/>
			<input type="submit" class="btn btn-success"/>
		</form:form>
	</div>
</div>
<script src="webjars/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript">
	$('#targetDate').datepicker({
	    format: 'yyyy-mm-dd'
	});
</script>

<%@ include file="common/footer.jspf" %>