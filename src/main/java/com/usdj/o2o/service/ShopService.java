/**
 * 
 */
package com.usdj.o2o.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.usdj.o2o.dto.ShopExecution;
import com.usdj.o2o.entity.Shop;

/**
 * @author gerrydeng
 *
 */
public interface ShopService {
	/**
	 * @param shopCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);
	
	/**
	 * @param employeeId
	 * @return
	 * @throws RuntimeException
	 */
	ShopExecution getByEmployeeId(long employeeId) throws RuntimeException;
	
	/**
	 * @param shopId
	 * @return
	 */
	Shop getByShopId(long shopId);
	
	/**
	 * @param shop
	 * @param shopImg
	 * @return
	 * @throws RuntimeException
	 */
	ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg) throws RuntimeException;
	
	/**
	 * @param shop
	 * @param shopImg
	 * @return
	 * @throws RuntimeException
	 */
	ShopExecution modifyShop(Shop shop, CommonsMultipartFile shopImg) throws RuntimeException;
}
