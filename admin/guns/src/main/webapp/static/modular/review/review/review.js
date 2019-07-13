/**
 * 时评管理管理初始化
 */
var Review = {
    id: "ReviewTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Review.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '编号', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '时评标题', field: 'rtitle', visible: true, align: 'center', valign: 'middle'},
            {title: '时评作者', field: 'rauthor', visible: true, align: 'center', valign: 'middle'},
            {title: '时评来源', field: 'fromsour', visible: true, align: 'center', valign: 'middle'},
            {title: '时评时间', field: 'rdate', visible: true, align: 'center', valign: 'middle'},
            {title: '时评顺序', field: 'rorder', visible: true, align: 'center', valign: 'middle'},
            {title: '时评链接', field: 'rurl', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Review.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Review.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加时评管理
 */
Review.openAddReview = function () {
    var index = layer.open({
        type: 2,
        title: '添加时评管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/review/review_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看时评管理详情
 */
Review.openReviewDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '时评管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/review/review_update/' + Review.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除时评管理
 */
Review.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/review/delete", function (data) {
            Feng.success("删除成功!");
            Review.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("reviewId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询时评管理列表
 */
Review.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Review.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Review.initColumn();
    var table = new BSTable(Review.id, "/review/list", defaultColunms);
    table.setPaginationType("client");
    Review.table = table.init();
});
