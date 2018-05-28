package com.redsun.restcontroller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redsun.controller.BaseController;
import com.redsun.entities.Result;
import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;

/**
 * Created by HauLL on 3/15/2017.
 */
@RestController
@RequestMapping("restful-attachments")
public class AttachmentsRestController extends BaseController{

	
    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "getByRefId/{refType}/{refId}", method = RequestMethod.GET)
    public Result getTenderById(@PathVariable final String refType, @PathVariable final Integer refId, HttpServletRequest request){
        final Map<String, Object> mapUrl = new HashMap<>();
        mapUrl.put("refType", refType);
        mapUrl.put("refId", refId);
        return RestAPIHelper.get(getMainDomain(request) + RedSunURLs.ATTACHMENTS_GET_BY_REF_ID + "{refType}/" + "{refId}", mapUrl);
    }

    @SuppressWarnings("unchecked")
	@Transactional
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public Object upload(@RequestParam("attachModel") final Object attachModel, @RequestParam("file") final MultipartFile file, HttpServletRequest request) throws IOException {
        final Map<String, Object> modelAttachment = objectMapper.readValue(attachModel.toString(), HashMap.class);
        final String fileName = modelAttachment.get("fileName").toString();
        final File tempFile = File.createTempFile(fileName + "%&%&%", null);
        file.transferTo(tempFile);
        final LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("file", new FileSystemResource(tempFile));
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        final HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);

        RestAPIHelper.post(getMainDomain(request) + RedSunURLs.ATTACHMENTS_UPLOAD + modelAttachment.get("refType") + "/" + modelAttachment.get("refId"), requestEntity);
        tempFile.delete();
        return RestAPIHelper.post(getMainDomain(request) + RedSunURLs.ATTACHMENTS_INSERT, modelAttachment);
    }

    @SuppressWarnings("rawtypes")
	@RequestMapping(value = "download/{id}/{fileName}/", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> download(@PathVariable final Integer id, @PathVariable final String fileName, HttpServletRequest request) throws IOException {
        final ResponseEntity responseEntity = new RestTemplate().exchange(getMainDomain(request) + RedSunURLs.ATTACHMENTS_DOWNLOAD + id, HttpMethod.GET, null, Resource.class);

        final Resource resource = (Resource)responseEntity.getBody();
        final InputStream file = resource.getInputStream();
        final Long contentLength = resource.contentLength();

        final HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("content-disposition", "inline;filename=\"" + fileName + "\"");

        return ResponseEntity.ok().headers(headers).contentLength(contentLength)
                .contentType(MediaType.parseMediaType("application/octet-stream")).body(new InputStreamResource(file));

    }

    @Transactional
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable final Integer id, HttpServletRequest request){
        return RestAPIHelper.delete(getMainDomain(request) + RedSunURLs.ATTACHMENTS_DELETE + id, null, new HashMap<>());
    }
}
