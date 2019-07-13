/**
 * 公告管理管理初始化
 */
var Announcement = {
    id: "AnnouncementTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Announcement.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '公告编号', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '公告内容', field: 'content', visible: true, align: 'center', valign: 'middle'},
            {title: '公告热度', field: 'hot', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Announcement.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Announcement.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加公告管理
 */
Announcement.openAddAnnouncement = function () {
    var index = layer.open({
        type: 2,
        title: '添加公告管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/announcement/announcement_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看公告管理详情
 */
Announcement.openAnnouncementDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '公告管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/announcement/announcement_update/' + Announcement.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除公告管理
 */
Announcement.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/announcement/delete", function (data) {
            Feng.success("删除成功!");
            Announcement.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("announcementId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询公告管理列表
 */
Announcement.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Announcement.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Announcement.initColumn();
    var table = new BSTable(Announcement.id, "/announcement/list", defaultColunms);
    table.setPaginationType("client");
    Announcement.table = table.init();
});
