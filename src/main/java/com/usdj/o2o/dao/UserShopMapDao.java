/**
 * 
 */
package com.usdj.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.usdj.o2o.entity.UserShopMap;

/**
 * @author gerrydeng
 *
 */
public interface UserShopMapDao {
	/**
	 * @param userShopMapCondition
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<UserShopMap> queryUserShopMapList(@Param("userShopCondition") UserShopMap userShopMapCondition,
			@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

	/**
	 * @param userShopMapCondition
	 * @return
	 */
	int queryUserShopMapCount(@Param("userShopCondition") UserShopMap userShopMapCondition);

	/**
	 * @param userShopMap
	 * @return
	 */
	int insertUserShopMap(UserShopMap userShopMap);

	/**
	 * @param userShopMap
	 * @return
	 */
	int updateUserShopMapPoint(UserShopMap userShopMap);
}
