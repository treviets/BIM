package com.redsun.doc.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	
	public final static String attachmentsPath = "WEB-INF\\classes\\attachments\\";
	
	// Save files to loccal computer and return map of resources.
	public final static LinkedMultiValueMap<String, Object> saveFilesToLocalReturnLinkedMultiValueMap(final String absolutePath, final MultipartFile[] files) throws IOException {
		LinkedMultiValueMap<String, Object> result = new LinkedMultiValueMap<String, Object>();
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
            fileName = absolutePath + file.getOriginalFilename();
            fos = new FileOutputStream(fileName);
            fos.write(bytes);
            fos.close();
            // Add map to result.
            result.add("files", new FileSystemResource(fileName));
	    }

		return result;
	}
	
	// Delete files from resources on local computer.
	public final static boolean deleteFilesFromLocalForMultipartFiles(final String absolutePath, final MultipartFile[] files) {
	    for (MultipartFile file : files) {
	        File f = new File(absolutePath + file.getOriginalFilename());
	        f.delete();
	    }
	    return true;
	}
	
	// Save file to loccal computer.
	public final static String saveFileToLocal(final String absolutePath, final MultipartFile file) throws IOException {
		if (file.isEmpty()) {
			return null;
        }
		String fileName = file.getOriginalFilename();
		// Create folder if not.
	    File dir = new File(absolutePath);
        if (!dir.exists())
            dir.mkdirs();
        // Save files.
		FileOutputStream fos;
        byte[] bytes = file.getBytes();
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
	
	// Delete a file from local computer.
	public final static boolean deleteFileFromLocal(final String fileFullName) {
		File file = new File(fileFullName);
		if(file.exists()) {
			file.delete();
		}
		
		return true;
	}
	
	
	//------------------------------------------------------------------------
	//------------------------------------------------------------------------
	//------------------------------------------------------------------------
	//------------------------------------------------------------------------
	//------------------------------------------------------------------------
	
	
	// Create a new directory.
	public final static void createDirectory(final String path) throws IOException {
		// Create folder if not.
	    File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
	}
	
	// Rename a directory.
	public final static void renameDirectory(final String path, final String newPath) throws IOException {
	    File dir = new File(path);
	    File newDir = new File(newPath);
        if (dir.exists()) {
        	dir.renameTo(newDir);
        } else {
        	newDir.mkdirs();
        }
	}
	
	// Delete a directory.
	public final static void deleteDirectory(final String path) throws IOException {
	    File dir = new File(path);
        if (dir.exists()) {
        	dir.delete();
        }
	}
	
}
