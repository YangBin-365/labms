package edu.xau.info.service.impl;

import edu.xau.info.bean.UserExample;
import edu.xau.info.mapper.RoleUserMapper;
import edu.xau.info.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 **/
@Slf4j
@Service
public class SpringDataUserDetailsService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleUserMapper roleUserMapper;

    //根据 账号查询用户信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //将来连接数据库根据账号查询用户信息
        UserExample userExample = new UserExample();
        userExample.createCriteria().andMobileEqualTo(username);
        List<edu.xau.info.bean.User> users = userMapper.selectByExample(userExample);
        if(users == null || users.size() != 1){
            return null;
        }
        edu.xau.info.bean.User user = users.get(0);
        //根据用户的id查询用户的权限
        log.info("user = {}",user);
        List<String> permissions =userMapper.findRoleByUserId(user.getUserid());

        //将permissions转成数组
        String[] permissionArray = new String[permissions.size()];
        permissions.toArray(permissionArray);

        log.info("permissionArray = {}",permissionArray);
        UserDetails userDetails = User.withUsername(username).password(user.getPassword()).authorities(permissionArray).build();
        return userDetails;
    }

}
