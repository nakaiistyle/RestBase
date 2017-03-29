package jp.co.allsmart.restbase.util;

public class StringUtil {

    /**
     * 文字列が null or length = 0をチェック。
     *
     * @param str チェック対象文字列
     * @return チェック結果
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }


    /**
     * 文字列がnullではなく、かつ{@literal length > 0 }であるかをチェック。
     *
     * @param str チェック対象文字列
     * @return 文字列がnullではなく、かつ{@literal length > 0 }のときtrue !isEmpty(String str) と等価
     */
    public static boolean hasValue(String str) {
        return ! isEmpty(str);
    }
}
