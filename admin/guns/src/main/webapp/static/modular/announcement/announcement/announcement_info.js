/**
 * 初始化公告管理详情对话框
 */
var AnnouncementInfoDlg = {
    announcementInfoData : {}
};

/**
 * 清除数据
 */
AnnouncementInfoDlg.clearData = function() {
    this.announcementInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AnnouncementInfoDlg.set = function(key, val) {
    this.announcementInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AnnouncementInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
AnnouncementInfoDlg.close = function() {
    parent.layer.close(window.parent.Announcement.layerIndex);
}

/**
 * 收集数据
 */
AnnouncementInfoDlg.collectData = function() {
    this
    .set('id')
    .set('content')
    .set('hot');
}

/**
 * 提交添加
 */
AnnouncementInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/announcement/add", function(data){
        Feng.success("添加成功!");
        window.parent.Announcement.table.refresh();
        AnnouncementInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.announcementInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
AnnouncementInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/announcement/update", function(data){
        Feng.success("修改成功!");
        window.parent.Announcement.table.refresh();
        AnnouncementInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.announcementInfoData);
    ajax.start();
}

$(function() {

});
