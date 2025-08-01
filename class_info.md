# Hit & Blow：内部設計書

---

## 全体構成図

```
Main
├── soundManager // 音楽関係を制御
└── GameManager  // ゲーム全体の流れを制御
    ├── Player   // 入力を受け取り、Paddleを返す
    ├── Ball     // 1桁の数字と表示を担当
    ├── Paddle   // 入力された数字列（Ballの並び）
    ├── display  // ゲーム実行時の表示全般
    └── Judge    // Paddle同士を比較し、Hit/Blowを判定
```

---

## クラス設計

<details>
<summary>Main</summary>



* **役割**：プログラムのエントリーポイント。GameManager を起動するだけのクラス。
* **メソッド**：

  * `main(String[] args)`
</details>

---

<details>
<summary>GameManager</summary>


* **役割**：ゲーム全体の制御（表示、入力受付、判定、繰り返し、終了処理）
* **主な役割**：

  * 選択肢を表示する
  * プレイヤーに数字列を入力させる
  * 正解（答え）との比較結果（Hit, Blow）を表示
  * プレイヤーのライフがなくなるまでor正解が出るまで繰り返す
* **メンバ変数例**：

  * `Paddle answer`（ランダムに生成された正解）
  * `Player player`（ユーザーの入力担当）
</details>

---

<details>
<summary>Player</summary>


* **役割**：ユーザーからの数字列の入力を受け取り、Paddleとして返す
* **主な役割**：

  * 入力バリデーション（桁数、重複、範囲）
  * 残り試行回数の制御
  * 入力結果を `Paddle` として返す
* **メソッド例**：
</details>

---

<details>
<summary>Paddle</summary>


* **役割**：数字列（Ballの並び）を持つデータクラス
* **主な役割**：

  * Ballを保持
  * 横並びでの表示
</details>

---

<details>
<summary>Ball</summary>


* **役割**：1桁の数字と、その表示処理を担当する部品クラス
* **主な役割**：

  * `value`（数値）の保持
  * `display()`
</details>

---

<details>
<summary>Judge</summary>


* **役割**：正解とプレイヤーの予想を比較し、Hit / Blow を返す
* **主な役割**：

  * `compare(List<Ball> answer, List<Ball> guess)` のような判定メソッドを提供
  * 戻り値： `Result`（例：`new int[]{hit, blow}`）
</details>

---

## データ構造

| データ | 型  | 説明 |
| --- | --- | --- |
| 数字の並び | `List<Ball>` | Paddle 内部に保持 |
| 正解データ | `Paddle` | GameManager が初期化時に生成 |
| 入力データ | `Paddle` | Player が入力して作成 |
| 判定結果  | `int[]`（`hit`, `blow`）| Judge から返る |

---

## 処理の流れ（例）

1. GameManager 起動、正解（Paddle）をランダム生成
2. GameManager が Ball 候補を横並びで表示
3. Player に入力を促す（例：4桁）
4. 入力バリデーション → Paddle に変換
5. GameManager → Judge に Paddle（正解・入力）を渡す
6. Judge が Hit / Blow を判定して返す
7. 結果表示
8. 正解 or 最大試行回数まで繰り返し

---

## 📝 設計上のポイント

* `Paddle` は入力値・正解のどちらにも使える汎用的なラッパー
* `Judge` がロジックを担当することで、役割分離が明確
* CLIベースだが、構成はそのままGUIへの展開がしやすい

---

## 🚀 拡張可能性（例）

| 機能追加               | 方法                                   |
| ------------------ | ------------------------------------ |
| GUI化（Swing/JavaFX） | `Ball.display()` を `JButton` などに差し替え |
| ターン数制限・スコア表示       | `GameManager` にカウント・スコアロジックを追加       |
| 多人数対応              | `Player` クラスを複数インスタンスで管理             |
| 結果履歴表示             | `List<Paddle>` をログとして保持、毎ターン表示       |
