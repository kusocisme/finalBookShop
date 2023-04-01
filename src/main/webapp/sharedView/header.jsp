<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="header-side">
  <header class="header" id="header">
    <div class="grid">
   	<nav class="navbar-header">
        <ul class="navbar_list list-left">
          <li class="navbar-item navbar-has-qr navbar-separate">
            Wellcome to book store
            <!--Header QR-->
            <div class="navbar-qr">
              <img src="./assets/img/qrcode.png" alt="" class="navbar-qr-img" />
              <div class="navbar-qr-apps">
                <a href="" class="navbar_qr-link" style="display: none">
                  <img src="" alt="google play" class="download-img" />
                </a>
                <a href="" class="navbar_qr-link" style="display: none">
                  <img src="" alt="google play" class="download-img" />
                </a>
              </div>
            </div>
          </li>
          <li class="navbar-item">|</li>
          <li class="navbar-item" >
            Connection
            <i class="navbar_list-icon fab fa-facebook"> </i>
            <i class="navbar_list-icon fab fa-github"></i>
          </li>
        </ul>

        <ul class="navbar_list list-right hidden-notify">
          <li class="navbar-item navbar-has-notify ">
            <i class="navbar_list-icon fas fa-bell"></i>
            Notifications
            <div class="navbar-notify">
              <header class="header-notify">
                <h3>New notification</h3>
              </header>
              <ul class="navbar-notify-list">
                <li class="navbar-notify-item">
                  <a href="" class="notify-link">
                    <img
                      src="https://kbimages1-a.akamaihd.net/625a6018-6751-4812-abfa-93c19d3fe0d8/140/215/60/False/lying-ways.jpg"
                      class="notify-img"
                    />
                    <div class="notify-body">
                      <span class="notify-name">News</span>
                      <span class="notify-description"
                        >Wellcome You guys back to my website <3
                      </span>
                    </div>
                  </a>
                </li>
                <li class="navbar-notify-item">
                  <a href="" class="notify-link">
                    <img
                      src="https://kbimages1-a.akamaihd.net/625a6018-6751-4812-abfa-93c19d3fe0d8/140/215/60/False/lying-ways.jpg"
                      class="notify-img"
                    />
                    <div class="notify-body">
                      <span class="notify-name">Product</span>
                      <span class="notify-description"
                        >Thank you for buy our e-book</span
                      >
                    </div>
                  </a>
                </li>
                <li class="navbar-notify-item">
                  <a href="" class="notify-link">
                    <img
                      src="https://kbimages1-a.akamaihd.net/625a6018-6751-4812-abfa-93c19d3fe0d8/140/215/60/False/lying-ways.jpg"
                      class="notify-img"
                    />
                    <div class="notify-body">
                      <span class="notify-name">New book</span>
                      <span class="notify-description"
                        >My shope already update new ebook</span
                      >
                    </div>
                  </a>
                </li>
              </ul>
              <footer class="footer-notify">
                <a href="" class="footer-notify-btn">See All</a>
              </footer>
            </div>
          </li>
          <li class="navbar-item">
            <a href="" class="navbar-item-link"
              ><i class="navbar_list-icon fas fa-question-circle"></i> Help?</a
            >
          </li>

          <c:choose>
            <c:when test="${sessionScope.userId == null}">
                      <li class="navbar-item">
            <a href="register" class="navbar-item-link">Sign up</a>
          </li>
          <li class="navbar-item">|</li>
              <li class="navbar-item">
                <a href="login" class="navbar-item-link">Log in</a>
              </li>
            </c:when>
            <c:otherwise>
              <li class="navbar-item">
                <a href="profile" class="navbar-item-link">
                  <i class="fas fa-user"></i>&nbsp;Profile</a
                >
              </li>
              <li class="navbar-item">
                <a href="logout" class="navbar-item-link">Log out</a>
              </li>
            </c:otherwise>
          </c:choose>
        </ul>
        <ul class="navbar_list list-middle lmh">
        <c:choose> 
        	<c:when test="${sessionScope.userId == null}">
        	<li class="navbar-item">
            <a href="login" class="navbar-item-link">Log In</a>
          </li>
          <li> | </li>
           <li class="navbar-item">
            <a href="register" class="navbar-item-link">Sign Up</a>
          </li>
           </c:when>
           <c:otherwise>
           <li class="navbar-item">
            <a href="profile" class="navbar-item-link"> <i class="fas fa-user"></i>&nbsp; Profile</a>
          </li>
          <li> | </li>
           <li class="navbar-item">
            <a href="logout" class="navbar-item-link">Log Out</a>
          </li>
           </c:otherwise>
		</c:choose>
        </ul>
        <ul class="navbar_list list-middle">
        	<li class="navbar-item">
            <a href="cart" class="navbar-item-link"><i class="navbar_list-icon fal fa-shopping-cart"></i> Cart</a>
          </li>
           <li class="navbar-item">
            <div class="menu-btn">
            	<i class="fas fa-list" style="font-size: 24px"></i>
            </div>
          </li>
        </ul>
      </nav>

	<div class="grid_row">
	<div class="navbar-search">
        <div class="navbar-logo grid_column-3">
          <a href="./home">
            <h1 class="logo-title">
              <span class="logo-header">E-BOOK</span>
            </h1></a
          >
        </div>
        <div class="grid_column-7 search-left">
          <div class="search-product">
            <form style="flex: 1" action="searchProduct" id="search">
              <input
                oninput="onSearchChange(this)"
                name="keyword"
                class="form-control-p"
                type="text"
                placeholder="Search by title, authur, series or release"
                list="keywords"
                required
              />
            </form>
            <button type="submit" form="search" class="btn-p btn-black">
              <i class="fas fa-search"></i>
            </button>
            <div class="product-history">
              <h4 class="history-heading">History Search</h4>
              <ul class="history-list">
                <li class="history-item">
                  <a href="" class="history-link"> Hello Python</a>
                </li>
                <li class="history-item">
                  <a href="" class="history-link">AI && Remaining Thing</a>
                </li>
              </ul>
            </div>
          </div>
        </div>
        <div class="grid_column-2 search-right">
         
          <ul class="search-right-list">
            <li class="search-right-item">
              <a href="#" class="wishlist-link">
                <div class="wishlist-product"></div>
                <input
                  type="submit"
                  name=""
                  class="btn-cart-list"
                  value="Wishlist"
                />
              </a>
            </li>
          </ul> 
          <ul class="search-right-list">
            <li class="search-right-item">
              <form action="cart" method="get">
                <a class="cart-link">
                  <div class="cart-product"></div>
                </a>
                <input
                  type="submit"
                  class="btn-cart-list"
                  name=""
                  value="Go cart"
                />
                <input type="hidden" name="url" value="cart" />
              </form>

              <div class="cart-products">
                <div class="cart-product-list cart-product-list_nocart">
                  <img
                    src="./assets/img/no-cart.png"
                    alt=""
                    class="cart-product-nocart"
                  />
                  <span class="cart-product-list--noproduct"
                    >Do not product in cart</span
                  >
                  <div class="add-tocart"></div>
                  <div class="item-in-cart"></div>

                  <button class="btn-gocart">Go to cart</button>
                </div>
              </div>
            </li>
          </ul>
        </div>
      </div>
	</div>
    </div>
    
  </header>
  <div class="navigation hidden" >
  	<div class="close-btn">
  		<i class="far fa-times-circle"></i>  
  	</div>
  	<div class="support-user" style="display: flex;">
		<ul style="display: flex;">
			<li>
		<a href="" class="navbar-item-link help-icon">
        <i class="navbar_list-icon fas fa-bell"></i> 
            Notification
         </a>
			 </li>
			 <li> 
		<a href="" class="navbar-item-link help-icon">
        <i class="navbar_list-icon fas fa-question-circle"></i> 
            Help?
         </a>
		</li>
		</ul>
  	</div>
	<div class="content-respon" >
            <ul class="category-list">
              <li class="category-item category--active">
                <a href="https://ebooks-shop.herokuapp.com/home?action=Roman&command=HOME" class="category-link">Romance</a>
              </li>
              <li class="category-item">
                <a href="https://ebooks-shop.herokuapp.com/home?action=Adventure&command=HOME" class="category-link">Adventure & Fantasy</a>
              </li>
              <li class="category-item">
                <a href="https://ebooks-shop.herokuapp.com/home?action=Action&command=HOME" class="category-link">Action, Graphic Novels, & Manga</a>
              </li>
              <li class="category-item">
                <a href="https://ebooks-shop.herokuapp.com/home?action=Business&command=HOME" class="category-link">Business & Finance</a>
              </li>
            </ul>
           <br>
           <p style="color: var(--bs-blue); margin: 0 15px;"> Sorting by</p>
           	<ul class="category-list">
              <li class="category-item">
                <a href="https://ebooks-shop.herokuapp.com/home?action=Popular&command=HOME" class="category-link">Popular</a>
              </li>
              <li class="category-item">
                <a href="https://ebooks-shop.herokuapp.com/home?action=New&command=HOME" class="category-link">Newest</a>
              </li>
              <li class="category-item">
                <a href="https://ebooks-shop.herokuapp.com/home?action=Sell&command=HOME" class="category-link">Best Selling</a>
              </li>
           </ul>
	</div>
  </div>
  
  
  <script>
	  var model = document.querySelector(".navigation");
	  var open_model = document.querySelector(".menu-btn");
	  var close_model = document.querySelector(".close-btn");


	  open_model.addEventListener("click", () => {
		  model.classList.remove("hidden");
	  });
	  
	  close_model.addEventListener('click', () => {
		  model.classList.add("hidden");
	  })

  </script>


