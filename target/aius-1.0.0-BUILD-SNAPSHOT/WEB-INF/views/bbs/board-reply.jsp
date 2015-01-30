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

<link href="./resources/css/plugins/summernote/summernote.css"
	rel="stylesheet">
<link href="./resources/css/plugins/summernote/summernote-bs3.css"
	rel="stylesheet">
<script type="text/javascript" src="./resources/ck/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="./resources/ck/ckfinder/ckfinder.js"></script>
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
					<li class="active"><a href="#"><i class="fa fa-th-large"></i>
							<span class="nav-label">게시판</span> <span class="fa arrow"></span></a>
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

			<div class="wrapper wrapper-content animated">
				<div class="row">
					<div class="col-lg-12" style="border-radius: 5px; display: inline-block;">
						<h2>답글 - ${tmp.at_id}번의 답글</h2>
						<form method="post" action="/bWriteProcess">
							<div class="form-group">
								<input type="checkbox" id="is_lock" name="is_lock" /> <label
									for="is_lock"><font size="2.6em">비밀로 하기</font></label><br>

								<input type="text" name="title" placeholder="제목을 입력해 주세요." id="title"
									maxlength="40" class="form-control" style="margin-bottom: 10px;">
								
								<input
									type="password" id="at_pass" name="at_pass" maxlength="20"
									placeholder="  글 비밀번호 입력" class="form-control" />
							</div>
							
							<input type="hidden" name="at_type" value="${tmp.at_type}"> 
							<input type="hidden" name="at_writer" class="writer" value="${nickname}">
							<input type="hidden" name="at_ref" value="${tmp.at_ref}">
							<input type="hidden" name="at_re_lev" value="${tmp.at_re_lev}"> 
							<input type="hidden" name="at_re_step" 	value="${tmp.at_re_step}"> 
							<input type="hidden" name="m" value="r">
							<input type="hidden" name="at_id" value="${tmp.at_id}"> 
							<textarea id="contents" name="contents" required="required"
								placeholder=" 내용을 입력해 주세요" class="bor_r_7 bor_d"></textarea>

							<script type="text/javascript">
								CKEDITOR
										.replace(
												'contents',
												{
													toolbar : [
															{
																name : 'document',
																items : [
																		'Source',
																		'-',
																		'DocProps',
																		'Preview',
																		'Print' ]
															},

															{
																name : 'basicstyles',
																items : [
																		'Bold',
																		'Italic',
																		'Underline',
																		'Strike',
																		'Subscript',
																		'Superscript',
																		'-',
																		'RemoveFormat' ]
															},
															{
																name : 'paragraph',
																items : [
																		'NumberedList',
																		'BulletedList',
																		'-',
																		'Outdent',
																		'Indent',
																		'-',
																		'JustifyLeft',
																		'JustifyCenter',
																		'JustifyRight',
																		'JustifyBlock' ]
															},

															{
																name : 'insert',
																items : [
																		'Image',
																		'Flash',
																		'Smiley',
																		'SpecialChar' ]
															},

															{
																name : 'styles',
																items : [
																		'Styles',
																		'Format',
																		'Font',
																		'FontSize' ]
															},
															{
																name : 'colors',
																items : [
																		'TextColor',
																		'BGColor' ]
															}

													],

													FilesystemEncoding : 'CP949',
													enterMode : 'CKEDITOR.ENTER_BR',
													height : '350px'
												});
							</script>
							<a href="bRead?b=${b}&p=${p}&n=${n}">
								<button type="button" class="btn btn-default btn-xs btn-left btn-margin-top">취소
								</button>
							</a>
							<a href="javascript:void(0);">
								<button type="submit" class="btn btn-default btn-xs btn-left btn-margin-top">제출
								</button>
							</a>
						</form>
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

	<script src="./resources/js/plugins/summernote/summernote.min.js"></script>

	<script>
		$(document).ready(function() {

			$('.summernote').summernote();

		});
		var edit = function() {
			$('.click2edit').summernote({
				focus : true
			});
		};
		var save = function() {
			var aHTML = $('.click2edit').code(); //save HTML If you need(aHTML: array).
			$('.click2edit').destroy();
		};
	</script>

</body>

</html>
