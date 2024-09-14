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
            <p class="m-0"><a href="">Home</a></p>
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
            <div class="form-group mb-4">
                <label for="productName">Search by Name</label>
                <input type="text" class="form-control" id="productName" placeholder="Enter product name">
            </div>
            <!-- Search by Name End -->

            <!-- Filter by price range Start -->
            <div class="form-group mb-4">
                <label for="minPrice">Min Price:</label>
                <input type="number" class="form-control" id="minPrice" placeholder="Min price">

                <label for="maxPrice" class="mt-2">Max Price:</label>
                <input type="number" class="form-control" id="maxPrice" placeholder="Max price">
            </div>
            <!-- Filter by price range End -->

            <!-- Filter by categories Start -->
            <div class="form-group">
                <label for="choose_categories">Filter by Categories</label>
                <div id="choose_categories">
                    <c:forEach var="category" items="${categories}">
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" name="categories" value="${category.id}" id="${category.id}">
                            <label class="form-check-label" for="${category.id}">
                                ${category.name}
                            </label>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <!-- Filter by categories End -->
        </div>
        <!-- Shop Sidebar End -->

        <!-- Results Section Start -->
        <div class="col-lg-9 col-md-12" id="searchResults">
            <!-- Products Section Start -->
            <div class="container-fluid pt-5">
                <div class="text-center mb-4">
                    <h2 class="section-title px-5"><span class="px-2">Products</span></h2>
                </div>
                <div id="alert-container"></div>
                <div class="row px-xl-5 pb-3">
                    <c:if test="${products != null}">
                        <c:forEach items="${products}" var="product">
                            <div class="col-lg-4 col-md-6 col-sm-12 pb-1">
                                <div class="card product-item border-0 mb-4">
                                    <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                                        <a href="/shomya/search-product?productId=${product.id}">
                                            <img class="img-fluid fixed-size" src="/shomya/resources/img/${product.image}" alt="Product Image">
                                        </a>
                                    </div>
                                    <div class="card-body text-center p-0 pt-4 pb-3">
                                        <h6 class="text-truncate mb-3">${product.name}</h6>
                                        <div class="d-flex justify-content-center">
                                            <h6>$${product.price}</h6>
                                        </div>
                                    </div>
                                    <div class="card-footer d-flex justify-content-between bg-light border">
                                        <a href="/shomya/viewproduct?productId=${product.id}" class="btn btn-sm text-dark p-0">
                                            <i class="fas fa-eye text-primary mr-1"></i>View Detail
                                        </a>
                                        <c:if test="${userRole == EnumHelper.getCustomerRole()}">
                                            <button type="button" class="btn btn-sm text-dark p-0" onclick="addtoCart(${product.id})">
                                                <i class="fas fa-shopping-cart text-primary mr-1"></i>Add To Cart
                                            </button>
                                        </c:if>
                                        <c:if test="${userRole == EnumHelper.getAdminRole()}">
                                            <a href="/shomya/deleteproduct?productId=${product.id}" class="btn btn-sm text-dark p-0">
                                                <i class="fas fa-trash text-danger mr-1"></i>Delete
                                            </a>
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

<jsp:include page="/resources/jsp/footer.jsp" />

<!-- Back to Top -->
<a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>

<script>
    // Function to search products by name and filter by price and category
    function searchProducts() {
        const name = document.getElementById('productName').value;
        const minPrice = document.getElementById('minPrice').value;
        const maxPrice = document.getElementById('maxPrice').value;
        const category = document.querySelector('input[name="categories"]:checked')?.value;

        const params = new URLSearchParams({
            name: name,
            category: category,
            minPrice: minPrice,
            maxPrice: maxPrice
        });

        const xhr = new XMLHttpRequest();
        xhr.open('GET', 'http://localhost:8080/shomya/search-product?' + params.toString(), true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                document.getElementById('searchResults').innerHTML = xhr.responseText;
            }
        };
        xhr.send();
    }

    document.getElementById('productName').addEventListener('keyup', searchProducts);
    document.getElementById('minPrice').addEventListener('input', searchProducts);
    document.getElementById('maxPrice').addEventListener('input', searchProducts);
    document.querySelectorAll('input[name="categories"]').forEach(function (el) {
        el.addEventListener('change', searchProducts);
    });

    function addtoCart(id) {
        const xhr = new XMLHttpRequest();
        const url = "/shomya/addtocart?productId=" + id + "&quantity=1";

        xhr.open("GET", url, true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    const result = xhr.responseText.trim();
                    const alertContainer = document.getElementById('alert-container');

                    if (result === "done") {
                        alertContainer.innerHTML = `
                            <div class="alert alert-success" role="alert">
                                Product Added <a href="/shomya/viewcart" class="alert-link">View Cart</a>
                            </div>`;
                    } else {
                        alertContainer.innerHTML = `
                            <div class="alert alert-danger" role="alert">
                                Can't add <a href="/shomya/viewcart" class="alert-link">View Cart</a>
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
