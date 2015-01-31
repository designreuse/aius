/**
 * 
 */

var btn_click_aius = function(board, page) {
	var tmp = "board?b=" + board + "&p=" + page;
	window.location = tmp;
}
var test = function() {
	alert("asdf");
}
$(function() {
	$('body').on('click', '#file_delete', function() {
		if (confirm("파일 삭제시 글의 수정을 \n하지 않을 경우에도 복구할 수 없습니다.\n정말로 파일을 삭제하시겠습니까?") == false)
			return false;
		var article_id = $(this).data("a");
		var file_id = $(this).data("b");
		$(this)
				.parent()
				.parent()
				.html(
						"<input type=\"file\" name=\"file\" style=\"width: 100%;\">");
		$
				.ajax({
					type : "get",
					url : "fileDelete",
					dataType : "text",
					data : {
						article_id : article_id,
						file_id : file_id
					},
					success : function(data) {
					},
					error : function(e, xhr) {
						alert(e.status + ">" + xhr)
					},
					cache : false,
					async : false,
					contentType : "application/x-www-form-urlencoded;charset=utf-8"
				})
	});
	$('#login_id_input').focus();

	$('#login_pw_input').keydown(function(event) {
		if (event.keyCode == 13) {
			$('form#login_form').submit();
			return false;
		}
	});
	$('form#login_form').submit(function() {

		var curr_url = document.referrer;
		var _id = $('#login_id_input').val();
		var _password = $('#login_pw_input').val();
		if (_id.length < 4) {
			$('#login_id_input').focus();
			alert("아이디를 확인해 주세요.(최소 4글자)");
			return false;
		} else {
			if (_password.length < 8) {
				$('#login_pw_input').focus();
				alert("비밀번호를 확인해 주세요.(최소 8글자)");
				return false;
			}
		}
		$.ajax({
			url : "loginProcessing",
			/*
			 * 여기서 url을 https://www.us-1.co.kr/login.do로 바꿔주면 됨.
			 */
			dataType : "jsonp",
			data : {
				id : _id,
				password : _password
			},
			jsonp : 'callBack',

			success : function(data) {
				var tmp = (data.result).substring(0, 1);
				if (tmp == 'N') {
					alert("비밀번호가 일치하지 않습니다.");
					$('#login_pw_input').val("");
					$('#login_pw_input').focus();
					return false;
				} else {
					window.location = curr_url;
					return false;
				}
			},
			error : function(e, xhr) {

			},
			cache : false,
			async : false,
			contentType : "application/x-www-form-urlencoded;charset=utf-8"
		})
		return false;
	});

	$('#bRecommend').click(function() {
		if (confirm("추천하시겠습니까?") == false)
			return false;
		var _id = $(this).data("b");
		var _article_id = $(this).data("a");
		$.ajax({
			type : "get",
			url : "bRecommend",
			dataType : "text",
			data : {
				article_id : _article_id,
				mem_id : _id
			},
			success : function(data) {
				data = data.trim();
				var bool = data.substring(0, 1);
				var recommend = data.substring(1);
				if (bool == "Y") {
					alert("추천하였습니다.");
					$('#at_recommend').html(recommend)
				} else {
					alert("이미 추천하였습니다.");
				}
			},
			complete : function(xhr, textStatus) {
			},
			error : function(e, xhr) {
				alert(e.status + ">" + xhr)
			},
			cache : false,
			async : false,
			contentType : "application/x-www-form-urlencoded;charset=utf-8"
		})
	});

	$('#bDelete').click(function() {
		var tmp = $(this).data("a");
		if (confirm("정말 삭제하시겠습니까?") == true) {
			$.ajax({
				type : "post",
				url : "at_delete",
				dataType : "text",
				data : {
					b : $(this).data("a"),
					n : $(this).data("b")
				},

				beforeSend : function(xhr) {
				},
				success : function(data) {
					location.href = "board?b=" + tmp + "&p=1";
				},
				error : function(e, xhr) {
				},

				complete : function(xhr, textStatus) {
				},
				cache : false,
				async : false,
				contentType : "application/x-www-form-urlencoded;charset=utf-8"
			})
		}
	});
	$('#sDelete').click(function() {
		var tmp = $(this).data("a");
		if (confirm("정말 삭제하시겠습니까?") == true) {
			$.ajax({
				type : "post",
				url : "at_delete",
				dataType : "text",
				data : {
					b : $(this).data("a"),
					n : $(this).data("b")
				},

				beforeSend : function(xhr) {
				},
				success : function(data) {
					location.href = "study?b=" + tmp + "&p=1";
				},
				error : function(e, xhr) {
				},

				complete : function(xhr, textStatus) {
				},
				cache : false,
				async : false,
				contentType : "application/x-www-form-urlencoded;charset=utf-8"
			})
		}
	});
	$('#pDelete').click(function() {
		var tmp = $(this).data("a");
		if (confirm("정말 삭제하시겠습니까?") == true) {
			$.ajax({
				type : "post",
				url : "at_delete",
				dataType : "text",
				data : {
					b : $(this).data("a"),
					n : $(this).data("b")
				},

				beforeSend : function(xhr) {
				},
				success : function(data) {
					location.href = "project?b=" + tmp + "&p=1";
				},
				error : function(e, xhr) {
				},

				complete : function(xhr, textStatus) {
				},
				cache : false,
				async : false,
				contentType : "application/x-www-form-urlencoded;charset=utf-8"
			})
		}
	});
	$('#atRestore').click(function() {
		var tmp = $(this).data("a");
		if (confirm("정말 복구하겠습니까?") == true) {
			$.ajax({
				type : "post",
				url : "at_restore",
				dataType : "text",
				data : {
					b : $(this).data("a"),
					n : $(this).data("b")
				},

				beforeSend : function(xhr) {
				},
				success : function(data) {
					alert("복구되었습니다");
					window.location.reload();
				},
				error : function(e, xhr) {
				},
				complete : function(xhr, textStatus) {
				},
				cache : false,
				async : false,
				contentType : "application/x-www-form-urlencoded;charset=utf-8"
			})
		}
	});

	$('a#logout').click(function() {
		if (confirm("로그아웃 하시겠습니까?") == false)
			return false;
		$.ajax({
			type : "post",
			url : "logout",
			dataType : "text",
			data : {},
			success : function(data) {
				window.location.reload();
				return false
			},
			error : function(e, xhr) {
				alert(e.status + ">" + xhr)
			},
			cache : false,
			async : false,
			contentType : "application/x-www-form-urlencoded;charset=utf-8"
		})
	});

	$('#bWriteProcess').add('#pWriteProcess').add('#sWriteProcess').submit(
			function() {
				var t_size = $('#title').val().length;
				var c_size = $('#contents').val().length;
				var p_size = $('#at_pass').val().length;
				if (t_size <= 0) {
					alert("제목을 입력해 주세요.");
					$('#title').focus();
					return false
				}
				if (t_size > 35) {
					alert("제목은 최대 35글자만 입력해 주세요. (현재 " + t_size + "글자.)");
					$('#title').focus();
					return false
				}
				if (p_size > 20) {
					alert("비밀번호는 최대 20글자만 입력해 주세요. (현재 " + t_size + "글자.)");
					$('#at_pass').focus();
					return false
				}
				if (p_size >= 0) {
					$('#at_write #at_pass').val(
							$.md5($('#at_write #at_pass').val()).substring(0,
									15));
				}
			});

	$('#at_pass_submit').click(function() {
		var p = $('input[name="p"]').data("a");
		var n = $('input[name="n"]').data("a");
		var b = $('input[name="b"]').data("a");

		var at_pass = $('#at_pass').val();
		if (at_pass.length <= 0) {
			alert("비밀번호를 입력해주세요. ");
			$('#at_pass').focus();
			return false;
		}
		$.ajax({
			type : "post",
			url : "pwCheck",
			dataType : "text",
			data : {
				at_pass : at_pass,
				p : p,
				n : n,
				b : b
			},
			success : function(data) {
				data = data.trim();
				if (data == "") {
					window.location.reload();
				} else {
					alert("게시물 비밀번호를 잘못 입력하였습니다.");
					$('#at_pass').val("");
					$('#at_pass').focus();
				}
			},
			error : function(e, xhr) {
			},
			cache : false,
			async : false,
			contentType : "application/x-www-form-urlencoded;charset=utf-8"
		});
		return false;
	});

	$('#at_pass').keydown(function(event) {
		if (event.keyCode == 13) {
			$('#at_pass_submit').click();
			return false;
		}
	});

	$('button#cmt_write')
			.click(
					function(event) {

						var p = $('#cmt_infor input[name="p"]').val();
						var b = $('#cmt_infor input[name="b"]').val();
						var article_id = $(
								'#cmt_infor input[name="article_id"]').val();
						var comment_writer = $(
								'#cmt_infor input[name="comment_writer"]')
								.val();
						var comment_data = $(
								'#cmt_infor input[name="comment_data"]').val();

						if (comment_data.length == 0) {
							alert("내용을 입력하세요.");
							$('#cmt_infor textarea[name="comment_data"]')
									.focus();
							return false;
						}
						if (comment_data.length > 999) {
							alert(alert("최대 1,000글자만 입력해 주세요. (현재 "
									+ comment_data.length + "글자.)"));
							$('#cmt_infor textarea[name="comment_data"]')
									.focus();
							return false;
						}
						$
								.ajax({
									type : "post",
									url : "cmtWrite",
									dataType : "text",
									data : {
										p : p,
										b : b,
										article_id : article_id,
										comment_writer : comment_writer,
										comment_data : comment_data
									},
									beforeSend : function(xhr) {
									},
									success : function(data) {
										$('input[name="comment_data"]').val("");
										$('input[name="comment_data"]').focus();
										$('div#cmt_write').after(data);
									},
									complete : function(xhr, textStatus) {
									},
									error : function(e, xhr) {
									},
									cache : false,
									async : false,
									contentType : "application/x-www-form-urlencoded;charset=utf-8"
								});
					});
	$('body').on('click', '#cmt_delete', function() {
		if (confirm("정말 삭제하겠습니까?") == false)
			return false;
		var tmp = $(this).parent().parent().parent().parent().parent();
		var id = $(this).data("a");
		var article_id = $(this).data("b");

		$.ajax({
			type : "get",
			url : "cmtDelete",
			dataType : "text",
			data : {
				comment_id : id,
				article_id : article_id
			},
			success : function(data) {
				tmp.remove();

			},
			error : function(e, xhr) {
				alert(e.status + ">" + xhr);
			},
			cache : false,
			async : false,
			contentType : "application/x-www-form-urlencoded;charset=utf-8"
		});
	});

	$('#user-register').submit(function() {
		/*
		 * 유효성 검사 비밀번호 같은지 확인 아이디 중복 확인 닉네임 중복 확인 후 토스.
		 */
		var user_id = $('#user-id').val();
		var user_name = $('#user-name').val();
		var user_nickname = $('#user-nickname').val();
		var user_email = $('#user-email').val();
		var user_pw = $('#user-pw').val();
		var user_pw_check = $('#user-pw-check').val();
		var user_intro = $('#user-intro').val();
		
		var regexp_id = /^[a-z0-9]{4,16}$/;
		if (!regexp_id.test(user_id)) {
			alert("아이디는 소문자, 숫자만 입력하실 수 있습니다. ( 최소 5글자, 최대 16글자 )"); 
			return false;
		}
		var regexp_name = /^[가-힣]{2,}$/;
		if (!regexp_name.test(user_name)) {
			alert("이름은 한글만 입력하실 수 있습니다. ( 최소 두글자 이상 )");
			return false;
		}
		var regexp_nickname = /^[a-z가-힣0-9]{3,6}$/;
		if (!regexp_nickname.test(user_nickname)) {
			alert("닉네임은 최소 세글자, 최대 6글자까지 입력하실 수 있습니다. ( 특수문자, 기호등 제외 )");
			return false;
		}
		if(user_pw.length!=user_pw_check.length) {
			alert("비밀번호가 일치하지 않습니다.");
			return false;
		} else {
			if(user_pw.length < 8) {
				alert("비밀번호는 최소 8글자 이상 입력해야만 합니다.");
				return false;
			}
			if(user_pw_check.length < 8) {
				alert("비밀번호는 최소 8글자 이상 입력해야만 합니다.");
				return false;
			}
			if(user_pw != user_pw_check) {
				alert("비밀번호가 일치하지 않습니다.");
				return false;
			}
		}
		
		$.ajax({
			type : "POST",
			url : "idCheck",
			dataType : "text",
			data : {
				id : $(this).val()
			},
			success : function(data) {
				data = data.trim();
				data = data.substring(0, 1);
				if (data == "Y") {
				} else {
					alert("이미 사용중인 아이디입니다. 다른 아이디를 사용해주세요.");
					return false;
				} 
			},
			error : function(e, xhr) {
				alert(e.status + ">" + xhr)
			},
			cache : false,
			async : false,
			contentType : "application/x-www-form-urlencoded;charset=utf-8"
		})
		
		
		$.ajax({
			type : "post",
			url : "nicknameCheck",
			dataType : "text",
			data : {
				nickname : user_nickname
			},
			success : function(data) {
				data = data.trim();
				data = data.substring(0, 1);
				if (data == "Y") {
					$('font.mem_nickname').html("사용 가능");
				} else {
					alert("이미 사용중인 닉네임입니다. 다른 닉네임을 사용해 주세요.");
					return false;
				} 
			},
			error : function(e, xhr) {
				alert(e.status + ">" + xhr)
			},
			cache : false,
			async : false,
			contentType : "application/x-www-form-urlencoded;charset=utf-8"
		})
		/*
		 * 간략한 유효성 검사 마무리.
		 * 여기서부터는 중복검사 들어가야 함.
		 */
	});

});
function b_hit_up(_id, _board, _p) {
	$.ajax({
		type : "get",
		url : "hit_up",
		dataType : "text",
		data : {
			id : _id
		},
		success : function(data) {
			location.href = "bRead?b=" + _board + "&p=" + _p + "&n=" + _id;
		},
		error : function(e, xhr) {
			alert(e.status + ">" + xhr)
		},
		cache : false,
		async : false,
		contentType : "application/x-www-form-urlencoded;charset=utf-8"
	});
	return false;
}

function s_hit_up(_id, _board, _p) {
	$.ajax({
		type : "get",
		url : "hit_up",
		dataType : "text",
		data : {
			id : _id
		},
		success : function(data) {
			location.href = "sRead?b=" + _board + "&p=" + _p + "&n=" + _id;
		},
		error : function(e, xhr) {
			alert(e.status + ">" + xhr)
		},
		cache : false,
		async : false,
		contentType : "application/x-www-form-urlencoded;charset=utf-8"
	});
	return false;
}

function p_hit_up(_id, _board, _p) {
	$.ajax({
		type : "get",
		url : "hit_up",
		dataType : "text",
		data : {
			id : _id
		},
		success : function(data) {
			location.href = "pRead?b=" + _board + "&p=" + _p + "&n=" + _id;
		},
		error : function(e, xhr) {
			alert(e.status + ">" + xhr)
		},
		cache : false,
		async : false,
		contentType : "application/x-www-form-urlencoded;charset=utf-8"
	});
	return false;
}