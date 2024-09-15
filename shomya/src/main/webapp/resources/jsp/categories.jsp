<!DOCTYPE html>
<html lang="en">
<%@ page import="iti.jets.persistence.model.Category" %>
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
<!-- Add Start -->
<div class="container-fluid bg-secondary my-5">
    <div class="row justify-content-md-center py-5 px-xl-5">
        <div class="col-md-6 col-12 py-5">
            <div class="text-center mb-2 pb-2">
                <h2 class="section-title px-5 mb-3"><span class="bg-secondary px-2">Add New Category</span></h2>
            </div>
            <form action="/shomya/app/addCategory" method="post" enctype="multipart/form-data">
                <div class="control-group">

                    <input type="text" class="form-control" name="categoryName" id="categoryName" placeholder="Enter Category Name"
                           required="required" data-validation-required-message="Please enter Category Name" />
                    <p class="help-block text-danger"></p>
                </div>

                <!-- Category Image -->
                <div class="control-group">
                    <label for="categoryImage">Category Image</label>
                    <input type="file"  name="categoryImage" id="categoryImage"
                           required="required" data-validation-required-message="Please Upload Category Image" />
                    <p class="help-block text-danger"></p>
                </div>

                <!-- Submit Button -->
                <div>
                    <button class="btn btn-primary py-2 px-4" type="submit">Add</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Add End -->
<%-- <!-- Categories Start -->--%>
    <div class="container-fluid pt-5">
        <div class="row px-xl-5 pb-3">
            <c:if test="${categoryList != null}">
                <c:forEach items="${categoryList}" var="category">
                    <div class="col-lg-4 col-md-6 pb-1">
                        <div class="cat-item d-flex flex-column border mb-4" style="padding: 30px;">
                            <p class="text-right">${category.products.size()} Products</p>
                            <a href="" class="cat-img position-relative overflow-hidden mb-3">
                                <img id="category-image-${category.id}" data-category-id="${category.id}" class="img-fluid fixed-size" src="/shomya/resources/img/default.jpg" alt="Loading...">
                            </a>
                            <h5 class="font-weight-semi-bold m-0">${category.name}</h5>
                            <div class="card-footer d-flex justify-content-between bg-light border">
                                <a href="/shomya/app/deletecategory?catId=${category.id}" class="btn btn-sm text-dark p-0"><i class="fas fa-trash text-danger mr-1"></i>Delete</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </div>
<%--    <!-- Categories End -->--%>

<jsp:include page="/resources/jsp/footer.jsp" />
<jsp:directive.include file="/resources/script.html"/>
<script>
    document.addEventListener('DOMContentLoaded', async () => {
        // Select all image elements that you want to load
        const categoryImages = document.querySelectorAll("[id^='category-image-']");
        console.log(categoryImages.length)
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

</body>
</html>