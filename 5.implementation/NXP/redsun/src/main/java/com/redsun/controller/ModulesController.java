package com.redsun.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.redsun.entities.Client;
import com.redsun.entities.ModulePermission;
import com.redsun.entities.ModuleProperty;
import com.redsun.entities.ModuleRole;
import com.redsun.service.ClientService;
import com.redsun.service.ModuleService;
import com.redsun.service.UserService;
import com.redsun.utils.RedSunConstants;
import com.redsun.utils.UserUtil;

@Controller
@RequestMapping("modules")
public class ModulesController extends BaseController{

	@Autowired
	ModuleService moduleService;
	@Autowired
	ClientService clientService;

	@Autowired
	UserService userService;
	
	@Autowired
	UserUtil userUtil;

	@RequestMapping(value = "/moduleproperties/{modulename}")
	@ResponseBody
	public Object getProjectPermissionsForUsers(@PathVariable("modulename") String moduleName) throws Exception {
		return moduleService.getModuleProperties(moduleName);
	}

	@RequestMapping(value = "/modulepermission/savelist", headers = "Accept=application/json")
	public @ResponseBody Object saveProjectRole(@RequestBody ModulePermission[] mps) throws Exception {

		if (mps[0].getId() <= 0) {
			// insert
			for (int i = 0; i < mps.length; i++) {
				ModulePermission mp = mps[i];
				String tem = mp.getKey();
				mp.setKey(mp.getModuleProperty().getName() + "_" + tem);
				moduleService.addModulePermission(mp);
			}
		} else {
			// update.
			ModulePermission tempM = moduleService.getModulePermission(mps[0].getId());
			// from database.
			List<ModulePermission> avList = moduleService.getListModulePermission(tempM.getName(), tempM.getKey());
			for (int i = 0; i < avList.size(); i++) {
				ModulePermission temp = avList.get(i);
				temp.setModuleProperty(mps[i].getModuleProperty());
				temp.setPermission(mps[i].getPermission());
				moduleService.updateModulePermission(temp);
			}
			// insert
			if (avList.size() < mps.length) {
				for (int i = avList.size(); i < mps.length; i++) {
					ModulePermission mp = mps[i];
					String tem = mp.getKey();
					mp.setKey(mp.getModuleProperty().getName() + "_" + tem);
					moduleService.addModulePermission(mp);
				}
			}
		}
		return mps.length;
	}

	@RequestMapping(value = "modulepermissionlist/{key}", method = RequestMethod.GET)
	public @ResponseBody Object getProjectRoles(@PathVariable("key") String key) throws Exception {
		List<ModulePermission> list = moduleService.getListModulePermission(key);
		return list;
	}

	@RequestMapping(value = "/modulepermission/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object deleteProjectRole(@PathVariable("id") int id) throws Exception {
		ModulePermission tempM = moduleService.getModulePermission(id);
		return moduleService.deleteModulePermission(tempM.getName(), tempM.getKey());
	}

	@RequestMapping(value = "/modulerole_update", headers = "Accept=application/json", method = RequestMethod.PUT)
	public @ResponseBody Object updateProjectPermission(@RequestBody ModuleRole role) throws Exception {
		ModuleRole originRole = moduleService.getModuleRole(role.getUsername(), role.getModulePermission().getKey());
		if (originRole == null) {
			return moduleService.addModuleRole(role);
		} else {
			role.setId(originRole.getId());
			return moduleService.updateModuleRole(role);
		}

	}

	@RequestMapping(value = "/delete/modulerole/{Id}", method = { RequestMethod.GET })
	public String deleteMenu(Model model, @PathVariable("Id") int id) throws Exception {
		moduleService.deleteModuleRole(id);
		return "redirect:/project/detail/";

	}

	@RequestMapping(value = "/permission/{key}")
	@ResponseBody
	public Object getProjectPermission(@PathVariable("key") String key) throws NumberFormatException, Exception {
		List<ModulePermission> permission = new ArrayList<>();

		if (RedSunConstants.ADMIN_GROUP.equalsIgnoreCase(userUtil.getLoginedRole())) {
			// have full access
			String pValue = "1|2|3|4";
			String[] pkeys = key.split("_");
			List<ModuleProperty> listPr = moduleService.getModuleProperties(pkeys[0]);

			for (int i = 0; i < listPr.size(); i++) {
				ModulePermission p = new ModulePermission();

				p.setKey(key);
				p.setPermission(pValue);
				p.setModuleProperty(listPr.get(i));

				permission.add(p);
			}

		} else {
			ModuleRole role = moduleService.getModuleRole(userUtil.getLoginedUsername(), key);
			if (role == null) {
				return null;
			}
			ModulePermission temp = moduleService.getModulePermission(role.getModulePermission().getId());
			// get list module permission
			permission = moduleService.getListModulePermission(temp.getName(), temp.getKey());
		}
		return permission;
	}

	@RequestMapping(value = "getclient", method = RequestMethod.GET)
	public @ResponseBody Object getClientById() throws Exception {
		Client client = clientService.getClientById(userUtil.getClientIdOfLoginedUser());
		return client;
	}

	@RequestMapping(value = "getmoduleroles/{key}/")
	public @ResponseBody Object getModuleRoles(@PathVariable("key") String key) throws Exception {
		List<ModuleRole> list = null;

		list = moduleService.getModuleRoles(key);
		return list;
	}
	@RequestMapping(value = "getmoduleroles/{key}/{username}")
	public @ResponseBody Object getModuleRolesForUser(@PathVariable("key") String key, @PathVariable("username") String username) throws Exception {

		return moduleService.getModuleRole(username, key);
	}
	
}
