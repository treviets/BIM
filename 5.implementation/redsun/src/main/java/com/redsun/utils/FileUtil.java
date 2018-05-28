package com.redsun.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	
	public final static String attachmentsPath = "WEB-INF\\classes\\attachments\\";
	
	// Save a file to loccal computer and return map of resources.
	public final static LinkedMultiValueMap<String, Object> saveFileToLocalReturnLinkedMultiValueMap(final MultipartFile file) throws IOException {
		LinkedMultiValueMap<String, Object> result = new LinkedMultiValueMap<String, Object>();
		ByteArrayResource fileResource = new ByteArrayResource(file.getBytes()) {
			@Override
			public String getFilename() {
				return file.getOriginalFilename();
			}
		};
        // Add map to result.
        result.add("file", fileResource);

		return result;
	}
	
	// Save files to loccal computer and return map of resources.
	public final static LinkedMultiValueMap<String, Object> saveFilesToLocalReturnLinkedMultiValueMap(final MultipartFile[] files) throws IOException {
		LinkedMultiValueMap<String, Object> result = new LinkedMultiValueMap<String, Object>();
	    for (MultipartFile file : files) {
	        if (file.isEmpty()) {
	            continue;
	        }
	        ByteArrayResource fileResource = new ByteArrayResource(file.getBytes()){
		            @Override
		            public String getFilename(){
		                return file.getOriginalFilename();
		            }
		    };
            // Add map to result.
            result.add("files",fileResource);
	    }

		return result;
	}
	
	// Delete a file from resources on local computer.
	public final static boolean deleteFileFromLocalForMultipartFile(final String absolutePath, final MultipartFile file) {
		File f = new File(absolutePath + file.getOriginalFilename());
		if(f.exists()) {
			f.delete();
		}
	    return true;
	}
	
	// Delete files from resources on local computer.
	public final static boolean deleteFilesFromLocalForMultipartFiles(final String absolutePath, final MultipartFile[] files) {
	    for (MultipartFile file : files) {
	        File f = new File(absolutePath + file.getOriginalFilename());
	        if(f.exists()) {
				f.delete();
			}
	    }
	    return true;
	}
	
	// Save a file to loccal computer.
	public final static String saveFileToLocal(final String absolutePath, final MultipartFile file) throws IOException {
		// Create folder if not.
	    File dir = new File(absolutePath);
        if (!dir.exists())
            dir.mkdirs();
        // Save files.
		FileOutputStream fos;
		byte[] bytes = file.getBytes();
		String fileName = file.getOriginalFilename();
        fos = new FileOutputStream(absolutePath + fileName);
        fos.write(bytes);
        fos.close();

		return fileName;
	}
	
	// Save files to loccal computer.
	public final static List<String> saveFilesToLocal(final String absolutePath, final MultipartFile[] files) throws IOException {
		List<String> result = new ArrayList<String>();
		// Create folder if not.
	    File dir = new File(absolutePath);
        if (!dir.exists())
            dir.mkdirs();
        // Save files.
		String fileName;
		FileOutputStream fos;
	    for (MultipartFile file : files) {
	        if (file.isEmpty()) {
	            continue;
	        }
            byte[] bytes = file.getBytes();
            fileName = file.getOriginalFilename();
            result.add(fileName);
            fos = new FileOutputStream(absolutePath + fileName);
            fos.write(bytes);
            fos.close();
	    }

		return result;
	}
	
	// Delete a file from local computer.
	public final static boolean deleteFileFromLocal(final String fileFullName) {
		File file = new File(fileFullName);
		if(file.exists()) {
			file.delete();
		}
		
		return true;
	}
	
	// Delete files from local computer.
	public final static boolean deleteFilesFromLocal(final List<String> fileFullNames) {
		for(String fileFullName : fileFullNames) {
			File file = new File(fileFullName);
			if(file.exists()) {
				file.delete();
			}
		}
		
		return true;
	}
	
	// Get file.
	public final static File getFile(final String fileFullName) throws FileNotFoundException {
		File file = new File(fileFullName);
		if(!file.exists()) {
			throw new FileNotFoundException("File with path as " + fileFullName + "was not found.");
		}
		
		return file;
	}
	
}
