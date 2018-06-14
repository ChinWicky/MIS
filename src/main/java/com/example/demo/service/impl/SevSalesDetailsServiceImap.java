package com.example.demo.service.impl;

import com.example.demo.entity.SevSalesDetails;
import com.example.demo.mapper.SevSalesDetailsDao;
import com.example.demo.service.SevSalesDetailsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Wicky
 * @since 2018-05-04
 */
@Service
public class SevSalesDetailsServiceImap extends ServiceImpl<SevSalesDetailsDao, SevSalesDetails> implements SevSalesDetailsService {

    @Autowired
    SevSalesDetailsDao sevSalesDetailsDao;
    @Override
    public List<SevSalesDetails> findWaiter(Integer id) {
        return sevSalesDetailsDao.findWaiter(id);
    }

    @Override
    public List<SevSalesDetails> findSalesId(Integer id) {
        return sevSalesDetailsDao.findSalesId(id);
    }

    @Override
    public List<SevSalesDetails> findAll() {
        return sevSalesDetailsDao.findAll();
    }

    @Override
    public Map getChart() {
        List<SevSalesDetails> sevSalesDetailsList = sevSalesDetailsDao.findAll();
        Map map = new HashMap();
        for(int i = 0 ; i < sevSalesDetailsList.size() ; i++){
            if(map.get(sevSalesDetailsList.get(i).getSevProject().getProName())!=null){
               // System.out.println(sevSalesDetailsList.get(i).getSevProject().getProName());
                Integer quantity = (Integer)map.get(sevSalesDetailsList.get(i).getSevProject().getProName());
                quantity = sevSalesDetailsList.get(i).getQuantity()+quantity;
                map.put(sevSalesDetailsList.get(i).getSevProject().getProName(),quantity);
                //sevSalesDetails.setQuantity(sevSalesDetailsList.get(i).getQuantity()+sevSalesDetails.getQuantity());
               // System.out.println("mapQuantity"+((SevSalesDetails) map.get(sevSalesDetailsList.get(i).getProId().toString())).getQuantity());
            }else{
                map.put(sevSalesDetailsList.get(i).getSevProject().getProName(),sevSalesDetailsList.get(i).getQuantity());
            }
        }
        return map;
    }

}
