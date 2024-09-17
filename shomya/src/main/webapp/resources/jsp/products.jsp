<%@ page import="iti.jets.persistence.model.Category" %>
<%@ page import="iti.jets.persistence.model.Product" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
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
                                <img id="product-image-${product.id}" data-product-id="${product.id}" class="img-fluid fixed-size" src="/shomya/resources/img/default.jpg" alt="Loading...">
                            </a>
                        </div>
                        <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                            <h6 class="text-truncate mb-3">${product.name}</h6>
                            <div class="d-flex justify-content-center">
                                <h6>$${product.price} <del> $<fmt:formatNumber value="${(product.price+(product.price*0.20))}" type="number" minFractionDigits="2" maxFractionDigits="2" /></del>></h6>
                            </div>
                        </div>

                        <div class="card-footer d-flex justify-content-between bg-light border">
                            <a href="/shomya/app/viewproduct?productId=${product.id}" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>View Detail</a>
                            <c:if test="${userRole == EnumHelper.getCustomerRole()}">
                                <c:if test="${product.quantity>0}">
                                <button type="button" class="btn btn-sm text-dark p-0" onclick="addtoCart(${product.id})"><i class="fas fa-shopping-cart text-primary mr-1"></i>Add To Cart</button>
                                </c:if>
                                <c:if test="${product.quantity==0}">
                                    <h6 class="text-truncate mb-3" style="color: #bd726b">Out of stock</h6>
                                </c:if>
                                </c:if>
                            <c:if test="${userRole == EnumHelper.getAdminRole()}">
                                <a href="/shomya/app/deleteproduct?productId=${product.id}" class="btn btn-sm text-dark p-0"><i class="fas fa-trash text-danger mr-1"></i>Delete</a>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:if>

        <!-- Pagination Controls -->
        <div class="col-12 pb-1">
            <nav aria-label="Page navigation">
                <ul class="pagination justify-content-center mb-3">
                    <!-- "Previous" Button -->
                    <c:if test="${currentPage > 1}">
                        <li class="page-item">
                            <a class="page-link" href="/shomya/app/products?page=${currentPage - 1}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                                <span class="sr-only">Previous</span>
                            </a>
                        </li>
                    </c:if>

                    <!-- Page Numbers -->
                    <c:forEach var="i" begin="1" end="${totalPages}">
                        <li class="page-item <c:if test='${i == currentPage}'>active</c:if>">
                            <a class="page-link" href="/shomya/app/products?page=${i}">${i}</a>
                        </li>
                    </c:forEach>

                    <!-- "Next" Button -->
                    <c:if test="${currentPage < totalPages}">
                        <li class="page-item">
                            <a class="page-link" href="/shomya/app/products?page=${currentPage + 1}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                                <span class="sr-only">Next</span>
                            </a>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </div>


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
                    else if(result.trim() == "view"){
                        $('#alert-container').html(`
                       <div class="alert alert-success" role="alert">
                Cart Updated <a href="/shomya/app/viewcart" class="alert-link">View Cart</a>
                </div>
                       ` );
                    }

                } else {
                    // Oh no! There has been an error with the request!
                }
            }
        };

        xhr.send(null);
    }


</script>


<script>
    document.addEventListener('DOMContentLoaded', async () => {
        // Select all image elements that you want to load
        const productImages = document.querySelectorAll("[id^='product-image-']");
        async function loadImageSequentially() {
            // Iterate over each image element
            for (const imgElement of productImages) {
                // Extract the category ID from the data attribute (e.g., data-category-id="1")
                const productId = imgElement.getAttribute('data-product-id');

                try {
                    // Wait for the image to load
                    const imgSrc = await loadImage(productId);
                    imgElement.src = imgSrc; // Replace the placeholder with the loaded image
                } catch (error) {
                    console.error(`Failed to load image for category ID: `+productId, error);
                }
            }
        }

        function loadImage(productId) {
            return new Promise((resolve, reject) => {
                const img = new Image();
                img.src = `/shomya/app/productImage?productId=`+productId;

                img.onload = () => {
                    resolve(img.src);
                };

                img.onerror = () => {
                    reject(new Error(`Failed to load image for category ID:`+productId));
                };
            });
        }

        // Start loading images
        await loadImageSequentially();
    });
</script>
</body>
</html>