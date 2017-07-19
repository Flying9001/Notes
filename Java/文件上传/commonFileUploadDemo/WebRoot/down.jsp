<%@ page language="java" import="java.util.*,java.io.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'down.jsp' starting page</title>
  </head>
  
  <body>
  <%
  String root=application.getRealPath("/")+"upload\\";
   
   String name="xxx项目开发计划.xls";
   
   String path=root+name;
   
   		response.setHeader("Content-Disposition", "attachment;filename=" + new String(name.getBytes("gbk"),"ISO8859-1"));
		response.setContentType("application/x-download");
		try {
			OutputStream os = response.getOutputStream();
			FileInputStream fis = new FileInputStream(path);
			byte[] b = new byte[1024];
			int i = 0;
			while ((i = fis.read(b)) > 0) {
				os.write(b, 0, i);
			}
			fis.close();
			os.flush();
			os.close();
		} catch (Exception e) {
			
		}
		//清除冲突
  		 out.clear();
		out = pageContext.pushBody();  
   
   %>
  </body>
</html>
