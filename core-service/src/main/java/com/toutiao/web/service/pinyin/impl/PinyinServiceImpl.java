package com.toutiao.web.service.pinyin.impl;

import com.toutiao.web.common.util.Pinyin4jUtil;
import com.toutiao.web.domain.pinyin.Pinyin;
import com.toutiao.web.service.pinyin.PinyinService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PinyinServiceImpl implements PinyinService{

    /**
     * 根据汉字转化拼音及拼音首字母
     * @param pinyin
     * @return
     */
    @Override
    public Pinyin getPinyin(String pinyin) {

        com.toutiao.web.domain.pinyin.Pinyin py = new com.toutiao.web.domain.pinyin.Pinyin();
        String pingYin = Pinyin4jUtil.getPingYin(pinyin);
        String pinYinHeadChar = Pinyin4jUtil.getPinYinHeadChar(pinyin);
        py.setPingYin(pingYin);
        py.setPinYinHeadChar(pinYinHeadChar);
        return py;
    }
}
