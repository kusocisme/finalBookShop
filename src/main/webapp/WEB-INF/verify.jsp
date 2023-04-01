<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="ISO-8859-1" />
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <meta name="description" content="" />
    <meta name="author" content="" />

    <title>Verify</title>

    <!-- Custom fonts for this template-->
    <link
      href="https://ngocthien2306.github.io/Admin-Site/vendor/fontawesome-free/css/all.min.css"
      rel="stylesheet"
      type="text/css"
    />
    <link
      href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
      rel="stylesheet"
    />

    <!-- Custom styles for this template-->
    <link
      href="https://ngocthien2306.github.io/Admin-Site/css/sb-admin-2.min.css"
      rel="stylesheet"
    />
  </head>

  <body class="bg-gradient-primary">
    <div class="container">
      <!-- Outer Row -->
      <div class="row justify-content-center">
        <div class="col-xl-10 col-lg-12 col-md-9">
          <div class="card o-hidden border-0 shadow-lg my-5">
            <div class="card-body p-0">
              <!-- Nested Row within Card Body -->
              <div class="row">
                <div
                  class="col-lg-6 d-none d-lg-block bbg-password-image"
                ></div>
                <div class="col-lg-6">
                  <div class="p-5">
                    <div class="text-center">
                      <h1 class="h4 text-gray-900 mb-2">Verify Email</h1>
                      <p class="mb-4">
                        We get it, stuff happens. Just enter your email address
                        below and we'll send you a link to sign up account!
                      </p>
                    </div>
                    <form action="verify" class="" method="POST">
                      <c:choose>
                        <c:when test = "${type == 'passReset'}">
                        <input type="hidden" name="action" value="VERIFY_resetPass" />
                        </c:when>
                        <c:when test = "${type == 'register'}">
                        <input type="hidden" name="action" value="VERIFY_register" />
                        </c:when>
                      </c:choose>
                      <div class="mb-3">
                        <input
                          id="codeInput"
                          type="text"
                          class="form-control"
                          name="code"
                        />
                      </div>
                      <span style="color: red"
                        ><c:out value="${verifyMessage}"
                      /></span><br>
                      <button type="submit" class="btn btn-primary">
                        Verify
                      </button>
                    </form>
                    <hr />
                    <div class="text-center">
                      <a class="small" href="home">Homepage?</a>
                    </div>
                    <div class="text-center">
                      <a class="small" href="login"
                        >Already have an account? Login!</a
                      >
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <script src="https://ngocthien2306.github.io/Admin-Site/vendor/jquery/jquery.min.js"></script>
    <script src="https://ngocthien2306.github.io/Admin-Site/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="https://ngocthien2306.github.io/Admin-Site/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="https://ngocthien2306.github.io/Admin-Site/js/sb-admin-2.min.js"></script>
  </body>
</html>
