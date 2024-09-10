<!DOCTYPE html>
<html lang="en">
<%@ page import="iti.jets.model.Product" %>
<%@ page import="iti.jets.model.Category" %>
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

<%@ include file="/resources/jsp/header.jsp" %>

<!-- Add Product Section Start -->
<div class="container-fluid bg-secondary my-5">
    <div class="row justify-content-md-center py-5 px-xl-5">
        <div class="col-md-6 col-12 py-5">
            <div class="text-center mb-2 pb-2">
                <h2 class="section-title px-5 mb-3">
                    <span class="bg-secondary px-2">Add New Product</span>
                </h2>
            </div>

            <form action="/shomya/addproduct" method="post" enctype="multipart/form-data">

                <!-- Product Name -->
                <div class="control-group">
                    <input type="text" class="form-control" name="pname" id="pname" placeholder="Enter Product Name"
                           required="required" />
                    <p class="help-block text-danger"></p>
                </div>

                <!-- Product Price -->
                <div class="control-group">
                    <input type="number" class="form-control" name="pprice" id="pprice" placeholder="Enter Product Price"
                           required="required" />
                    <p class="help-block text-danger"></p>
                </div>

                <!-- Product Quantity -->
                <div class="control-group">
                    <input type="number" class="form-control" name="pquantity" id="pquantity" placeholder="Enter Product Quantity"
                           required="required" />
                    <p class="help-block text-danger"></p>
                </div>

                <!-- Product Description -->
                <div class="control-group">
                    <input type="text" class="form-control" name="pdesc" id="pdesc" placeholder="Enter Product Description"
                           required="required" />
                    <p class="help-block text-danger"></p>
                </div>

                <!-- Category Selection -->
                <div class="control-group">
                    <label for="categorySelect">Select Category</label>
                    <select class="form-control" id="categoryId" name="categoryId" required="required">
                    <c:if test="${categoryList != null}">
                                    <c:forEach items="${categoryList}" var="category">
                                           <option value="5">${category.name}</option>
                                    </c:forEach>
                                </c:if>
                    </select>
                </div>

                <!-- Product Image -->
                <div class="control-group">
                    <label for="pimage">Product Image</label>
                    <input type="file" name="pimage" id="pimage" required="pimage" />
                    <p class="help-block text-danger"></p>
                </div>

                <!-- Submit Button -->
                <div>
                    <button class="btn btn-primary py-2 px-4" type="submit">Add Product</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Add Product Section End -->

<jsp:include page="/resources/jsp/footer.jsp" />

</body>
</html>
