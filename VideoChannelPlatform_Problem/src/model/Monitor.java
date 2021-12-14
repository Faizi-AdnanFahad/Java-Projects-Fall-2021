package model;

public class Monitor extends Follower {
	
	private int[] views; /* views[i] indicates how many times channel[i] has been watched*/
	private int[] total; /* total[i] indicates the total watch time of channel[i] */
	private int[] max; /* max[i] indicates the maximum watch time of channel[i]  */
	private double[] avg; /* avg[i] indicates the maximum watch time of channel[i] */
	
	public Monitor(String name, int maxNumOfChannel) {
		super(name, maxNumOfChannel);
		this.views = new int[maxNumOfChannel];
		this.total = new int[maxNumOfChannel];
		this.max = new int[maxNumOfChannel];
		this.avg = new double[maxNumOfChannel];
	}
	
	public String toString() {
		String result = "";
		
		String statsMonitor = "[";
		for (int i = 0; i < this.noC; i ++) {
			if (this.views[i] > 0) {
				statsMonitor += this.channels[i].getChannelName() + " {#views: " 
						+ this.views[i] + ", max watch time: " 
						+ this.max[i] + ", avg watch time: " + String.format("%.2f", this.avg[i]) + "}";
			}
			else {
				statsMonitor += this.channels[i].getChannelName();
			}
			if (i < this.noC - 1) {
				statsMonitor += ", ";
			}
		}
		statsMonitor += "]";
		
		if (this.noC == 0) {
			result = "Monitor " + this.name + " follows no channels.";
		}
		else if (this.noC != 0) {
			result = "Monitor " + this.name + " follows " + statsMonitor + ".";
		}
		
		return result;
	}
	
	public void updateStat(String channelName, int watchTime) {
		int index = -1;
		for (int i = 0; i < this.noC; i ++) {
			if (this.channels[i].getChannelName().equals(channelName)) {
				index = i;
			}
		}
		this.views[index] ++;
		this.total[index] += watchTime;
		
		if (this.max[index] < watchTime) {
			this.max[index] = watchTime;
		}
		
		this.avg[index] = ((double) this.total[index] / this.views[index]);
	}
}
