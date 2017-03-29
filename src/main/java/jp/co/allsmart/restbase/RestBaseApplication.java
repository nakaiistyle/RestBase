package jp.co.allsmart.restbase;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

@SpringBootApplication
public class RestBaseApplication extends SpringBootServletInitializer{

    public static void main(String[] args) {
        new RestBaseApplication()
        .configure(new SpringApplicationBuilder(RestBaseApplication.class))
        .run(args);
    }

    /**
     * Jerseyコンフィグ設定。
     *
     * @author Nakai
     *
     */
    @Component
    static class JerseryConfig extends ResourceConfig {
        public JerseryConfig() {
            packages(true, this.getClass().getPackage().toString());
            register(new ObjectMapperContextResolver());
        }
    }

    /**
     * Jersey用ObjectMapper設定。
     *
     * @author Nakai
     *
     */
    @Provider
    public static class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {

        /** ObjectMapper */
        private final ObjectMapper mapper;

        /**
         * ObjectMapper設定。
         */
        public ObjectMapperContextResolver() {
            mapper = new ObjectMapper();
            // JSONはsnake_caseを基本形式に設定
            mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

            // 不明なパラメータはデシリアライズ対象外として読み飛ばし
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            // NULLのプロパティはシリアライズ対象外
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

            // プロパティ名を利用したシリアライズ指定
            mapper.configure(MapperFeature.USE_STD_BEAN_NAMING, true);
        }

        /**
         * コンテキスト取得。
         */
        @Override
        public ObjectMapper getContext(Class<?> type) {
            return mapper;
        }
    }
}
