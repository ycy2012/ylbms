package com.ylbms.system.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ylbms.common.orm.Page;
import com.ylbms.common.security.Digests;
import com.ylbms.common.utils.Encodes;
import com.ylbms.system.dao.MenuDao;
import com.ylbms.system.dao.OrgDAO;
import com.ylbms.system.dao.RoleDAO;
import com.ylbms.system.dao.UserDAO;
import com.ylbms.system.model.Menu;
import com.ylbms.system.model.Org;
import com.ylbms.system.model.Role;
import com.ylbms.system.model.User;
import com.ylbms.system.security.SystemRealm;
import com.ylbms.system.service.SystemService;
import com.ylbms.system.utils.UserUtils;

/**
 * 
 * @ClassName: SystemServiceImpl
 * @Description: 系统管理，安全相关实体的管理类,包括用户、角色、菜单.
 * @author JackLiang
 * @date 2013-5-17 上午11:31:01
 * @version V1.0
 * 
 */
@Component("systemService")
@Transactional(readOnly = true)
public class SystemServiceImpl implements SystemService {

	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;

	@Autowired
	private UserDAO userDao;
	@Autowired
	private RoleDAO roleDao;
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private OrgDAO orgDao;
	@Autowired
	private SystemRealm systemRealm;

	// -- User Service --//
	public User getUser(Long id) {
		return userDao.get(id);
	}

	public List<User> findUser(Page<User> page, User user) {

		return null;
	}

	/**
	 * 根据用户名查询用户信息
	 * 
	 * @param loginName
	 * @return
	 */
	public User getUserByLoginName(String loginName) {
		return userDao.findUniqueBy("loginName", loginName);
	}

	/**
	 * 保存User
	 * 
	 * @param user
	 */
	@Transactional(readOnly = false)
	public void saveUser(User user) {
		if(StringUtils.isNotBlank(user.getPassword())){
			user.setPassword(entryptPassword(user.getPassword()));
		}
		userDao.save(user);
		systemRealm.clearCachedAuthorizationInfo(user.getFullname());
	}

	@Transactional(readOnly = false)
	public void deleteUser(Long id) {
		userDao.delete(id);
	}

	/**
	 * 修改密码
	 */
	@Transactional(readOnly = false)
	public void updatePasswordById(Long id, String loginName, String newPassword) {
		if (!StringUtils.isBlank(newPassword)
				&& !StringUtils.isBlank(loginName)) {
			userDao.updatePasswordById(entryptPassword(newPassword), id);
			systemRealm.clearCachedAuthorizationInfo(loginName);
		}
	}

	/**
	 * 判断用户名是不是唯一
	 * 
	 * @param newUserName
	 * @param oldUserName
	 * @return
	 */
	@Transactional(readOnly = true)
	public boolean isUserNameUnique(String newUserName, String oldUserName) {
		return userDao.isPropertyUnique("loginName", newUserName, oldUserName);
	}

	/**
	 * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
	 */
	public static String entryptPassword(String plainPassword) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt,
				HASH_INTERATIONS);
		return Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword);
	}

	/**
	 * 验证密码
	 * 
	 * @param plainPassword
	 *            明文密码
	 * @param password
	 *            密文密码
	 * @return 验证成功返回true
	 */
	public static boolean validatePassword(String plainPassword, String password) {
		byte[] salt = Encodes.decodeHex(password.substring(0, 16));
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt,
				HASH_INTERATIONS);
		return password.equals(Encodes.encodeHex(salt)
				+ Encodes.encodeHex(hashPassword));
	}

	@Transactional(readOnly = false)
	public void updateUserLoginInfo(Long id) {
		userDao.updateLoginInfo(SecurityUtils.getSubject().getSession()
				.getHost(), new Date(), id);
	}

	// -- Role Service --//
	public Role getRoleModel(Long roleID) {
		return roleDao.get(roleID);
	}

	public Role getRoleByName(String roleName) {
		return roleDao.findUniqueBy("name", roleName);
	}

	public List<Role> findAllRole() {
		User currentUser = UserUtils.getUser();
		if (!currentUser.isAdmin()) {
			return roleDao.findByUserId(currentUser.getId());
		} else {
			return roleDao.getAll();
		}
	}

	@Transactional(readOnly = false)
	public void saveRole(Role role) {
		if (role.getId() == null) {
			role.setUser(UserUtils.getUser());
		}
		roleDao.save(role);
		systemRealm.clearAllCachedAuthorizationInfo();
	}

	public void deleteRole(Long roleID) {
		roleDao.delete(roleID);
		systemRealm.clearAllCachedAuthorizationInfo();
	}

	// -- Menu Service --//
	public Menu getMenu(Long menuID) {
		return menuDao.get(menuID);
	}

	public List<Menu> findAllMenu() {
		return UserUtils.getMenuList();
	}

	@Transactional(readOnly = false)
	public void saveMenu(Menu menu) {
		menu.setParent(this.getMenu(menu.getParent().getId()));
		String oldParentIds = menu.getParentIds(); // 获取修改前的parentIds，用于更新子节点的parentIds
		menu.setParentIds(menu.getParent().getParentIds()
				+ menu.getParent().getId() + ",");
		if (menu.getId() == null) {
			menu.setUser(UserUtils.getUser());
		}
		menuDao.save(menu);
		// 更新子节点 parentIds
		List<Menu> list = menuDao.findByParentIdsLike("%," + menu.getId()
				+ ",%");
		for (Menu e : list) {
			e.setParentIds(e.getParentIds().replace(oldParentIds,
					menu.getParentIds()));
		}
		menuDao.saveMenu(list);
		systemRealm.clearAllCachedAuthorizationInfo();
	}
	
	@Transactional(readOnly = false)
	public void deleteMenu(Long id) {
		menuDao.delete(id);
		systemRealm.clearAllCachedAuthorizationInfo();
	}

	// **************org********************

	public List<Org> getAllOrg() {
		return orgDao.getAll();
	}

}
