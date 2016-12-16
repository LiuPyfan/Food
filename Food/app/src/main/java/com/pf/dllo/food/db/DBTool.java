package com.pf.dllo.food.db;

import com.pf.dllo.food.app.Food;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by dllo on 2016/12/8.
 */
public class DBTool {
    private static DBTool ourInstance = new DBTool();
    private static CollectBeanDao sCollectBeanDao;
    private static SearchBeanDao sSearchBeanDao;

    // 对外提供getInstance方法获取本类的单例对象
    public static DBTool getInstance() {
        if (ourInstance == null) {
            synchronized (DBTool.class) {
                if (ourInstance == null) {
                    ourInstance = new DBTool();
                }
            }
        }
        // 初始化xxxDao对象
        sCollectBeanDao = Food.getDaoSession().getCollectBeanDao();
        sSearchBeanDao = Food.getDaoSession().getSearchBeanDao();
        return ourInstance;
    }
    
    private DBTool() {
    }
    /********增加*******/
    // 增加单一的搜索的SearchBean
    public void insertSearchBean(SearchBean searchBean){
        sSearchBeanDao.insert(searchBean);
    }
    // 增加搜索的SearchBean 的集合

    public void insertSearchList(List<SearchBean> list){
        sSearchBeanDao.insertInTx(list);
    }
    /********删除*******/
    // 根据某一个食物字段进行删除操作
    public void deleteByName(String name){
        DeleteQuery<SearchBean> deleteQuery = sSearchBeanDao.queryBuilder().where(SearchBeanDao.Properties.Name.eq(name)).buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();
    }

    // 删除所有内容
    public void deleteAllName(){
        sSearchBeanDao.deleteAll();
    }
    /********查询*******/
    // 查询所有的方法
    public List<SearchBean>querySearchAll(){
        // 查询方法1
        List<SearchBean>list = sSearchBeanDao.loadAll();
        // 查询方法2
        List<SearchBean>personList = sSearchBeanDao.queryBuilder().list();
        return list;
    }
    // 查重方法
    // 根据食物名查询
    public boolean hasSave(String food){
        QueryBuilder<SearchBean> queryBuilder = sSearchBeanDao.queryBuilder().where(SearchBeanDao.Properties.Name.eq(food));
        // 获取到我们要查询的内容的size
        Long size = queryBuilder.buildCount().count();
        return size > 0 ? true :false;
    }



    /// 收藏 ///
    /********增加*******/
        // 增加单一对象的方法
        public void insertCollectBean(CollectBean bean){
           sCollectBeanDao.insert(bean);
        }
        // 增加集合的方法
    public void insertList(List<CollectBean>list){
        sCollectBeanDao.insertInTx(list);
    }
    /********删除*******/
    // 删除单一对象的方法
    public void deleteCollectBean(CollectBean person){
        sCollectBeanDao.delete(person);
    }
    // 删除所有内容
    public void deleteAll(){
        sCollectBeanDao.deleteAll();
    }
    // 根据id进行的删除
    public void deleteById(Long id){
        sCollectBeanDao.deleteByKey(id);
    }

    // 根据某一个字段进行删除操作
    public void deleteByTitle(String title){
        DeleteQuery<CollectBean> deleteQuery = sCollectBeanDao.queryBuilder().where(CollectBeanDao.Properties.Title.eq(title)).buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();
    }
    // 根据题目,网址进行删除
    public void deleteByUrl(String title,String url){
        DeleteQuery<CollectBean> deleteQuery = sCollectBeanDao.queryBuilder().where(CollectBeanDao.Properties.Title.eq(title),

                CollectBeanDao.Properties.Url.eq(url)).buildDelete();
        if (deleteQuery != null) {
            deleteQuery.executeDeleteWithoutDetachingEntities();
        }
    }
    /********查询*******/

    // 查询所有的方法
    public List<CollectBean> queryAll(){
        // 查询方法1
        List<CollectBean>list = sCollectBeanDao.loadAll();
        // 查询方法2
        List<CollectBean>personList = sCollectBeanDao.queryBuilder().list();
        return list;
    }
    // 查重方法
    // 根据标题来查询
    public boolean isSave(String title){
        QueryBuilder<CollectBean> queryBuilder = sCollectBeanDao.queryBuilder().where(CollectBeanDao.Properties.Title.eq(title));
        // 获取到我们要查询的内容的size
        Long size = queryBuilder.buildCount().count();
        return size > 0 ? true :false;
    }

    public boolean isSave(CollectBean bean){
        QueryBuilder<CollectBean> queryBuilder = sCollectBeanDao.queryBuilder().where(CollectBeanDao.Properties.Title.eq(bean.getTitle()),
                CollectBeanDao.Properties.Url.eq(bean.getUrl()));
        Long size = queryBuilder.buildCount().count();
        return size > 0 ? true :false;
    }
        

    
    
}
