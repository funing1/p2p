package cn.hlyc.action.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.format.FormatterRegistrar;
import org.springframework.stereotype.Controller;

import cn.hlyc.action.common.BaseAction;
import cn.hlyc.cache.BaseCacheService;
import cn.hlyc.domain.admin.AdminModel;
import cn.hlyc.domain.adminRole.Admin_role;
import cn.hlyc.domain.privilege.Privilege;
import cn.hlyc.domain.product.ProductEarningRate;
import cn.hlyc.domain.role.Role;
import cn.hlyc.domain.rolePrivilege.Role_Privilege;
import cn.hlyc.domain.user.UserModel;
import cn.hlyc.service.admin.IAdminService;
import cn.hlyc.service.adminRole.AdminRoleService;
import cn.hlyc.service.privilege.PrivilegeService;
import cn.hlyc.service.role.RoleService;
import cn.hlyc.service.rolePrivilege.RoleprivilegeService;
import cn.hlyc.service.user.IUserService;
import cn.hlyc.utils.ConfigurableConstants;
import cn.hlyc.utils.FrontStatusConstants;
import cn.hlyc.utils.JsonMapper;
import cn.hlyc.utils.Response;
import cn.hlyc.utils.TokenUtil;

@Controller
@Namespace("/account")
@Scope("prototype")
public class AdminAction extends BaseAction {
	@Autowired // 默认by type
	// @Resource(name="redisCache") // by name
	private BaseCacheService baseCacheService;

	// 再次修改
	// 再次修改
	@Autowired
	private IAdminService adminService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private AdminRoleService adminRoleService;

	@Autowired
	private PrivilegeService privilegeService;

	@Autowired
	private RoleprivilegeService roleprivilegeService;

	@Action("login")
	public void login() {
		String username = this.getRequest().getParameter("username");
		String password = this.getRequest().getParameter("password");
		try {
			AdminModel admin = adminService.login(username, password);
			
			if (admin != null) {
				baseCacheService.set("AdminId", admin.getId()+"");
				// ServletActionContext.getResponse().getWriter().write("{\"status\":\"1\"}");
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.SUCCESS).setData(admin).toJSON());
				return;
			} else {
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.BREAK_DOWN).toJSON());
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Action("quanxian")
	public void quanxian() {
		Integer adminId = Integer.parseInt(this.getRequest().getParameter("adminId"));
		this.getResponse().setCharacterEncoding("utf-8");
		try {
			AdminModel admin = adminService.findById(adminId);
			if (admin == null) {
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.BREAK_DOWN).toJSON());
				return;
			}

			List<Integer> roleIdList = adminRoleService.findroleId(admin.getId());

			List<Role> roleList = new ArrayList<>();
			List<Privilege> privilegesList = new ArrayList<>();

			if (roleIdList.contains(1)) {
				List<Role> roleList2 = roleService.findAll();
				roleList.addAll(roleList2);
				List<Privilege> privilegesList2 = privilegeService.findAll();
				privilegesList.addAll(privilegesList2);
			} else {

				for (Integer roleId : roleIdList) {

					Role role = roleService.findOne(roleId);
					List<Integer> privilegeIdList = roleprivilegeService.findPrivilegeId(roleId);
					for (Integer privilegeId : privilegeIdList) {
						Privilege privilege = privilegeService.findById(privilegeId);
						privilegesList.add(privilege);
					}

					roleList.add(role);

				}
			}

			admin.setRolelist(roleListChildren(roleList));
			admin.setPrivilegelist(privilegeListChildren(privilegesList));

			this.getResponse().getWriter()
					.write(Response.build().setStatus(FrontStatusConstants.SUCCESS).setData(admin).toJSON());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<Role> roleListChildren(List<Role> list) {

		List<Role> listfather = new ArrayList<>();
		List<Role> listChildren = new ArrayList<>();

		for (Role role : list) {
			if (role.getIsparent() == 1) {
				listfather.add(role);
			} else {
				listChildren.add(role);
			}
		}
		if (!listfather.isEmpty()) {
			for (Role rolef : listfather) {
				for (Role rolec : listChildren) {
					if (rolef.getId() == rolec.getParentId()) {
						rolef.getRolesChildrens().add(rolec);
					}
				}
			}
		} else {
			listfather.addAll(listChildren);
		}
		return listfather;

	}

	private List<Privilege> privilegeListChildren(List<Privilege> list) {

		List<Privilege> listfather = new ArrayList<>();
		List<Privilege> listChildren = new ArrayList<>();

		for (Privilege pri : list) {
			if (pri.getIsparent() == 1) {
				listfather.add(pri);
			} else {
				listChildren.add(pri);
			}
		}

		for (Privilege prif : listfather) {
			for (Privilege pric : listChildren) {
				if (prif.getId() == pric.getParentId()) {
					prif.getPrivilegeChildren().add(pric);
				}
			}
		}

		return listfather;
	}

	@Action("findAllAdmin")
	public void findAllAdmin() {

		try {
			this.getResponse().setCharacterEncoding("utf-8");
			List<AdminModel> adminList = adminService.findAll();
			if (!adminList.isEmpty() && adminList != null) {
				// ServletActionContext.getResponse().getWriter().write("{\"status\":\"1\"}");
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.SUCCESS).setData(adminList).toJSON());
				return;
			} else {
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.BREAK_DOWN).toJSON());
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
			try {
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.BREAK_DOWN).toJSON());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Action("findAlljuese")
	public void findAlljuese() {
		this.getResponse().setCharacterEncoding("utf-8");
		try {
			List<Role> roelList = roleService.findAll();
			if (!roelList.isEmpty() && roelList != null) {
				// ServletActionContext.getResponse().getWriter().write("{\"status\":\"1\"}");
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.SUCCESS).setData(roelList).toJSON());
				return;
			} else {
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.BREAK_DOWN).toJSON());
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
			try {
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.BREAK_DOWN).toJSON());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
	
	
	@Action("findAllquanxian")
	public void findAllquanxian() {
		this.getResponse().setCharacterEncoding("utf-8");
		try {
			List<Privilege> PrivilegeList = privilegeService.findAll();
			if (!PrivilegeList.isEmpty() && PrivilegeList != null) {
				// ServletActionContext.getResponse().getWriter().write("{\"status\":\"1\"}");
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.SUCCESS).setData(PrivilegeList).toJSON());
				return;
			} else {
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.BREAK_DOWN).toJSON());
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
			try {
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.BREAK_DOWN).toJSON());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Action("updateRole")
	public void updateRole() {
		try {

			String adminId = this.getRequest().getParameter("adminId");
			String jueses_Ids = this.getRequest().getParameter("jueses_Ids");
             
			String[] jueses_IdArray = jueses_Ids.split(",");
			
			adminRoleService.delByAdminId(Integer.parseInt(adminId));
		    
			for (String roleId : jueses_IdArray) {
				
				
				adminRoleService.saveRole(new Admin_role(Integer.parseInt(adminId),Integer.parseInt(roleId)));
				
			}
			

			this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.SUCCESS).toJSON());

		} catch (IOException e) {
			e.printStackTrace();
			try {
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.BREAK_DOWN).toJSON());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	
	
	@Action("updatePrivilege")
	public void updatePrivilege() {
		try {

			String roleId = this.getRequest().getParameter("roleId");
			String quanxian_Ids = this.getRequest().getParameter("quanxian_Ids");
             
			String[] quanxian_IdsArray = quanxian_Ids.split(",");
			
			roleprivilegeService.delByRoleId(Integer.parseInt(roleId));
		    
			for (String quanxianId : quanxian_IdsArray) {
				
				roleprivilegeService.saveRolePrivilege(new Role_Privilege(Integer.parseInt(roleId),Integer.parseInt(quanxianId)));
				
				
			}
			

			this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.SUCCESS).toJSON());

		} catch (IOException e) {
			e.printStackTrace();
			try {
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.BREAK_DOWN).toJSON());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	
	

}
