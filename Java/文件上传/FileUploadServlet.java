package com.sc.web.shopcart;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;

import com.sc.utils.FileUpload;

public class FileUploadServlet extends HttpServlet{

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		DiskFileUpload dfu = new DiskFileUpload();
		String forwardURI = "upload_image.jsp";
		try {
			dfu.setFileSizeMax(4194304);
			List<FileItem> list = dfu.parseRequest(req);
			for (FileItem file : list) {
				String imgName = file.getName();
				if(!imgName.endsWith(".jpg")) {
					req.setAttribute("prompt", "你上传的图片不符合要求,请检查后再次上传");
					forwardURI = "upload_image.jsp";
					break;
				}
				else {
					FileUpload fileUpload = new FileUpload(file);
					HttpSession session = req.getSession(false);
					if(session != null){
						boolean flag = fileUpload.doFileUpload(req);
						if(flag) {
							forwardURI = "zhanghuziliao.jsp";
						}
						else {
							req.setAttribute("prompt", "上传失败了");
							forwardURI = "upload_image.jsp";
						}
					}
					else {
						req.setAttribute("prompt", "非法用户");
						forwardURI = "upload_image.jsp";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("prompt", "文件过大，限制大小为4M");
			forwardURI = "upload_image.jsp";
		}
		req.getRequestDispatcher(forwardURI).forward(req, resp);
	}
}
