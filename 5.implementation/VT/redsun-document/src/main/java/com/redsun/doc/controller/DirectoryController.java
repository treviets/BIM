package com.redsun.doc.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redsun.doc.entities.Directory;
import com.redsun.doc.service.DirectoryResourceService;
import com.redsun.doc.service.DirectoryService;

@RestController
@RequestMapping(value = "directory")
public class DirectoryController { 
	
	
	@Autowired
    DirectoryService directoryService;
	@Autowired
	DirectoryResourceService directoryRS;
	// Insert.
	@RequestMapping(value = "insert", method = { RequestMethod.POST })
	public Object insert(@Validated @RequestBody Directory directory) throws IOException {
		Object obj = null;
		try{
			obj = directoryService.insert(directory);
		}catch(Exception e){
			//log.error(e.getMessage());
		}
		
		return obj;
	}
	
	@RequestMapping(value = "insertdef", method = { RequestMethod.POST })
	public Object insertDef(@Validated @RequestBody Directory directory) throws IOException {
		Object obj = null;
		try{
			obj = directoryService.insertDef(directory);
		}catch(Exception e){
			//log.error(e.getMessage());
		}
		
		return obj;
	}
	
	@RequestMapping(value = "createlistdirectory/{id}/{projectName}/{userName}/{clientId}", method = { RequestMethod.GET })
	public Object createListDirectory(@PathVariable("id") Integer id, @PathVariable("projectName") String projectName
									, @PathVariable("userName") String userName, @PathVariable("clientId") Integer clientId) throws IOException {
		Object obj = null;
		try{
			obj = directoryService.insertBatch(id, projectName, userName, clientId);
		}catch(Exception e){
			//log.error(e.getMessage());
		}
		
		return obj;
	}
	
	// Update.
	@RequestMapping(value = "update/{id}", method = { RequestMethod.PUT })
	public Object update(@PathVariable("id") Integer id, @RequestBody Directory directory) throws IOException {
		return directoryService.update(directory);
	}
	
	// Update.
	@RequestMapping(value = "updatedef/{id}", method = { RequestMethod.PUT })
	public Object updateDef(@PathVariable("id") Integer id, @RequestBody Directory directory) throws IOException {
		return directoryService.updateDef(directory);
	}
		
	// Move folder to trash.
	@RequestMapping(value = "/updatetrash/{id}/{location}", method = { RequestMethod.PUT })
	public Object moveFolderToTrash(@PathVariable("id") Integer id, @PathVariable("location") String location) throws IOException {
		return directoryService.moveFolderToTrash(id, 1, location);
	}
		
	// Delete.
	@RequestMapping(value = "delete/{id}/{location}", method = { RequestMethod.DELETE })
	public Object delete(@PathVariable("id") Integer id, @PathVariable("location") String location) throws IOException {
		return directoryService.delete(id, location);
	}
	
	@RequestMapping(value = "deletedef/{id}/{location}", method = { RequestMethod.DELETE })
	public Object deleteDef(@PathVariable("id") Integer id, @PathVariable("location") String location) throws IOException {
		return directoryService.deleteDef(id, location);
	}
	
	// Get by Id.
	@RequestMapping(value = "getbyid/{id}", method = { RequestMethod.GET })
	public Object getById(@PathVariable("id") Integer id) {
		return directoryService.getById(id);
	}
	
	@RequestMapping(value = "getdefbyid/{id}", method = { RequestMethod.GET })
	public Object getDirectoryDefById(@PathVariable("id") Integer id) {
		return directoryService.getDirectoryDefById(id);
	}
	
    @RequestMapping(value="/list/{projectId}/{clientId}/{userName}", method = {RequestMethod.GET})
    public Object getDirectory(@PathVariable("projectId") int projectId
    						, @PathVariable("clientId") int clientId
    						, @PathVariable("userName") String userName, Model model) {
    	
        return directoryService.listDirectory(projectId, clientId, userName);
    }
    
    @RequestMapping(value="/list/{clientId}/{userName}", method = {RequestMethod.GET})
    public Object getDirectory(@PathVariable("clientId") int clientId
    						, @PathVariable("userName") String userName, Model model) {
    	
        return directoryService.listDirectory(clientId, userName, 0);
    }
    
    @RequestMapping(value="/listdefault", method = {RequestMethod.GET})
    public Object getDirectory(Model model) {
    	
        return directoryService.listDefaultDirectory();
    }
    
    @RequestMapping(value="/list/directorybyuser/{clientId}/{userName}", method = {RequestMethod.GET})
    public Object getDirectoryByUser(@PathVariable("clientId") int clientId
    						, @PathVariable("userName") String userName, Model model) {
    	
        return directoryService.listDirectoryByUser(clientId, userName, 0);
    }
    
    @RequestMapping(value="/doc/{directoryId}", method = {RequestMethod.GET})
    public Object getDirectory(@PathVariable("directoryId") int directoryId, Model model) {
    	
        return directoryService.listDocument(directoryId);
    }
    
    @RequestMapping(value="/list/resourceassigned/{clientId}", method = {RequestMethod.GET})
    public Object getResourceAssigned(@PathVariable("clientId") int clientId, Model model) {
    	
        return directoryRS.getResourceAssigned(clientId);
    }
    
    @RequestMapping(value="/list/fromtrash/{clientId}/{userName}", method = {RequestMethod.GET})
    public Object getDirectoryFromTrash(@PathVariable("clientId") int clientId
    									, @PathVariable("userName") String userName, Model model) {
        return directoryService.listDirectory(clientId, userName, 1);
    }
    
    @RequestMapping(value = "/undofromtrash/{ids}", method = { RequestMethod.PUT })
	public Object undoFolderFromTrash(@PathVariable("ids") String ids) throws IOException {
		return directoryService.undoFolderFromTrash(ids, 0);
	}
    
    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public Resource download(@PathVariable final Integer id, final HttpServletResponse response) throws IOException {
        final Map<String, Object> result = directoryService.download(id).getResult();
        final File file = (File) result.get("file");
        //final String fileType = result.get("fileType").toString().replace('\\','/');
        if (file != null) {
            //response.setContentType(fileType);
            response.setHeader("Content-Disposition", "inline; filename=" + file.getName());
            response.setHeader("Content-Length", String.valueOf(file.length()));
            return new FileSystemResource(file);
        }
        return null;
    }
}
