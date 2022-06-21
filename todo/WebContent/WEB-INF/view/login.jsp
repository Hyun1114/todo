<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="favicon.ico">
    <title>로그인</title>
    <!-- Simple bar CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/templeate/css/simplebar.css">
    <!-- Fonts CSS -->
    <link href="https://fonts.googleapis.com/css2?family=Overpass:ital,wght@0,100;0,200;0,300;0,400;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
    <!-- Icons CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/templeate/css/feather.css">
    <!-- Date Range Picker CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/templeate/css/daterangepicker.css">
    <!-- App CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/templeate/css/app-light.css" id="lightTheme">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/templeate/css/app-dark.css" id="darkTheme" disabled>
  </head>
  <body class="light ">
    <div class="wrapper vh-100">
      <div class="row align-items-center h-100">
        <form class="col-lg-3 col-md-4 col-10 mx-auto text-center" id="loginForm" method="post" action="${pageContext.request.contextPath}/login">
          <a class="navbar-brand mx-auto mt-2 flex-fill text-center" href="./index.html">
            <svg version="1.1" id="logo" class="navbar-brand-img brand-md" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 120 120" xml:space="preserve">
              <g>
                <polygon class="st0" points="78,105 15,105 24,87 87,87 	" />
                <polygon class="st0" points="96,69 33,69 42,51 105,51 	" />
                <polygon class="st0" points="78,33 15,33 24,15 87,15 	" />
              </g>
            </svg>
          </a>
          <h1 class="h6 mb-3">로그인</h1>
          <div class="form-group">
            <label for="loginMemberId" class="sr-only">아이디</label>
            <input type="text" id="memberId" name="memberId" class="form-control form-control-lg" placeholder="id" required="" autofocus="">
          </div>
          <div class="form-group">
            <label for="loginMemberPw" class="sr-only">Password</label>
            <input type="password" id="memberPw" name="memberPw" class="form-control form-control-lg" placeholder="Password" required="">
          </div>
          <button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
          <br>
          <a class="btn btn-outline-primary btn-block" href="${pageContext.request.contextPath}/AddMember">회원가입</a>
          <p class="mt-5 mb-3 text-muted">© 2021</p>
        </form>
      </div>
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
    <script src="js/apps.js"></script>
    <!-- Global site tag (gtag.js) - Google Analytics -->
    <script async src="https://www.googletagmanager.com/gtag/js?id=UA-56159088-1"></script>
    <script>
		let loginBtn = $('#loginBtn').click(function() {
			if($('#memberId').val() == '') { // id가 공백이면
				alert('아이디를 입력해주세요!');
				return;
			} else if($('#memberPw').val() == '') { // pw가 공백이면
				alert('비밀번호를 입력해주세요!');
			} else {
				// 버튼을 클릭 했을 때
				$('#loginForm').submit();
			}
		});
	</script>
  </body>
</html>