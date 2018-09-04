package com.toutiao.web.common.extension.mybatis.typehandler;

import com.toutiao.web.common.util.JSONUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author WuShoulei on 2017/12/13
 */
public class JSONTypeHandler extends BaseTypeHandler<Object> {

    private Class clazz;

    public JSONTypeHandler(Class clazz) {
        if (null == clazz) {
            throw new IllegalArgumentException("Type argument can not be null!");
        }
        this.clazz = clazz;
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, JSONUtil.stringfy(parameter));
    }

    @Override
    public Object getNullableResult(ResultSet resultSet, String columnName) throws SQLException {

        return JSONUtil.parse(resultSet.getString(columnName), clazz);
    }

    @Override
    public Object getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {

        return JSONUtil.parse(resultSet.getString(columnIndex), clazz);
    }

    @Override
    public Object getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {

        return JSONUtil.parse(callableStatement.getString(columnIndex), clazz);
    }
}
