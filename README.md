#自己尝试的servlet提交表单

##主要的知识点

### Myservlet的doPost()和doGet()方法
```aidl
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
        req.getRequestDispatcher("/list.jsp").forward(req, resp);   //请求转发
    }
```




##  遇到的问题


### 1.在web.xml中urlpattern设置少一个斜杠无法正常启动

![null](pic/Snipaste_2019-05-25_16-08-27.png)


### 2.与第一个问题有点类似,问题在Myservlet.java中

![null](pic/Snipaste_2019-05-25_16-14-06.png)

####少个"/"
![null](pic/Snipaste_2019-05-25_16-12-50.png)

####加上"/"
![null](pic/Snipaste_2019-05-25_16-16-55.png)


###第三个问题也类似
在index.jsp中
![null](pic/Snipaste_2019-05-25_16-18-01.png)

###第四个问题
在list.jsp中
![null](pic/Snipaste_2019-05-25_16-20-15.png)

这个跳转的路径不对,怎么修改,在我参考的样例里这样可以跳转
[参考的样例](https://github.com/96998/Chapter6_2)
![null](pic/Snipaste_2019-05-25_16-21-24.png)


**主要想知道"/"有什么意义,什么时候该加"/"和不加"/",有什么意义**
**Thank you**