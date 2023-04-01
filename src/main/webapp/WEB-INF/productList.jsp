<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" errorPage="error.jsp"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <!-- Global -->
    <c:import url="sharedView/global.html" />


    <!-- CSS-->
    <link rel="stylesheet" href="./css/swiper.min.css" />
    <link rel="stylesheet" href="./css/productListPage.css" />
    <title>Kết quả tìm kiếm của: ${keyword}</title>
  </head>
  <body>
    <c:import url="sharedView/header.jsp"></c:import>
    <main class="search-main">
      <h1 class="search-title">Kết quả tìm kiếm với: ${keyword}</h1>
      <div class="search-container">
        <c:forEach var="product" items="${products}">
          <c:url var="productLink" value="product">
            <c:param name="command" value="LOAD" />
            <c:param name="id" value="${product.id}" />
          </c:url>
          <div class="card">
            <a href="${productLink}" style="color: 	#77CEED;">
              <div class="img">
                <img src="${product.pictureUrl}" />
              </div>
              <div class="content">
                <div class="productName">
                  <h3>${product.getProductName()}</h3>
                </div>
                <div class="price_rating">
                  <span>${product.price}đ</span>
                  <div class="rating">
                    <c:forEach var="i" begin="1" end="${product.getStar()}">
                      <i class="fas fa-star"></i>
                    </c:forEach>
                    <c:forEach var="i" begin="1" end="${5 - product.getStar()}">
                      <i class="far fa-star unchecked"></i>
                    </c:forEach>
                  </div>
                </div>
              </div>
            </a>
          </div>
        </c:forEach>
      </div>
    </main>
    <c:import url="sharedView/footer.jsp"></c:import>
    <script>
      function onSearchChange(searchbar) {
        searchbar.setCustomValidity("");
        const keyword = searchbar.value;
        const titleTemplate = "Kết quả tìm kiếm với: ";

        $(".search-title").text(titleTemplate + keyword);

        if (keyword === "") return;

        $.ajax({
          url: "/searchProduct?action=AJAX",
          type: "get",
          data: {
            keyword,
          },
          success: function (data) {
            $(".search-container").empty().append(data);
          },
          error: function (xhr) {
            console.error(xhr);
          },
        });
      }
    </script>
  </body>
</html>
