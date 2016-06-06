package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


import shared.*;

public class GameParametersPane extends JPanel
			 implements ActionListener,Constants,
				    FocusListener {
    
    protected static final String stop = 
    		"Stop Game";
    
    protected static final String nameString = 
    		"Name of the Game:";   
    protected static final String stakeString = 
    		"Stake:";    
    protected static final String delayString = 
    		"Pause between balls (in seconds):";
    
    protected static final String countDownString = 
    		"Countdown (in seconds):";
    
    protected static final String maxPlayersString = 
    		"Maximum number of players:";
    
    protected static final String maxCardsString = 
    		"Maximum number of cards per player:";
    
    protected static final String hostLabelString = 
    		"This server's hostname:";

    // PENDING: should these number things be sliders to reduce risk of typos?
    protected JLabel delayField;
    protected JLabel countDownField;
    protected JLabel nameField;
    protected JLabel stakeField;
    protected JLabel maxPlayersField;
    protected JLabel maxCardsField;

    protected JButton stopButton;

	private Game settings;
    
    public GameParametersPane(Game game) {
		// TODO Auto-generated constructor stub
	super(false);

	this.settings=game;

	    // create the properties fields
	
    JLabel nameLabel = new JLabel(nameString, JLabel.LEFT);
    JLabel nameField = new JLabel(settings.getGameName(),JLabel.LEFT);
    
    
    JLabel stakeLabel = new JLabel(stakeString, JLabel.LEFT);
    JLabel stakeField = new JLabel(new Double(settings.getStake()).toString(),JLabel.LEFT);
 
    JLabel delayLabel = new JLabel(delayString, JLabel.LEFT);
    JLabel delayField = new JLabel(new Long(settings.getBallDelay()/Constants.ONE_SECOND).toString(),JLabel.LEFT);
     

    JLabel countDownLabel = new JLabel(countDownString, JLabel.LEFT);
    JLabel countDownField = new JLabel(new Long(settings.getCountDown()/Constants.ONE_SECOND).toString());


    JLabel maxPlayersLabel = new JLabel(maxPlayersString, JLabel.LEFT);
    JLabel maxPlayersField = new JLabel(new Integer(settings.getMaxPlayers()).toString(), JLabel.LEFT);


    JLabel maxCardsLabel = new JLabel(maxCardsString, JLabel.LEFT);
    JLabel maxCardsField = new JLabel(new Integer(settings.getMaxCards()).toString(), JLabel.LEFT);

	    // create the go and stop buttons



        stopButton = new JButton(stop);
	stopButton.setMnemonic('s');
	stopButton.setActionCommand(stop);
	stopButton.setEnabled(false);

            // Register the listeners
	stopButton.addActionListener(this);

	    // Do the layout.
	JPanel parameterPane = new JPanel(false);
	parameterPane.setBorder(BorderFactory.createTitledBorder(
				  BackOffice.controlPaneTitle));
	GridBagLayout gridbag = new GridBagLayout();
	parameterPane.setLayout(gridbag);

	    // many rows
	Utilities.addParameterRow(parameterPane,
			  nameLabel,
			  nameField);
	Utilities.addParameterRow(parameterPane,
			  stakeLabel,
			  stakeField);
	Utilities.addParameterRow(parameterPane,
				  delayLabel,
				  delayField);
	Utilities.addParameterRow(parameterPane,
				  countDownLabel,
				  countDownField);
	Utilities.addParameterRow(parameterPane,
				  maxPlayersLabel,
				  maxPlayersField);
	Utilities.addParameterRow(parameterPane,
				  maxCardsLabel,
				  maxCardsField);


	JComponent[] compList = new JComponent[1];
	compList[0] = stopButton;
	
	Box buttonBox = Utilities.makeEvenlySpacedBox(compList);

	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	add(parameterPane);
	add(Box.createRigidArea(new Dimension(20, 20)));
	add(buttonBox);
	add(Box.createRigidArea(new Dimension(20, 20)));
    }





	public void focusLost(FocusEvent e) {
	//when a field loses the focus, generate an action event
	JTextField source;
	ActionEvent event;

	source = (JTextField)(e.getComponent());
	source.setBackground(Color.WHITE);
	source.postActionEvent();
	//event = new ActionEvent(source,
				//ActionEvent.ACTION_PERFORMED,
				//source.getCommand());
	//actionPerformed(new 
	//ACK!  There's no JTextField getActionCommand or performAction!
	//is postActionEvent the same as the latter?
    }

    public void focusGained(FocusEvent e) {
    	JTextField source;
    	ActionEvent event;
    	System.out.println("Hey");
    	source = (JTextField)(e.getComponent());
    	source.setBackground(Color.YELLOW);
    	
    }
 
    public void actionPerformed(ActionEvent e) {
    	     	
    }
    
    public Dimension getMaximumSize() {
	Dimension d = getPreferredSize();
	d.width = Short.MAX_VALUE;
	return d;
    }


}
