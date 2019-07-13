/**
 * 初始化体验者管理详情对话框
 */
var UserinfoInfoDlg = {
    userinfoInfoData : {}
};

/**
 * 清除数据
 */
UserinfoInfoDlg.clearData = function() {
    this.userinfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserinfoInfoDlg.set = function(key, val) {
    this.userinfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserinfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
UserinfoInfoDlg.close = function() {
    parent.layer.close(window.parent.Userinfo.layerIndex);
}

/**
 * 收集数据
 */
UserinfoInfoDlg.collectData = function() {
    this
    .set('id')
    .set('username')
    .set('password')
    .set('perssion');
}

/**
 * 提交添加
 */
UserinfoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/userinfo/add", function(data){
        Feng.success("添加成功!");
        window.parent.Userinfo.table.refresh();
        UserinfoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.userinfoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
UserinfoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/userinfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.Userinfo.table.refresh();
        UserinfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.userinfoInfoData);
    ajax.start();
}

$(function() {

});
