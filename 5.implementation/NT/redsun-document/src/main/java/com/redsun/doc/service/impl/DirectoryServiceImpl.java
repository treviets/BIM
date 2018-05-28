package com.redsun.doc.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.redsun.doc.dao.DirectoryDao;
import com.redsun.doc.dao.DocumentDao;
import com.redsun.doc.entities.Directory;
import com.redsun.doc.entities.DirectoryResource;
import com.redsun.doc.entities.Document;
import com.redsun.doc.entities.Result;
import com.redsun.doc.service.DirectoryService;
import com.redsun.doc.utils.FileUtil;



@Service
public class DirectoryServiceImpl implements DirectoryService{
	
	@Autowired
	private DirectoryDao directoryDao;
	
	@Autowired
	private DocumentDao documentDao;

    @Autowired
    private Environment env;
	
    // Insert.
	@Override
	public Result insert(final Directory directory) throws IOException {
		// Modify location.
		String location = directory.getLocation();
		if(location == null) {
			location = "";
		} else {
			location = location.replace('\\', File.separatorChar);
			directory.setLocation(location);
		}
		
		// Check name exist.
		if(directoryDao.existName(location, directory.getName())) {
			Result result = new Result();
			result.setStatus(-2);//Exist.
			result.setDescription("doc.folder.exist");// Code for exist (language).
			return result; 
		}

		// Call DAO.
		Date currentDate = new Date();
		directory.setCreateDate(currentDate);
		directory.setUpdateDate(currentDate);
		directory.setTrash(0);
		//directory.setCreateUserName(createUserName);
		final int directoryId = directoryDao.insert(directory);
		
		// Create a new directory on the OS.
		FileUtil.createDirectory(env.getProperty("rootDirectory") + location + directory.getName());
		
		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", directoryId);
		return new Result(result, true);
	}
	
	// Insert.
		@Override
		public Result insertBatch(int projectId, String projectName, String userName, int clientId) throws IOException {
			Directory directory = new Directory();
			directory.setName(projectName);
			directory.setLocation("Projects" + File.separatorChar);
			directory.setParentId(1);
			directory.setProjectId(projectId);
			directory.setClientId(clientId);
			Date currentDate = new Date();
			directory.setCreateDate(currentDate);
			directory.setUpdateDate(currentDate);
			directory.setCreateUserName(userName);
			directory.setTrash(0);
			// Check name exist.
			if(directoryDao.existName(directory.getLocation(), directory.getName())) {
				Result result = new Result();
				result.setStatus(-2);//Exist.
				result.setDescription("doc.folder.exist");// Code for exist (language).
				return result; 
			}
			
			int directoryId = directoryDao.insert(directory);
			List<Directory> listDefaultDirectory = directoryDao.listDefaultDirectory();
			for(int i=0; i<listDefaultDirectory.size(); i++){
				listDefaultDirectory.get(i).setProjectId(projectId);
				listDefaultDirectory.get(i).setClientId(clientId);
				if(!userName.equals("admin"))
					listDefaultDirectory.get(i).setCreateUserName(userName);
				String location = listDefaultDirectory.get(i).getLocation();
				if(location == null){
					location = "Projects" + File.separatorChar + projectName + File.separatorChar;
					listDefaultDirectory.get(i).setParentId(directoryId);
				}
				else{
					location = location.replace('\\', File.separatorChar);
					location = location.replace('/', File.separatorChar);
					location = "Projects" + File.separatorChar + projectName + File.separatorChar + location;
				}
				listDefaultDirectory.get(i).setLocation(location);
				listDefaultDirectory.get(i).setCreateDate(currentDate);
				listDefaultDirectory.get(i).setUpdateDate(currentDate);
			}
			directoryDao.insertBatch(listDefaultDirectory);
			List<Directory> listDirectories = directoryDao.getDirectoryByProjectInit(projectId, clientId, userName, 0);
			for(int i=0; i<listDirectories.size(); i++){
				String path = "";
				if(!listDirectories.get(i).getName().equals(projectName)){
					path = listDirectories.get(i).getLocation();
					path = path.replace(File.separatorChar, '-');
					path = path + listDirectories.get(i).getName() + "-";
					for(int j=i+1; j<listDirectories.size(); j++){
						String subPath = listDirectories.get(j).getLocation();
						subPath = subPath.replace(File.separatorChar, '-');
						subPath = subPath.replace('/', '-');
						subPath = subPath.replace('\\', '-');
						if(subPath.equals(path))
							listDirectories.get(j).setParentId(listDirectories.get(i).getId());
					}
				}
			}
			directoryDao.updateBatch(listDirectories);
			
			// Create a new directory on the OS.
			for(int i=0; i<listDirectories.size(); i++)
				FileUtil.createDirectory(env.getProperty("rootDirectory") + listDirectories.get(i).getLocation() + listDirectories.get(i).getName());
			// Return.
			final Map<String, Object> result = new HashMap<String, Object>();
			result.put("id", 1);
			return new Result(result, true);
		}

    // Update.
	@Override
	public Result update(final Directory directory) throws IOException {
		// Call DAO.
		Date currentDate = new Date();
		directory.setUpdateDate(currentDate);
		//directory.setCreateUserName(createUserName);
		final List<Directory> directoriesDB = directoryDao.getById(directory.getId());
		if(directoriesDB.isEmpty()) {
			Result result = new Result();
			result.setStatus(-1);// Not exist.
			result.setDescription("doc.folder.notexist");// Code for not exist (language).
			return result; 
		}
		// Check id and name exist.
		String location = directory.getLocation();
		if(location == null) {
			location = "";
		} else {
			location = location.replace('\\', File.separatorChar);
			directory.setLocation(location);
		}
		if(directoryDao.existName(location, directory.getName())) {
			Result result = new Result();
			result.setStatus(-3);//Exist.
			result.setDescription("doc.folder.exist");// Code for exist (language).
			return result; 
		}
		// Modify location.
		String oldLocation = directoriesDB.get(0).getLocation();
		if(oldLocation == null) {
			oldLocation = "";
		}
		String newLocation = directory.getLocation();
		if(newLocation == null) {
			newLocation = "";
		} else {
			newLocation = newLocation.replace('\\', File.separatorChar);
			directory.setLocation(newLocation);
		}
		// Update directory.
		final int directoryId = directoryDao.update(directory);
		
		// Rename a directory on the OS.
		FileUtil.renameDirectory(env.getProperty("rootDirectory") + oldLocation + directoriesDB.get(0).getName(), env.getProperty("rootDirectory") + newLocation + directory.getName());
		
		// Update location of all related directories.
/*		
		String compareString = oldLocation + directoriesDB.get(0).getName() + File.separatorChar;
		String single = "" + File.separatorChar;
		String doub = "" + File.separatorChar + File.separatorChar;
		compareString = compareString.replaceAll(single, doub);
*/		
		directoryDao.updateLocation(oldLocation + directoriesDB.get(0).getName() + File.separatorChar, newLocation + directory.getName() + File.separatorChar);
		// Update location of all related documents.
		documentDao.updateLocation(oldLocation + directoriesDB.get(0).getName() + File.separatorChar, newLocation + directory.getName() + File.separatorChar);

		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", directoryId);
		return new Result(result, true);
	}	

    // Delete.
	@Override
	public Result delete(final Integer directoryId, final String lo) throws IOException {
		// Call DAO.
		final List<Directory> directoriesDB = directoryDao.getDirectoryByLocation(directoryId, lo);
		if(directoriesDB.isEmpty()) {
			Result result = new Result();
			//result.setStatus();// Not exist.
			result.setDescription("NotExist");// Code for not exist (language).
			return result; 
		}
		//delete file in folder
		for(int i=0; i<directoriesDB.size(); i++){
			final List<Document> docs = directoryDao.listFiles(directoriesDB.get(i).getId());
			for(int j=0; j<docs.size(); j++){
				final Document doc = docs.get(j);
				final int data = documentDao.delete(doc.getId());
				final String pathToFile = env.getProperty("rootDirectory").replace('\\', File.separatorChar) + doc.getLocation().replace('\\', File.separatorChar)+ doc.getName();
				final Path path = Paths.get(pathToFile);
				if (Files.exists(path)) {
					FileUtils.forceDelete(new File(path.toUri()));
				}
			}
		}
		//delete directory assign to resource
		for(int i=0; i<directoriesDB.size(); i++){
			final Directory directory = directoriesDB.get(i);
			List<DirectoryResource> listDR = directoryDao.getDirectoryResource(directory.getId());
			if(listDR.size()>0)
				directoryDao.deleteDR(directory.getId());
		}
		// Modify location.
		final List<Integer> ids = new ArrayList<Integer>();
		for(int i=0; i<directoriesDB.size(); i++){
			
			final Directory directory = directoriesDB.get(i);
			final int data = directoryDao.delete(directory.getId());
			ids.add(data);
			String location = directory.getLocation();
			if(location == null) {
				location = "";
			} else {
				location = location.replace('\\', File.separatorChar);
			}
			// Delete a directory on the OS.
			FileUtil.deleteDirectory(env.getProperty("rootDirectory") + location + directory.getName());
		}
		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("ids", ids);
		return new Result(result, true);
	}
	
	@Override
	public Result moveFolderToTrash(final int id, final int isTrash, final String location) throws IOException {
		List<Directory> listDirectories = directoryDao.getDirectoryByLocation(id, location);
		List<Integer> ids = new ArrayList<Integer>();
		for(int i=0; i<listDirectories.size(); i++){
			ids.add(directoryDao.moveFolderToTrash(listDirectories.get(i).getId(), isTrash));
		}
		
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("ids", ids);
		return new Result(result, true);
	}
	
	@Override
	public Result undoFolderFromTrash(final String id, final int isTrash) throws IOException {
		String []directoryIds = id.split("-");
		List<Integer> ids = new ArrayList<Integer>();
		for(int i=0; i<directoryIds.length; i++){
			int key = Integer.parseInt(directoryIds[i]);
			ids.add(directoryDao.moveFolderToTrash(key, isTrash));
		}
		
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("ids", ids);
		return new Result(result, true);
	}

	// Get By Id.
	@Override
	public Result getById(final Integer directoryId) {
		// Call DAO.
		final List<Directory> data = directoryDao.getById(directoryId);
		
		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("directory", data);
		return new Result(result, true);
	}

    // Exists.
	@Override
	public Result exists(final Integer directoryId) {
		// Call DAO.
		final boolean data = directoryDao.exists(directoryId);

		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("exists", data);
		return new Result(result, true);
	}

    // Count
	@Override
	public Result count() {
		// Call DAO.
		final long data = directoryDao.count();

		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		return new Result(result, true);
	}
	
	
	@Override
	public Result listDirectory(int projectId, int clientId, String userName){
		final List<Directory> listDirectories = directoryDao.listDirectory(projectId, clientId, userName, 0);
		final List<Directory> listFolders = new ArrayList<Directory>();
		final List<Directory> subFolders = null;
		final List<Directory> rootList = null;
		Directory directory = null;
		int i=0;
		listFolders.add(listDirectories.get(i));
		listDirectories.remove(listFolders.get(i));
		while(listDirectories.size()>0){
			for(int j=0;j<listDirectories.size(); j++)
				if(listDirectories.get(j).getParentId()==listFolders.get(listFolders.size()-1).getId()){
					listFolders.add(listDirectories.get(j));
					listDirectories.remove(listFolders.get(listFolders.size()-1));
				}
				subFolders.add(listFolders.get(listFolders.size()-1));
				listFolders.remove(subFolders.get(subFolders.size()-1));
				if(listFolders.size()==0){
					directory = new Directory();
					directory = subFolders.get(subFolders.size()-1);
					subFolders.remove(subFolders.size()-1);
					directory.setSubFolders(subFolders);
					subFolders.clear();
					rootList.add(directory);
				}
		}
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("rootList", rootList);
		return new Result(result, true);
	}

	@Override
	public Result listDirectory(int clientId, String userName, int trash) {
		// TODO Auto-generated method stub
		final List<Directory> listDirectories = directoryDao.listDirectory(clientId, userName, trash);
		List<Directory> rootDirectories = getRoot(listDirectories);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("listDirectories", rootDirectories);
		return new Result(result, true);
	}
	
	@Override
	public Result listDefaultDirectory() {
		// TODO Auto-generated method stub
		List<Directory> listDefaultDirectory = directoryDao.listDefaultDirectory();
		List<Directory> rootDirectories = getRoot(listDefaultDirectory);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("listDefaultDirectories", rootDirectories);
		return new Result(result, true);
	}
	
	private List<Directory> getRoot(List<Directory> directories) {
		// Init list of root diretories.
		List<Directory> roots = new ArrayList<Directory>();
		int len = directories.size();
		for(int i =0; i< len; i++) {
			roots.add(directories.get(i));
		}
		// Find children.
		for(int i = 0; i < len; i++) {
			Directory directory = directories.get(i);
			List<Directory> children = getChildren(directories, directory.getId());
			directory.setSubFolders(children);
			// Remove children in roots.
			removeChildrenInRoots(roots, children);
		}
		
		return roots;
	}
	
	private List<Directory> getChildren(List<Directory> directories, int pId) {
		List<Directory> result = new ArrayList<Directory>();
		int len = directories.size();
		for(int i = 0; i < len; i++) {
			Directory directory = directories.get(i);
			if(directory.getParentId() == pId) {
				result.add(directory);
			}
		}
		return result;
	}

	private void removeChildrenInRoots(List<Directory> parent, List<Directory> children) {
		int parentLen = parent.size();
		int childrenLen = children.size();
		if(parentLen == 0 || childrenLen == 0) {
			return;
		}
		for(int i = 0; i < parentLen; i++) {
			for(int j = 0; j < childrenLen; j++) {
				if(parent.get(i).getId() == children.get(j).getId()) {
					parent.remove(i);
					parentLen--;
					i--;
					break;
				}
			}
		}
	}

	@Override
	public Result listDocument(int directoryId) {
		// TODO Auto-generated method stub
		final List<Document> docs = directoryDao.listFiles(directoryId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("docs", docs);
		return new Result(result, true);
	}
	
	@Override
	public Result listDirectoryByUser(int clientId, String userName, int trash) {
		// TODO Auto-generated method stub
		final List<Directory> listDirectories = directoryDao.listDirectory(clientId, userName, trash);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("listDirectories", listDirectories);
		return new Result(result, true);
	}
	
	@Override
	public Result download(final Integer id) {
		final List<Document> docs = directoryDao.getFile(id);
		final Map<String, Object> result = new HashMap<>();
		if(!docs.isEmpty()){
			final Document doc = docs.get(0);
			final String pathToFile = env.getProperty("rootDirectory").replace('\\', File.separatorChar) + doc.getLocation() + File.separatorChar + doc.getName();
			final File file =  new File(pathToFile);
			result.put("file", file.exists()?file:null);
			//result.put("fileType", new File(attach.getMimeType()));
		}
		return new Result(result, true);
	}

	@Override
	public Result getDirectoryFromTrash(int clientId) {
		// TODO Auto-generated method stub
		List<Directory> listDirectory = directoryDao.getDirectoryFromTrash(clientId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("listDirectory", listDirectory);
		return new Result(result, true);
	}

	@Override
	public Result insertDef(Directory directory) throws IOException {
		// Modify location.
		String location = directory.getLocation();
		if(location == null) {
			location = "";
		} else {
			location = location.replace('\\', File.separatorChar);
			directory.setLocation(location);
		}
		
		// Check name exist.
		if(directoryDao.existNameDef(location, directory.getName())) {
			Result result = new Result();
			result.setStatus(-2);//Exist.
			result.setDescription("doc.folder.exist");// Code for exist (language).
			return result; 
		}

		// Call DAO.
		Date currentDate = new Date();
		directory.setCreateDate(currentDate);
		directory.setUpdateDate(currentDate);
		directory.setTrash(0);
		//directory.setCreateUserName(createUserName);
		final int directoryId = directoryDao.insertDef(directory);
		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", directoryId);
		return new Result(result, true);

	}

	@Override
	public Result updateDef(Directory directory) throws IOException {
		// Call DAO.
		Date currentDate = new Date();
		directory.setUpdateDate(currentDate);
		//directory.setCreateUserName(createUserName);
		final List<Directory> directoriesDB = directoryDao.getDirectoryDefById(directory.getId());
		if(directoriesDB.isEmpty()) {
			Result result = new Result();
			result.setStatus(-1);// Not exist.
			result.setDescription("doc.folder.notexist");// Code for not exist (language).
			return result; 
		}
		// Check id and name exist.
		String location = directory.getLocation();
		if(location == null) {
			location = "";
		} else {
			location = location.replace('\\', File.separatorChar);
			directory.setLocation(location);
		}
		if(directoryDao.existNameDef(location, directory.getName())) {
			Result result = new Result();
			result.setStatus(-3);//Exist.
			result.setDescription("doc.folder.exist");// Code for exist (language).
			return result; 
		}
		// Modify location.
		String oldLocation = directoriesDB.get(0).getLocation();
		if(oldLocation == null) {
			oldLocation = "";
		}
		String newLocation = directory.getLocation();
		if(newLocation == null) {
			newLocation = "";
		} else {
			newLocation = newLocation.replace('\\', File.separatorChar);
			directory.setLocation(newLocation);
		}
		// Update directory.
		final int directoryId = directoryDao.updateDef(directory);
		
		directoryDao.updateLocationDef(oldLocation + directoriesDB.get(0).getName() + File.separatorChar, newLocation + directory.getName() + File.separatorChar);
		// Update location of all related documents.
		//documentDao.updateLocation(oldLocation + directoriesDB.get(0).getName() + File.separatorChar, newLocation + directory.getName() + File.separatorChar);

		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", directoryId);
		return new Result(result, true);
	}

	@Override
	public Result deleteDef(Integer id, String lo) throws IOException {
		// Call DAO.
		final List<Directory> directoriesDB = directoryDao.getDirectoryDefByLocation(id, lo);
		if(directoriesDB.isEmpty()) {
			Result result = new Result();
			//result.setStatus();// Not exist.
			result.setDescription("NotExist");// Code for not exist (language).
			return result; 
		}
		// Modify location.
		final List<Integer> ids = new ArrayList<Integer>();
		for(int i=0; i<directoriesDB.size(); i++){
			
			final Directory directory = directoriesDB.get(i);
			final int data = directoryDao.deleteDef(directory.getId());
			ids.add(data);
			String location = directory.getLocation();
			if(location == null) {
				location = "";
			} else {
				location = location.replace('\\', File.separatorChar);
			}
		}
		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("ids", ids);
		return new Result(result, true);
	}

	@Override
	public Result getDirectoryDefById(Integer id) {
		// Call DAO.
		final List<Directory> data = directoryDao.getDirectoryDefById(id);
		
		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("directory", data);
		return new Result(result, true);
	}

}