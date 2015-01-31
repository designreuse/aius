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
					<li><a href="/"><i class="fa fa-th-large"></i> <span
							class="nav-label">메인으로</span></a></li>

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
					<li class="active"><a href="#"><i class="fa fa-th-large"></i>
							<span class="nav-label">프로젝트</span> <span class="fa arrow"></span></a>
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

			<div class="wrapper wrapper-content animated">
				
			<%@include file=".././bbs/common-read.jsp"%>
				<div class="row">
					<div class="col-lg-12">
						<a href="board?b=${b}&p=${p}">
							<button type="button" class="btn btn-default btn-xs btn-left">목록
							</button>
						</a>

						<c:if test="${id ne null}">
							<a href="javascript:void(0);"><button type="button"
									class="btn btn-default btn-xs btn-left" id="bRecommend"
									data-b="${id}" data-a="${vo.at_id}">추천</button></a>

							<a href="/pReply?b=${b}&p=${p}&n=${n}" id="reply">
								<button	type="button" class="btn btn-default btn-xs btn-left" id="pReply">답글</button></a>



							<c:if test="${level ne null && nickname ne null && id ne null}">
								<c:if test="${level >= '9'|| nickname eq vo.at_writer}">
									<a href="/pModify?b=${b}&p=${p}&n=${n}"><button type="button"
											class="btn btn-default btn-xs btn-left">수정</button></a>
								</c:if>
								<c:if
									test="${ (level >= '9'|| nickname eq vo.at_writer) && vo.at_deleted eq 'N'}">
									<a href="#"><button type="button"
											class="btn btn-default btn-xs btn-left" id="pDelete"
											data-a="${b}" data-b="${vo.at_id}">삭제</button></a>
								</c:if>
								<c:if test="${level >= '9' && vo.at_deleted eq 'Y'}">
									<a href="#"><button type="button"
											class="btn btn-default btn-xs btn-left" id="atRestore"
											data-a="${b}" data-b="${vo.at_id}">복구</button></a>
								</c:if>
							</c:if>
						</c:if>
					</div>
				</div>
				
				<c:if test="${level ne null && nickname ne null && id ne null}">
					<c:if test="${nickname ne null && level >= '3'}">
						<div class="row" style="margin-top: 20px;" id="cmt_write">
							<div class="col-lg-12 form-group has-success">
								<form id="cmt_infor" onsubmit="return false">
									<input type="hidden" name="article_id" value="${vo.at_id}">
									<input type="hidden" name="comment_writer" value="${nickname}">
									<input type="hidden" name="p" value="${p}"> <input
										type="hidden" name="b" value="${b}>"> <input
										type="text" class="form-control" name="comment_data" style="height: 120px;">
                           		 	<button type="button" class="btn btn-default" style="margin-top: 10px;" id="cmt_write">댓글 입력</button>
								</form>
							</div>
						</div>
					</c:if>
				</c:if>
				
				
				<c:forEach var="comment" items="${comments}" varStatus="status">
				
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox float-e-margins margin-bottom " >
							<div class="ibox-title"
								style="width: 100%; display: inline-table;">
								<p>
									<strong>${comment.cmt_writer} - </strong>
									<font size="2.9em" color="#005766">${comment.cmt_date} ${comment.cmt_time}</font>
									<c:if test="${id ne null && (nickname eq comment.cmt_writer || level >= '9')}">
										<input type="image" class="delete_image" value="삭제" id="cmt_delete" src="./resources/btn-image/delete.png"  data-a="${comment.cmt_id}" data-b="${comment.at_id}">
									</c:if>
								</p>
								<p>${comment.cmt_data} </p>
							</div>


						</div>
					</div>
				</div>
				</c:forEach>
				
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
