package detector;
/**
 * netstatのログをJSON形式で表現したもの
 * JSONICで使用する
 * @author mishiro
 *
 */
public class NetstatJson {
	private String Timestamp;
	private int ESTABLISHED;
	private int FIN_WAIT1;
	private int FIN_WAIT2;
	private int TIME_WAIT;
	private int LAST_ACK;
	private int SYN_RECV;

	public String getTimestamp() {return Timestamp;}
	public void setTimestamp(String timestamp) {this.Timestamp = timestamp;}

	public int getESTABLISHED() {return ESTABLISHED;}
	public void setESTABLISHED(int eSTABLISHED) {ESTABLISHED = eSTABLISHED;}

	public int getFIN_WAIT1() {return FIN_WAIT1;}
	public void setFIN_WAIT1(int fIN_WAIT1) {FIN_WAIT1 = fIN_WAIT1;}

	public int getFIN_WAIT2() {return FIN_WAIT2;}
	public void setFIN_WAIT2(int fIN_WAIT2) {FIN_WAIT2 = fIN_WAIT2;}

	public int getTIME_WAIT() {return TIME_WAIT;}
	public void setTIME_WAIT(int tIME_WAIT) {TIME_WAIT = tIME_WAIT;}

	public int getLAST_ACK() {return LAST_ACK;}
	public void setLAST_ACK(int lAST_ACK) {LAST_ACK = lAST_ACK;}

	public int getSYN_RECV() {return SYN_RECV;}
	public void setSYN_RECV(int sYN_RECV) {SYN_RECV = sYN_RECV;}

}
