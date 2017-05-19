package com.booboomx.tvshow.dao.greendao;

import com.booboomx.tvshow.bean.LiveCategory;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import java.util.Map;

/**
 * Created by booboomx on 17/5/18.
 */

public class DaoSession extends AbstractDaoSession {
    private final DaoConfig liveCategoryDaoConfig;

    private final LiveCategoryDao liveCategoryDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        liveCategoryDaoConfig = daoConfigMap.get(LiveCategoryDao.class).clone();
        liveCategoryDaoConfig.initIdentityScope(type);

        liveCategoryDao = new LiveCategoryDao(liveCategoryDaoConfig, this);

        registerDao(LiveCategory.class, liveCategoryDao);
    }

    public void clear() {
        liveCategoryDaoConfig.clearIdentityScope();
    }

    public LiveCategoryDao getLiveCategoryDao() {
        return liveCategoryDao;
    }
}
