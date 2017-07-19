<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>My JSP 'upload.jsp' starting page</title>
    
  </head>
  
  <body>
    <form action="uploadDo.jsp" method="post" enctype="multipart/form-data">
    	<input type="text" name="userNmae"/>
    	<input type="checkbox" name="userNmae2"/>
    	<input type="file" name="files">
    	<br/>
    	<input type="submit" value="上传"/>
    </form>
  </body>
</html>
