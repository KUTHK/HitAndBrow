import java.util.Scanner;

/**
 * Playerクラス
 * ユーザーからの入力を受け取り、Paddleオブジェクトとして返すクラス。
 * また、残り試行回数の管理も行います。
 * 
 * 主な役割:
 * - ユーザー入力のバリデーション
 * - 残り試行回数の管理
 * - 入力結果をPaddleとして返す
 * 
 * 使用方法:
 * - `new Player(int maxTries)`でインスタンスを作成します。
 * - `getInput(int numCount, int minValue, int maxValue)`でユーザー入力を取得します。
 * - `getLife()`で残り試行回数を取得します。
 * - `decLife()`で残り試行回数を1減らします。
 * 
 * クラス変数:
 * - life: 残り試行回数を保持する整数型の変数
 * - scanner: ユーザー入力用のスキャナー
 */
public class Player {
    private int life; // 残り試行回数
    private Scanner scanner; // ユーザー入力用のスキャナー

    /**
     * コンストラクタ
     * 指定された試行回数でPlayerオブジェクトを初期化します。
     * 
     * @param maxTries 最大試行回数
     */

    public Player(int maxTries) {
        this.life = maxTries;
        this.scanner = new Scanner(System.in); // スキャナーを初期化
    }

    /**
     * ユーザーからの入力を受け取り、Paddleオブジェクトとして返します。
     * 
     * @param numCount 入力する数字の個数
     * @param minValue 数字の最小値
     * @param maxValue 数字の最大値
     * @return ユーザー入力を基にしたPaddleオブジェクト
     * @throws IllegalArgumentException 入力が無効な場合
     */

    public Paddle getInput(int numCount, int minValue, int maxValue) {
        String[] tokens;

        while (true) {
            // try {
            //     Thread.sleep(1000); // 1秒待機してから入力を促す
            // } catch (InterruptedException e) {
            //     Thread.currentThread().interrupt();
            //     System.out.println("スリープ中に割り込みが発生しました。");
            // }
            System.out.print("数字列を入力してください: ");
            try {
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("入力が空です。もう一度入力してください。");
                    continue;
                }

                tokens = input.split("\\s+");

                if (tokens.length != numCount) {
                    System.out.printf("%d個の数字をスペース区切りで入力してください。%n", numCount);
                    continue;
                }
            } catch (Exception e) {
                System.out.println("入力時にエラーが発生しました: " + e.getMessage());
                continue;
            }

            int[] digits = new int[numCount];
            boolean isValid = true;

            for (int i = 0; i < numCount; i++) {
                try {
                    int value = Integer.parseInt(tokens[i]);
                    if (value < minValue || value > maxValue) {
                        System.out.printf("[%d ~ %d]%n", minValue, maxValue);
                        isValid = false;
                        break;
                    }
                    digits[i] = value;
                } catch (NumberFormatException e) {
                    System.out.println("無効な入力です: 半角数字のみを使用してください。");
                    isValid = false;
                    break;
                }
            }

            if (isValid) {
                return new Paddle(digits);
            }
        }
    }

    /**
     * 残り試行回数を取得します。
     * 
     * @return 残り試行回数
     */

    public int getLife() {
        return life;
    }

    /**
     * 残り試行回数を1減らします。
     */
    public void decLife() {
        life--;
    }
}
