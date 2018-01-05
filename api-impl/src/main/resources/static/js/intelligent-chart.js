var dpr = window.devicePixelRatio;
var baseFontSize = 12 * dpr;
var baseItemWidth = 25 * dpr;

var locationBaseUrl = 0;   // 从url中获取的id

/**
 * 报告页图表集合
 * */
$(function () {
    var locationUrl = window.location.href;
    locationBaseUrl = parseInt(locationUrl.substr(locationUrl.lastIndexOf('/') + 1));
    console.log(locationBaseUrl);

    var chartGrid = {
        left: 0,
        right: '6%',
        bottom: 0,
        containLabel: true
    };

    /**
     * 市场价格走势
     * */
    var priceChart = echarts.init(document.getElementById('priceChart'), null, {renderer: 'svg'}, {devicePixelRatio: dpr ,width: '100%', height: '100%'});
    // 显示标题，图例和空的坐标轴
    priceChart.setOption({
        color: ['#455765', '#f25a5a', '#fecebc', '#7f7f7f', '#4a7aa3'],
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
        grid: chartGrid,
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
        grid: chartGrid,
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

    /**
     * 地铁信息
     * */
    var trafficSubwayChart = echarts.init(document.getElementById('trafficSubwayChart'), null, {renderer: 'svg'}, {devicePixelRatio: dpr ,width: '100%', height: '100%'});
    // 显示标题，图例和空的坐标

    var trafficSubwayLabel = {
        normal: {
            show: true,
            position: 'bottom',
            color: '#666',
            fontSize: baseFontSize - 5,
            formatter: '{c}km\n大望路站'
        }
    };
    trafficSubwayChart.setOption({
        color: ['#455765', '#f25a5a', '#fecebc', '#7f7f7f', '#4a7aa3'],
        textStyle: {fontSize: baseFontSize},
        grid: {
            left: 0,
            right: '6%',
            bottom: 0,
            top: 0,
            containLabel: true
        },
        xAxis: {
            show: false,
            data: []
        },
        yAxis:  {
            show: false,
            inverse: true,
            min: 0,
            max: 3
        },
        series: [
            {
                name: '',
                type: 'bar',
                label: trafficSubwayLabel,
                barGap: 1.5,
                barWidth: '8%',
                data: [2]
            },
            {
                name: '',
                type: 'bar',
                label: trafficSubwayLabel,
                barGap: 1.5,
                barWidth: '8%',
                data: [1]
            },
            {
                name: '',
                type: 'bar',
                label: trafficSubwayLabel,
                barGap: 1.5,
                barWidth: '8%',
                data: [1]
            },
            {
                name: '',
                type: 'bar',
                barGap: 1.5,
                barWidth: '8%',
                data: [0]
            },
            {
                name: '',
                type: 'bar',
                barGap: 1.5,
                barWidth: '8%',
                data: [0]
            }
        ]
    });
    // trafficSubwayChart.showLoading();



    /**
     * 环线桥信息
     * */
    var trafficRondChart = echarts.init(document.getElementById('trafficRondChart'), null, {renderer: 'svg'}, {devicePixelRatio: dpr ,width: '100%', height: '100%'});
    // 显示标题，图例和空的坐标

    var trafficRondLabel = {
        normal: {
            show: true,
            position: 'bottom',
            color: '#666',
            fontSize: baseFontSize - 5,
            formatter: '{c}km\n大望路站'
        }
    };
    trafficRondChart.setOption({
        color: ['#455765', '#f25a5a', '#fecebc', '#7f7f7f', '#4a7aa3'],
        textStyle: {fontSize: baseFontSize},
        grid: {
            left: 0,
            right: '6%',
            bottom: 0,
            top: 0,
            containLabel: true
        },
        xAxis: {
            show: false,
            data: []
        },
        yAxis:  {
            show: false,
            inverse: true,
            min: 0,
            max: 3
        },
        series: [
            {
                name: '',
                type: 'bar',
                label: trafficRondLabel,
                barGap: 1.5,
                barWidth: '8%',
                data: [2]
            },
            {
                name: '',
                type: 'bar',
                label: trafficRondLabel,
                barGap: 1.5,
                barWidth: '8%',
                data: [1.4]
            },
            {
                name: '',
                type: 'bar',
                label: trafficRondLabel,
                barGap: 1.5,
                barWidth: '8%',
                data: [1.4]
            },
            {
                name: '',
                type: 'bar',
                label: trafficRondLabel,
                barGap: 1.5,
                barWidth: '8%',
                data: [1.4]
            },
            {
                name: '',
                type: 'bar',
                label: trafficRondLabel,
                barGap: 1.5,
                barWidth: '8%',
                data: [1.4]
            }
        ]
    });
    // trafficRondChart.showLoading();


    $.get(router_city('/findhouse/showMyReportData/' + locationBaseUrl)).done(function (data) {
        priceChart.hideLoading();
        marketChart.hideLoading();
        var priceData = data.data.fhpt,    // 价格走势
            marketData = data.data.fhtp,   // 供需情况
            compareData = JSON.parse(data.data.intelligenceFhRes.fhResult.value);   // 小区详细数据

        console.log(compareData);
        /**
         * 价格走势
         * */
        var maxTarget = priceData.maxTarget,
            minTarget = Math.abs(priceData.minTarget),
            baseTarget = priceData.target;
        var totalPriceArr = [],
            priceArr = [],
            priceTimeArr = [];
        var priceChartData = priceData.ptlists;

        // 页面文字内容赋值
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

        for (var i = priceChartData.length - 1; i >= 0; i--) {
            totalPriceArr.push(priceChartData[i].totalPrice);
            priceArr.push(priceChartData[i].price);
            priceTimeArr.push(priceChartData[i].periodicTime)
        }

        priceChart.setOption({
            xAxis: {
                data: priceTimeArr
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
        });

        /**
         * 供需情况
         * */
        var marketDataRatio = marketData.ratio;
        var maxVolume = marketDataRatio.maxVolume,
            maxVolumeRatio = marketDataRatio.maxVolumeRatio,
            minVolume = marketDataRatio.minVolume,
            minVolumeRatio = marketDataRatio.minVolumeRatio,
            averageVolume = marketDataRatio.averageVolume,
            averageVolumeRatio = marketDataRatio.averageVolumeRatio;
        var marketAllArr = [],
            markerTargetArr = [],
            periodicTimeArr = [];
        var marketChartArr = marketData.trend;

        // 页面内容赋值
        $('#maxVolume').text(maxVolume + '套');
        $('#maxVolumeRatio').text(maxVolumeRatio);
        $('#minVolume').text(minVolume + '套');
        $('#minVolumeRatio').text(minVolumeRatio);
        $('#averageVolume').text(averageVolume + '套');
        $('#averageVolumeRatio').text(averageVolumeRatio);

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
                // 根据名字对应到相应的系列
                name: '北京市场',
                data: marketAllArr
            },{
                // 根据名字对应到相应的系列
                name: '目标市场',
                data: markerTargetArr
            }]
        });

        /**
         * 地铁信息
         * */

        var projNameArr = [];
        var nearestSubwayDescArr = [];
        for (var i = 0; i < compareData.length; i++) {
            projNameArr.push(compareData[i].projname);
            nearestSubwayDescArr.push(compareData[i].nearestSubwayDesc.split('$'));
        }


        trafficSubwayChart.setOption({

        });
        console.log(nearestSubwayDescArr[0][2]);



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