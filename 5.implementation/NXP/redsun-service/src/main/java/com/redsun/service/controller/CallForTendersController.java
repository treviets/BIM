package com.redsun.service.controller;

import com.redsun.service.entities.CallForTenders;
import com.redsun.service.service.CallForTendersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * CallForTenders Controller
 */
@RestController
@RequestMapping("call-for-tenders-service")
public class CallForTendersController {
	
	@Autowired
	private CallForTendersService callForTendersService;

	/**
	 * this is return list all tender of system
	 * @param clientId
	 * @return
	 */
	@RequestMapping(value = "list/{clientId}/{pageNo}/{itemsPerPage}", method = { RequestMethod.GET })
	public Object list(@PathVariable(required = false) final Integer clientId, @PathVariable final Integer pageNo, @PathVariable final Integer itemsPerPage){
		return callForTendersService.getAll(clientId, pageNo, itemsPerPage);
	}

	/**
	 * method to get tender by client id & tender id
	 * @param clientId
	 * @param tenderId
	 * @return
	 */
	@RequestMapping(value = "get/{clientId}/{tenderId}", method = { RequestMethod.GET })
	public Object getById(@PathVariable final Integer clientId, @PathVariable final Integer tenderId) {
		return callForTendersService.getById(clientId, tenderId);
	}

	@RequestMapping(value = "update/{tenderId}", method = { RequestMethod.PUT })
	public Object updateTender(@RequestBody CallForTenders callForTenders) {
		return callForTendersService.update(callForTenders);
	}

	@RequestMapping(value = "insert", method = { RequestMethod.POST })
	public Object createTender(@RequestBody CallForTenders callForTenders) {
		return callForTendersService.insert(callForTenders);
	}

	@RequestMapping(value = "delete/{refType}/{tenderId}", method = { RequestMethod.DELETE })
	public Object deleteQuotation(@PathVariable final String refType, @PathVariable final Integer tenderId) throws IOException {
		return callForTendersService.delete(refType, tenderId);
	}

}
