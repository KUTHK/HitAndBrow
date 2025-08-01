/**
 * Ballクラス
 * 1つの数値を表すクラスで、数値の保持と表示を担当します。
 * 
 * 主な役割:
 * - 数値の保持
 * - 数値の表示
 * - 数値の比較
 * 
 * 使用方法:
 * - `new Ball(int value)`でインスタンスを作成します。
 * - `getValue()`で数値を取得します。
 * - `display()`で数値を表示します。
 * - `equals(Object obj)`で他のBallオブジェクトと値を比較します。
 * 
 * クラス変数:
 * - value: 数値を保持する整数型の変数
 */
public class Ball {
    private int value; // 数値を保持

    /**
     * コンストラクタ
     * 指定された値でBallオブジェクトを初期化します。
     * 
     * @param value 数値
     */
    public Ball(int value) {
        this.value = value;
    }

    /**
     * 数値を取得します。
     * 
     * @return 数値
     */
    public int getValue() {
        return value;
    }


    /**
     * 他のBallオブジェクトと値を比較します。
     * 
     * @param obj 比較対象のオブジェクト
     * @return 値が等しい場合はtrue、それ以外はfalse
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ball ball = (Ball) obj;
        return this.value == ball.value;
    }
}
