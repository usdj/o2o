/**
 * 
 */
package com.usdj.o2o.dao;

import java.util.List;

import com.usdj.o2o.entity.ProductImg;

/**
 * @author gerrydeng
 *
 */
public interface ProductImgDao {
	/**
	 * @param productId
	 * @return
	 */
	List<ProductImg> queryProductImgList(long productId);

	/**
	 * @param productImgList
	 * @return
	 */
	int batchInsertProductImg(List<ProductImg> productImgList);

	/**
	 * @param productId
	 * @return
	 */
	int deleteProductImgByProductId(long productId);

}
