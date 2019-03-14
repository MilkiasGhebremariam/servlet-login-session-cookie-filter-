import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.Map.Entry;
@WebServlet("/login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;



    private  boolean checkUser(Iterator userPasswords, String username, String password) {
        while(userPasswords.hasNext())
        {
            Entry<Integer, UserD> mappairs = (Entry<Integer, UserD>)userPasswords.next();
            if(username.equals(mappairs.getValue().getName())
                    && password.equals(mappairs.getValue().getPassword()))
                return true;

        }
        return false;
    }

    @Override
    public void init() throws ServletException {
        // TODO Auto-generated method stub
        //super.init();
        UserData.addUsers();

    }
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // super.doGet(req, resp);
        String userName = null;
        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("username")) {
                userName = cookie.getValue();
                break;}
        }
        if(userName != null) {
            req.setAttribute("uname", userName);
            req.setAttribute("rememberme", "checked");
        }
        req.getRequestDispatcher("login-form.jsp").forward(req, resp);
        //resp.sendRedirect("login-form.jsp");
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // super.doPost(req, resp);
        String username = req.getParameter("user_name");
        String password = req.getParameter("password");
        Boolean rememberme = req.getParameter("rememberme")!= null;
        //check if user exist and if password is pass
        UserD loggedInUser = UserData.checkUser(username, password);
        if(UserData.hasLoggedIn()) {
            HttpSession session = req.getSession(true);
            session.setAttribute("CurrentUser", loggedInUser);
            if(rememberme) {
                //adding cookie
                Cookie cookie = new Cookie("username", loggedInUser.getName());
                cookie.setMaxAge(2578000);
                cookie.setPath("/");
                resp.addCookie(cookie);
            }
            else {
                //removing cookie
                Cookie cookie = new Cookie("username", null);
                cookie.setMaxAge(-1);
                resp.addCookie(cookie);
            }
        }
        else
            resp.sendRedirect("login-form.jsp");


    }
    }

