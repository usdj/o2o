/**
 * 
 */
package com.usdj.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.usdj.o2o.entity.Shop;

/**
 * @author gerrydeng
 *
 */
public interface ShopDao {
	/**
	 * @param shopCondition
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, @Param("rowIndex") int rowIndex,
			@Param("pageSize") int pageSize);

	/**
	 * @param shopCondition
	 * @return
	 */
	int queryShopCount(@Param("shopCondition") Shop shopCondition);

	/**
	 * @param employeeId
	 * @return
	 */
	List<Shop> queryByEmployeeId(long employeeId);

	/**
	 * @param shopId
	 * @return
	 */
	Shop queryByShopId(long shopId);

	/**
	 * @param shop
	 * @return
	 */
	int insertShop(Shop shop);

	/**
	 * @param shop
	 * @return
	 */
	int updateShop(Shop shop);

	/**
	 * @param shopName
	 * @return
	 */
	int deleteShopByName(String shopName);

}
