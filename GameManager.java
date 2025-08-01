import java.io.IOException;

/**
 * GameManagerクラス
 * ゲーム全体の制御を担当するクラス。
 * 
 * 主な役割:
 * - 正解の生成
 * - プレイヤーからの入力受付
 * - 判定結果の表示
 * - ゲームの繰り返し処理
 * 
 * 使用方法:
 * - `new GameManager(int numCount, int max_life)`でインスタンスを作成します。
 * - `new GameManager(int numCount, int max_life, int min_value, int max_value)`でインスタンスを作成します。
 * - `start()`メソッドを呼び出してゲームを開始します。
 * 
 * クラス変数:
 * - answer: 正解の数字列を保持するPaddleオブジェクト
 * - player: プレイヤー情報を管理するPlayerオブジェクト
 * - judge: 判定ロジックを提供するJudgeオブジェクト
 * - max_life: 最大試行回数
 * - numCount: 数字の個数
 * - minValue: 数字の最小値
 * - maxValue: 数字の最大値
 */
public class GameManager {
    private Paddle answer; // 正解の数字列
    private Player player; // プレイヤー情報
    private Judge judge; // 判定ロジック
    private int max_life; // 最大試行回数
    private int numCount; // 数字の個数
    private int minValue = 0; // 数字の最小値
    private int maxValue = 9; // 数字の最大値
    private StageManager stageManager; // ステージ管理

    /**
     * コンストラクタ
     * 指定された設定ファイルパスでGameManagerオブジェクトを初期化します。
     * 
     * @param stageSettingsFilePath ステージ設定ファイルのパス
     * @throws IOException 入出力例外
     */
    public GameManager(String stageSettingsFilePath) throws IOException {
        this.stageManager = new StageManager(stageSettingsFilePath);
        initializeGame();
        this.judge = new Judge();
    }

    /**
     * クラス変数の初期化
     * ゲーム開始前に必要な変数を初期化します。
     */
    private void initializeGame() {
        if (stageManager == null) {
            throw new IllegalStateException("StageManagerが初期化されていません。");
        }
        else if (!stageManager.isLastStage()) {
            this.numCount = stageManager.getNumCount();
            this.minValue = stageManager.getMinValue();
            this.maxValue = stageManager.getMaxValue();
            this.max_life = stageManager.getMaxLife();
            this.answer = Paddle.generateRandom(numCount, minValue, maxValue);
            this.player = new Player(max_life);
        }
        // debug用に正解を表示
        System.out.println("正解: ");
        answer.println();
        System.out.println();
    }

    /**
     * ゲームを開始します。
     * プレイヤーの入力を受け付け、正解と比較して結果を表示します。
     * ゲームはプレイヤーが正解するか、試行回数が尽きるまで続きます。
     */
    public void start() {
        initializeGame();
        System.out.println("ゲーム開始！ 総ステージ数: " + stageManager.getTotalStages());
        while (player.getLife() > 0 && !stageManager.isLastStage()) {
            System.out.printf("ステージ %d/%d%n", stageManager.getCurrentStage(), stageManager.getTotalStages());

            System.out.println("桁数: " + numCount + ", 最大試行回数: " + max_life + ", 数字範囲: " + minValue + "-" + maxValue);

            Paddle guess = player.getInput(numCount, minValue, maxValue);
            int[] result = judge.compare(answer, guess);
            System.out.printf("結果: Hit=%d, Blow=%d%n", result[0], result[1]);

            if (result[0] == answer.getBalls().size()) {
                System.out.println("ステージクリア！次のステージに進みます。");
                stageManager.nextStage();
                initializeGame();
            } else {
                player.decLife();
                System.out.printf("残り試行回数: %d%n", player.getLife());
            }
        }

        if (stageManager.isLastStage()) {
            System.out.println("おめでとうございます！全ステージをクリアしました！");
        } else {
            System.out.print("ゲームオーバー！正解は ");
            answer.println();
            System.out.print(" でした。");
        }
    }
}
