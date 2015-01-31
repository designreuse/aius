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
							<a href="/index"><font size="40px" color="#8C97A8"
								style="margin: 0;">WHO</font></a>
						</div>
						<div class="logo-element"><a href="/index"><font color="#8C97A8"
								style="margin: 0; font-size: 15px;">WHO</font></a></div>
					</li>
					<li class="active"><a href="/"><i class="fa fa-th-large"></i>
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
			<%@include file="./index/common-login.jsp"%>
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
					<div class="col-lg-12">
						<div class="ibox float-e-margins" style="margin-bottom: 10px;">
							<div class="ibox-title">
								공지 사항 - ${notice.at_title} <a href="javascript:void(0);"
									onclick="b_hit_up('${notice.at_id}', 'notice', '1')"><button
										type="button" class="btn btn-default btn-xs btn-right">글
										보기</button></a>

							</div>
							<div class="ibox-content"
								style="padding-top: 10px; padding-bottom: 3px;min-height: 42px;">

								<p>${notice.at_content}</p>
							</div>
							<div class="ibox-content"
								style="padding-top: 10px; padding-bottom: 9px;">

								<img src="./resources/btn-image/user.png" class="btn-image-aius"
									style="margin-left: 0px;">${notice.at_writer} <img
									src="./resources/btn-image/view.png" class="btn-image-aius">${notice.at_hit}
								<img src="./resources/btn-image/recommend.png"
									class="btn-image-aius">${notice.at_recommend_num} <img
									src="./resources/btn-image/time.png" class="btn-image-aius">

								<c:if test="${today ne article.at_date}">
										${notice.at_date}
									</c:if>
								<c:if test="${today eq article.at_date}">
										${notice.at_time}
									</c:if>
							</div>
						</div>
					</div>
				</div>


				<div class="row" style="margin-top: 20px;">
					<div class="col-lg-12">
						<div class="ibox float-e-margins" style="margin-bottom: 10px;">
							<div class="ibox-title">사진첩</div>
							<div class="ibox-content"
								style="padding-top: 10px; padding-bottom: 3px;">
								<a class="" href="./resources/img/p1.jpg" title="Picture 1">
									<img alt="image" class="ibox-image-aius"
									src="./resources/img/p1.jpg" />
								</a> <a class="" href="./resources/img/p2.jpg" title="Picture 2">
									<img alt="image" class="ibox-image-aius"
									src="./resources/img/p2.jpg" />
								</a> <a class="" href="./resources/img/p3.jpg" title="Picture 3">
									<img alt="image" class="ibox-image-aius"
									src="./resources/img/p3.jpg" />
								</a> <a class="" href="./resources/img/p4.jpg" title="Picture 4">
									<img alt="image" class="ibox-image-aius"
									src="./resources/img/p4.jpg" />
								</a>
							</div>

						</div>
					</div>
				</div>

				<!-- 
					<div class="col-lg-12">
						<div class="panel panel-info">
							<div class="panel-heading">
								자유 게시판 <a href="board?b=freeboard&p=1"><button type="button"
										class="btn btn-default btn-xs btn-right">더 보기</button></a>
							</div>
							<div class="panel-body">

								<table class="table">
									<thead>
										<tr>
											<th class="col-md-1">#</th>
											<th class="col-md-7">제목</th>
											<th class="col-md-1">글쓴이</th>
											<th class="col-md-2">날짜</th>
											<th class="col-md-1">조회수</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="i" begin="0" end="10">
											<tr>
												<td>${i}</td>
												<td>Mark</td>
												<td>Otto</td>
												<td>@mdo</td>
												<td>${i}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>


							</div>

						</div>
					</div>
 -->
			
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
