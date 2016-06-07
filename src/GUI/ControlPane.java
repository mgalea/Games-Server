package GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


import shared.*;
import game.*;

public class ControlPane extends JPanel
			 implements ActionListener,Constants,
				    FocusListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 9060708327884392826L;

	protected static final String go = 
    		"Start a Bingo Game";
    
    protected static final String stop = 
    		"No More New Games";
    
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
    
    protected static final String activeLabelString = 
    		"Number of Active Games:";

    // PENDING: should these number things be sliders to reduce risk of typos?
    protected JTextField delayField;
    protected JTextField countDownField;
    protected JTextField nameField;
    protected JTextField stakeField;
    protected JTextField maxPlayersField;
    protected JTextField maxCardsField;
    

    protected JButton goButton;
    protected JButton stopButton;
    protected JButton noMoreButton;
    
      
    GameSettings settings=new GameSettings();
	
	public ControlPane(String hostname, GameSettings gameSettings) {
	super(false);
	
	this.settings=gameSettings;
	
	    // create the properties fields
	
    JLabel nameLabel = new JLabel(nameString, JLabel.LEFT);
    nameField = new JTextField(settings.getGameName());
    nameField.setActionCommand(nameString);
    
    JLabel stakeLabel = new JLabel(stakeString, JLabel.LEFT);
    stakeField = new JTextField(new Double(settings.getStake()).toString());
    stakeField.setActionCommand(stakeString);

    JLabel delayLabel = new JLabel(delayString, JLabel.LEFT);
    delayField = new JTextField(new Long(settings.getBallDelay()/Constants.ONE_SECOND).toString());
    delayField.setActionCommand(delayString);
    

        JLabel countDownLabel = new JLabel(countDownString, JLabel.LEFT);
        countDownField = new JTextField(new 
	    Long(settings.getCountDown()/Constants.ONE_SECOND).toString());
        countDownField.setActionCommand(countDownString);

        JLabel maxPlayersLabel = new JLabel(maxPlayersString, JLabel.LEFT);
        maxPlayersField = new JTextField(new
 	    Integer(settings.getMaxGamePlayers()).toString());
        maxPlayersField.setActionCommand(maxPlayersString);

        JLabel maxCardsLabel = new JLabel(maxCardsString, JLabel.LEFT);
        maxCardsField = new JTextField(new
	    Integer(settings.getMaxCards()).toString());
        maxCardsField.setActionCommand(maxCardsString);

        JLabel hostLabel = new JLabel(hostLabelString, JLabel.LEFT);
        JLabel hostNameLabel = new JLabel(hostname);
        
        JLabel activeLabel = new JLabel(activeLabelString, JLabel.LEFT);
        JLabel activeGamesNameLabel = new JLabel(Integer.toString(Start.getRunningGames()));

	    // create the go and stop buttons
        goButton = new JButton(go);
        goButton.setMnemonic('g');
        goButton.setActionCommand(go);
	
        noMoreButton = new JButton(go);
		noMoreButton.setMnemonic('m');
		noMoreButton.setActionCommand(go);


        stopButton = new JButton(stop);
	stopButton.setMnemonic('s');
	stopButton.setActionCommand(stop);
	stopButton.setEnabled(false);

            // Register the listeners
        nameField.addActionListener(this);
        nameField.addFocusListener(this);
        
        stakeField.addActionListener(this);
        stakeField.addFocusListener(this);
        
        delayField.addActionListener(this);
        delayField.addFocusListener(this);
        
        countDownField.addActionListener(this);
        countDownField.addFocusListener(this);
        maxPlayersField.addActionListener(this);
        maxPlayersField.addFocusListener(this);
        maxCardsField.addActionListener(this);
        maxCardsField.addFocusListener(this);

	goButton.addActionListener(this);
	stopButton.addActionListener(this);

	    // Do the layout.
	JPanel parameterPane = new JPanel(false);
	parameterPane.setBorder(BorderFactory.createTitledBorder(
				  BackOffice.controlPaneTitle));
	parameterPane.setBackground(Color.GRAY);
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
	Utilities.addParameterRow(parameterPane,
				  hostLabel,
				  hostNameLabel);
	Utilities.addParameterRow(parameterPane,
			  activeLabel,
			activeGamesNameLabel);

	JComponent[] compList = new JComponent[2];
	compList[0] = goButton;
	compList[1] = stopButton;
	
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
    	System.out.println("Hey");
    	source = (JTextField)(e.getComponent());
    	source.setBackground(Color.YELLOW);
    	
    }
 
    public void actionPerformed(ActionEvent e) {
    	
        switch (e.getActionCommand()){
        
        case go:			
				bingo bingoGame = new bingo (GAME_TYPE.BINGO90,settings);	  
				bingoGame.run();
    	        stopButton.setEnabled(true);
    	    
        	break;
        	
        case stop:
    	        Start.SetNoMoreGames();
    	        stopButton.setEnabled(false);
    	        goButton.setEnabled(false);
        	break;
        	
        case nameString:
    	    settings.setGameName(nameField.getText());    	    
    	    break;
    	    
        case stakeString:
        	settings.setStake(Double.parseDouble(stakeField.getText()));   	    
    	    break;
 
        	
        case delayString:
    	    settings.setDelay((Long.parseLong(delayField.getText()))*Constants.ONE_SECOND);    	    
    	    break;
    	    
        case countDownString:
        	settings.setCountDown((Long.parseLong(countDownField.getText()))*Constants.ONE_SECOND);   	    
    	    break;
    	    
        case maxPlayersString:
        	settings.setMaxGamePlayers(Integer.parseInt(maxPlayersField.getText()));   	    
    	    break;
    	    
        case maxCardsString:
        	settings.setMaxCards(Integer.parseInt(maxCardsField.getText()));    	    
    	    break;
    	    
    	   default:
    		   break;        	
        }       	            	
    }
    
    public Dimension getMaximumSize() {
	Dimension d = getPreferredSize();
	d.width = Short.MAX_VALUE;
	return d;
    }



}
