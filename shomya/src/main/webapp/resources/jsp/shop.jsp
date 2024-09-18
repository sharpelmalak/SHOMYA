<!DOCTYPE html>
<html lang="en" xmlns:jsp="jakarta.faces.html" xmlns:c="http://xmlns.jcp.org/jsf/composite">
<jsp:directive.include file="/resources/head.html"/>

<body>
<%@ include file="/resources/jsp/header.jsp" %>

<!-- Page Header Start -->
<div class="container-fluid bg-secondary mb-5">
    <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
        <h1 class="font-weight-semi-bold text-uppercase mb-3">Our Shop</h1>
        <div class="d-inline-flex">
            <p class="m-0"><a href="/shomya">Home</a></p>
            <p class="m-0 px-2">-</p>
            <p class="m-0">Shop</p>
        </div>
    </div>
</div>
<!-- Page Header End -->

<!-- Shop Start -->
<div class="container-fluid pt-5">
    <div class="row px-xl-5">
        <!-- Shop Sidebar Start -->
        <div class="col-lg-3 col-md-12">
            <!-- Search by Name Start -->
            <form method="post" action="/shomya/app/shop">
            <div class="form-group mb-4">
                <label for="productName">Search by Name</label>
                <input type="text" class="form-control" id="productName" name="name" placeholder="Enter product name">
            </div>
            <!-- Search by Name End -->

            <!-- Filter by price range Start -->
            <div class="form-group mb-4">
                <label for="minPrice">Min Price:</label>
                <input type="number" class="form-control" id="minPrice" name="minprice" placeholder="Min price">

                <label for="maxPrice" class="mt-2">Max Price:</label>
                <input type="number" class="form-control" id="maxPrice" name="maxprice" placeholder="Max price">
            </div>
            <!-- Filter by price range End -->

            <!-- Filter by categories Start -->
            <div class="form-group">
                <label for="choose_categories">Filter by Categories</label>
                <div id="choose_categories">
                    <c:forEach var="category" items="${categories}">
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" name="categories" value="${category.id}" id="${category.id}">
                            <label class="form-check-label" name="${category.id}">
                                ${category.name}
                            </label>
                        </div>
                    </c:forEach>
                </div>
            </div>
                <button class="btn btn-primary py-2 px-4" type="submit" >Search</button>
            </form>
            <!-- Filter by categories End -->
        </div>
        <!-- Shop Sidebar End -->

        <!-- Results Section Start -->
        <div class="col-lg-9 col-md-12" >
            <!-- Products Section Start -->
            <div class="container-fluid pt-5">
                <div class="text-center mb-4">
                    <h2 class="section-title px-5"><span class="px-2">Products</span></h2>
                </div>

                <div class="row px-xl-5 pb-3">
                    <c:if test="${products != null}">
                        <c:forEach items="${products}" var="product">
                            <div class="col-lg-4 col-md-6 col-sm-12 pb-1">
                                <div class="card product-item border-0 mb-4">
                                    <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                                        <a href="/shomya/app/viewproduct?productId=${product.id}">
                                            <img id="product-image-${product.id}" data-product-id="${product.id}" class="img-fluid fixed-size" src="/shomya/resources/img/default.jpg" alt="Loading...">
                                        </a>
                                    </div>
                                    <div class="card-body text-center p-0 pt-4 pb-3">
                                        <h6 class="text-truncate mb-3">${product.name}</h6>
                                        <div class="d-flex justify-content-center">
                                            <h6>$${product.price}</h6>
                                        </div>
                                    </div>
                                    <div class="card-footer d-flex justify-content-between bg-light border">
                                        <a href="/shomya/app/viewproduct?productId=${product.id}" class="btn btn-sm text-dark p-0">
                                            <i class="fas fa-eye text-primary mr-1"></i>View Detail
                                        </a>
                                        <c:if test="${product.quantity>0}">
                                            <button type="button" class="btn btn-sm text-dark p-0" onclick="addtoCart(${product.id})"><i class="fas fa-shopping-cart text-primary mr-1"></i>Add To Cart</button>
                                        </c:if>
                                        <c:if test="${product.quantity==0}">
                                            <h6 class="text-truncate mb-3" style="color: #bd726b">Out of stock</h6>
                                        </c:if>

                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
            <!-- Products Section End -->
        </div>
        <!-- Results Section End -->
    </div>
</div>
<!-- Shop End -->

<div class="col-12 pb-1">
    <nav aria-label="Page navigation">
        <ul class="pagination justify-content-center mb-3">
            <!-- "Previous" Button -->
            <c:if test="${currentPage > 1}">
                <li class="page-item">
                    <a class="page-link" href="/shomya/app/shop?page=${currentPage - 1}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                        <span class="sr-only">Previous</span>
                    </a>
                </li>
            </c:if>

            <!-- Page Numbers -->
            <c:forEach var="i" begin="1" end="${totalPages}">
                <li class="page-item <c:if test='${i == currentPage}'>active</c:if>">
                    <a class="page-link" href="/shomya/app/shop?page=${i}">${i}</a>
                </li>
            </c:forEach>

            <!-- "Next" Button -->
            <c:if test="${currentPage < totalPages}">
                <li class="page-item">
                    <a class="page-link" href="/shomya/app/shop?page=${currentPage + 1}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                        <span class="sr-only">Next</span>
                    </a>
                </li>
            </c:if>
        </ul>
    </nav>
</div>

<jsp:include page="/resources/jsp/footer.jsp" />

<!-- Back to Top -->
<a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>

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

    function addtoCart(id) {
        const xhr = new XMLHttpRequest();
        const url = "/shomya/app/addtocart?productId=" + id + "&quantity=1";

        xhr.open("GET", url, true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    const result = xhr.responseText.trim();
                    const alertContainer = document.getElementById('alert-container');

                    if (result === "done") {
                        alertContainer.innerHTML = `
                            <div class="alert alert-success" role="alert">
                                Product Added <a href="/shomya/app/viewcart" class="alert-link">View Cart</a>
                            </div>`;
                    } else {
                        alertContainer.innerHTML = `
                            <div class="alert alert-danger" role="alert">
                                Can't add <a href="/shomya/app/viewcart" class="alert-link">View Cart</a>
                            </div>`;
                    }

                    setTimeout(function () {
                        alertContainer.innerHTML = '';
                    }, 3000);
                }
            }
        };
        xhr.send(null);
    }
</script>

</body>
</html>
