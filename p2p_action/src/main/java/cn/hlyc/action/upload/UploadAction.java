package cn.hlyc.action.upload;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.hlyc.action.common.BaseAction;
import cn.hlyc.action.filter.GetHttpResponseHeader;
import cn.hlyc.cache.BaseCacheService;
import cn.hlyc.domain.user.UserModel;
import cn.hlyc.service.user.IUserService;
import cn.hlyc.utils.CutImg;
import cn.hlyc.utils.FrontStatusConstants;
import cn.hlyc.utils.FtpUtil;
import cn.hlyc.utils.ImageHelper;
import cn.hlyc.utils.Response;

public class UploadAction extends BaseAction {

	@Autowired
	private IUserService userService;

	@Autowired
	private BaseCacheService baseCacheService;

	private File uploadImage; // 得到上传的文件
	private String uploadImageContentType; // 得到文件的类型
	private String uploadImageFileName; // 得到文件的名称

	public void upload() {

		try {

			HttpServletRequest request = ServletActionContext.getRequest();
			
			String filename = uploadImageFileName.substring(0,uploadImageFileName.lastIndexOf("."));

			String userId = request.getParameter("userId");
			String tpname = userId + "_" + filename + ""+".jpg";
			String tpname2 = "fnw"+userId + "_" + filename +".jpg";
			// 获取要保存文件夹的物理路径(绝对路径)
			if (userId != null) {

				FileInputStream in = new FileInputStream(uploadImage);
				boolean flag = FtpUtil.uploadFile("192.168.50.128", 21, "tptest", "123", "/home/tptest/", "/touxiang",
						tpname2 , in);

				if (flag == true) {

					this.getResponse().getWriter()
							.write(Response.build().setStatus(FrontStatusConstants.SUCCESS)
									.setData("http://192.168.50.128/touxiang/" + tpname2)
									.toJSON());

				} else {
					this.getResponse().getWriter()
							.write(Response.build().setStatus(FrontStatusConstants.BREAK_DOWN).toJSON());
				}

			} else {
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.BREAK_DOWN).toJSON());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.BREAK_DOWN).toJSON());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	public void cut() {
		try {
			System.out.println("============开始裁剪=============");
			HttpServletRequest request = ServletActionContext.getRequest();

			Integer x = Integer.parseInt(request.getParameter("x"));
			Integer y = Integer.parseInt(request.getParameter("y"));
			Integer w = Integer.parseInt(request.getParameter("w"));
			Integer h = Integer.parseInt(request.getParameter("h"));
			String imgPath = request.getParameter("imgPath");
			String realImgName = imgPath.substring(31);
			String realImgName2 = imgPath.substring(34);
			System.out.println(realImgName2);
			String userId = request.getParameter("userId");

			String path = request.getSession().getServletContext().getRealPath("img");
			
		
			boolean downloadFile = FtpUtil.downloadFile("192.168.50.128", 21, "tptest", "123", "/home/tptest/touxiang",
					realImgName, path);
			if (downloadFile == true) {
				ImageHelper.zoomImage(path +File.separator+realImgName,
						path + File.separator+realImgName2, 500, 500);
				ImageHelper.cutImage(path + File.separator+realImgName2,
						path + File.separator+realImgName2, x, y, w, h);

				FileInputStream in = new FileInputStream(
						path + File.separator+realImgName2);
				boolean flag = FtpUtil.uploadFile("192.168.50.128", 21, "tptest", "123", "/home/tptest/", "/touxiang",
						realImgName2, in);
				if (flag == true) {
					userService.updateImg(realImgName2,
							Integer.parseInt(userId));

					this.getResponse().getWriter()
							.write(Response.build().setStatus(FrontStatusConstants.SUCCESS)
									.setData("http://192.168.50.128/touxiang/" + realImgName2)
									.toJSON());

				} else {
					this.getResponse().getWriter()
							.write(Response.build().setStatus(FrontStatusConstants.BREAK_DOWN).toJSON());
				}
			} else {
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.BREAK_DOWN).toJSON());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public File getUploadImage() {
		return uploadImage;
	}

	public void setUploadImage(File uploadImage) {
		this.uploadImage = uploadImage;
	}

	public String getUploadImageContentType() {
		return uploadImageContentType;
	}

	public void setUploadImageContentType(String uploadImageContentType) {
		this.uploadImageContentType = uploadImageContentType;
	}

	public String getUploadImageFileName() {
		return uploadImageFileName;
	}

	public void setUploadImageFileName(String uploadImageFileName) {
		this.uploadImageFileName = uploadImageFileName;
	}
}
