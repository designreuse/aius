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
					<li><a href="/"><i class="fa fa-th-large"></i> <span
							class="nav-label">메인으로</span></a></li>

					<li><a href="#"><i class="fa fa-th-large"></i> <span
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
					<li class="active"><a href="#"><i class="fa fa-th-large"></i> <span
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
			<div class="wrapper wrapper-content animated">
				<c:forEach var="article" items="${article}" varStatus="status">
					<div class="row">
						<c:choose>
							<c:when test="${article.at_re_step == 0}">
								<div class="col-lg-12"
									onclick="s_hit_up('${article.at_id}', '${board}', '${curr_page_num}')"
									style="cursor: pointer;">
							</c:when>
							<c:when test="${article.at_re_step == 1}">

								<div class="col-lg-12"
									onclick="s_hit_up('${article.at_id}', '${board}', '${curr_page_num}')"
									style="margin-left: 20px; cursor: pointer;">
							</c:when>
							<c:when test="${article.at_re_step == 2}">

								<div class="col-lg-12"
									onclick="s_hit_up('${article.at_id}', '${board}', '${curr_page_num}')"
									style="margin-left: 40px; cursor: pointer;">
							</c:when>
							<c:when test="${article.at_re_step == 3}">

								<div class="col-lg-12"
									onclick="s_hit_up('${article.at_id}', '${board}', '${curr_page_num}')"
									style="margin-left: 60px; cursor: pointer;">
							</c:when>
							<c:otherwise>

								<div class="col-lg-12"
									onclick="s_hit_up('${article.at_id}', '${board}', '${curr_page_num}')"
									style="margin-left: 80px; cursor: pointer;">
							</c:otherwise>
						</c:choose>

						<div class="ibox float-e-margins" style="margin-bottom: 10px;">
							<div class="ibox-title">


								<c:if test="${article.at_deleted eq 'N'}">
									<h5>
										<c:if test="${article.at_re_step != 0 }">
											<img src="./resources/btn-image/share-alt.png"
												class="btn-image-aius" style="margin-left: 0px;">
										</c:if>

										<c:if test="${article.is_lock ne 'Y'}">
											#${article.at_id} ${article.at_title}
										</c:if>
										<c:if test="${article.is_lock eq 'Y'}">

											<c:choose>
												<c:when test="${level >= 9}">
											#${article.at_id} ${article.at_title}<img
														src="./resources/btn-image/lock.png"
														class="btn-image-aius" style="margin-left: 7px;">
												</c:when>
												<c:otherwise>
											#비밀글 입니다.<img src="./resources/btn-image/lock.png"
														class="btn-image-aius" style="margin-left: 7px;">
												</c:otherwise>
											</c:choose>
										</c:if>
									</h5>
								</c:if>
								<c:if test="${article.at_deleted eq 'Y'}">
									<h5 style="color: red;">
										<c:if test="${article.at_re_step != 0 }">
											<img src="./resources/btn-image/share-alt.png"
												class="btn-image-aius" style="margin-left: 0px;">
										</c:if>
										<c:if test="${article.is_lock ne 'Y'}">
											#${article.at_id} ${article.at_title}
										</c:if>
										<c:if test="${article.is_lock eq 'Y'}">
											#${article.at_id} ${article.at_title}<img
												src="./resources/btn-image/lock.png" class="btn-image-aius"
												style="margin-left: 7px;">
										</c:if>
									</h5>
								</c:if>
								<div class="ibox-tools">
									<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
									</a>
								</div>
							</div>
							<div class="ibox-content"
								style="padding-top: 10px; padding-bottom: 3px;">
								<c:if test="${article.is_lock ne 'Y'}">
									<p>${article.at_content}</p>
								</c:if>
								<c:if test="${article.is_lock eq 'Y'}">
									<c:choose>
										<c:when test="${level >= 9}">
											<p>${article.at_content}</p>
										</c:when>
										<c:otherwise>
											<p>#비밀글 입니다.</p>
										</c:otherwise>
									</c:choose>
								</c:if>

							</div>
							<div class="ibox-content"
								style="padding-top: 10px; padding-bottom: 9px; min-height: 42px;">

								<img src="./resources/btn-image/user.png" class="btn-image-aius"
									style="margin-left: 0px;">${article.at_writer} <img
									src="./resources/btn-image/view.png" class="btn-image-aius">${article.at_hit}
								<img src="./resources/btn-image/recommend.png"
									class="btn-image-aius">${article.at_recommend_num} <img
									src="./resources/btn-image/time.png" class="btn-image-aius">
								<c:if test="${today ne article.at_date}">
										${article.at_date}
									</c:if>
								<c:if test="${today eq article.at_date}">
										${article.at_time}
									</c:if>
							</div>
						</div>
					</div>
			</div>
			</c:forEach>
			<div class="col-lg-12" style="text-align: center;">
				<div class="btn-group">

					<c:if test="${curr_page_num ne '1' }">
						<button type="button" class="btn btn-white btn-height-34"
							onclick="btn_click_aius('${board}', '${curr_page_num - 1}')">
							<i class="fa fa-chevron-left"></i>
						</button>
					</c:if>

					<c:forEach var="i" begin="0" end="10" step="1">
						<c:if test="${curr_page_num -5 + i > 0}">
							<c:if test="${curr_page_num -5 + i <= page_num }">
								<c:if test="${curr_page_num -5 + i eq curr_page_num}">
									<button
										class="btn btn-white active btn-height-34 btn-navigate-board"
										onclick="btn_click_aius('${board}', '${curr_page_num -5 + i}')">
										${curr_page_num -5 + i}</button>

								</c:if>
								<c:if test="${curr_page_num -5 + i ne curr_page_num}">
									<button class="btn btn-white btn-height-34 btn-navigate-board"
										onclick="btn_click_aius('${board}', '${curr_page_num -5 + i}')">${curr_page_num -5 + i}</button>
								</c:if>
							</c:if>
						</c:if>
					</c:forEach>

					<c:if test="${curr_page_num < page_num }">
						<button type="button" class="btn btn-white btn-height-34"
							onclick="btn_click_aius('${board}', '${curr_page_num + 1}')">
							<i class="fa fa-chevron-right"></i>
						</button>
					</c:if>

					<c:if test="${ id ne null }">
						<c:if test="${level >= '6' }">
						<a href="sWrite?b=${board}&p=${curr_page_num}"><button
								type="button" class="btn btn-white btn-height-34">글쓰기</button></a>
						</c:if>
					</c:if>
				</div>
			</div>
		</div>
	</div>

	<!-- Mainly scripts -->
	<script src="./resources/js/aius.js"></script>
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
