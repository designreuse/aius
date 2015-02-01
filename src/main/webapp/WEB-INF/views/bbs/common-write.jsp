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
<input type="hidden" name="at_type" value="${b}">
<input type="hidden" name="at_writer" class="writer" value="${nickname}">
<input type="hidden" name="at_ref" value="0">
<input type="hidden" name="at_re_lev" value="0">
<input type="hidden" name="at_re_step" value="0">
<input type="hidden" name="m" value="w">
<textarea id="contents" name="contents" required="required"
	style="width: 100%; min-height: 200px;" placeholder=" 내용을 입력해 주세요"
	class="bor_r_7 bor_d"></textarea>

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
	var ckfinder = '/resources/ck/ckfinder';
	var commonCommand = ckfinder
			+ '/core/connector/java/connector.java?command=QuickUpload';
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
		//		filebrowserFlashBrowseUrl : '/resources/ck/ckfinder/ckfinder.html',
		//filebrowserUploadUrl : '/resources/ck/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files',
		//	filebrowserImageUploadUrl : '/resources/ck/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images',
		//		filebrowserFlashUploadUrl :'/resources/ck/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash',
		FilesystemEncoding : 'CP949',
		enterMode : 'CKEDITOR.ENTER_BR',

		height : '350px'
	});
</script>