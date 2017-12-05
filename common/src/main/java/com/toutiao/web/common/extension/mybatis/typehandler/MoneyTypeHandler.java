package com.toutiao.web.common.extension.mybatis.typehandler;

import com.toutiao.web.common.commonmodel.Money;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.poi.ss.formula.functions.T;

import java.sql.*;

/**
 * zhangjinglei 2017/9/8 下午5:06
 * 暂时没啥用
 */
@MappedJdbcTypes(JdbcType.BIGINT)
public class MoneyTypeHandler extends BaseTypeHandler<Money> {
    private Class<T> type;
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Money money, JdbcType jdbcType) throws SQLException {
    }

    @Override
    public Money getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return null;
    }

    @Override
    public Money getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    @Override
    public Money getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }

//    @Override
//    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Money money, JdbcType jdbcType) throws SQLException {
//        if(money==null||money.getValue()==null) {
//            preparedStatement.setNull(i, Types.BIGINT);
//        }
//        else {
//            preparedStatement.setLong(i,money.getValue());
//        }
//    }
//
//    @Override
//    public Money getNullableResult(ResultSet resultSet, String s) throws SQLException {
//         String res = resultSet.getString(s);
//         if(res!=null){
//             return new Money(Integer.valueOf(res.trim()));
//         }
//         return null;
//    }
//
//    @Override
//    public Money getNullableResult(ResultSet resultSet, int i) throws SQLException {
//        String res = resultSet.getString(i);
//        if(res!=null){
//            return new Money(Integer.valueOf(res.trim()));
//        }
//        return null;
//    }
//
//    @Override
//    public Money getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
//        String res = callableStatement.getString(i);
//        if(res!=null){
//            return new Money(Integer.valueOf(res.trim()));
//        }
//        return null;
//    }
}
