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
        String mode = args.length > 0 ? args[0] : "cui";

        if (mode.equalsIgnoreCase("gui")) {
            javax.swing.SwingUtilities.invokeLater(() -> {
                GameFrame frame = new GameFrame("stage_settings.txt");
                frame.setVisible(true);
            });
        } else {
            try {
                System.out.println("Hit & Blow ゲームを開始します！");
                String stageSettingsFilePath = "stage_settings.txt"; // ステージ設定ファイルのパス
                GameManager gameManager = new GameManager(stageSettingsFilePath);
                gameManager.start();
            } catch (IOException e) {
                System.err.println("ステージ設定ファイルの読み込み中にエラーが発生しました: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                System.err.println("エラーが発生しました: " + e.getMessage());
                e.printStackTrace();
            } finally {
                System.out.println("ゲームを終了します");
            }
        }
    }
}
