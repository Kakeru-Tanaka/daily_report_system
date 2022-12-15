package services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import actions.views.FavoriteConverter;
import actions.views.FavoriteView;
import actions.views.ReportConverter;
import actions.views.ReportView;
import constants.AttributeConst;
import constants.JpaConst;
import models.Favorite;

/**
 * いいねテーブルの操作に関わる処理を行うクラス
 */
public class FavoriteService  extends ServiceBase {

    /**
     * 日報のいいねフラグをtrueにする
     * @param rv 日報データ
     * @param ev 従業員データ
     * @return 処理が完了した場合true
    */
    public boolean doFavorite(ReportView rv,EmployeeView ev) {
        try {
            Favorite r = em.createNamedQuery(JpaConst.Q_FAV_GET_MINE_FAV_TO_REPORT,Favorite.class)
                .setParameter(JpaConst.JPQL_PARM_REPORT, ReportConverter.toModel(rv))
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(ev))
                .getSingleResult();
            em.getTransaction().begin();
            r.setFavoriteFlag(JpaConst.FAV_REP_TRUE);
            r.setFavoriteAt(LocalDateTime.now());
            em.getTransaction().commit();

            return true;
        }catch(NoResultException e) {
            e.printStackTrace();

            return false;
        }
    }

    /**
     * 日報のいいねフラグをfalseにする
     * @param rv 日報データ
     * @param ev 従業員データ
     * @return 処理が完了した場合true
    */
    public boolean cancelFavorite(ReportView rv,EmployeeView ev) {
        try {
            Favorite r = em.createNamedQuery(JpaConst.Q_FAV_GET_MINE_FAV_TO_REPORT,Favorite.class)
                .setParameter(JpaConst.JPQL_PARM_REPORT, ReportConverter.toModel(rv))
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(ev))
                .getSingleResult();
            em.getTransaction().begin();
            r.setFavoriteFlag(JpaConst.FAV_REP_FALSE);
            r.setFavoriteAt(LocalDateTime.now());
            em.getTransaction().commit();

            return true;
        }catch(NoResultException e) {
            e.printStackTrace();

            return false;
        }
    }

    /**
     * 指定した日報についたいいねの件数を取得し、返却する
     * @param rv ReportView
     * @return いいねの件数
     */
    public long countAllFavoriteToReport(ReportView rv) {

        long count = (long)em.createNamedQuery(JpaConst.Q_FAV_ALL_FAV_COUNT_TO_REPORT,Long.class)
                .setParameter(JpaConst.JPQL_PARM_REPORT, ReportConverter.toModel(rv))
                .getSingleResult();

        return count;
    }


    /**
     * 日報のリストをもとにいいね数のリストを作成し、返却する
     * @param reportViewList 日報のリスト
     * @return いいね数のリスト
    */
    public List<Long> getAllCountFavoriteToReport(List<ReportView> reportViewList){
        List<Long> favoriteCount = new ArrayList<Long>();
        for(ReportView rv : reportViewList){
            favoriteCount.add(countAllFavoriteToReport(rv));
        }
        return favoriteCount;


    }

    /**
     * 日報に関する自分のいいねデータの件数を取得する
     * @param rv 日報データ
     * @param ev 従業員データ
     * @return 自分のいいねデータの件数
     */
    public long countCreatedMineFavoriteDataToReport(ReportView rv,EmployeeView ev) {

        long count = (long)em.createNamedQuery(JpaConst.Q_FAV_COUNT_CREATED_MINE_FAV_DATA_TO_REPORT,Long.class)
                .setParameter(JpaConst.JPQL_PARM_REPORT, ReportConverter.toModel(rv))
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(ev))
                .getSingleResult();

        return count;
    }

    /**
     * 日報に従業員がいいねした件数を取得する
     * @param rv 日報データ
     * @param ev 従業員データ
     * @return 自分のいいねの件数
     */
    public long countMineFavoriteToReport(ReportView rv,EmployeeView ev) {

        long count = (long)em.createNamedQuery(JpaConst.Q_FAV_COUNT_MINE_FAV_TO_REPORT,Long.class)
                .setParameter(JpaConst.JPQL_PARM_REPORT, ReportConverter.toModel(rv))
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(ev))
                .getSingleResult();

        return count;
    }

    /**
     * 従業員用の日報いいねテーブルを作成する(既にある場合は作成しない)
     * @param rv 日報データ
     * @param ev 従業員データ
     */
    public void create(ReportView rv,EmployeeView ev) {
        if(countCreatedMineFavoriteDataToReport(rv,ev) == 0) {
            FavoriteView favoriteView = new FavoriteView(
                    null,
                    ReportConverter.toModel(rv),
                    EmployeeConverter.toModel(ev),
                    AttributeConst.FAV_FLAG_FALSE.getIntegerValue(),
                    LocalDateTime.now());
            createInternal(FavoriteConverter.toModel(favoriteView));
        }
    }

    /**
     * いいねデータを作成する
     * @param rv いいねデータ
     */
    private void createInternal(Favorite r) {
        em.getTransaction().begin();
        em.persist(r);
        em.getTransaction().commit();
    }

}