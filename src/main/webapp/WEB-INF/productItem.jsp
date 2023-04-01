<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" errorPage="error.jsp" isELIgnored="false"%> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <!-- Global -->
    <c:import url="sharedView/global.html" />
    <!-- Jquery validate -->
    <script
      src="https://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"
      type="text/javascript"
    ></script>
    <!-- Local -->
    <link rel="stylesheet" href="./css/productItem.css" />
    <title>${product.getProductName()}</title>
  </head>
  <body>
    <c:import url="sharedView/header.jsp"></c:import>
    <br />

    <main class="product-container">
      <section class="product">
        <div class="product-view">
          <figure class="product-image-wrapper">
            <img
              class="product-image"
              src="${product.pictureUrl}"
              alt="${product.getProductName()}"
            />
          </figure>

          <div class="button-wrapper">
            <div class="product-btn add-to-cart">
              <form action="cart">
                <input type="hidden" name="productId" value="${product.id}" />
                <input type="hidden" name="quantity" value="1" />
                <input type="hidden" name="action" value="ADD" />
                <button type="submit" class="btn-action">
                  <span class="add-to-card-span">Thêm vào giỏ hàng</span>
                </button>
              </form>
            </div>
            <div class="product-btn buy-now">
              <form action="confirm" method="post">
                <input type="hidden" name="action" value="BUYNOW" />
                <input type="hidden" name="productId" value="${product.id}" />
                <input type="hidden" name="quantity" value="1" />
                <button type="submit" class="btn-action">
                  <span class="buy-now-span">Mua ngay</span>
                </button>
                <input
                  type="hidden"
                  name="price"
                  value="${product.getPriceDiscount()}"
                />
              </form>
            </div>
          </div>
        </div>
        <div class="product-info">
          <h1 class="product-name">${product.getProductName()}</h1>
          <div class="product-supplier-author">
            <div class="product-author-row">
              <div class="product-row-content">
                Nhà cung cấp:
                <span style="color: #2489f4">${product.supplier}</span>
              </div>
              <div class="product-row-content">
                Tác giả: <span>${product.nameAuthor}</span>
              </div>
            </div>
            <div class="product-author-row">
              <div class="product-row-content">
                Nhà xuất bản: <span>${product.NXB}</span>
              </div>
            </div>
          </div>
          <div class="product-rate">
            <div class="product-stars">
              <c:forEach var="i" begin="1" end="${product.getStar()}">
                <i class="fas fa-star"></i>
              </c:forEach>
              <c:forEach var="i" begin="1" end="${5 - product.getStar()}">
                <i class="far fa-star"></i>
              </c:forEach>
              <span>${product.getAverageStar()}</span>
            </div>
            <span>(${reviews.size()} <span>đánh giá</span>)</span>
            <div class="product-addon">


            </div>
          </div>
          <div class="product-price">
            <span class="price-old"> ${product.price} đ</span>&nbsp; &nbsp;
            ${product.getPriceDiscount()} đ
          </div>
          <div class="product-quantity-wrapper">
            <label>Số lượng:</label>
            <div class="product-quantity">
              1
            </div>
          </div>
          <c:if test="${isUserOwnFile == true}">
            <div class="download-wrapper">
              <a href="${product.getFile().getUrl()}">
                <div class="product-btn download">Tải về</div>
              </a>
            </div>
          </c:if>
        </div>
      </section>
      <section class="product-details">
        <div class="product-title">Thông tin sản phẩm</div>
        <div class="product-details-contents">
          <div class="product-details-content">
            <div class="product-content-label">Mã hàng</div>
            <div class="product-content-detail">${product.getSku()}</div>
          </div>
          <div class="product-details-content">
            <div class="product-content-label">Tên Nhà Cung Cấp</div>
            <div class="product-content-detail">${product.supplier}</div>
          </div>
          <div class="product-details-content">
            <div class="product-content-label">Tác giả</div>
            <div class="product-content-detail">${product.nameAuthor}</div>
          </div>
          <div class="product-details-content">
            <div class="product-content-label">NXB</div>
            <div class="product-content-detail">${product.NXB}</div>
          </div>
          <div class="product-details-content">
            <div class="product-content-label">Genres</div>
            <div class="product-content-detail">${product.typeBook}</div>
          </div>
          <div class="product-details-description">${product.description}</div>
        </div>
      </section>
      <section class="product-rating-container">
        <div class="product-title">Đánh giá sản phẩm</div>
        <section class="product-rating">
          <div class="rating-view">
            <div class="user-rating">
              ${product.getAverageStar()}<span class="rating">/5</span>
            </div>
            <div class="rating-star">
              <c:forEach var="i" begin="1" end="${product.getStar()}">
                <i class="fas fa-star"></i>
              </c:forEach>
              <c:forEach var="i" begin="1" end="${5 - product.getStar()}">
                <i class="far fa-star"></i>
              </c:forEach>
            </div>
            <span class="number-rating"
              >(${reviews.size()} <span>đánh giá</span>)</span
            >
          </div>
          <div class="review-rating-container">
            <div class="review-rating">
              <div class="rating-star">
                <c:forEach var="i" begin="1" end="5">
                  <i class="fas fa-star"></i>
                </c:forEach>
              </div>
              <span>${product.getNStar(5)}%</span>
            </div>
            <div class="review-rating">
              <div class="rating-star">
                <c:forEach var="i" begin="1" end="4">
                  <i class="fas fa-star"></i>
                </c:forEach>
              </div>
              <span>${product.getNStar(4)}%</span>
            </div>
            <div class="review-rating">
              <div class="rating-star">
                <c:forEach var="i" begin="1" end="3">
                  <i class="fas fa-star"></i>
                </c:forEach>
              </div>
              <span>${product.getNStar(3)}%</span>
            </div>
            <div class="review-rating">
              <div class="rating-star">
                <c:forEach var="i" begin="1" end="2">
                  <i class="fas fa-star"></i>
                </c:forEach>
              </div>
              <span>${product.getNStar(2)}%</span>
            </div>
            <div class="review-rating">
              <div class="rating-star">
                <i class="fas fa-star"></i>
              </div>
              <span>${product.getNStar(1)}%</span>
            </div>
          </div>
          <c:choose>
            <c:when test="${sessionScope.userId == null}">
              <div class="noti-non-user">
                Chỉ có thành viên mới có thể viết nhận xét.Vui lòng&nbsp;
                <form action="login" method="get">
                  <input
                    type="hidden"
                    name="url"
                    value="product?command=LOAD&id=${product.getId()}"
                  />
                  <input type="hidden" name="redirect" value="product" />
                  <button class="btn-action" style="color: var(--blue-color)">
                    đăng nhập&nbsp;
                  </button>
                </form>
                hoặc
                <form action="register" method="get">
                  <input
                    type="hidden"
                    name="url"
                    value="product?command=LOAD&id=${product.getId()}"
                  />
                  <input type="hidden" name="redirect" value="product" />
                  <button class="btn-action" style="color: var(--bs-red)">
                    &nbsp;đăng ký
                  </button>
                </form>
              </div>
            </c:when>
            <c:otherwise>
              <div class="review-form-container">
                <form
                  class="rating-form"
                  action="review?action=CREATE&productId=${product.getId()}"
                  method="POST"
                >
                  <span class="fas fa-times fa-2x exit-form"></span>
                  <h2 class="form-title">Viết đánh giá sản phẩm</h2>
                  <div class="review-rating-stars">
                    <input type="radio" name="rating" id="star5" value="5" />
                    <label for="star5" class="full" title="5 Stars"></label>
                    <input type="radio" name="rating" id="star4" value="4" />
                    <label for="star4" class="full" title="4 Stars"></label>
                    <input type="radio" name="rating" id="star3" value="3" />
                    <label for="star3" class="full" title="3 Stars"></label>
                    <input type="radio" name="rating" id="star2" value="2" />
                    <label for="star2" class="full" title="2 Stars"></label>
                    <input type="radio" name="rating" id="star1" value="1" />
                    <label for="star1" class="full" title="1 Stars"></label>
                  </div>
                  <textarea
                    placeholder="Nhập nhận xét của bạn về sản phẩm"
                    name="review-content"
                    class="review-content"
                    cols="5"
                    rows="3"
                    required
                  ></textarea>
                  <div class="button-wrapper">
                    <div class="cancel-form">Hủy</div>
                    <input class="submit" type="submit" value="Gửi nhận xét" />
                  </div>
                </form>
              </div>
              <div class="product-btn review-btn .noti-non-user">
                <span class="fal fa-pen"></span>
                Viết đánh giá
              </div>
            </c:otherwise>
          </c:choose>
        </section>
        <section class="user-comments">
          <div class="overlay">
            <form
              class="comment-delete-confirm"
              action="review?action=DELETE&productId=${product.getId()}&reviewId="
            >
              <div class="fas fa-times fa-2x exit-form"></div>
              <h2>Bạn có chắc là muốn xóa bình luận này chứ</h2>
              <div class="button-wrapper">
                <div class="cancel-comment-delete cancel-form">Hủy</div>
                <input type="text" name="commentId" value="" />
                <input class="submit" type="submit" value="Xác nhận" />
              </div>
            </form>
          </div>
          <c:forEach var="review" items="${reviews}">
            <div
              class="user-comment"
              id="${review.getId()}"
              data-productId="${product.getId()}"
            >
              <section class="user">
                <div class="username">${review.getUserName()}</div>
                <span class="comment-date">${review.getDate()}</span>
              </section>
              <section class="comment">
                <div class="rating-star">
                  <c:forEach var="i" begin="1" end="${review.getStars()}">
                    <i class="fas fa-star"></i>
                  </c:forEach>
                  <c:forEach var="i" begin="1" end="${5 - review.getStars()}">
                    <i class="far fa-star"></i>
                  </c:forEach>
                  <c:if test="${review.getUserName() == sessionScope.username}">
                    <div class="comment-menu">
                      <i class="fas fa-ellipsis-h"></i>
                      <ul class="comment-menu-dropdown">
                        <li class="comment-menu-update">Chỉnh sửa</li>
                        <li class="comment-menu-delete">Xóa</li>
                      </ul>
                    </div>
                  </c:if>
                </div>
                <div class="comment-content">${review.getContent()}</div>
              </section>
            </div>
          </c:forEach>
        </section>
      </section>
    </main>
    <c:import url="sharedView/footer.jsp"></c:import>
    <script>
      $(document).ready(function () {
        //Create review
        const updateForm = $(".review-form-container");
        const formTextarea = $(".review-content");

        const setReviewContent = (data) => formTextarea.val(data);

        $(".review-btn").click(() => {
          setReviewContent("");
          updateForm.show();
        });

        $(".cancel-form, .exit-form").click(() => {
          updateForm.unbind("submit");
          updateForm.submit();
          updateForm.hide();
        });

        $(".rating-form").validate({
          rules: {
            "review-content": "required",
          },
          messages: {
            "review-content": "Enter your review",
          },
        });

        //Update, delete review
        $(".comment-menu").click(function () {
          const el = $(this);
          el.toggleClass("comment-menu-active");
          el.children(".comment-menu-dropdown").toggle();
        });

        const toggleDeleteComment = () => {
          $(".overlay").toggleClass("confirm-delete-active");
        };

        const overlay = $(".overlay");

        $("div.exit-form, .cancel-comment-delete.cancel-form").click(
          // toggleDeleteComment
          () => overlay.hide()
        );

        $(".comment-menu-delete").click(function () {
          overlay.show();

          const deletedReview = $(this).parents(".user-comment");
          const reviewId = deletedReview.attr("id");

          const delReviewForm = $(".comment-delete-confirm");
          delReviewForm.submit((e) => {
            e.preventDefault();
            const url = delReviewForm.attr("action") + reviewId;
            $.post(url, (data, status) => {
              if (status !== "success") {
                console.log("Error from server");
                return;
              }

              // toggleDeleteComment();
              overlay.hide();
              deletedReview.remove();
            });
          });
        });

        //update popup
        $(".comment-menu-update").click(function () {
          const id = $(this).parents(".user-comment").attr("id");
          const updating = $(this).parents(".rating-star").next();
          const productId = $(this)
            .parents(".user-comment")
            .attr("data-productId");
          const url = "/bookshop/home/review?reviewId=" + id;

          $.ajax({
            url,
            type: "get",
            success: function (data) {
              updateForm.show();
              setReviewContent(data);

              updateForm.submit((e) => {
                e.preventDefault();

                const updateUrl =
                  "/bookshop/home/review?action=UPDATE&reviewId=" +
                  id +
                  "&productId=" +
                  productId;
                const content = formTextarea.val();
                const starsRadio = $("input[name=rating]");
                const stars = starsRadio.filter(":checked").val() ?? 1;
                const form = { stars, content };
                $.post(updateUrl, form, (updateData, updateStatus) => {
                  if (updateData === "500") {
                    console.log("Error from server");
                    return;
                  }

                  updateForm.hide();
                  updating.text(updateData);

                  updateForm.unbind("submit");
                  updateForm.submit();
                });
              });
            },
            error: function (xhr) {
              console.error(xhr);
            },
          });
        });
      });
    </script>
    <script src="./js/productItem.js"></script>
  </body>
</html>
