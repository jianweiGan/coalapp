package com.skyblue.coalapp.server.user.service;

import com.skyblue.coalapp.server.user.domain.User;
import com.skyblue.coalapp.server.user.repository.UserRepository;
import com.skyblue.coalapp.server.user.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by 张杨 on 2017/5/18.
 */
@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByPhone(String phoneNum) {

        logger.info("Get user Info by phone number");

        User user = userRepository.findByPhoneNum(phoneNum);

        return user;
    }

    @Override
    public User createUserAndSave(String PhoneNum) {

        User user = new User(PhoneNum);
        User saveResult = userRepository.save(user);

        return saveResult;
    }

    @Override
    @Transactional
    public User updateUserInfo(UserVO user){

        if(StringUtils.isNotEmpty(user.getAvatar())){

            userRepository.updateAvatar(user.getAvatar(), user.getId());
        } else if(StringUtils.isNotEmpty(user.getNickname())){

            userRepository.updateNickname(user.getNickname(), user.getId());
        } else if(user.getGender() != null){

            userRepository.updateGender(user.getGender(), user.getId());
        } else if(StringUtils.isNotEmpty(user.getBio())){

            userRepository.updateBio(user.getBio(), user.getId());
        }


        User result = userRepository.findOne(user.getId());
        return result;
    }

    @Override
    public User findById(int id){
        User result = userRepository.findOne(id);
        return result;
    }
}