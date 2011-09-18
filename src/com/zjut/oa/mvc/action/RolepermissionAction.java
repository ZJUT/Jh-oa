package com.zjut.oa.mvc.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.zjut.oa.db.Pager;
import com.zjut.oa.mvc.core.ActionAdapter;
import com.zjut.oa.mvc.core.Constant;
import com.zjut.oa.mvc.core.annotation.Fail;
import com.zjut.oa.mvc.core.annotation.Result;
import com.zjut.oa.mvc.core.annotation.Success;
import com.zjut.oa.mvc.domain.Menu;
import com.zjut.oa.mvc.domain.Operator;
import com.zjut.oa.mvc.domain.Permission;
import com.zjut.oa.mvc.domain.Resource;
import com.zjut.oa.mvc.domain.Role;
import com.zjut.oa.mvc.domain.Rolepermission;
import com.zjut.oa.mvc.domain.strengthen.PermissionTogether;
import com.zjut.oa.mvc.domain.strengthen.RolePermissionTogether;

public class RolepermissionAction extends ActionAdapter {

	@Override
	public String show(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return super.show(req, resp);
	}

	@SuppressWarnings("unchecked")
	@Result("/WEB-INF/pages/freeze/rolepermission/viewAdd.jsp")
	public String viewAdd(HttpServletRequest req, HttpServletResponse resp) {
		int roleID = param(req, "roleID", 0);

		Role role = new Role();
		Permission permission = new Permission();

		setAttr(req, PAGE_ROLEPERMISSION_ROLELIST_KEY, role.listAll());

		List<Permission> permissionList = (List<Permission>) permission
				.listAll(" order by resourceID asc, optID asc");

		// 填充组合对象
		List<PermissionTogether> permissionTogetherList = new ArrayList<PermissionTogether>();

		for (Permission p : permissionList) {
			int tmp_menuID = p.getMenuID();
			int tmp_resourceID = p.getResourceID();
			int tmp_optID = p.getOptID();

			Menu prepare_menu = new Menu();
			prepare_menu = prepare_menu.get(tmp_menuID);

			Resource prepare_resource = new Resource();
			prepare_resource = prepare_resource.get(tmp_resourceID);

			Operator prepare_operator = new Operator();
			prepare_operator = prepare_operator.get(tmp_optID);

			PermissionTogether pt = new PermissionTogether();
			pt.setId(p.getId());
			pt.setMenu(prepare_menu);
			pt.setResource(prepare_resource);
			pt.setOperator(prepare_operator);
			pt.setDescription(p.getDescription());

			permissionTogetherList.add(pt);
		}
		setAttr(req, PAGE_ROLEPERMISSION_PERMISSION_TOGETHER_LIST_KEY,
				permissionTogetherList);
		setAttr(req, PAGE_ROLEPERMISSION_ROLEID_KEY, roleID);

		return INPUT;
	}

	@SuppressWarnings("unchecked")
	@Success(path = "/action/role/list", isAction = true)
	@Fail(path = "/WEB-INF/pages/freeze/rolepermission/viewAdd.jsp")
	public String add(HttpServletRequest req, HttpServletResponse resp) {
		int roleID = param(req, "roleID", 0);
		String[] permissionID = params(req, "permissionID");

		// 页面必须数据
		Role role = new Role();
		Permission permission = new Permission();

		setAttr(req, PAGE_ROLEPERMISSION_ROLELIST_KEY, role.listAll());

		List<Permission> permissionList = (List<Permission>) permission
				.listAll(" order by resourceID asc, optID asc");

		// 填充组合对象
		List<PermissionTogether> permissionTogetherList = new ArrayList<PermissionTogether>();

		for (Permission p : permissionList) {
			int tmp_menuID = p.getMenuID();
			int tmp_resourceID = p.getResourceID();
			int tmp_optID = p.getOptID();

			Menu prepare_menu = new Menu();
			prepare_menu = prepare_menu.get(tmp_menuID);

			Resource prepare_resource = new Resource();
			prepare_resource = prepare_resource.get(tmp_resourceID);

			Operator prepare_operator = new Operator();
			prepare_operator = prepare_operator.get(tmp_optID);

			PermissionTogether pt = new PermissionTogether();
			pt.setId(p.getId());
			pt.setMenu(prepare_menu);
			pt.setResource(prepare_resource);
			pt.setOperator(prepare_operator);
			pt.setDescription(p.getDescription());

			permissionTogetherList.add(pt);
		}
		setAttr(req, PAGE_ROLEPERMISSION_PERMISSION_TOGETHER_LIST_KEY,
				permissionTogetherList);
		setAttr(req, PAGE_ROLEPERMISSION_ROLEID_KEY, roleID);

		if (roleID == 0 || roleID == -1) {
			setAttr(req, TIP_NAME_KEY, "请先选择角色");
			return FAIL;
		}

		Rolepermission rolepermission = new Rolepermission();
		List<Rolepermission> existRPList = (List<Rolepermission>) rolepermission
				.filter(" where roleID=" + roleID);

		if (existRPList.size() > 0) {
			setAttr(req, TIP_NAME_KEY, "此角色权限已存在,如需编辑请点击相应角色的\"重新分配角色权限\"的链接");
			return FAIL;
		}

		if (permissionID.length == 0) {
			setAttr(req, TIP_NAME_KEY, "请先勾选权限");
			return FAIL;
		}

		int require_num = permissionID.length;
		int count = 0;
		for (int i = 0, len = permissionID.length; i < len; i++) {
			Rolepermission rp = new Rolepermission();
			rp.setRoleID(roleID);
			rp.setPermissionID(Integer.parseInt(permissionID[i]));
			if (rp.save() != -1)
				count += 1;
		}
		if (require_num == count) {
			setAttr(req, TIP_NAME_KEY, "添加角色权限成功");
			return SUCCESS;
		} else {
			setAttr(req, TIP_NAME_KEY, "添加角色权限失败");
			return FAIL;
		}
	}

	@SuppressWarnings("unchecked")
	@Result("/WEB-INF/pages/freeze/role/list.jsp")
	public String delete(HttpServletRequest req, HttpServletResponse resp) {
		int roleID = param(req, "roleID", 0);

		// 调用角色业务方法
		RoleAction roleAction = new RoleAction();
		if (roleID == 0) {
			setAttr(req, TIP_NAME_KEY, "参数错误");
			return roleAction.list(req, resp);
		}
		// 角色原始权限
		Rolepermission rolepermission = new Rolepermission();
		List<Rolepermission> existRPList = (List<Rolepermission>) rolepermission
				.filter(" where roleID=" + roleID);

		Role role=new Role();
		role=role.get(roleID);
		
		// 当前角色已有权限则先删除
		String[] deleteRP = new String[existRPList.size()];
		if (existRPList.size() > 0) {
			for (int i = 0, len = existRPList.size(); i < len; i++) {
				Rolepermission tmp_rp = existRPList.get(i);
				deleteRP[i] = Long.toString(tmp_rp.getId());
			}
			int[] results = rolepermission.batchDelete(deleteRP);
			log.debug("batchDelete results[0]: " + results[0]);
			if (!(results.length > 0 && results[0] > 0)) {
				setAttr(req, TIP_NAME_KEY, "删除["+role.getRolename()+"]的角色权限失败");
			} else {
				setAttr(req, TIP_NAME_KEY, "删除["+role.getRolename()+"]的角色权限成功");
			}
		} else {
			setAttr(req, TIP_NAME_KEY, "["+role.getRolename()+"]的角色权限不存在");
		}
		return roleAction.list(req, resp);
	}

	@SuppressWarnings("unchecked")
	@Result("/WEB-INF/pages/freeze/rolepermission/viewModify.jsp")
	public String viewModify(HttpServletRequest req, HttpServletResponse resp) {
		int roleID = param(req, "roleID", 0);

		Role role = new Role();
		Permission permission = new Permission();

		setAttr(req, PAGE_ROLEPERMISSION_ROLELIST_KEY, role.listAll());

		List<Permission> permissionList = (List<Permission>) permission
				.listAll(" order by resourceID asc, optID asc");

		// 填充组合对象
		List<PermissionTogether> permissionTogetherList = new ArrayList<PermissionTogether>();

		for (Permission p : permissionList) {
			int tmp_menuID = p.getMenuID();
			int tmp_resourceID = p.getResourceID();
			int tmp_optID = p.getOptID();

			Menu prepare_menu = new Menu();
			prepare_menu = prepare_menu.get(tmp_menuID);

			Resource prepare_resource = new Resource();
			prepare_resource = prepare_resource.get(tmp_resourceID);

			Operator prepare_operator = new Operator();
			prepare_operator = prepare_operator.get(tmp_optID);

			PermissionTogether pt = new PermissionTogether();
			pt.setId(p.getId());
			pt.setMenu(prepare_menu);
			pt.setResource(prepare_resource);
			pt.setOperator(prepare_operator);
			pt.setDescription(p.getDescription());

			permissionTogetherList.add(pt);
		}
		setAttr(req, PAGE_ROLEPERMISSION_PERMISSION_TOGETHER_LIST_KEY,
				permissionTogetherList);
		setAttr(req, PAGE_ROLEPERMISSION_ROLEID_KEY, roleID);

		// 获取此角色的权限
		Rolepermission rp = new Rolepermission();
		List<Rolepermission> rpListForRoleID = (List<Rolepermission>) rp
				.filter(" where roleID=" + roleID);

		setAttr(req, PAGE_ROLEPERMISSION_DATALIST_KEY, rpListForRoleID);

		return INPUT;
	}

	@SuppressWarnings("unchecked")
	@Success(path = "/WEB-INF/pages/freeze/rolepermission/viewModify.jsp")
	@Fail(path = "/WEB-INF/pages/freeze/rolepermission/viewModify.jsp")
	public String modify(HttpServletRequest req, HttpServletResponse resp) {
		int roleID = param(req, "roleID", 0);
		String[] permissionID = params(req, "permissionID");

		// 页面必须数据
		Role role = new Role();
		Permission permission = new Permission();

		setAttr(req, PAGE_ROLEPERMISSION_ROLELIST_KEY, role.listAll());

		List<Permission> permissionList = (List<Permission>) permission
				.listAll(" order by resourceID asc, optID asc");

		// 填充组合对象
		List<PermissionTogether> permissionTogetherList = new ArrayList<PermissionTogether>();

		for (Permission p : permissionList) {
			int tmp_menuID = p.getMenuID();
			int tmp_resourceID = p.getResourceID();
			int tmp_optID = p.getOptID();

			Menu prepare_menu = new Menu();
			prepare_menu = prepare_menu.get(tmp_menuID);

			Resource prepare_resource = new Resource();
			prepare_resource = prepare_resource.get(tmp_resourceID);

			Operator prepare_operator = new Operator();
			prepare_operator = prepare_operator.get(tmp_optID);

			PermissionTogether pt = new PermissionTogether();
			pt.setId(p.getId());
			pt.setMenu(prepare_menu);
			pt.setResource(prepare_resource);
			pt.setOperator(prepare_operator);
			pt.setDescription(p.getDescription());

			permissionTogetherList.add(pt);
		}
		setAttr(req, PAGE_ROLEPERMISSION_PERMISSION_TOGETHER_LIST_KEY,
				permissionTogetherList);
		setAttr(req, PAGE_ROLEPERMISSION_ROLEID_KEY, roleID);

		// 角色原始权限
		Rolepermission rolepermission = new Rolepermission();
		List<Rolepermission> existRPList = (List<Rolepermission>) rolepermission
				.filter(" where roleID=" + roleID);
		setAttr(req, PAGE_ROLEPERMISSION_DATALIST_KEY, existRPList);

		if (roleID == 0 || roleID == -1) {
			setAttr(req, TIP_NAME_KEY, "加载角色失败");
			return FAIL;
		}

		if (permissionID.length == 0) {
			setAttr(req, TIP_NAME_KEY, "请先勾选权限");
			return FAIL;
		}

		// 当前角色已有权限则先删除
		String[] deleteRP = new String[existRPList.size()];
		String[] deleteRP_PID = new String[existRPList.size()];
		if (existRPList.size() > 0) {
			for (int i = 0, len = existRPList.size(); i < len; i++) {
				Rolepermission tmp_rp = existRPList.get(i);
				deleteRP[i] = Long.toString(tmp_rp.getId());
				deleteRP_PID[i] = Integer.toString(tmp_rp.getPermissionID());
			}
			Arrays.sort(permissionID);
			Arrays.sort(deleteRP_PID);
			if (Arrays.equals(permissionID, deleteRP_PID)) {
				setAttr(req, TIP_NAME_KEY, "无任何修改");
				return FAIL;
			}

			int[] results = rolepermission.batchDelete(deleteRP);
			log.debug("batchDelete results[0]: " + results[0]);
			if (!(results.length > 0 && results[0] > 0)) {
				setAttr(req, TIP_NAME_KEY, "删除已有角色权限失败");
			}
		}

		int require_num = permissionID.length;
		int count = 0;
		for (int i = 0, len = permissionID.length; i < len; i++) {
			Rolepermission rp = new Rolepermission();
			rp.setRoleID(roleID);
			rp.setPermissionID(Integer.parseInt(permissionID[i]));
			if (rp.save() != -1)
				count += 1;
		}
		if (require_num == count) {
			setAttr(req, TIP_NAME_KEY, "重新分配角色权限成功");
			existRPList = (List<Rolepermission>) rolepermission
					.filter(" where roleID=" + roleID);
			setAttr(req, PAGE_ROLEPERMISSION_DATALIST_KEY, existRPList);
			return SUCCESS;
		} else {
			setAttr(req, TIP_NAME_KEY, "重新分配角色权限失败");
			return FAIL;
		}
	}

	@Override
	public String viewFilter(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return super.viewFilter(req, resp);
	}

	@SuppressWarnings("unchecked")
	@Result("/WEB-INF/pages/freeze/role/list.jsp")
	@Success(path="/WEB-INF/pages/freeze/rolepermission/filter.jsp")
	public String filter(HttpServletRequest req, HttpServletResponse resp) {
		int roleID = param(req, "roleID", 0);
		
		// 调用角色业务方法
		RoleAction roleAction = new RoleAction();
		if(roleID==0){
			setAttr(req, TIP_NAME_KEY, "加载角色失败");
			return roleAction.list(req, resp);
		}

		// 获取此角色的权限
		Rolepermission rp = new Rolepermission();
		List<Rolepermission> rpListForRoleID = (List<Rolepermission>) rp
				.filter(" where roleID=" + roleID);

		List<RolePermissionTogether> rptListForRoleID=new ArrayList<RolePermissionTogether>();
		//同角色
		Role role=new Role();
		role=role.get(roleID);
		
		for(Rolepermission rolepermission : rpListForRoleID){
			int tmp_permissionID=rolepermission.getPermissionID();
			
			
			Permission permission=new Permission();
			permission=permission.get(tmp_permissionID);
			int tmp_menuID=permission.getMenuID();
			int tmp_resourceID=permission.getResourceID();
			int tmp_optID=permission.getOptID();
			Menu menu=new Menu();
			menu=menu.get(tmp_menuID);
			Resource resource=new Resource();
			resource=resource.get(tmp_resourceID);
			Operator operator=new Operator();
			operator=operator.get(tmp_optID);
			PermissionTogether pt=new PermissionTogether();
			pt.setId(permission.getId());
			pt.setMenu(menu);
			pt.setResource(resource);
			pt.setOperator(operator);
			pt.setDescription(permission.getDescription());
			
			RolePermissionTogether rpt=new RolePermissionTogether();
			rpt.setId(rolepermission.getId());
			rpt.setRole(role);
			rpt.setPermissiontogether(pt);

			rptListForRoleID.add(rpt);
		}
		setAttr(req, PAGE_ROLEPERMISSION_ROLEPERMISSION_TOGETHER_FOR_ROLEID_KEY, rptListForRoleID);
		setAttr(req, PAGE_ROLEPERMISSION_ROLE_KEY, role);
		
		return SUCCESS;
	}

	@Override
	public String list(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return super.list(req, resp);
	}

	@Override
	public String listByPage(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return super.listByPage(req, resp);
	}

	@Override
	public String batchDelete(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return super.batchDelete(req, resp);
	}

	@Override
	public String showMyself(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return super.showMyself(req, resp);
	}

	@Override
	public String viewModifyMyself(HttpServletRequest req,
			HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return super.viewModifyMyself(req, resp);
	}

	@Override
	public String modifyMyself(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return super.modifyMyself(req, resp);
	}

	@Override
	public String viewFilterMyself(HttpServletRequest req,
			HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return super.viewFilterMyself(req, resp);
	}

	@Override
	public String filterMyself(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return super.filterMyself(req, resp);
	}

	@Override
	public String listMyself(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return super.listMyself(req, resp);
	}

	@Override
	public String listByPageMyself(HttpServletRequest req,
			HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return super.listByPageMyself(req, resp);
	}

	@Override
	public String batchDeleteMyself(HttpServletRequest req,
			HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return super.batchDeleteMyself(req, resp);
	}

}
