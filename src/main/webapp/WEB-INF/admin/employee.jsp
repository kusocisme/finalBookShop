<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>

  	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Admin</title>

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
        <!-- Content Wrapper -->
                <!-- Sidebar -->
		<c:import url="shareCode/leftHeader.html"></c:import>
        <!-- End of Sidebar -->
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
                            <h6 class="m-0 font-weight-bold text-primary">List of Employment</h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>Name</th>
                                            <th>Position</th>
                                            <th>Office</th>
                                            <th>Age</th>
                                            <th>Start date</th>
                                            <th>Salary</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>Name</th>
                                            <th>Position</th>
                                            <th>Office</th>
                                            <th>Age</th>
                                            <th>Start date</th>
                                            <th>Salary</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                        <tr>
                                            <td>Phan Dat</td>
                                            <td>Staff</td>
                                            <td>Ho Chi minh</td>
                                            <td>20</td>
                                            <td>2022/09/25</td>
                                            <td>$1000</td>
                                        </tr>
                                        <tr>
                                            <td>Tan Phuoc</td>
                                            <td>Accountant</td>
                                            <td>Dong Nai</td>
                                            <td>20</td>
                                            <td>2022/09/25</td>
                                            <td>$1000</td>
                                        </tr>
                                        <tr>
                                            <td>Hoang Duong</td>
                                            <td>Accountant</td>
                                            <td>Binh Duong</td>
                                            <td>21</td>
                                            <td>2022/09/25</td>
                                            <td>$1000</td>
                                        </tr>
                                        <tr>
                                            <td>Thanh Thanh</td>
                                            <td>Staff</td>
                                            <td>Ho Chi Minh</td>
                                            <td>21</td>
                                            <td>2022/09/25</td>
                                            <td>$1000</td>
                                        </tr>

                                    </tbody>
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
	    <c:import url="shareCode/logoutModal.html"></c:import>
    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

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
