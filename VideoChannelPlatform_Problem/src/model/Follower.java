package model;

public class Follower {
	
	protected String name;
	
	protected Channel[] channels;
	protected int noC; // NUmber of Channels;
	
	public Follower(String name, int maxNumOfChannels) {
		this.name = name;
		this.channels = new Channel[maxNumOfChannels];
		this.noC = 0;
	}
	
	public void Follow(Channel c) {
		this.channels[this.noC] = c;
		this.noC ++;
	}
	
	public void unFollow(Channel c) {
		int index = -1;
		for (int i = 0; i < this.noC; i ++) {
			if (this.channels[i].getChannelName().equals(c.getChannelName())) {
				this.channels[i] = null;
				index = i;
			}
		}
		
		if (index != -1) {
			for (int i = index; i < this.noC; i ++) {
				if (i < this.noC - 1) {
					this.channels[i] = this.channels[i + 1];
				}
				else {
					this.channels[i] = null;
				}
				this.noC -= 1;
			}
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	/* Helper Method */
	protected String getChannelArrayInString() {
		String listOfChannels = "[";
		for (int i = 0; i < this.noC; i ++) {
			listOfChannels += this.channels[i].getChannelName();
			if (i < this.noC - 1) {
				listOfChannels += ", ";
			}
		}
		listOfChannels += "]";
		return listOfChannels;
	}
}
