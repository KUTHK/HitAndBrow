import java.io.*;
import java.util.*;

/**
 * StageManagerクラス
 * ゲームのステージを管理するクラス。
 * 
 * 主な役割:
 * - 現在のステージ番号の管理
 * - 総ステージ数の管理
 * - ステージ進行の制御
 * - ステージごとの設定の保持
 * 
 * 使用方法:
 * - `new StageManager(String filePath)`でインスタンスを作成します。
 * - `nextStage()`で次のステージに進みます。
 * - `isLastStage()`で現在のステージが最後かどうかを判定します。
 * - `getCurrentStage()`で現在のステージ番号を取得します。
 * - `loadStageSettings(String filePath)`でステージ設定をファイルから読み込みます。
 * 
 * クラス変数:
 * - currentStage: 現在のステージ番号
 * - totalStages: 総ステージ数
 * - stageSettings: 各ステージの設定を保持するリスト
 */
public class StageManager {
    private int currentStage; // 現在のステージ番号
    private int totalStages; // 総ステージ数
    private List<Map<String, Integer>> stageSettings; // 各ステージの設定を保持

    /**
     * コンストラクタ
     * 指定された総ステージ数でStageManagerオブジェクトを初期化します。
     * 
     * @param totalStages 総ステージ数
     */
    public StageManager(String filePath) {
        this.currentStage = 0;
        try {
            this.stageSettings = initializeStageSettings(loadStageSettings(filePath));
        } catch (IOException e) {
            throw new RuntimeException("ステージ設定ファイルの読み込み中にエラーが発生しました: " + filePath, e);
        }
    }

    /**
     * 現在のステージ番号を取得します。
     * 
     * @return 現在のステージ番号
     */
    public int getCurrentStage() {
        return currentStage;
    }

    /**
     * 総ステージ数を取得します。
     * 
     * @return 総ステージ数
     */
    public int getTotalStages() {
        return totalStages;
    }

    /**
     * ステージの初期化を行います。
     */
    private List<Map<String, Integer>> initializeStageSettings(List<Map<String, Integer>> settings) {
        this.stageSettings = settings;
        this.totalStages = settings.size();
        return settings;
    }

    /**
     * 次のステージに進みます。
     * ステージ番号を1増やします。
     */
    public void nextStage() {
        if (currentStage < totalStages) {
            currentStage++;
        }
    }

    /**
     * 現在のステージが最後のステージかどうかを判定します。
     * 
     * @return 最後のステージの場合はtrue、それ以外はfalse
     */
    public boolean isLastStage() {
        return currentStage == totalStages;
    }


    /**
     * 現在のステージの設定を取得します。
     * 
     * @return 現在のステージの設定マップ (例: life, numCount, minValue, maxValue)
     */
    public Map<String, Integer> getCurrentStageSetting() {
        if (stageSettings == null || currentStage >= stageSettings.size()) {
            throw new IllegalStateException("ステージ設定が正しく初期化されていません。");
        }
        return stageSettings.get(currentStage);
    }

    /**
     * ファイルからステージ設定を読み込みます。
     * 
     * @param filePath 読み込み元のファイルパス
     * @return ステージ設定のリスト (各ステージの設定を保持するマップのリスト)
     * @throws IOException ファイル操作中のエラー
     */
    public List<Map<String, Integer>> loadStageSettings(String filePath) throws IOException {
        List<Map<String, Integer>> stage = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            // 最初の行はコメントとして無視
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",\\s*");
                Map<String, Integer> settings = new HashMap<>();
                settings.put("life", Integer.parseInt(parts[0]));
                settings.put("numCount", Integer.parseInt(parts[1]));
                settings.put("minValue", Integer.parseInt(parts[2]));
                settings.put("maxValue", Integer.parseInt(parts[3]));
                stage.add(settings);
            }
        }
        return stage;
    }

    /**
     * 現在のステージの数字の個数を取得します。
     * 
     * @return 現在のステージの数字の個数
     */
    public int getNumCount() {
        return getCurrentStageSetting().get("numCount");
    }

    /**
     * 現在のステージの最小値を取得します。
     * 
     * @return 現在のステージの最小値
     */
    public int getMinValue() {
        return getCurrentStageSetting().get("minValue");
    }

    /**
     * 現在のステージの最大値を取得します。
     * 
     * @return 現在のステージの最大値
     */
    public int getMaxValue() {
        return getCurrentStageSetting().get("maxValue");
    }

    /**
     * 現在のステージの最大試行回数を取得します。
     * 
     * @return 現在のステージの最大試行回数
     */
    public int getMaxLife() {
        return getCurrentStageSetting().get("life");
    }
}
