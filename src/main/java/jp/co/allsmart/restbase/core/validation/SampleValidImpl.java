package jp.co.allsmart.restbase.core.validation;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SampleValidImpl implements ConstraintValidator<SampleValid, String>{

    static final String VALIDATE_PATTERN = "^[a-zA-Z0-9]*$";

    /** 最小長 */
    int min;
    /** 最大長 */
    int max;

    @Override
    public void initialize(SampleValid constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if(value == null){
            return true;
        }


        // 文字列パターンチェック
        Pattern p = Pattern.compile(VALIDATE_PATTERN);
        if(!p.matcher(value).matches()){
            return false;
        }

        // 文字列長チェック
        if(value.length() < min || value.length() > max){
            return false;
        }

        return true;
    }
}
