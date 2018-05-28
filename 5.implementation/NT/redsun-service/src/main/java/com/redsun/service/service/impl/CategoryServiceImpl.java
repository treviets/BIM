package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.redsun.service.entities.Category;
import com.redsun.service.entities.CategoryData;
import com.redsun.service.entities.Result;
import com.redsun.service.service.CategoryService;
import com.redsun.service.utils.RedSunConstants;

@Service
public class CategoryServiceImpl implements CategoryService {

	/*
	 * Just for testing purpose
	 * */
	private static Map<Integer,Category> categoriesList = new HashMap<Integer,Category>();
	private static Map<Integer,CategoryData> categoriesDataList = new HashMap<Integer, CategoryData>();
	private static Map<Integer,Map<Integer,CategoryData>> catDataMap = new HashMap<Integer, Map<Integer,CategoryData>>();
	static{
		
		Category cat = new Category();
		cat.setCategoryDesc("STEEL");
		cat.setCategoryID(1);
		cat.setCategoryName("Steel");
		categoriesList.put(1,cat);
		cat = new Category();
		cat.setCategoryDesc("Plastic");
		cat.setCategoryID(2);
		cat.setCategoryName("Plastic");
		categoriesList.put(2, cat);
		
		CategoryData catData = new CategoryData();
		catData.setStatusID(1);
		catData.setStatusName("Primary");
		catData.setTransactionAmount(163656.3893115595);
		catData.setVendorStatusCount(17);
		catData.setTransactionCurrencyCode("EUR");
		categoriesDataList.put(1, catData);
		
		catData = new CategoryData();
		catData.setStatusID(2);
		catData.setStatusName("Secondary");
		catData.setTransactionAmount(20460.780007839203);
		catData.setVendorStatusCount(14);
		catData.setTransactionCurrencyCode("EUR");
		categoriesDataList.put(2, catData);
		
		catData = new CategoryData();
		catData.setStatusID(3);
		catData.setStatusName("Technical");
		catData.setTransactionAmount(30054.069977998734);
		catData.setVendorStatusCount(10);
		catData.setTransactionCurrencyCode("EUR");
		categoriesDataList.put(3, catData);
		
		catData = new CategoryData();
		catData.setStatusID(5);
		catData.setStatusName("Eliminate");
		catData.setTransactionAmount(13783.630024313927);
		catData.setVendorStatusCount(12);
		catData.setTransactionCurrencyCode("EUR");
		categoriesDataList.put(4, catData);
		
		catDataMap.put(1, categoriesDataList);
		
	}
	/*End testing data**/
	
	public Result getAllCategories() {
		Result result = new Result();
		result.setStatus(RedSunConstants.STATUS_SUCCESS_FLAG);
		result.setDescription(RedSunConstants.STATUS_SUCCESS_STRING);
		result.setResult(categoriesList);
		return result;
	}

	public Result getCategoryData(int id) {
		Result result = new Result();
		result.setStatus(RedSunConstants.STATUS_SUCCESS_FLAG);
		result.setDescription(RedSunConstants.STATUS_SUCCESS_STRING);
		result.setResult(catDataMap.get(id));
		return result;
	}

}
