
<html>
<%@ page import="iti.jets.persistence.model.Category" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<jsp:directive.include file="/resources/head.html"/>
<body>
<%@include file="/resources/jsp/header.jsp"%>


<!-- Page Header Start -->
<div class="container-fluid bg-secondary mb-5">
    <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
        <h1 class="font-weight-semi-bold text-uppercase mb-3">Shopping Cart</h1>
        <div class="d-inline-flex">
            <p class="m-0"><a href="/shomya">Home</a></p>
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
                    <th>#</th>
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
                    <td class="align-middle">
                        <img id="product-image-${item.product.id}" data-product-id="${item.product.id}" class="img-fluid" style="width: 50px; height: 50px" src="/shomya/resources/img/default.jpg" alt="Loading...">
                    </td>

                    <td class="align-middle">
                            ${item.product.name}</td>
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
                    <td id="total-${item.product.id}" class="align-middle">$<fmt:formatNumber value="${(item.product.price*item.quantity)}" type="number" minFractionDigits="2" maxFractionDigits="2" /></td>
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
            <div class="card border-secondary mb-5">
                <div class="card-header bg-secondary border-0">
                    <h4 class="font-weight-semi-bold m-0">Cart Summary</h4>
                </div>
                <div class="card-body">
                    <div class="d-flex justify-content-between mb-3 pt-1">
                        <h6 class="font-weight-medium">Subtotal</h6>
                        <h6 id="subtotal" class="font-weight-medium">$0</h6>
                    </div>
                    <div class="d-flex justify-content-between">
                        <h6 class="font-weight-medium">Shipping</h6>
                        <h6 id="shipping" class="font-weight-medium">$0</h6>
                    </div>
                </div>
                <div class="card-footer border-secondary bg-transparent">
                    <div class="d-flex justify-content-between mt-2">
                        <h5 class="font-weight-bold">Total</h5>
                        <h5 id="total" class="font-weight-bold">$0</h5>
                    </div>
                    <button id="checkoutButton" class="btn btn-block btn-primary my-3 py-3">Proceed To Checkout</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Cart End -->
<!-- Button to trigger modal (this will be triggered automatically based on the error) -->
<button id="errorButton" type="button" class="btn btn-danger" style="display:none;">
    Error
</button>

<!-- Error Modal Structure -->

<div class="modal fade" id="errorModal" tabindex="-1" role="dialog" aria-labelledby="errorModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="errorModalLabel">Error</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body" id="errorContent">
                <!-- Error details will be dynamically inserted here -->
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>


<!-- Modal Structure -->
<div class="modal fade" id="billingConfirmationModal" tabindex="-1" role="dialog" aria-labelledby="billingModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="billingModalLabel">Confirm Billing Details</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Please confirm your billing details before proceeding:</p>
                <ul>
                    <li><strong>Billing Address: </strong>${user.address}</li>
                    <li><strong>Payment Method:</strong> Virtually with Credit Limit</li>
                    <li><strong>Your Credit: </strong>$${user.creditLimit}</li>
                    <li><strong>Total Amount: </strong><span id="final-total"></span></li>
                </ul>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                <button type="button" class="btn btn-primary" id="confirmBilling">Confirm and Pay</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="successModal" tabindex="-1" role="dialog" aria-labelledby="successModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="successModalLabel">Order Placed Successfully</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body" id="successContent">
                <p>Your order has been placed successfully! Thank you for shopping with us.</p>
                <p>Order Number: <strong id="orderNumber">#123456</strong></p>
            </div>
            <div class="modal-footer">
                <a href="/shomya">
                <button type="button" class="btn btn-primary" data-dismiss="modal">Continue Shopping</button>
                </a>
                <a id="order" href="" class="btn btn-secondary">View Order Details</a>
            </div>
        </div>
    </div>
</div>


<jsp:include page="/resources/jsp/footer.jsp" />
<jsp:directive.include file="/resources/script.html"/>


<script>

    $(document).ready(function() {
        updateCartSummary();
    });
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
        const url = "/shomya/app/addtocart?productId="+id+"&quantity="+quantity;

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
                url: '/shomya/app/removeProductfromCart', // URL to servlet
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
            url: '/shomya/app/calculateTotal', // URL to the servlet
            type: 'POST',
            success: function(response) {
                console.log("update summary called")
                // Update the subtotal, shipping, and total in the UI
                const subtotal = response.subtotal.toFixed(2);
                const shipping = response.shipping.toFixed(2);
                const total = response.total.toFixed(2);

                // Update HTML elements with the new values
                document.getElementById('subtotal').innerHTML = '$' + subtotal;
                document.getElementById('shipping').innerHTML = '$' + shipping;
                document.getElementById('total').innerHTML = '$' + total;
                document.getElementById('final-total').innerHTML = '$' + total;
            },
            error: function() {
                alert("Error calculating total.");
            }
        });
    }


    function showError(message) {
        document.getElementById("errorContent").innerHTML = message;
        $('#errorModal').modal('show');
    }


    $('#errorModal').on('hidden.bs.modal', function () {
        // Redirect the user to the cart page after acknowledging the error
        window.location.href = '/shomya/app/viewcart';
    });
    document.getElementById("checkoutButton").addEventListener("click", function() {
        $.ajax({
            url: '/shomya/app/checkCartAndCredit',
            method: 'POST',
            success: function(response) {
                if (response.cartChanged) {
                    showError('Some items in your cart have changed due to stock updates.');
                } else if (response.creditLimitInsufficient) {
                    showError('Your credit limit is insufficient to complete the purchase.');
                } else {
                    // If no issues, proceed to the checkout page
                    $('#billingConfirmationModal').modal('show');
                }
            },
            error: function(error) {
                showError('An error occurred while checking your cart or credit limit.');
            }
        });
    });

    document.getElementById("confirmBilling").addEventListener("click", function() {
        $('#billingConfirmationModal').modal('hide');
        $.ajax({
            url: '/shomya/app/checkout',
            method: 'POST',
            success: function(response) {
                console.log(response)
                if (response.isOrder) {
                    var link = document.getElementById('order');
                    link.href = "/shomya/app/vieworder?orderId="+response.orderId;
                    showSuccessModal(response.orderId)
                }
                else {
                    showError('Sorry maybe some items in your cart have changed due to stock updates.');
                }
            },
            error: function(error) {
                showError('An error occurred while placing Order.');
            }
        });
    });
    function showSuccessModal(orderNumber) {
        document.getElementById('orderNumber').textContent = '#' + orderNumber;
        $('#successModal').modal('show');
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
