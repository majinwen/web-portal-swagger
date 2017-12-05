package com.toutiao.web.common.extension.mybatis.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.postgresql.util.PGobject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * zhangjinglei 2017/9/15 下午8:17
 */
public class TextLtreeListTypeHandler extends BaseTypeHandler<Object> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i,
                                    Object parameter, JdbcType jdbcType) throws SQLException {
        Connection c = ps.getConnection();
        Array inArray = c.createArrayOf("text", (String[])parameter);
        ps.setArray(i, inArray);
    }
    private List<String> getStringArray(Array outputArray) throws SQLException{
        if (outputArray == null) {
            return null;
        }
        List<String> res=new ArrayList<>();

        for(Object obj : (Object[]) outputArray.getArray()){
            res.add(((PGobject)obj).getValue());
        }

        return res;//.toArray(new String[0]);
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        return getStringArray(rs.getArray(columnName));
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        return getStringArray(rs.getArray(columnIndex));
    }

    @Override
    public List<String> getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        return getStringArray(cs.getArray(columnIndex));
    }
}
