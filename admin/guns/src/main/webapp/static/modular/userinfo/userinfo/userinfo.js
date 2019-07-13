/**
 * 体验者管理管理初始化
 */
var Userinfo = {
    id: "UserinfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Userinfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '编号', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '用户名', field: 'username', visible: true, align: 'center', valign: 'middle'},
            {title: '密码', field: 'password', visible: true, align: 'center', valign: 'middle'},
            {title: '权限', field: 'perssion', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Userinfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Userinfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加体验者管理
 */
Userinfo.openAddUserinfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加体验者管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/userinfo/userinfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看体验者管理详情
 */
Userinfo.openUserinfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '体验者管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/userinfo/userinfo_update/' + Userinfo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除体验者管理
 */
Userinfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/userinfo/delete", function (data) {
            Feng.success("删除成功!");
            Userinfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("userinfoId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询体验者管理列表
 */
Userinfo.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Userinfo.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Userinfo.initColumn();
    var table = new BSTable(Userinfo.id, "/userinfo/list", defaultColunms);
    table.setPaginationType("client");
    Userinfo.table = table.init();
});
