package bt6;

/**
 * Person クラス（親クラス）
 * 
 * 【課題の解き方】 Student クラスと Teacher クラスを見比べると、同じようなコードが たくさんあることに気づく。 具体的には： - id, myoji, namaeという3つの変数
 * - これら3つを受け取るコンストラクタ - getProfile() メソッド（プロフィール情報を作る） - getId() メソッド（IDを返す）
 * 
 * これらの「共通する部分」を、この Person クラスにまとめた。 Student と Teacher は、どちらも「人」であるため、Person（人）という
 * 親クラスを作って、共通する部分をここに記述することにした。
 * 
 * こうすることで、同じコードを2回書かなくて済むようになる。
 */
public class Person {
    // 共通する変数：ID、苗字、名前
    // Student も Teacher も、どちらもこれらの情報を持つ
    private String id;
    private String myoji;
    private String namae;

    // 共通するコンストラクタ
    // Student も Teacher も、作成時に id, myoji, namae を渡していた
    public Person(String id, String myoji, String namae) {
        this.id = id;
        this.myoji = myoji;
        this.namae = namae;
    }

    // 共通するメソッド：プロフィール情報を作る
    // Student も Teacher も、全く同じ形式でプロフィールを作成していた
    // protected にすることで、子クラス（Student, Teacher）から使用可能にしている
    protected String getProfile() {
        return id + ": " + myoji + " " + namae;
    }

    // 共通するメソッド：IDを返す
    // Student も Teacher も、同じように getId() を持っていた
    public String getId() {
        return id;
    }
}
