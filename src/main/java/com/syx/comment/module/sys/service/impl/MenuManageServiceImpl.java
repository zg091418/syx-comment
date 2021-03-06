package com.syx.comment.module.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fantasi.common.db.dao.BaseDao;
import com.fantasi.common.db.dao.BaseTableDao;
import com.syx.comment.module.sys.service.MenuManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 描述:
 * 菜单管理的接口实现
 *
 * @author Msater Zg
 * @create 2017-11-07 19:20
 */

@Service
public class MenuManageServiceImpl implements MenuManageService {
    @Autowired
    BaseTableDao tableDao;

    @Autowired
    BaseDao baseDao;

    @Override
    public JSONObject insertModule(String moduleValue, String moduleUrl, String id) {
        JSONArray jsonArray = JSON.parseArray(moduleValue);
        JSONArray jsonArrayUrl = JSON.parseArray(moduleUrl);
        int jsonLength = jsonArray.size();
        int result = 0;
        if ("".equals(id)) {
            for (int i = 0; i < jsonLength; i++) {
                String sql = "INSERT INTO sys_menu (menu_name,menu_type,menu_pid) VALUE(?,'模块',0)";
                result = baseDao.execute(sql, new String[]{(String) jsonArray.get(i)});
            }
        } else {
            for (int i = 0; i < jsonLength; i++) {
                String sql = "INSERT INTO sys_menu (menu_name,menu_type,menu_pid,menu_content) VALUE(?,'子功能',?,?)";
                result = baseDao.execute(sql, new String[]{(String) jsonArray.get(i), id, (String) jsonArrayUrl.get(i)});
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        return jsonObject;
    }

    @Override
    public JSONArray getAllModule() {
        String getAllModuleSql = "SELECT * FROM sys_menu where menu_pid = 0 ";
        List<Map<String, String>> returnList = baseDao.rawQuery(getAllModuleSql);
        JSONArray jsonArray = (JSONArray) JSON.toJSON(returnList);
        return jsonArray;
    }

    @Override
    public JSONObject deleteModule(String moduleId) {
        String deleteModuleSql = "DELETE FROM sys_menu WHERE id = ? OR menu_pid = ?";
        String deleteMenuModuleSql = "DELETE FROM sys_role_menu WHERE menu_id = ?";
        int result = baseDao.execute(deleteModuleSql, new String[]{moduleId, moduleId});
        baseDao.execute(deleteMenuModuleSql, new String[]{moduleId});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        return jsonObject;
    }

    @Override
    public JSONArray getAllSecondModule(String moduleId) {
        String getAllSecondModuleSql = "SELECT * FROM sys_menu WHERE menu_pid = ?";
        List<Map<String, String>> returnList = baseDao.rawQuery(getAllSecondModuleSql, new String[]{moduleId});
        JSONArray jsonArray = (JSONArray) JSON.toJSON(returnList);
        return jsonArray;
    }

    @Override
    public JSONObject updateModuleInfo(String menuId, String menuName, String menuContent) {
        StringBuilder updateModuleSql = new StringBuilder();
        int result = 0;
        if ("".equals(menuContent)) {
            updateModuleSql.append("UPDATE sys_menu SET menu_name = ? WHERE id = ? ");
            result = baseDao.execute(updateModuleSql.toString(), new String[]{menuName, menuId});
        } else {
            updateModuleSql.append("UPDATE sys_menu SET menu_name = ?,menu_content = ? WHERE id = ? ");
            result = baseDao.execute(updateModuleSql.toString(), new String[]{menuName, menuContent, menuId});
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        return jsonObject;
    }
}
