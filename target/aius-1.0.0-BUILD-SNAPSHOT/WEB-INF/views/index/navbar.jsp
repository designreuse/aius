<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<nav class="navbar-default navbar-static-side" role="navigation">
	<div class="sidebar-collapse">
		<ul class="nav" id="side-menu">
			<li class="nav-header">
				<div class="dropdown profile-element">
					<span> <!-- 
                    			사용자의 대략적인 정보를 나타내는 곳. 
                    		 --> <a data-toggle="dropdown"
						class="dropdown-toggle" href="#"> <span class="clear">
								<span class="block m-t-xs"> <strong class="font-bold">David
										Williams</strong>
							</span> <span class="text-muted text-xs block">Art Director <b
									class="caret"></b></span>
						</span>
					</a>
						<ul class="dropdown-menu animated fadeInRight m-t-xs">
							<li><a href="login.html">Logout</a></li>
						</ul>
				</div>
				<div class="logo-element">IN+</div>
			</li>
			<li class="active"><a href=""><i class="fa fa-th-large"></i>
					<span class="nav-label">메인으로</span></a></li>

			<li><a href="#"><i class="fa fa-th-large"></i> <span
					class="nav-label">소개</span> <span class="fa arrow"></span></a>
				<ul class="nav nav-second-level">
					<li><a href="#">AIUS 소개</a></li>
					<li><a href="#">회원 가입</a></li>
					<li><a href="#">가입 신청</a></li>
				</ul></li>
			<li><a href="#"><i class="fa fa-th-large"></i> <span
					class="nav-label">게시판</span> <span class="fa arrow"></span></a>
				<ul class="nav nav-second-level">
					<li><a href="board">자유 게시판</a></li>
					<li><a href="#">갤러리</a></li>
				</ul></li>
			<li><a href="#"><i class="fa fa-th-large"></i> <span
					class="nav-label">스터디</span> <span class="fa arrow"></span></a>
				<ul class="nav nav-second-level">
					<li><a href="#">HTML5</a></li>
					<li><a href="#">Spring</a></li>
					<li><a href="#">Computer Vision</a></li>
					<li><a href="#">패턴 인식</a></li>
				</ul></li>
			<li><a href="#"><i class="fa fa-th-large"></i> <span
					class="nav-label">프로젝트</span> <span class="fa arrow"></span></a>
				<ul class="nav nav-second-level">
					<li><a href="#">트레지오</a></li>
					<li><a href="#">SCSC</a></li>
					<li><a href="#">Image Transforms</a></li>
				</ul></li>
		</ul>

	</div>
</nav>