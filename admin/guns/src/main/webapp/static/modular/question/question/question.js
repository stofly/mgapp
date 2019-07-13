/**
 * 题目管理管理初始化
 */
var Question = {
    id: "QuestionTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Question.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '编号', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '题目类型', field: 'type', visible: true, align: 'center', valign: 'middle'},
            {title: '标题', field: 'title', visible: true, align: 'center', valign: 'middle'},
            {title: '选项A', field: 'optiona', visible: true, align: 'center', valign: 'middle'},
            {title: '选项B', field: 'optionb', visible: true, align: 'center', valign: 'middle'},
            {title: '选项C', field: 'optionc', visible: true, align: 'center', valign: 'middle'},
            {title: '选项D', field: 'optiond', visible: true, align: 'center', valign: 'middle'},
            {title: '提示', field: 'tips', visible: true, align: 'center', valign: 'middle'},
            {title: '答案', field: 'answer', visible: true, align: 'center', valign: 'middle'},
            {title: '解析', field: 'explain', visible: true, align: 'center', valign: 'middle'},
            {title: '科目', field: 'kind', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Question.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Question.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加题目管理
 */
Question.openAddQuestion = function () {
    var index = layer.open({
        type: 2,
        title: '添加题目管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/question/question_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看题目管理详情
 */
Question.openQuestionDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '题目管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/question/question_update/' + Question.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除题目管理
 */
Question.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/question/delete", function (data) {
            Feng.success("删除成功!");
            Question.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("questionId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询题目管理列表
 */
Question.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Question.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Question.initColumn();
    var table = new BSTable(Question.id, "/question/list", defaultColunms);
    table.setPaginationType("client");
    Question.table = table.init();
});
