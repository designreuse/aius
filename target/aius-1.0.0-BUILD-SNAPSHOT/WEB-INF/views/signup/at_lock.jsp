<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>INSPINIA | Main view</title>

<link href="./resources/css/bootstrap.min.css" rel="stylesheet">
<link href="./resources/font-awesome/css/font-awesome.css"
	rel="stylesheet">

<link href="./resources/css/animate.css" rel="stylesheet">
<link href="./resources/css/style.css" rel="stylesheet">

<link href="./resources/css/aius.css" rel="stylesheet">
</head>

<body>

	<div id="wrapper">
		<!-- 옆의 메뉴 추가 -->

		<nav class="navbar-default navbar-static-side" role="navigation">
			<div class="sidebar-collapse">
				<ul class="nav" id="side-menu">
					<li class="nav-header">
						<div class="dropdown profile-element" style="text-align: center">
							<a href="/index"><font size="40px" color="#8C97A8"
								style="margin: 0;">WHO</font></a>
						</div>
						<div class="logo-element">IN+</div>
					</li>
					<li><a href="/"><i class="fa fa-th-large"></i>
							<span class="nav-label">메인으로</span></a></li>

					<li><a href="#"><i class="fa fa-th-large"></i> <span
							class="nav-label">소개</span> <span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="/information">WHO 소개</a></li>
							<li><a href="/contacts">구성원 소개</a></li>
							<li><a href="/register">회원 가입</a></li>
							<li><a href="#">가입 신청</a></li>
						</ul></li>
					<li><a href="#"><i class="fa fa-th-large"></i> <span
							class="nav-label">게시판</span> <span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="board?b=notice&p=1">공지사항</a></li>
							<li><a href="board?b=freeboard&p=1">자유 게시판</a></li>
							<li><a href="gallery">갤러리</a></li>
						</ul></li>
					<li><a href="#"><i class="fa fa-th-large"></i> <span
							class="nav-label">스터디</span> <span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="study?b=html5&p=1">HTML5</a></li>
							<li><a href="study?b=spring&p=1">Spring</a></li>
							<li><a href="study?b=vision&p=1">Computer Vision</a></li>
							<li><a href="study?b=pattern&p=1">패턴 인식</a></li>
						</ul></li>
					<li><a href="#"><i class="fa fa-th-large"></i> <span
							class="nav-label">프로젝트</span> <span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="project?b=tradio&p=1">트레지오</a></li>
							<li><a href="project?b=scsc&p=1">SCSC</a></li>
							<li><a href="project?b=samsung&p=1">Image Transforms</a></li>
						</ul></li>
				</ul>

			</div>
		</nav>

		<div id="page-wrapper" class="gray-bg">
			<%@include file=".././index/common-login.jsp"%>


			<div class="lock-word animated fadeInDown">
				<span class="first-word">LOCKED</span><span>SCREEN</span>
			</div>
			<div class="middle-box text-center lockscreen animated" style="margin-top: -400px;">
				<div>
					<form class="m-t" role="form" onsubmit="return false;">
						<div class="form-group">
							<input type="password" class="form-control" placeholder="******" id="at_pass" name="at_pass"
								required="required">
						</div>
						<button type="button" class="btn btn-primary block full-width" id="at_pass_submit">Unlock</button>
						<input type="hidden" name="n" data-a="${n}">
						<input type="hidden" name="b" data-a="${b}">
						<input type="hidden" name="p" data-a="${p}">
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- Mainly scripts -->
	<script src="./resources/js/jquery.md5.js"></script>
	<script src="./resources/js/jquery-2.1.1.js"></script>
	<script src="./resources/js/bootstrap.min.js"></script>
	<script src="./resources/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script
		src="./resources/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

	<!-- Custom and plugin javascript -->
	<script src="./resources/js/inspinia.js"></script>
	<script src="./resources/js/aius.js"></script>
	<script src="./resources/js/plugins/pace/pace.min.js"></script>
</body>

</html>

