/**
 * 错题集管理管理初始化
 */
var Uq = {
    id: "UqTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Uq.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '用户题目编号', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '用户编号', field: 'uiid', visible: true, align: 'center', valign: 'middle'},
            {title: '题目编号', field: 'qid', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Uq.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Uq.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加错题集管理
 */
Uq.openAddUq = function () {
    var index = layer.open({
        type: 2,
        title: '添加错题集管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/uq/uq_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看错题集管理详情
 */
Uq.openUqDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '错题集管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/uq/uq_update/' + Uq.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除错题集管理
 */
Uq.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/uq/delete", function (data) {
            Feng.success("删除成功!");
            Uq.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("uqId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询错题集管理列表
 */
Uq.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Uq.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Uq.initColumn();
    var table = new BSTable(Uq.id, "/uq/list", defaultColunms);
    table.setPaginationType("client");
    Uq.table = table.init();
});
