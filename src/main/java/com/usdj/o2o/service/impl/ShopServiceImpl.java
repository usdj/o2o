/**
 * 
 */
package com.usdj.o2o.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.RemoteTimeoutException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.usdj.o2o.dao.ShopAuthMapDao;
import com.usdj.o2o.dao.ShopCategoryDao;
import com.usdj.o2o.dao.ShopDao;
import com.usdj.o2o.dto.ShopExecution;
import com.usdj.o2o.entity.Shop;
import com.usdj.o2o.entity.ShopAuthMap;
import com.usdj.o2o.entity.ShopCategory;
import com.usdj.o2o.enums.ShopStateEnum;
import com.usdj.o2o.service.ShopService;
import com.usdj.o2o.util.FileUtil;
import com.usdj.o2o.util.ImageUtil;
import com.usdj.o2o.util.PageCalculator;

/**
 * @author gerrydeng
 *
 */
@Service
public class ShopServiceImpl implements ShopService {
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private ShopAuthMapDao shopAuthMapDao;
	@Autowired
	private ShopCategoryDao shopCategoryDao;

	@Override
	@Transactional
	public ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg) throws RuntimeException {

		if (shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP_INFO);
		}
		try {
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			if(shop.getShopCategory() != null) {
				Long shopCategoryId = shop.getShopCategory().getShopCategoryId();
				ShopCategory shopCategory = new ShopCategory();
				shopCategory = shopCategoryDao.queryShopCategoryById(shopCategoryId);
				ShopCategory parentCategory = new ShopCategory();
				parentCategory.setShopCategoryId(shopCategory.getParentId());
				shop.setParentCategory(parentCategory);
			}
			int effectedNum = shopDao.insertShop(shop);
			if(effectedNum <= 0) {
				throw new RuntimeException("店铺创建失败");
			} else {
				try {
					if (shopImg != null) {
						addShopImg(shop, shopImg);
						effectedNum = shopDao.updateShop(shop);
						if(effectedNum <= 0) {
							throw new RuntimeException("创建图片地址失败");
						}
					}
				} catch (Exception e) {
					throw new RuntimeException("addShopImg error:" + e.toString());
				}
				ShopAuthMap shopAuthMap = new ShopAuthMap();
				shopAuthMap.setEmployeeId(shop.getOwnerId());
				shopAuthMap.setShopId(shop.getShopId());
				shopAuthMap.setName("");
				shopAuthMap.setTitle("Owner");
				shopAuthMap.setTitleFlag(1);
				shopAuthMap.setCreateTime(new Date());
				shopAuthMap.setLastEditTime(new Date());
				shopAuthMap.setEnableStatus(1);
				try {
					effectedNum = shopAuthMapDao.insertShopAuthMap(shopAuthMap);
					if (effectedNum <= 0) {
						throw new RuntimeException("授权创建失败");
					} else {// 创建成功
						return new ShopExecution(ShopStateEnum.CHECK, shop);
					}
				} catch (Exception e) {
					throw new RuntimeException("insertShopAuthMap error: "
							+ e.getMessage());
				}
			}
		} catch (Exception e) {
			throw new RemoteTimeoutException("addShop error:" + e.getMessage());
		}
	}

	/**
	 * @param shop
	 * @param shopImg
	 */
	private void addShopImg(Shop shop, CommonsMultipartFile shopImg) {
		String dest = FileUtil.getShopImagePath(shop.getShopId());
		// 后面spring mvc用commonsmultipartFile
		String shopImgAddr = ImageUtil.generateThumbnail(shopImg, dest);
		shop.setShopImg(shopImgAddr);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.usdj.o2o.service.ShopService#getShopList(com.usdj.o2o.entity.Shop,
	 * int, int)
	 */
	@Override
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
		int count = shopDao.queryShopCount(shopCondition);
		ShopExecution shopExecution = new ShopExecution();
		if (shopList != null) {
			shopExecution.setShopList(shopList);
			shopExecution.setCount(count);
		} else {
			shopExecution.setState(ShopStateEnum.INNER_ERROR.getState());
		}
		return shopExecution;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.usdj.o2o.service.ShopService#getByEmployeeId(long)
	 */
	@Override
	public ShopExecution getByEmployeeId(long employeeId) throws RuntimeException {
		List<Shop> shopList = shopDao.queryByEmployeeId(employeeId);
		ShopExecution shopExecution = new ShopExecution();
		shopExecution.setShopList(shopList);
		return shopExecution;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.usdj.o2o.service.ShopService#getByShopId(long)
	 */
	@Override
	public Shop getByShopId(long shopId) {
		return shopDao.queryByShopId(shopId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.usdj.o2o.service.ShopService#modifyShop(com.usdj.o2o.entity.Shop,
	 * org.springframework.web.multipart.commons.CommonsMultipartFile)
	 */
	
	@Override
	@Transactional
	public ShopExecution modifyShop(Shop shop, CommonsMultipartFile shopImg) throws RuntimeException {
		// TODO Auto-generated method stub
		if (shop == null || shop.getShopId() == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOPID);
		} else {
			try {
				if (shopImg != null) {
					Shop tempShop = shopDao.queryByShopId(shop.getShopId());
					if (tempShop.getShopImg() != null) {
						FileUtil.deleteFile(tempShop.getShopImg());
					}
					addShopImg(shop, shopImg);
				}
				shop.setLastEditTime(new Date());
				int effectedNum = shopDao.updateShop(shop);
				if (effectedNum <= 0) {
					return new ShopExecution(ShopStateEnum.INNER_ERROR);
				} else {// 创建成功
					shop = shopDao.queryByShopId(shop.getShopId());
					return new ShopExecution(ShopStateEnum.SUCCESS, shop);
				}
			} catch (Exception e) {
				throw new RuntimeException("modifyShop error: "
						+ e.getMessage());
			}
		}
	}

}
