package com.redsun.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.redsun.entities.Client;
import com.redsun.entities.Menu;
import com.redsun.entities.Result;
import com.redsun.entities.Role;
import com.redsun.entities.RolePermissionMenu;
import com.redsun.entities.User;
import com.redsun.service.ClientService;
import com.redsun.service.MenuService;
import com.redsun.service.ProjectService;
import com.redsun.service.RoleService;
import com.redsun.service.UserService;
import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;
import com.redsun.utils.UserUtil;

@Controller
@RequestMapping("/system")
public class SystemController extends BaseController{
	@Autowired
	ClientService clientService;

	@Autowired
	UserService userService;

	@Autowired
	MenuService menuService;

	@Autowired
	ProjectService projectService;

	@Autowired
	RoleService roleService;
	
	@Autowired
	UserUtil userUtil;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "", method = { RequestMethod.GET })
	@PreAuthorize("@permissionEveluator.hasPermission('System', '1|2|3|4')")
	public String index(Model model, HttpServletRequest request) throws Exception {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		User user = clientService.getClient(userName);
		int client_Id = user.getClientId();
		String getProjectDataURL = getMainDomain(request) + RedSunURLs.RESOURCES_ALL_TYPE_URL_SERVICE_LIST + client_Id;
		Result listResources = RestAPIHelper.get(getProjectDataURL, new HashMap<String, Object>());
		List<User> users = userService.getAllUsers();
		// remove duplicate object
		Map<Object, Object> resources = listResources.getResult();
		List<Object> resourceObjects = (List<Object>)resources.get("resources");
		List<Object> resourceObjectsFiltered = new ArrayList<Object>();
		
		//filter to only show resource which are have no account already
		for(Object resourceObject : resourceObjects){
			Map<Object, Object> dataObject = (Map<Object, Object>)resourceObject;
			boolean hasUserName = false;
			for(User u : users){
				if(dataObject.get("code").equals(u.getUsername())){
					hasUserName = true;
					u.setFullname(String.valueOf(dataObject.get("fullName")));
					break;
				}
			}
			if(!hasUserName){
				resourceObjectsFiltered.add(dataObject);
			}
			
		}
		if(resourceObjectsFiltered.size() > 0){
			resources.put("resources", resourceObjectsFiltered);
		}
		

		model.addAttribute("resources", resources);
		model.addAttribute("userList", users);

		// prepare menu
		model.addAttribute("menuList", menuService.getAll());

		// prepare roles.
		model.addAttribute("roleList", roleService.getAll(true));
		return "system";
	}
	
	@PreAuthorize("@permissionEveluator.hasPermission('System', '1|2|3|4')")
	@RequestMapping(value = "/list/roles", method = { RequestMethod.GET }, headers = "Accept=application/json")
	public @ResponseBody Object listRoles(Model model) throws Exception {
		return roleService.getAll(true);
	}
	
	@PreAuthorize("@permissionEveluator.hasPermission('System', '1|2|3|4')")
	@RequestMapping(value = "/get/role/{roleId}", method = { RequestMethod.GET }, headers = "Accept=application/json")
	public @ResponseBody Object listRoles(Model model, @PathVariable("roleId") int roleId) throws Exception {
		return roleService.getRoleById(roleId);
	}

	@PreAuthorize("@permissionEveluator.hasPermission('System', '3')")
	@RequestMapping(value = "/edit/role", method = { RequestMethod.POST }, headers = "Accept=application/json")
	public @ResponseBody Object editRole(@RequestBody Role role) throws Exception {
		roleService.edit(role);
		List<RolePermissionMenu> menus = role.getMenus();
		//delete permission menu of this role first
		roleService.deleteRolePermissionMenuList(role.getId());
		//set roleid 
		for(int i = 0; i< menus.size(); i++){
			menus.get(i).setRoleId(role.getId());
		}
		// insert role_permission_menu.
		roleService.addRolePermissionMenuList(menus);
		return role.getId();
	}

	@PreAuthorize("@permissionEveluator.hasPermission('System', '4')")
	@RequestMapping(value = "/delete/role/{roleId}", method = { RequestMethod.GET })
	public String deleteRole(Model model, @PathVariable("roleId") int roleId) throws Exception {
		roleService.delete(roleId);
		return "redirect:/system#roles";
	}

	@PreAuthorize("@permissionEveluator.hasPermission('System', '2')")
	@RequestMapping(value = "/add/role", method = { RequestMethod.POST }, headers = "Accept=application/json")
	public @ResponseBody Object addRole(@RequestBody Role role) throws Exception {
		int roleId =  roleService.add(role);
		List<RolePermissionMenu> menus = role.getMenus();
		//set roleid 
		for(int i = 0; i< menus.size(); i++){
			menus.get(i).setRoleId(roleId);
		}
		// insert role_permission_menu.
		roleService.addRolePermissionMenuList(menus);
		return roleId;

	}

	@PreAuthorize("@permissionEveluator.hasPermission('System', '1|2|3|4')")
	@RequestMapping(value = "/list/menus", method = { RequestMethod.GET })
	public @ResponseBody Object listMenus(Model model) throws Exception {
		return menuService.getAll();
	}

	@PreAuthorize("@permissionEveluator.hasPermission('System', '1|2|3|4')")
	@RequestMapping(value = "/get/menu/{menuId}", method = { RequestMethod.GET })
	public @ResponseBody Object getUser(Model model, @PathVariable("menuId") int menuId) throws Exception {
		return menuService.get(menuId);
	}

	@PreAuthorize("@permissionEveluator.hasPermission('System', '3')")
	@RequestMapping(value = "/edit/menu", method = { RequestMethod.POST })
	public @ResponseBody Object editMenu(Model model, Menu menu) throws Exception {
		return menuService.editMenu(menu);
	}

	@PreAuthorize("@permissionEveluator.hasPermission('System', '4')")
	@RequestMapping(value = "/delete/menu/{menuId}", method = { RequestMethod.GET })
	public String deleteMenu(Model model, @PathVariable("menuId") int menuId) throws Exception {
		menuService.deleteMenu(menuId);
		return "redirect:/system#menus";

	}

	@PreAuthorize("@permissionEveluator.hasPermission('System', '2')")
	@RequestMapping(value = "/add/menu", method = { RequestMethod.POST })
	public @ResponseBody Object addMenu(Menu menu) throws Exception {
		return menuService.addMenu(menu);
	}
	
	@PreAuthorize("@permissionEveluator.hasPermission('System', '1|2|3|4')")
	@RequestMapping(value = "/list/users", method = { RequestMethod.POST })
	public @ResponseBody Object listUsers(Model model) throws IOException {
		return null;
	}

	@PreAuthorize("@permissionEveluator.hasPermission('System', '3')")
	@RequestMapping(value = "/edit/user", method = { RequestMethod.POST })
	public @ResponseBody Object editUser(Model model, User user) throws Exception {
		return userService.editUser(user);
	}

	@PreAuthorize("@permissionEveluator.hasPermission('System', '1|2|3|4')")
	@RequestMapping(value = "/get/user/{username}", method = { RequestMethod.GET })
	public @ResponseBody Object getUser(Model model, @PathVariable("username") String username) throws Exception {
		return userService.getUser(username);
	}
	@PreAuthorize("@permissionEveluator.hasPermission('System', '4')")
	@RequestMapping(value = "/delete/user/{username}", method = { RequestMethod.GET })
	public String deleteUser(Model model, @PathVariable("username") String username) throws Exception {
		userService.deleteUser(username);
		return "redirect:/system";
	}

	@PreAuthorize("@permissionEveluator.hasPermission('System', '2')")
	@RequestMapping(value = "/add/user", method = { RequestMethod.POST })
	public @ResponseBody Object addUser(User user) throws Exception {
		return userService.addUser(user);
	}

	@RequestMapping(value = "/list/clients", method = { RequestMethod.POST })
	public @ResponseBody Object listClient(Model model) throws IOException {

		return null;
	}

	@RequestMapping(value = "/edit/client", method = { RequestMethod.POST })
	public @ResponseBody Object editClient(Model model, @RequestParam("clientId") int clientId) throws IOException {
		return null;
	}

	@RequestMapping(value = "/delete/client", method = { RequestMethod.POST })
	public @ResponseBody Object deleteClient(Model model, @RequestParam("clientId") int clientId) throws IOException {
		return null;
	}

	@RequestMapping(value = "/add/client", method = { RequestMethod.POST })
	public @ResponseBody Object addClient(Model model, Client client) throws IOException {
		return null;
	}
	
	@RequestMapping(value = "/changepassword/{currentPass}/{newPass}", method = { RequestMethod.GET })
	public @ResponseBody Object changePassword(@PathVariable("currentPass") String currentPass, @PathVariable("newPass") String newPass) throws Exception {
		return userService.changePassword(currentPass, newPass);
	}
	
	@RequestMapping(value="/get-system-permission/", method = { RequestMethod.GET })
	public @ResponseBody Object getCurrentPerssion(){
		return userUtil.getCurrentUserPermission();
	}
}
