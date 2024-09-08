<!DOCTYPE html>
<html lang="en">
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

<%--<jsp:include page="/resources/jsp/header.jsp" />--%>
<%@include file="/resources/jsp/header.jsp"%>
<!-- Subscribe Start -->
<div class="container-fluid bg-secondary my-5">
    <div class="row justify-content-md-center py-5 px-xl-5">
        <div class="col-md-6 col-12 py-5">
            <div class="text-center mb-2 pb-2">
                <h2 class="section-title px-5 mb-3"><span class="bg-secondary px-2">Add New Category</span></h2>
            </div>
            <form action="/shomya/addCategory" method="post" enctype="multipart/form-data">
                <div class="control-group">

                    <input type="text" class="form-control" name="categoryName" id="categoryName" placeholder="Enter Category Name"
                           required="required" data-validation-required-message="Please enter Category Name" />
                    <p class="help-block text-danger"></p>
                </div>

                <!-- Category Image -->
                <div class="control-group">
                    <label for="categoryImage">Category Image</label>
                    <input type="file"  name="categoryImage" id="categoryImage"
                           required="required" data-validation-required-message="Please Upload Category Image" />
                    <p class="help-block text-danger"></p>
                </div>

                <!-- Submit Button -->
                <div>
                    <button class="btn btn-primary py-2 px-4" type="submit">Add</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Subscribe End -->
<%-- <!-- Categories Start -->--%>
    <div class="container-fluid pt-5">
        <div class="row px-xl-5 pb-3">
            <c:if test="${categoryList != null}">
                <c:forEach items="${categoryList}" var="category">
                    <h4>${category.name}</h4>
                    <h4>${category.image}</h4>
<%--                    <h4>${fn:length(category.products)}<h4>--%>
<%--                    <h4>${category.getProducts().size()}</h4>--%>
<%--                            <div class="col-lg-4 col-md-6 pb-1">--%>
<%--                                    <div class="cat-item d-flex flex-column border mb-4" style="padding: 30px;">--%>
<%--                                        <p class="text-right"></p>--%>
<%--                                        <a href="#" class="cat-img position-relative overflow-hidden mb-3">--%>
<%--&lt;%&ndash;                                            <img class="img-fluid" src="/resources/img/category/${category.image}" alt="image not found">&ndash;%&gt;--%>
<%--                                        </a>--%>
<%--                                        <h5 class="font-weight-semi-bold m-0">${category.name}</h5>--%>
<%--                                    </div>--%>
<%--                                </div>--%>

                </c:forEach>

            </c:if>


        </div>

<%--        <div class="col-lg-4 col-md-6 pb-1">--%>
<%--                <div class="cat-item d-flex flex-column border mb-4" style="padding: 30px;">--%>
<%--                    <p class="text-right">15 Products</p>--%>
<%--                    <a href="" class="cat-img position-relative overflow-hidden mb-3">--%>
<%--                        <img class="img-fluid" src="img/cat-1.jpg" alt="">--%>
<%--                    </a>--%>
<%--                    <h5 class="font-weight-semi-bold m-0">Men's dresses</h5>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="col-lg-4 col-md-6 pb-1">--%>
<%--                <div class="cat-item d-flex flex-column border mb-4" style="padding: 30px;">--%>
<%--                    <p class="text-right">15 Products</p>--%>
<%--                    <a href="" class="cat-img position-relative overflow-hidden mb-3">--%>
<%--                        <img class="img-fluid" src="img/cat-2.jpg" alt="">--%>
<%--                    </a>--%>
<%--                    <h5 class="font-weight-semi-bold m-0">Women's dresses</h5>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="col-lg-4 col-md-6 pb-1">--%>
<%--                <div class="cat-item d-flex flex-column border mb-4" style="padding: 30px;">--%>
<%--                    <p class="text-right">15 Products</p>--%>
<%--                    <a href="" class="cat-img position-relative overflow-hidden mb-3">--%>
<%--                        <img class="img-fluid" src="img/cat-3.jpg" alt="">--%>
<%--                    </a>--%>
<%--                    <h5 class="font-weight-semi-bold m-0">Baby's dresses</h5>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="col-lg-4 col-md-6 pb-1">--%>
<%--                <div class="cat-item d-flex flex-column border mb-4" style="padding: 30px;">--%>
<%--                    <p class="text-right">15 Products</p>--%>
<%--                    <a href="" class="cat-img position-relative overflow-hidden mb-3">--%>
<%--                        <img class="img-fluid" src="img/cat-4.jpg" alt="">--%>
<%--                    </a>--%>
<%--                    <h5 class="font-weight-semi-bold m-0">Accerssories</h5>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="col-lg-4 col-md-6 pb-1">--%>
<%--                <div class="cat-item d-flex flex-column border mb-4" style="padding: 30px;">--%>
<%--                    <p class="text-right">15 Products</p>--%>
<%--                    <a href="" class="cat-img position-relative overflow-hidden mb-3">--%>
<%--                        <img class="img-fluid" src="img/cat-5.jpg" alt="">--%>
<%--                    </a>--%>
<%--                    <h5 class="font-weight-semi-bold m-0">Bags</h5>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="col-lg-4 col-md-6 pb-1">--%>
<%--                <div class="cat-item d-flex flex-column border mb-4" style="padding: 30px;">--%>
<%--                    <p class="text-right">15 Products</p>--%>
<%--                    <a href="" class="cat-img position-relative overflow-hidden mb-3">--%>
<%--                        <img class="img-fluid" src="img/cat-6.jpg" alt="">--%>
<%--                    </a>--%>
<%--                    <h5 class="font-weight-semi-bold m-0">Shoes</h5>--%>
<%--                </div>--%>
<%--            </div>--%>

    </div>
<%--    <!-- Categories End -->--%>

<jsp:include page="/resources/jsp/footer.jsp" />
</body>
</html>