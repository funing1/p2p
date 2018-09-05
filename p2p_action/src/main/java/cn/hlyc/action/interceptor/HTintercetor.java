package cn.hlyc.action.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import cn.hlyc.cache.BaseCacheService;
import cn.hlyc.domain.admin.AdminModel;
import cn.hlyc.domain.privilege.Privilege;
import cn.hlyc.domain.role.Role;
import cn.hlyc.service.admin.IAdminService;
import cn.hlyc.service.adminRole.AdminRoleService;
import cn.hlyc.service.privilege.PrivilegeService;
import cn.hlyc.service.role.RoleService;
import cn.hlyc.service.rolePrivilege.RoleprivilegeService;

public class HTintercetor extends MethodFilterInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private BaseCacheService baseCacheService;

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

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		System.out.println("我是拦截器");

		String AdminId = baseCacheService.get("AdminId");

		if (AdminId == null) {
			System.out.println("您还没登录");
			return "tologin";
		}

		AdminModel admin = adminService.findById(Integer.parseInt(AdminId));

		if (admin == null) {
			System.out.println("您不是后台用户");
			return "tologin";
		}

		HttpServletRequest request = ServletActionContext.getRequest();
		StringBuffer requestURL = request.getRequestURL();

		System.out.println(requestURL);

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

		List<Privilege> privilegelist = admin.getPrivilegelist();

		boolean quanxian = false;

		if (requestURL.toString().contains("/user/signup") || requestURL.toString().contains("/updateUser/update")
				|| requestURL.toString().contains("/user/del")) {

			for (Privilege privilege : privilegelist) {
				if (privilege.getId() == 1 || privilege.getId() == 2) {
					quanxian = true;
				}

				for (Privilege privilege2 : privilege.getPrivilegeChildren()) {
					if (privilege2.getId() == 1 || privilege2.getId() == 2) {
						quanxian = true;
					}

				}

			}

		}

		if (requestURL.toString().contains("/product/addProduct")
				|| requestURL.toString().contains("/product/modifyProduct")
				|| requestURL.toString().contains("/product/delProduct")) {

			for (Privilege privilege : privilegelist) {
				if (privilege.getId() == 9) {
					quanxian = true;
				}

				for (Privilege privilege2 : privilege.getPrivilegeChildren()) {
					if (privilege2.getId() == 9) {
						quanxian = true;
					}

				}
			}

		}

		if (!quanxian) {
			System.out.println("您的权限不够");
			return "tologin";
		}

		System.out.println("放行");
		return invocation.invoke();
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

}
