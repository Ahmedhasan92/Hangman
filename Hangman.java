import java.io.*;
import java.util.*;
public class Hangman {
    static String[] words ={"terminator", "banana", "computer", "cow", "rain", "water" };
    static int count =0;
    public static void hangmanImage(){
        if(count>=1)
            System.out.println("---------");
        if(count>=2)
            System.out.println("    |    ");
        if(count>=3)
            System.out.println("    |    ");
        if(count>=4)
            System.out.println("    0    ");
        if(count>=5)
            System.out.println("   /|\\   ");
        if(count>=6)
            System.out.println("    |    ");
        if(count>=7)
            System.out.println("   / \\   ");
    }

    public static void hang(String guess,StringBuffer ans, String word){
        StringBuffer newans=new StringBuffer("");
        for(int i=0;i<word.length();i++){
            if (word.charAt(i) == guess.charAt(0)) {
                newans.append(guess.charAt(0));
			} else if (ans.charAt(i) != '*') {
                newans.append(word.charAt(i));
			} else {
                newans.append("*");
			}
        }
        boolean f=true;
        for(int i=0;i<ans.length();i++){
            if(ans.charAt(i)!=newans.charAt(i)){
                f=false;
                break;
            }
        }
        if(f){
            count++;
            hangmanImage();
        }else{
            for(int i=0;i<ans.length();i++){
                ans.replace(i,i+1,newans.charAt(i)+"");
            }
        }
        boolean flag=true;
        for(int i=0;i<ans.length();i++){
            if(ans.charAt(i)!=word.charAt(i)){
                flag=false;
                break;
            }
        }
        if(flag){
            System.out.println("You win ! The word is "+word);
        }
    }
    public static boolean isValid(String guess){
        if(guess.length()>1)
            return false;
        if(guess.charAt(0)<97 || guess.charAt(0)>122)
            return false;
        return true;
    }
    public static void main(String args[]){
        System.out.println("Welcome to the game, Hangman!");
        Scanner sc=new Scanner(System.in);
        String word= words[(int)(Math.random()*words.length)];
        int t=0;
        while(t==0){
            count=0;
            StringBuffer ans=new StringBuffer("");
            for(int i=0;i<word.length();i++){
                ans.append("*");
            }
            boolean h=true;
            ArrayList<Character> ls=new ArrayList<>();
            for(int i=0;i<26;i++){
                ls.add((char)(i+97));
            }
            while(count<7 && ans.indexOf("*")!=-1){
                System.out.println("You have "+(7-count)+" choices");
                System.out.println("Available letters are :"+ls);
                System.out.println("Please guess a letter: ");
                System.out.println(ans);
                System.out.println("Type exit to end");
                String guess=sc.next();
                if(guess.equals("exit")){
                    System.exit(0);
                }
                if(guess.equals("hint") && h){
                    h=false;
                    for(int i=0;i<ans.length();i++){
                        if(ans.charAt(i)=='*'){
                            ans.setCharAt(i,word.charAt(i));
                            break;
                        }
                    }
                    continue;
                }
                if(!isValid(guess)){
                    System.out.println("Invalid Input");
                    continue;
                }
                if(ls.contains(guess.charAt(0)))
                    ls.remove(Character.valueOf(guess.charAt(0)));
                hang(guess,ans,word);
            }
            if(count==7){
                System.out.println("The word is "+word);
            }
        }
        
    }
}
