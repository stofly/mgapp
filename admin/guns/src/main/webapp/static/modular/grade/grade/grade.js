/**
 * 成绩管理管理初始化
 */
var Grade = {
    id: "GradeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Grade.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '编号', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '姓名', field: 'username', visible: true, align: 'center', valign: 'middle'},
            {title: '成绩', field: 'grade', visible: true, align: 'center', valign: 'middle'},
            {title: '用时', field: 'usetime', visible: true, align: 'center', valign: 'middle'},
            {title: '结束', field: 'endtime', visible: true, align: 'center', valign: 'middle'},
            {title: '分类', field: 'kind', visible: true, align: 'center', valign: 'middle'},
            {title: '排名', field: 'number', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Grade.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Grade.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加成绩管理
 */
Grade.openAddGrade = function () {
    var index = layer.open({
        type: 2,
        title: '添加成绩管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/grade/grade_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看成绩管理详情
 */
Grade.openGradeDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '成绩管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/grade/grade_update/' + Grade.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除成绩管理
 */
Grade.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/grade/delete", function (data) {
            Feng.success("删除成功!");
            Grade.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("gradeId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询成绩管理列表
 */
Grade.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Grade.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Grade.initColumn();
    var table = new BSTable(Grade.id, "/grade/list", defaultColunms);
    table.setPaginationType("client");
    Grade.table = table.init();
});
