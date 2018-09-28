package com.toutiao.web.dao.mapper.officeweb;

import com.toutiao.web.dao.entity.officeweb.BuildTags;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 18710 on 2018/9/28.
 */
@Repository
public interface BuildTagsMapper {
     List<BuildTags> buildTagsList(Integer cityId);
}
