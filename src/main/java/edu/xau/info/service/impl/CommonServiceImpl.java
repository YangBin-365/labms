package edu.xau.info.service.impl;

import edu.xau.info.bean.Menu;
import edu.xau.info.mapper.MenuMapper;
import edu.xau.info.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: 杨斌
 * @Date: 2020/7/16 0016 11:11
 */
@Service
@Slf4j
public class CommonServiceImpl implements CommonService {

    @Autowired
    MenuMapper menuMapper;


    @Override
    public List<String> findMenuByRole(Collection<? extends GrantedAuthority>  authorities) {
        List<String> list = new ArrayList<>();
        for (GrantedAuthority authority : authorities) {
            list.add(authority.toString());
        }
        log.info("list = {}",list);
        return menuMapper.findMenuByRole(list);
    }
}
