<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>用户分析</title>
  <script type="text/javascript" src="../../resources/bootstrap-3.3.7/js/jquery-3.1.1.min.js"></script>
  <link type="text/css" href="../../resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
  <link href="../../resources/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" rel="stylesheet"/>
  <script type="text/javascript" src="../../resources/bootstrap-3.3.7/js/bootstrap.min.js"></script>
  <script src="../../resources/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
  <script src="../../resources/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <!--微信样式-->
  <link rel="stylesheet" type="text/css" href="https://res.wx.qq.com/c/=/mpres/htmledition/style/widget/pagination218878.css,/mpres/htmledition/style/biz_web/widget/date_range218878.css,/mpres/htmledition/style/biz_web/widget/dropdown2f12f7.css">
  <link onerror="wx_loaderror(this)" rel="stylesheet" type="text/css" href="https://res.wx.qq.com/mpres/htmledition/style/page/stat/stat_overview2f3a2e.css">
  <link onerror="wx_loaderror(this)" rel="stylesheet" type="text/css" href="https://res.wx.qq.com/mpres/htmledition/style/base/lib330daf.css">
  <link onerror="wx_loaderror(this)" rel="stylesheet" type="text/css" href="https://res.wx.qq.com/mpres/htmledition/style/base/base3560fc.css">
  <link onerror="wx_loaderror(this)" rel="stylesheet" type="text/css" href="https://res.wx.qq.com/mpres/htmledition/style/base/layout_head330daf.css">


</head>
<body style="background: #FFFFFF">

<div class="main_hd">
    <h2>用户分析</h2>
   <%-- <div class="title_tab" id="js_topTab">
        <ul class="tab_navs title_tab" data-index="0">
            <li data-index="0" class="tab_nav first js_top selected" data-id="user_stat">
              <a href="#">用户增长</a>
            </li>
          &lt;%&ndash;  <li data-index="1" class="tab_nav  js_top" data-id="user_attr">
              <a href="/misc/useranalysis?">用户属性</a>
            </li>&ndash;%&gt;
        </ul>
    </div>--%>
</div>
<div>
    <div class="form-group col-sm-4">
        <label for="startDate" class="col-sm-4 control-label">开始时间</label>
        <input id="startDate"size="16" type="text"  onfocus="changeEndDate()"value="" class="form_datetime col-sm-8">
    </div>
    <div class="form-group col-sm-4">
        <label for="endDate" class="col-sm-4 control-label">结束时间</label>
        <input id="endDate"size="16" type="text"  onfocus="changeStartDate()" value="" class="form_datetime col-sm-8">
    </div>
    <div class="form-group col-sm-4">
        <button type="button" class="btn btn-success" onclick="query()">查询</button>
    </div>
</div>
<div class="wrp_overview" style="margin-top: 50px;">
    <div class="info_box" id="">
        <div class="inner">
            <div class="info_hd append_ask">
            <h4 style="text-align: left;"><br>关键指标,不填写查询时间，默认是昨日时间到今日时间的一天数据</h4>
                <div class="ext_info help">
                    <i id="js_ask_keys" class="icon_msg_mini ask"></i>
                    <div class="help_content" id="js_ask_keys_content" style="display: none">
                        <i class="dropdown_arrow out"></i>
                        <i class="dropdown_arrow in"></i>
                        <dl class="help-change-list" id="pop_items_info">
                            <dt>新关注人数</dt>
                            <dd>新关注的用户数</dd>
                            <dt>取消关注人数</dt>
                            <dd>取消关注的用户数</dd>
                            <dt>净增关注人数</dt>
                            <dd>净增长的关注用户数</dd>
                            <dt>累积关注人数</dt>
                            <dd>当前关注的用户总数</dd>
                          <%--  <dt>日、周、月</dt>
                            <dd>分别计算昨日数据相比1天、7天、30天前的变化情况</dd>--%>
                           <%-- <dt>请注意</dt>
                            <dd>用户总数根据昨日数据计算，而用户管理模块根据当前实时数据计算，两者可能不一致</dd>--%>
                        </dl>
                      <%--  <div class="help-change-footer" id="footer_items_info">
                            <span class="wechat_data_range">数据从2013年7月1日开始统计。</span>
                            由于服务器缓存，以及指标计算方法和统计时间的差异，数据可能出现微小误差，一般在1%以内。
                        </div>--%>
                    </div>
                </div>
            </div>
            <div class="info_bd">
                <div class="content" id="js_keydata">
                    <table class="ui_trendgrid ui_trendgrid_3">
                        <tbody>
                            <tr>
                            <td class="first">
                                <div class="ui_trendgrid_item">
                                    <div class="ui_trendgrid_chart"></div>
                                    <dl>
                                        <dt><b>新关注人数</b></dt>
                                        <dd class="ui_trendgrid_number">
                                            <strong>
                                                <input id="subscribeNum" style="border-style:none;background: #FFF;text-align: center" value="0">
                                            </strong>
                                            <em class="ui_trendgrid_unit"></em>
                                        </dd>
                                        <%--<dd>日 &nbsp;&nbsp;&nbsp;--</dd>
                                        <dd>周 &nbsp;&nbsp;&nbsp;-- </dd>
                                        <dd>月 &nbsp;&nbsp;&nbsp;-- </dd>--%>
                                    </dl>
                                </div>
                            </td>
                            <td>
                                <div class="ui_trendgrid_item">
                                    <div class="ui_trendgrid_chart"></div>
                                    <dl>
                                        <dt><b>取消关注人数</b></dt>
                                        <dd class="ui_trendgrid_number">
                                            <input id="unSubscribeNum" style="border-style:none;background: #FFF;text-align: center" value="0">
                                            <em class="ui_trendgrid_unit"></em>
                                        </dd>
                                      <%--  <dd>日 &nbsp;&nbsp;&nbsp;--</dd>
                                        <dd>周 &nbsp;&nbsp;&nbsp;-- </dd>
                                        <dd>月 &nbsp;&nbsp;&nbsp;-- </dd>--%>
                                    </dl>
                                </div>
                            </td>
                            <td>
                                <div class="ui_trendgrid_item">
                                    <div class="ui_trendgrid_chart"></div>
                                    <dl>
                                        <dt><b>净增关注人数</b></dt>
                                        <dd class="ui_trendgrid_number">
                                            <input id="netSubscribeNum" style="border-style:none;background: #FFF;text-align: center" value="0">
                                            <em class="ui_trendgrid_unit"></em>
                                        </dd>
                                       <%-- <dd>日 &nbsp;&nbsp;&nbsp;--</dd>
                                        <dd>周 &nbsp;&nbsp;&nbsp;-- </dd>
                                        <dd>月 &nbsp;&nbsp;&nbsp;-- </dd>--%>
                                    </dl>
                                </div>
                            </td>
                            <td class="last">
                                <div class="ui_trendgrid_item">
                                    <div class="ui_trendgrid_chart"></div>
                                    <dl>
                                        <dt><b>累积关注人数</b></dt>
                                        <dd class="ui_trendgrid_number">
                                            <input  id="totalSubscribeNum" style="border-style:none;background: #FFF;text-align: center" value="0">
                                            <em class="ui_trendgrid_unit"></em>
                                        </dd>
                                     <%--   <dd>日 <i class="icon_up" title="上升"></i>0%</dd>
                                        <dd>周 <i class="icon_up" title="上升"></i>0% </dd>
                                        <dd>月 <i class="icon_up" title="上升"></i>0% </dd>--%>
                                    </dl>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function(){
        var endDate = (new Date()).toLocaleString();
        var startDate =  (new Date(Date.parse(endDate) - 86400000)).toLocaleString();
        debugger;
        $.ajax({
            type: "get",
            url: "${base.contextPath}/admin/userAnalysis?startDate="+startDate+"&endDate="+endDate,
            contentType: "application/json",
            success: function (data) {
                var user = JSON.parse(data);
                debugger;
                $("#subscribeNum").val(user.subscribeNum);
                $("#unSubscribeNum").val(user.unSubscribeNum);
                $("#netSubscribeNum").val(user.netSubscribeNum);
                $("#totalSubscribeNum").val(user.totalSubscribeNum);
            }
        });
    });
  var query = function(){
      var startDate =  $("#startDate").val();
      var endDate =  $("#endDate").val();
      //alert("开始："+startDate+" 结束："+endDate);
      $.ajax({
          type: "get",
          url: "${base.contextPath}/admin/userAnalysis?startDate="+startDate+"&endDate="+endDate,
          contentType: "application/json",
          success: function (data) {
              var user = JSON.parse(data);
              debugger;
              $("#subscribeNum").val(user.subscribeNum);
              $("#unSubscribeNum").val(user.unSubscribeNum);
              $("#netSubscribeNum").val(user.netSubscribeNum);
              $("#totalSubscribeNum").val(user.totalSubscribeNum);
          }
      });
  }
  var changeStartDate = function(){
      var startDate =  $("#startDate").val();
      if(startDate!=null && startDate!=undefined && startDate!=''){
          $('#endDate').datetimepicker('setStartDate', startDate);
      }
  }
  var changeEndDate = function(){
      var endDate =  $("#endDate").val();
      if(endDate!=null && endDate!=undefined && endDate!=''){
          $('#startDate').datetimepicker('setEndDate', endDate);
      }
  }

  $("#js_ask_keys").mouseover(function(){
    $('#js_ask_keys_content').css('display','block');
  });

  $("#js_ask_keys").mouseout(function(){
    $('#js_ask_keys_content').css('display','none');
  });

  $("#startDate").datetimepicker({
      format: "yyyy-mm-dd hh:ii:ss",
      endData: $("#endDate").val(),
      autoclose: true,
      todayBtn: true,
      todayHighlight: true,
      showMeridian: true,
      pickerPosition: "bottom-left",
      language: 'zh-CN',//中文，需要引用zh-CN.js包
      startView: 2,//月视图
      minView: 0//日期时间选择器所能够提供的最精确的时间选择视图
  });
  $("#endDate").datetimepicker({
      format: "yyyy-mm-dd hh:ii:ss",
      startDate:$("#startDate").val(),
      autoclose: true,
      todayBtn: true,
      todayHighlight: true,
      showMeridian: true,
      pickerPosition: "bottom-left",
      language: 'zh-CN',//中文，需要引用zh-CN.js包
      startView: 2,//月视图
      minView: 0//日期时间选择器所能够提供的最精确的时间选择视图
  });
</script>
</body>
</html>
