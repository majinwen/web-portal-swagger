$(function () {
    $('.map-message-btn').find('li.parent-child').addClass('current');
    $('.map-message-btn').find('li.vegetable-market').addClass('current');

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
    $(this).parents('.expand-content').find('ul.result-data-expand').addClass('none');
    $('#'+text).removeClass('none');
});

/*function renderDom(data, parentType) {
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
}*/
