<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="iti.jets.persistence.model.Category, iti.jets.persistence.model.Product" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Products and Categories</title>
  <!-- Add your CSS here -->
</head>
<body>
<!-- Categories Section Start -->
<div class="container">
  <div class="text-center mb-4">
    <h2 class="section-title px-5"><span class="px-2">Categories</span></h2>
  </div>
  <div class="row px-xl-5 pb-3">
    <c:if test="${categories != null}">
      <c:forEach items="${categories}" var="category">
        <div class="col-lg-3 col-md-4 col-sm-6 pb-1">
          <div class="card category-item border-0 mb-4">
            <div class="card-body text-center p-0 pt-4 pb-3">
              <h6 class="text-truncate mb-3">${category.name}</h6>
            </div>
          </div>
        </div>
      </c:forEach>
    </c:if>
  </div>
</div>
<!-- Categories Section End -->

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
              <a href="/shomya/app/viewproduct?productId=${product.id}">
                <img class="img-fluid fixed-size" src="/shomya/resources/img/${product.image}" alt="Product Image">
              </a>
            </div>
            <div class="card-body text-center p-0 pt-4 pb-3">
              <h6 class="text-truncate mb-3">${product.name}</h6>
              <div class="d-flex justify-content-center">
                <h6>${product.price}</h6>
              </div>
            </div>
            <div class="card-footer d-flex justify-content-between bg-light border">
              <a href="/shomya/app/viewproduct?productId=${product.id}" class="btn btn-sm text-dark p-0">
                <i class="fas fa-eye text-primary mr-1"></i>View Detail
              </a>
              <c:if test="${userRole == EnumHelper.getCustomerRole()}">
                <button type="button" class="btn btn-sm text-dark p-0" onclick="addtoCart(${product.id})">
                  <i class="fas fa-shopping-cart text-primary mr-1"></i>Add To Cart
                </button>
              </c:if>
            </div>
          </div>
        </div>
      </c:forEach>
    </c:if>
  </div>
</div>
<!-- Products Section End -->
</body>
</html>
