<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="error.jsp" isELIgnored="false"%> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
  <!-- Global -->
  <c:import url="sharedView/global.html" />
  <!-- Carousel -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css">
  <!-- Local -->
  <link rel="stylesheet" href="./css/home.css">
 <!-- SEO -->
  <title>E-Book Store | World of Books, Novels & Comics</title>
  <meta name="description" content="E-Book Store discover a wonderful selection books, novels from booksellers located around the world. We have every books you need, just come and see ">
</head>

<body>
  <c:import url="sharedView/header.jsp"></c:import>
  <br>
  <div class="adver-demo">
    <div class="owl-carousel owl-theme">
      <div class="adver-item">
        <a href=""><img src="https://cdn0.fahasa.com/media/magentothem/banner7/Mega_sale_LDP_Icanconnect_Mainbanner.jpg" alt=""> </a>
      </div>
      <div class="adver-item">
        <a href=""><img src="https://cdn0.fahasa.com/media/magentothem/banner7/FAHASA-ONT12_840x320.jpg" alt=""> </a>
      </div>
      <div class="adver-item">
        <a href=""><img src="https://cdn0.fahasa.com/media/magentothem/banner7/Mega_sale_LDP_Icanconnect_Mainbanner.jpg" alt=""> </a>
      </div>
      <div class="adver-item">
        <a href=""><img src="https://cdn0.fahasa.com/media/magentothem/banner7/VNPAYT1222_840x320.jpg" alt=""> </a>
      </div>
    </div>
  </div>
  <div class="app_container">
    <div class="grid">
      <div class="grid_row app-content">
        <div class="grid_column-2">
          <nav class="category">
            <h3 class="category-heading"> <i class="category-heading-icon fas fa-list"></i>Content</h3>
            <ul class="category-list">
              <li class="category-item category--active">
                <a href="http://localhost:8080/bookshop/home?action=Roman&command=HOME" class="category-link">Romance</a>
              </li>
              <li class="category-item">
                <a href="http://localhost:8080/bookshop/home?action=Adventure&command=HOME" class="category-link">Adventure & Fantasy</a>
              </li>
              <li class="category-item">
                <a href="http://localhost:8080/bookshop/home?action=Action&command=HOME" class="category-link">Action, Graphic Novels, & Manga</a>
              </li>
              <li class="category-item">
                <a href="http://localhost:8080/bookshop/home?action=Business&command=HOME" class="category-link">Business & Finance</a>
              </li>
            </ul>
          </nav>
        </div>
        <div class="grid_column-10">
          <div class="home-filter">
            <div class="sort-by grid_column-6" >
              <span class="home-label" style="width: 100px;">Sorting by</span>
              <form action="home" method="post">
              <button class="btn-product home-filter-btn">Popular</button>
              <input type="hidden" name="command" value="HOME">
              <input type="hidden" name="action" value="Popular">
              </form>
              <form action="home" method="post" >
              <button class="btn-product btn-primary home-filter-btn">Newest</button>
              <input type="hidden" name="command" value="HOME">
              <input type="hidden" name="action" value="New">
              </form>
              <form action="home" method="post">
              <button class="btn-product home-filter-btn hidden-new">Selling</button>
              <input type="hidden" name="command" value="HOME">
              <input type="hidden" name="action" value="Sell">
              </form>
            </div>
            <div class="select-input grid_column-3">
              <span class="select-price-label">Price</span>
              <i class="fas fa-angle-down select-price-icon"></i>
              <ul class="select-input-list">
                <li class="select-input-item">
                  <form action="">
                    <input type="hidden" name="sort" value="INC">
                    <button class="select-input-link btn-sort">Increase</button>
                  </form>

                </li>
                <li class="select-input-item">
                  <form action="">
                    <input type="hidden" name="sort" value="DES">
                    <button class="select-input-link btn-sort">Decrease</button>
                  </form>
                </li>
              </ul>
            </div>
          </div>
          <div class="home-product">
            <div class="grid_row">
              <c:forEach var="product" items="${list_product}">
                <c:url var="link" value="home">
                  <c:param name="command" value="LOAD" />
                  <c:param name="productID" value="${product.id}" />
                </c:url>
                <div class="grid_column-2-5">
                  <div class="card-trending">
                    <div class="product-item border-card">
                      <a class="card-link-product" href="${link}">
                        <div class="product-item-img" style="background-image: url(${product.pictureUrl});"></div>
                      </a>
                      <p class="trending-item-name">${product.getProductName()}</p>
                      <p class="trending-item-author">${product.nameAuthor}</p>
                      <div class="product-action">
                                                 <span class="product-action-heart product-action-liked">
                          <i class="like-icon far fa-heart"></i>
                          <i class="liked-icon fas fa-heart"></i>
                        </span>
                        <div class="product-action-star">
                          <c:forEach var="i" begin="1" end="${product.getStar()}">
                            <i class="star-gold fas fa-star"></i>
                          </c:forEach>
                          <c:forEach var="i" begin="1" end="${5 - product.getStar()}">
                            <i class="star-gold far fa-star"></i>
                          </c:forEach>
                        </div>
                      </div>
                      <div class="trending-item-price">
                        <span class="price-old mr-up">${product.price}</span>

                        <span class="price-current mr-up">${product.getPriceDiscount()}</span>
                      </div>
                      <form action="${pageContext.request.contextPath}/cart" method="post">
                        <input type="hidden" name="productId" value="${product.id}">
                        <input type="hidden" name="quantity" value="1">
                        <input type="hidden" name="action" value="ADD">
                        <input type="hidden" name="url" value="product?command=LOAD&id=${product.id}"> 
                        <input type="submit" class="btn_add-to-cart" value="Add to cart" />
                      </form>
		                      <div class="product-item-favourite">
                        <i class="fas fa-check"></i> Interesting
                      </div>

                      <c:choose>
                        <c:when test="${product.discount == 0 }">

                        </c:when>
                        <c:otherwise>
                          <div class="product-item-sale">
                            <span class="product-item-label">Discount</span>
                            <span class="product-item-percent">${product.getDiscount()}%</span>
                          </div>
                        </c:otherwise>
                      </c:choose>
                    </div>
                  </div>
                </div>
              </c:forEach>
            </div>
          </div>
           <div class="home-page">
              <div class="filter-control">
                <div class="info-page">
                  <label> ${next + 1} </label> / <label> ${max + 1}</label>
                </div>
                <form action="home" method="post">
                  <input type="hidden" name="action" value="PREV">
                  <input type="hidden" name="sort" value="${sorting}">
                  <input type="hidden" name="next" value="${next}">
                  <button type="submit" class="filter-page-btn input-page"><i class="fas fa-angle-left"></i></button>
                </form>

                <form action="home" method="post">
                  <input type="hidden" name="action" value="NEXT">
                  <input type="hidden" name="next" value="${next}">
                  <input type="hidden" name="sort" value="${sorting}">
                  <button type="submit" class="filter-page-btn input-page"><i class="fas fa-angle-right"></i></button>
                </form>
                
                <form action="home" method="post">
                  <input type="text" class="input-page-2" name="index">
                  <input type="hidden" name="action" value="INDEX">
                  <button type="submit" class="input-go">Go</button>
                </form>
              </div>
            </div>
        </div>

      </div>
    </div>
  </div>
  <div class="grid">
    <div class="grid_row">
      <div class="trending_book">
        <div class="trending_book-header">
          <span class="trending_book-title">Trending Now in eBooks</span>
          <ul class="trending-list">
            <li class="trending_item"><a href="" class="trending_item-link">Discount</a></li>
            <li class="trending_item"><a href="" class="trending_item-link">Newest</a></li>
            <li class="trending_item"><a href="" class="trending_item-link">See All</a></li>
          </ul>
        </div>
        <div class="carousel owl-carousel">
          <c:forEach var="item" items="${trending_book}">
            <c:url var="link" value="home">
              <c:param name="command" value="LOAD" />
              <c:param name="productID" value="${item.id}" />
            </c:url>
            <div class="card-trending">
              <div class="product-item border-card">
                <a href="${link}">
                  <div class="product-item-img" style="background-image: url(${item.pictureUrl});"></div>
                </a>
                <p class="trending-item-name">${item.getProductName()}</p>
                <p class="trending-item-author">${item.getNameAuthor()}</p>
                <div class="product-action">
                        <span class="product-action-heart product-action-liked">
                          <i class="like-icon far fa-heart"></i>
                          <i class="liked-icon fas fa-heart"></i>
                        </span>
                  <div class="product-action-star">
                    <c:forEach var="i" begin="1" end="${product.getStar()}">
                      <i class="star-gold fas fa-star"></i>
                    </c:forEach>
                    <c:forEach var="i" begin="1" end="${5 - product.getStar()}">
                      <i class="star-gold far fa-star"></i>
                    </c:forEach>
                  </div>
                </div>
                <div class="trending-item-price">
                  <span class="price-old mr-up"> ${item.price}</span>
                  <span class="price-current mr-up">${item.getPriceDiscount() }</span>
                </div>
                <form action="${pageContext.request.contextPath}/cart" method="post">
                  <input type="hidden" name="productId" value="${item.id}">
                  <input type="hidden" name="quantity" value="1">
                  <input type="hidden" name="action" value="ADD">
                  <input type="submit" class="btn_add-to-cart" value="Add to cart" />
                </form>
                		                      <div class="product-item-favourite">
                        <i class="fas fa-check"></i> Interesting
                      </div>

                <c:choose>
                  <c:when test="${item.discount == 0 }">

                  </c:when>
                  <c:otherwise>
                    <div class="product-item-sale">
                      <span class="product-item-label">Discount</span>
                      <span class="product-item-percent">${item.getDiscount()}%</span>
                    </div>
                  </c:otherwise>
                </c:choose>
              </div>
            </div>
          </c:forEach>
        </div>
      </div>
    </div>
    <br />
    <br />
    <div class="grid">
      <div class="grid_row">
        <div class="trending_book">
          <div class="trending_book-header">
            <span class="trending_book-title">Popular eBook Pre-orders <br>
              <p class="trending_book-title-after">
                Stay ahead of the curve and get the most anticipated eBooks of the year the moment they come out. </p>
            </span>
            <ul class="trending-list mrtop">
              <li class="trending_item"><a href="" class="trending_item-link">Discount</a></li>
              <li class="trending_item"><a href="" class="trending_item-link">Newest</a></li>

            </ul>
          </div>
          <div class="carousel owl-carousel">
            <c:forEach var="item" items="${po_order}">
              <c:url var="link" value="home">
                <c:param name="command" value="LOAD" />
                <c:param name="productID" value="${item.id}" />
              </c:url>
              <div class="card-trending">
                <div class="product-item border-card">
                  <a href="${link}">
                    <div class="product-item-img" style="background-image: url(${item.pictureUrl});"></div>
                  </a>
                  <p class="trending-item-name">${item.getProductName() }</p>
                  <p class="trending-item-author">${item.getNameAuthor() }</p>
                  <div class="product-action">
                                        <span class="product-action-heart product-action-liked">
                          <i class="like-icon far fa-heart"></i>
                          <i class="liked-icon fas fa-heart"></i>
                        </span>
                        <div class="product-action-star">
                          <c:forEach var="i" begin="1" end="${product.getStar()}">
                            <i class="star-gold fas fa-star"></i>
                          </c:forEach>
                          <c:forEach var="i" begin="1" end="${5 - product.getStar()}">
                            <i class="star-gold far fa-star"></i>
                          </c:forEach>
                        </div>
                  </div>
                  <div class="trending-item-price">
                    <span class="price-old mr-up"> ${item.price}</span>
                    <span class="price-current mr-up">${item.getPriceDiscount()}</span>
                  </div>
                  <form action="${pageContext.request.contextPath}/cart" method="post">
                    <input type="hidden" name="productId" value="${item.id}">
                    <input type="hidden" name="quantity" value="1">
                    <input type="hidden" name="action" value="ADD">
                    <input type="submit" class="btn_add-to-cart" value="Add to cart" />
                  </form>
		                      <div class="product-item-favourite">
                        <i class="fas fa-check"></i> Interesting
                      </div>
                  <c:choose>
                    <c:when test="${item.discount == 0 }">

                    </c:when>
                    <c:otherwise>
                      <div class="product-item-sale">
                        <span class="product-item-label">Discount</span>
                        <span class="product-item-percent">${item.getDiscount()}%</span>
                      </div>
                    </c:otherwise>
                  </c:choose>
                </div>
              </div>
            </c:forEach>
          </div>
        </div>
      </div>
    </div>
    <br>
    <div class="grid">
      <div class="grid_row">
        <div class="trending_book">
          <div class="trending_book-header">
            <span class="trending_book-title">Romance Book<br>
              <p class="trending_book-title-after">
                Any of the romance book titles that you score through this generator are yours to use. </p>
            </span>
            <ul class="trending-list mrtop">
              <li class="trending_item"><a href="" class="trending_item-link">Discount</a></li>
              <li class="trending_item"><a href="" class="trending_item-link">Newest</a></li>

            </ul>
          </div>
          <div class="carousel owl-carousel">
            <c:forEach var="item" items="${romance_book}">
              <c:url var="link" value="home">
                <c:param name="command" value="LOAD" />
                <c:param name="productID" value="${item.id}" />
              </c:url>
              <div class="card-trending">
                <div class="product-item border-card">
                  <a href="${link}">
                    <div class="product-item-img" style="background-image: url(${item.pictureUrl});"></div>
                  </a>
                  <p class="trending-item-name">${item.getProductName() }</p>
                  <p class="trending-item-author">${item.getNameAuthor() }</p>
                  <div class="product-action">
                                            <span class="product-action-heart product-action-liked">
                          <i class="like-icon far fa-heart"></i>
                          <i class="liked-icon fas fa-heart"></i>
                        </span>
                    
                    <div class="product-action-star">
                      <c:forEach var="i" begin="1" end="${product.getStar()}">
                        <i class="star-gold fas fa-star"></i>
                      </c:forEach>
                      <c:forEach var="i" begin="1" end="${5 - product.getStar()}">
                        <i class="star-gold far fa-star"></i>
                      </c:forEach>
                    </div>
                  </div>
                  <div class="trending-item-price">
                    <span class="price-old mr-up"> ${item.price}</span>
                    <span class="price-current mr-up">${item.getPriceDiscount()}</span>
                  </div>
                  <form action="${pageContext.request.contextPath}/cart" method="post">
                    <input type="hidden" name="productId" value="${item.id}">
                    <input type="hidden" name="quantity" value="1">
                    <input type="hidden" name="action" value="ADD">
                    <input type="submit" class="btn_add-to-cart" value="Add to cart" />
                  </form>
		                      <div class="product-item-favourite">
                        <i class="fas fa-check"></i> Interesting
                      </div>
                  <c:choose>
                    <c:when test="${item.discount == 0 }">

                    </c:when>
                    <c:otherwise>
                      <div class="product-item-sale">
                        <span class="product-item-label">Discount</span>
                        <span class="product-item-percent">${item.getDiscount()}%</span>
                      </div>
                    </c:otherwise>
                  </c:choose>
                </div>
              </div>
            </c:forEach>
          </div>
        </div>
      </div>
    </div>
  </div>
  <br>
  <br>
  <div class="grid">
    <span class="trending_book-title">Human Education - Psychology Books<br>
    </span>
    <div class="header-colection">
      <ul class="list-filter list-left-2">
        <li class="link-filter"> <a href="" class="trending_item-link"> Giá tốt </a></li>
        <li class="link-filter list-hidden"> <a href="" class="trending_item-link"> Sắp phát hành </a></li>
        <li class="link-filter"> <a href="" class="trending_item-link"> Sáng tạo </a></li>
      </ul>
      <ul class="list-filter list-right-2">
        <li class="link-filter"> <button onclick="loadMorePrev()" class="btn-goproduct"> <i class="far fa-chevron-left icon-i"></i> </button> </li>
        <li class="link-filter"> <button onclick="loadMoreNext()" class="btn-goproduct"> <i class="far fa-chevron-right icon-i"></i> </button></li>
      </ul>
    </div>
    <div class="grid_row">
      <div class="grid_column-4 hidden-site">
        <img class="main-img" style="width: 100%;" src="https://cdn0.fahasa.com/media/wysiwyg/Thang-10-2021/Tr%C3%AAn%20h%C3%A0nh%20tr%C3%ACnh%20t%E1%BB%B1%20h%E1%BB%8Dc_350x415.jpg" alt="">
      </div>
      <div class="grid_column-8 flex-site">
      <div id="content-load">
        <c:forEach var="product" items="${human_book}">
        <c:url var="link" value="home">
             <c:param name="command" value="LOAD" />
             <c:param name="productID" value="${product.id}" />
         </c:url>
          <div class="grid_column-6 number_product">
            <div class="side-contain" style="display: flex; flex-wrap: nowrap;">
              <div class="img-sidebar">
              <a href="${link}">
                <img  src="${product.pictureUrl}" alt="" class="img-card-sidebar">
               </a>
              </div>
              <div class="sidebar-content">
                <p class="trending-item-name">${product.getProductName() }</p>
                <p class="trending-item-author">${product.getNameAuthor() }</p>
                <div class="product-action">
                        <span class="product-action-heart product-action-liked">
                          <i class="like-icon far fa-heart"></i>
                          <i class="liked-icon fas fa-heart"></i>
                        </span>
					<div class="product-action-star">
                    <c:forEach var="i" begin="1" end="${product.getStar()}">
                      <i class="star-gold fas fa-star"></i>
                    </c:forEach>
                    <c:forEach var="i" begin="1" end="${5 - product.getStar()}">
                      <i class="star-gold far fa-star"></i>
                    </c:forEach>
                  </div>
                </div>
                <div class="trending-item-price">
                  <span class="price-old mr-up"> ${product.price}</span>
                  <span class="price-current mr-up">${product.getPriceDiscount()}</span>
                </div>
              </div>
            </div>
          </div>
        </c:forEach>
		</div>
		<div class="footer-side" style="width: 100%;">
          <center>
          	<input class="index-product" type="hidden" name="amount" value="${amount}">
            <button style="" onclick="loadMore()" type="button" name="button" class="btn-watch">Xem thêm</button>
             </center>
        </div>
       
      </div>
    </div>
  </div>
  <br> <br>
  <div class="grid">
    <span class="trending_book-title">Business - Investment<br>
    </span>
    <div class="header-colection">
      <ul class="list-filter list-left-2">
        <li class="link-filter"> <a href="" class="trending_item-link"> Giá tốt </a></li>
        <li class="link-filter list-hidden"> <a href="" class="trending_item-link"> Sắp phát hành </a></li>
        <li class="link-filter"> <a href="" class="trending_item-link"> Sáng tạo </a></li>
      </ul>
      <ul class="list-filter list-right-2">
        <li class="link-filter"> <button onclick="loadMoreInPrev()" class="btn-goproduct"> <i class="far fa-chevron-left icon-i"></i> </button> </li>
        <li class="link-filter"> <button onclick="loadMoreInNext()" class="btn-goproduct"> <i class="far fa-chevron-right icon-i"></i> </button></li>
      </ul>
    </div>
    <div class="grid_row">
      <div class="grid_column-4 hidden-site">
        <img class="main-img" style="width: 100%;" src="https://cdn0.fahasa.com/media/wysiwyg/Thang-05-2021/350%20x%20415.jpg" alt="">
      </div>
      <div class="grid_column-8 flex-site">
      <div id="content-load-2">
        <c:forEach var="product" items="${bussin_book}">
        <c:url var="link" value="home">
             <c:param name="command" value="LOAD" />
             <c:param name="productID" value="${product.id}" />
         </c:url>
          <div class="grid_column-6 number_product_2">
            <div class="side-contain" style="display: flex; flex-wrap: nowrap;">
              <div class="img-sidebar">
              <a href="${link}">
                <img src="${product.pictureUrl}" alt="" class="img-card-sidebar">
               </a>
              </div>
              <div class="sidebar-content">
                <p class="trending-item-name">${product.getProductName() }</p>
                <p class="trending-item-author">${product.getNameAuthor() }</p>
                <div class="product-action">
                        <span class="product-action-heart product-action-liked">
                          <i class="like-icon far fa-heart"></i>
                          <i class="liked-icon fas fa-heart"></i>
                        </span>
					<div class="product-action-star">
                    <c:forEach var="i" begin="1" end="${product.getStar()}">
                      <i class="star-gold fas fa-star"></i>
                    </c:forEach>
                    <c:forEach var="i" begin="1" end="${5 - product.getStar()}">
                      <i class="star-gold far fa-star"></i>
                    </c:forEach>
                  </div>
                </div>
                <div class="trending-item-price">
                  <span class="price-old mr-up"> ${product.price}</span>
                  <span class="price-current mr-up">${product.getPriceDiscount()}</span>
                </div>
              </div>
            </div>
          </div>
        </c:forEach>
		</div>
		<div class="footer-side" style="width: 100%;">
          <center>
          	<input class="index-product-2" type="hidden" name="amount2" value="${amount}">
            <button style="" onclick="loadInMore()" type="button" name="button" class="btn-watch">Xem thêm</button>
             </center>
        </div>
       
      </div>
    </div>
  </div>
  <br> <br>
  <c:import url="sharedView/footer.jsp"></c:import>
  <c:import url="sharedView/javascript.html"></c:import>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
function loadMoreNext() {
	var index = document.getElementsByClassName("index-product-2")[0].value;
  $.ajax({
    url: "/bookshop/home?command=Add&action=Next&amount2=" + index,
    type: "post", //send it through get method
    success: function(data) {
      var row = document.getElementById("content-load");
      row.innerHTML = data;
      console.log(data);
    },
    error: function(xhr) {

    }
  });
}
function loadMorePrev() {
	var index = document.getElementsByClassName("index-product-2")[0].value;
  $.ajax({
    url: "/bookshop/home?command=Add&action=Prev&amount2=" + index,
    type: "post", //send it through get method
    success: function(data) {
      var row = document.getElementById("content-load");
      row.innerHTML = data;
      console.log(data);
    },
    error: function(xhr) {

    }
  });
}
function loadMoreInNext() {
	var index = document.getElementsByClassName("index-product-2")[0].value;
  $.ajax({
    url: "/bookshop/home?command=Add&action=Next&amount2=" + index,
    type: "post", //send it through get method
    success: function(data) {
      var row = document.getElementById("content-load-2");
      row.innerHTML = data;
      console.log(data);
    },
    error: function(xhr) {

    }
  });
}
function loadMoreInPrev() {
	var index = document.getElementsByClassName("index-product-2")[0].value;
  $.ajax({
    url: "/bookshop/home?command=Add&action=Prev&amount2=" + index,
    type: "post", //send it through get method
    success: function(data) {
      var row = document.getElementById("content-load-2");
      row.innerHTML = data;
      console.log(data);
    },
    error: function(xhr) {

    }
  });
}
function loadInMore() {
	var index = document.getElementsByClassName("index-product-2")[0].value;
  $.ajax({
    url: "/bookshop/home?command=Add&action=Import&amount2=" + index,
    type: "post", //send it through get method
    success: function(data) {
      var row = document.getElementById("content-load-2");
      row.innerHTML += data;
      console.log(data);
    },
    error: function(xhr) {

    }
  });
}

function loadMore() {
	var index = document.getElementsByClassName("index-product-2")[0].value;
  $.ajax({
    url: "/bookshop/home?command=Add&action=Import&amount2=" + index,
    type: "post", //send it through get method
    success: function(data) {
      var row = document.getElementById("content-load");
      row.innerHTML += data;
      console.log(data);
    },
    error: function(xhr) {

    }
  });
}


</script>
</body>

</html>