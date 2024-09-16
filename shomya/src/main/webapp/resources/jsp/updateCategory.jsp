<!DOCTYPE html>
<html lang="en">
<%@ page import="iti.jets.persistence.model.Category" %>
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

<c:if test="${userRole == EnumHelper.getAdminRole()}">
  <!-- Add Start -->
  <div class="container-fluid bg-secondary my-5">
    <div class="row justify-content-md-center py-5 px-xl-5">
      <div class="col-md-6 col-12 py-5">
        <div class="text-center mb-2 pb-2">
          <h2 class="section-title px-5 mb-3"><span class="bg-secondary px-2">Update Category</span></h2>
        </div>
        <form action="/shomya/app/categories">
          <button class="btn btn-back py-2 px-4" type="submit"><i class="fas fa-arrow-left"></i> Back</button>
        </form>
        <c:if test="${category != null}">
        <form action="/shomya/app/updatecategory" method="post" enctype="multipart/form-data">

          <input type="hidden" name="catId" value="${category.id}" />
          <div class="control-group">
            <input type="text" class="form-control" name="categoryName" id="categoryName" placeholder="Enter Category Name"
                   required="required" data-validation-required-message="Please enter Category Name" value="${category.name}"/>
            <p class="help-block text-danger"></p>
          </div>

          <!-- Category Image -->
          <div class="control-group">
            <label for="categoryImage">Category Image</label>
            <input type="file"  name="categoryImage" id="categoryImage"/>
          </div>

          <!-- Submit Button -->
          <div>
            <button class="btn btn-primary py-2 px-4" type="submit">Update</button>
          </div>
        </form>
        </c:if>
      </div>
    </div>
  </div>
  <!-- Add End -->
</c:if>


<jsp:directive.include file="/resources/jsp/footer.jsp" />
<jsp:directive.include file="/resources/script.html"/>

</body>
</html>