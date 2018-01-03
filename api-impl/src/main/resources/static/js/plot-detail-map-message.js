$(function () {
    var educationUrl = 'http://api.map.baidu.com/place/v2/search?query=亲子教育&tag=亲子教育&location='+ locationnumber +'&radius=3000&radius_limit=true&scope=2&page_size=20&output=json&ak=UrflQIXBCuEZUVkwxgC3xE5y8rRPpjpS';
    var shoppingUrl = 'http://api.map.baidu.com/place/v2/search?query=菜市场&tag=菜市场&location='+ locationnumber +'&radius=3000&radius_limit=true&scope=2&page_size=20&output=json&ak=UrflQIXBCuEZUVkwxgC3xE5y8rRPpjpS';
    var hospitalUrl = 'http://api.map.baidu.com/place/v2/search?query=医院&tag=医院&location='+ locationnumber +'&radius=3000&radius_limit=true&scope=2&page_size=3&output=json&ak=UrflQIXBCuEZUVkwxgC3xE5y8rRPpjpS';
    var busUrl = 'http://api.map.baidu.com/place/v2/search?query=公交车站&tag=公交车站&location='+ locationnumber +'&radius=3000&scope=2&page_size=20&output=json&ak=UrflQIXBCuEZUVkwxgC3xE5y8rRPpjpS';
    var subwayUrl = 'http://api.map.baidu.com/place/v2/search?query=地铁站&tag=地铁站&location=' + locationnumber + '&radius=3000&scope=2&page_size=20&output=json&ak=UrflQIXBCuEZUVkwxgC3xE5y8rRPpjpS';

    $.ajax({
        type: 'GET',
        url: educationUrl,
        dataType: 'jsonp',
        success: function (response) {
            if (response.message === 'ok') {
                var array = response.results;
                array.sort(function(a,b){
                    return a.detail_info.distance-b.detail_info.distance;
                });
                var arr = array.slice(0,5);
                $('.map-message-btn').find('li.parent-child').addClass('current');
                renderDom(arr, '教育培训');
            }
        },
        error: function (err) {
            console.log(err);
        }
    });
    $.ajax({
        type: 'GET',
        url: shoppingUrl,
        dataType: 'jsonp',
        success: function (response) {
            if (response.message === 'ok') {
                var array = response.results;
                array.sort(function(a,b){
                    return a.detail_info.distance-b.detail_info.distance;
                });
                var arr = array.slice(0,5);
                $('.map-message-btn').find('li.vegetable-market').addClass('current');
                renderDom(arr, '休闲购物');
            }
        },
        error: function (err) {
            console.log(err);
        }
    });
    $.ajax({
        type: 'GET',
        url: hospitalUrl,
        dataType: 'jsonp',
        success: function (response) {
            if (response.message === 'ok') {
                var array = response.results;
                array.sort(function(a,b){
                    return a.detail_info.distance-b.detail_info.distance;
                });
                var arr = array.slice(0,5);
                renderDom(arr, '医疗配套');
            }
        },
        error: function (err) {
            console.log(err);
        }
    });
    $.ajax({
        type: 'GET',
        url: busUrl,
        dataType: 'jsonp',
        success: function (response) {
            if (response.message === 'ok') {
                if (response.results.length > 0){
                    var array = response.results;
                    array.sort(function(a,b){
                        return a.detail_info.distance-b.detail_info.distance;
                    });
                    var arr = array.slice(0,5);
                    var lineNumber = (arr[0].address).split(';').length;
                    $('#busStation').text(arr[0].name);
                    $('#busStationNumber').text(lineNumber + '条线路')
                }
            }
        },
        error: function (err) {
            console.log(err);
        }
    });
    $.ajax({
        type: 'GET',
        url: subwayUrl,
        dataType: 'jsonp',
        success: function (response) {
            if (response.message === 'ok') {
                if (response.results.length > 0) {
                    var array = response.results;
                    array.sort(function(a,b){
                        return a.detail_info.distance-b.detail_info.distance;
                    });
                    var arr = array.slice(0,5);
                    // console.log(array)
                    var subwayLine = (arr[0].address).split(';')[0].substring(2);
                    var subwayDistance = (((arr[0].detail_info.distance).toFixed(0))/100/10).toFixed(1) + 'km';
                    $('#subwayLine').text(arr[0].name　+'['+　subwayLine+']');
                    $('#subwayDistance').text(subwayDistance);
                }
            }
        },
        error: function (err) {
            console.log(err);
        }
    });
});

$('.map-message-btn').on('click', 'li', function () {
    $(this).addClass('current').siblings().removeClass('current');
    var text = $(this).attr('data-type');
    var parentText = $(this).parent().attr('data-type');

    if (parentText == '教育培训') {
        $(this).removeClass('choose');
        $(this).prevAll().addClass('choose');
        $(this).nextAll().removeClass('choose');
    }
    var url = 'http://api.map.baidu.com/place/v2/search?query=' + text + '&tag=' + text + '&location=' + locationnumber + '&radius=3000&radius_limit=true&scope=2&page_size=20&output=json&ak=UrflQIXBCuEZUVkwxgC3xE5y8rRPpjpS';
    $.ajax({
        type: 'GET',
        url: url,
        dataType: 'jsonp',
        success: function (response) {
            if (response.message === 'ok') {
                var array = response.results;
                array.sort(function(a,b){
                    return a.detail_info.distance-b.detail_info.distance;
                });
                var arr = array.slice(0,5);
                renderDom(arr, parentText);
            }
        },
        error: function (err) {
            console.log(err)
        }
    })
});

function renderDom(data, parentType) {
    var str = '';
    var star;
    switch (parentType) {
        case '教育培训':
            for (var i = 0; i < data.length; i++) {
                var index = i + 1;
                // console.log(data[i].detail_info.distance);
                var distances = (Math.round(data[i].detail_info.distance/100)/10).toFixed(1) + "km";
                str += '<li><p><i class="expand-icon expand-radius">' + index + '</i>' +
                    '<span class=expand-name>' + data[i].name +
                    '</span></p><span class="expand-distance">' + distances +
                    '</span></li>';
            }
            $('#educationListDom').empty();
            $('#educationListDom').append(str);
            break;
        case '休闲购物':
            for (var i = 0; i < data.length; i++) {
                var starStr = '';
                var index = i + 1;
                var distances = (Math.round(data[i].detail_info.distance/100)/10).toFixed(1) + "km";
                if (data[i].detail_info.overall_rating != undefined) {
                    star = Math.round(data[i].detail_info.overall_rating);
                    // console.log(star);
                    if (star > 0) {
                        for (var j = 0; j < 5; j++){
                            if (j < star) {
                                starStr += '<i class="red-star"></i>';
                            } else {
                                starStr += '<i class="star-icon"></i>';
                            }
                        }
                    }
                    str += '<li><p><i class="expand-icon expand-radius">' + index + '</i>' +
                        '<span class=expand-name><em>' + data[i].name +
                        '</em><em class="star-box">' + starStr + '</em>' +
                        '</span></p><span class="expand-distance">' + distances +
                        '</span></li>';

                } else {
                    str += '<li><p><i class="expand-icon expand-radius">' + index + '</i>' +
                        '<span class=expand-name><em>' + data[i].name + '</em>' +
                        '</span></p><span class="expand-distance">' + distances +
                        '</span></li>';
                }

            }
            $('#shoppintListDom').empty();
            $('#shoppintListDom').append(str);
            break;
        case '医疗配套':
            if (data.length <= 0) {
                $('#hospitalListWrapper').addClass('none');
            } else {
                for (var i = 0; i < data.length; i++) {
                    var index = i + 1;
                    var distances = (Math.round(data[i].detail_info.distance/100)/10).toFixed(1) + "km";
                    str += '<li><p><i class="expand-icon medical-treatment"></i>' +
                        '<span class=expand-name>' + data[i].name +
                        '</span></p><span class="expand-distance">' + distances +
                        '</span></li>';
                }
                $('#hospitalListDom').empty();
                $('#hospitalListDom').append(str);
            }
            break;
    }
}