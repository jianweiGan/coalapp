package com.dayuzl.coalapp.server.Information.service;

import com.dayuzl.coalapp.server.user.domain.User;
import com.dayuzl.coalapp.server.Information.domain.InfoDepartment;
import com.dayuzl.coalapp.server.Information.repository.InfoDepartRepository;
import com.dayuzl.coalapp.server.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InforDeparServiceImpl implements InfoDepartService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private InfoDepartRepository infoDepartRepository;

    @Autowired
    private UserService userService;

    @Override
    @CacheEvict(value="infoDepartmentList",allEntries=true)
    public void addInfoDepartment(InfoDepartment infoDepart) {
        if(infoDepart != null){
            infoDepartRepository.save(infoDepart);
        }

        if(infoDepart != null && infoDepart.getUser() != null
                && infoDepart.getUser().getId() != null) {

            User user = userService.findUser(infoDepart.getUser());
            user.setIsInfoStoreManager(1);
            userService.updateUserInfo(user);
        }

    }

    @Override
    @Cacheable(value = "infoDepartment", key="#infoDepart.toString()",unless="#result != null")
    public InfoDepartment findOne(InfoDepartment infoDepart) {

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("focus");

        Example<InfoDepartment> ex = Example.of(infoDepart, matcher);

        return infoDepartRepository.findOne(ex);
    }

    @Override
    @Cacheable(value = "infoDepartmentList", key="#infoDepartment.toString()",unless="!(#result.size()>0)")
    public List<InfoDepartment> findAll(InfoDepartment infoDepartment) {

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("focus");

        Example<InfoDepartment> ex = Example.of(infoDepartment, matcher);

        return infoDepartRepository.findAll(ex);
    }

    @Override
    @CacheEvict(value="infoDepartmentList",allEntries=true)
    public void deleteById(InfoDepartment infoDepartment) {
        infoDepartRepository.delete(infoDepartment);
    }

    @CacheEvict(value="infoDepartmentList",allEntries=true)
    @Scheduled(fixedDelay = 5*60*1000)
    public void clearCache(){
        logger.info("now clean info department cache");
    }
}
