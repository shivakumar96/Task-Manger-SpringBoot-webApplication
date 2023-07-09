

<%@ include file="common/header.jspf" %>

         <%@ include file="common/navigation.jspf" %>


        <div class="container">
        <h2> Enter todo Details  </h2>
        <form:form method="post" modelAttribute="todo">
            <fieldset class="mb-3">
                <form:label path="description"> Description </form:label>
                <form:input type="text" path="description" required="required"/>
                <form:errors path="description" class="text-warning"/>
            </fieldset>

            <fieldset class="mb-3">
                 <form:label path="targetDate"> Target Date </form:label>
                 <form:input type="text" path="targetDate" required="required"/>
            </fieldset>

            <form:input type="hidden"  path="id"/>
            <form:input type="hidden"  path="done"/>


            <input type="submit" class="btn btn-success" />
        </form:form>
        </div>
        <script type="text/javascript">
            $('#targetDate').datepicker({
                format: 'yyyy-mm-dd',
                startDate: '-3d'
            });
        </script>
<%@ include file="common/footer.jspf" %>