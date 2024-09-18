<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ page import="iti.jets.business.service.helper.EnumHelper" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<style>
  .hidden {
    display: none;
  }

  .position-relative {
    position: relative; /* Ensure the parent has position for absolute positioning of the child */
  }

  .search-results {
    position: absolute; /* Position the suggestions box absolutely */
    top: 100%; /* Place it directly below the input field */
    left: 0; /* Align to the left of the parent container */
    width: 100%; /* Make it the same width as the parent container */
    background: white;
    border: 1px solid #ddd;
    border-top: none; /* Optional: Remove top border to blend with the search input */
    box-shadow: 0px 4px 8px rgba(0,0,0,0.2);
    z-index: 1000;
    max-height: 300px; /* Optional: Limit the height if there are many suggestions */
    overflow-y: auto; /* Add scroll if content overflows */
  }

  .search-results div {
    padding: 10px;
    cursor: pointer;
  }

  .search-results div:hover {
    background-color: #f1f1f1;
  }

  .hidden {
    display: none; /* Hide the search results initially */
  }


</style>
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

      <!-- Search Container -->
      <div class="col-lg-6 col-6 text-left position-relative">
        <form id="search-form">
          <div class="input-group">
            <input type="text" class="form-control" id="search-input" placeholder="Search for products" autocomplete="off" />
            <div class="input-group-append">
        <span class="input-group-text bg-transparent text-primary">
           <a href="#"  id="search-link">
             <i class="fa fa-search"></i>
          </a>
        </span>
            </div>
          </div>
        </form>

        <!-- Hidden suggestion box -->
        <div id="search-results" class="search-results hidden"></div>
      </div>


      <div class="col-lg-3 col-6">
        <div class="d-flex align-items-center gap-2">
          <a href="/shomya/app/viewcart" class="btn border" >
            <i class="fas fa-shopping-cart text-primary"></i>
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
              <a href="/shomya/app/shop" class="nav-item nav-link ${pageContext.request.requestURI.endsWith('/shop') ? 'active' : ''}">Shop</a>
              <a href="/shomya/app/customerOrder?customerId=${user.id}"  class="nav-item nav-link ${pageContext.request.requestURI.contains('/customerOrder') ? 'active' : ''}">My Orders</a>
              <a href="/shomya/app/view-interests"  class="nav-item nav-link ${pageContext.request.requestURI.contains('/view-interests') ? 'active' : ''}">My Interests</a>

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


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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


  $(document).ready(function() {
    function fetchSearchResults(query) {
      $.ajax({
        url: '/shomya/app/search-product',
        method: 'GET',
        data: { search: query },
        success: function(data) {
          // Update the suggestion box with the received data
          $('#search-results').html(data).removeClass('hidden');
        },
        error: function(xhr, status, error) {
          console.error('Error fetching search results:', error);
          $('#search-results').html('<p>Error fetching results. Please try again.</p>').removeClass('hidden');
        }
      });
    }

    $('#search-input').on('input', function() {
      const query = $(this).val().trim();
      if (query.length > 0) {
        $('#search-link').attr('href', "/shomya/app/products?search="+query);
        fetchSearchResults(query);
      } else {
        $('#search-results').empty().addClass('hidden'); // Hide results if search query is empty
      }
    });

    // Handle clicks on suggestion items
    $(document).on('click', '.search-results div', function() {
      const selectedText = $(this).text();


      $('#search-input').val(selectedText);
      $('#search-results').empty().addClass('hidden'); // Hide results after selection
      var url ="/shomya/app/products?search=" + encodeURIComponent(selectedText);
      // Redirect to the constructed URL
      window.location.href = url;

    });



    // Hide the suggestion box when clicking outside
    $(document).on('click', function(event) {
      if (!$(event.target).closest('#search-form, #search-results').length) {
        $('#search-results').empty().addClass('hidden');
      }
    });
  });

</script>