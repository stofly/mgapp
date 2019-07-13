/**
 * 初始化错题集管理详情对话框
 */
var UqInfoDlg = {
    uqInfoData : {}
};

/**
 * 清除数据
 */
UqInfoDlg.clearData = function() {
    this.uqInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UqInfoDlg.set = function(key, val) {
    this.uqInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UqInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
UqInfoDlg.close = function() {
    parent.layer.close(window.parent.Uq.layerIndex);
}

/**
 * 收集数据
 */
UqInfoDlg.collectData = function() {
    this
    .set('id')
    .set('uiid')
    .set('qid');
}

/**
 * 提交添加
 */
UqInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/uq/add", function(data){
        Feng.success("添加成功!");
        window.parent.Uq.table.refresh();
        UqInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.uqInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
UqInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/uq/update", function(data){
        Feng.success("修改成功!");
        window.parent.Uq.table.refresh();
        UqInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.uqInfoData);
    ajax.start();
}

$(function() {

});
