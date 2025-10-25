package bt4;

//数字スライム
public class NumberSlime {
    public int number; //自スライムの数字
    //引数の数字(num)を自スライムの数字(number)に代入する．
    public NumberSlime(int num) {
        this.number = num;
    }

    /**
     * 吸収(absorb): 自スライムの数字に引数のスライムの数字を全て加算した後に，
     * 引数のスライムの数字を 0 にする．
     * @param slime
     */
    public void absorb(NumberSlime slime) {
        // 自スライムに引数スライムの数字をすべて加算
        this.number += slime.getNumber();
        // 引数スライムの数字を0にする
        slime.setNumber(0);
    }

    /**
     * 分裂(split)：自スライムの数字の半分を分け与えて新スライムを作り，
     * 新スライムを返す．自スライムの数字を半分にした数が小数の場合は，
     * それを切り下げた数字（整数）を自スライムも引数スライムも持つこととする．
     * @return NumberSlime
     */
    public NumberSlime split() {
        int newSlimeNumber = this.number / 2;
        // 分裂後に自スライムの`number`を更新
        this.number = newSlimeNumber;
        // 新しいスライムを作成
        return new NumberSlime(newSlimeNumber);
    }

    /**
     * 平均化(average)：自スライムと引数スライムが両者の数字の平均値となる
     * 整数をそれぞれ持つようにする．両者の数字の平均値が小数の場合は，
     * それを切り下げた数字（整数）を自スライムも引数スライムも持つこととする．
     * @param slime
     */
    public void average(NumberSlime slime) {
        int averageSlimeNumber = (this.number + slime.getNumber()) / 2;
        
        // 自スライムの`number`を更新
        this.number = averageSlimeNumber;

        // 引数スライムのnumberを更新
        slime.setNumber(averageSlimeNumber);
    }

    //自スライムの数字を返す
    public int getNumber() {
        return this.number;
    }

    /**
     * setterを実装
     * @param number
     */
    public void setNumber(int number) {
        this.number = number;
    }
}


