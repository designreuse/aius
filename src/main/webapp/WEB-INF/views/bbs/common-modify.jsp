<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<c:if test="${is_lock eq 'Y' }">
	<div class="form-group">
		<input type="checkbox" id="is_lock" name="is_lock" checked="checked" />
		<label for="is_lock"><font size="2.6em">비밀로 하기</font></label><br>
		<input type="text" id="title" name="title" placeholder="제목을 입력해 주세요."
			value="${title}" maxlength="40" class="form-control"
			style="margin-bottom: 10px;"> <input type="password"
			id="at_pass" name="at_pass" maxlength="20" placeholder="  글 비밀번호 입력"
			class="form-control" value="${article_password}" />
	</div>
</c:if>
<c:if test="${is_lock ne 'Y' }">
	<div class="form-group">
		<input type="checkbox" id="is_lock" name="is_lock" /> <label
			for="is_lock"><font size="2.6em">비밀로 하기</font></label><br> <input
			type="text" name="title" placeholder="제목을 입력해 주세요." value="${title}"
			id="title" maxlength="40" class="form-control"
			style="margin-bottom: 10px;"> <input type="password"
			id="at_pass" name="at_pass" maxlength="20" placeholder="  글 비밀번호 입력"
			class="form-control" />
	</div>
</c:if>
<input type="hidden" name="at_type" value="${b}">
<input type="hidden" name="at_writer" class="writer" value="${nickname}">
<input type="hidden" name="at_ref" value="0">
<input type="hidden" name="at_re_lev" value="0">
<input type="hidden" name="at_re_step" value="0">
<input type="hidden" name="m" value="m">
<input type="hidden" name="at_id" value="${n}">
<input type="file" name="file" style="display:none;">

<textarea id="contents" name="contents" required="required" style="width: 100%; min-height: 200px;"
	placeholder=" 내용을 입력해 주세요" class="bor_r_7 bor_d">${content}</textarea>


<c:if test="${files_size > 0}">
	<c:forEach var="file" items="${files}" varStatus="curr">

		<div class="row" <c:if test="${curr.count=='1'}">style="margin-top: 15px;"</c:if>>
			<div class="col-lg-12">
				<div class="ibox float-e-margins margin-bottom">

					<div id="file_area" class="ibox-title">
						<img src="./resources/btn-image/download-alt.png"
							style="height: 15px; width: 15px;"> <a
							href="/fileDown?filePath=${file.attach_file_path}&fileName=${file.attach_file_name}">
							${file.attach_file_name} (${file.file_size}K)
							${file.file_upload_date} ${file.file_upload_time }</a> <font
							color="black" size="2.9em"> <a href="javascript:void(0);"
							id="file_delete" data-a="${file.article_id}"
							data-b="${file.file_id}"> 삭제하기 </a>
						</font>
						<c:if test="${curr.count != files_size}">
							<br>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>

</c:if>
<c:choose>
	<c:when test="${files_size == '0'}">
		<div class="row" style="margin-top: 15px;">
			<div class="col-lg-12">
				<div class="ibox float-e-margins margin-bottom">
					<div id="file_area" class="ibox-title">
						<input type="file" name="file" style="width: 100%;">
					</div>
				</div>
			</div>
		</div>
		<div class="row" style="margin-top: 0px;">
			<div class="col-lg-12">
				<div class="ibox float-e-margins margin-bottom">
					<div id="file_area" class="ibox-title">
						<input type="file" name="file" style="width: 100%;">
					</div>
				</div>
			</div>
		</div>
	</c:when>
	<c:when test="${files_size == '1'}">
		<div class="row" style="margin-top: 0;">
			<div class="col-lg-12">
				<div class="ibox float-e-margins margin-bottom">
					<div id="file_area" class="ibox-title">
						<input type="file" name="file" style="width: 100%;">
					</div>
				</div>
			</div>
		</div>
	</c:when>
	<c:otherwise></c:otherwise>
</c:choose>

<script type="text/javascript">
	CKEDITOR.replace('contents', {
		toolbar : [
				{
					name : 'document',
					items : [ 'Source', '-', 'DocProps', 'Preview', 'Print' ]
				},

				{
					name : 'basicstyles',
					items : [ 'Bold', 'Italic', 'Underline', 'Strike',
							'Subscript', 'Superscript', '-', 'RemoveFormat' ]
				},
				{
					name : 'paragraph',
					items : [ 'NumberedList', 'BulletedList', '-', 'Outdent',
							'Indent', '-', 'JustifyLeft', 'JustifyCenter',
							'JustifyRight', 'JustifyBlock' ]
				},

				{
					name : 'insert',
					items : [ 'Image', 'Smiley', 'Link', 'SpecialChar' ]
				},

				{
					name : 'styles',
					items : [ 'Styles', 'Format', 'Font', 'FontSize' ]
				}, {
					name : 'colors',
					items : [ 'TextColor', 'BGColor' ]
				}

		],
		filebrowserBrowseUrl : '',
		filebrowserImageBrowseUrl : '',
		filebrowserUploadUrl : '',
		filebrowserImageUploadUrl : '',
//		filebrowserBrowseUrl : '/resources/ck/ckfinder/ckfinder.html',
//		filebrowserImageBrowseUrl : '/resources/ck/ckfinder/ckfinder.html',
//		filebrowserFlashBrowseUrl : '/resources/ck/ckfinder/ckfinder.html',
//		filebrowserUploadUrl : '/resources/ck/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files',
//		filebrowserImageUploadUrl : '/resources/ck/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images',
//		filebrowserFlashUploadUrl :'/resources/ck/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash',
		FilesystemEncoding : 'CP949',
		enterMode : 'CKEDITOR.ENTER_BR',
		height : '350px'
	});
</script>