$.namespace = function() {
    var a=arguments, o=null, i, j, d;
    for (i=0; i<a.length; i=i+1) {
        d=a[i].split(".");
        o=window;
        for (j=0; j<d.length; j=j+1) {
            o[d[j]]=o[d[j]] || {};
            o=o[d[j]];
        }
    }
    return o;
};
$.namespace("$com.toutiao.ad.jsonp");
$com.toutiao.ad.json=function (config) {
    config = config || [];
    $.each(config,function (index, ele) {
        if(ele) {
            $.ajax({
                url: '/ad/jsapi/json',
                data: {"positionid": ele["pid"]},
                cache:false,
                success: function (data) {
                    var dom=$(data.html);
                    if(ele["jqid"]) {
                        $(ele["jqid"]).html(dom);
                    }
                    if(ele["callback"]){
                        try {
                            ele.callback(dom);
                        }
                        catch (error){
                            console.error(error);
                        }
                    }

                },
                dataType: 'json'
            });

        }
    })
};


$com.toutiao.ad.json_chain = function (config) {
    config = config || [];
    $.each(config,function (index, ele) {
        if(ele) {
            $.ajax({
                url: '/ad/jsapi/json',
                data: {"positionid": ele["pid"]},
                cache:false,
                async:false,
                success: function (data) {
                    if(data.html !="" && data.html!= undefined){
                    var dom=$(data.html);

                        if(ele["jqid"]) {
                            $(ele["jqid"]).html(dom);
                        }
                        if(ele["callback"]){
                            try {
                                ele.callback(dom);
                            }
                            catch (error){
                                console.error(error);
                            }
                        }
                    }
                },
                dataType: 'json'
            });

        }
    })
};
