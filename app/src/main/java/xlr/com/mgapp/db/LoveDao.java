package xlr.com.mgapp.db;

import xlr.com.mgapp.MyApplication;
import xlr.com.mgapp.bean.QuestBean;

public class LoveDao {
    /**
     * 添加数据
     *
     * @param questBean
     */
    public static void insertLove(QuestBean questBean) {
        MyApplication.getDaoInstant().getQuestBeanDao().insertOrReplace(questBean);
    }

    /**
     * 更新数据
     *
     * @param shop
     */
    public static void updateLove(QuestBean shop) {
        MyApplication.getDaoInstant().getQuestBeanDao().update(shop);
    }

    /**
     * 查询条件为id=id的数据
     *
     * @return
     */
    public static QuestBean queryLove(long id) {

        return MyApplication.getDaoInstant().getQuestBeanDao().queryBuilder().where(QuestBeanDao.Properties.Id.eq(id)).list().get(0);
    }

}
