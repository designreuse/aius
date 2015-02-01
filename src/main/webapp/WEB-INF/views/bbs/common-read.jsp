<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<div class="row">
	<div class="col-lg-12">
		<div class="ibox float-e-margins" style="margin-bottom: 10px;">
			<div class="ibox-title">
				<h5>#${vo.at_id} ${vo.at_title}</h5>
			</div>
			<c:if test="${files_size > 0}">
				<div class="ibox-content">
					<c:forEach var="file" items="${files}" varStatus="curr">
						<img src="./resources/btn-image/download-alt.png"
							style="height: 15px; width: 15px;">
						<a
							href="/fileDown?filePath=${file.attach_file_path}&fileName=${file.attach_file_name}">
							${file.attach_file_name} (${file.file_size}K)
							${file.file_upload_date} ${file.file_upload_time }</a>
						<c:if test="${curr.count != files_size}">
							<br>
						</c:if>
					</c:forEach>
				</div>
			</c:if>
			<div class="ibox-content" id="image-showing-aius"
				style="padding-top: 10px; padding-bottom: 3px;">
				${vo.at_content}</div>

			<div class="ibox-content"
				style="padding-top: 10px; padding-bottom: 9px;">
				<img src="./resources/btn-image/user.png" class="btn-image-aius"
					style="margin-left: 0px;">${vo.at_writer} <img
					src="./resources/btn-image/view.png" class="btn-image-aius">${vo.at_hit}
				<img src="./resources/btn-image/recommend.png"
					class="btn-image-aius"><font id="at_recommend">${vo.at_recommend_num}</font>
				<img src="./resources/btn-image/time.png" class="btn-image-aius">

				<c:if test="${today ne article.at_date}">
										${vo.at_date}
									</c:if>
				<c:if test="${today eq article.at_date}">
										${vo.at_time}
								</c:if>
			</div>

		</div>
	</div>
</div>