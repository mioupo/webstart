package com.xa3ti.webstart.base.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

public class FolderPath {
	/**
	 * Banner广告图片文件夹/upload/ADFolder
	 */
	public static String ADFolder = "/upload/ADFolder/";
	/**
	 * 新闻图片文件夹/upload/newsImage
	 */
	public static String newsImage = "/upload/newsImage/";
	/**
	 * 二维码文件夹/upload/Qrcode
	 */
	public static String Qrcode = "/upload/Qrcode/";
	/**
	 * 广告图片/upload/loadAD
	 */
	public static String loadAD = "/upload/loadAD/";
	/**
	 * 展商LOGO图片
	 */
	public static String companyLogo = "/upload/companyLogo/";
	/**
	 * 商品展示图片
	 */
	public static String productImg = "/upload/productImg/";
	/**
	 * 运行的项目名/cioe/
	 */
	public static String rootFolder = "";
	/**
	 * 用户图片
	 */
	public static String userphoto = "/upload/userPhoto/";
	/**
	 * 抽奖活动照片
	 */
	public static String luckyDraw="/upload/luckyDraw/";
	
	
	public static String exhibitionMap="/upload/exhibitionMap/";
	/**
	 * 广告图片
	 */
	
	
	public static String update="/upload/update/";
	public static String advertisementImage = "/upload/advertisement/advertisementImage/";
	public static String historyReviewImage = "/upload/historyReview/historyReviewImage/";
	public static String PRIZE_IMAGE = "/upload/prize/prizeImage/";

	/**
	 * 获取上传后的路径
	 * 
	 * @param folderName
	 * @param fileName
	 * @return
	 */
	public static String getFileUploadPath(String folderName, String fileName) {
		return rootFolder + folderName + fileName;
	}

	/**
	 * 获取物理路径
	 * 
	 * @param request
	 * @param path
	 *            相对路径
	 * @return
	 */
	public static String getPhysicalPath(HttpServletRequest request, String path) {
	 String folder=	 request.getSession().getServletContext().getRealPath(path);
	 
	 File f=new File(folder);
			if(!f.exists())
			{
				f.mkdirs();
			}
		return folder;
	}
	/**
	 * 上传文件
	 * @param HttpServletRequest request 
	 * @param MultipartFile file 
	 * @param folder 文件夹路径
	 * @param fileExtName 文件扩展名 .png .jpg
	 * @return
	 * @throws IOException
	 */
	public static String uploadFile(HttpServletRequest request,
			MultipartFile file, String folder, String fileExtName)
			throws IOException {
		
		
		String fn = new Date().getTime()+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		
		if(file != null && !file.isEmpty())
		{
			
			String path = request.getSession().getServletContext().getRealPath(folder);
			
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path,fn));
		} 
		
		return getFileUploadPath(folder, fn);
	}
	
	/**
	 * 上传文件
	 * @param HttpServletRequest request 
	 * @param MultipartFile file 
	 * @param folder 文件夹路径
	 * @param fileExtName 文件扩展名 .png .jpg
	 * @return 上传图片的绝对路径
	 * @throws IOException
	 */
	public static String uploadFileGetPhysicalPath(HttpServletRequest request,
			MultipartFile file, String folder, String fileExtName)
			throws IOException {
		
		String physicalPath="";
		String fn = new Date().getTime()+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		
		if(file != null && !file.isEmpty())
		{
			
			String path = request.getSession().getServletContext().getRealPath(folder);
			File f=new File(path,fn);
			FileUtils.copyInputStreamToFile(file.getInputStream(),f);
			physicalPath=f.getAbsolutePath().toString();
		} 
		
		return physicalPath;
	}
	/**
	 * 上传文件
	 * @param HttpServletRequest request 
	 * @param MultipartFile file 
	 * @param folder 文件夹路径
	 * @return
	 * @throws IOException
	 */
	public static String uploadFile(HttpServletRequest request,
			MultipartFile file, String folder)
			throws IOException {
		
		
		String fn = new Date().getTime()+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		
		if(file != null && !file.isEmpty())
		{
			
			String path = request.getSession().getServletContext().getRealPath(folder);
			
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path,fn));
		} 
		
		return getFileUploadPath(folder, fn);
	}
	/**
	 * 创建日期文件夹
	 * @param request
	 * @return
	 */
	public static String CreateFilePath(HttpServletRequest request,String folderPath)
	{
    	String physicalFolder= FolderPath.getPhysicalPath(request,folderPath);

		String dateFolder=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
		File file = new File(physicalFolder,dateFolder);
		if(!file.exists()&&!file.isDirectory())
		{
			file.mkdirs();
		}
		return file.getAbsolutePath();
	}
	
	/**
	 * 创建UUID文件夹
	 * @param request
	 * @return
	 */
	public static String CreateFilePathUUID(HttpServletRequest request,String folderPath)
	{
    	String physicalFolder= FolderPath.getPhysicalPath(request,folderPath); 
		String uuid=UUID.randomUUID()+"";
		File file = new File(physicalFolder,uuid);
		if(!file.exists()&&!file.isDirectory())
		{
			file.mkdirs();
		}
		return file.getAbsolutePath();
	}
	
	/**
	 * 创建文件夹下的子文件夹
	 * @param folderPath
	 * @param subFolder
	 * @return
	 */
	public static String CreateFilePathUUID(String folderPath,String subFolder)
	{
		File file = new File(folderPath,subFolder);
		if(!file.exists()&&!file.isDirectory())
		{
			file.mkdirs();
		}
		return file.getAbsolutePath();
	}
}
