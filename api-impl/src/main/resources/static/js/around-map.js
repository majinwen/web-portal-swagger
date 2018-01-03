/*
	自定义控件
	* 定义一个自定义控件的构造函数
	* 设置自定义控件构造函数的prototyoe属性为Control的实例，以便继承控件基类
	* 实现initialize()方法并提供defaultAnchor和defaultOffset属性 */

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
	search: function (word,dtd) {
        var self = this;
        if (word.length == 0) {
            if (self.local != null) {
                local.clearResults();
            }
            map.removeOverlay(self.circle);
            self.circle = null;
        } else {
            if (self.circle == null) {
                self.circle = new BMap.Circle(self.defaultcenterdpoint, 3000, {
                    fillColor: '',
                    strokeWeight: 0.1,
                    fillOpacity: 0,
                    strokeOpacity: 0
                });
                map.addOverlay(self.circle);
            }
            if (self.local != null) {
                self.local.clearResults();
            } else {
                self.local = new BMap.LocalSearch(map);
            }
            self.local.searchNearby(word, self.circle.getCenter(), 3000, {});
            self.local.setSearchCompleteCallback(function (results) {
                var s = [];
                for (var i = 0; i < results.getCurrentNumPois(); i++) {
                    var temp = results.getPoi(i);
                    var distances = (((map.getDistance(self.circle.getCenter(),results.getPoi(i).point).toFixed(0))/100/10).toFixed(1) + 'km');
                    s.push({
                        'title': temp.title, 'content': temp.address, 'x': temp.point.lng, 'y': temp.point.lat, 'distance': distances
                    });
                }
                self.results = s;
                self.clearmarkers();
                self.addmarkers(s);
                dtd.resolve();
            });
        }
        return dtd;
    }
};

$(function(){
	// 默认显示
	var dtd = $.Deferred();
    var mapresults = [];
	// 添加子类型
	$('#typeNavSel').empty();
	toggleNav('#mapTypeNav li.current','#typeNavSel','currenttype');
    mapSearch($('#typeNavSel li.currenttype').text(),dtd);

	// 点击切换显示
	$('#mapTypeNav .map-type-item').click(function(){
		$(this).addClass('current').siblings().removeClass('current');
		$('#typeNavSel').empty();
		toggleNav('#mapTypeNav li.current','#typeNavSel','currenttype');
        $('.results-title i').attr('class', $(this).attr('data-icon') + '');
		$('.results-title .title-map-type').text($('#typeNavSel').find('.currenttype').text());
		var dtd2 = $.Deferred();
		$('#resultsPanel').empty();

        mapSearch($('#typeNavSel li.currenttype').text(),dtd2);
	});

	$('#typeNavSel').on('click','li',function(){
		$('#resultsPanel').empty();
		$(this).addClass('currenttype').siblings().removeClass('currenttype');
		$('.results-title .title-map-type').text($(this).text());
		var dtd1 = $.Deferred();

		mapSearch($(this).text(),dtd1);
	});

    function mapSearch(text,dtd) {
        Map.search(text,dtd).done(function () {
            mapresults = Map.results;
            console.log(mapresults);
            if ($('#typeNavSel li.currenttype').text() == '地铁站') {
                for(var i = 0; i < mapresults.length; i++){
                    $('#resultsPanel').append('<li><i>' + (i+1) + '</i><p><span class="content">' + mapresults[i].title + '</span><em class="line">(' + mapresults[i].content + ')</em></p><em class="distance">---- ' + mapresults[i].distance + '</em></li>');
                }
            } else if ($('#typeNavSel li.currenttype').text() == '公交站') {
                var contents;
                for(var i = 0; i < mapresults.length; i++){
                    contents = (mapresults[i].content).split('').length;
                    $('#resultsPanel').append('<li><i>' + (i+1) + '</i><p><span class="content">' + mapresults[i].title + '</span><em class="line">(共' + contents + '条线路)</em></p><em class="distance">---- ' + mapresults[i].distance + '</em></li>');
                }
            } else {
                for(var i = 0; i < mapresults.length; i++){
                    $('#resultsPanel').append('<li><i>' + (i+1) + '</i><p><span class="content">' + mapresults[i].title + '</span></p><em class="distance">---- ' + mapresults[i].distance + '</em></li>');
                }
            }
            $('.results-title .result-num').text($('#resultsPanel').find('li').length);
        })

    }
});
var dataKey = [];
function toggleNav(nav,navItem,classname){
	dataKey = $(nav).attr('data-key').split(',');
	for(var i = 0; i < dataKey.length; i++){
		$(navItem).append('<li>'+dataKey[i]+ '</li>');
	}
	$(navItem).find('li:first').addClass(classname);
}
function toggleType(select, classname){
	$(select).click(function(){
		$(this).addClass(classname).siblings().removeClass(classname);
	});
}