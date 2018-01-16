$(function () {
    $('.map-message-btn').find('li.parent-child').addClass('current');
    $('.map-message-btn').find('li.vegetable-market').addClass('current');

    Map.init($('#map-img').data('lng'), $('#map-img').data('lat'), 15, 1, '');

    var _word1 = $('.map-message-btn').find('li[data-type="qinzi"]').find('span').text();
    var _word2 = $('.map-message-btn').find('li[data-type="caishichang"]').find('span').text();

    Map.search(_word1, null, $('#qinzi'));

    Map.search(_word2, null, $('#caishichang'));

    $('.map-message-btn').on('click', 'li', function () {
        $(this).addClass('current').siblings().removeClass('current');
        var text = $(this).data('type');
        var parentText = $(this).parent().data('type');
        var _word = $(this).find('span').text();

        Map.search(_word, function (results) {
            $('#' + text).html('');
            var _resultHtml = '';
            var _end = results.length>5?5:results.length;
            for (var rIndex in results) {
                if (rIndex < _end) {
                    var _num = parseInt(rIndex) + 1;
                    var result = results[rIndex];
                    _resultHtml += '<li>' +
                        '<p><i class="expand-icon expand-radius">' + _num + '</i><span class="expand-name">'+ result['title'] +'</span></p>' +
                        '<span class="expand-distance">'+ result['distance'] + '</span>' +
                        '</li>';
                }
            }
            $('#' + text).html(_resultHtml);
        });

        if (parentText == '教育培训') {
            $(this).removeClass('choose');
            $(this).prevAll().addClass('choose');
            $(this).nextAll().removeClass('choose');
        }
        $(this).parents('.expand-content').find('ul.result-data-expand').addClass('none');
        $('#'+text).removeClass('none');
    });

   /*$('#medical-surrounding').on('click', function () {
       var _word = $(this).data('word');

       var dtd3 = $.Deferred();
       Map.search(_word, dtd3).done(function () {
           $('#hospitalListDom').html('');
           var _resultHtml = '';
           var _end = Map.results.length>5?5:Map.results.length;
           for (var rIndex in Map.results) {
               if (rIndex < _end) {
                   var _num = parseInt(rIndex) + 1;
                   var result = Map.results[rIndex];

                   _resultHtml += '<li>' +
                       '<p><i class="expand-icon medical-treatment"></i><span class="expand-name">'+ result['title'] +'</span></p>' +
                       '<span class="expand-distance">'+ result['distance'] + '</span>' +
                       '</li>';
               }
           }
           $('#hospitalListDom').html(_resultHtml);
       });
   });*/
});