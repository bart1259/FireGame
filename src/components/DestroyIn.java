package components;

import coreEngine.Time;

//Componeent that can be added to any entity
public class DestroyIn extends Component{
	
	//The time left until the entity is destoyed
	double timeLeft;
	
	//Constructor takes the amount of time left until the entity gets destoyred
	public DestroyIn(double duration) {
		timeLeft = duration;
	}
	
	@Override
	public void update() {
		//Decrease the time left
		timeLeft -= Time.getDeltaTime();
		
		//If there is not time left destoy the entity
		if(timeLeft <= 0) {
			getEntity().terminate();
		}
	}
	
}
