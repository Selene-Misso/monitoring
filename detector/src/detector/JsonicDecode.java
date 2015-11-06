package detector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import net.arnx.jsonic.JSON;

public class JsonicDecode {
	private Detector detector;
	public static void main(String[] args) {
		JsonicDecode jd = new JsonicDecode();
		jd.readJsonFile();
	}
	
	public JsonicDecode(){
		detector = new Detector();
	}
	
	/**
	 * JSONファイルを1行ずつ読み込む
	 */
	public void readJsonFile(){
		String name = "/Users/mishiro/Git/aws/monitoring/2015-10-26T03:03:32Z/WEB/net.dat";
		try {
			File file = new File(name);
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			// ファイルを1行ずつ読む
			String str = br.readLine();
			String json = "";
			int i = 1;
			while(str != null){
				if(str.equals("[") || str.equals(",") || str.equals("]")){
					// JSON配列を除去
				}else if(str.startsWith("{") && i == 1){
					// {から始まる文字列と
					i = 0; json = str;
				}else if(str.endsWith("}") && i == 0){
					// }で終わる文字列を結合
					i = 1; json += str;
					//System.out.println(json);
					parseJson(json);
				}
				str = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * netstatのJSONをパースする．
	 * @param str
	 */
	public void parseJson(String str){
		NetstatJson netstat = JSON.decode(str.replace(".UTC", ""), NetstatJson.class);
		
		boolean result = detector.isEndOfEstablished(netstat.getESTABLISHED());
		System.out.print("Timestamp: " + netstat.getTimestamp());
		System.out.println(", ESTABLISHED: " + netstat.getESTABLISHED() + ", "+ result);
/*		System.out.println("FIN_WAIT1: "+ netstat.getFIN_WAIT1());
		System.out.println("FIN_WAIT2: "+ netstat.getFIN_WAIT2());
		System.out.println("TIME_WAIT: "+ netstat.getTIME_WAIT());
		System.out.println("LAST_ACK: "+ netstat.getLAST_ACK());
		System.out.println("SYN_RECV: "+ netstat.getSYN_RECV());*/
	}
}
