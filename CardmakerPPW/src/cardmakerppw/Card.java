/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardmakerppw;

import java.util.List;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
/**
 *
 * @author croni
 */
public class Card implements java.io.Serializable {
    
    public final static String EXTENSION = "ppc"; //Extension for card file
    
    private String title = "No_Title";
    private Type type = Type.FIGHTER;
    private Alliance ally = Alliance.Free;
    private String cost = "";
    private String att1 = ""; //1st attribute
    private String att2 = ""; //2nd attribute
    private String condition = "";
    private ImageIcon art;
    private String ability = ""; //Use for the effects of suypports/items/climaxes
    private String flavor = "";
    private boolean fullArt = false;
    
//fighter exclusive
    private int level = 1;
    private String name = "Nameless";
    private Action act1;//1st action
    private Action act2; //2nd action
    private int hp = 100;
    private int armor = 0;
    private int counter = 0;
    private int ward = 0;
    private int bane = 0;
    private int strike = 1;
    private boolean tagTeam = false;
    
    //@Constructor
    public Card(){
        title = "Insert Title Here";
        type = Type.FIGHTER;
        ally = Alliance.Free;
    }
    
    public Card(String title, Type type, Alliance alliance, String attribute1,
            String attribute2, String cost, String ability, String flavor, boolean fullArt,
            ImageIcon art, String condition, boolean tagTeam){
        
        if(title != null){
        this.title = new String (title.toCharArray());
        }
        
        this.type = type;
        this.ally = alliance;
        
        if(att1 != null){
        this.att1 = new String(attribute1.toCharArray());
        }
        
        if(att2 != null){
        this.att2 = new String(attribute2.toCharArray());
        }
        
        if(cost != null){
            this.cost = new String(cost.toCharArray());
        }
        
        if(ability != null){
        this.ability = new String(ability.toCharArray());
        }
        
        this.fullArt = fullArt;
        
        if(art != null){
            this.art = art;
        }
        
        if(condition != null){
            this.condition = condition;
        }
        
        this.tagTeam = tagTeam;
    }
    
    public Card(String title, Type type, Alliance alliance, String attribute1,
            String attribute2, String cost , String ability, String flavor, boolean fullArt,
            int level, Action action1, Action action2, int hp , int armor, int counter,
            int ward, int bane, int strike, ImageIcon art, String name, String condition, boolean tagTeam){
    
        this(title, type, alliance, attribute1, attribute2, cost, ability,
                flavor, fullArt, art, condition, tagTeam);
        
        if(hp < 10){
            hp = 10;
        }
        
        this.hp = hp;
        
        if(level < 1){
            level = 1;
        }
        
        this.level = level;
        this.act1 = action1;
        this.act2 = action2;
        
        if(armor < 0){
            armor = 0;
        }
        
        this.armor = armor;
        
        if(counter < 0){
            counter = 0;
        }
        
        this.counter = counter;
        
        if(ward < 0){
            ward = 0;
        }
        
        this.ward = ward;
        
        if( bane < 0){
            bane = 0;
        }
        
        this.bane = bane;
        
        if(strike < 1){
            strike = 1;
        }
        
        this.strike = strike;
        
        if(name != null){
        this.name = new String (name.toCharArray());
        }
    }
    
    //@Accessor
    public String getTitle(){
        return title;
    }
    
    public Type getType(){
        return type;
    }
    
    public Alliance getAlliance(){
        return ally;
    }
    
    public String getAttribute1(){
        return new String(att1.toCharArray());
    }
    
    public String getAttribute2(){
        return new String(att2.toCharArray());
    }
    
    public String getCondition(){
        return new String ( condition.toCharArray());
    }
    
    public String getCost(){
        return new String (cost.toCharArray());
    }
    
    public String getAbility(){
        return new String (ability.toCharArray());
    }
    
    public String getFlavor(){
        return new String (flavor.toCharArray());
    }
    
    public boolean getFullArt(){
        return fullArt;
    }
    
    public ImageIcon getArt(){
        return art;
    }
    
    public String getFighterName(){
        return new String(name.toCharArray());
    }
    
    public int getLevel(){
        return level;
    }
    
    public int getHP(){
        return hp;
    }
    
    public int getArmor(){
        return armor;
    }
    
    public int getCounter(){
        return counter;
    }
    
    public int getWard(){
        return ward;
    }
    
    public int getBane(){
        return bane;
    }
    
    public int getStrikes(){
        return strike;
    }
    
    public Action getAction1(){
        return act1;
    }
    
    public Action getAction2(){
        return act2;
    }
    
    public boolean getTagTeam(){
        return tagTeam;
    }
    
    //@Mutators
    public void setTitle(String title){
        if(title == null){
            return;
        }
        
        this.title = new String(title.toCharArray());
    }
    
    public void setType(Type type){
        this.type = type;
    }
    
    public void setAlliance(Alliance alliance){
        this.ally = alliance;
    }
    
    public void setCondition(String condition){
        if(condition == null || condition.equals("") || allSpaces(condition)){
            return;
        }
        
        this.condition = new String( condition.toCharArray());
    }
    
    public void setCost(String cost){
        if(cost == null){
            return;
        }
        
        this.cost = new String (cost.toCharArray());
    }
    
    public void setAttribute1( String attribute){
        if(attribute == null){
            return;
        }
        this.att1 = new String(attribute.toCharArray());
    }
    
    public void setAttribute2( String attribute){
        if(attribute == null){
            return;
        }
        this.att2 = new String( attribute.toCharArray());
    }
    
    public void setArt(ImageIcon art){
        this.art = art;
    }
    
    public void setFullArt(boolean fullArt){
        this.fullArt = fullArt;
    }
    
    public void setAbility(String ability){
        if(ability == null){
            return;
        }
        this.ability = ability;
    }
    
    public void setFlavor(String flavor){
        if(flavor == null){
            return;
        }
        this.flavor = new String( flavor.toCharArray());
    }
    
    public void setFighterName(String name){
        if(name == null){
            return;
        }
        this.name = name;
    }
    
    public void setLevel(int level){
        if( level < 1 ){
            level = 1;
        }
        
        this.level = level;
    }
    
    public void setAction1(Action action){
        act1 = action;
    }
    
    public void setAction2(Action action){
        act2 = action;
    }
    
    public void setHP(int hp){
        if (hp < 10){
            hp = 10;
        }
        
        this.hp = hp;
    }
    
    public void setArmor(int armor){
        if(armor < 0){
            armor = 0;
        }
        
        this.armor = armor;
    }
    
    public void setCounter(int counter){
        if(counter < 0){
            counter = 0;
        }
        
        this.counter = counter;
    }
    
    public void setWard(int ward){
        if( ward < 0){
            ward = 0;
        }
        
        this.ward = ward;
    }
    
    public void setBane(int bane){
        if( bane < 0){
            bane = 0;
        }
        
        this.bane = bane;
    }
    
    public void setStrike(int strike){
        if(strike < 1){
            strike = 1;
        }
        
        this.strike = strike;
    }
    
    public void setTagTeam(boolean tagTeam){
        this.tagTeam = tagTeam;
    }
    
    //@Methods
    public String toString(){
        String str = "";
        
        if(title != null && !title.equals("") && !allSpaces(title)){
            str = title;
        }
        
        if(fullArt){
            str = str + "_FA";
        }
        
        if(type == Type.FIGHTER){
            str = str + " - LV: " + level + " HP: " + hp + "\nName : " + name;
        } else{
            str = str + " - " + type;
        }
        
        
        str = str + "\n< " + ally + " >";
        
        if(( att1!= null && !att1.equals("") && !allSpaces(att1)) || ( att2!= null && !att2.equals("") && !allSpaces(att2)) ){
            if((att1!= null && !att1.equals("") && !allSpaces(att1)) && ( att2!= null && !att2.equals("") && !allSpaces(att2)) ){
                str = str + " « " + att1 + " / " + att2 + " »";
            } else if ( att1!= null && !att1.equals("") && !allSpaces(att1)){
                str = str + " « " + att1 + " »";
            } else{
                str = str + " « " + att2 + " »";
            }
        }
        
        if(flavor != null && !flavor.equals("") && !allSpaces(flavor)){
            str = str + "\n\"" + flavor + "\"";
        }
        
        if(cost != null && !cost.equals("") && !allSpaces(cost)){
            str = str + "\n[Cost][" + cost + "]";
        }
        
        if(ability != null && !ability.equals("") && !allSpaces(ability)){
            str = str + "\n\n" + ability + "\n";
        }
        
        if(type == Type.FIGHTER){
            if(act1 != null){
                str = str + "\n" + act1.toString() + "\n";
            }

            if(act2 != null){
                str = str + "\n" + act2.toString() + "\n";
            }
            
            str = str + "\n[Armor " + armor + "]-[Counter " + counter 
                    + "]-[Ward " + ward + "]-[Baneful " + bane + "]-[Strike " 
                    + strike + "]" ;
        }
        
        if(art != null){
            str = str + "\n\nArt :: " + art.toString();
        }
        
        return str;
    }
    
    public String attributesToString(){
        String str = "";
        
        if(( att1!= null && !att1.equals("") && !allSpaces(att1)) || ( att2!= null && !att2.equals("") && !allSpaces(att2)) ){
            if((att1!= null && !att1.equals("") && !allSpaces(att1)) && ( att2!= null && !att2.equals("") && !allSpaces(att2)) ){
                str = str + "«" + att1 + " / " + att2 + "»";
            } else if ( att1!= null && !att1.equals("") && !allSpaces(att1)){
                str = str + "«" + att1 + "»";
            } else{
                str = str + "«" + att2 + "»";
            }
        } else { return "";}
        
        return str;
    }
    
    public boolean allSpaces(String str){
        for(char c : str.toCharArray()){
            if(c != ' '){
                return false;
            }
        }
        return true;
    }
}