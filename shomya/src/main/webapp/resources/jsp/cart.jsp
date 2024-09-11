
<html>
<%@ page import="iti.jets.model.Category" %>
<jsp:directive.include file="/resources/head.html"/>
<body>
<%@include file="/resources/jsp/header.jsp"%>


<!-- Page Header Start -->
<div class="container-fluid bg-secondary mb-5">
    <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
        <h1 class="font-weight-semi-bold text-uppercase mb-3">Shopping Cart</h1>
        <div class="d-inline-flex">
            <p class="m-0"><a href="">Home</a></p>
            <p class="m-0 px-2">-</p>
            <p class="m-0">Shopping Cart</p>
        </div>
    </div>
</div>
<!-- Page Header End -->



<!-- Cart Start -->
<div class="container-fluid pt-5">

    <div class="row px-xl-5">
        <div class="col-lg-8 table-responsive mb-5">
            <table class="table table-bordered text-center mb-0">
                <thead class="bg-secondary text-dark">
                <tr>
                    <th>Products</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total</th>
                    <th>Remove</th>
                </tr>
                </thead>
                <tbody class="align-middle">
                <c:if test="${cart.size()>0}">
                    <c:forEach items="${cart}" var="item">
                <tr id="row-${item.product.id}">
                    <td class="align-middle"><img src="/shomya/resources/img/${item.product.image}" alt="image" style="width: 50px;">${item.product.name}</td>
                    <td class="align-middle">$${item.product.price}</td>
                    <td class="align-middle">
                        <div class="input-group  mx-auto" style="width: 100px;">
                            <div class="input-group-btn">
                                <button class="btn btn-sm btn-primary btn-minus" onclick="decreaseQuantity(${item.product.id})">
                                    <i class="fa fa-minus"></i>
                                </button>
                            </div>
                            <input id="${item.product.id}" type="text" class="form-control form-control-sm bg-secondary text-center" value="${item.quantity}" onchange="checkQuantity(${item.product.quantity},${item.product.id},${item.product.price})">
                            <div class="input-group-btn">
                                <button class="btn btn-sm btn-primary btn-plus" onclick="increaseQuantity(${item.product.quantity},${item.product.id})">
                                    <i class="fa fa-plus"></i>
                                </button>
                            </div>
                        </div>
                    </td>
                    <td id="total-${item.product.id}" class="align-middle">$${item.product.price*item.quantity}</td>
                    <td class="align-middle"><button onclick="removeProduct(${item.product.id})" class="btn btn-sm btn-primary"><i class="fa fa-times"></i></button></td>
                </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${cart.size()==0}">
                    <tr>
                        <td colspan="5" class="text-center">Your cart is empty.</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
        <div class="col-lg-4">
<%--            <form class="mb-5" action="">--%>
<%--                <div class="input-group">--%>
<%--                    <input type="text" class="form-control p-4" placeholder="Coupon Code">--%>
<%--                    <div class="input-group-append">--%>
<%--                        <button class="btn btn-primary">Apply Coupon</button>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </form>--%>
            <div class="card border-secondary mb-5">
                <div class="card-header bg-secondary border-0">
                    <h4 class="font-weight-semi-bold m-0">Cart Summary</h4>
                </div>
                <div class="card-body">
                    <div class="d-flex justify-content-between mb-3 pt-1">
                        <h6 class="font-weight-medium">Subtotal</h6>
                        <h6 id="subtotal" class="font-weight-medium">$150</h6>
                    </div>
                    <div class="d-flex justify-content-between">
                        <h6 class="font-weight-medium">Shipping</h6>
                        <h6 id="shipping" class="font-weight-medium">$10</h6>
                    </div>
                </div>
                <div class="card-footer border-secondary bg-transparent">
                    <div class="d-flex justify-content-between mt-2">
                        <h5 class="font-weight-bold">Total</h5>
                        <h5 id="total" class="font-weight-bold">$160</h5>
                    </div>
                    <button class="btn btn-block btn-primary my-3 py-3">Proceed To Checkout</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Cart End -->


<jsp:include page="/resources/jsp/footer.jsp" />
<jsp:directive.include file="/resources/script.html"/>


<script>


    // Decrease quantity function
    function decreaseQuantity(id) {
        const quantityInput = document.getElementById(id.toString());
        let currentValue = parseInt(quantityInput.value);

        if (currentValue > 1) {
            quantityInput.value = currentValue - 1;
            quantityInput.dispatchEvent(new Event("change"))
        }

    }

    // Increase quantity function
    function increaseQuantity(maxQuantity,id) {
        const quantityInput = document.getElementById(id.toString());
        let currentValue = parseInt(quantityInput.value);

        if (currentValue < maxQuantity) {
            quantityInput.value = currentValue + 1;
            quantityInput.dispatchEvent(new Event("change"))
        }
    }

    // Check if the manually entered quantity is valid
    function checkQuantity(maxQuantity,id,price) {
        const quantityInput = document.getElementById(id.toString());
        let currentValue = parseInt(quantityInput.value);

        if (isNaN(currentValue) || currentValue < 1) {
            quantityInput.value = 1; // Set to 1 if input is invalid or less than 1
        } else if (currentValue > maxQuantity) {
            quantityInput.value = maxQuantity; // Set to maxQuantity if input exceeds max
        }
        document.getElementById('total-' + id).innerHTML = '$' + (currentValue * price).toFixed(2);
        addtoCart(id,currentValue)

    }
    function addtoCart(id,quantity) {
        const xhr = new XMLHttpRequest();
        const method = "GET";
        const url = "/shomya/addtocart?productId="+id+"&quantity="+quantity;

        xhr.open(method, url, true);
        xhr.onreadystatechange = () => {
            // In local files, status is 0 upon success in Mozilla Firefox
            if (xhr.readyState === XMLHttpRequest.DONE) {
                const status = xhr.status;
                if (status === 0 || (status >= 200 && status < 400)) {
                    // The request has been completed successfully
                    let result = xhr.responseText;
                    console.log(result);
                    updateCartSummary();
                    // if (result.trim() == "done") {
                    //
                    // } else if(result.trim() == "error"){
                    // }
                } else {
                    // Oh no! There has been an error with the request!
                }
            }
        };

        xhr.send(null);
    }

    function removeProduct(productId) {
        if (confirm("Are you sure you want to remove this product from cart?")) {
            $.ajax({
                url: '/shomya/removeProductfromCart', // URL to servlet
                type: 'POST',
                data: { id: productId },
                success: function(response) {
                    // Assuming response contains the status or success message
                    if (response === 'success') {
                        // Remove the row corresponding to the product ID

                        document.getElementById('row-' + productId).remove();
                        updateCartSummary();
                    } else {
                        alert("Failed to remove the product. Try again.");
                    }
                },
                error: function() {
                    alert("Error occurred while removing the product.");
                }
            });
        }
    }
    function updateCartSummary() {
        $.ajax({
            url: '/shomya/calculateTotal', // URL to the servlet
            type: 'POST',
            success: function(response) {
                // Update the subtotal, shipping, and total in the UI
                const subtotal = response.subtotal.toFixed(2);
                const shipping = response.shipping.toFixed(2);
                const total = response.total.toFixed(2);

                // Update HTML elements with the new values
                document.getElementById('subtotal').innerHTML = '$' + subtotal;
                document.getElementById('shipping').innerHTML = '$' + shipping;
                document.getElementById('total').innerHTML = '$' + total;
            },
            error: function() {
                alert("Error calculating total.");
            }
        });
    }
    $(document).ready(function() {
        updateCartSummary();
    });

</script>
</body>
</html>
