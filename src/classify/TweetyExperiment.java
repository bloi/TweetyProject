package classify;

public class TweetyExperiment {

	private Integer currentTestIndex; 
	
	public TweetyExperiment() {
		currentTestIndex = 0; 
	}

	public void run () {
		for(int i=0; i<10; i++){
			currentTestIndex = i;
			
			
		}
	}
	
	public Integer getCurrentTestIndex() {
		return currentTestIndex;
	}

	public void setCurrentTestIndex(Integer currentTestIndex) {
		this.currentTestIndex = currentTestIndex;
	}
	
}
