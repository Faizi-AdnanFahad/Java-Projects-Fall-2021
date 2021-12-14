package model;

public class Channel {

	private String name;

	private String[] releasedVideos;
	private int noV; // Number of released videos;

	private Follower[] followers; /* Polymorphic array */
	private int noF; // Number of followers;

	public Channel(String name, int maxFollowers, int maxReleasedVideos) {
		this.name = name;
		this.releasedVideos = new String[maxReleasedVideos];
		this.noV = 0;

		this.followers = new Follower[maxFollowers];
		this.noF = 0;
	}

	public void releaseANewVideo(String video) {
		this.releasedVideos[this.noV] = video;
		this.noV ++;
		
		for (int i = 0; i < this.noF; i ++) {
			if (this.followers[i] instanceof Subscriber) {
				((Subscriber) this.followers[i]).recommendedVideo(video);
			}
		}
	}

	public void follow(Follower f) {
		/* Update on the current channel */
		this.followers[this.noF] = f;
		this.noF ++;

		/* Update on the parameter (Follower) 'f'*/
		f.Follow(this);
	}

	public void unfollow(Follower f) {
		/* Update on the current channel */
		int index = -1;
		for (int i = 0; i < this.noF; i ++) {
			if (this.followers[i].name.equals(f.name)) {
				this.followers[i] = null;
				index = i;
			}
		}

		if (index != -1) {
			for (int i = index; i < this.noF; i ++) {
				if (i < this.noF - 1) {
					this.followers[i] = this.followers[i + 1];
				}
				else {
					this.followers[i] = null;
				}
			}	
			this.noF -= 1;
		}

		/* Update on the parameter (Follower) 'f'*/
		f.unFollow(this);
	}

	public String toString() {
		String result = "";

		String listOfVideos = "<";
		for (int i = 0; i < this.noV; i ++) {
			listOfVideos += this.releasedVideos[i];
			if (i < this.noV - 1) {
				listOfVideos += ", ";
			}
		}
		listOfVideos += ">";

		String listOfFollowers = "[";
		for (int i = 0; i < this.noF; i ++) {
			if (this.followers[i] instanceof Subscriber) {
				listOfFollowers += "Subscriber " + this.followers[i].getName();
			}
			else if ( this.followers[i] instanceof Monitor) {
				listOfFollowers += "Monitor " + this.followers[i].getName();
			}
			if (i < this.noF - 1) {
				listOfFollowers += ", ";
			}
		}
		listOfFollowers += "]";

		if (this.noV == 0 && this.noF == 0) {
			result = this.name + " released no videos and has no followers.";
		}
		else if (this.noV != 0 && this.noF == 0) {
			result = this.name + " released " + listOfVideos + " and has no followers.";
		}
		else if (this.noV == 0 && this.noF != 0) {
			result = this.name + " released no videos and is followed by " + listOfFollowers + ".";
		}
		else if (this.noV != 0 && this.noF != 0) {
			result = this.name + 
					" released " + listOfVideos + " and is followed by " + listOfFollowers + ".";
		}

		return result;
	}

	public String getChannelName() {
		return this.name;
	}
	
	public String[] getReleasedVideos() {
		return this.releasedVideos;
	}
	
	public int getNoV() {
		return this.noV;
	}
	
	public int getNoF() {
		return this.noF;
	}
	
	public void addWatchTime(int watchTime) {
		for (int i = 0; i < this.noF; i ++) {
			if (this.followers[i] instanceof Monitor) {
				((Monitor) this.followers[i]).updateStat(this.name, watchTime);
			}
		}
	}
}