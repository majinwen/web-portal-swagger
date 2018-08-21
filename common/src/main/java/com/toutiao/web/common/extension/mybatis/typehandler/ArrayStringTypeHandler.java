package com.toutiao.web.common.extension.mybatis.typehandler;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.*;
@MappedTypes({String.class})
@MappedJdbcTypes({JdbcType.ARRAY})
public class ArrayStringTypeHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {
        String[] strings = s.split(",");
        Connection conn = preparedStatement.getConnection();
        Array array = conn.createArrayOf("varchar",strings);
        preparedStatement.setArray(i, array);
    }

    @Override
    public String getNullableResult(ResultSet resultSet, String s) throws SQLException {
        Integer[] array = (Integer[]) resultSet.getArray(s).getArray();
        return this.getStringArray(array);
    }

    @Override
    public String getNullableResult(ResultSet resultSet, int i) throws SQLException {
        Integer[] array = (Integer[]) resultSet.getArray(i).getArray();
        return this.getStringArray(array);
    }

    @Override
    public String getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        Integer[] array = (Integer[]) callableStatement.getArray(i).getArray();
        return this.getStringArray(array);
    }

    private String getStringArray(Integer[] columnValue) {
        if (columnValue == null||columnValue.length==0){
            return null;
        }
        return StringUtils.join(columnValue," ");
    }
}
