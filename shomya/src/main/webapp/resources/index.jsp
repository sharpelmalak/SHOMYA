<!DOCTYPE html>
<html lang="en">
<%@ page import="iti.jets.persistence.model.Category" %>
<jsp:directive.include file="/resources/head.html"/>
<body>
<jsp:directive.include file="/resources/jsp/header.jsp" />

<c:if test="${userRole != EnumHelper.getAdminRole()}">
    <jsp:directive.include file="/resources/jsp/carousel.jsp" />

    <jsp:directive.include file="/resources/jsp/featured.jsp" />

    <jsp:directive.include file="/resources/jsp/offers.jsp" />
</c:if>>


<div class="container-fluid pt-5">
    <div class="row px-xl-5 pb-3">
        <c:if test="${categoryList != null}">
            <c:forEach items="${categoryList}" var="category">
                <div class="col-lg-4 col-md-6 pb-1">
                    <div class="cat-item d-flex flex-column border mb-4" style="padding: 30px;">
                        <p class="text-right">${category.products.size()} Products</p>
                        <a href="/shomya/app/products?categoryId=${category.id}" class="cat-img position-relative overflow-hidden mb-3">
                            <img id="category-image-${category.id}" data-category-id="${category.id}" class="img-fluid fixed-size" src="/shomya/resources/img/default.jpg" alt="Loading...">
                        </a>
                        <h5 class="font-weight-semi-bold m-0">${category.name}</h5>
                        <div class="card-footer d-flex justify-content-between bg-light border">
                            <c:if test="${userRole == EnumHelper.getAdminRole()}">
                                <a href="/shomya/app/updatecategory?catId=${category.id}" class="btn btn-sm text-dark p-0"><i class="fas fa-edit text-primary mr-1"></i>Update</a>
                                <a href="/shomya/app/deletecategory?catId=${category.id}" class="btn btn-sm text-dark p-0"><i class="fas fa-trash text-danger mr-1"></i>Delete</a>

                            </c:if>
                        </div>
                    </div>
                </div>
            </c:forEach>

            <script>
                document.addEventListener('DOMContentLoaded', async () => {
                    // Select all image elements that you want to load
                    const categoryImages = document.querySelectorAll("[id^='category-image-']");
                    async function loadImageSequentially() {
                        // Iterate over each image element
                        console.log("image load")
                        for (const imgElement of categoryImages) {
                            // Extract the category ID from the data attribute (e.g., data-category-id="1")
                            const categoryId = imgElement.getAttribute('data-category-id');
                            console.log("image" + categoryId)
                            try {
                                // Wait for the image to load
                                const imgSrc = await loadImage(categoryId);
                                imgElement.src = imgSrc; // Replace the placeholder with the loaded image
                            } catch (error) {
                                console.error(`Failed to load image for category ID: `+categoryId, error);
                            }
                        }
                    }

                    function loadImage(categoryId) {
                        return new Promise((resolve, reject) => {
                            const img = new Image();
                            img.src = `/shomya/app/categoryImage?categoryId=`+categoryId;

                            img.onload = () => {
                                resolve(img.src);
                            };

                            img.onerror = () => {
                                reject(new Error(`Failed to load image for category ID:`+categoryId));
                            };
                        });
                    }

                    // Start loading images
                    await loadImageSequentially();
                });
            </script>

        </c:if>
    </div>
</div>


<jsp:directive.include file="/resources/jsp/UiSubscribe.jsp" />

<jsp:directive.include file="/resources/jsp/footer.jsp" />

<jsp:directive.include file="/resources/script.html" />
</body>

</html>