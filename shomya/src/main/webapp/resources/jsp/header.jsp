<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ page import="iti.jets.business.service.helper.EnumHelper" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script>
  document.addEventListener("DOMContentLoaded", function() {
    var currentPath = window.location.pathname;
    var navLinks = document.querySelectorAll(".nav-item");

    navLinks.forEach(function(link) {
      if (link.getAttribute("href") === currentPath) {
        link.classList.add("active");
      }
    });
  });
</script>

<!-- Topbar Start -->

<div class="container-fluid">
  <div class="row align-items-center py-3 px-xl-5">
    <div class="col-lg-3 d-none d-lg-block">
      <a href="/shomya" class="text-decoration-none">
        <h1 class="m-0 display-5 font-weight-semi-bold">
          <span class="text-primary font-weight-bold border px-3 mr-1">S</span>HOMYA
        </h1>
      </a>
    </div>


    <c:if test="${userRole == EnumHelper.getCustomerRole()}">
      <div class="col-lg-6 col-6 text-left">
        <form action="">
          <div class="input-group">
            <input type="text" class="form-control" placeholder="Search for products">
            <div class="input-group-append">
                        <span class="input-group-text bg-transparent text-primary">
                            <i class="fa fa-search"></i>
                        </span>
            </div>
          </div>
        </form>
      </div>
      <div class="col-lg-3 col-6">
        <div class="d-flex align-items-center gap-2">
          <a href="" class="btn border">
            <i class="fas fa-heart text-primary"></i>
            <span class="badge">0</span>
          </a>
          <a href="/shomya/app/viewcart" class="btn border">
            <i class="fas fa-shopping-cart text-primary"></i>
            <span class="badge">0</span>
          </a>
        </div>
      </div>
    </c:if>
  </div>
</div>


<!-- Topbar End -->
<!-- ------------------------------------------------------------------------------- -->
<!-- Navbar Start -->


<div class="container-fluid mb-5">
  <div class="row border-top px-xl-5">
    <div class="col-lg-9">
      <nav class="navbar navbar-expand-lg bg-light navbar-light py-3 py-lg-0 px-0">
        <a href="" class="text-decoration-none d-block d-lg-none">
          <h1 class="m-0 display-5 font-weight-semi-bold">
            <span class="text-primary font-weight-bold border px-3 mr-1">S</span>HOMYA
          </h1>
        </a>
        <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
          <div class="navbar-nav mr-auto py-0">
            <a href="/shomya/" class="nav-item nav-link ${pageContext.request.requestURI.endsWith('/') ? 'active' : ''}">Home</a>
            <c:if test="${userRole == EnumHelper.getAdminRole()}">
              <a href="/shomya/app/categories" class="nav-item nav-link ${pageContext.request.requestURI.endsWith('/categories') ? 'active' : ''}">Categories</a>
              <a href="/shomya/app/products" class="nav-item nav-link ${pageContext.request.requestURI.endsWith('/products') ? 'active' : ''}">Products</a>
              <a href="/shomya/app/addproduct" class="nav-item nav-link ${pageContext.request.requestURI.endsWith('/addproduct') ? 'active' : ''}">Add Products</a>
              <a href="/shomya/app/customers" class="nav-item nav-link ${pageContext.request.requestURI.endsWith('/customers') ? 'active' : ''}">Customers</a>
            </c:if>
            <c:if test="${userRole == EnumHelper.getCustomerRole()}">
              <a href="/shomya/app/products" class="nav-item nav-link ${pageContext.request.requestURI.endsWith('/products') ? 'active' : ''}">View Products</a>
              <a href="/shomya/app/viewcart" class="nav-item nav-link ${pageContext.request.requestURI.endsWith('/viewcart') ? 'active' : ''}">My Cart</a>
              <a href="/shomya/app/search-product" class="nav-item nav-link ${pageContext.request.requestURI.endsWith('/shop.jsp') ? 'active' : ''}">My Shop</a>
              <a href="#" class="nav-item nav-link">My Orders</a>
            </c:if>
          </div>
          <c:choose>
            <c:when test="${user != null}">
              <div class="navbar-nav ml-auto py-0">
                <a href="/shomya/app/viewprofile" class="nav-item nav-link ${pageContext.request.requestURI.endsWith('/ViewProfileServlet') ? 'active' : ''}">Account</a>
                <a href="/shomya/app/logout" class="nav-item nav-link ${pageContext.request.requestURI.endsWith('/logout') ? 'active' : ''}">Logout</a>
              </div>
            </c:when>
            <c:otherwise>
              <div class="navbar-nav ml-auto py-0">
                <a href="/shomya/app/login" class="nav-item nav-link ${pageContext.request.requestURI.endsWith('/login') ? 'active' : ''}">Login</a>
                <a href="/shomya/app/registration" class="nav-item nav-link ${pageContext.request.requestURI.endsWith('/registration') ? 'active' : ''}">Register</a>
              </div>
            </c:otherwise>
          </c:choose>
        </div>
      </nav>
    </div>
  </div>
</div>
<!-- Navbar End -->