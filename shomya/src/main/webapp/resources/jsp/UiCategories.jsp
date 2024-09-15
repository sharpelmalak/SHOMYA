<div class="container-fluid pt-5">
  <div class="row px-xl-5 pb-3">
    <!-- Category Circle 1 -->
    <div class="col-lg-2 col-md-4 col-sm-6 pb-1">
      <div class="cat-circle text-center">
        <a
          href=""
          class="cat-img position-relative overflow-hidden d-flex justify-content-center align-items-center"
        >
          <img class="img-fluid" src="/shomya/resources/img/cat-1.jpg" alt="" />
        </a>
        <h5 class="font-weight-semi-bold m-0 mt-2">Men's dresses</h5>
      </div>
    </div>

    <!-- Category Circle 2 -->
    <div class="col-lg-2 col-md-4 col-sm-6 pb-1">
      <div class="cat-circle text-center">
        <a
          href=""
          class="cat-img position-relative overflow-hidden d-flex justify-content-center align-items-center"
        >
          <img class="img-fluid" src="/shomya/resources/img/cat-2.jpg" alt="" />
        </a>
        <h5 class="font-weight-semi-bold m-0 mt-2">Category 2</h5>
      </div>
    </div>

    <!-- Category Circle 3 -->
    <div class="col-lg-2 col-md-4 col-sm-6 pb-1">
      <div class="cat-circle text-center">
        <a
          href=""
          class="cat-img position-relative overflow-hidden d-flex justify-content-center align-items-center"
        >
          <img class="img-fluid" src="/shomya/resources/img/cat-2.jpg" alt="" />
        </a>
        <h5 class="font-weight-semi-bold m-0 mt-2">Category 3</h5>
      </div>
    </div>

    <!-- Category Circle 4 -->
    <div class="col-lg-2 col-md-4 col-sm-6 pb-1">
      <div class="cat-circle text-center">
        <a
          href=""
          class="cat-img position-relative overflow-hidden d-flex justify-content-center align-items-center"
        >
          <img class="img-fluid" src="/shomya/resources/img/cat-2.jpg" alt="" />
        </a>
        <h5 class="font-weight-semi-bold m-0 mt-2">Category 4</h5>
      </div>
    </div>

    <!-- Category Circle 5 -->
    <div class="col-lg-2 col-md-4 col-sm-6 pb-1">
      <div class="cat-circle text-center">
        <a
          href=""
          class="cat-img position-relative overflow-hidden d-flex justify-content-center align-items-center"
        >
          <img class="img-fluid" src="/shomya/resources/img/cat-2.jpg" alt="" />
        </a>
        <h5 class="font-weight-semi-bold m-0 mt-2">Category 5</h5>
      </div>
    </div>

    <!-- Category Circle 6 -->
    <div class="col-lg-2 col-md-4 col-sm-6 pb-1">
      <div class="cat-circle text-center">
        <a
          href=""
          class="cat-img position-relative overflow-hidden d-flex justify-content-center align-items-center"
        >
          <img class="img-fluid" src="/shomya/resources/img/cat-2.jpg" alt="" />
        </a>
        <h5 class="font-weight-semi-bold m-0 mt-2">Category 6</h5>
      </div>
    </div>
  </div>
</div>

<style>
  .cat-item {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    border-radius: 50%;
    overflow: hidden;
    background-color: #f8f9fa; /* Background color for better visibility */
  }

  .cat-img {
    width: 100%;
    height: 0;
    padding-top: 100%; /* Creates a square container for the image */
    position: relative;
  }

  .cat-img img {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    object-fit: cover;
    border-radius: 50%;
  }

  .cat-item h5 {
    margin-top: 10px;
    font-size: 14px;
    text-align: center;
  }

  @media (max-width: 768px) {
    .cat-item {
      width: 100%;
    }
  }
</style>
