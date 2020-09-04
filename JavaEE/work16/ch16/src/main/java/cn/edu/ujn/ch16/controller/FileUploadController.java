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
�ļ��ϴ�
*/
@Controller
public class FileUploadController {
	// ִ���ļ��ϴ�
	@RequestMapping("/fileUpload")
	public String handleFormUpload(@RequestParam("name") String name,
			@RequestParam("uploadfile") List<MultipartFile> uploadfile, HttpServletRequest request) {
		// �ж��ļ��Ƿ����
		if (!uploadfile.isEmpty() && uploadfile.size() > 0) {
			// ѭ������ϴ����ļ�
			for (MultipartFile file : uploadfile) {
				// ��ȡ�ϴ��ļ���ԭʼ����
				String originalFilename = file.getOriginalFilename();
				// �����ϴ��ļ��ı����ַĿ¼
				String dirPath = request.getServletContext().getRealPath("/upload/");
				File filePath = new File(dirPath);
				// ��������ļ��ĵ�ַ�����ڣ����ȴ���Ŀ¼
				if (!filePath.exists()) {
					filePath.mkdirs();
				}
				// ʹ��UUID���������ϴ����ļ����ƣ��ϴ���_uuid_ԭʼ�ļ�����
				String newFilename = name + "_" + UUID.randomUUID() + "_" + originalFilename;
				try {
					// ʹ��MultipartFile�ӿڵķ�������ϴ��ļ���ָ��λ��
					file.transferTo(new File(dirPath + newFilename));
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					return "error";
				}

			}
			// ��ת���ɹ�ҳ��
			return "success";
		} else {
			return "error";
		}
	}

	// �ļ�����
	@RequestMapping("/download")
	// ResponseEntuty����������@ResponseBodyע�⣬����ֱ�ӷ��ؽ������
	public ResponseEntity<byte[]> fileDownload(HttpServletRequest request, String filename) throws Exception {
		// ָ��Ҫ���ص��ļ�����·��
		String path = request.getServletContext().getRealPath("/upload/");
		// �������ļ�����
		File file = new File(path + File.separator + filename);
		// ���ļ������룬��ֹ�����ļ�����
		filename = this.getFilename(request, filename);
		// ������Ӧͷ
		HttpHeaders headers = new HttpHeaders();
		// ֪ͨ����������صķ�ʽ���ļ�
		headers.setContentDispositionFormData("attachment", filename);
		// MediaType�������Internet Media
		// Type����������ý�����ͣ���MediaType.APPLICATION_OCTET_STREAM��ֵΪapplication/octet-stream,����ʾ�Զ�����������ʽ���ط����ļ�����
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		// ʹ��Sring MVC��ܵ�ResponseEntity�����װ������������
		// HttpStatus.OK ��ʾ200�����������ѳɹ�����������
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
	}

	/**
	 * ����������Ĳ�ͬ���б������ã����ر������ļ���
	 */
	public String getFilename(HttpServletRequest request, String filename) throws Exception {
		// IE��ͬ�汾User-Agent�г��ֵĹؼ���
		String[] IEBrowserKeyWords = { "MSIE", "Trident", "Edge" };
		// ��ȡ����ͷ������Ϣ
		String userAgent = request.getHeader("User-Agent");
		// �Բ�ͬ��������в�ͬ��ʽ��ת��
		for (String keyWord : IEBrowserKeyWords) {
			if (userAgent.contains(keyWord)) {
				// IE�ں��������ͳһΪUTF-8������ʾ
				return URLEncoder.encode(filename, "UTF-8");
			}
		}
		// ��������������ͳһΪISO-8859-1������ʾ
		return new String(filename.getBytes("UTF-8"), "ISO-8859-1");
	}

}
