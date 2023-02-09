package com.blog.service.implement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.service.FileService;
@Service
public class IFileService implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		//File name
				String name= file.getOriginalFilename();
				
				
				//RandomName Generating File
				String randomId = UUID.randomUUID().toString();
				String fileName = randomId.concat(name.substring(name.lastIndexOf('.')));
				
				//FullPath
				String  filePath = path+File.separator+fileName;
				
				//create folder if not created
				File f = new File(path);
				
				if(f.exists()) {
					f.mkdir();
				}
				System.out.println("Not able to detect before detection");
				Files.copy(file.getInputStream(),Paths.get(filePath));
				System.out.println("Not able to detect");
				return fileName;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fullPath = path+ File.separator+fileName;
		//db logic to return inputStream
		InputStream is = new FileInputStream(fullPath);
		return is;
	}

}
