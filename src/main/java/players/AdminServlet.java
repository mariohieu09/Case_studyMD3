package players;

import DAO.PlayerDAO;
import DAO.UserDAO;
import model.Player;
import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AdminServlet", value = "/Admin")
public class AdminServlet extends HttpServlet {
    private PlayerDAO playerDAO;
    private UserDAO userDAO;
    public void init(){
        playerDAO = new PlayerDAO();
        userDAO = new UserDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "login":
                login(request, response);
                break;
            case "register":
                register(request, response);
                break;
            case "playerRegister":
                playerRegister(request, response);
                break;
            default:
                welcome(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "createUser":
                createUser(request, response);
                break;
            case "createPlayer":
                createPlayer(request, response);
                break;
            case "login":
                if(confirmLogin(request, response)){
                    ListPlayer(request, response);
                };
                break;


        }
    }

    private void welcome(HttpServletRequest request, HttpServletResponse response){
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher("Welcome/welcome.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void ListPlayer(HttpServletRequest  request, HttpServletResponse response){
        List<Player> list = playerDAO.selectAllPlayer();
        try{
            request.setAttribute("players", list);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Home/homePage.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void createUser(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String Username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirm");
        if(password.equals(confirm)){
          try {
              User user = new User(name, age, Username, password);
              userDAO.insertUser(user);
              String message = "Welcome " + name;
              RequestDispatcher dispatcher = request.getRequestDispatcher("Home/homePage.jsp");
              request.setAttribute("message", message);
              dispatcher.forward(request, response);
          } catch (ServletException e) {
              throw new RuntimeException(e);
          } catch (IOException e) {
              throw new RuntimeException(e);
          } catch (SQLException e) {
              throw new RuntimeException(e);
          }
        }
    }
    private boolean confirmLogin(HttpServletRequest request, HttpServletResponse response){
        boolean confirm = false;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(password.equals(userDAO.selectUser(username))){
                confirm = true;
        }else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("Login/login.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return confirm;
    }
    private void login(HttpServletRequest request, HttpServletResponse response){
        try{
           RequestDispatcher dispatcher = request.getRequestDispatcher("Login/login.jsp");
           dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void register(HttpServletRequest request, HttpServletResponse response){
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher("Register/Register.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void playerRegister(HttpServletRequest request, HttpServletResponse response){
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher("Register/playerRegister.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void createPlayer(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        int age = request.getIntHeader("age");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String games = request.getParameter("games");
        try{
            Player player = new Player(name, age, username, password, games);
            playerDAO.insertPlayer(player);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Welcome/welcome.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
