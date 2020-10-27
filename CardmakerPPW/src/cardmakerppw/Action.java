/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardmakerppw;

/**
 *
 * @author croni
 */
public class Action implements java.io.Serializable {
    
    public static final int DamageMin = 0;
    public static final int DamageMax = 300;
    
    private String actionName = "No_Name Action";
    private boolean isAtk = false;
    private int dmg = 60; //attack's base damage
    private String cost = "";
    private String effect = "";

//@Constructors    
    public Action(){
        this.actionName = "Default Attack";
        this.isAtk = true;
        this.dmg = 30;
        this.cost = "";
        this.effect = "";
    }
    
    public Action(String actionName, String cost, String effect, boolean isAttack){
        if(actionName != null){
            this.actionName = actionName;
        }
        if(cost!= null){
            this.cost = new String(cost.toCharArray());
        }
        if(effect != null){
            this.effect = new String(effect.toCharArray());
        }
        this.isAtk = isAttack;
    }
    
    public Action(String actionName, int dmg, String cost, String effect, boolean isAttack){
        this(actionName, cost, effect, isAttack);
        this.dmg = dmg;
    }
    
//@Accessors
    public String getName(){
        return new String (actionName.toCharArray());
    }
    
    public boolean IsAttack(){
        return isAtk;
    }
    
    public int getDamage(){
        if(dmg <= 0){
            return 0;
        }
        return dmg;
    }
    
    public String getCost(){
        return new String (cost.toCharArray());
    }
    
    public String getEffect(){
        return new String(effect.toCharArray());
    }

    //@Mutator
    public void setName(String name){
        if(name == null){
            return;
        }
        
        this.actionName = name;
    }
    
    public void setIsAttack(boolean isAtk){
        this.isAtk = isAtk;
    }
    
    public void setDamage(int dmg){
        if(dmg <= 0){
            this.dmg = 0;
            return;
        }
        this.dmg = dmg;
    }
    
    public void setCost(String cost){
        if(cost == null){
            return;
        }
        this.cost = new String(cost.toCharArray());
    }
    
    public void setEffect(String effect){
        if(effect == null){
            return;
        }
        this.effect = new String(effect.toCharArray());
    }
    
    //@Methods
    public String toString(){
        String str = "";
        
        if(actionName != null && !actionName.equals("") && !allSpaces(actionName) ){
            str = actionName;
        }
        
        if(isAtk){
            str = str + "     " + dmg;
        }
        
        if(cost != null && !cost.equals("") && !allSpaces(cost)){
            str = str + "\n\t[Act Cost][" + cost + "]";
        }
        
        if( effect != null && !effect.equals("") && !allSpaces(effect)){
            str = str + "\n\t" + effect;
        }
        
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
