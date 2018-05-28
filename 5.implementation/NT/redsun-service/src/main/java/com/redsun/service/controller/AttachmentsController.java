package com.redsun.service.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.redsun.service.entities.Attachments;
import com.redsun.service.entities.Result;
import com.redsun.service.service.AttachmentsService;
import com.redsun.service.validation.AttachmentsValidator;

/**
 * Attachments Controller
 */
@RestController
@RequestMapping("attachments-service")
public class AttachmentsController {

    @Autowired
    private AttachmentsService attachmentsService;

    @InitBinder
    protected void InitBinder(WebDataBinder binder) {
        binder.setValidator(new AttachmentsValidator());
    }

    @RequestMapping(value = "insert", method = {RequestMethod.POST})
    public Object insert(@Validated @RequestBody Attachments attachments) {
        return attachmentsService.insert(attachments);
    }

    @RequestMapping(value = "update/{id}", method = {RequestMethod.PUT})
    public Object update(@PathVariable("id") Integer id, @RequestBody Attachments attachments) {
        return attachmentsService.update(attachments);
    }

    @RequestMapping(value = "delete/{id}", method = {RequestMethod.DELETE})
    public Object delete(@PathVariable("id") Integer id) throws IOException {
        return attachmentsService.delete(id);
    }

    @RequestMapping(value = "getbyid/{id}", method = {RequestMethod.GET})
    public Object getById(@PathVariable("id") Integer id) {
        return attachmentsService.getById(id);
    }

    @RequestMapping(value = "list/page/filter/{itemsPerPage}/{pageNo}", method = {RequestMethod.POST})
    public Object listAttachmentsForPageAndFilter(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo, @RequestBody Attachments attachments) {
        return attachmentsService.listAttachmentsForPageAndFilter(itemsPerPage, pageNo, attachments);
    }

    @RequestMapping(value = "getByRefId/{refType}/{refId}", method = {RequestMethod.GET})
    public Object getByRefId(@PathVariable final String refType, @PathVariable final Integer refId) {
        return attachmentsService.getByRefId(refType, refId);
    }

    @RequestMapping(value = "upload/{refType}/{refId}", method = {RequestMethod.POST})
    public Result upload(@PathVariable final String refType, @PathVariable final Integer refId, @RequestParam(value = "file") final MultipartFile file) throws IOException {
        return attachmentsService.upload(refType, refId, file);
    }

    /**
     * Ref: http://memorynotfound.com/spring-mvc-download-file-examples/,
     * https://www.leveluplunch.com/java/tutorials/038-retrieve-file-spring-resttemplate/
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "download/{id}", method = RequestMethod.GET)
    public Resource download(@PathVariable final Integer id, final HttpServletResponse response) throws IOException {
        final Map<String, Object> result = attachmentsService.download(id).getResult();
        final File file = (File) result.get("file");
        final String fileType = result.get("fileType").toString().replace('\\','/');
        if (file != null) {
            response.setContentType(fileType);
            response.setHeader("Content-Disposition", "inline; filename=" + file.getName());
            response.setHeader("Content-Length", String.valueOf(file.length()));
            return new FileSystemResource(file);
        }
        return null;
    }
}
