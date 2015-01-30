<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>



<div class="row border-bottom" >
	<nav class="navbar navbar-static-top white-bg" role="navigation"
		style="margin-bottom: 0">
		<div class="navbar-header">
			<a class="navbar-minimalize minimalize-styl-2 btn btn-primary "
				href="#"><i class="fa fa-bars"></i> </a>
			<form role="search" class="navbar-form-custom" method="post"
				action="search_results.html" style="margin-bottom: 0;">
				<div class="form-group">
				</div>
			</form>
		</div>
		<ul class="nav navbar-top-links navbar-right">

			<c:choose>
				<c:when test="${id eq null}">
					<li><a href="/login?"> <i class="fa fa-sign-in"></i> Log in
					</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="javascript:void(0);" id="logout"> <i
							class="fa fa-sign-out"></i> Log out
					</a></li>
				</c:otherwise>
			</c:choose>
		</ul>

	</nav>
</div>