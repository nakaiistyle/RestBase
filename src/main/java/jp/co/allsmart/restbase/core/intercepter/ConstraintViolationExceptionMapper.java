package jp.co.allsmart.restbase.core.intercepter;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.validation.Path.Node;
import javax.ws.rs.FormParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.CaseFormat;

import jp.co.allsmart.restbase.dto.response.ValidationError;
import jp.co.allsmart.restbase.util.StringUtil;

/**
 * バリデーション例外ハンドラ。
 *
 * バリデーションエラーの例外で、ログ出力/エラーレスポンス返送を行う。
 *
 * @author Nakai
 *
 */
@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    /*
     * JerseyがValidationExceptionMapperを登録しており、
     * ConstraintViolationExceptionをGlobalExceptionMapperでは処理出来ないため、個別のMapperを登録する。
     */

    /**
     * バリデーション例外ハンドリング。
     *
     * 発生した例外に応じ、ログ出力/レスポンス返送を行う。
     *
     * @param exception 例外
     */
    @Override
    public Response toResponse(final ConstraintViolationException exception) {


        ValidationError entity = createResponse(exception.getConstraintViolations());

        return Response.status(Status.BAD_REQUEST).entity(entity).type(MediaType.APPLICATION_JSON).build();

    }

     /**
      * エラー内容からレスポンスBODYを作成する。
      *
      * バリデーションエラー内容から、レスポンスパラメータを作成する。
      *
      * @param cv バリデーションエラー結果
      * @return バリデーションエラーパラメータ
      */
     private ValidationError createResponse(Set<ConstraintViolation<?>> cv) {

         ValidationError resp = new ValidationError();

        // エラー要因毎にループ
        cv.forEach(c -> {
            // エラーパラメータ名/メッセージを設定
            ValidationError.Detail detail = new ValidationError.Detail();
            detail.setParam(
                    getParameterName(c.getPropertyPath(), c.getLeafBean())
                    .orElse(""));
            detail.setMessage(c.getMessage());
            resp.getErrorParam().add(detail);
        });

        return resp;
    }

    /**
     * パラメータ物理名の取得。
     *
     * パラメータのプロパティ名称から、API仕様上の物理名を取得する。
     *
     * @param path エラー対象パス
     * @param obj エラー対象オブジェクト
     * @return パラメータ物理名
     */
    private Optional<String> getParameterName(Path path, Object obj) {

        String rv = null;

        Iterator<javax.validation.Path.Node> itor = path.iterator();
        while (itor.hasNext()) {
            Node node = itor.next();
            Field f = getField(obj.getClass(), node.getName());

            if(f != null){

                // PathParamアノテーションから取得
                PathParam pathParam = f.getAnnotation(PathParam.class);
                if(pathParam != null){
                    rv = pathParam.value();
                    continue;
                }

                // QueryParamアノテーションから取得
                QueryParam queryParam = f.getAnnotation(QueryParam.class);
                if(queryParam != null){
                    rv = queryParam.value();
                    continue;
                }

                // FormParamアノテーションから取得
                FormParam formParam = f.getAnnotation(FormParam.class);
                if(formParam != null){
                    rv = formParam.value();
                    continue;
                }

                // JsonPropertyアノテーションから取得
                JsonProperty jsonProperty = f.getAnnotation(JsonProperty.class);
                if(jsonProperty != null){
                    rv = jsonProperty.value();
                    continue;
                }

                // アノテーションから取得出来ない場合はプロパティ名をsnake_caseに変換
                if(StringUtil.hasValue(f.getName())){
                    rv = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, f.getName());
                }
            }
        }
        return Optional.ofNullable(rv);
    }

    /**
     * フィールド名から対象フィールドを取得する。
     *
     * @param clazz 対象クラス
     * @param fieldName フィールド名
     * @return フィールド
     */
    private Field getField(Class<?> clazz, String fieldName) {

        // フィールド名なし時はNULLを返却
        if(StringUtil.isEmpty(fieldName)){
            return null;
        }

        while (clazz != null) {
            try {
                return clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException nsfe) {
                clazz = clazz.getSuperclass();
            }
        }
        return null;
    }
}
