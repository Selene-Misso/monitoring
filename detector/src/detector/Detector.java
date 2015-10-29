package detector;

public class Detector {
	// 移動平均で使用する秒数
	int interval = 3;
	// 検出閾値
	int threshold = -30;
	// バッファ
	int array[] = new int[interval];

	/**
	 * ESTABLISHEDをみて終了時を検出する関数．
	 * @param input 現時刻のESTABLISHEDの値
	 * @return 終了を検出したらtrue, 検出しなかったらfalse
	 */
	public boolean isEndOfEstablished(int input){
		// 前回の平均をとる
		double last = calcAverage(array);
		
		// 移動平均
		array = calcMovingAverage(array, input, interval);
		// 平均
		double average = calcAverage(array);
		
		// 前回と今回の平均が閾値以下なら検出
		if((average - last) <= threshold){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 移動平均を計算する関数
	 * @param array バッファ
	 * @param push 新しい値
	 * @param interval バッファの数
	 * @return 新しいバッファ(配列)
	 */
	public int[] calcMovingAverage(int[] array, int push, int interval){
		// 配列を一つずつずらす
		for(int i = 0; i < (interval - 1); i++){
			array[i] = array[i+1];
		}
		// 最後にpushを格納する
		array[interval - 1] = push;
		
		return array;
		
	}
	
	/**
	 * 配列の平均を計算する
	 * @param array 入力配列
	 * @return 入力配列の平均値
	 */
	public double calcAverage(int[] array){
		int sum = 0;
		for(int i = 0; i < array.length; i++){
			sum += array[i];
		}
		return ((double)sum) / array.length;
	}

}
