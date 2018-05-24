/**
 * Created by wangkejie.
 */
window.Hybrid = window.Hybrid || {};

var isIos = (/iphone|ipad/gi).test(navigator.appVersion);
var bridgePostMsg = function(url) {
  var iframeObj;
  iframeObj = document.createElement('iframe');
  iframeObj.setAttribute("src", url);
  iframeObj.setAttribute("style", "display:none;");
  iframeObj.setAttribute("height", "0px");
  iframeObj.setAttribute("width", "0px");
  iframeObj.setAttribute("frameborder", "0");
  document.body.appendChild(iframeObj);
  iframeObj.parentNode.removeChild(iframeObj);
  iframeObj = null;
};
var _getHybridUrl = function(params) {
  var k
  var paramStr = ''
  var url = 'lvka://';
  url += params.tagname + '?t=' + new Date().getTime(); // 时间戳，防止url不起效
  if (params.callback) {
    url += '&callback=' + params.callback;
    delete params.callback;
  }
  if (params.param) {
    paramStr = typeof params.param === 'object' ? JSON.stringify(params.param) : params.param;
    url += '&param=' + encodeURIComponent(paramStr);
  }
  return url;
};
window.Hybrid.callback = function(data) {
  var callbackId = data.callback;
  if (!callbackId) return;

  if (typeof data === 'string') data = JSON.parse(data);

  if (callbackId.indexOf('header_') !== -1 && window.Hybrid['Header_Event']) {
    window.Hybrid['Header_Event'][callbackId] && window.Hybrid['Header_Event'][callbackId](data.data || {});
  } else {
    window.Hybrid[callbackId] && window.Hybrid[callbackId](data.data || {}, data);
  }
  return true;
};
window.requestHybrid = function(params) {
  // 生成唯一执行函数，执行后销毁
  var tt = (new Date().getTime());
  var t = 'hybrid_' + tt;
  var tmpFn;

  // 处理有回调的情况
  if (params.callback) {
    tmpFn = params.callback;
    params.callback = t;
    window.Hybrid[t] = function (data) {
      tmpFn(data);
      delete window.Hybrid[t];
    }
  }
  bridgePostMsg(_getHybridUrl(params));
};
