package com.dayuzl.coalapp.server.product.service;

import com.dayuzl.coalapp.server.product.domain.Factory;
import com.dayuzl.coalapp.server.product.domain.ProductType;

import java.util.List;

/**
 * Created by 张杨 on 2017/5/19.
 */
public interface FactoryService {

    void deleteById(Factory factory);

    void saveOrUpdateFactory(Factory factory);

    List<Factory> getFactoryList(Factory factory);

    List<ProductType> getProductTypeList(ProductType productType);
}
