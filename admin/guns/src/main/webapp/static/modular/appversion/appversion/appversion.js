/**
 * 应用版本管理管理初始化
 */
var Appversion = {
    id: "AppversionTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Appversion.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '编号', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '应用名称', field: 'appname', visible: true, align: 'center', valign: 'middle'},
            {title: '应用版本', field: 'appversion', visible: true, align: 'center', valign: 'middle'},
            {title: '应用链接', field: 'appurl', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Appversion.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Appversion.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加应用版本管理
 */
Appversion.openAddAppversion = function () {
    var index = layer.open({
        type: 2,
        title: '添加应用版本管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/appversion/appversion_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看应用版本管理详情
 */
Appversion.openAppversionDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '应用版本管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/appversion/appversion_update/' + Appversion.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除应用版本管理
 */
Appversion.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/appversion/delete", function (data) {
            Feng.success("删除成功!");
            Appversion.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("appversionId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询应用版本管理列表
 */
Appversion.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Appversion.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Appversion.initColumn();
    var table = new BSTable(Appversion.id, "/appversion/list", defaultColunms);
    table.setPaginationType("client");
    Appversion.table = table.init();
});
