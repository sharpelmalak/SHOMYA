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

<!-- Products Start -->
<div class="container-fluid pt-5">
    <div class="text-center mb-4">
        <h2 class="section-title px-5"><span class="px-2">Trandy Products</span></h2>
    </div>
    <div class="row px-xl-5 pb-3">

        <c:if test="${productList != null}">
            <c:forEach items="${productList}" var="product">
                <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                    <div class="card product-item border-0 mb-4">
                        <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                            <a href="/shomya/viewproduct?productId=${product.id}">
                                <img class="img-fluid fixed-size" src="/shomya/resources/img/${product.image}" alt="not Found">
                            </a>
                        </div>
                        <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                            <h6 class="text-truncate mb-3">${product.name}</h6>
                            <div class="d-flex justify-content-center">
                                <h6>$${product.price}</h6>
<%--                                <h6>$${product.price}</h6><h6 class="text-muted ml-2"><del>$123.00</del></h6>--%>
                            </div>
                        </div>

                        <div class="card-footer d-flex justify-content-between bg-light border">
                            <a href="/shomya/viewproduct?productId=${product.id}" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>View Detail</a>
                            <c:if test="${userRole == EnumHelper.getCustomerRole()}">
                                <a href="#" class="btn btn-sm text-dark p-0"><i class="fas fa-shopping-cart text-primary mr-1"></i>Add To Cart</a>0
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:if>
<%--        <div class="col-lg-3 col-md-6 col-sm-12 pb-1">--%>
<%--            <div class="card product-item border-0 mb-4">--%>
<%--                <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">--%>
<%--                    <img class="img-fluid w-100" src="/shomya/resources/img/product-2.jpg" alt="">--%>
<%--                </div>--%>
<%--                <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">--%>
<%--                    <h6 class="text-truncate mb-3">Colorful Stylish Shirt</h6>--%>
<%--                    <div class="d-flex justify-content-center">--%>
<%--                        <h6>$123.00</h6><h6 class="text-muted ml-2"><del>$123.00</del></h6>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="card-footer d-flex justify-content-between bg-light border">--%>
<%--                    <a href="" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>View Detail</a>--%>
<%--                    <a href="" class="btn btn-sm text-dark p-0"><i class="fas fa-shopping-cart text-primary mr-1"></i>Add To Cart</a>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="col-lg-3 col-md-6 col-sm-12 pb-1">--%>
<%--            <div class="card product-item border-0 mb-4">--%>
<%--                <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">--%>
<%--                    <img class="img-fluid w-100" src="/shomya/resources/img/product-3.jpg" alt="">--%>
<%--                </div>--%>
<%--                <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">--%>
<%--                    <h6 class="text-truncate mb-3">Colorful Stylish Shirt</h6>--%>
<%--                    <div class="d-flex justify-content-center">--%>
<%--                        <h6>$123.00</h6><h6 class="text-muted ml-2"><del>$123.00</del></h6>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="card-footer d-flex justify-content-between bg-light border">--%>
<%--                    <a href="" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>View Detail</a>--%>
<%--                    <a href="" class="btn btn-sm text-dark p-0"><i class="fas fa-shopping-cart text-primary mr-1"></i>Add To Cart</a>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="col-lg-3 col-md-6 col-sm-12 pb-1">--%>
<%--            <div class="card product-item border-0 mb-4">--%>
<%--                <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">--%>
<%--                    <img class="img-fluid w-100" src="/shomya/resources/img/product-4.jpg" alt="">--%>
<%--                </div>--%>
<%--                <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">--%>
<%--                    <h6 class="text-truncate mb-3">Colorful Stylish Shirt</h6>--%>
<%--                    <div class="d-flex justify-content-center">--%>
<%--                        <h6>$123.00</h6><h6 class="text-muted ml-2"><del>$123.00</del></h6>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="card-footer d-flex justify-content-between bg-light border">--%>
<%--                    <a href="" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>View Detail</a>--%>
<%--                    <a href="" class="btn btn-sm text-dark p-0"><i class="fas fa-shopping-cart text-primary mr-1"></i>Add To Cart</a>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="col-lg-3 col-md-6 col-sm-12 pb-1">--%>
<%--            <div class="card product-item border-0 mb-4">--%>
<%--                <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">--%>
<%--                    <img class="img-fluid w-100" src="/shomya/resources/img/product-5.jpg" alt="">--%>
<%--                </div>--%>
<%--                <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">--%>
<%--                    <h6 class="text-truncate mb-3">Colorful Stylish Shirt</h6>--%>
<%--                    <div class="d-flex justify-content-center">--%>
<%--                        <h6>$123.00</h6><h6 class="text-muted ml-2"><del>$123.00</del></h6>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="card-footer d-flex justify-content-between bg-light border">--%>
<%--                    <a href="" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>View Detail</a>--%>
<%--                    <a href="" class="btn btn-sm text-dark p-0"><i class="fas fa-shopping-cart text-primary mr-1"></i>Add To Cart</a>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="col-lg-3 col-md-6 col-sm-12 pb-1">--%>
<%--            <div class="card product-item border-0 mb-4">--%>
<%--                <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">--%>
<%--                    <img class="img-fluid w-100" src="/shomya/resources/img/product-6.jpg" alt="">--%>
<%--                </div>--%>
<%--                <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">--%>
<%--                    <h6 class="text-truncate mb-3">Colorful Stylish Shirt</h6>--%>
<%--                    <div class="d-flex justify-content-center">--%>
<%--                        <h6>$123.00</h6><h6 class="text-muted ml-2"><del>$123.00</del></h6>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="card-footer d-flex justify-content-between bg-light border">--%>
<%--                    <a href="" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>View Detail</a>--%>
<%--                    <a href="" class="btn btn-sm text-dark p-0"><i class="fas fa-shopping-cart text-primary mr-1"></i>Add To Cart</a>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="col-lg-3 col-md-6 col-sm-12 pb-1">--%>
<%--            <div class="card product-item border-0 mb-4">--%>
<%--                <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">--%>
<%--                    <img class="img-fluid w-100" src="/shomya/resources/img/product-7.jpg" alt="">--%>
<%--                </div>--%>
<%--                <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">--%>
<%--                    <h6 class="text-truncate mb-3">Colorful Stylish Shirt</h6>--%>
<%--                    <div class="d-flex justify-content-center">--%>
<%--                        <h6>$123.00</h6><h6 class="text-muted ml-2"><del>$123.00</del></h6>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="card-footer d-flex justify-content-between bg-light border">--%>
<%--                    <a href="" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>View Detail</a>--%>
<%--                    <a href="" class="btn btn-sm text-dark p-0"><i class="fas fa-shopping-cart text-primary mr-1"></i>Add To Cart</a>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="col-lg-3 col-md-6 col-sm-12 pb-1">--%>
<%--            <div class="card product-item border-0 mb-4">--%>
<%--                <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">--%>
<%--                    <img class="img-fluid w-100" src="/shomya/resources/img/product-8.jpg" alt="">--%>
<%--                </div>--%>
<%--                <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">--%>
<%--                    <h6 class="text-truncate mb-3">Colorful Stylish Shirt</h6>--%>
<%--                    <div class="d-flex justify-content-center">--%>
<%--                        <h6>$123.00</h6><h6 class="text-muted ml-2"><del>$123.00</del></h6>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="card-footer d-flex justify-content-between bg-light border">--%>
<%--                    <a href="" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>View Detail</a>--%>
<%--                    <a href="" class="btn btn-sm text-dark p-0"><i class="fas fa-shopping-cart text-primary mr-1"></i>Add To Cart</a>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
    </div>
</div>
<!-- Products End -->

<jsp:include page="/resources/jsp/footer.jsp" />
</body>
</html>