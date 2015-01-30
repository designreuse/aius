<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="row">
	<div class="col-lg-12">
		<div class="ibox float-e-margins margin-bottom ">
			<div class="ibox-title" style="width: 100%; display: inline-table;">
				<p>
					<strong>${comment.cmt_writer} - </strong> <font size="2.9em"
						color="#005766">${comment.cmt_date} ${comment.cmt_time}</font>
					<c:if
						test="${id ne null && (nickname eq comment.cmt_writer || level >= '9')}">
						<input type="image" class="delete_image" value="삭제"
							id="cmt_delete" src="./resources/btn-image/delete.png"
							data-a="${comment.cmt_id}" data-b="${comment.at_id}">
					</c:if>
				</p>
				<p>${comment.cmt_data}</p>
			</div>


		</div>
	</div>
</div>