<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="favicon.ico">
    <title>일정 수정</title>
    <!-- Simple bar CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/templeate/css/simplebar.css">
    <!-- Fonts CSS -->
    <link href="https://fonts.googleapis.com/css2?family=Overpass:ital,wght@0,100;0,200;0,300;0,400;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
    <!-- Icons CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/templeate/css/feather.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/templeate/css/dataTables.bootstrap4.css">
    <!-- Date Range Picker CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/templeate/css/daterangepicker.css">
    <!-- App CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/templeate/css/app-light.css" id="lightTheme">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/templeate/css/app-dark.css" id="darkTheme" disabled>
  </head>
  <body class="vertical center">
    <div class="wrapper">
      <main role="main" class="main-content">
        <div class="container-fluid">
          <div class="row justify-content-center">
            <div class="col-12">
              <h2 class="mb-2 page-title">${todoDate} 오늘 일정</h2>
              <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/member/calendar">달력</a>
              <div class="row my-4">
                <!-- Small table -->
                <div class="col-md-12">
                  <div class="card shadow">
                    <div class="card-body">
                      <!-- table -->
                      <table class="table datatables" id="dataTable-1">
                        <thead>
                          <tr>
                            <th>날짜</th>
                            <th>내용</th>
                            <th>등록 날짜</th>
                            <th>수정 날짜</th>
                            <th>수정</th>
                            <td>삭제</td>
                          </tr>
                        </thead>
                        <tbody>
                        	<c:forEach var="t" items="${todoList}">
	                          <tr>
	                         	<td>${todoDate}</td>
								<td>${t.todoContent}</td>
								<td>${t.createDate}</td>
								<td>${t.updateDate}</td>
								<td><a class="btn btn-outline-primary btn-block" href="${pageContext.request.contextPath}/member/updateTodo?todoNo=${t.todoNo}&memberId=${loginMember.memberId}&todoDate=${t.todoDate}&todoContent=${t.todoContent}">수정</a></td>
								<td><a class="btn btn-outline-primary btn-block" href="${pageContext.request.contextPath}/member/removeTodo?todoNo=${t.todoNo}&memberId=${loginMember.memberId}&todoDate=${t.todoDate}">삭제</a></td>
	                          </tr>
                          </c:forEach>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div> 
              </div>
            </div> 
          </div>
          <h2 class="mb-2 page-title">일정 수정</h2>
              	<form class="mx-auto text-center" method="post" action="${pageContext.request.contextPath}/member/updateTodo">
		         	<input type="hidden" name="memberId" value="${loginMember.memberId}">
					<input type="hidden" name="todoNo" value="${todoNo}">
					<input type="hidden" name="todoDate" value="${todoDate}">
		           	<div class="form-group">
						<label for="todoContent" class="sr-only">내용</label>
		            	<textarea class="form-control" name="todoContent" id="example-textarea" rows="4">${todoContent}</textarea>
		          	</div>
		          <button class="btn btn-lg btn-primary btn-block" type="submit">수정</button>
		          <br>
		         <button class="btn btn-lg btn-primary btn-block" type="reset">초기화</button>
		          <p class="mt-5 mb-3 text-muted">© 2021</p>
		      </form>
        </div>
      </main>
    </div>
    <script src="js/jquery.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/moment.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/simplebar.min.js"></script>
    <script src='js/daterangepicker.js'></script>
    <script src='js/jquery.stickOnScroll.js'></script>
    <script src="js/tinycolor-min.js"></script>
    <script src="js/config.js"></script>
    <script src='js/jquery.dataTables.min.js'></script>
    <script src='js/dataTables.bootstrap4.min.js'></script>
    <script>
      $('#dataTable-1').DataTable(
      {
        autoWidth: true,
        "lengthMenu": [
          [16, 32, 64, -1],
          [16, 32, 64, "All"]
        ]
      });
    </script>
    <script src="js/apps.js"></script>
    <!-- Global site tag (gtag.js) - Google Analytics -->
    <script async src="https://www.googletagmanager.com/gtag/js?id=UA-56159088-1"></script>
    <script>
      window.dataLayer = window.dataLayer || [];

      function gtag()
      {
        dataLayer.push(arguments);
      }
      gtag('js', new Date());
      gtag('config', 'UA-56159088-1');
    </script>
  </body>
</html>