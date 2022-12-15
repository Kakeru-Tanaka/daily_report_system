package actions.views;

import constants.AttributeConst;
import constants.JpaConst;
import models.Favorite;

/**
 * いいねデータのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class FavoriteConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param rv FavoriteViewのインスタンス
     * @return Favoriteのインスタンス
     */
    public static Favorite toModel(FavoriteView rv) {
        return new Favorite(
                rv.getId(),
                rv.getEmployee(),
                rv.getReport(),
                rv.getFavoriteFlag() == null
                    ? null
                    : rv.getFavoriteFlag() == AttributeConst.FAV_FLAG_TRUE.getIntegerValue()
                        ? JpaConst.FAV_REP_TRUE
                        : JpaConst.FAV_REP_FALSE,
                rv.getFavoriteAt());
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param r Favoriteのインスタンス
     * @return FavoriteViewのインスタンス
     */
    public static FavoriteView toView(Favorite r) {
        return new FavoriteView(
                r.getId(),
                r.getReport(),
                r.getEmployee(),
                r.getFavoriteFlag() == null
                    ? null
                    : r.getFavoriteFlag() == JpaConst.FAV_REP_TRUE
                        ? AttributeConst.FAV_FLAG_TRUE.getIntegerValue()
                        : AttributeConst.FAV_FLAG_FALSE.getIntegerValue(),
                r.getFavoriteAt());
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param r DTOモデル(コピー先)
     * @param rv Viewモデル(コピー元)
     */
    public static void copyViewToModel(Favorite r,FavoriteView rv) {
        r.setId(rv.getId());
        r.setReport(rv.getReport());
        r.setEmployee(rv.getEmployee());
        r.setFavoriteFlag(rv.getFavoriteFlag());
        r.setFavoriteAt(rv.getFavoriteAt());
    }

}