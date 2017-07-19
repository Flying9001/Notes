package com.sc.utils;

import java.io.File;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;

import com.sc.dao.Users_Dao;
import com.sc.daoImpl.shopcart.Users_DaoImpl;
import com.sc.entity.Users;

/**
 * 对用户头像上传进行处理
 * @author xushuang
 *
 */
public class FileUpload {
	private FileItem file = null;
	public FileUpload(FileItem file) {
		this.file = file;
	}
	
	public boolean doFileUpload(HttpServletRequest request) {
		boolean isUpload = true;
		String rootPath = request.getRealPath("");
		File dict = new File(rootPath+"/img");
		if(!dict.exists()) {
			dict.mkdir();
		}
		String upFileName = file.getName();
		String dir = dict.getPath();
		String houzui=upFileName.substring(upFileName.lastIndexOf("."),upFileName.length());
		String filename=GetDate_Util.getDHSMS()+houzui; 
		while(new File(dir,"/"+filename).exists()){
			try {
				Thread.sleep(20);
				filename=GetDate_Util.getDHSMS()+houzui;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			file.write(new File(dir,"/"+filename));
			HttpSession session = request.getSession();
			Users user = (Users) session.getAttribute("user");
			user.setImg("img"+"\\"+filename);
			session.setAttribute("user", user);
			insertFilePathToDB("img"+"\\"+filename,user);
		} catch (Exception e) {
			e.printStackTrace();
			isUpload = false;
		}
		return isUpload;
	}
	
	private void insertFilePathToDB(String filePath,Users user){
		Users_Dao userDao = new Users_DaoImpl();
		String sql = "update Users set imgURL = ? where userOnly = ?";
		String[] parameters  = {filePath,user.getUserOnly().toString()};
		userDao.update_Users(sql, parameters);
	}
}
