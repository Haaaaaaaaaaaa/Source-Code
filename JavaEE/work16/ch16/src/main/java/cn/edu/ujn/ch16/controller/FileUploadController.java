package cn.edu.ujn.ch16.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/*
文件上传
*/
@Controller
public class FileUploadController {
	// 执行文件上传
	@RequestMapping("/fileUpload")
	public String handleFormUpload(@RequestParam("name") String name,
			@RequestParam("uploadfile") List<MultipartFile> uploadfile, HttpServletRequest request) {
		// 判断文件是否存在
		if (!uploadfile.isEmpty() && uploadfile.size() > 0) {
			// 循环输出上传的文件
			for (MultipartFile file : uploadfile) {
				// 获取上传文件的原始名称
				String originalFilename = file.getOriginalFilename();
				// 设置上传文件的保存地址目录
				String dirPath = request.getServletContext().getRealPath("/upload/");
				File filePath = new File(dirPath);
				// 如果保存文件的地址不存在，就先创建目录
				if (!filePath.exists()) {
					filePath.mkdirs();
				}
				// 使用UUID重新命名上传的文件名称（上传入_uuid_原始文件名）
				String newFilename = name + "_" + UUID.randomUUID() + "_" + originalFilename;
				try {
					// 使用MultipartFile接口的方法完成上传文件到指定位置
					file.transferTo(new File(dirPath + newFilename));
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					return "error";
				}

			}
			// 跳转到成功页面
			return "success";
		} else {
			return "error";
		}
	}

	// 文件下载
	@RequestMapping("/download")
	// ResponseEntuty对象类似于@ResponseBody注解，用于直接返回结果对象
	public ResponseEntity<byte[]> fileDownload(HttpServletRequest request, String filename) throws Exception {
		// 指定要下载的文件所在路径
		String path = request.getServletContext().getRealPath("/upload/");
		// 创建该文件对象
		File file = new File(path + File.separator + filename);
		// 对文件名编码，防止中文文件乱码
		filename = this.getFilename(request, filename);
		// 设置响应头
		HttpHeaders headers = new HttpHeaders();
		// 通知浏览器以下载的方式打开文件
		headers.setContentDispositionFormData("attachment", filename);
		// MediaType代表的是Internet Media
		// Type（即互联网媒体类型），MediaType.APPLICATION_OCTET_STREAM的值为application/octet-stream,即表示以二进制流的形式下载返回文件数据
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		// 使用Sring MVC框架的ResponseEntity对象封装返回下载数据
		// HttpStatus.OK 表示200，即服务器已成功处理了请求
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
	}

	/**
	 * 根据浏览器的不同进行编码设置，返回编码后的文件名
	 */
	public String getFilename(HttpServletRequest request, String filename) throws Exception {
		// IE不同版本User-Agent中出现的关键词
		String[] IEBrowserKeyWords = { "MSIE", "Trident", "Edge" };
		// 获取请求头代理信息
		String userAgent = request.getHeader("User-Agent");
		// 对不同浏览器进行不同格式的转码
		for (String keyWord : IEBrowserKeyWords) {
			if (userAgent.contains(keyWord)) {
				// IE内核浏览器，统一为UTF-8编码显示
				return URLEncoder.encode(filename, "UTF-8");
			}
		}
		// 火狐等其它浏览器统一为ISO-8859-1编码显示
		return new String(filename.getBytes("UTF-8"), "ISO-8859-1");
	}

}
