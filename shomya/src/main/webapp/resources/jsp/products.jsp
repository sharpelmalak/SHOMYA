<%@ page import="iti.jets.persistence.model.Category" %>
<%@ page import="iti.jets.persistence.model.Product" %>
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

<%--<jsp:include page="/resources/jsp/header.jsp" />--%>
<%@include file="/resources/jsp/header.jsp"%>

<!-- Products Start -->
<div class="container-fluid pt-5">
    <div class="text-center mb-4">
        <h2 class="section-title px-5"><span class="px-2">Trandy Products</span></h2>
    </div>
    <div id="alert-container"></div>
    <div class="row px-xl-5 pb-3">

        <c:if test="${productList != null}">
            <c:forEach items="${productList}" var="product">
                <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                    <div class="card product-item border-0 mb-4">
                        <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                            <a href="/shomya/app/viewproduct?productId=${product.id}">
                                <img class="img-fluid fixed-size" src="/shomya/app/productImage?productId=${product.id}" alt="not Found">
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
                            <a href="/shomya/app/viewproduct?productId=${product.id}" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>View Detail</a>
                            <c:if test="${userRole == EnumHelper.getCustomerRole()}">
                                <button type="button" class="btn btn-sm text-dark p-0" onclick="addtoCart(${product.id})"><i class="fas fa-shopping-cart text-primary mr-1"></i>Add To Cart</button>
                            </c:if>
                            <c:if test="${userRole == EnumHelper.getAdminRole()}">
                                <a href="/shomya/app/deleteproduct?productId=${product.id}" class="btn btn-sm text-dark p-0"><i class="fas fa-trash text-danger mr-1"></i>Delete</a>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:if>

    </div>
</div>
<!-- Products End -->

<jsp:include page="/resources/jsp/footer.jsp" />
<jsp:directive.include file="/resources/script.html"/>

<script>
    function addtoCart(id) {
        const xhr = new XMLHttpRequest();
        const method = "GET";
        const url = "/shomya/app/addtocart?productId="+id+"&quantity=1";

        xhr.open(method, url, true);
        xhr.onreadystatechange = () => {
            // In local files, status is 0 upon success in Mozilla Firefox
            if (xhr.readyState === XMLHttpRequest.DONE) {
                const status = xhr.status;
                if (status === 0 || (status >= 200 && status < 400)) {
                    // The request has been completed successfully
                    let result = xhr.responseText;
                    console.log(result);
                    if (result.trim() == "done") {
                        $('#alert-container').html(`
                        <div class="alert alert-success" role="alert">
                            Product Added <a href="/shomya/app/viewcart" class="alert-link">View Cart</a>
                     </div>
                     `);
                    } else if(result.trim() == "error"){
                        $('#alert-container').html(`
                       <div class="alert alert-danger" role="alert">
                Can't add <a href="/shomya/app/viewcart" class="alert-link">View Cart</a>
                </div>
                       ` );
                    }
                    // Automatically hide the alert after 3 seconds
                    setTimeout(function() {
                        $('#alert-container').html(''); // Clear the alert
                    }, 3000); // 3000 milliseconds = 3 seconds
                } else {
                    // Oh no! There has been an error with the request!
                }
            }
        };

        xhr.send(null);
    }


</script>
</body>
</html>