package com.ylbms.test;

import static org.junit.Assert.assertTrue;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.apache.shiro.util.ByteSource;
import org.hibernate.Criteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.ylbms.system.dao.UserDao;
import com.ylbms.system.model.User;
import com.ylbms.system.security.SystemRealm;
import com.ylbms.system.service.SystemService;
import com.ylbms.system.service.SystemService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mvc.xml",
		"classpath:applicationContext.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class UserServiceTest extends AbstractJUnit4SpringContextTests {

	AtomicInteger counter = new AtomicInteger();

	@Autowired
	private SystemService systemService;
	
	@Autowired
	private UserDao userDao;

	public void testBackwardsCompatibleSaltedAuthenticationInfo() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(
				Sha1Hash.ALGORITHM_NAME);
		// enable this for Shiro 1.0 backwards compatibility:
		matcher.setHashIterations(1024);
		// simulate an account with SHA-1 hashed password, using the username as
		// the salt
		// (BAD IDEA, but backwards-compatible):
		final String username = "admin";
		// 1 SHA256 twice equal below;
		final String password = "admin";
		// Note that a normal app would reference an attribute rather
		// than create a new RNG every time:
		RandomNumberGenerator rng = new SecureRandomNumberGenerator();
		ByteSource byteSource = rng.nextBytes();
		// ByteSource byteSource = new SimpleByteSource(password);
		// ByteSource byteSource = ByteSource.Util.bytes(password);
		String salt = byteSource.toHex();
		System.out.println(salt);
		// now hash the plain-text password with the random salt and multiple
		// iterations and then Base64-encode the value (requires less space than
		// Hex):
		Sha1Hash sha1Hash = new Sha1Hash(password, byteSource, 1024);
		String hexPassword = sha1Hash.toHex();
		System.out.println(hexPassword);
		AuthenticationInfo account = new SimpleAuthenticationInfo(
				new SystemRealm.Principal(), hexPassword,
				ByteSource.Util.bytes(Hex.decode(salt)), "getName()");

		// simulate a username/password (plaintext) token created in response to
		// a login attempt:
		AuthenticationToken token = new UsernamePasswordToken(username,
				password);

		// verify the hashed token matches what is in the account:
		assertTrue(matcher.doCredentialsMatch(token, account));
	}
	
	@Test
	public void testIsAdmin(){
		User user=systemService.getUser(1l);
		boolean flag=false;
		System.out.println(user.getId().equals(1L));
		
		flag=user.isAdmin();
		
	}

	
}
