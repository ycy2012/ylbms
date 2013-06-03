package com.ylbms.common.security;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.tags.PermissionTag;

/**
 * 
 * @ClassName: BaseShiroDbRealm
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author JackLiang
 * @date 2013-5-16 下午2:55:55
 * @version V1.0
 * 
 */
public class HasAnyPermissionsTag extends PermissionTag {

	private static final long serialVersionUID = 1L;

	private static final String PERMISSION_NAMES_DELIMETER = ",";

	@Override
	protected boolean showTagBody(String permissionNames) {
		boolean hasAnyPermission = false;
		Subject subject = getSubject();
		if (subject != null) {
			// Iterate through permissions and check to see if the user has one
			// of the permissions
			for (String permission : permissionNames
					.split(PERMISSION_NAMES_DELIMETER)) {

				if (subject.isPermitted(permission.trim())) {
					hasAnyPermission = true;
					break;
				}
			}
		}
		return hasAnyPermission;
	}

	
}
