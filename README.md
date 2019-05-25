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
不太明白映射访问地址第一个要加"/"

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



## 问题解决的过程


依据:
```
主要是路径的问题，URL是统一地址定位器，每个页面或者说模块都有一个唯一的地址。但在实际应用时，有两种定位方式。一种是绝对定位，另一种是相对定位。绝对定位就要以/开头，它是以应用程序的根目录作为绝对根（在JS脚本中/是以http://localhost:8080/为绝对根）。相对位置是相对于当前位置的“虚拟目录”而言，跟文件目录结构类似。
```


分析:
```
根据上述的理论,前两个问题可以轻松的解决,以问题二来分析,list.jsp在web下,web就是根目录
```


第四问改为../index.jsp即可





## 仍然有的疑问

```
第三个问题是表单提交的地址,而表单处理的映射访问地址是"/servlet/addPerson"为绝对路径,然而我填上绝对地址却是错误的
第四个问题是我不论是相对地址还是绝对地址都不正确
```





## 问题的最终解决

```
https://github.com/96998/Chapter6_3,李老师我在servlet中的表单处理遇到了点小问题,您能帮我看看吗?问题在README.md中
16:46:26
老师 2019/5/25 16:46:26
你的问题主要是路径的问题，URL是统一地址定位器，每个页面或者说模块都有一个唯一的地址。但在实际应用时，有两种定位方式。一种是绝对定位，另一种是相对定位。绝对定位就要以/开头，它是以应用程序的根目录作为绝对根（在JS脚本中/是以http://localhost:8080/为绝对根）。相对位置是相对于当前位置的“虚拟目录”而言，跟文件目录结构类似。
18:49:54
我 2019/5/25 18:49:54
谢谢老师,按照你的前两个问题解决了,不过后面两个还有些困惑，第三个问题是表单提交的地址,而表单处理的映射访问地址是"servlet/addPerson"为绝对路径,然而我填上绝对地址却是错误的
第四个问题是我不论是相对地址还是绝对地址都不正确

19:13:03
老师 2019/5/25 19:13:03
这个还是地址的问题，当提交之后地址变成servlet/这个子目录下的，这时的index.jsp其实是servlet这个目录的父目录里的文件。因此就会找不到。如果只是这个地方用到的话，可以用"../index.jsp"来解决，如果其他地方也要用到这个页面，那么可以用绝对路径来生成index.jsp的链接。

老师 2019/5/25 19:13:19
要注意分析浏览器地址栏里的地址变化

你撤回了一条消息
19:35:29
我 2019/5/25 19:35:29
[图片],IDEA的根路径是由这里设置的吧?我用根路径导一直到http://localhost:8080

老师 2019/5/25 19:36:23
这个路径不是关键，这里是主机及端口。关键是应用程序的路径及其子路径
19:37:53
我 2019/5/25 19:37:53
[图片],那这里的用根路径的写法应该是什么,我用/index.jsp不对

老师 2019/5/25 19:39:31
/<项目名称>/index.jsp

老师 2019/5/25 19:39:47
但不建议这样用
19:40:30
我 2019/5/25 19:40:30
那其他地方用到这个网页的话也要一个个配置

老师 2019/5/25 19:41:03
嗯，链接改一下就好，不会那么麻烦的
19:42:46
我 2019/5/25 19:42:46
话说,为什么web.xml里的utl-pattern不用/<项目名称>/servelt/addPerson

你撤回了一条消息
19:44:54
老师 2019/5/25 19:44:54
这个配置默认就是以<项目名称>为基目录的，因此没写

我 2019/5/25 19:46:43
这样子我就理解了,我一开始以为他们的根目录全都是localhost:8080,谢谢老师耐心帮我解决这个有点钻牛角尖的问题,很抱歉打搅您那么久
19:47:30
老师 2019/5/25 19:47:30
/呲牙没事的，刚开始接触难免会困惑

```



### 归纳

```
在web.xml中的pattern-url的配置,其根目录默认为localhost:8080/工程名/,而web目录下的jsp文件的根目录为localhost:8080,所以我的/index.jsp会错,观察下url的变化,会发现实际上index.jsp在当前网站的父目录下,所有可以用../index.jsp或者根目录/Chapter6_3_war_exploded/index.jsp,不建议用根目录,到此问题彻底解决
```

