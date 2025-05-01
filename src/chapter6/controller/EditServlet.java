package chapter6.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import chapter6.beans.Message;
import chapter6.beans.User;
import chapter6.logging.InitApplication;
import chapter6.service.MessageService;

@WebServlet(urlPatterns = { "/edit" })
public class EditServlet extends HttpServlet {

	/**
	* ロガーインスタンスの生成
	*/
	Logger log = Logger.getLogger("twitter");

	/**
	* デフォルトコンストラクタ
	* アプリケーションの初期化を実施する。
	*/
	public EditServlet() {
		InitApplication application = InitApplication.getInstance();
		application.init();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		log.info(new Object() {
		}.getClass().getEnclosingClass().getName() +
				" : " + new Object() {
				}.getClass().getEnclosingMethod().getName());

		HttpSession session = request.getSession();
		List<String> errorMessages = new ArrayList<String>();

		User user = (User) session.getAttribute("loginUser");

		String id = request.getParameter("id");

		//空白やスペースはエラー
		if (StringUtils.isBlank(id)) {
			errorMessages.add("不正なパラメータが入力されました");

		session.setAttribute("errorMessages", errorMessages);
		response.sendRedirect("./");

		return;
		}

		//数字以外はエラー
		if (id.matches("^[^0-9]+$")) {
			errorMessages.add("不正なパラメータが入力されました");

		session.setAttribute("errorMessages", errorMessages);
		response.sendRedirect("./");

		return;
		}


		//ログイン中のユーザーがつぶやいたもの以外のidを入力したらエラー

		if (id != userId) {
			errorMessages.add("不正なパラメータが入力されました");

		session.setAttribute("errorMessages", errorMessages);
		response.sendRedirect("./");

		return;
		}


		int messageId = Integer.parseInt(id);
		Message message = new MessageService().select(messageId);




		request.setAttribute("messages", message);
		request.getRequestDispatcher("/edit.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		log.info(new Object() {
		}.getClass().getEnclosingClass().getName() +
				" : " + new Object() {
				}.getClass().getEnclosingMethod().getName());

		HttpSession session = request.getSession();
		List<String> errorMessages = new ArrayList<String>();

		//リクエストから値を取り出している
		String text = request.getParameter("text");
		String id = request.getParameter("id");

		if (!isValid(text, errorMessages)) {
			session.setAttribute("errorMessages", errorMessages);
			response.sendRedirect("/edit");
			return;
		}
		//テキストとidをメッセージという変数に格納したい
		Message message = new Message();
		message.setText(text);

		int messageId = Integer.parseInt(id);
		message.setId(messageId);

		//MessageServiceを呼び出している
		new MessageService().update(message);
		response.sendRedirect("./");
	}

	//有効かどうか判断
	private boolean isValid(String text, List<String> errorMessages) {

		log.info(new Object() {
		}.getClass().getEnclosingClass().getName() +
				" : " + new Object() {
				}.getClass().getEnclosingMethod().getName());

		if (StringUtils.isBlank(text)) {
			errorMessages.add("メッセージを入力してください");
		} else if (140 < text.length()) {
			errorMessages.add("140文字以下で入力してください");
		}

		if (errorMessages.size() != 0) {
			return false;
		}
		return true;
	}
}
