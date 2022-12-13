package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Favorite;

/**
 * いいねデータのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */

public class FavoriteConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param fv LikeViewのインスタンス
     * @return Likeのインスタンス
     */

    public static Favorite toModel(FavoriteView fv) {

        return new Favorite(
                fv.getId(),
                EmployeeConverter.toModel(fv.getEmployee_id()),
                fv.getReport_id());

    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param e Likeのインスタンス
     * @return LikeViewのインスタンス
     */
    public static FavoriteView toView(Favorite f) {

        if(f == null) {
            return null;
        }

        return new FavoriteView(
                f.getId(),
                EmployeeConverter.toView(f.getEmployee_id()),
                f.getReport_id());
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<FavoriteView> toViewList(List<Favorite> list) {
        List<FavoriteView> evs = new ArrayList<>();

        for (Favorite f : list) {
            evs.add(toView(f));
        }

        return evs;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param f DTOモデル(コピー先)
     * @param fv Viewモデル(コピー元)
     */
    public static void copyViewToModel(Favorite f, FavoriteView fv) {
        f.setId(fv.getId());
        f.setEmployee_id(EmployeeConverter.toModel(fv.getEmployee_id()));
        f.setReport_id(fv.getReport_id());


    }


}
