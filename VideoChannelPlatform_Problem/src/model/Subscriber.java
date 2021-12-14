package model;

public class Subscriber extends Follower {

	private String[] recommendedVideos;
	private int noV; /* Number of recommended videos */

	public Subscriber(String name, int maxNumOfChannel, int maxNumOfRecommendedVideos) {
		super(name, maxNumOfChannel);
		this.recommendedVideos = new String[maxNumOfRecommendedVideos];
		this.noV = 0;
	}

	public void recommendedVideo(String name) {
		this.recommendedVideos[this.noV] = name;
		this.noV ++;
	}

	public void watch(String video, int min) {
		for (int i = 0; i < this.noC; i ++) {
			for (int m = 0; m < this.channels[i].getNoV(); m ++) {
				if (this.channels[i].getReleasedVideos()[m].equals(video)) {
					this.channels[i].addWatchTime(min);
					break;
				}
			}
		}
		
	}

	public String toString() {
		String result = "";

		String listOfVideos = "<";
		for (int i = 0; i < this.noV; i ++) {
			listOfVideos += this.recommendedVideos[i];
			if (i < this.noV - 1) {
				listOfVideos += ", ";
			}
		}
		listOfVideos += ">";

		if (this.noC == 0) {
			result = "Subscriber " + this.name + 
					" follows no channels and has no recommended videos.";
		}
		else if (this.noC != 0 && this.noV == 0) {
			result = "Subscriber " + this.name
					+ " follows " + super.getChannelArrayInString() 
					+ " and has no recommended videos.";
		}
		else if (this.noC == 0 && this.noV != 0) {
			result = "Subscriber " + this.name
					+ " follows no channels and and is recommended " + listOfVideos + ".";
		}
		else if (this.noC != 0 && this.noV != 0) {
			result = "Subscriber " + this.name
					+ " follows " + super.getChannelArrayInString()
					+ " and is recommended " + listOfVideos + ".";
		}
		return result;
	}

}
