var Map = {
    map: null,
    markerico: new BMap.Icon('/static/images/map/zbpt_icon_zb@3x.png', new BMap.Size(36, 50), {anchor: new BMap.Size(9, 44), imageSize: new BMap.Size(36,50)}),
    markerico2: new BMap.Icon('/static/images/map/zbpt_icon_zb_black1@3x.png', new BMap.Size(22, 34),{anchor: new BMap.Size(11, 38), imageSize: new BMap.Size(22,34)}),
    markers: [],
    circle: null,
    defaultcenterdpoint: null,
    currentpanorama: null,
    results: [],
    currentinfobox: null,
    init: function(x, y, z, c, title){
        var self = this;
        map = new BMap.Map('mapContainer', {enableMapClick: false});	// 构造底图时，关闭底图可点功能
        map.centerAndZoom(new BMap.Point(x, y), z);
        if(!c){
            map.disableScrollWheelZoom();	// 禁用滚轮放大缩小
            map.setMinZoom(11);
            map.setMaxZoom(18);
        }else{
            map.enableScrollWheelZoom();	// 启用滚轮放大缩小
            map.setMinZoom(11);
            map.setMaxZoom(18);
            // 创建标注
            var pt = new BMap.Point(x,y);
            self.defaultcenterdpoint = pt;
            var markerCenter = self.getmarker(pt, {'x': x, 'y': y, 'title': title, 'content': '', ico: self.markerico});
            map.addOverlay(markerCenter);

        }
        if (self.circle == null) {
            self.circle = new BMap.Circle(self.defaultcenterdpoint, 3000, {
                fillColor: '',
                strokeWeight: 0.1,
                fillOpacity: 0,
                strokeOpacity: 0
            });
            map.addOverlay(self.circle);
        }
    },
    load: function(data){
        var self = this;
        self.addmarkers(data);
    },
    addmarkers: function(data){
        var self = this;
        for (var i = 0; i < data.length; i++) {
            var x = data[i].x,
                y = data[i].y;
            var pt = new BMap.Point(x, y);
            var marker = self.getmarker(pt, data[i],i);
            map.addOverlay(marker);
            self.markers.push(marker);
        }
    },
    getmarker: function(pt, data,index){		// 创建标注
        var self = this;
        var marker = new BMap.Marker(pt);
        marker.title = data.title;
        marker.content = data.content;
        marker.id = data.id;
        marker.cb = data.callback;
        if(data.ico){
            marker.setIcon(data.ico);
        }else{
            marker.setIcon(self.markerico2);
            var label = new BMap.Label(index+1,{offset:new BMap.Size(0,1)});
            label.setStyle({
                backgroundColor: 'transparent',
                color: '#fff',
                border: 'none',
                width: '20px',
                height: '23px',
                lineHeight:'23px',
                textAlign: 'center'
            });
            marker.setLabel(label);
        };
        return marker;
    },
    clearmarkers: function(){
        var self = this;
        for (var i = 0; i < self.markers.length; i++){
            map.removeOverlay(self.markers[i]);
        }
        self.markers = [];
    },
    search: function (word, cb, obj) {
        var self = this;
        var options = {
            onSearchComplete: function(results){
                var s = [];
                for (var i = 0; i < results.getCurrentNumPois(); i++) {
                    var temp = results.getPoi(i);
                    var _distance = map.getDistance(self.circle.getCenter(),results.getPoi(i).point);
                    var distanceStr;
                    if (1000 < _distance) {
                        distanceStr = ((_distance.toFixed(0))/100/10).toFixed(1) + 'km';
                    } else {
                        distanceStr = parseInt(_distance) + 'm';
                    }
                    s.push({
                        'title': temp.title, 'content': temp.address, 'x': temp.point.lng, 'y': temp.point.lat, 'distance': distanceStr
                    });
                }
                self.results = s;

                if (obj) {
                    $(obj).html('');
                    var _resultHtml = '';
                    var _end = s.length>5?5:s.length;
                    for (var rIndex in s) {
                        if (rIndex < _end) {
                            var _num = parseInt(rIndex) + 1;
                            var result = s[rIndex];
                            _resultHtml += '<li>' +
                                '<p><i class="expand-icon expand-radius">' + _num + '</i><span class="expand-name">'+ result['title'] +'</span></p>' +
                                '<span class="expand-distance">'+ result['distance'] + '</span>' +
                                '</li>';
                        }
                    }
                    $(obj).html(_resultHtml);
                }
                else {
                    cb(s);
                }
            }
        };

        var local = new BMap.LocalSearch(map, options);
        local.searchNearby(word, self.circle.getCenter(), 3000, {});
    }
};