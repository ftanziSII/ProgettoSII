package twitter;

import java.io.IOException;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;


class StreamTwitter {
	public static void main(String[] args) throws TwitterException, IOException{
		Configuration config = new Configuration();
		TwitterStream twitter = config.getTwitterStream();
	    StatusListener listener = new StatusListener(){
	        public void onStatus(Status status) {
	        	if(status.getGeoLocation()!=null){
	        		System.out.println(status.getUser().getName() + " : " + status.getText());
	        		System.out.println(status.getGeoLocation());
	        	}
	            //System.out.println(status.getUser().getName() + " : " + status.getText());
	        }
	        public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}
	        public void onTrackLimitationNotice(int numberOfLimitedStatuses) {}
	        public void onException(Exception ex) {
	            ex.printStackTrace();
	        }
			public void onScrubGeo(long arg0, long arg1) {			
			}
			public void onStallWarning(StallWarning arg0) {			
			}
	    };
	    //TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
	    twitter.addListener(listener);
	    //FilterQuery q1 = new FilterQuery("#oscar");
	    // sample() method internally creates a thread which manipulates TwitterStream and calls these adequate listener methods continuously.
	    twitter.sample();
	    //twitter.filter("#romainter");
	}

	
}
