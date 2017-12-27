<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="${staticurl}/js/flexible.js"></script>
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${staticurl}/css/jquery.fullPage.css">
    <link rel="stylesheet" href="${staticurl}/css/intelligent.css">
    <title>筛选</title>
    <script src="${staticurl}/js/jquery-2.1.4.min.js"></script>
    <script src="${staticurl}/js/jquery.fullPage.min.js"></script>
</head>
<body>
<div id="superContainer">
    <div class="section page1">
        <div class="bgbox bg1">
            <div class="page-content">
                <h1>智能找房</h1>
                <p>房产头条大数据<br>根据您的需求推荐最匹配的小区和房源</p>
                <button type="button" class="button begin">开始体验</button>
            </div>
        </div>
    </div>
    <div class="section page2">
        <div class="bgbox bg2">
            <div class="page-content">
                <h2>请选择购房目的</h2>
                <p>方便为您提供更精准的服务</p>
                <div class="choose-wrapper">
                    <div class="choose-item-box">
                        <div class="box-line-triangle"></div>
                        <div class="choose-item-cont">
                            <div class="dashed-line one"></div>
                            <p data-user-type="1">
                                <span>自住</span>
                                <span>刚需</span>
                                <span>交通便利</span>
                            </p>
                        </div>
                    </div>
                    <div class="clear">
                        <div class="choose-item-box">
                            <div class="box-line-triangle"></div>
                            <div class="choose-item-cont">
                                <div class="dashed-line two"></div>
                                <p data-user-type="2">
                                    <span>自住</span>
                                    <span>改善</span>
                                    <span>配套完善</span>
                                </p>
                            </div>
                        </div>
                        <div class="choose-item-box">
                            <div class="box-line-triangle"></div>
                            <div class="choose-item-cont">
                                <div class="dashed-line three"></div>
                                <p data-user-type="3">
                                    <span>出租</span>
                                    <span>保值</span>
                                    <span>回报率</span>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <button type="button" class="button" id="userTypeSubmit">确定</button>
            </div>
        </div>
    </div>
    <div class="section page3 active">
        <div class="bgbox bg3">
            <div class="page-content">
                <div class="result-text">
                    <p>为您匹配了<em class="high-light-red">234</em>个小区</p>
                    <p>有<em class="high-light-red">37%</em>的用户和您的需求相同</p>
                </div>
            </div>
        </div>
        <button type="button" class="button">开始体验</button>
    </div>
    <div class="section page4">
        <div class="bgbox bg4">
            <div class="page-content">
                <div class="user-header-box">
                    <div class="user-line-triangle"></div>
                    <img src="/static/images/intelligent/user-header.png" alt="用户头像">
                </div>
                <div class="word-cont">
                    <p>繁华都市中，每个人都想有自己的空间。多年打拼后，您终于开始寻找第一个家园。我们明白，您挣的每分每厘都得来不易，凝聚无数的早起通勤和深夜加班。因此我们根据您的条件，为您精心挑选最具性价比的社区，可以让你拥有第一个舒适小家，争取做到：</p>
                    <ol>
                        <li>-    尽量离交通站近，睡多一点</li>
                        <li>-    餐饮便利，到家能吃口热饭</li>
                        <li>-    有休闲地儿，周末看场大片</li>
                    </ol>
                </div>
                <button type="button" class="button">开始体验</button>
            </div>
        </div>
    </div>
    <div class="section page5">
        5
    </div>
</div>

<script>
    $(function () {
        $('#superContainer').fullpage({
            fitToSection: true,
            resize: false,
            onLeave: function (index, nextIndex, direction) {
                if (nextIndex == 4) {
                    $.fn.fullpage.setAllowScrolling(true, 'down');
                }
            }
        });

        $.fn.fullpage.setAllowScrolling(false, 'up, down');

        $('.begin').on('click', function () {
            $.fn.fullpage.moveSectionDown();
        });

        // 用户类型选择
        $('.choose-wrapper').on('click', '.choose-item-box', function () {
            $('.choose-wrapper').find('.choose-item-box').removeClass('current');
            $(this).addClass('current');
        });

        if ($('.choose-wrapper').find('.current').length > 0){
            $('.button').on('click', function () {
                $.fn.fullpage.moveSectionDown();
            });
        }

        // 提交选中用户类型
        $('#userTypeSubmit').on('click', function () {
            if ($('.choose-wrapper').find('.current').length > 0){
                var params = $('.choose-wrapper').find('.choose-item-box.current').find('p').data('user-type');
                var userTypeUrl = 'userType=' + params;
                $.fn.fullpage.moveSectionDown();
                console.log(userTypeUrl);
            }
        })
    })
</script>
</body>
</html>