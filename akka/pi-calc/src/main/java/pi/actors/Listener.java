package pi.actors;

import pi.domain.PiApproxiamtion;
import akka.actor.UntypedActor;

public class Listener extends UntypedActor {
	  public void onReceive(Object message) {
	    if (message instanceof PiApproxiamtion) {
	      PiApproxiamtion approximation = (PiApproxiamtion) message;
	      System.out.println(String.format("\n\tPi approximation: " + 
	        "\t%s\n\tCalculation time: \t%s millSec",
	          approximation.getPiApprox(), approximation.getDuration()));
	      getContext().system().shutdown();
	    } else {
	      unhandled(message);
	    }
	  }
	}