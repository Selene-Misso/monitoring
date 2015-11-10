package detector;

public class Detector {
	// [ESTABLISHED] 移動平均で使用する秒数
	int interval = 3;
	// [ESTABLISHED] 検出閾値
	int threshold = -30;
	// [ESTABLISHED] バッファ
	int array[] = new int[interval];
	
	// [TIME_WAIT] 判定結果を保持する秒数
	int interval_time_waits = 60;
	// [TIME_WAIT] 閾値
	int max_time_waits = 4000;
	// [TIME_WAIT] バッファ
	int array_time_waits[] = new int[interval_time_waits];

	/**
	 * ESTABLISHEDをみて終了時を検出する関数．
	 * @param input 現時刻のESTABLISHEDの値
	 * @return 終了を検出したらtrue, 検出しなかったらfalse
	 */
	public boolean isEndOfEstablished(int input){
		// 前回の平均をとる
		double last = calcAverage(array);
		
		// 移動平均
		array = moveArray(array, input, interval);
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
	public int[] moveArray(int[] array, int push, int interval){
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

	/**
	 * TIME_WAITが閾値を超えたかを判定する
	 * @param num TIME_WAITの値
	 * @return 閾値を超えたらTrue,超えなかったらFalse
	 */
	public boolean isSaturated(int num){
		if(num >= max_time_waits)
			return true;
		else
			return false;
	}
	
	/**
	 * TIME_WAITが指定期間長い状態が続き，停止状態になりそうかを判定する
	 * @param input 新しいTIME_WAIT値
	 * @return 停止状態ならtrue, それ以外ならfalse
	 */
	public boolean isLongTimeWait(int input){
		// 判定
		int x = 0;
		if(isSaturated(input)) x = 1;
		
		// 配列にプッシュ
		array_time_waits = moveArray(array_time_waits, x, interval_time_waits);
		
		// 配列を平均
		double ave = calcAverage(array_time_waits);
		
		System.out.print(input+","+ave + ":");
		
		if(ave >= 0.9)
			return true;
		else
			return false;
	}
	
}
