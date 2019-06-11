package org.zy.gateway.admin.client.web;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.bson.types.Binary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.zy.gateway.admin.client.model.FileEntity;

@RequestMapping("mongo")
@RestController
public class MongoDBController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MongoTemplate mongoTemplate;

	@PostMapping("upload")
	public Object upload(MultipartFile image) throws Exception {
		logger.info(image.getOriginalFilename());

		FileEntity fe = new FileEntity();
		fe.setName(image.getOriginalFilename());
		fe.setContent(new Binary(image.getBytes()));
		fe.setType(image.getContentType());
		fe.setSize(image.getSize());
		fe.setCreateTime(new Date());
		fe = mongoTemplate.save(fe);

		String url = "http://127.0.0.1:9901/mongo/file/" + fe.getId();
		logger.info(url);

		return url;
	}

	@GetMapping(value = "file/{id}", produces = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
	public Object getImage(@PathVariable String id) {
		logger.info(id);
		FileEntity fe = mongoTemplate.findById(id, FileEntity.class);
		return fe.getContent().getData();
	}

	@GetMapping("download/{id}")
	public void download(@PathVariable String id, HttpServletResponse response) throws Exception {
		logger.info(id);

		FileEntity fe = mongoTemplate.findById(id, FileEntity.class);

		response.setContentType(fe.getType());
		response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fe.getName(), "UTF-8"));

		OutputStream out = response.getOutputStream();
		out.write(fe.getContent().getData());
		out.flush();
		out.close();
	}

}