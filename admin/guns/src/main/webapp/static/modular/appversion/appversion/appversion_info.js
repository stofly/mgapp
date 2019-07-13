/**
 * 初始化应用版本管理详情对话框
 */
var AppversionInfoDlg = {
    appversionInfoData : {}
};

/**
 * 清除数据
 */
AppversionInfoDlg.clearData = function() {
    this.appversionInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AppversionInfoDlg.set = function(key, val) {
    this.appversionInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AppversionInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
AppversionInfoDlg.close = function() {
    parent.layer.close(window.parent.Appversion.layerIndex);
}

/**
 * 收集数据
 */
AppversionInfoDlg.collectData = function() {
    this
    .set('id')
    .set('appname')
    .set('appversion')
    .set('appurl');
}

/**
 * 提交添加
 */
AppversionInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/appversion/add", function(data){
        Feng.success("添加成功!");
        window.parent.Appversion.table.refresh();
        AppversionInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.appversionInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
AppversionInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/appversion/update", function(data){
        Feng.success("修改成功!");
        window.parent.Appversion.table.refresh();
        AppversionInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.appversionInfoData);
    ajax.start();
}

$(function() {

});
