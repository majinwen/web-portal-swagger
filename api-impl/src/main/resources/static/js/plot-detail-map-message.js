$(function () {
    var educationUrl = 'http://api.map.baidu.com/place/v2/search?query=亲子&location=39.915,116.404&radius=3000&scope=2&page_size=5&distance&output=json&ak=qecR0qeVFD5yOk8NvT5aDLNjgWiKHbaf';
    var shoppingUrl = 'http://api.map.baidu.com/place/v2/search?query=菜市场&location=39.915,116.404&radius=3000&scope=2&page_size=5&distance&output=json&ak=qecR0qeVFD5yOk8NvT5aDLNjgWiKHbaf';
    var hospitalUrl = 'http://api.map.baidu.com/place/v2/search?query=医院&location=39.915,116.404&radius=3000&scope=2&page_size=3&distance&output=json&ak=qecR0qeVFD5yOk8NvT5aDLNjgWiKHbaf';
    $.ajax({
        type: 'GET',
        url: educationUrl,
        dataType: 'jsonp',
        success: function (response) {
            if (response.message === 'ok') {
                $('.map-message-btn').find('li.parent-child').addClass('current');
                renderDom(response.results, '教育配套');
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
                $('.map-message-btn').find('li.vegetable-market').addClass('current');
                renderDom(response.results, '休闲购物');
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
                renderDom(response.results, '医疗配套');
            }
        },
        error: function (err) {
            console.log(err);
        }
    });
});

$('.map-message-btn').on('click', 'li', function () {
    $(this).addClass('current').siblings().removeClass('current');
    var text = $(this).text();
    var parentText = $(this).parent().attr('data-type');

    if (parentText == '教育配套') {
        $(this).removeClass('choose');
        $(this).prevAll().addClass('choose');
        $(this).nextAll().removeClass('choose');
    }
    var url = 'http://api.map.baidu.com/place/v2/search?query=' + text + '&location=39.915,116.404&radius=3000&scope=2&page_size=5&distance&output=json&ak=qecR0qeVFD5yOk8NvT5aDLNjgWiKHbaf';
    $.ajax({
        type: 'GET',
        url: url,
        dataType: 'jsonp',
        success: function (response) {
            if (response.message === 'ok') {
                renderDom(response.results, parentText);
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
        case '教育配套':
            for (var i = 0; i < data.length; i++) {
                var index = i + 1;
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
                    for (var j=0; j< star; j++){
                        starStr += '<i class="star-icon"></i>';
                    }
                    str += '<li><p><i class="expand-icon expand-radius">' + index + '</i>' +
                        '<span class=expand-name><em>' + data[i].name +
                        '</em><em class="star-box">' + starStr + '</em>' +
                        '</span></p><span class="expand-distance">' + distances +
                        '</span></li>';

                } else {
                    str += '<li><p><i class="expand-icon expand-radius">' + index + '</i>' +
                        '<span class=expand-name>' + data[i].name +
                        '</span></p><span class="expand-distance">' + distances +
                        '</span></li>';
                }

            }
            $('#shoppintListDom').empty();
            $('#shoppintListDom').append(str);
            break;
        case '医疗配套':
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
            break;
    }
}