package yunha.config;

import yunha.annotation.Bean;
import yunha.annotation.Configuration;
import yunha.annotation.Scope;
import yunha.bean.TestBean;
import yunha.enums.ScopeType;

@Configuration
public class TestBeanConfig {

    @Bean
    @Scope(type = ScopeType.PROTOTYPE)
    public TestBean testBean() {
        TestBean testBean = new TestBean();
        testBean.setName("콩");
        return testBean;
    }

    @Bean(value = "bean2")
    public TestBean redBean() {
        TestBean testBean = new TestBean();
        testBean.setName("콩");
        return testBean;
    }
}
