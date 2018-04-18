package com.example.demo.security;

import com.example.demo.entity.SysRole;
import com.example.demo.entity.SysUser;
import com.example.demo.mapper.SysRoleDao;
import com.example.demo.mapper.SysUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 *
 */
public class CustomUserService implements UserDetailsService {
    @Autowired
    SysUserDao userDao;
    @Autowired
    SysRoleDao roleDao;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Map map=new HashMap();
        map.put("username",s);
        System.out.println("s:"+s);
        HttpServletRequest request= ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();

        List userList = userDao.selectByMap(map);
        if (userList.size()==0 ) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        SysUser user = (SysUser)userList.get(0);
        request.getSession().setAttribute("user",user);
        SysUser user1 =(SysUser)request.getSession().getAttribute("user");
        System.out.println("session username:"+user1.getUsername()+";password:"+user1.getPassword());

        //将个人信息显示在网页上
        System.out.println("roleid:"+user.getRoleId());
        SysRole role = roleDao.selectById(user.getRoleId());
        System.out.println("s:"+s);
        System.out.println("username:"+user.getUsername()+";password:"+user.getPassword());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
        authorities.add(new SimpleGrantedAuthority(role.getName()));

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), authorities);
    }
}
