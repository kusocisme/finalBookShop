<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" errorPage="error.jsp" isELIgnored="false"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset=UTF-8>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Admin</title>

    <!-- Custom fonts for this template -->
    <link href="https://ngocthien2306.github.io/Admin-Site/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="https://ngocthien2306.github.io/Admin-Site/css/sb-admin-2.min.css" rel="stylesheet">


    <!-- Custom styles for this page -->
    <link href="https://ngocthien2306.github.io/Admin-Site/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

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
                    <h1 class="h3 mb-2 text-gray-800">Tables</h1>


                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">List of Product</h6>
                            <c:url var="addProduct" value="product">
						        <c:param name="command" value="Load"/>
						    </c:url>
                            <a href="${addProduct}" class="float-right">
								<span>
                                <button type="submit" style="btn"> 
									<i class="fas fa-plus"></i> 
								</button>  
								</span>
							</a>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>NXB</th>
                                            <th>SKU</th>
                                            
                                            <th>Author Name</th>
                                            <th>Title</th>
                                            <th>Price</th>
                                            <th>Discount</th>
											<th>Active</th>
                                        </tr>
                                    </thead>
                                    <tbody>
									<c:forEach var="item" items="${list}">
										<c:url var="update" value="product">
						                  <c:param name="command" value="Load" />
						                  <c:param name="id" value="${item.id}" />
						                </c:url>
						                <c:url var="remove" value="product">
						                  <c:param name="command" value="Delete" />
						                  <c:param name="id" value="${item.id}" />
						                </c:url>
										<tr>
                                            <th>${item.NXB}</th>
                                            <th>${item.getSku()}</th>
                                            
                                            <th>${item.nameAuthor}</th>
                                            <th> <span
												style="display: block;
												width: 320px;
												overflow: hidden;
												white-space: nowrap;
												text-overflow: ellipsis;">
												${item.getProductName()}
												</span>
											</th>
                                            <th>${item.price}</th>
                                            <th>${item.discount}%</th>
                                            <th>
                                            	<a href="${update}">
													<span><button type="submit"
													style="border: none; color: #2196f3; background: transparent;"> 
														<i class="fas fa-edit"></i> 
													</button>  
													</span>
												</a>
												<a href="${remove}">
													<span><button type="submit"
													style="border: none; color: #E74C3C; background: transparent;"> 
														<i class="fas fa-trash-alt"></i> 
													</button>  
													</span>                                 	
												</a>
                                            </th>
                                        </tr>
									</c:forEach>
									</tbody>
                                    <tfoot>
                                        <tr>
                                            <th>NXB</th>
                                            <th>SKU</th>
                                           
                                            <th>Author Name</th>
											<th>Title</th>
                                            <th>Price</th>
                                             <th>Discount</th>
											<th>Active</th>
                                        </tr>
                                    </tfoot>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->
        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Logout Modal-->
	<c:import url="shareCode/logoutModal.html"></c:import>

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Bootstrap core JavaScript-->
    <script src="https://ngocthien2306.github.io/Admin-Site/vendor/jquery/jquery.min.js"></script>
    <script src="https://ngocthien2306.github.io/Admin-Site/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="https://ngocthien2306.github.io/Admin-Site/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="https://ngocthien2306.github.io/Admin-Site/js/sb-admin-2.min.js"></script>
    <!-- Page level plugins -->
	<script src="https://ngocthien2306.github.io/Admin-Site/vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="https://ngocthien2306.github.io/Admin-Site/vendor/datatables/dataTables.bootstrap4.min.js"></script>
	
    <!-- Page level custom scripts -->
    <script src="https://ngocthien2306.github.io/Admin-Site/js/demo/datatables-demo.js"></script>

</body>

</html>