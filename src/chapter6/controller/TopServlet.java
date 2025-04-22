package chapter6.controller;

import java.util.logging.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import chapter6.logging.InitApplication;


@WebServlet(urlPatterns = { "/index.jsp" })
public class TopServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;


    /**
    * ロガーインスタンスの生成
    */
    Logger log = Logger.getLogger("twitter");

    /**
    * デフォルトコンストラクタ
    * アプリケーションの初期化を実施する。
    */
    public TopServlet() {
        InitApplication application = InitApplication.getInstance();
        application.init();

    }

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws IOException, ServletException {
//
//
//	  log.info(new Object(){}.getClass().getEnclosingClass().getName() +
//        " : " + new Object(){}.getClass().getEnclosingMethod().getName());
//
//      boolean isShowMessageForm = false;
//      User user = (User) request.getSession().getAttribute("loginUser");
//      if (user != null) {
//          isShowMessageForm = true;
//      }
//      List<UserMessage> messages = new MessageService().select();
//      	request.setAttribute("messages", messages);
//      	request.setAttribute("isShowMessageForm", isShowMessageForm);
//        request.getRequestDispatcher("/top.jsp").forward(request, response);
//    }
}