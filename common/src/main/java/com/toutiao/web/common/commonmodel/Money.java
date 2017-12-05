package com.toutiao.web.common.commonmodel;

import com.toutiao.web.common.util.StringValidationUtils;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * zhangjinglei 2017/9/8 下午4:53
 * 钱类型,钱类型的表示的是分。而不是元
 *  数据库里所有表示钱的字段都是int8的，需要一个money类型处理各种情况
 */
@Data
public class Money implements Serializable {

    private  Long value=null;

    /**
     * 钱数 单位是分
     * @param li
     */
    public Money(Long li) {
        if(li!=null) {
            this.value = li.longValue();
        }
    }

    /**
     * 钱数 单位是元
     * @param yuan
     * @throws NumberFormatException
     */
    public Money(String yuan) throws NumberFormatException{

        if(StringUtils.isNotBlank(yuan)){
            yuan=yuan.trim();
            if(StringValidationUtils.isNumber(yuan)){
                long l = new BigDecimal(yuan).multiply(new BigDecimal(1000)).longValue();
                this.setValue(l);
            }
            else {
                throw new NumberFormatException("money format is wrong");
            }
        }
    }


    public String toDecimalString(){
        if(this.getValue()==0){
            return "0";
        }
        else {
            String valuestr = this.getValue().toString();
            boolean isNev=this.getValue()<0;
            if(isNev){
                valuestr=StringUtils.strip("-");
            }
            if(valuestr.length()==1){
                valuestr = "0.00"+valuestr;
            }
            else if(valuestr.length()==2){
                valuestr = "0.0"+valuestr;
            }
            else if(valuestr.length()==3){
                valuestr = "0."+valuestr;
            }
            else {
                valuestr=valuestr.substring(0,valuestr.length()-3)+"."+valuestr.substring(valuestr.length()-3);
            }

            if(isNev){
                valuestr ="-"+valuestr;
            }
            return valuestr;
        }
    }

    public BigDecimal toBigDecimal(){
        if(this.getValue()==null){
            return null;
        }
        Double v = this.getValue() / 1000.0;
        return BigDecimal.valueOf(v);
    }


}
