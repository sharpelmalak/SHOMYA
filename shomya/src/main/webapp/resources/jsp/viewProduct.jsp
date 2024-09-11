
<%@ page import="iti.jets.model.Product" %>
<%@ page import="iti.jets.model.Category" %>

<html>
<jsp:directive.include file="/resources/head.html"/>
<body>

<%@ include file="/resources/jsp/header.jsp" %>

<!-- Shop Detail Start -->
<div class="container-fluid py-5">
    <div class="row px-xl-5">
        <div class="col-lg-5 pb-5">
            <div id="product-carousel" class="carousel slide" data-ride="carousel">
                <div class="carousel-inner border">
                    <div class="carousel-item active">
                        <img class="w-100 h-100" src="/shomya/resources/img/${product.image}" alt="Image">
                    </div>
                </div>

            </div>
        </div>

        <div class="col-lg-7 pb-5">
            <h3 class="font-weight-semi-bold">${product.name}</h3>

            <h3 class="font-weight-semi-bold mb-4">$${product.price}</h3>
            <p class="mb-4">${product.description}</p>
            <div class="d-flex align-items-center mb-4 pt-2">
                <div class="input-group quantity mr-3" style="width: 130px;">
                    <div class="input-group-btn">
                        <button class="btn btn-primary btn-minus" >
                            <i class="fa fa-minus"></i>
                        </button>
                    </div>
                    <input type="text" class="form-control bg-secondary text-center" value="1">
                    <div class="input-group-btn">
                        <button class="btn btn-primary btn-plus">
                            <i class="fa fa-plus"></i>
                        </button>
                    </div>
                </div>
                <button class="btn btn-primary px-3"><i class="fa fa-shopping-cart mr-1"></i> Add To Cart</button>
            </div>

        </div>
    </div>
</div>
<!-- Shop Detail End -->


<!-- Products Start -->
<div class="container-fluid py-5">
    <div class="text-center mb-4">
        <h2 class="section-title px-5"><span class="px-2">You May Also Like in ${product.category.name}</span></h2>
    </div>
    <div class="row px-xl-5">
        <div class="col">
            <div class="owl-carousel related-carousel">

                <c:forEach items="${product.category.products}" var="pr">
                <div class="card product-item border-0">
                    <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                        <a href="/shomya/viewproduct?productId=${pr.id}">
                        <img class="img-fluid w-100" src="/shomya/resources/img/${pr.image}" alt="image">
                        </a>
                    </div>
                    <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                        <h6 class="text-truncate mb-3">${pr.name}</h6>
                        <div class="d-flex justify-content-center">
                            <h6>$${pr.price}</h6><h6 class="text-muted ml-2"><del>$5</del></h6>
                        </div>
                    </div>
                    <div class="card-footer d-flex justify-content-between bg-light border">
                        <a href="/shomya/viewproduct?productId=${pr.id}" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>View Detail</a>
                        <a href="#" class="btn btn-sm text-dark p-0"><i class="fas fa-shopping-cart text-primary mr-1"></i>Add To Cart</a>
                    </div>
                </div>
                </c:forEach>

            </div>
        </div>
    </div>
</div>
<!-- Products End -->

<jsp:directive.include file="/resources/jsp/footer.jsp" />
<jsp:directive.include file="/resources/script.html"/>

</body>
</html>
