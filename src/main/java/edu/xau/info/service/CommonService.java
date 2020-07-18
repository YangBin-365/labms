package edu.xau.info.service;


import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public interface CommonService {
    List<String> findMenuByRole(Collection<? extends GrantedAuthority>  authorities);

    void updatepswd(String mobile,String oldpswd, String newpswd);
}
