<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Who will change our world?</title>

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
							<a href="/index"><font size="40px" color="#8C97A8" style="margin: 0;">WHO</font></a>
						</div>
						<div class="logo-element"><a href="/index"><font color="#8C97A8"
								style="margin: 0; font-size: 15px;">WHO</font></a></div>
					</li>
					<li><a href="/"><i class="fa fa-th-large"></i> <span
							class="nav-label">메인으로</span></a></li>

					<li class="active"><a href="#"><i class="fa fa-th-large"></i> <span
							class="nav-label">소개</span> <span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="/information">WHO 소개</a></li>
							<li><a href="/contacts">구성원 소개</a></li>
							<li><a href="/register">회원 가입</a></li>
							<li><a href="#">동아리 가입 신청</a></li>
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
							<li><a href="study?b=web&p=1">웹 프로그래밍</a></li>
							<li><a href="study?b=algorithm&p=1">알고리즘</a></li>
							<li><a href="study?b=vision&p=1">컴퓨터 비전</a></li>
							<li><a href="study?b=pattern&p=1">패턴 인식</a></li>
						</ul></li>
					<li><a href="#"><i class="fa fa-th-large"></i> <span
							class="nav-label">프로젝트</span> <span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="project?b=tradio&p=1">트레지오</a></li>
							<li><a href="project?b=scsc&p=1">SCSC</a></li>
						</ul></li>
				</ul>

			</div>
		</nav>

		<div id="page-wrapper" class="gray-bg">
			<%@include file=".././index/common-login.jsp"%>
			<!-- 
				<div class="row">
					<div class="col-lg-12">
						<img alt="image" src="./resources/image/background.jpg"
							class="col-lg-12 img-inintialize ">
					</div>
				</div>
				 -->

			<div class="wrapper wrapper-content animated">
				<div class="row">
					<c:forEach var="i" begin="0" end="10" step="1">
						<div class="col-lg-4">
							<div class="contact-box">
								<a href="profile.html">
									<div class="col-sm-4">
										<div class="text-center">
											<img alt="image" class="img-circle m-t-xs img-responsive"
												src="./resources/img/a2.jpg">
											<div class="m-t-xs font-bold">Graphics designer</div>
										</div>
									</div>
									<div class="col-sm-8">
										<h3>
											<strong>John Smith</strong>
										</h3>
										<p>
											<i class="fa fa-map-marker"></i> Riviera State 32/106
										</p>
										<address>
											<strong>Twitter, Inc.</strong><br> 795 Folsom Ave, Suite
											600<br> San Francisco, CA 94107<br> <abbr
												title="Phone">P:</abbr> (123) 456-7890
										</address>
									</div>
									<div class="clearfix"></div>
								</a>
							</div>
						</div>
					</c:forEach>

				</div>
			</div>
		</div>
	</div>
	</div>

	<!-- Mainly scripts -->
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
