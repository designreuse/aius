<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>

<div class="form-group">
	<input type="checkbox" id="is_lock" name="is_lock" /> <label
		for="is_lock"><font size="2.6em">비밀로 하기</font></label><br> <input
		type="text" name="title" placeholder="제목을 입력해 주세요." id="title"
		maxlength="40" class="form-control" style="margin-bottom: 10px;">
	<input type="password" id="at_pass" name="at_pass" maxlength="20"
		placeholder="  글 비밀번호 입력" class="form-control" />
</div>

<input type="hidden" name="at_type" value="${tmp.at_type}">
<input type="hidden" name="at_writer" class="writer" value="${nickname}">
<input type="hidden" name="at_ref" value="${tmp.at_ref}">
<input type="hidden" name="at_re_lev" value="${tmp.at_re_lev}">
<input type="hidden" name="at_re_step" value="${tmp.at_re_step}">
<input type="hidden" name="m" value="r">
<input type="hidden" name="at_id" value="${tmp.at_id}">
<textarea id="contents" name="contents" required="required"
	placeholder=" 내용을 입력해 주세요" class="bor_r_7 bor_d"></textarea>
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
					items : [ 'Image', 'Flash', 'Smiley', 'SpecialChar' ]
				},

				{
					name : 'styles',
					items : [ 'Styles', 'Format', 'Font', 'FontSize' ]
				}, {
					name : 'colors',
					items : [ 'TextColor', 'BGColor' ]
				}

		],

		FilesystemEncoding : 'CP949',
		enterMode : 'CKEDITOR.ENTER_BR',
		height : '350px'
	});
</script>