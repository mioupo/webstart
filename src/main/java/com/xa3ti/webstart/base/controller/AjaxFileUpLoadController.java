package com.xa3ti.webstart.base.controller;

//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//import java.util.UUID;
//
//import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

//import com.alibaba.fastjson.JSON;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
//import com.xa3ti.webstart.base.entity.XaResult;
import com.xa3ti.webstart.base.util.UserSettingConfig;


@Controller
@RequestMapping("/ajaxFileUpLoadController")
public class AjaxFileUpLoadController {
	
	@Autowired
	private UserSettingConfig userSettingConfig;
	
//	@Autowired
//	private PhotosService photosService;
	

	//上传文件类型
	private static final List<String> fileExt=new ArrayList<String>();
	static{
		fileExt.add(".JPG");
		fileExt.add(".PNG");
		fileExt.add(".JPEG");
		fileExt.add(".BMP");
		fileExt.add(".GIF");
	}
	/**
	 * @Title: upload
	 * @Description: 上传图片
	 * @param file
	 * @param request
	 * @return    
	 */
	@ApiOperation(value="上传图片",notes="上传图片")
	@ResponseBody
	@RequestMapping(value="photo",method=RequestMethod.POST)
	public String upload1(@ApiParam(value="要上传的图片") @RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(required=false) Integer width,@RequestParam(required=false) Integer height,
			HttpServletRequest request){
//		String root=request.getSession().getServletContext().getRealPath("/");
//		String picturePath =userSettingConfig.getPicture_path();
//		String ext =file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
//		String newName = UUID.randomUUID().toString()+ext;
//		File targetFile=new File(root+picturePath+newName);
//		XaResult<Photos> xr=new XaResult<Photos>();
//		//检查文件类型
//		if(!fileExt.contains(ext.toUpperCase())){
//			xr.setCode(XaConstant.Code.error);
//			xr.setMessage("上传图片失败，文件类型为：jpg、gif、png、jpeg、bmp");
//			
//			return JSON.toJSONString(xr);
//		}
//		//如果图片有宽高要求，检查图片的宽高
//		if(XaUtil.isNotEmpty(width) && XaUtil.isNotEmpty(height)){			
//			try {
//				BufferedImage image=ImageIO.read(file.getInputStream());
//				int _width = image.getWidth();
//				int _height = image.getHeight();
//				System.out.println(width+"    "+height);
//				if(width!=_width || height!=_height){
//					xr.setMessage("上传的图片规则不符，宽："+width+" 高："+height+" 请重新上传!");
//					xr.setCode(XaConstant.Code.error);
//					return JSON.toJSONString(xr);
//				}
//			} catch (IOException e1) {
//				xr.setCode(XaConstant.Code.error);
//				xr.setMessage("图片上传失败:"+e1.getMessage());
//				return JSON.toJSONString(xr);
//			}
//		}
//		try {
//			file.transferTo(targetFile);
//			Photos photos=new Photos();
//			photos.setImgUrl(picturePath+newName);
//			photos.setStatus(XaConstant.Status.valid);
//			xr =photosService.createPhoto(photos);
//			return JSON.toJSONString(xr);
//		} catch (IllegalStateException e) {
//			xr.setCode(XaConstant.Code.error);
//			xr.setMessage("图片上传失败:"+e.getMessage());
//		} catch (IOException e) {
//			xr.setCode(XaConstant.Code.error);
//			xr.setMessage("图片上传失败:"+e.getMessage());
//		}
		//return JSON.toJSONString(xr);
		return "";
	}
	
	/**
	 * @Title: download
	 * @Description: 根据ID和type查询photos
	 * @param id
	 * @param type
	 * @return    
	 */
//	@ApiOperation(value="下载图片",notes="下载图片")
//	@ResponseBody
//	@RequestMapping(method=RequestMethod.GET)
//	public XaResult<Photos> download(@RequestParam(required=true) Long id,@RequestParam(required=true) int type){
//		XaResult<Photos> xr=photosService.findPhotoListByContentId(id, type);
//		return xr;
//	}
//	

	/**
	 * @Title: upload
	 * @Description: 图片上传
	 * @param file  要上传的图片
	 * @param width 如果需要检查图片的宽度，就需要传入相应的宽度，当宽度不限制的话，可以不传该参数
	 * @param height 如果需要检查图片的高度，就需要传入相应的高度，当高度不限制的话，可以不传该参数
	 * @param request
	 * @return    
	 */
//	@ResponseBody
//	@RequestMapping(method=RequestMethod.POST)
//	public String upload(@RequestParam(value = "file", required = false) MultipartFile file,@RequestParam(required=false) Integer width,@RequestParam(required=false) Integer height, HttpServletRequest request){
//		String root=request.getSession().getServletContext().getRealPath("/");
//		String picturePath =userSettingConfig.getPicture_path();
//		String ext =file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
//		String newName = UUID.randomUUID().toString()+ext;
//		File targetFile=new File(root+picturePath+newName);
//		XaResult<String> xr=new XaResult<String>();
//		//检查文件类型
//		if(!fileExt.contains(ext.toUpperCase())){
//			xr.setCode(XaConstant.Code.error);
//			xr.setMessage("上传图片失败，文件类型为：jpg、gif、png、jpeg、bmp、");
//			return JSON.toJSONString(xr);
//		}
		
		//如果图片有宽高要求，检查图片的宽高
//		if(XaUtil.isNotEmpty(width) && XaUtil.isNotEmpty(height)){			
//			try {
//				BufferedImage image=ImageIO.read(file.getInputStream());
//				int _width = image.getWidth();
//				int _height = image.getHeight();
//				System.out.println(width+"    "+height);
//				if(width!=_width || height!=_height){
//					xr.setMessage("上传的图片规则不符，宽："+width+" 高："+height+" 请重新上传!");
//					xr.setCode(XaConstant.Code.error);
//					return JSON.toJSONString(xr);
//				}
//			} catch (IOException e1) {
//				xr.setCode(XaConstant.Code.error);
//				xr.setMessage("图片上传失败:"+e1.getMessage());
//				return JSON.toJSONString(xr);
//			}
//		}
//		try {
//			file.transferTo(targetFile);
//			xr.setObject(picturePath+newName);
//		} catch (IllegalStateException e) {
//			xr.setCode(XaConstant.Code.error);
//			xr.setMessage("图片上传失败:"+e.getMessage());
//		} catch (IOException e) {
//			xr.setCode(XaConstant.Code.error);
//			xr.setMessage("图片上传失败:"+e.getMessage());
//		}
//		return JSON.toJSONString(xr);
//	}
//	
	
}
