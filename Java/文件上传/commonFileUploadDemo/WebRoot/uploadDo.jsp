<%@ page language="java" import="java.util.*,java.io.*" pageEncoding="UTF-8"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>My JSP 'uploadDo.jsp' starting page</title>
    
  </head>
  
  <body>
    <%
	//获取磁盘文件条目工厂
  	DiskFileItemFactory factory=new DiskFileItemFactory();
	//高水平的API文件上传处理
  	ServletFileUpload upload=new ServletFileUpload(factory);
  	//设置字体编码格式
  	upload.setHeaderEncoding("UTF-8");
	//设置文件上传的路径  	
  	String root=application.getRealPath("/")+"upload\\";
  	try{
		//可以上传多个文件
  		List items=upload.parseRequest(request);
  		//iterator迭代器，可以实现遍历，与while搭配使用
		Iterator it=items.iterator();
  		while(it.hasNext()){
  			FileItem item=(FileItem)it.next();
  			if(item.isFormField()){
				//这里没有对表单提交的简单字符串进行处理
  			}else{
				//这里是对表单提交的非简单字符串(二进制的数据，如图片，视频，文件等)进行处理
  				if(item.getName()!=null&&!"".equals(item.getName())){
  					//截取上传文件的字符串名字,+1是为了去掉反斜杠
					String fileName=item.getName();
  					fileName = fileName.substring(fileName.lastIndexOf("\\") + 1,fileName.length());
  					File tempFile=new File(fileName);
  					File file=new File(root+fileName);
  					item.write(file);
  				}
  			}
  		}
  	}catch(Exception e){
  		e.printStackTrace();
  	}
     %>
  </body>
</html>
