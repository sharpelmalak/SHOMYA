<!DOCTYPE html>
<html lang="en">
<%@ page import="iti.jets.model.Category" %>
<%@ page import="java.util.List" %>
<jsp:directive.include file="/resources/head.html"/>
<style>
    input[type=file]::file-selector-button {

        border-radius: .2em;
        background-color: #b67093;
        transition: 1s;
    }

    input[type=file]::file-selector-button:hover {
        background-color: #d4efef;

    }
</style>
<body>

<%--<jsp:include page="/resources/jsp/header.jsp" />--%>
<%@include file="/resources/jsp/header.jsp"%>
<div class="container">
    <h2 class="text-center">Select Categories of Interest</h2>

    <form action="/shomya/saveInterests" method="post">
        <div class="form-group">
            <c:forEach var="category" items="${categories}">
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" name="categories" value="${category.id}" id= "${category.id}">
                    <label class="form-check-label" for="${category.id}">
                            ${category.name}
                    </label>
                </div>
            </c:forEach>
        </div>
        <button type="submit" class="btn btn-primary">Save Interests</button>
    </form>
</div>

<jsp:include page="/resources/jsp/footer.jsp" />
<jsp:directive.include file="/resources/script.html"/>
</body>
</html>