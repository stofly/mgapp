/**
 * 初始化时评管理详情对话框
 */
var ReviewInfoDlg = {
    reviewInfoData : {}
};

/**
 * 清除数据
 */
ReviewInfoDlg.clearData = function() {
    this.reviewInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ReviewInfoDlg.set = function(key, val) {
    this.reviewInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ReviewInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ReviewInfoDlg.close = function() {
    parent.layer.close(window.parent.Review.layerIndex);
}

/**
 * 收集数据
 */
ReviewInfoDlg.collectData = function() {
    this
    .set('id')
    .set('rtitle')
    .set('rauthor')
    .set('fromsour')
    .set('rdate')
    .set('rorder')
    .set('rurl');
}

/**
 * 提交添加
 */
ReviewInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/review/add", function(data){
        Feng.success("添加成功!");
        window.parent.Review.table.refresh();
        ReviewInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.reviewInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ReviewInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/review/update", function(data){
        Feng.success("修改成功!");
        window.parent.Review.table.refresh();
        ReviewInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.reviewInfoData);
    ajax.start();
}

$(function() {

});
