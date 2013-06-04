package com.ylbms.system.service;

import java.util.List;

import com.ylbms.system.model.Menu;
import com.ylbms.system.model.Org;
import com.ylbms.system.model.Role;
import com.ylbms.system.model.User;

/**
 * 
 * @ClassName: SystemService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author JackLiang
 * @date 2013-5-17 上午11:22:49
 * @version V1.0
 * 
 */

public interface SystemService {

	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;

	User getUserByLoginName(String loginName);

	List<Menu> findAllMenu();

	void updateUserLoginInfo(Long userID);

	void saveUser(User currentUser);

	void updatePasswordById(Long id, String loginName, String newPassword);

	User getUser(Long userId);

	List<Org> getAllOrg();

	void saveMenu(Menu menu);

	public void deleteMenu(Long id);

	public Role getRoleModel(Long roleID);

	public void saveRole(Role role);

	public void deleteRole(Long roleID);

	public Menu getMenu(Long menuID);
}
