package com.shubh.blog.services.impl;

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

import com.shubh.blog.config.AppConstants;
import com.shubh.blog.exceptions.FileFormatException;
import com.shubh.blog.services.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException, FileFormatException {
//		Get file name
		String name = file.getOriginalFilename();
		
//		Checking if the file extension type
		String extension = name.substring(name.lastIndexOf(".")+1);
		
		if(!AppConstants.ALLOWED_FILES.contains(extension)) {
			throw new FileFormatException(extension);
		}
		
//		Generate random uuid
		String randomId = UUID.randomUUID().toString();
		
//		Concatenate random uuid with the filename after .
		String fileName1 = randomId.concat(name.substring(name.lastIndexOf(".")));
		
//		Get the full path of the file
		String fullPath = path + File.separator + fileName1;
		
//		Create folder if not created
		File f = new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		
//		File copy
		
		Files.copy(file.getInputStream(), Paths.get(fullPath));
		
		return fileName1;
		
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fullPath = path + File.separator + fileName;
		InputStream is = new FileInputStream(fullPath);
		return is;
	}

}
