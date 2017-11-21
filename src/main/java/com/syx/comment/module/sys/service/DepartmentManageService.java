package com.syx.comment.module.sys.service;

import com.alibaba.fastjson.JSONArray;
import com.syx.comment.entity.SysDepartment;
import com.syx.comment.entity.SysUser;

import java.util.List;

/**
 * 描述:
 * 系统用户管理——API
 *
 * @author Msater Zg
 * @create 2017-11-07 19:14
 */
public interface DepartmentManageService {
    /**
     * 添加部门，同时添加本部门部长的账号
     *
     * @param sysDepartment
     * @param sysUser
     * @return
     */
    SysDepartment saveDepartmentInformation(SysDepartment sysDepartment, SysUser sysUser);

    /**
     * 根据数据包编码的得到所有的部门信息
     *
     * @param depPacketNo
     * @return
     */
    JSONArray getDepartmentInformation(String depPacketNo);

    /**
     * 删除部门信息
     *
     * @param id
     */
    void deleteDepartment(Long id);
}
