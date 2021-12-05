package bookstore;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;
import jade.core.AID;
import jade.core.Agent;
import jade.core.AgentContainer;
import jade.core.behaviours.*;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;

public class PaymentAgent extends Agent{
	public PaymentAgent() {
		 System.out.println("in paymentagent");
	 }  
	protected void setup() 
    {
        addBehaviour(  // -------- Anonymous SimpleBehaviour 

            new SimpleBehaviour( this ) 
            {
            	public void action() 
                {
            		System.out.println( "Hello World! My name is " + myAgent.getLocalName() );
                }
            	public boolean done() {  
               	 try {
					killAgent(myAgent.getLocalName());
				} catch (ControllerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
               	 return true;  
               	 }
            }
        );
    }   //  --- setup ---
    public void killAgent(String name) throws ControllerException {
        AID agentID = new AID(name, AID.ISLOCALNAME);
        jade.wrapper.AgentContainer controller = getContainerController();
        AgentController agent = controller.getAgent(name);
        agent.kill();
        System.out.println("+++ Killed: " + agentID);
       }
    
   public int addpayment(int order_id,int amount,String payment_type) throws SQLException {
	 DBConnect db = new DBConnect();
   	 int payment_id = db.addpayment(order_id,amount,payment_type);
   	 System.out.println(payment_id +"added to payments"); 
   	 return payment_id;
   }
   	
}