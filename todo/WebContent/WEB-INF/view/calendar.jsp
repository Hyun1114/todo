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
    <title>일정 리스트</title>
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
              <h4 class="mb-2 page-title">${loginMember.memberId}님 반갑습니다!</h4>
              <a class="btn btn-outline btn-primary" href="${pageContext.request.contextPath}/logout">로그아웃</a>
              <a class="btn btn-outline btn-danger" href="${pageContext.request.contextPath}/member/removeMember">회원탈퇴</a>
              <p></p><p></p>
              <p></p><p></p>
              <h1>
					<span><a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/member/calendar?currentYear=${targetYear}&currentMonth=${targetMonth}&option=pre">이전</a></span>
					${targetYear}년 ${targetMonth}월
					<span><a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/member/calendar?currentYear=${targetYear}&currentMonth=${targetMonth}&option=next">다음</a></span>
				</h1>
				<div>
					이달의 일정 : ${todoList.size()}
				</div>
              <div class="row my-4">
              <!-- 달력 + todo -->
                <!-- Small table -->
                <div class="col-md-12">
                  <div class="card shadow">
                    <div class="card-body">
                      <!-- table -->
                      <table class="table datatables" id="dataTable-1">
                      	<tr>
							<td>일</td><td>월</td><td>화</td><td>수</td><td>목</td><td>금</td><td>토</td>
						</tr>
                      	<tr>
                        	<c:forEach var="i" begin="1" end="${startBlank+endDay+endBlank}" step="1">
                        		<c:if test="${i-startBlank >= 1 && i-startBlank<=endDay}">
									<td style="height:120px">
										<a style="font-size:15px" href="${pageContext.request.contextPath}/member/todoList?y=${targetYear}&m=${targetMonth}&d=${i-startBlank}">${i-startBlank}</a>
										<div>
										<c:forEach var="todo" items="${todoList}">
											<c:if test="${(i-startBlank) == todo.todoDate.substring(8)}">
												<div>${todo.todoContent}</div>
											</c:if>
										</c:forEach>
										</div>
									</td>
	                          </c:if>
		                        <c:if test="${i-startBlank < 1}">
									<td style="height:120px; font-size:15px">${preEndDay+(i-startBlank)}</td>	
								</c:if>
								<c:if test="${i-startBlank > endDay}">
									<td style="height:120px; font-size:15px">${i-startBlank-endDay}</td>	
								</c:if>
								<c:if test="${i%7 == 0}">
									<tr></tr>
								</c:if>
                          	</c:forEach>
                          </tr>
                      </table>
                    </div>
                  </div>
                </div> 
              </div>
            </div> 
          </div>
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