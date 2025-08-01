/**
 * Judgeクラス
 * プレイヤーの予想と正解を比較し、HitとBlowの数を判定します。
 * 
 * 主な役割:
 * - Hit: 数字と位置が両方一致している場合のカウント
 * - Blow: 数字は一致しているが位置が異なる場合のカウント
 * 
 * 使用方法:
 * - `compare(Paddle answer, Paddle guess)`メソッドを呼び出して判定を実行します。
 * - 戻り値はint配列で、[0]がHitの数、[1]がBlowの数を表します。
 * 
 * クラス変数:
 * - なし
 */

public class Judge {
    /**
     * プレイヤーの予想と正解を比較し、HitとBlowの数を計算します。
     * 
     * @param answer 正解のPaddleオブジェクト
     * @param guess プレイヤーの予想のPaddleオブジェクト
     * @return int配列 [Hitの数, Blowの数]
     */
    public int[] compare(Paddle answer, Paddle guess) {
        int hit = 0; // 数字と位置が一致している数
        int blow = 0; // 数字は一致しているが位置が異なる数

        // 正解の各Ballとプレイヤーの予想を比較
        for (int i = 0; i < answer.getBalls().size(); i++) {
            final Ball ans = answer.getBalls().get(i);

            // 位置と数字が一致している場合はHitをカウント
            if (ans.equals(guess.getBalls().get(i))) {
                hit++;
            } 
            // 数字は一致しているが位置が異なる場合はBlowをカウント
            else if (guess.getBalls().stream().anyMatch(ball -> ball.equals(ans))) {
                blow++;
            }
        }

        // 結果を配列で返す
        return new int[]{hit, blow};
    }
}
