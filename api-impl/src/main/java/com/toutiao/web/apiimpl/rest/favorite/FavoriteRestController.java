package com.toutiao.web.apiimpl.rest.favorite;


import com.toutiao.app.service.favorite.FavoriteRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/favorite")
public class FavoriteRestController {

    @Autowired
    private FavoriteRestService favoriteRestService;

    @RequestMapping("/test")
    public Integer test(){
        Integer integer = favoriteRestService.queryPlotFavoriteByUserId(111);
        return integer;
    }

    @RequestMapping("/test01")
    public Integer test01(){
        Integer integer = favoriteRestService.queryPlotFavoriteByPlotId(11);
        return integer;
    }

    @RequestMapping("/test02")
    public String test02(){
        Integer integer = favoriteRestService.updateEsfFavoriteByEsfIdAndUserId(1234, 1);
        return String.valueOf(integer);
    }
}
