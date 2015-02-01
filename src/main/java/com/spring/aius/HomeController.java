package com.spring.aius;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.json.JSONException;
import org.json.JSONObject;

import com.spring.dao.AttachFile;
import com.spring.dao.Board;
import com.spring.dao.Comment;
import com.spring.dao.Member;
import com.spring.dao.DaoFactory;
import com.spring.dto.BoardVO;
import com.spring.dto.CommentVO;
import com.spring.dto.FileVO;
import com.spring.dto.MemberVO;
import com.spring.etc.Btype;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	private static Boolean flag = false;
	private static String curr_lock_str = "__curr_lock";
	private static String curr_Lock_str_md5 = "2e11edb12c37d93";
	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 * 
	 * @throws SQLException
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws SQLException {
		logger.info("Welcome home! The client locale is {}.", locale);

		ApplicationContext context = new AnnotationConfigApplicationContext(
				DaoFactory.class);
		Board b_dao = context.getBean("BoardDao", Board.class);

		BoardVO n_board = b_dao.get_notice();
		n_board.setAt_content(n_board.getAt_content().replace("&amp;", "&"));
		model.addAttribute("notice", n_board);

		Date date = new Date();
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdformat.format(date);

		model.addAttribute("today", today);
		return "home";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Locale locale, Model model) throws SQLException {
		logger.info("Welcome home! The client locale is {}.", locale);

		ApplicationContext context = new AnnotationConfigApplicationContext(
				DaoFactory.class);
		Board b_dao = context.getBean("BoardDao", Board.class);

		BoardVO n_board = b_dao.get_notice();
		n_board.setAt_content(n_board.getAt_content().replace("&amp;", "&"));
		model.addAttribute("notice", n_board);

		Date date = new Date();
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdformat.format(date);

		model.addAttribute("today", today);
		return "home";
	}

	@RequestMapping(value = "/bRecommend", method = RequestMethod.GET)
	public String bRecommend(@RequestParam String article_id,
			@RequestParam String mem_id, Locale locale, Model model) {
		ApplicationContext context = new AnnotationConfigApplicationContext(
				DaoFactory.class);
		Board b_dao = context.getBean("BoardDao", Board.class);
		boolean ck = b_dao.article_recommend(article_id, mem_id); // 조회 후 올려주는거
		String data = "";

		if (ck) { // 참인경우 올려줘야지.
			data = "Y" + b_dao.inquiry_recommend(article_id, mem_id);
		} else {
			data = "N" + b_dao.inquiry_recommend(article_id, mem_id);
		}
		model.addAttribute("data", data);
		return "bbs/at_recommend";
	}

	@RequestMapping(value = "/contacts", method = RequestMethod.GET)
	public String contacts(Locale locale, Model model) throws SQLException {
		logger.info("Welcome home! The client locale is {}.", locale);

		ApplicationContext context = new AnnotationConfigApplicationContext(
				DaoFactory.class);
		Board b_dao = context.getBean("BoardDao", Board.class);

		return "aius/contacts";
	}

	@RequestMapping(value = "/bRead", method = RequestMethod.GET)
	public String bRead(@RequestParam String b, @RequestParam String p,
			@RequestParam String n, Locale locale, Model model,
			HttpSession session, HttpServletRequest request) {
		String board = b;
		String page = p;
		String num = n;
		/*
		 * board 는 게시판 종류 페이지는 게시물이 존재하는 페이지 번호. num은 게시물 아이디임!!!!!!!!!!!!!!!
		 */
		Btype bt_return = new Btype();
		String board_type = bt_return.bt_return(board);
		ApplicationContext context = new AnnotationConfigApplicationContext(
				DaoFactory.class);
		Board b_dao = context.getBean("BoardDao", Board.class);
		BoardVO vo = b_dao.get_column(num);
		if(vo.getAt_content().equals("")) {
			vo.setAt_content("<br><br>");
		}
		vo.setAt_pw("NOT ACCESS");
		/* 여기서 글이 지워졌는지 확인 */

		String title = vo.getAt_title();
		String content = vo.getAt_content();
		model.addAttribute("b", board);
		model.addAttribute("p", page);
		model.addAttribute("n", num);
		model.addAttribute("article_id", vo.getAt_id());
		model.addAttribute("board", board);
		model.addAttribute("board_type", board_type);
		model.addAttribute("vo", vo);

		if (session.getAttribute("id") != null
				&& session.getAttribute("nickname") != null
				&& session.getAttribute("level") != null
				&& (Integer) session.getAttribute("level") >= 9) {

			// 여기서 관리자 체크 해줘야됨.
			flag = true;
		} else { // 관리자가 아닌 경우,
			if (vo.getAt_deleted().equals("Y")) {
				return "bbs/at_deleted";
			}
			if (session.getAttribute("id") != null
					&& session.getAttribute("nickname") != null
						&& session.getAttribute("level") != null) {
				// 여기까지는 로그인 상태라는건 알음.
				if (vo.getAt_writer().equals(session.getAttribute("nickname"))) {
					// 여기서 로그인과 글쓴이가 같으면?
					flag = true;
				} else {
					if (vo.getIs_lock().equals("N")) {
						flag = true;
					}
					// 여기는 로그인 != 글쓴이
					if (!flag) {
						return "signup/at_lock";
					}
				}
			} else {
				if (vo.getIs_lock().equals("N")) {
					flag = true;
				}
				if (!flag)
					return "signup/at_lock";
			}

		}
		Comment c_dao =  context.getBean("CommentDao", Comment.class);
		Vector<CommentVO> comments = c_dao.select_comment(num); // n은 게시물 id
		/*
		 * num에 해당하는 게시물의 코멘트를 모조리 불러와 comments에 넣음.
		 */
		content = content.replace("&amp;", "&");

		model.addAttribute("title", title);
		model.addAttribute("content", content);
		model.addAttribute("comments", comments);

		AttachFile f_dao = new DaoFactory().AttachFileDao();
		Vector<FileVO> f_vo = f_dao.select_file(Integer.parseInt(num));

		model.addAttribute("files", f_vo);
		model.addAttribute("files_size", f_vo.size());
		
		flag = false;
		return "bbs/board-read";
	}

	@RequestMapping(value = "/sRead", method = RequestMethod.GET)
	public String sRead(@RequestParam String b, @RequestParam String p,
			@RequestParam String n, Locale locale, Model model,
			HttpSession session, HttpServletRequest request) {
		String board = b;
		String page = p;
		String num = n;
		/*
		 * board 는 게시판 종류 페이지는 게시물이 존재하는 페이지 번호. num은 게시물 아이디임!!!!!!!!!!!!!!!
		 */
		Btype bt_return = new Btype();
		String board_type = bt_return.bt_return(board);
		ApplicationContext context = new AnnotationConfigApplicationContext(
				DaoFactory.class);
		Board b_dao = context.getBean("BoardDao", Board.class);
		BoardVO vo = b_dao.get_column(num);
		vo.setAt_pw("NOT ACCESS");
		/* 여기서 글이 지워졌는지 확인 */
		if(vo.getAt_content().equals("")) {
			vo.setAt_content("<br><br>");
		}
		String title = vo.getAt_title();
		String content = vo.getAt_content();
		model.addAttribute("b", board);
		model.addAttribute("p", page);
		model.addAttribute("n", num);
		model.addAttribute("article_id", vo.getAt_id());
		model.addAttribute("board", board);
		model.addAttribute("board_type", board_type);
		model.addAttribute("vo", vo);

		if (session.getAttribute("id") != null
				&& session.getAttribute("nickname") != null
				&& session.getAttribute("level") != null
				&& (Integer) session.getAttribute("level") >= 9) {

			// 여기서 관리자 체크 해줘야됨.
			flag = true;
		} else { // 관리자가 아닌 경우,
			if (vo.getAt_deleted().equals("Y")) {
				return "bbs/at_deleted";
			}
			if (session.getAttribute("id") != null
					&& session.getAttribute("nickname") != null
					&& session.getAttribute("level") != null) {
				// 여기까지는 로그인 상태라는건 알음.
				if (vo.getAt_writer().equals(session.getAttribute("nickname"))) {
					// 여기서 로그인과 글쓴이가 같으면?
					flag = true;
				} else {
					if (vo.getIs_lock().equals("N")) {
						flag = true;
					}
					// 여기는 로그인 != 글쓴이
					if (!flag) {
						return "signup/at_lock";
					}
				}
			} else {
				if (vo.getIs_lock().equals("N")) {
					flag = true;
				}
				if (!flag)
					return "signup/at_lock";
			}

		}
		
		Comment c_dao =  context.getBean("CommentDao", Comment.class);
		Vector<CommentVO> comments = c_dao.select_comment(num); // n은 게시물 id
		/*
		 * num에 해당하는 게시물의 코멘트를 모조리 불러와 comments에 넣음.
		 */
		content = content.replace("&amp;", "&");

		model.addAttribute("title", title);
		model.addAttribute("content", content);
		model.addAttribute("comments", comments);
		
		AttachFile f_dao = new DaoFactory().AttachFileDao();
		Vector<FileVO> f_vo = f_dao.select_file(Integer.parseInt(num));

		model.addAttribute("files", f_vo);
		model.addAttribute("files_size", f_vo.size());
		flag = false;
		return "bbs/study-read";
	}

	@RequestMapping(value = "/pRead", method = RequestMethod.GET)
	public String pRead(@RequestParam String b, @RequestParam String p,
			@RequestParam String n, Locale locale, Model model,
			HttpSession session, HttpServletRequest request) {
		String board = b;
		String page = p;
		String num = n;
		/*
		 * board 는 게시판 종류 페이지는 게시물이 존재하는 페이지 번호. num은 게시물 아이디임!!!!!!!!!!!!!!!
		 */
		Btype bt_return = new Btype();
		String board_type = bt_return.bt_return(board);
		ApplicationContext context = new AnnotationConfigApplicationContext(
				DaoFactory.class);
		Board b_dao = context.getBean("BoardDao", Board.class);
		BoardVO vo = b_dao.get_column(num);
		vo.setAt_pw("NOT ACCESS");
		/* 여기서 글이 지워졌는지 확인 */
		if(vo.getAt_content().equals("")) {
			vo.setAt_content("<br><br>");
		}
		String title = vo.getAt_title();
		String content = vo.getAt_content();
		model.addAttribute("b", board);
		model.addAttribute("p", page);
		model.addAttribute("n", num);
		model.addAttribute("article_id", vo.getAt_id());
		model.addAttribute("board", board);
		model.addAttribute("board_type", board_type);
		model.addAttribute("vo", vo);

		if (session.getAttribute("id") != null
				&& session.getAttribute("nickname") != null
				&& session.getAttribute("level") != null
				&& (Integer) session.getAttribute("level") >= 9) {

			// 여기서 관리자 체크 해줘야됨.
			flag = true;
		} else { // 관리자가 아닌 경우,
			if (vo.getAt_deleted().equals("Y")) {
				return "bbs/at_deleted";
			}
			if (session.getAttribute("id") != null
					&& session.getAttribute("nickname") != null
					&& session.getAttribute("level") != null) {
				// 여기까지는 로그인 상태라는건 알음.
				if (vo.getAt_writer().equals(session.getAttribute("nickname"))) {
					// 여기서 로그인과 글쓴이가 같으면?
					flag = true;
				} else {
					if (vo.getIs_lock().equals("N")) {
						flag = true;
					}
					// 여기는 로그인 != 글쓴이
					if (!flag) {
						return "signup/at_lock";
					}
				}
			} else {
				if (vo.getIs_lock().equals("N")) {
					flag = true;
				}
				if (!flag)
					return "signup/at_lock";
			}

		}

		
		Comment c_dao =  context.getBean("CommentDao", Comment.class);
		Vector<CommentVO> comments = c_dao.select_comment(num); // n은 게시물 id
		/*
		 * num에 해당하는 게시물의 코멘트를 모조리 불러와 comments에 넣음.
		 */
		content = content.replace("&amp;", "&");

		model.addAttribute("title", title);
		model.addAttribute("content", content);
		model.addAttribute("comments", comments);
		
		AttachFile f_dao = new DaoFactory().AttachFileDao();
		Vector<FileVO> f_vo = f_dao.select_file(Integer.parseInt(num));

		model.addAttribute("files", f_vo);
		model.addAttribute("files_size", f_vo.size());
		
		flag = false;
		return "bbs/project-read";
	}

	@RequestMapping(value = "/hit_up", method = RequestMethod.GET)
	public String hit_up(@RequestParam String id, Locale locale, Model model,
			HttpSession session) {

		/*
		 * 
		 * 여기서도 세션이 있나 확인해야 할 것 같음.
		 */
		ApplicationContext context = new AnnotationConfigApplicationContext(
				DaoFactory.class);
		Board b_dao = context.getBean("BoardDao", Board.class);
		String article_id = id;
		b_dao.article_hit_up(article_id);

		return "bbs/board-read";
	}

	@RequestMapping(value = "/information", method = RequestMethod.GET)
	public String information(Locale locale, Model model) throws SQLException {
		logger.info("Welcome home! The client locale is {}.", locale);

		ApplicationContext context = new AnnotationConfigApplicationContext(
				DaoFactory.class);
		Board b_dao = context.getBean("BoardDao", Board.class);

		BoardVO n_board = b_dao.get_notice();
		n_board.setAt_content(n_board.getAt_content().replace("&amp;", "&"));
		model.addAttribute("notice", n_board);
		return "aius/information";
	}

	@RequestMapping(value = "/gallery", method = RequestMethod.GET)
	public String gallery(Locale locale, Model model) throws SQLException {
		logger.info("Welcome home! The client locale is {}.", locale);

		return "bbs/gallery";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model, HttpSession session)
			throws SQLException {
		logger.info("Welcome home! The client locale is {}.", locale);

		
		return "signup/login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Locale locale, Model model, HttpSession session, HttpServletResponse response) throws SQLException, IOException {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		if (session.getAttribute("id") != null
				&& session.getAttribute("nickname") != null
				&& session.getAttribute("level") != null) {

			String url = "/index";
			response.sendRedirect(url);
			return "home";
		}
		return "signup/register";
	}
	void select_board(@RequestParam String b, @RequestParam String p,
			Locale locale, Model model, HttpServletRequest request,
			HttpSession session) throws SQLException, UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");

		Btype bt_return = new Btype();
		String board_type = bt_return.bt_return(b);

		int page = 1;
		if (p != null) {
			page = Integer.parseInt(p);
			if (page < 1)
				page = 1;
		}

		ApplicationContext context = new AnnotationConfigApplicationContext(
				DaoFactory.class);
		Board b_dao = context.getBean("BoardDao", Board.class);

		int num = 0;
		Vector<BoardVO> article;

		if (session.getAttribute("id") != null
				&& session.getAttribute("nickname") != null
				&& session.getAttribute("level") != null
				&& (Integer) session.getAttribute("level") >= 9) {
			num = b_dao.get_column_number(b);
			article = b_dao.select_board(b, page);
		} else {
			num = b_dao.get_column_number_not_delete(b);
			article = b_dao.select_board_not_delete(b, page);
		}
		// 현재 게시판 종류의 총 게시물의 갯수를 가져옴.
		int page_num = num % 15;
		// 현재 게시물의 갯수를 15로 나눈 나머지가 0이 아닐경우, 페이지가 한개 더 추가됨.
		if (page_num == 0)
			page_num = num / 15;
		else
			page_num = (num / 15) + 1;
		// 총 페이지의 갯수를 지정함.
		int curr_page_num = Integer.parseInt(p);
		// 현재 페이지 넘버를 가지는 정수형 변수.
		int curr_page = (int) ((curr_page_num - 1) / 10);
		int pt = (int) page_num / 10;
		int n = 0;

		model.addAttribute("board", b);
		model.addAttribute("num", num);
		model.addAttribute("page_num", page_num);
		model.addAttribute("curr_page", curr_page);
		model.addAttribute("n", n);
		model.addAttribute("curr_page_num", curr_page_num);
		model.addAttribute("pt", pt);

		for (int i = 0; i < article.size(); i++) {
			String tmp = article.elementAt(i).getAt_content().replaceAll("<[^>]*>","");
			int size = tmp.length() - 1 < 120 ? 
					tmp.length()
					: 120;
			article.elementAt(i).setAt_content(
					tmp.substring(0, size));
		}
		/*
		 * board -> board 게시판 종류에 따라, page -> 페이지 번호에 따라 게시물을 불러옴. article 벡터에
		 * 게시물을 넣은 후, 각 어트리뷰트 추가.
		 */

		model.addAttribute("article", article);
		model.addAttribute("page", page);

		Date date = new Date();
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdformat.format(date);

		model.addAttribute("today", today);
	}
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public String board(@RequestParam String b, @RequestParam String p,
			Locale locale, Model model, HttpServletRequest request,
			HttpSession session) throws SQLException,
			UnsupportedEncodingException {
		logger.info("board! The client locale is {}.", locale);
		select_board(b, p, locale, model, request, session);
		
		/*
		 * article = 게시물을 담고 있는 벡터. board_type = 게시판 이름 예) Swift, C++, 자유게시판
		 * board = 게시물 종류
		 */
		return "bbs/board";
	}

	@RequestMapping(value = "/study", method = RequestMethod.GET)
	public String study(@RequestParam String b, @RequestParam String p,
			Locale locale, Model model, HttpServletRequest request,
			HttpSession session) throws SQLException,
			UnsupportedEncodingException {
		logger.info("board! The client locale is {}.", locale);

		select_board(b, p, locale, model, request, session);
		return "bbs/study";
	}

	@RequestMapping(value = "/project", method = RequestMethod.GET)
	public String project(@RequestParam String b, @RequestParam String p,
			Locale locale, Model model, HttpServletRequest request,
			HttpSession session) throws SQLException,
			UnsupportedEncodingException {
		logger.info("board! The client locale is {}.", locale);

		select_board(b, p, locale, model, request, session);
		return "bbs/project";

	}

	@RequestMapping(value = "/loginProcessing")
	public void loginProcessing(Locale locale, Model model,
			HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws IOException,
			NoSuchAlgorithmException, JSONException {

		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String callBack = request.getParameter("callBack");
		JSONObject obj = new JSONObject();

		String user_id = id;
		String user_pass = password;
		// DB request
		ApplicationContext context = new AnnotationConfigApplicationContext(
				DaoFactory.class);
		Member m_dao = context.getBean("MemberDao", Member.class);
		MemberVO vo = m_dao.Login(user_id);

		MessageDigest md = null;
		md = MessageDigest.getInstance("md5");

		byte[] bytData = user_pass.getBytes();
		md.update(bytData);
		byte[] digest = md.digest();
		String strENCData = "";
		for (int i = 0; i < digest.length; i++)
			strENCData = strENCData
					+ Integer.toHexString(digest[i] & 0xFF).toUpperCase();

		strENCData = strENCData.substring(0, 15);
		if (vo.getUser_id().equals(user_id)
				&& vo.getUser_pw().equals(strENCData)) {
			session.setAttribute("id", vo.getUser_id());
			session.setAttribute("nickname", vo.getUser_nickname());
			session.setAttribute("level", vo.getUser_level());
			session.setMaxInactiveInterval(60 * 60 * 60);

			// String loginIP = request.getRemoteAddr();
			// m_dao.save_ip(user_id, loginIP);
			obj.put("result", "Y");
		} else {
			obj.put("result", "N");
		}
		PrintWriter out = response.getWriter();
		out.write(callBack + "(" + obj.toString() + ")");
		out.flush();
		out.close();
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout(Locale locale, Model model, HttpSession session) {
		if (session.getAttribute("nickname") != null
				|| session.getAttribute("id") != null
				|| session.getAttribute("level") != null) {
			session.invalidate();
		}
		return "home";
	}
	
	@RequestMapping(value = "/at_delete", method = RequestMethod.POST)
	public String at_delete(@RequestParam String b, @RequestParam String n,
			Locale locale, Model model)  {
		/*
		 * 
		 * 여기서도 작성자와 세션값이 같나 확인.
		 */
		String article_id = n;
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		Board b_dao = context.getBean("BoardDao", Board.class);

		b_dao.article_delete(article_id);
		return "empty";

	}
	@RequestMapping(value = "/at_restore", method = RequestMethod.POST)
	public String at_restore(@RequestParam String b, @RequestParam String n,
			Locale locale, Model model)  {
		/*
		 * 
		 * 여기서도 작성자와 세션값이 같나 확인.
		 */
		String article_id = n;
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		Board b_dao = context.getBean("BoardDao", Board.class);

		b_dao.article_restore(article_id);
		return "empty";

	}
	
	@RequestMapping(value = "/bWrite", method = RequestMethod.GET)
	public String bWrite(@RequestParam String b, @RequestParam String p,Locale locale, Model model , HttpSession session)  {
		model.addAttribute("b", b);
		model.addAttribute("p", p);
		
		if(session.getAttribute("nickname")==null || 
				session.getAttribute("id")== null || 
					session.getAttribute("level")==null) return "signup/login";
		return "bbs/board-write";

	}
	
	@RequestMapping(value = "/sWrite", method = RequestMethod.GET)
	public String sWrite(@RequestParam String b, @RequestParam String p,Locale locale, Model model , HttpSession session)  {
		model.addAttribute("b", b);
		model.addAttribute("p", p);
		
		if(session.getAttribute("nickname")==null || 
				session.getAttribute("id")== null || 
					session.getAttribute("level")==null) return "signup/login";
		return "bbs/study-write";

	}
	
	@RequestMapping(value = "/pWrite", method = RequestMethod.GET)
	public String pWrite(@RequestParam String b, @RequestParam String p,Locale locale, Model model , HttpSession session)  {
		model.addAttribute("b", b);
		model.addAttribute("p", p);
		
		if(session.getAttribute("nickname")==null || 
				session.getAttribute("id")== null || 
					session.getAttribute("level")==null) return "signup/login";
		return "bbs/project-write";

	}
	
	String writeProcess(Locale locale, Model model, HttpSession session, @RequestParam("file") MultipartFile[] files,
			HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		if(session.getAttribute("nickname")==null || 
				session.getAttribute("id")== null || 
					session.getAttribute("level")==null) return "signup/login";
		
		BoardVO vo = new BoardVO();
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		Board b_dao = context.getBean("BoardDao", Board.class);

		String article_title = request.getParameter("title");
		String article_write = request.getParameter("at_writer");
		String article_password = request.getParameter("at_pass");
		String checked = request.getParameter("is_lock");
		
		String is_lock = "Y";
		if ( checked ==null || article_password == null || article_password.equals("")) {
			is_lock = "N";
			article_password = "";
		} else {
			if( !article_password.equals(curr_Lock_str_md5) ) {
				//현재 비밀번호가 바뀐 상태임.
				MessageDigest md = null;
				try {
					md = MessageDigest.getInstance("md5");
				} catch (NoSuchAlgorithmException e1) {
					e1.printStackTrace();
				}

				byte[] bytData = article_password.getBytes();
				md.update(bytData);
				byte[] digest = md.digest();
				String strENCData = "";
				for (int i = 0; i < digest.length; i++)
					strENCData = strENCData
							+ Integer.toHexString(digest[i] & 0xFF).toUpperCase();
				
				strENCData = strENCData.substring(0, 15);
				article_password = strENCData; 
			}
		}
		
		int article_hit = 0;
		int article_re_lev = 0;
		int article_ref = 0;
		int article_re_step = 0;
		int article_id = 0;

		if (request.getParameter("at_id") != null
				&& request.getParameter("at_id").trim().length() != 0) {
			article_id = Integer.parseInt(request.getParameter("at_id"));
		}
		if (request.getParameter("at_re_lev") != null
				&& request.getParameter("at_re_lev").trim().length() != 0) {
			article_re_lev = Integer.parseInt(request
					.getParameter("at_re_lev"));
		}
		if (request.getParameter("at_ref") != null
				&& request.getParameter("at_ref").trim().length() != 0) {
			article_ref = Integer.parseInt(request.getParameter("at_ref"));
		}
		if (request.getParameter("at_re_step") != null
				&& request.getParameter("at_re_step").trim().length() != 0) {
			article_re_step = Integer.parseInt(request
					.getParameter("at_re_step"));
		}
		String article_type = request.getParameter("at_type");
		String article_content = request.getParameter("contents");
	
		

		article_title = new String(article_title.getBytes("8859_1"),"utf-8");
		article_write = new String(article_write.getBytes("8859_1"),"utf-8");
		article_content = new String(article_content.getBytes("8859_1"),"utf-8");
		article_content = article_content.replaceAll("<script", "&lt;script");
		article_content = article_content.replaceAll("</script", "&lt;/script");
		article_content = article_content.replaceAll("<iframe", "&lt;iframe");
		article_content = article_content.replaceAll("<embed", "&lt;embed");  // embed 태그를 사용하지 않을 경우만
		article_content = article_content.replaceAll("<object", "&lt;object");    // object 태그를 사용하지 않을 경우만
		article_content = article_content.replaceAll("<frame", "&lt;frame");

		vo.setAt_id(article_id);
		vo.setAt_title(article_title);
		vo.setAt_writer(article_write);
		vo.setAt_hit(article_hit);
		vo.setAt_type(article_type);
		vo.setAt_content(article_content);
		vo.setAt_ref(article_ref);
		vo.setAt_re_lev(article_re_lev);
		vo.setAt_re_step(article_re_step);
		vo.setIs_lock(is_lock);
		vo.setAt_pw(article_password);
		
		
		// enctype="multipart/form-data" 넘기는 파라미터는 기존의 request로 받을 수 없다.
		if (request.getParameter("m").equals("w")) {
			article_id = b_dao.insert(vo);
		} else if (request.getParameter("m").equals("r")) {
			article_id = b_dao.reply_article(vo);
		} else if (request.getParameter("m").equals("m")) {
			article_id = b_dao.modify(vo);
		}
		
		
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			String name = file.getOriginalFilename();
			
			String _extension = name.substring(name.lastIndexOf('.') + 1).toLowerCase();
			
			String blockExt[] = {"jsp","jspx"};
			 
			// 금지할 확장자 체크
			boolean flag = true;
			for(int j=0; j<blockExt.length; j++) {
				if( blockExt[j].equals(_extension) ){
					System.out.println("업로드 금지 파일입니다.");
					flag = false;
					break;
				}
			}
			
			if(!flag) continue;
			if (name.equals("")) {
				continue;
			}
			try {
				byte[] bytes = file.getBytes();
				double file_size = file.getSize()/(double)1024;
				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				System.out.println(rootPath);
				File dir = new File(rootPath + File.separator + "tmpFiles/"
						+ article_id);
				String path = rootPath + File.separator + "tmpFiles/"
						+ article_id;
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + name);
				System.out.println(serverFile.length());
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				logger.info("Server File Location="
						+ serverFile.getAbsolutePath());
				FileVO db_file = new FileVO();
				db_file.setArticle_id(article_id);
				db_file.setAttach_file_path(path);
				db_file.setAttach_file_name(file.getOriginalFilename());
				db_file.setFile_size(file_size);
				AttachFile f_vo =  new DaoFactory().AttachFileDao();
				f_vo.insert(db_file);
			} catch (Exception e) {
				System.out.println("파일업로드 실패");
			}
		}
		vo.setAt_id(article_id);

		model.addAttribute("b", article_type);
		model.addAttribute("vo", vo);
		
		// //////////////////////////////////////////////////////
		String url = "Read?b=" + article_type + "&p=1&n=" + article_id;
		
		
		
		return url;
	}
	@RequestMapping(value = "/bWriteProcess", method = RequestMethod.POST)
	public String bWriteProcess(Locale locale, Model model, HttpSession session, @RequestParam("file") MultipartFile[] files,
			HttpServletResponse response, HttpServletRequest request) throws IOException, SQLException  {

		String result = writeProcess(locale, model, session, files, response, request);
		result = "b" + result;
		response.sendRedirect(result);
		
		if(result.equals("signup/login")) 
			return "bbs/board-read";
		else return result;
	}
	
	@RequestMapping(value = "/sWriteProcess", method = RequestMethod.POST)
	public String sWriteProcess(Locale locale, Model model, HttpSession session, @RequestParam("file") MultipartFile[] files,
			HttpServletResponse response, HttpServletRequest request) throws IOException, SQLException  {

		String result = writeProcess(locale, model, session, files, response, request);
		result = "s" + result;
		response.sendRedirect(result);
		
		if(result.equals("signup/login")) 
			return "bbs/study-read";
		else return result;
	}
	
	@RequestMapping(value = "/pWriteProcess", method = RequestMethod.POST)
	public String pWriteProcess(Locale locale, Model model, HttpSession session, @RequestParam("file") MultipartFile[] files,
			HttpServletResponse response, HttpServletRequest request) throws IOException, SQLException  {
		
		String result = writeProcess(locale, model, session, files, response, request);
		result = "p" + result;
		response.sendRedirect(result);
		
		if(result.equals("signup/login")) 
			return "bbs/project-read";
		else return result;
	}
	
	@RequestMapping(value = "/bModify", method = RequestMethod.GET)
	public String bModify(@RequestParam String b, @RequestParam String p, @RequestParam String n,
			Locale locale, Model model, HttpSession session, HttpServletRequest request)   {
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		Board b_dao = context.getBean("BoardDao", Board.class);
		Btype bt_return = new Btype();
		String board = b;
		String board_type = bt_return.bt_return(board);
		
		model.addAttribute("board", board);
		if(session.getAttribute("nickname")==null || 
				session.getAttribute("id")== null || 
					session.getAttribute("level")==null) return "signup/login";
		
		
		BoardVO tmp = b_dao.get_column(n);
		String title = tmp.getAt_title();
		String content = tmp.getAt_content();
		content = content.replace("<br>", "\r\n");


		model.addAttribute("board_type", board_type);
		model.addAttribute("title", title);
		model.addAttribute("content", content);
		model.addAttribute("n", n);
		model.addAttribute("b", b);
		model.addAttribute("p", p);
		model.addAttribute("is_lock", tmp.getIs_lock());
		model.addAttribute("article_password", curr_lock_str);
		
		if (session.getAttribute("id") != null
				&& session.getAttribute("level") != null
					&& session.getAttribute("nickname") !=null ) {
			int level = (Integer) session.getAttribute("level");
			if (level > 5) {
				AttachFile db_file = new DaoFactory().AttachFileDao();
				Vector<FileVO> f_vo = db_file.select_file(Integer.parseInt(n));
				model.addAttribute("f_vo", f_vo);
				
				model.addAttribute("files", f_vo);
				model.addAttribute("files_size", f_vo.size());
				return "bbs/board-modify";
			} else
				return "signup/login";
		} else {
			// 레벨 검사도 같이 하면될 듯 싶오요.
			return "signup/login";
		}
	}
	@RequestMapping(value = "/sModify", method = RequestMethod.GET)
	public String sModify(@RequestParam String b, @RequestParam String p, @RequestParam String n,
			Locale locale, Model model, HttpSession session, HttpServletRequest request)   {
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		Board b_dao = context.getBean("BoardDao", Board.class);
		Btype bt_return = new Btype();
		String board = b;
		String board_type = bt_return.bt_return(board);
		
		Vector<BoardVO> newest = b_dao.lately_article();
		model.addAttribute("newest", newest);
		
		model.addAttribute("board", board);
		if(session.getAttribute("nickname")==null || 
				session.getAttribute("id")== null || 
					session.getAttribute("level")==null) return "signup/login";
		
		
		BoardVO tmp = b_dao.get_column(n);
		String title = tmp.getAt_title();
		String content = tmp.getAt_content();
		content = content.replace("<br>", "\r\n");


		model.addAttribute("board_type", board_type);
		model.addAttribute("title", title);
		model.addAttribute("content", content);
		model.addAttribute("n", n);
		model.addAttribute("b", b);
		model.addAttribute("p", p);

		model.addAttribute("is_lock", tmp.getIs_lock());
		model.addAttribute("article_password", curr_lock_str);
		
		if (session.getAttribute("id") != null
				&& session.getAttribute("level") != null
					&& session.getAttribute("nickname") !=null ) {
			int level = (Integer) session.getAttribute("level");
			if (level > 5) {
				AttachFile db_file = new DaoFactory().AttachFileDao();
				Vector<FileVO> f_vo = db_file.select_file(Integer.parseInt(n));
				model.addAttribute("f_vo", f_vo);
				
				model.addAttribute("files", f_vo);
				model.addAttribute("files_size", f_vo.size());
				
				return "bbs/study-modify";
			
			} else
				return "signup/login";
		} else {
			// 레벨 검사도 같이 하면될 듯 싶오요.
			return "signup/login";
		}
	}
	@RequestMapping(value = "/pModify", method = RequestMethod.GET)
	public String pModify(@RequestParam String b,@RequestParam String p, @RequestParam String n,
			Locale locale, Model model, HttpSession session, HttpServletRequest request)   {
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		Board b_dao = context.getBean("BoardDao", Board.class);
		Btype bt_return = new Btype();
		String board = b;
		String board_type = bt_return.bt_return(board);
		
		
		model.addAttribute("board", board);
		if(session.getAttribute("nickname")==null || 
				session.getAttribute("id")== null || 
					session.getAttribute("level")==null) return "signup/login";
		
		
		BoardVO tmp = b_dao.get_column(n);
		String title = tmp.getAt_title();
		String content = tmp.getAt_content();
		content = content.replace("<br>", "\r\n");


		model.addAttribute("board_type", board_type);
		model.addAttribute("title", title);
		model.addAttribute("content", content);
		model.addAttribute("n", n);
		model.addAttribute("b", b);
		model.addAttribute("p", p);
		
		
		if (session.getAttribute("id") != null
				&& session.getAttribute("level") != null
					&& session.getAttribute("nickname") !=null ) {
			int level = (Integer) session.getAttribute("level");
			if (level > 5) {
				AttachFile db_file = new DaoFactory().AttachFileDao();
				Vector<FileVO> f_vo = db_file.select_file(Integer.parseInt(n));
				model.addAttribute("f_vo", f_vo);
				
				model.addAttribute("files", f_vo);
				model.addAttribute("files_size", f_vo.size());
				return "bbs/project-modify";
			} else
				return "signup/login";
		} else {
			// 레벨 검사도 같이 하면될 듯 싶오요.
			return "signup/login";
		}
	}
	String reply(@RequestParam String b, @RequestParam String p, @RequestParam String n,
			Locale locale, Model model, HttpSession session, HttpServletRequest request) {
		Btype bt_return = new Btype();
		String board_type = bt_return.bt_return(n);
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		Board b_dao = context.getBean("BoardDao", Board.class);

		model.addAttribute("board", b);

		if(session.getAttribute("nickname")==null || 
				session.getAttribute("id")== null || 
					session.getAttribute("level")==null) return "signup/login";
		
		
		BoardVO tmp = b_dao.get_column(n);
		tmp.setAt_pw("NOT ACCESS");
		model.addAttribute("board_type", board_type);
		model.addAttribute("tmp", tmp);
		model.addAttribute("b", b);
		model.addAttribute("p", p);
		model.addAttribute("n", n);
		model.addAttribute("is_lock", tmp.getIs_lock());
		model.addAttribute("article_password", curr_lock_str);
		
		return "complete";
	}
	@RequestMapping(value = "/bReply", method = RequestMethod.GET)
	public String bReply(@RequestParam String b, @RequestParam String p, @RequestParam String n,
			Locale locale, Model model, HttpSession session, HttpServletRequest request) {

		
		String result = reply(b, p, n, locale, model, session, request);
		if(result.equals("complete")) {
			int level = (Integer) session.getAttribute("level");
			if (level > 5) return "bbs/board-reply";
			else return "empty";
		} else {
			return result;
		}

	}
	
	@RequestMapping(value = "/sReply", method = RequestMethod.GET)
	public String sReply(@RequestParam String b,  @RequestParam String p, @RequestParam String n,
			Locale locale, Model model, HttpSession session, HttpServletRequest request) {

		String result = reply(b, p, n, locale, model, session, request);
		if(result.equals("complete")) {
			int level = (Integer) session.getAttribute("level");
			if (level > 5) return "bbs/study-reply";
			else return "empty";
		} else {
			return result;
		}

	}
	
	@RequestMapping(value = "/pReply", method = RequestMethod.GET)
	public String pReply(@RequestParam String b,  @RequestParam String p, @RequestParam String n,
			Locale locale, Model model, HttpSession session, HttpServletRequest request) {

		String result = reply(b, p, n, locale, model, session, request);
		if(result.equals("complete")) {
			int level = (Integer) session.getAttribute("level");
			if (level > 5) return "bbs/project-reply";
			else return "empty";
		} else {
			return result;
		}

	}
	
	@RequestMapping(value = "/pwCheck", method = RequestMethod.GET)
	public String pwCheckg(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.sendRedirect("/index");
		return "home";
	}
	
	
	@RequestMapping(value = "/pwCheck", method = RequestMethod.POST)
	public String pwCheckp(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String article_password = request.getParameter("at_pass");
		String n = request.getParameter("n");
		/////////////비밀번호 암호화 
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("md5");
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}

		byte[] bytData = article_password.getBytes();
		md.update(bytData);
		byte[] digest = md.digest();
		String strENCData = "";
		for (int i = 0; i < digest.length; i++)
			strENCData = strENCData
					+ Integer.toHexString(digest[i] & 0xFF).toUpperCase();
		strENCData = strENCData.substring(0, 15);
		article_password = strENCData;
		
		/////////////비밀번호 암호화 
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		Board b_dao = context.getBean("BoardDao", Board.class);
		BoardVO vo = b_dao.get_column_password(n);

		if (article_password.equals(vo.getAt_pw())) {
			flag = true;
			return "empty";
		}
		return "signup/at_lock";
	}

	
	@RequestMapping(value = "/cmtWrite", method = RequestMethod.POST)
	public String cmt_write(@RequestParam String p, @RequestParam String b,
			@RequestParam String article_id,
			@RequestParam String comment_writer,
			@RequestParam String comment_data, Locale locale, Model model,
			HttpSession session) {

		if(session.getAttribute("nickname")==null || 
				session.getAttribute("id")== null || 
					session.getAttribute("level")==null) return "signup/login";
		
		CommentVO vo = new CommentVO();
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		Comment c_dao = context.getBean("CommentDao", Comment.class);

		int at_id = 0;

		if (!article_id.equals("") && article_id.trim().length() != 0) {
			at_id = Integer.parseInt(article_id);
		}
		comment_data = comment_data.replaceAll("<", "&lt");
		comment_data = comment_data.replaceAll(">", "&gt");
		comment_data = comment_data.replaceAll("\n", "<br>");
		comment_data = comment_data.replaceAll(" ", "&nbsp;");
		/*
		 * 태그를 지원하지 않음.
		 * 띄어쓰기를 &nbsp;로 치환 삽입.
		 * \n을 <br>로 치환 삽입.
		 */
		vo.setAt_id(at_id);
		vo.setCmt_writer(comment_writer);
		vo.setCmt_data(comment_data);

		vo = c_dao.insert(vo);

		model.addAttribute("comment", vo);
		return "cmt/cmt-write";

	}
	@RequestMapping(value = "/cmtDelete", method = RequestMethod.GET)
	public String cmt_delete(@RequestParam String comment_id, @RequestParam String article_id 
			,Locale locale, Model model, HttpSession session) {

		/*
		 * 
		 * 
		 * 
		 * 여기서도 세션과 cmt작성자가 같은지 확인.
		 */

		if(session.getAttribute("nickname")==null || 
				session.getAttribute("id")== null || 
					session.getAttribute("level")==null) return "signup/login";
		
		String cmt_id = comment_id;
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		Comment c_dao = context.getBean("CommentDao", Comment.class);
		c_dao.comment_delete(cmt_id, article_id);
		return "empty";

	}
	
	@RequestMapping(value = "/fileDown", method = RequestMethod.GET)
	public ModelAndView fileDown(@RequestParam("filePath") String filePath,
			@RequestParam("fileName") String fileName)
			throws UnsupportedEncodingException {
		filePath = new String(filePath.getBytes("8859_1"), "UTF-8");
		fileName = new String(fileName.getBytes("8859_1"), "UTF-8");

		String fullPath = filePath + "/" + fileName;
		System.out.println(fullPath);
		File downloadFile = new File(fullPath);
		return new ModelAndView("download", "downloadFile", downloadFile);
	}
	
	@RequestMapping(value = "/fileDelete", method = RequestMethod.GET)
	public String fileDelete(@RequestParam("article_id") String article_id,
			@RequestParam("file_id") String file_id) {
		
		/*
		 * 여기서 글 작성자와 세션 닉네임이 같나 한번더 확인해야 함.
		 * 
		 * 
		 * */
		int at_id = Integer.parseInt(article_id);
		int f_id = Integer.parseInt(file_id);

		AttachFile db_file = new DaoFactory().AttachFileDao();
		db_file.delete_file(at_id, f_id);
		return "empty";
	}

	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	public String id_check(@RequestParam("id") String id, Locale locale, Model model,
			HttpServletResponse response) throws IOException {
		/*
		 * 
		 * 회원가입시 아이디 유무를 확인함.
		 * Z가 리턴되면 입력된 아이디의 문자열이 이상한 것.
		 * Y는 아이디가 없으므로 회원가입을 해도 됨.
		 * N는 이미 아이디가 존재하므로 회원가입할 수 없음.
		 */
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		Member m_dao = context.getBean("MemberDao", Member.class);
		int num = m_dao.id_check(id);
		if(num==0) model.addAttribute("returning", "Y");		
		else model.addAttribute("returning", "N");
		
		return "index/returning";
	}
	
	@RequestMapping(value = "/nicknameCheck", method = RequestMethod.POST)
	public String nicknameCheck(@RequestParam("nickname") String nickname, Locale locale, Model model,
			HttpServletResponse response) throws IOException {
		/*
		 * 
		 * 회원가입시 닉네임 유무를 확인함.
		 * Z가 리턴되면 입력된 닉네임의 문자열이 이상한 것.
		 * Y는 닉네임이 없으므로 회원가입을 해도 됨.
		 * N는 이미 닉네임이 존재하므로 회원가입할 수 없음.
		 */
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		Member m_dao = context.getBean("MemberDao", Member.class);
		int num 	= m_dao.nickname_check(nickname);
		if(num==0) 	model.addAttribute("returning", "Y"); 
		else model.addAttribute("returning", "N");
		
		return "index/returning";
	}
	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	public String sign_up(Locale locale, Model model, HttpServletRequest request, HttpSession session,
			HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		
		MemberVO vo = new MemberVO();

		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		Member m_dao = context.getBean("MemberDao", Member.class);
		String mem_id= request.getParameter("user-id");
		String mem_pw= request.getParameter("user-pw");
		String mem_name =request.getParameter("user-name");
		String mem_nickname=request.getParameter("user-nickname");
		String mem_email=request.getParameter("user-email");
		String mem_intro=request.getParameter("user-intro");
		
		vo.setUser_id(mem_id);
		vo.setUser_pw(mem_pw);
		vo.setUser_name(mem_name);
		vo.setUser_nickname(mem_nickname);
		vo.setUser_email(mem_email);
		vo.setUser_intro(mem_intro);
		
		if( m_dao.insert(vo) ){ 
			if(session.getAttribute("id")!=null
					|| session.getAttribute("nickname")!=null
						|| session.getAttribute("level")!=null) {
				//세션이 하나라도 남아있으면 초기화 시켜줌
				session.invalidate();
			}
			session.setAttribute("id", mem_id);
			session.setAttribute("nickname", mem_nickname);
			session.setAttribute("level", 3);
			session.setMaxInactiveInterval(60 * 60 * 60);
		} 	
		String url = "/index";
		response.sendRedirect(url);
		return "empty";
	}
	
	
}
