<%@ page import="iti.jets.model.Product" %>
<%@ page import="iti.jets.model.Category" %>
<!DOCTYPE html>
<html lang="en">

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

<%@include file="/resources/jsp/header.jsp"%>
<!-- Add Product Section Start -->
<div class="container-fluid bg-secondary my-5">
    <div class="row justify-content-md-center py-5 px-xl-5">
        <div class="col-md-6 col-12 py-5">
            <div class="text-center mb-2 pb-2">
                <h2 class="section-title px-5 mb-3">
                    <span class="bg-secondary px-2">Update Product</span>
                </h2>
            </div>
            <form action="/shomya/products">
                <button class="btn btn-back py-2 px-4" type="submit"><i class="fas fa-arrow-left"></i> Back</button>
            </form>
            <form action="/shomya/viewproduct" method="post" enctype="multipart/form-data">

                <input type="hidden" name="productID" value="${product.id}" />
                <!-- Product Name -->
                <div class="control-group">
                    <input type="text" class="form-control" name="pname"  placeholder="Enter Product Name"
                           required="required" value="${product.name}" />
                    <p class="help-block text-danger"></p>
                </div>

                <!-- Product Price -->
                <div class="control-group">
                    <input type="number" class="form-control" name="pprice"  placeholder="Enter Product Price"
                           step="any"  required="required" value="${product.price}"/>
                    <p class="help-block text-danger"></p>
                </div>

                <!-- Product Quantity -->
                <div class="control-group">
                    <input type="number" class="form-control" name="pquantity"  placeholder="Enter Product Quantity"
                           required="required" value="${product.quantity}"/>
                    <p class="help-block text-danger"></p>
                </div>

                <!-- Product Description -->
                <div class="control-group">
                    <input type="text" class="form-control" name="pdesc"  placeholder="Enter Product Description"
                           required="required" value=" ${product.description}"/>
                    <p class="help-block text-danger"></p>
                </div>

                <!-- Category Selection -->

                <div class="control-group">
                    <label for="categoryId">Select Category</label>
                    <select class="form-control" id="categoryId" name="categoryId" required="required" >
                        <c:if test="${categoryList != null}">
                            <c:forEach items="${categoryList}" var="category">
                                <option value="${category.id}"
                                    <c:if test="${category.name == product.category.name}">
                                        selected="selected"
                                    </c:if>
                                >${category.name}
                                </option>
                            </c:forEach>
                        </c:if>
                    </select>
                </div>

                <!-- Product Image -->
                <div class="control-group">
                    <label for="pimage">Product Image</label>
                    <input type="file" name="pimage" id="pimage" />
                    <p class="help-block text-danger"></p>
                </div>

                <!-- Submit Button -->
                <div>
                    <button class="btn btn-primary py-2 px-4" type="submit">Update Product</button>
                </div>
            </form>

        </div>
    </div>
</div>
<!-- Add Product Section End -->

<jsp:include page="/resources/jsp/footer.jsp" />
<jsp:directive.include file="/resources/script.html"/>

</body>
</html>
