package servlet;

import person.Person;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Jiajiason
 * @date 2019-05-25 14:52
 */
public class Myservlet extends HttpServlet {
    public Myservlet() {
        super();
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        Person person = new Person();
        String name = req.getParameter("name");
//        int age = Integer.parseInt(req.getParameter("age"));
        String ageStr = req.getParameter("age");
        int age = 0;
        String regex = "^\\+?[1-9][0-9]*$";        // 正则表达式
        if (ageStr.matches(regex))                   //检验age
            age = Integer.parseInt(ageStr);
        person.setName(name);
        person.setAge(age);
        ServletContext servletContext = getServletContext();   //获取ServletContext对象
        //        List<Person> all = new ArrayList<Person>();
/**
 * 上面注释掉的原因是all是用来存储所有的人的,如果按照上面的代码,则就最多存储一个人
 */
        List<Person> all = (List<Person>) servletContext.getAttribute("person");
        if (all == null) {
            all = new ArrayList<Person>();
        }
        all.add(person);
        servletContext.setAttribute("person", all);
        req.getRequestDispatcher("/list.jsp").forward(req, resp);       //请求转发
//        req.getRequestDispatcher("list.jsp").forward(req, resp);
//        这里的少个"/"就无法正常跳转
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        this.doPost(req,resp);
    }
}
