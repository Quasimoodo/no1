import java.io.File;
import java.io.FileReader;
class analyze_worker
{
    private boolean error_flag = false;
    private String keyword[]={"BEGIN","END","FOR","IF","THEN","ELSE"};
    private String keyword_ans[]={"Begin","End","For","If","Then","Else"};
    void work(char[] chars)//working function
    {
        String letter="";
        char ch;
        for(int i=0;i<chars.length;i++)
        {
            if(error_flag)//error break
            break;

            ch=chars[i];
            letter="";// origianl state placed
            if(ch==' '||ch=='\t'||ch=='\n'||ch=='\r')
            {
                //do nothing
            }
            else if(ch>='A'&&ch<='z')//isletter
            {
                boolean flag_key=false;// key or ident
                while(ch>='A'&&ch<='z')
                {
                    letter+=ch;
                    ch=chars[++i];
                }
                while(ch>='0'&&ch<='9'||ch>='A'&&ch<='z')
                {
                    letter+=ch;
                    ch=chars[++i];
                }
                i--;
                for(int j=0;j<6;j++)
                {
                    if(letter.equals(keyword[j]))
                    {
                        flag_key=true;
                        System.out.println(keyword_ans[j]);
                        break;
                    }
                }
                if(!flag_key)
                {
                    System.out.println("Ident("+letter+")");
                }

            }
            else if (ch>='0'&&ch<='9')
            {
                while(ch=='0')//ignore 0 behind
                {
                    ch=chars[++i];
                    if(!(ch>='0'&&ch<='9'))
                    letter+='0';

                }

                while(ch>='0'&&ch<='9')
                {
                    letter+=ch;
                    ch=chars[++i];
                }
                System.out.println("Int("+letter+")");
                i--;//prepare for the digt that not handle
            }
            else switch(ch)
            {
                case '+':
                    System.out.println("Plus");
                    break;
                case '*':
                     System.out.println("Star");
                     break;
                case ',':
                    System.out.println("Comma");
                    break;
                case '(':
                    System.out.println("LParenthesis");
                    break;
                case ')':
                    System.out.println("RParenthesis");
                    break;
                case ':':
                    ch=chars[i+1];
                    if(ch=='=')
                    {
                        System.out.println("Assign");
                        i++;//this digit has been read already
                    }
                    else
                    {
                        System.out.println("Colon");//only a colon
                    }
                    break;
                default: 
                    error_flag=true;
                    System.out.println("Unknown");

            }
            
        }
    }
}

public class Word_anzer 
{
    public static void main (String[] args)throws Exception
    {
        File file= new File(args[0]);
        FileReader reader=new FileReader(file);
        int length=(int)file.length();
        char buf[]=new char[length+1];
        reader.read(buf);
        reader.close();
        analyze_worker now= new analyze_worker();
        now.work(buf);
    }
     
}