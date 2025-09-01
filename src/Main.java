import java.io.IOException;

/**
 * Mainクラス
 * プログラムのエントリーポイント。
 * GameManagerを起動してゲームを開始します。
 * 
 * 主な役割:
 * - ゲームの初期化
 * - ゲームの開始
 * 
 * 使用方法:
 * - このクラスを実行することでゲームが開始されます。
 * - ゲーム終了後に終了メッセージが表示されます。
 */
public class Main {
    /**
     * プログラムのエントリーポイント。
     * GameManagerを初期化し、ゲームを開始します。
     * 
     * @param args コマンドライン引数（未使用）
     */
    public static void main(String[] args) {
        try {
            System.out.println("Hit & Blow ゲームを開始します！");
            String stageSettingsFilePath = "../resources/stage_settings.txt"; // ステージ設定ファイルのパス
            try {
                GameManager gameManager = new GameManager(stageSettingsFilePath);
                gameManager.start();
            } catch (IOException e) {
                System.err.println("ステージ設定ファイルの読み込み中にエラーが発生しました: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.err.println("エラーが発生しました: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("ゲームを終了します");
        }
    }
}
