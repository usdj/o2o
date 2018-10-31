/**
 * 
 */
package com.usdj.o2o.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.usdj.o2o.entity.LocalAuth;

/**
 * @author gerrydeng
 *
 */
public interface LocalAuthDao {
	/**
	 * @param userName
	 * @param password
	 * @return
	 */
	LocalAuthDao queryLocalByUserNameAndPwd(@Param("username") String userName, @Param("password") String password);

	/**
	 * @param userId
	 * @return
	 */
	LocalAuth queryLocalByUserId(@Param("userId") long userId);

	/**
	 * @param localAuth
	 * @return
	 */
	int insertLocalAuth(LocalAuth localAuth);

	/**
	 * @param userId
	 * @param userName
	 * @param password
	 * @param newPassword
	 * @param lastEditTime
	 * @return
	 */
	int updateLocalAuth(@Param("userId") Long userId, @Param("userName") String userName,
			@Param("password") String password, @Param("newPassword") String newPassword,
			@Param("lastEditTime") Date lastEditTime);
}
