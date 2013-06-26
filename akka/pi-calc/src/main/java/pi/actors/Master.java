package pi.actors;

import pi.domain.Calculate;
import pi.domain.PiApproxiamtion;
import pi.domain.Result;
import pi.domain.Work;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinRouter;

public class Master extends UntypedActor {
	  private final int nrOfMessages;
	  private final int nrOfElements;

	  private double pi;
	  private int nrOfResults;
	  private final long start = System.currentTimeMillis();

	  private final ActorRef listener;
	  private final ActorRef workerRouter;

	  public Master(
	    final int nrOfWorkers, 
	    int nrOfMessages, 
	    int nrOfElements, 
	    ActorRef listener) {
	    
	    this.nrOfMessages = nrOfMessages;
	    this.nrOfElements = nrOfElements;
	    this.listener = listener;

	    workerRouter = this.getContext().actorOf(new Props(WorkerActor.class).withRouter(
	      new RoundRobinRouter(nrOfWorkers)), "workerRouter");
	  }

	  public void onReceive(Object message) {
		  if (message instanceof Calculate) {
			    for (int start = 0; start < nrOfMessages; start++) {
			      workerRouter.tell(new Work(start, nrOfElements), getSelf());
			    }
			  } else if (message instanceof Result) {
			    Result result = (Result) message;
			    pi += result.getResult();
			    nrOfResults += 1;
			    if (nrOfResults == nrOfMessages) {
			      // Send the result to the listener
			      long duration =System.currentTimeMillis() - start;
			      listener.tell(new PiApproxiamtion(pi, duration), getSelf());
			      // Stops this actor and all its supervised children
			      getContext().stop(getSelf());
			    }
			  } else {
			    unhandled(message);
			  }
	  }
	}