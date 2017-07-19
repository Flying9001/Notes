Struts2使用jquery ajax返回json数据

操作步骤：
1.配置web.xml
2.创建Action
3.配置struts.xml
4.创建页面(jsp/html)
5.引入jquery&测试

    关于使用Struts2操作json需要导入的jar(不包含Struts2核心jar)：
    commons-beanutils-1.8.0.jar
    commons-collections-3.2.2.jar
    commons-lang-2.4.jar
    commons-logging-1.2.jar
    ezmorph-1.0.6.jar
    json-lib-2.3-jdk15.jar
    struts2-json-plugin-2.3.31.jar
    注意:commons-lang-xx.jar不同于 commons-lang3-xx.jar

1.配置web.xml 
    web.xml:
    <?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd" id="WebApp_ID" version="3.0">
  <display-name>CampusStore</display-name>
  <welcome-file-list>
    <welcome-file>homepage.jsp</welcome-file>
  </welcome-file-list>
  <filter>
    <filter-name>struts2</filter-name>
    <filter-class>org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter</filter-class>
  </filter>
  <filter-mapping>
    <filter-name>struts2</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
</web-app>

2.创建Action
    ActionTest.java:
package com.cs.demo.action;
import java.util.HashMap;
import java.util.Map;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;
/**
 * @author lucien
 *
 */
public class ActionTest extends ActionSupport{
    private static final boolean DBG = true;
    
    private String result;
    private String account;
    private String passcode;
    
    public String ajaxTest() throws Exception {
        try {
            if(DBG){System.out.println("Account:" + account + ",passcode:" +passcode);}
//      Map存放数据
            Map<String, String> map = new HashMap<String,String>();
            map.put("account",account);
            map.put("passcode", passcode);
//      转换为json数据
            JSONObject json = JSONObject.fromObject(map);
//      json转String
            result = json.toString();
            if(DBG){System.out.println("result: " + result);}
        } catch (Exception e) {
            // TODO: handle exception
        }

        return SUCCESS;
    }

    /**
    *  此处省略 set get 方法 
    */
}

3.配置struts.xml
    struts.xml:
    <?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE struts PUBLIC
    "-//Apache Software Foundation//DTD Struts Configuration 2.3//EN"
    "http://struts.apache.org/dtds/struts-2.3.dtd">

<struts>
    <!-- 开发者模式 -->
    <constant name="struts.devMode" value="true" />
    <!-- 解决乱码问题 -->
    <constant name="struts.i18n.encoding" value="utf-8"/>
    <!-- 使用json数据传输时必须package需继承 json-default,可以继承多个包(有别于Java) -->
    <package name="test" namespace="/test" extends="struts-default,json-default">
        <action name="ajaxTest" class="com.cs.demo.action.ActionTest" method="ajaxTest">
            <result type="json">
                <param name="root">result</param>
            </result>   
        </action>
    </package>
</struts>

4.创建页面(jsp/html)
5.引入jquery&测试
    ajaxTest.jsp:
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + 
        request.getServerPort() + path + "/";   
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.11.3.js"></script>
<title>Insert title here</title>
</head>
<body>
    <form action="test/ajaxTest" method="post">
        姓名:<input type="text" id="account" name="account"/>
        密码:<input type="password" id="passcode" name="passcode"/>
        <input type="submit" value="提交"/>
    </form>
</body>
<script type="text/javascript">
    $(document).ready(function() {
        alert("ajaxTest!");
        $("form").submit(function() {
            alert("ajaxTest!");
            var form = $(this);
            $.ajax({
                type: form.attr('method'),
                url: "test/ajaxTest",
                data:{
                    account: $("input[name=account]").val(),
                    passcode: $("input[name=passcode]").val()
                },
                dataType: "json",
                cache: false,
                success: function(data){
                    alert("Success!");
                    alert(data);
                },
                error: function(){
                    alert("Network Error..." );
                }
            });
        });
        
    });
</script>
</html>









