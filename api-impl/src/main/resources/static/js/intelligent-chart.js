var dpr = window.devicePixelRatio;
var baseFontSize = 12 * dpr;
var baseItemWidth = 25 * dpr;

var locationBaseUrl = '';   // 从url中获取的id

/**
 * 报告页图表集合
 * */
$(function () {
    var locationUrl = location.href;
    locationBaseUrl = locationUrl.substr(locationUrl.lastIndexOf('/')+1);

    /**
     * 市场价格走势
     * */
    var priceChart = echarts.init(document.getElementById('priceChart'), null, {renderer: 'svg'}, {devicePixelRatio: dpr ,width: '100%', height: '100%'});
    // 显示标题，图例和空的坐标轴
    priceChart.setOption({
        color: ['#455765', '#f25a5a'],
        textStyle: {fontSize: baseFontSize},
        tooltip: {
            trigger: 'axis',
            textStyle: {fontSize: baseFontSize}
        },
        legend: {
            itemGap: 20,
            itemWidth: baseItemWidth,
            data: [{
                name: '北京市场',
                icon: 'line'
            },{
                name: '目标市场',
                icon: 'line'
            }]
        },
        grid: {
            left: '2%',
            right: '6%',
            bottom: 0,
            containLabel: true
        },
        xAxis: {
            show: true,
            boundaryGap: false,
            scale: true,
            axisLabel: {fontSize: baseFontSize - 10},
            data: []
        },
        yAxis: {
            scale: true,
            axisLine: {show: true},
            axisTick: {show: false},
            axisLabel: {show: false},
            splitLine: {show: false}
        },
        dataZoom: [
            {
                type: 'inside',
                start: 50,
                end: 100,
                filterMode: 'empty',
                zoomLock: true
            }
        ],
        series: [{
            name: '北京市场',
            type: 'line',
            showSymbol: false,
            data: []
        },{
            name: '目标市场',
            type: 'line',
            showSymbol: false,
            data: []
        }]
    });
    priceChart.showLoading();
    $.get(router_city('/findhouse/queryPt')).done(function (data) {
        // console.log(data);
        priceChart.hideLoading();
        var maxTarget = data.data.maxTarget,
            minTarget = Math.abs(data.data.minTarget),
            baseTarget = data.data.target;

        $('#maxTarget').text(maxTarget + '%');
        $('#minTarget').text(minTarget + '%');
        if (maxTarget > baseTarget) {
            $('#priceMaxCompare').text('高');
        } else {
            $('#priceMaxCompare').text('低');
        }
        if (minTarget > baseTarget) {
            $('#priceMinCompare').text('高');
        } else {
            $('#priceMinCompare').text('低');
        }

        var totalPriceArr = [],
            priceArr = [],
            priceTimeArr = [];
        var priceChartData = data.data.ptlists;
        for (var i = priceChartData.length - 1; i >= 0; i--) {
            totalPriceArr.push(priceChartData[i].totalPrice);
            priceArr.push(priceChartData[i].price);
            priceTimeArr.push(priceChartData[i].periodicTime)
        }
        var chartOptions = {
            xAxis: {
                data: ['2017-12', '2017-11', '2017-10', '2017-09', '2017-08', '2017-07', '2017-06', '2017-05', '2017-04', '2017-03', '2017-02', '2017-01']
            },
            series: [{
                // 根据名字对应到相应的系列
                name: '北京市场',
                data: totalPriceArr
            },{
                // 根据名字对应到相应的系列
                name: '目标市场',
                data: priceArr
            }]
        };


        priceChart.setOption(chartOptions);
    });


    /**
     * 市场供需情况
     * */
    var marketChart = echarts.init(document.getElementById('marketChart'), null, {renderer: 'svg'}, {devicePixelRatio: dpr ,width: '100%', height: '100%'});
    // 显示标题，图例和空的坐标
    marketChart.setOption({
        textStyle: {fontSize: baseFontSize},
        tooltip: {
            show: true,
            trigger: 'axis',
            textStyle: {fontSize: baseFontSize},
            axisPointer: {type: 'shadow'}
        },
        legend: {
            itemGap: 20,
            itemWidth: baseItemWidth,
            data: [{
                name: '北京市场',
                icon: 'line'
            },{
                name: '目标市场',
                icon: 'line'
            }]
        },
        grid: {
            left: '2%',
            right: '6%',
            bottom: 0,
            containLabel: true
        },
        yAxis:  {
            type: 'value',
            axisTick: {show: false},
            axisLine: {show: false},
            splitLine: {show: false},
            axisLabel: {show: false}
        },
        xAxis: [
            {
                type: 'category',
                axisTick: {show: false},
                axisLine: {show: true},
                axisLabel: {fontSize: baseFontSize - 10},
                data: []
            }, {
                type: 'category',
                axisLine: {show: false},
                axisTick: {show: false},
                axisLabel: {show: false},
                splitArea: {show: false},
                splitLine: {show: false},
                data: []
            }
        ],
        series: [
            {
                name: '北京市场',
                type: 'bar',
                xAxisIndex: 1,
                itemStyle: {
                    normal: {
                        show: true,
                        color: '#277ace',
                        barBorderRadius: 10,
                        borderWidth: 0,
                        borderColor: '#333'
                    }
                },
                barGap: '0%',
                barCategoryGap: '70%',
                data: []
            },
            {
                name: '目标市场',
                type: 'bar',
                itemStyle: {
                    normal: {
                        show: true,
                        color: '#5de3e1',
                        barBorderRadius: 10,
                        borderWidth: 0,
                        borderColor: '#333'
                    }
                },
                barGap: '0%',
                barCategoryGap: '70%',
                data: []
            }
        ]
    });

    marketChart.showLoading();
    $.get(router_city('/findhouse/queryTd')).done(function (data) {
        marketChart.hideLoading();
        console.log(data);
        var maxVolume = data.data.ratio.maxVolume,
            maxVolumeRatio = data.data.ratio.maxVolumeRatio,
            minVolume = data.data.ratio.minVolume,
            minVolumeRatio = data.data.ratio.minVolumeRatio,
            averageVolume = data.data.ratio.averageVolume,
            averageVolumeRatio = data.data.ratio.averageVolumeRatio;

        $('#maxVolume').text(maxVolume + '套');
        $('#maxVolumeRatio').text(maxVolumeRatio);
        $('#minVolume').text(minVolume + '套');
        $('#minVolumeRatio').text(minVolumeRatio);
        $('#averageVolume').text(averageVolume + '套');
        $('#averageVolumeRatio').text(averageVolumeRatio);

        var marketAllArr = [],
            markerTargetArr = [],
            periodicTimeArr = [];

        var marketChartArr = data.data.trend;
        for (var i = marketChartArr.length - 1; i >= 0; i--) {
            marketAllArr.push(marketChartArr[i].allSd);
            markerTargetArr.push(marketChartArr[i].targetSd);
            periodicTimeArr.push(marketChartArr[i].periodicTime)
        }
        marketChart.setOption({
            xAxis: [{
                data: periodicTimeArr
            },{
                data: periodicTimeArr
            }],
            series: [{
                data: marketAllArr
            },{
                data: markerTargetArr
            }]
        });
        console.log(locationBaseUrl);
    });


    $.get(router_city('/findhouse/showMyReport/' + locationBaseUrl)).done(function (data) {
        console.log(data);
    })
});

function router_city(urlparam) {
    urlparam = urlparam || '';
    if (urlparam[0] != '/') {
        urlparam = '/' + urlparam
    }
    var uri = new URI(window.location.href);
    var segmens = uri.segment();
    var city = '';
    if (segmens.length > 0) {
        city = '/' + segmens[0]
    }
    return city + urlparam
}

/**
 * 拼接参数
 * @param req
 * @param sortFlag 非排序时排序置空
 * @returns {string}
 */
function joinParams(req) {
    var targetUrl = '';

    for (var key in req) {
        if (null != req[key]) {
            targetUrl += '&' + key + "=" + req[key];
        }
    }

    if (targetUrl.length > 1) {
        targetUrl = '?' + targetUrl.substring(1);
    }

    return targetUrl;
}