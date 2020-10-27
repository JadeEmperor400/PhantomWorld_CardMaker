/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardmakerppw;
import javax.swing.JFrame;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.lang.NullPointerException;
import java.net.URL;
import java.awt.Graphics;
import java.lang.ClassLoader;
import java.lang.Class;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import java.awt.Desktop;
import cardmakerppw.FileManagement.FileManager;
import cardmakerppw.Alliance;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.Canvas;
import java.awt.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;
import java.awt.BasicStroke;
import java.awt.Color;
/**
 *
 * @author croni
 */
public class CardmakerPPW {

    /**
     * @param args the command line arguments
     */
    //Dimensions
        //Card Size
    private static final int cardWidth = 1500;
    private static final int cardHeight = 2100;
        //Size of normal Art
    private static final int artWidth = 1440;
    private static final int artHeight = 900; 
    private static final int e_Ht = 16;// Base height of GUI element
    
    
    //Static Refs
    private static Card card = new Card();
    private static Action action1 = new Action();
    private static Action action2 = new Action();
    private static BufferedImage buffImg = new BufferedImage( 1500, 2100, BufferedImage.TYPE_INT_ARGB);
    private static ImageIcon cardArt = null;
    //Frame Elements
    static JFrame f;
    
    //Textfields
        //General
    static JTextField title, cost, att1, att2, flavor;
        //Fighter
    static JTextField name;
        //Actions
    static JTextField actName1, actCost1;
    static JTextField actName2, actCost2;

    //Text Areas
    static JTextArea effect, actEffect1, actEffect2, condition;
    
    //Dropdown menus
        //Alliance
    private static final Alliance[] allyChoice = {Alliance.Free , Alliance.GrandSanctuary, Alliance.NightStrider, Alliance.MysticGate, Alliance.ForbiddenAngle };
    private static final JComboBox<Alliance> allyBox = new JComboBox<Alliance>(allyChoice);
        //Type
    private static final Type[] typeChoice = {Type.SUPPORT, Type.FIGHTER, Type.ITEM, Type.CLIMAX};
    private static final JComboBox<Type> typeBox = new JComboBox<Type>(typeChoice);
        //Striks
    private static final String[] stk = {"Dual","Tri","Quad", "Penta", "Hex"};
    
    
    //Toggles
    static JCheckBox fullArt = new JCheckBox("Full Art", false);
    static JCheckBox isAttack1 = new JCheckBox("Action 1 is Attack",false);
    static JCheckBox isAttack2 = new JCheckBox("Action 2 is Attack",false);
    static JCheckBox TagTeam = new JCheckBox("TagTeam", false);

    //Spinners
    static JSpinner actDmg1 = new JSpinner(new SpinnerNumberModel(60, Action.DamageMin, Action.DamageMax, 10));
    static JSpinner actDmg2 = new JSpinner(new SpinnerNumberModel(60, Action.DamageMin, Action.DamageMax, 10));
    static JSpinner level = new JSpinner(new SpinnerNumberModel(1, 1, 7, 1));
    static JSpinner hp = new JSpinner(new SpinnerNumberModel(100, 10, 500, 10));
    static JSpinner strike = new JSpinner(new SpinnerNumberModel(1, 1, 6, 1));
    static JSpinner armor = new JSpinner(new SpinnerNumberModel(0, 0, 60, 10));
    static JSpinner counter = new JSpinner(new SpinnerNumberModel(0, 0, 60, 10));
    static JSpinner ward = new JSpinner(new SpinnerNumberModel(0, 0, 60, 10));
    static JSpinner bane = new JSpinner(new SpinnerNumberModel(0, 0, 60, 10));
    
    //Menu
    static JMenu menu;
    static JMenuItem save, export, load;
    static JMenuBar bar;
    
    //Button
    static JButton preview , artChange;
    
    //Image Label
    static JLabel cardPreview = new JLabel();
    static JPanel actPanel;
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        //TESTS
        
        Action a1 = new Action("Brutal Strike", 40, null, null,false);
        Action a2 = new Action("Meditate", "Discard a card", "Draw 2 cards", false);
        ImageIcon img = null;
        
        action1 = a1;
        action2 = a2;
        
        Card c = new Card("Test Card", Type.FIGHTER, Alliance.NightStrider,
                "WarBeast", "Moon", null ,"Skill: Goat: Discard a card. If you do, draw a card",
                "Hello Boys", false, 2, action1, action2, 120, 0, 0, 30, 0, 1, img , "Test", null, false);
        
        card = c;
        getFrame();
        
        drawFrame();
        
        /*
        System.out.println(c.toString());
        FileManager.SaveCard(c);
        
        /*
        Card lc = FileManager.LoadCard();
        if(lc != null){
            System.out.println(lc.toString());
            
            if(lc.getArt() != null){
                JOptionPane.showConfirmDialog(null, lc.toString(), "This your Card?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, lc.getArt());
            } else {
                JOptionPane.showConfirmDialog(null, lc.toString() , "This your card? No Art?",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            }
        }*/
        
        
    } //End of main
    
    //Draws the GUI of the App
    private static void drawFrame(){
        //Frame Creation
        if(f == null){
            f = new JFrame("Phantom World Card Maker");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setSize(1440, 920);
            f.setMinimumSize(new Dimension(1440, 920));
            f.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        } else {
            f.getContentPane().removeAll();
            f.getContentPane().repaint();
        }
        
        f.setTitle("Phantom World Card Maker - " + card.getTitle());
        
        boolean fighter = (card.getType() == Type.FIGHTER);

        f.setLayout(new FlowLayout());
        
//CardImage
        //updatePreview();
        cardPreview.setIcon(previewCard());
        f.add(cardPreview);
        
        JPanel p1 = new JPanel();//Panel for the main layout
        p1.setAlignmentX(Component.LEFT_ALIGNMENT);
        BoxLayout mainLayout = new BoxLayout(p1, BoxLayout.Y_AXIS);
        
        p1.setLayout(mainLayout);
        p1.setBounds(900, 0, 700, 900);
        if(bar == null){
            fileMenu();
        }
        
        f.setJMenuBar(bar);
        
        //Data Setting
        title = new JTextField(card.getTitle());
        title.setMinimumSize(new Dimension(320, e_Ht));
        title.setMaximumSize(new Dimension(320, e_Ht));
        
        cost = new JTextField(card.getCost());
        cost.setMinimumSize(new Dimension(400, e_Ht));
        cost.setMaximumSize(new Dimension(400, e_Ht));
        cost.setPreferredSize(new Dimension(400, e_Ht));
        
        att1 = new JTextField(card.getAttribute1());
        att1.setMinimumSize(new Dimension(120, e_Ht));
        att1.setMaximumSize(new Dimension(120, e_Ht));
        att1.setPreferredSize(new Dimension(120, e_Ht));
        att2 = new JTextField(card.getAttribute2());
        att2.setMinimumSize(new Dimension(120, e_Ht));
        att2.setMaximumSize(new Dimension(120, e_Ht));
        att2.setPreferredSize(new Dimension(120, e_Ht));
        
        flavor = new JTextField(card.getFlavor());
        flavor.setMinimumSize(new Dimension(400, e_Ht));
        flavor.setMaximumSize(new Dimension(400, e_Ht));
        flavor.setPreferredSize(new Dimension(400, e_Ht));
        
        effect = new JTextArea(card.getAbility());
        effect.setMinimumSize(new Dimension(300, e_Ht * 3));
        effect.setMaximumSize(new Dimension(300, e_Ht * 3));
        effect.setPreferredSize(new Dimension(300, e_Ht * 3));
        
        condition = new JTextArea(card.getCondition());
        condition.setMinimumSize(new Dimension(320, e_Ht * 2));
        condition.setMaximumSize(new Dimension(320, e_Ht * 2));
        condition.setPreferredSize(new Dimension(320, e_Ht * 2));
        
        name = new JTextField(card.getFighterName());
        name.setMinimumSize(new Dimension(300, e_Ht));
        name.setMaximumSize(new Dimension(300, e_Ht));
        name.setPreferredSize(new Dimension(300, e_Ht));
        
        //Act 1
        actName1 = new JTextField(card.getAction1().getName());
        actName1.setMinimumSize(new Dimension(124, e_Ht));
        actName1.setMaximumSize(new Dimension(124, e_Ht));
        actName1.setPreferredSize(new Dimension(124, e_Ht));
        
        actEffect1 = new JTextArea(card.getAction1().getEffect());
        actEffect1.setMinimumSize(new Dimension(360, e_Ht * 3));
        actEffect1.setMaximumSize(new Dimension(360, e_Ht * 3));
        actEffect1.setPreferredSize(new Dimension(360, e_Ht * 3));
        
        actCost1 = new JTextField(card.getAction1().getCost());
        actCost1.setMinimumSize(new Dimension(240, e_Ht));
        actCost1.setMaximumSize(new Dimension(240, e_Ht));
        actCost1.setPreferredSize(new Dimension(240, e_Ht));
        
        //Act2
        actName2 = new JTextField(card.getAction2().getName());
        actName2.setMinimumSize(new Dimension(124, e_Ht));
        actName2.setMaximumSize(new Dimension(124, e_Ht));
        actName2.setPreferredSize(new Dimension(124, e_Ht));
        
        actEffect2 = new JTextArea(card.getAction2().getEffect());
        actEffect2.setMinimumSize(new Dimension(360, e_Ht * 3));
        actEffect2.setMaximumSize(new Dimension(360, e_Ht * 3));
        actEffect2.setPreferredSize(new Dimension(360, e_Ht * 3));
        
        actCost2 = new JTextField(card.getAction2().getCost());
        actCost2.setMinimumSize(new Dimension(240, e_Ht));
        actCost2.setMaximumSize(new Dimension(240, e_Ht));
        actCost2.setPreferredSize(new Dimension(240, e_Ht));
        
        //Set Size of damage scrolls
        actDmg1.setMinimumSize(new Dimension(48, e_Ht));
        actDmg1.setMaximumSize(new Dimension(48, e_Ht));
        actDmg2.setMinimumSize(new Dimension(48, e_Ht));
        actDmg2.setMaximumSize(new Dimension(48, e_Ht));
        
        //Add to Frame
            //Title && Level
        JPanel t_panel = new JPanel();
        //t_panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        t_panel.setLayout(new FlowLayout());
        t_panel.setMinimumSize(new Dimension(700,e_Ht * 2));
        t_panel.setMaximumSize(new Dimension(700,e_Ht * 2));
        JLabel t_label = new JLabel("Title");
        t_label.setMaximumSize( new Dimension (e_Ht * 2, e_Ht));
        t_panel.add(t_label);
        title.setPreferredSize(new Dimension (320 , e_Ht));
        t_panel.add(title);
        //if(fighter){
            JLabel levelLabel = new JLabel("Level");
            levelLabel.setMaximumSize( new Dimension (e_Ht * 2, e_Ht));
            t_panel.add(levelLabel);
            level.setValue(card.getLevel());
            level.setMinimumSize( new Dimension (e_Ht * 2, e_Ht));
            level.setMaximumSize( new Dimension (e_Ht * 2, e_Ht));
            level.setPreferredSize( new Dimension (e_Ht * 2, e_Ht));
            t_panel.add(level);
            level.setVisible(fighter);
            levelLabel.setVisible(fighter);
            
            JLabel hpLabel = new JLabel("HP");
            hpLabel.setMaximumSize( new Dimension (e_Ht * 2, e_Ht));
            t_panel.add(hpLabel);
            hp.setValue(card.getHP());
            hp.setMinimumSize( new Dimension (e_Ht * 3, e_Ht));
            hp.setMaximumSize( new Dimension (e_Ht * 3, e_Ht));
            hp.setPreferredSize( new Dimension (e_Ht * 3, e_Ht));
            t_panel.add(hp);
            hp.setVisible(fighter);
            hpLabel.setVisible(fighter);
        //}
        fullArt.setSelected(card.getFullArt());
        fullArt.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                card.setFullArt(fullArt.isSelected());
                cardPreview.setIcon(previewCard());
            }
        });
        
        t_panel.add(fullArt);
        p1.add(t_panel);
        
            //Type, Name
        JPanel n_panel = new JPanel();
        //n_panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        n_panel.setLayout(new FlowLayout());
        n_panel.setMinimumSize(new Dimension(700,e_Ht * 2));
        n_panel.setMaximumSize(new Dimension(700,e_Ht * 2));
        JLabel type_label = new JLabel("Type");
        type_label.setMaximumSize( new Dimension (e_Ht * 2, e_Ht));
        n_panel.add(type_label);
        
        typeBox.setSelectedItem(card.getType());
        typeBox.setPreferredSize(new Dimension (240 , e_Ht));
        
        n_panel.add(typeBox);
        
            JLabel name_label = new JLabel("Name");
            name_label.setMaximumSize( new Dimension (e_Ht * 2, e_Ht));
            n_panel.add(name_label);
            name.setPreferredSize(new Dimension (240 , e_Ht));
            n_panel.add(name);
            name_label.setVisible(fighter);
            name.setVisible(fighter);
        
        p1.add(n_panel);
        n_panel.setVisible(true);
        
        
        typeBox.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JComboBox jc = (JComboBox)e.getSource();
                card.setType((Type)jc.getSelectedItem());
                boolean fight = (card.getType() == Type.FIGHTER);
                hp.setVisible(fight);
                level.setVisible(fight);
                actPanel.setVisible(fight);
                name_label.setVisible(fight);
                levelLabel.setVisible(fight);
                hpLabel.setVisible(fight);
                cardPreview.setIcon(previewCard());
            }
        });
        
            //Alliance and Attributes
        JPanel a_panel = new JPanel();
        //a_panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        a_panel.setLayout(new FlowLayout());
        a_panel.setMinimumSize(new Dimension(700,e_Ht * 2));
        a_panel.setMaximumSize(new Dimension(700,e_Ht * 2));
        
        JLabel allyLabel = new JLabel("Alliance");
        allyLabel.setMaximumSize(new Dimension (e_Ht * 3, e_Ht));
        a_panel.add(allyLabel);
        allyBox.setSelectedItem(card.getAlliance());
        allyBox.setPreferredSize(new Dimension (124, e_Ht));
        allyBox.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JComboBox jc = (JComboBox)e.getSource();
                card.setAlliance((Alliance)jc.getSelectedItem());
                cardPreview.setIcon(previewCard());
            }
        });
        a_panel.add(allyBox);
        
        JLabel attLabel = new JLabel("Attributes 1st:");
        attLabel.setMaximumSize(new Dimension (e_Ht * 3, e_Ht));
        a_panel.add(attLabel);
        a_panel.add(att1);
        JLabel attLabel2 = new JLabel("2nd:");
        attLabel2.setMaximumSize(new Dimension (e_Ht, e_Ht));
        a_panel.add(attLabel2);
        a_panel.add(att2);
        
        p1.add(a_panel);
        
            //Condition
        JPanel conPan = new JPanel();
        conPan.setLayout(new FlowLayout());
        conPan.setMinimumSize(new Dimension(700,e_Ht * 3));
        conPan.setMaximumSize(new Dimension(700,e_Ht * 3));
        
        JLabel conLbl = new JLabel("Condition");
        conLbl.setMaximumSize(new Dimension (e_Ht * 2, e_Ht));
        conPan.add(conLbl);
        conPan.add(condition);
        
        p1.add(conPan);
        
            //Cost
        JPanel c_panel = new JPanel();
        //c_panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        c_panel.setLayout(new FlowLayout());
        c_panel.setMinimumSize(new Dimension(700,e_Ht * 2));
        c_panel.setMaximumSize(new Dimension(700,e_Ht * 2));
            
        JLabel costLabel = new JLabel("Cost");
        costLabel.setMaximumSize(new Dimension (e_Ht * 2, e_Ht));
        c_panel.add(costLabel);
        c_panel.add(cost);
        p1.add(c_panel);
        
            //Flavor
        JPanel f_panel = new JPanel();
        //f_panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        f_panel.setLayout(new FlowLayout());
        f_panel.setMinimumSize(new Dimension(700,e_Ht * 2));
        f_panel.setMaximumSize(new Dimension(700,e_Ht * 2));
        
        JLabel flavorLabel = new JLabel("Flavor");
        flavorLabel.setMaximumSize(new Dimension (e_Ht * 2, e_Ht));
        f_panel.add(flavorLabel);
        f_panel.add(flavor);
        p1.add(f_panel);
        
            //Ability/Effect
        JPanel e_panel = new JPanel();
        //e_panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        e_panel.setLayout(new BoxLayout(e_panel, BoxLayout.Y_AXIS));
        e_panel.setMinimumSize(new Dimension(700,e_Ht * 5));
        e_panel.setMaximumSize(new Dimension(700,e_Ht * 5));
        
        JLabel effectLabel = new JLabel("Effect / Ability");
        effectLabel.setMaximumSize(new Dimension (e_Ht * 5, e_Ht));
        e_panel.add(effectLabel);
        e_panel.add(effect);
        
        p1.add(e_panel);
        
        //Actions
        //if(fighter){
            actPanel = new JPanel();
            actPanel.setLayout(new BoxLayout(actPanel, BoxLayout.Y_AXIS));
            actPanel.setMinimumSize(new Dimension(700,e_Ht * 20));
            actPanel.setMaximumSize(new Dimension(700,e_Ht * 20));
            
            //Action1
            JLabel actLbl1 = new JLabel("Action1");
            actLbl1.setMaximumSize(new Dimension (e_Ht * 3, e_Ht));
            actPanel.add(actLbl1);
            
            JPanel actP1 = new JPanel();
            actP1.setLayout(new FlowLayout());
            actP1.setMinimumSize(new Dimension(700,e_Ht * 2));
            actP1.setMaximumSize(new Dimension(700,e_Ht * 2));
            actP1.setPreferredSize(new Dimension(700,e_Ht * 2));
            
            JLabel anl1 = new JLabel("Action Name");
            anl1.setMaximumSize(new Dimension (e_Ht * 2, e_Ht));
            actP1.add(anl1);
            
            actP1.add(actName1);
            
            actDmg1.setValue(card.getAction1().getDamage());
            actDmg1.setVisible(action1.IsAttack());
            actP1.add(actDmg1);
              
            isAttack1.setSelected(action1.IsAttack());
            actP1.add(isAttack1);
            isAttack1.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    action1.setIsAttack(isAttack1.isSelected());
                    actDmg1.setVisible(isAttack1.isSelected());
                    cardPreview.setIcon(previewCard());
                }
            });
            
            actPanel.add(actP1);
            
            JPanel costPan1 = new JPanel();
            costPan1.setLayout(new FlowLayout());
            costPan1.setMinimumSize(new Dimension(700,e_Ht * 2));
            costPan1.setMaximumSize(new Dimension(700,e_Ht * 2));
            costPan1.setPreferredSize(new Dimension(700,e_Ht * 2));
            costPan1.add(new JLabel("Act Cost"));
            costPan1.add(actCost1);
            
            actPanel.add(costPan1);
            actPanel.add(new JLabel("Action Effect"));
            actPanel.add(actEffect1);
            
            //Action2
            JLabel actLbl2 = new JLabel("Action2");
            actLbl2.setMaximumSize(new Dimension (e_Ht * 3, e_Ht));
            actPanel.add(actLbl2);
            
            JPanel actP2 = new JPanel();
            actP2.setLayout(new FlowLayout());
            actP2.setMinimumSize(new Dimension(700,e_Ht * 2));
            actP2.setMaximumSize(new Dimension(700,e_Ht * 2));
            actP2.setPreferredSize(new Dimension(700,e_Ht * 2));
            
            JLabel anl2 = new JLabel("Action Name");
            anl2.setMaximumSize(new Dimension (e_Ht * 2, e_Ht));
            actP2.add(anl2);
            
            actP2.add(actName2);
            
            actDmg2.setValue(card.getAction2().getDamage());
            actDmg2.setVisible(action2.IsAttack());
            actP2.add(actDmg2);
                
            isAttack2.setSelected(action2.IsAttack());
            actP2.add(isAttack2);
            isAttack2.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    action2.setIsAttack(isAttack2.isSelected());
                    actDmg2.setVisible(isAttack2.isSelected());
                    cardPreview.setIcon(previewCard());
                }
            });
            
            actPanel.add(actP2);
            
            JPanel costPan2 = new JPanel();
            //costPan2.setAlignmentX(Component.LEFT_ALIGNMENT);
            costPan2.setLayout(new FlowLayout());
            costPan2.setMinimumSize(new Dimension(700,e_Ht * 2));
            costPan2.setMaximumSize(new Dimension(700,e_Ht * 2));
            costPan2.setPreferredSize(new Dimension(700,e_Ht * 2));
            costPan2.add(new JLabel("Act Cost"));
            costPan2.add(actCost2);
            
            actPanel.add(costPan2);
            actPanel.add(new JLabel("Action Effect"));
            actPanel.add(actEffect2);
            
            p1.add(actPanel);
            
                //Key Ability Spinners
            JPanel k_panel = new JPanel();
            //k_panel.setAlignmentX(Component.LEFT_ALIGNMENT);
            k_panel.setLayout(new FlowLayout());
            k_panel.setMinimumSize(new Dimension(700,e_Ht * 2));
            k_panel.setMaximumSize(new Dimension(700,e_Ht * 2));

            k_panel.add(new JLabel("Strikes"));
            strike.setValue(card.getStrikes());
            strike.setPreferredSize(new Dimension(e_Ht * 4, e_Ht));
            k_panel.add(strike);
            
            k_panel.add(new JLabel("Armor"));
            armor.setValue(card.getArmor());
            armor.setPreferredSize(new Dimension(e_Ht * 4, e_Ht));
            k_panel.add(armor);
            
            k_panel.add(new JLabel("Counter"));
            counter.setValue(card.getCounter());
            counter.setPreferredSize(new Dimension(e_Ht * 4, e_Ht));
            k_panel.add(counter);

            k_panel.add(new JLabel("Ward"));
            ward.setValue(card.getWard());
            ward.setPreferredSize(new Dimension(e_Ht * 4, e_Ht));
            k_panel.add(ward);

            k_panel.add(new JLabel("Bane"));
            bane.setValue(card.getBane());
            bane.setPreferredSize(new Dimension(e_Ht * 4, e_Ht));
            k_panel.add(bane);


            actPanel.add(k_panel);
            
            TagTeam.setSelected(card.getTagTeam());
            TagTeam.setPreferredSize(new Dimension(e_Ht * 4, e_Ht));
            TagTeam.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    card.setTagTeam(TagTeam.isSelected());
                    cardPreview.setIcon(previewCard());
                }
            });
            
            actPanel.add(TagTeam);
            actPanel.setVisible(card.getType() == Type.FIGHTER);
        //}//End of Fighter ExclusiveParts

        //Preview and Art Set
        JPanel buttonPan= new JPanel();
        buttonPan.setLayout(new FlowLayout());
        
        
        preview = new JButton("Update Preview");
        preview.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setCard();
                cardPreview.setIcon(previewCard());
                //drawFrame();
            }
        });
        buttonPan.add(preview);
        
        artChange = new JButton("Change Art");
        artChange.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Change Card Art
                setArt();
            }
        });
        buttonPan.add(artChange);
        
        p1.add(buttonPan);
        p1.setVisible(true);
        f.getContentPane().add(p1);
        f.setVisible(true);
    }//End of drawFrame
    
    //File Menu Creates the file operation menu
    private static void fileMenu(){
        menu = new JMenu("File");
        
        save = new JMenuItem("Save Card");
        load = new JMenuItem("Load Card");
        export = new JMenuItem("(TODO)Export Card");
        
        save.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                saveCard();
            }
        });
        load.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                loadCard();
            }
        });
        export.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                exportCard();
            }
        });
        bar = new JMenuBar();
        
        menu.add(save);
        menu.add(load);
        menu.add(export);
        bar.add(menu);
    }//End of FileMenu
    
    //Obtains the frame needed for the card based on type
    private static ImageIcon getFrame(){
        return getFrame(card);
    }
    
    private static ImageIcon getFrame(Card c){
        if(c == null){
            return null;
        }
        
        String path = "cardmakerppw/Frames/";
        
        if(c.getFullArt()){
            path = path + "FullArt/";
        } else {
            path = path + "Basic/";
        }
        
        switch(c.getAlliance()){
            case GrandSanctuary:
                path = path + "GrandSanctuary";
                break;
            case NightStrider:
                path = path + "NightStrider";
                break;
            case MysticGate:
                path = path + "MysticGate";
                break;
            case ForbiddenAngle:
                path = path + "ForbiddenAngle";
                break;
            default:
                path = path + "Free";
                break;
        }
        
        path = path + "_";
        
        switch(c.getType()){
            case FIGHTER:
                path = path + "Fighter";
                break;
            case SUPPORT:
                path = path + "Support";
                break;
            case ITEM:
                path = path + "Item";
                break;
            case CLIMAX:
                path = path + "Climax";
                break;
            default:
                path = path + "Support";
                break;
        }
        
        path = path + ".png";
        
        ImageIcon img = null;
        URL resource = CardmakerPPW.class.getClassLoader().getResource(path);
        System.out.println(resource);
        File file = new File(resource.getFile());
        /*
        try{
            Desktop.getDesktop().open(file);
        } catch(IOException e){
        
        } catch (NullPointerException e){
        
        }*/
        
        //img = new ImageIcon(file.getAbsolutePath());
        /*
        JFrame t = new JFrame("Test");
        t.add(new JLabel(img));
        t.setVisible(true);
        */ // TODO: This works but what is below fails??
        if(resource != null){
            try{
                    if(ImageIO.read(file) != null){
                        img = new ImageIcon(file.getAbsolutePath());
                        System.out.println("Frame Found!! : " + file.getAbsolutePath());
                        return img;
                    }
                }    
            catch (IOException e) {
                System.out.println("Resource Not Found : Linked to\n" + resource.toString());
                return null;
            }
        } else{
            System.out.println("Resource not found");
            return null;
        }
        return img;
    }//End of Getframe
    
    private static ImageIcon makeCardImage(){
        buffImg = new BufferedImage( cardWidth, cardHeight, BufferedImage.TYPE_INT_ARGB);
        
        Graphics g = buffImg.getGraphics();
        
        if(card.getArt() != null){
            Image a = card.getArt().getImage();
            
            int w = artWidth;
            int h = artHeight;
            
            int x = 32;
            int y = 160;
            
            if(card.getFullArt()){
                w = cardWidth;
                h = cardHeight;
                x = 0;
                y = 0;
            }
            
            g.drawImage(a, x, y, w, h, null);
        }
        
        g.drawImage(getFrame().getImage(), 0, 0, cardWidth, cardHeight, null);
        
        if(card.getType() == Type.FIGHTER){
            drawLevel(g);
            drawHP(g);
            drawName(g);
        }
        
        drawInfo(g);
        drawTitle(g);
        drawAttributes(g);
        return new ImageIcon(buffImg);
    }
    
    //Card Image Preview
    private static ImageIcon previewCard(){
        BufferedImage prev = new BufferedImage( 600 , 840, BufferedImage.TYPE_INT_ARGB);
        Graphics g = prev.getGraphics();
        g.drawImage(makeCardImage().getImage(), 0, 0, 600, 840, null);
        return new ImageIcon(prev);
    }
    
    //Changes card variables;
    private static void setCard(){
        /*
        Action a1 = new Action(actName1.getText(), (int)actDmg1.getValue(), actCost1.getText(), actEffect1.getText(),
        isAttack1.isSelected()); 
        
        Action a2 = new Action(actName2.getText(), (int)actDmg2.getValue(), actCost2.getText(), actEffect2.getText(),
        isAttack2.isSelected()); 
        */
        
        action1.setName(actName1.getText());
        action1.setCost(actCost1.getText());
        action1.setDamage((int)actDmg1.getValue());
        action1.setIsAttack(isAttack1.isSelected());
        action1.setEffect(actEffect1.getText());
        
        action2.setName(actName2.getText());
        action2.setCost(actCost2.getText());
        action2.setDamage((int)actDmg2.getValue());
        action2.setIsAttack(isAttack2.isSelected());
        action2.setEffect(actEffect2.getText());
        
        card.setTitle(title.getText());
        card.setType((Type)typeBox.getSelectedItem());
        card.setAlliance((Alliance)allyBox.getSelectedItem());
        card.setFighterName(name.getText());
        card.setAttribute1(att1.getText());
        card.setAttribute2(att2.getText());
        card.setCost(cost.getText());
        card.setAbility(effect.getText());
        card.setFlavor(flavor.getText());
        card.setFullArt(fullArt.isSelected());
        card.setFighterName(name.getText());
        card.setLevel((int)level.getValue());
        card.setHP((int)hp.getValue());
        card.setArmor((int)armor.getValue());
        card.setCounter((int)counter.getValue());
        card.setWard((int)ward.getValue());
        card.setBane((int)bane.getValue());
        card.setStrike((int)strike.getValue());
        card.setCondition(condition.getText());
        card.setTagTeam(TagTeam.isSelected());
        card.setAction1(action1);
        card.setAction2(action2);
        card.setArt(cardArt);
        
        /*
        card = new Card(title.getText(), (Type)typeBox.getSelectedItem(), (Alliance)allyBox.getSelectedItem(),
            att1.getText(), att2.getText(), cost.getText(), effect.getText(), flavor.getText(),
            fullArt.isSelected(), (int)level.getValue(), a1, a2, (int)hp.getValue(), (int)armor.getValue(),
            (int)counter.getValue(), (int)ward.getValue(), (int)bane.getValue(), (int)strike.getValue(),
            cardArt, name.getText())
        */
    }
    
    //Set Art for the card
    private static void setArt(){
        cardArt = FileManager.LoadImage();
        card.setArt(cardArt);
        cardPreview.setIcon(previewCard());
        setCard();
    }
    
    private static void loadCard(){
        Card c = FileManager.LoadCard();
        
        if(c != null){
            card = c;
        }
        
        drawFrame();
    }
    
    private static void saveCard(){
        
        setCard();
        FileManager.SaveCard(card);
    }
    
    //Export card image
    private static void exportCard(){
        FileManager.ExportCardImage(buffImg);
    }
    
    //Draw text on image
    private static void drawTitle(Graphics g){
        g.setFont(new Font("Minion Condensed", Font.BOLD, 100));
        g.setColor(Color.BLACK);
        g.drawString(card.getTitle(), 248 + 2, 120 - 2);
        g.drawString(card.getTitle(), 248 - 2, 120 + 2);
        g.drawString(card.getTitle(), 248 - 2, 120 - 2);
        g.drawString(card.getTitle(), 248 + 2, 120 + 2);
        
        g.setFont(new Font("Minion Condensed", Font.BOLD, 100));
        g.setColor(Color.WHITE);
        g.drawString(card.getTitle(), 248, 120);
    }
    
    private static void drawLevel(Graphics g){
        String words = "";
        words = words + card.getLevel();
        int x = 80;
        int y = 180;
        
        g.setFont(new Font("Minion Condensed", Font.ITALIC, 120));
        g.setColor(Color.BLACK);
        g.drawString(words, x + 2, y - 2);
        g.drawString(words, x - 2, y + 2);
        g.drawString(words, x - 2, y - 2);
        g.drawString(words, x + 2, y + 2);
        
        g.setFont(new Font("Minion Condensed", Font.ITALIC, 120));
        g.setColor(Color.WHITE);
        g.drawString( words, x, y);
    }
    
    private static void drawHP(Graphics g){
        String words = "";
        words = words + card.getHP();
        int x = 1300;
        int y = 124;
        
        g.setFont(new Font("Minion Condensed", Font.BOLD, 100));
        g.setColor(Color.BLACK);
        g.drawString(words, x + 2, y - 2);
        g.drawString(words, x - 2, y + 2);
        g.drawString(words, x - 2, y - 2);
        g.drawString(words, x + 2, y + 2);
        //Drop Shadow
        g.drawString(words, x + 3, y + 3);
        g.drawString(words, x + 4, y + 4);
        g.drawString(words, x + 5, y + 5);
        g.drawString(words, x + 6, y + 6);
        
        g.setFont(new Font("Minion Condensed", Font.BOLD, 100));
        g.setColor(Color.WHITE);
        g.drawString( words, x, y);
    }
    
    private static void drawAttributes(Graphics g){
        String words =  card.attributesToString();
        
        int x = 1060 ;
        int y = 1946;
        
        if(card.getFullArt()){
            g.setFont(new Font("Minion Condensed", Font.BOLD, 50));
            y+= 28;
            g.setColor(Color.BLACK);
            g.drawString(words, x + 1, y - 1);
            g.drawString(words, x - 1, y + 1);
            g.drawString(words, x - 1, y - 2);
            g.drawString(words, x + 1, y + 1);
            g.setColor(Color.WHITE);
        } else if (card.getAlliance() == Alliance.ForbiddenAngle){
            g.setColor(Color.WHITE);
        } else{
            g.setColor(Color.BLACK);
        }
        
        g.setFont(new Font("Minion Condensed", Font.BOLD, 50));
        
        g.drawString( words, x, y);
    }
    
    private static void drawName(Graphics g){
        String words =  card.getFighterName();
        
        int x = 220 ;
        int y = 1160;
        
        g.setFont(new Font("Minion Condensed", Font.PLAIN, 60));
        
        if(card.getFullArt()){    
            y += 48;
            g.setColor(Color.BLACK);
            g.drawString(words, x + 1, y - 1);
            g.drawString(words, x - 1, y + 1);
            g.drawString(words, x - 1, y - 1);
            g.drawString(words, x + 1, y + 1);
            g.setColor(Color.WHITE);
        } else if (card.getAlliance() == Alliance.ForbiddenAngle){
            g.setColor(Color.WHITE);
        } else{
            g.setColor(Color.BLACK);
        }
        
        g.drawString( words, x, y);
    }
    
    private static void drawFlavor(Graphics g){
    
    }
    
    private static void drawInfo(Graphics g){
        String words = "";
        int x = 170;
        int y = 1200;
        
        g.setFont(new Font("Minion Condensed", Font.PLAIN, 56));
        
        if(!card.getCondition().equals("") && !card.allSpaces(card.getCondition()) && card.getCondition() != null){
            words = words + "-" + card.getCondition() + "\n\n";
        }
        
        if(!card.getCost().equals("") && !card.allSpaces(card.getCost()) && card.getCost() != null){
            words = words + "[Cost][" + card.getCost() + "]\n\n";
        }
        
        if(!card.getAbility().equals("") && !card.allSpaces(card.getAbility()) && card.getAbility() != null){
            words = words + card.getAbility() + "\n\n";
        }
        
        if(card.getType() == Type.FIGHTER){
            if(card.getAction1() != null){
                words = words + "-> " + action1.getName();
                
                if (action1.IsAttack()){
                    words = words + "     ";
                    words = words + action1.getDamage();
                }
                
                words = words + "\n";
                
                if(!action1.getCost().equals("") && !action1.allSpaces(action1.getCost()) && action1.getCost() != null){
                    words = words + "[Act Cost][" + action1.getCost() + "]\n";
                }
                
                if(!action1.getEffect().equals("") && !action1.allSpaces(action1.getEffect()) && action1.getEffect() != null){
                    words = words + action1.getEffect();
                }
                
                words = words + "\n\n";
            }
            
            if(card.getAction2() != null){
                words = words + "-> " + action2.getName();
                if (action2.IsAttack()){
                    words = words + "     ";
                    words = words + action2.getDamage();
                }
                
                words = words + "\n";
                
                if(!action2.getCost().equals("") && !action2.allSpaces(action2.getCost()) && action2.getCost() != null){
                    words = words + "[Act Cost][" + action2.getCost() + "]\n";
                }
                
                if(!action2.getEffect().equals("") && !action2.allSpaces(action2.getEffect()) && action2.getEffect() != null){
                    words = words + action2.getEffect();
                }
                
                words = words + "\n\n";
            }
            
            if( card.getArmor() > 0){
                words = words + "[Armor " + card.getArmor() + "] ";
            }
            if( card.getCounter() > 0){
                words = words + "[Counter " + card.getCounter() + "] ";
            }
            if( card.getWard() > 0){
                words = words + "[Ward " + card.getWard() + "] ";
            }
            if( card.getBane() > 0){
                words = words + "[Bane " + card.getBane() + "] ";
            }
            if( card.getStrikes() > 1 && card.getStrikes() < 7 ){
                words = words + "[" + stk[card.getStrikes() - 2] +" Strike] ";
            }
            if(card.getTagTeam()){
                words = words + "[Tag Team]";
            }
        }
        

        if(card.getFullArt()){
            g.setColor(Color.BLACK);
            drawString(g , words, x + 1, y - 1);
            drawString(g ,words, x - 1, y + 1);
            drawString(g ,words, x - 1, y - 2);
            drawString(g ,words, x + 1, y + 1);
            g.setColor(Color.WHITE);
        } else if (card.getAlliance() == Alliance.ForbiddenAngle){
            g.setColor(Color.WHITE);
        } else{
            g.setColor(Color.BLACK);
        }
        
        drawString(g , words, x , y );
    }
    
    //Split Newlines
    static void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
        g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }//End draw String
}//End of Class
