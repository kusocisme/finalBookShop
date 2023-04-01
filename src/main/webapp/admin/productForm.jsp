<%@ page pageEncoding="UTF-8" errorPage="../error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Admin</title>

    <!-- Custom fonts for this template-->
    <link href="https://ngocthien2306.github.io/Admin-Site/vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
        type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link href="https://ngocthien2306.github.io/Admin-Site/css/sb-admin-2.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/admin/ProductForm.css">
</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <c:import url="shareCode/leftHeader.html"></c:import>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <c:import url="shareCode/headerUser.html"></c:import>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">${FormCommand} Product</h1>
                    </div>

                    <!-- Content Row -->


                    <!-- Content Row -->
                    <div class="row">
                        <div class="col-lg-8 mb-4">
                            <form class="user" action="product" method="post">
                                <input id="command" type="hidden" name="command" value="${FormCommand}">
                                <div class="p-2 card shadow">
                                    <div class="form-group row">
                                        <div class="col-sm-6 mb-3 mb-sm-0">
                                            <label class="">Author</label>
                                            <input type="text" class="form-control" id="exampleInputPassword"
                                                placeholder="Name of author..." name="author"
                                                value="${item.nameAuthor}">
                                        </div>
                                        <div class="col-sm-6">
                                            <label class="">NXB</label>
                                            <input type="text" class="form-control" id="exampleRepeatPassword"
                                                placeholder="Organzation..." name="nxb" value="${item.NXB}">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="">Product name</label>
                                        <input type="text" class="form-control" id="exampleInputEmail"
                                            aria-describedby="emailHelp" placeholder="Title of e-book..."
                                            name="nameItem" value="${item.getProductName()}">
                                    </div>
                                    <div class="form-group">
                                        <label class="">Describe</label>
                                        <textarea name="description" rows="8" cols="40" class="form-control"
                                            placeholder="Detail product">${item.description}</textarea>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-sm-6 mb-3 mb-sm-0">
                                            <label class="">Price</label>
                                            <input type="text" class="form-control" id="exampleInputPassword"
                                                placeholder="VND" name="price" value="${item.price}">
                                        </div>
                                        <input id="productId" type="hidden" name="id" value=${item.id}>
                                        <div class="col-sm-6">
                                            <label class="">Supplier</label>
                                            <input type="text" class="form-control" id="exampleRepeatPassword"
                                                placeholder="Organzation..." name="supplier" value="${item.supplier}">
                                        </div>
                                    </div>
                                     <div class="form-group row">
                                        <div class="col-sm-6 mb-3 mb-sm-0">
                                            <label class="">Discount</label>
                                            <input type="text" class="form-control" id="exampleInputPassword"
                                                placeholder="% Discount" name="discount" value="${item.discount}">
                                        </div>
                                        <div class="col-sm-6">
                                            <label class="">Code</label>
                                            <input type="text" class="form-control" id="exampleRepeatPassword"
                                                placeholder="(A - Z)" name="code" value="${item.codeProduct}">
                                        </div>
                                    </div>
                                    <hr>
                                    <input name="action" type="submit" value="Save Change"
                                        class="btn btn-primary btn-user btn-block" />
                                </div>
                            </form>
                        </div>
                        <div class="col-lg-4 mb-4">
                      		         <c:if test="${FormCommand == 'Update'}">
                        <div class="row">
                            <h1 class="h3 mb-0 text-gray-800">Product File</h1><br>
                            <div class="p-2 card shadow w-100 d-flex flex-row align-items-center">
                                <c:if test="${not empty item.file}">
                                <div class="d-block m-1">
                                    <a class="" href="${item.file.url}" target="blank"><h4>Get file</h4></a>
                                </div>
                                <form class="m-1" action="file" method="post">
                                    <input type="hidden" name="command" value="Delete">
                                    <input type="hidden" name="productId" value="${item.id}">
                                    <input type="hidden" name="fileId" value="${item.file.id}">
                                    <input name="action" type="submit" value="Delete file"
                                        class="btn btn-danger" />
                                </form>                                
                                </c:if>
                                <button class="btn btn-primary m-1 d-block" id="uploadFile">Upload File</button>
                            </div>
                        </div>
                        <div class="row">
                            <h1 class="h3 mb-0 text-gray-800">Product Photos</h1>
                            <div class="p-2 card shadow w-100">
                                <button class="btn btn-primary" id="uploadPhoto">Upload Photo</button>
                                <div id="carouselExampleIndicators" class="carousel slide w-100 h-auto" data-ride="carousel">
                                <div class="carousel-inner">
                                    <c:forEach var="photo" items="${item.photos}">
                                        <c:if test="${photo.main}">
                                        <div class="carousel-item active">
                                        </c:if>
                                        <c:if test="${not photo.main}">
                                        <div class="carousel-item">
                                        </c:if>                                         
                                            <img src="${photo.url}" class="d-block w-100" alt="...">
                                            <div class="carousel-caption d-none d-md-block w-30">
                                                <c:if test="${photo.main}">
                                                    <h5><span class="badge badge-pill badge-primary">Main
                                                            Photo</span></h5>
                                                </c:if>
                                                <c:if test="${not photo.main}">
                                                    <form action="photo" method="post">
                                                        <input type="hidden" name="command" value="SetMain">
                                                        <input type="hidden" name="productId" value=${item.id}>
                                                        <input type="hidden" name="photoId" value=${photo.id}>
                                                        <input name="action" type="submit" value="Set to main"
                                                            class="btn btn-primary btn-block" />
                                                    </form>
                                                    <form action="photo" method="post">
                                                        <input type="hidden" name="command" value="Delete">
                                                        <input type="hidden" name="productId" value=${item.id}>
                                                        <input type="hidden" name="photoId" value=${photo.id}>
                                                        <input name="action" type="submit" value="Delete photo"
                                                            class="btn btn-danger btn-block" />
                                                    </form>
                                                </c:if>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button"
                                    data-slide="prev">
                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span class="sr-only">Previous</span>
                                </a>
                                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button"
                                    data-slide="next">
                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                    <span class="sr-only">Next</span>
                                </a>
                                </div>
                            </div>
                        </div>
                    </c:if>  
                        </div>
                    </div>
           
                </div>
            </div>
        </div>
    </div>

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <c:import url="shareCode/logoutModal.html"></c:import>

    <script src="https://ngocthien2306.github.io/Admin-Site/js/file.js">
    </script>
    <!-- Bootstrap core JavaScript-->
    <script src="https://ngocthien2306.github.io/Admin-Site/vendor/jquery/jquery.min.js">
    </script>
    <script src="https://ngocthien2306.github.io/Admin-Site/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="https://ngocthien2306.github.io/Admin-Site/vendor/jquery-easing/jquery.easing.min.js">
    </script>

    <!-- Custom scripts for all pages-->
    <script src="https://ngocthien2306.github.io/Admin-Site/js/sb-admin-2.min.js">
    </script>

    <!-- cloudinary upload widget -->
    <script src="https://upload-widget.cloudinary.com/global/all.js" type="text/javascript"></script>

    <!-- local upload instantiation -->
    <script src="../js/admin/uploadPhoto.js" type="text/javascript"></script>
    <script src="../js/admin/uploadFile.js" type="text/javascript"></script>
</body>

</html>