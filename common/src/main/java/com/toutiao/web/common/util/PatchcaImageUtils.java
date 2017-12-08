package com.toutiao.web.common.util;

import com.github.bingoohuang.patchca.color.SingleColorFactory;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.filter.predefined.CurvesRippleFilterFactory;
import com.github.bingoohuang.patchca.font.RandomFontFactory;
import com.github.bingoohuang.patchca.word.RandomWordFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PatchcaImageUtils {

    /**
     * 验证码
     *
     * @return
     */
    public static ConfigurableCaptchaService getCs() {
        ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
        cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
        RandomFontFactory r = new RandomFontFactory();
        r.setMaxSize(16);
        r.setMaxSize(12);
        r.setRandomStyle(true);
        List<String> list = new ArrayList<String>();
        list.add("Cursive");
        r.setFamilies(list);
        cs.setFontFactory(r);
        cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
        cs.setWidth(120);
        cs.setHeight(60);
        cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
        // AdaptiveRandomWordFactory t = new AdaptiveRandomWordFactory();
        RandomWordFactory t = new RandomWordFactory();
        //0123456789abcdefghijklmnopqrstuvwsyz
        t.setCharacters("ABCDEFGHJKLMNOPQRSTUVWSYZ123456789");
        t.setMaxLength(4);
        t.setMinLength(4);
        cs.setWordFactory(t);
        return cs;
    }
}
