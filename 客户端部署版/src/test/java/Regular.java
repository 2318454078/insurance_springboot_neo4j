import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regular {
    public static String getQuestionResolution(String html){
        String regex = "宽限期([\\s\\S]*?)\n\n";
        Matcher matcher = Pattern.compile(regex).matcher(html);
        if (matcher.find()){
            return matcher.group(1).trim();
        }
        return "";
    }
    public static void main(String[] args){
        String html="  宽限期              您交纳首期保险费后，如果您以后到期未交纳保险费，自保险费应交日起60\n" +
                "                          日内为宽限期。宽限期内发生的保险事故，百年人寿仍会承担保险责任，但\n" +
                "                          在给付保险金时会扣除您欠交的保险费。 \n" +
                "                          如果您在宽限期结束之后仍未交纳当期保险费，则本合同自宽限期满的次日\n" +
                "                          零时起效力中止，但本合同另有约定的除外。  \n\n" +
                "sncdiudsh";
        System.out.println(Regular.getQuestionResolution(html));
    }
}
