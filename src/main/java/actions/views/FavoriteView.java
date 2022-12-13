package actions.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.Report;

/**
 * いいねについて画面の入力値・出力値を扱うViewモデル
 *
 */
@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)


public class FavoriteView {

    /**
     * id
     */
    private Integer id;

    /**
     * いいねをした従業員のid
     */
    private EmployeeView employee_id;

    /**
     * いいねをしたレポートのid
     */
    private Report report_id;

}
