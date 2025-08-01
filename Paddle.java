/**
 * Paddleクラス
 * 数字のリストを保持し、ランダムな数字列を生成する機能を提供します。
 * 
 * 主な役割:
 * - 数字列の保持
 * - ランダムな数字列の生成
 * 
 * 使用方法:
 * - `new Paddle(int[] input)`で指定された数字列を保持するインスタンスを作成します。
 * - `generateRandom(int numCount, int minValue, int maxValue)`でランダムな数字列を生成します。
 * - `getBalls()`で保持している数字列を取得します。
 * 
 * クラス変数:
 * - balls: 数字列を保持するリスト
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Paddle {
    private List<Ball> balls; // 数字列を保持

    /**
     * コンストラクタ
     * 指定された数字列でPaddleオブジェクトを初期化します。
     * 
     * @param input 数字列
     */
    public Paddle(int[] input) {
        this.balls = new ArrayList<>();
        for (int x : input) {
            this.balls.add(new Ball(x));
        }
    }

    /**
     * ランダムな数字列を生成します。
     * 
     * @param numCount 数字列の桁数
     * @param minValue 数字の最小値
     * @param maxValue 数字の最大値
     * @return ランダムな数字列を持つPaddleオブジェクト
     */
    public static Paddle generateRandom(int numCount, int minValue, int maxValue) {
        Random random = new Random();
        int[] input = new int[numCount];
        for (int i = 0; i < numCount; i++) {
            // minValue-maxValueのランダムな数字を生成
            input[i] = random.nextInt(maxValue - minValue + 1) + minValue;
        }
        return new Paddle(input);
    }

    /**
     * 数字列を取得します。
     * 
     * @return 数字列を保持するリスト
     */
    public List<Ball> getBalls() {
        return balls;
    }

    /**
     * 数字列を文字列として標準出力に表示．
     * 
     */
    public void println() {
        for (Ball ball : balls) {
            System.out.print(ball.getValue() + " ");
        }
    }
}
