package com.toutiao.web.apiimpl.authentication;

import com.toutiao.web.common.authentication.RoleDataRule;
import com.toutiao.web.common.constant.datarule.CustomerDataRuleEnum;
import com.toutiao.web.common.constant.datarule.EntranceDataRuleEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * zhangjinglei 2017/10/20 上午10:30
 */
public class Filter {

    /**
     * 客户 查询的数据权限过滤sql
     * @param userNo
     * @param rule
     * @param lqueryArr
     * @param creatorField
     * @param ltreeField
     * @return
     */
    public static String customer(String userNo,RoleDataRule rule,String lqueryArr, String creatorField, String ltreeField){
        List<String> or = new ArrayList<>();
        String where = " and 1=2 ";
        List<Integer> customer = rule.getCustomer();
        if(customer!=null){
            for(Integer e:customer){
                if(e== CustomerDataRuleEnum.Creator.getValue()){
                    if(StringUtils.isNotBlank(creatorField)){
                        or.add(creatorField + "='" +userNo+"'");
                    }
                }
                else if(e== CustomerDataRuleEnum.Ltree.getValue()){
                    if(StringUtils.isNotBlank(creatorField)){
                        or.add(ltreeField + " ? " +lqueryArr);
                    }
                }
            }
        }
        if(or.size()>0){
            where = " and "+ StringUtils.join(or," or ");
        }
        return where;
    }

    /**
     * 机会数据权限过滤sql片段生成
     * @param userNo
     * @param rule
     * @param lqueryArr
     * @param creatorColumn:条件1:创建人列名
     * @param ltreeColumn:条件2:ltree列名
     * @param belongUserNoColumn:条件3:归属人列名
     * @return
     */
    public static String getNashChanceWhere(String userNo,RoleDataRule rule,String lqueryArr,String creatorColumn,String belongUserNoColumn,String ltreeColumn){
        String where = " and 1=2 ";
        List<String> or = new ArrayList<>();
        List<Integer> combination = rule.getEntrance();
        if(combination!=null){
            for(Integer c : combination){
                if(c == EntranceDataRuleEnum.Creator.getValue()){
                    if(StringUtils.isNotBlank(creatorColumn)){
                        or.add(creatorColumn+"='"+userNo+"'");
                    }
                }else if(c == EntranceDataRuleEnum.Owner.getValue()){
                    if(StringUtils.isNotBlank(belongUserNoColumn)){
                        or.add(belongUserNoColumn+"='"+userNo+"'");
                    }
                }else if(c == EntranceDataRuleEnum.Ltree.getValue()) {
                    if (StringUtils.isNotBlank(ltreeColumn)) {
                        or.add(ltreeColumn + " ?? " + lqueryArr);
                    }
                }
            }
        }
        if(or.size()>0){
            where = " and ("+StringUtils.join(or," or ")+") ";
        }
        return where;
    }

    /**
     * 机会列表筛选条件ltree串生成
     * 用例:power_filter ~ '*{1}.1.2.3.4'::lquery
     * @param ltrees:条件[城市,区域,商圈,项目]
     * @return
     */
    public static String getNashChanceTiaojianFilter(Integer... ltrees){
        StringBuffer tree = new StringBuffer();
        tree.append("*{1}.");
        boolean flag = false;
        for(Integer l : ltrees){
            if(l!=-1){
                flag = true;
                tree.append(l+".");
            }else{
                break;
            }
        }
        String tiaojiantree = tree.toString();
        if(flag){
            tiaojiantree += "*";
        }else{
            tiaojiantree = null;
        }
        return tiaojiantree;
    }
}
