package com.syx.comment.repository;

import com.syx.comment.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述:
 * 系统用户的reposity
 *
 * @author Msater Zg
 * @create 2017-11-11 11:00
 */
public interface SysUserRepository extends JpaRepository<SysUser, Long> {
    /**
     * 根据登陆名称获取个人信息
     * @param userName
     * @return
     */
    SysUser findSysUserByUserName(String userName);
}
