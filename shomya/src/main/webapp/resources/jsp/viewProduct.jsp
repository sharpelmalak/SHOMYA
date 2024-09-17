
<%@ page import="iti.jets.persistence.model.Product" %>
<%@ page import="iti.jets.persistence.model.Category" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<jsp:directive.include file="/resources/head.html"/>
<body>

<%@ include file="/resources/jsp/header.jsp" %>

<!-- Shop Detail Start -->
<div class="container-fluid py-5">
    <div id="alert-container"></div>
    <div class="row px-xl-5">
        <div class="col-lg-5 pb-5">
            <div id="product-carousel" class="carousel slide" data-ride="carousel">
                <div class="carousel-inner border">
                    <div class="carousel-item active">
                        <img class="w-100 h-100" src="/shomya/app/productImage?productId=${product.id}" alt="Image">
                    </div>
                </div>

            </div>
        </div>

        <div class="col-lg-7 pb-5">
            <h3 class="font-weight-semi-bold">${product.name}</h3>

            <h3 class="font-weight-semi-bold mb-4">$${product.price}</h3>
            <p class="mb-4">${product.description}</p>
            <c:if test="${product.quantity>0}">
            <div class="d-flex align-items-center mb-4 pt-2">
                <div class="input-group mr-3" style="width: 130px;">
                    <div class="input-group-btn">
                        <button class="btn btn-primary btn-minus" onclick="decreaseQuantity()">
                            <i class="fa fa-minus"></i>
                        </button>
                    </div>
                    <input id="customerQuantity" type="text" class="form-control bg-secondary text-center" value="1" onchange="checkQuantity(${product.quantity})">
                    <div class="input-group-btn">
                        <button class="btn btn-primary btn-plus" onclick="increaseQuantity(${product.quantity})">
                            <i class="fa fa-plus"></i>
                        </button>
                    </div>
                </div>
                <button type="button" class="btn btn-primary px-3" onclick="addtoCart(${product.id},getQauntity())"><i class="fa fa-shopping-cart mr-1" ></i> Add To Cart</button>
            </div>
            </c:if>
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
                        <a href="/shomya/app/viewproduct?productId=${pr.id}">
                            <img id="product-image-${pr.id}" data-product-id="${pr.id}" class="img-fluid fixed-size" src="/shomya/resources/img/default.jpg" alt="Loading...">

                        </a>
                    </div>
                    <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                        <h6 class="text-truncate mb-3">${pr.name}</h6>
                        <div class="d-flex justify-content-center">
                            <h6>$${pr.price} <del> $<fmt:formatNumber value="${(pr.price+(pr.price*0.20))}" type="number" minFractionDigits="2" maxFractionDigits="2" /></del>></h6>
                        </div>
                    </div>
                    <div class="card-footer d-flex justify-content-between bg-light border">
                        <a href="/shomya/app/viewproduct?productId=${pr.id}" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>View Detail</a>
                        <button type="button" class="btn btn-sm text-dark p-0" onclick="addtoCart(${pr.id},1)"><i class="fas fa-shopping-cart text-primary mr-1"></i>Add To Cart</button>
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
    function getQauntity()
    {
        return document.getElementById("customerQuantity").value
    }

    // Decrease quantity function
    function decreaseQuantity() {
        const quantityInput = document.getElementById('customerQuantity');
        let currentValue = parseInt(quantityInput.value);

        if (currentValue > 1) {
            quantityInput.value = currentValue - 1;
        }
    }

    // Increase quantity function
    function increaseQuantity(maxQuantity) {
        const quantityInput = document.getElementById('customerQuantity');
        let currentValue = parseInt(quantityInput.value);

        if (currentValue < maxQuantity) {
            quantityInput.value = currentValue + 1;
        }
    }

    // Check if the manually entered quantity is valid
    function checkQuantity(maxQuantity) {
        const quantityInput = document.getElementById('customerQuantity');
        let currentValue = parseInt(quantityInput.value);

        if (isNaN(currentValue) || currentValue < 1) {
            quantityInput.value = 1; // Set to 1 if input is invalid or less than 1
        } else if (currentValue > maxQuantity) {
            quantityInput.value = maxQuantity; // Set to maxQuantity if input exceeds max
        }
    }

        function addtoCart(id,quantity) {
            console.log("add to cart called");
        const xhr = new XMLHttpRequest();
        const method = "GET";
        const url = "/shomya/app/addtocart?productId="+id+"&quantity="+quantity;
        console.log("add to cart called");
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
                    else if(result.trim() == "view"){
                        $('#alert-container').html(`
                       <div class="alert alert-success" role="alert">
                Cart Updated <a href="/shomya/app/viewcart" class="alert-link">View Cart</a>
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
