Zepto(function () {
    var index;
    var chooseArr = [];
    var huxingArr = [];
    var yearArr = [];
    $('#category-nav').on('click', 'li', function () {
        index = $(this).index();
        var showDom = $('.category-cont').children().eq(index);
        $(this).toggleClass('current').siblings().removeClass('current');
        if (showDom.hasClass('none')) {
            $('.global-mark').removeClass('none');
            showDom.siblings().addClass('none').removeClass('category-roll');
            showDom.removeClass('none').addClass('category-roll')
        } else {
            showDom.addClass('none');
            $('.global-mark').addClass('none')
        }
        if (index === 0) {
            getCircleData(renderCircleDom);
        } else if (index === 3) {
            if (chooseArr == '') {
                $(this).removeClass('choose');
                $('.reset-more').click()
            }
        } else if (index === 2) {
            if (huxingArr == '') {
                $(this).removeClass('choose');
                $('.reset-huxing').click()
            } else if (yearArr == '') {
                $(this).removeClass('choose');
                $('.reset-year').click()
            }
        }
        /*$('body').on('touchmove', function (event) {
            event.preventDefault();
        }, false);*/
    });

    $('.category-cont').click(function (e) {
        e.stopPropagation();
    });
    /*
     * 位置查询
     * */
    $('.category-parent').on('click', 'li', function () {
        // event.preventDefault();
        var parentIndex = $(this).parents('.category-box').find('.current').index();

        if (parentIndex === 2) {
            $(this).addClass('current');
            return false
        }
        $(this).addClass('current').siblings().removeClass('current');

        if (parentIndex === 1) {
            $(this).parents('.category-box').find('.current').eq(0).children().find('em').text($(this).text());
            $(this).parents('.category-box').find('.current').eq(0).removeClass('current').addClass('choose');
            $(this).parents('.category-roll').removeClass('category-roll').addClass('none');
            // $('body').off('touchmove');
            $('.global-mark').addClass('none');
        }

        var place = $(this).text();
        switch (place) {
            case '区域':
                getCircleData(renderCircleDom);
                break;
            case '地铁':
                getSubwayData(renderSubwayDom);
                break;
        }
    });
    $('.reset-year').click(function () {
        $(this).parents('.category-box').find('.current').eq(0).children().find('em').text('楼龄');
        $(this).parent().prev().children('.current').removeClass('current');
    });
    $('.confrim-year').click(function () {
        var items = $(this).parent().prev().children('.current');
        for (var i = 0; i < items.length; i++) {
            yearArr.push($(items[i]).text())
        }
        if (items.length > 0) {
            $(this).parents('.category-box').find('.current').eq(0).children().find('em').text('更多');
        }
        $('.global-mark').addClass('none');
        $(this).parents('.category-box').find('.current').eq(0).removeClass('current').addClass('choose');
        $(this).parents('.category-roll').removeClass('category-roll').addClass('none');

    });

    $('.reset-huxing').click(function () {
        $(this).parents('.category-box').find('.current').eq(0).children().find('em').text('户型');
        $(this).parent().prev().children('.current').removeClass('current');
    });
    $('.confrim-huxing').click(function () {
        var items = $(this).parent().prev().children('.current');
        for (var i = 0; i < items.length; i++) {
            huxingArr.push($(items[i]).text())
        }
        if (items.length > 0) {
            $(this).parents('.category-box').find('.current').eq(0).children().find('em').text('更多');
        }
        $('.global-mark').addClass('none');
        $(this).parents('.category-box').find('.current').eq(0).removeClass('current').addClass('choose');
        $(this).parents('.category-roll').removeClass('category-roll').addClass('none');

    });
    // 三级联动
    $('.category-child').on('click', 'li', function () {
        $(this).addClass('current').siblings().removeClass('current');
        var area = $(this).parent().prev().find('.current').text();
        var index = $(this).index() - 1;
        if (index >= 0) {
            if (area === '区域') {
                var arr = circleData[index].children;
                circleChildrenDom(arr);
                return false;
            } else if (area === '地铁') {
                console.log('地铁');
            }
        }
        if ($(this).index() > 0) {
            $(this).parents('.category-box').find('.current').eq(0).children().find('em').text($(this).text());
        } else {
            var parentText = $(this).parent().prev().find('.current').text();
            $(this).parents('.category-box').find('.current').eq(0).children().find('em').text(parentText);
        }
        $(this).parents('.category-box').find('.current').eq(0).removeClass('current').addClass('choose');
        $(this).parents('.category-roll').removeClass('category-roll').addClass('none');
        // $('body').off('touchmove');
        $('.global-mark').addClass('none');
    });
    // 区域第三级dom渲染
    function circleChildrenDom(arr) {
        $('.category-children').empty();
        $('.category-children').show();
        var str = '<li class="current">不限</li>';
        for (var i = 0; i < arr.length; i++) {
            str += '<li>' + arr[i].circleName + '</li>'
        }
        $('.category-children').append(str).css('left', '66%');
    }

    // 区域点击赋值
    $('.category-children').on('click', 'li', function () {
        $(this).addClass('current').siblings().removeClass('current');
        if ($(this).index() > 0) {
            $(this).parents('.category-box').find('.current').eq(0).children().find('em').text($(this).text());
        } else {
            var parentText = $(this).parent().prev().find('.current').text();
            $(this).parents('.category-box').find('.current').eq(0).children().find('em').text(parentText);
        }
        $(this).parents('.category-box').find('.current').eq(0).removeClass('current').addClass('choose');
        $(this).parents('.category-roll').removeClass('category-roll').addClass('none');
        // $('body').off('touchmove');
        $('.global-mark').addClass('none');
    });
    /*
     * 渲染 circle dom
     * @params data 要渲染的数据
     * */
    var circleData = null; // 用来全局存储区域数据，三级联动需要
    function renderCircleDom(data) {
        circleData = data.circle;
        $('.category-child').empty();
        var str = '<li class="current">不限</li>';
        for (var i = 0; i < data.circle.length; i++) {
            str += '<li>' + data.circle[i].shortName + '</li>'
        }
        $('.category-child').append(str).css('left', '33%');
    }

    /*
     * 渲染 subway dom
     * @params data 要渲染的数据
     * */
    function renderSubwayDom(data) {
        $('.category-child').empty();
        $('.category-children').empty();
        $('.category-children').hide();
        var str = '<li class="current">不限</li>';
        for (var i = 0; i < data.subwayLine.length; i++) {
            str += '<li>' + data.subwayLine[i].shortName + '</li>'
        }
        $('.category-child').append(str).css('left', '33%');
    }

    /*
     * 获取circle json 数据
     * @params callback 回调函数
     * */
    function getCircleData(callBack) {
        // http://www.css88.com/doc/zeptojs_api/#$.ajax (文档供参考，记得删除哦)
        $('.category-parent').children().eq(0).addClass('current');
        $('.category-parent').children().eq(0).siblings().removeClass('current');
        $.ajax({
            type: 'GET', // 请求方法
            url: '/static/mock/circle.json', // 接口url地址
            dataType: 'json', // 预期服务器返回的数据类型
            success: function (response) { // 成功函数
                callBack(response)
            },
            error: function (err) { // 失败函数
                console.log(err)

            }
        })
    }

    /*
     * 获取 subway json 数据
     * @params callback 回调函数
     * */
    function getSubwayData(callBack) {
        $.ajax({
            type: 'GET',
            url: '/static/mock/subway.json',
            dataType: 'json',
            success: function (response) {
                callBack(response)
            },
            error: function (err) {
                console.log(err)
            }
        })
    }

    /*
     * 更多选择
     * */
    $('.more-options').on('click', 'span', function () {
        $(this).toggleClass('current');
    });

    $('.confrim-more').on('click', function () {
        var chooseItem = $(this).parent().prev().children().find('.current');
        for (var i = 0; i < chooseItem.length; i++) {
            if (chooseItem[i].className == 'current') {
                chooseArr.push($(chooseItem[i]).text());
            }
        }
        console.log(chooseArr);

        if (chooseArr == '') {
            $(this).parents('.category-box').find('.current').eq(0).children().find('em').text('更多');
        } else {
            $(this).parents('.category-box').find('.current').eq(0).children().find('em').text('多选');
        }
        $(this).parents('.category-box').find('.current').eq(0).removeClass('current').addClass('choose');
        $(this).parents('.category-roll').removeClass('category-roll').addClass('none');
        // $('body').off('touchmove');
        $('.global-mark').addClass('none');
        return chooseArr;
    });
    $('.reset-more').on('click', function () {
        $(this).parent().prev().find('.current').removeClass('current');
        $(this).parents('.category-box').find('.current').eq(0).children().find('em').text('更多');
        chooseArr = [];
        console.log(chooseArr);
    });
    $('.global-mark').click(function (event) {
        $('#category-nav').children().eq(index).click()
    })
});