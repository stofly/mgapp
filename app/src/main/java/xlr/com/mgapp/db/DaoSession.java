package xlr.com.mgapp.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import xlr.com.mgapp.bean.QuestBean;
import xlr.com.mgapp.bean.QuestBean2;

import xlr.com.mgapp.db.QuestBeanDao;
import xlr.com.mgapp.db.QuestBean2Dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig questBeanDaoConfig;
    private final DaoConfig questBean2DaoConfig;

    private final QuestBeanDao questBeanDao;
    private final QuestBean2Dao questBean2Dao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        questBeanDaoConfig = daoConfigMap.get(QuestBeanDao.class).clone();
        questBeanDaoConfig.initIdentityScope(type);

        questBean2DaoConfig = daoConfigMap.get(QuestBean2Dao.class).clone();
        questBean2DaoConfig.initIdentityScope(type);

        questBeanDao = new QuestBeanDao(questBeanDaoConfig, this);
        questBean2Dao = new QuestBean2Dao(questBean2DaoConfig, this);

        registerDao(QuestBean.class, questBeanDao);
        registerDao(QuestBean2.class, questBean2Dao);
    }
    
    public void clear() {
        questBeanDaoConfig.clearIdentityScope();
        questBean2DaoConfig.clearIdentityScope();
    }

    public QuestBeanDao getQuestBeanDao() {
        return questBeanDao;
    }

    public QuestBean2Dao getQuestBean2Dao() {
        return questBean2Dao;
    }

}
